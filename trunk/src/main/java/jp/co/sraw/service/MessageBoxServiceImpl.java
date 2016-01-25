/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonService;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.controller.messagebox.MessageBoxForm;
import jp.co.sraw.dto.MessageBoxDto;
import jp.co.sraw.entity.UsMessageBoxTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.UsMessageBoxTblRepository;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>UserServiceクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class MessageBoxServiceImpl extends CommonService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UsMessageBoxTblRepository usMessageBoxTblRepository;

	@Autowired
	private UserServiceImpl userServiceImpl;


	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MessageBoxServiceImpl.class);

	private static final String CODE_SYBCODE = "0021";

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@SuppressWarnings("unchecked")
	public List<MessageBoxDto> findAllMessage(UserInfo userInfo,Boolean messageSendFlag) {
		logger.infoCode("I0001");
		String partyCode = null;
		String roleCode = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append("    A.MESSAGE_KEY");
		sb.append("  , A.USER_KEY");
		sb.append("  , A.SEND_DATE");
		sb.append("  , A.MESSAGE_TITLE");
		sb.append("  , A.MESSAGE_CONTENTS");
		sb.append("  , A.REF_MESSAGE_KEY");
		sb.append("  , A.MAKE_USER_KEY");
		sb.append("  , A.UPD_DATE");
		sb.append("  , A.UPD_USER_KEY");
		sb.append("  , B.USER_FAMILY_NAME");
		sb.append("  , B.USER_FAMILY_NAME_KN");
		sb.append("  , B.USER_FAMILY_NAME_EN");
		sb.append("  , B.USER_MIDDLE_NAME");
		sb.append("  , B.USER_MIDDLE_NAME_KN");
		sb.append("  , B.USER_MIDDLE_NAME_EN");
		sb.append("  , B.USER_NAME");
		sb.append("  , B.USER_NAME_KN");
		sb.append("  , B.USER_NAME_EN");
		sb.append("  , C.USER_FAMILY_NAME AS USER_FAMILY_NAME2");
		sb.append("  , C.USER_FAMILY_NAME_KN AS USER_FAMILY_NAME_KN2");
		sb.append("  , C.USER_FAMILY_NAME_EN AS USER_FAMILY_NAME_EN2");
		sb.append("  , C.USER_MIDDLE_NAME AS USER_MIDDLE_NAME2");
		sb.append("  , C.USER_MIDDLE_NAME_KN AS USER_MIDDLE_NAME_KN2");
		sb.append("  , C.USER_MIDDLE_NAME_EN AS USER_MIDDLE_NAME_EN2");
		sb.append("  , C.USER_NAME AS USER_NAME2");
		sb.append("  , C.USER_NAME_KN AS USER_NAME_KN2");
		sb.append("  , C.USER_NAME_EN AS USER_NAME_EN2");
		sb.append(" FROM");
		sb.append("  US_MESSAGE_BOX_TBL A ");
		sb.append("  INNER JOIN US_USER_TBL B ");
		sb.append("    ON A.USER_KEY = B.USER_KEY ");

			sb.append("  INNER JOIN US_USER_TBL C ");
			sb.append("    ON A.MAKE_USER_KEY = C.USER_KEY ");

		if(messageSendFlag){
		sb.append(" WHERE");
			sb.append("    A.MAKE_USER_KEY = :userKey");
		}else{
			sb.append(" WHERE");
			sb.append("    A.USER_KEY = :userKey");
		}
//		sb.append("    1 = 1");

		sb.append(" GROUP BY");
		sb.append("    A.MESSAGE_KEY");
		sb.append("  , A.USER_KEY");
		sb.append("  , A.SEND_DATE");
		sb.append("  , A.MESSAGE_TITLE");
		sb.append("  , A.MESSAGE_CONTENTS");
		sb.append("  , A.REF_MESSAGE_KEY");
		sb.append("  , A.MAKE_USER_KEY");
		sb.append("  , A.UPD_DATE");
		sb.append("  , A.UPD_USER_KEY");
		sb.append("  , B.USER_FAMILY_NAME");
		sb.append("  , B.USER_FAMILY_NAME_KN");
		sb.append("  , B.USER_FAMILY_NAME_EN");
		sb.append("  , B.USER_MIDDLE_NAME");
		sb.append("  , B.USER_MIDDLE_NAME_KN");
		sb.append("  , B.USER_MIDDLE_NAME_EN");
		sb.append("  , B.USER_NAME");
		sb.append("  , B.USER_NAME_KN");
		sb.append("  , B.USER_NAME_EN");
		sb.append("  , C.USER_FAMILY_NAME");
		sb.append("  , C.USER_FAMILY_NAME_KN");
		sb.append("  , C.USER_FAMILY_NAME_EN");
		sb.append("  , C.USER_MIDDLE_NAME");
		sb.append("  , C.USER_MIDDLE_NAME_KN");
		sb.append("  , C.USER_MIDDLE_NAME_EN");
		sb.append("  , C.USER_NAME");
		sb.append("  , C.USER_NAME_KN");
		sb.append("  , C.USER_NAME_EN");
		sb.append(" ORDER BY");
		sb.append("  A.SEND_DATE DESC");
		String sql = sb.toString();

		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("userKey", userInfo.getTargetUserKey());
//		query.setParameter("userKey", "0000000001");

		List<MessageBoxDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			MessageBoxDto dto = new MessageBoxDto();
			dto = (MessageBoxDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}


	@Transactional
	public boolean update(UserInfo userInfo, MessageBoxForm form) {
		logger.infoCode("I0001");
		try {
			UsMessageBoxTbl entity = new UsMessageBoxTbl();
			if (form.getPageMode().equals(CommonConst.PAGE_MODE_EDIT)) {
				entity = findOne(userInfo, form);
				if (entity == null) {
					throw new Exception();
				}
			} else {
				UsUserTbl usUserTbl = new UsUserTbl();
				usUserTbl.setUserKey(form.getMakeUserKey());
				entity.setUsUserTbl(usUserTbl);
			}
			entity.setUpdUserKey(userInfo.getLoginUserKey());
			//
			entity.setMakeUserKey(userInfo.getTargetUserKey());
			entity.setMessageContents(form.getMessageContents());
			entity.setMessageKey(form.getMessageKey());
			entity.setMessageTitle(form.getMessageTitle());
			entity.setRefMessageKey(form.getRefMessageKey());
			entity.setSendDate(DateUtil.getTimestamp(form.getSendDate(), "yyyy-MM-dd"));

			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity = usMessageBoxTblRepository.saveAndFlush(entity);

			if (entity != null) {
				logger.infoCode("I0002");
				return true;
			}

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

	@Transactional
	public boolean delete(UserInfo userInfo, MessageBoxForm form) {
		logger.infoCode("I0001");
		try {

			int c = usMessageBoxTblRepository.delete(form.getMessageKey(), form.getUpdDateAsTimestamp());

			if (c > 0) {
				usMessageBoxTblRepository.flush();
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=削除に失敗しました。{0}
		}
		return false;
	}

	/**
	 * eventKey指定取得
	 *
	 * @param eventKey
	 * @return
	 */
	public UsMessageBoxTbl getOne(final String messageKey) {
		return usMessageBoxTblRepository.getOne(messageKey);
	}


	public UsMessageBoxTbl findOne(UserInfo userInfo, MessageBoxForm form) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 定数区分
		Specification<UsMessageBoxTbl> whereMessageKey = StringUtil.isNull(form.getMessageKey()) ? null
				: new Specification<UsMessageBoxTbl>() {
					@Override
					public Predicate toPredicate(Root<UsMessageBoxTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("messageKey"), form.getMessageKey ());
					}
				};
		Specification<UsMessageBoxTbl> whereUpdDate = StringUtil.isNull(form.getUpdDate()) ? null
				: new Specification<UsMessageBoxTbl>() {
					@Override
					public Predicate toPredicate(Root<UsMessageBoxTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("updDate"), form.getUpdDateAsTimestamp());
					}
				};

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return usMessageBoxTblRepository.findOne(Specifications.where(whereMessageKey).and(whereUpdDate));
	}

}
