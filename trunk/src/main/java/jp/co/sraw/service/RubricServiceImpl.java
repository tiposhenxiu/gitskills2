/*
* ファイル名：RubricServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/05   etoh        新規作成
*/
package jp.co.sraw.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonService;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.controller.skill.RubricEditForm;
import jp.co.sraw.entity.NrRubricTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.oxm.rubric.Rubric;
import jp.co.sraw.oxm.rubric.RubricCategory;
import jp.co.sraw.repository.NrRubricTblRepository;
import jp.co.sraw.util.DateUtil;

/**
 * <B>ルーブリック関連のビジネスロジック</B>
 * <P>
 */
@Service
@Transactional(readOnly = false)
public class RubricServiceImpl extends CommonService {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(RubricServiceImpl.class);

	@Autowired
	private NrRubricTblRepository rubricRepository;

	@Autowired
	private Marshaller xmlMarshaller;
	@Autowired
	private Unmarshaller xmlUnmarshaller;

	@Override
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<NrRubricTbl> findAll() {
		return rubricRepository.findAllByOrderByRubricName();
	}

	private NrRubricTbl findOne(final String rkey, final Timestamp dt) {
		return rubricRepository.findByRubricKeyAndUpdDate(rkey, dt);
	}

	/**
	 * View用。
	 *
	 * @param rkey
	 * @return
	 */
	public Rubric findOne(String rkey) {
		NrRubricTbl entity = rubricRepository.findOne(rkey);
		return unmarshal(entity.getRubricContents());
	}

	@Transactional
	public boolean update(RubricEditForm form, UserInfo userInfo) {
		logger.infoCode("I0001", form.getPageMode());

		try {
			NrRubricTbl entity = new NrRubricTbl();
			if (form.getPageMode().equals(CommonConst.PAGE_MODE_EDIT)) {
				entity = findOne(form.getKey(), form.getUpdDate());
				if (entity == null) {
					throw new RuntimeException(form.getKey() + ": not such rubric, or edited by someone else.");
				}
			}
			entity.setRubricName(form.getName());
			entity.setRubricMemo(form.getSummary());
			Rubric rub = form.getRubric();
			rub.setName(form.getName());
			rub.setSummary(form.getSummary());
			entity.setRubricContents(marshal(rub));
			entity.setUpdUserKey(userInfo.getLoginUserKey());
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity = rubricRepository.saveAndFlush(entity);
			if (entity == null) {
				throw new RuntimeException("failed to saveAndFlush().");
			}
			logger.infoCode("I0002");
			return true;
		} catch (Exception e) {
			logger.errorCode("E1007", e);
		}
		return false;
	}

	@Transactional
	public void delete(String rkey) {
		rubricRepository.delete(rkey);
	}

	private Rubric unmarshal(String content) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8.name()));
			StreamSource src = new StreamSource(bais);
			Rubric rubric = (Rubric) xmlUnmarshaller.unmarshal(src);
			return rubric;
		} catch (UnsupportedEncodingException e) {
			logger.errorCode("E013", e);
		} catch (XmlMappingException e) {
			logger.error("failed to map from XML to Object.");
			logger.errorCode("E013", e);
		} catch (IOException e) {
			logger.errorCode("E013", e);
		}
		return null;
	}

	private String marshal(Rubric rub) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamResult res = new StreamResult(baos);
		try {
			xmlMarshaller.marshal(rub, res);
			return baos.toString(StandardCharsets.UTF_8.name());
		} catch (XmlMappingException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}

		return null;
	}

	/**
	 * Edit専用。
	 *
	 * @param editForm
	 * @param rkey
	 */
	public void populateFormForEdit(RubricEditForm editForm, String rkey) {
		NrRubricTbl rubric = rubricRepository.findOne(rkey);
		editForm.setKey(rkey);
		editForm.setUpdDate(rubric.getUpdDate());

		populateRubric(editForm, rubric.getRubricContents());
	}

	/**
	 * Create/Edit共用。
	 *
	 * @param editForm
	 * @param rkey
	 *            Can be null/empty.
	 * @param updDate
	 *            Can be null/empty.
	 * @param xmlString
	 */
	public void populateFormForEdit(RubricEditForm editForm, String rkey, Timestamp updDate, String xmlString) {
		editForm.setKey(rkey); // can be null/empty.
		editForm.setUpdDate(updDate); // can be null/empty.

		populateRubric(editForm, xmlString);
	}

	private void populateRubric(RubricEditForm editForm, String xmlString) {
		Rubric rub = unmarshal(xmlString);
		editForm.setName(rub.getName());
		editForm.setSummary(rub.getSummary());
		editForm.setRubric(rub);
	}

	/**
	 * ルーブリックから、特定のレンズに合わない小項目を削除する。 この結果、中項目のchildListがemptyになる可能性がある。
	 * 
	 * @param rub
	 * @param lensId
	 */
	public void filterByLens(Rubric rub, int lensId) {
		if (lensId == CommonConst.LENSID_FULL) {
			return;
		}
		rub.getCategoryList().forEach(cat -> {
			cat.getChildList().forEach(subc -> {
				Iterator<RubricCategory> ite = subc.getChildList().iterator();
				while (ite.hasNext()) {
					RubricCategory p = ite.next();
					if (!RubricCategory.testLensFor(p.getLens(), lensId)) {
						ite.remove();
					}
				}
			});
		});

	}

	/**
	 * ルーブリックから、特定のレンズに合う小項目のabilityCode一覧を生成。
	 * 
	 * @param rub
	 * @param lensId
	 * @return
	 */
	public List<String> codesThroughLens(Rubric rub, int lensId) {
		List<String> codes = new ArrayList<String>();
		rub.getCategoryList().forEach(cat -> {
			cat.getChildList().forEach(subc -> {
				subc.getChildList().forEach(item -> {
					if (lensId == CommonConst.LENSID_FULL || RubricCategory.testLensFor(item.getLens(), lensId)) {
						codes.add(item.getAbilityCode());
					}
				});
			});
		});
		return codes;
	}
}
