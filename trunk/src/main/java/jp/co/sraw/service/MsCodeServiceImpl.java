/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonService;
import jp.co.sraw.controller.admin.MsCodeForm;
import jp.co.sraw.entity.MsCodeTbl;
import jp.co.sraw.entity.MsCodeTblPK;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.MsCodeTblRepository;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;

/**
* <B>UserServiceクラス</B>
* <P>
* ユーザーサービスのメソッドを提供する
*/
@Service
@Transactional(readOnly = true)
public class MsCodeServiceImpl extends CommonService {

	@Autowired
	private MsCodeTblRepository msCodeTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MsCodeServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * orderBy
	 *
	 * @return
	 */
	private Sort orderBy() {
		return new Sort(Sort.Direction.ASC, "hyojiSrt", "id.josuKbn", "id.josuCode");
	}

	/**
	 * 全データ取得
	 *
	 * @return
	 */
	public List<MsCodeTbl> findAll() {
		return this.findAllJosuKbn(null);
	}

	/**
	 * 定数区分
	 * @return
	 */
	public List<MsCodeTbl> findAllJosuKbn(final String josuKbn) {
		return findAllJosuKbnAndUseFlag(josuKbn, null);
	}

	/***
	 * 定数区分、有効フラグ
	 *
	 * @param josuKbn
	 * @param useFlag
	 * @return
	 */
	public List<MsCodeTbl> findAllJosuKbnAndUseFlag(final String josuKbn, final String useFlag) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 定数区分
		Specification<MsCodeTbl> whereKbn = StringUtil.isNull(josuKbn) ? null : new Specification<MsCodeTbl>(){
			@Override
			public Predicate toPredicate(Root<MsCodeTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("id").get("josuKbn"), josuKbn);
			}
		};
		Specification<MsCodeTbl> whereUseFlag = StringUtil.isNull(useFlag) ? null : new Specification<MsCodeTbl>(){
			@Override
			public Predicate toPredicate(Root<MsCodeTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("useFlag"), useFlag);
			}
		};

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return msCodeTblRepository.findAll(Specifications.where(whereKbn).and(whereUseFlag), orderBy());
	}

	/**
	 * 定数区分と定数コード指定取得
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @return
	 */
	public MsCodeTbl findOne(final String josuKbn, final String josuCode) {
		try {
			MsCodeTblPK id = new MsCodeTblPK();
			id.setJosuKbn(josuKbn);
			id.setJosuCode(josuCode);
			return msCodeTblRepository.findOne(id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 定数区分と定数コード指定存在チェック
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @return
	 */
	public boolean exists(final String josuKbn, final String josuCode) {
		try {
			MsCodeTblPK id = new MsCodeTblPK();
			id.setJosuKbn(josuKbn);
			id.setJosuCode(josuCode);
			return msCodeTblRepository.exists(id);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 定数区分と定数コード指定存在チェック
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @return
	 */
	public boolean exists(final MsCodeTblPK id) {
		try {
			return msCodeTblRepository.exists(id);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 新規追加、更新
	 *
	 * @param form
	 * @param userInfo
	 * @return
	 */
	@Transactional
	public boolean update(MsCodeForm form, String loginUserKey) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}
		try {
			MsCodeTbl entity = new MsCodeTbl();

			// 更新の場合
			if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
				entity = findOne(form.getJosuKbn(), form.getJosuCode(), form.getUpdDate());
				if (entity == null) {
					//throw new Exception();
					return false;
				}
			} else {
				// 新規の場合
				MsCodeTblPK pk = new MsCodeTblPK();
				pk.setJosuKbn(form.getJosuKbn());
				pk.setJosuCode(form.getJosuCode());
				entity.setId(pk);
			}

			entity.setJosuName(form.getJosuName());
			entity.setJosuNameEn(form.getJosuNameEn());
			entity.setJosuNameAbbr(form.getJosuNameAbbr());
			entity.setJosuNameAbbrEn(form.getJosuNameAbbrEn());
			entity.setSntaZksei1Txt(form.getSntaZksei1Txt());
			entity.setSntaZksei2Txt(form.getSntaZksei2Txt());
			entity.setSntaZksei3Txt(form.getSntaZksei3Txt());
			entity.setSntaZksei1Num(form.getSntaZksei1Num());
			entity.setSntaZksei2Num(form.getSntaZksei2Num());
			entity.setSntaZksei3Num(form.getSntaZksei3Num());
			entity.setCommentProperty(form.getCommentProperty());
			entity.setUseFlag(form.getUseFlag());
			entity.setHyojiSrt(form.getHyojiSrt());
			//
			entity.setUpdUserKey(loginUserKey);
			entity.setUpdDate(DateUtil.getNowTimestamp());
			//
			entity = msCodeTblRepository.saveAndFlush(entity);

			if (entity != null) {
				logger.infoCode("I0002"); // I0002=メソッド終了:{0}
				return true;
			}

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

	/**
	 * 削除
	 *
	 * @param form
	 * @return
	 */
	@Transactional
	public boolean detele(final MsCodeForm form, final String loginUserKey) {
		return detele(form.getJosuKbn(),
				form.getJosuCode(),
				form.getUpdDate(),
				loginUserKey);
	}

	/**
	 * 削除
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @param updDate
	 * @return
	 */
	@Transactional
	public boolean detele(final String josuKbn,
			final String josuCode,
			final Timestamp updDate,
			final String loginUserKey
			) {
		try {
			int c = msCodeTblRepository.delete(
					josuKbn,
					josuCode,
					updDate);
			if (c > 0) {
				msCodeTblRepository.flush();
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=削除に失敗しました。{0}
		}
		return false;
	}

	/**
	 * プライマリキーとデータ更新日指定取得
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @param updDate
	 * @return
	 */
	public MsCodeTbl findOne(final String josuKbn, final String josuCode, final Timestamp updDate) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// キー
		Specification<MsCodeTbl> wherePkKey = (StringUtil.isNull(josuKbn) || StringUtil.isNull(josuCode)) ? null
				: new Specification<MsCodeTbl>() {
					@Override
					public Predicate toPredicate(Root<MsCodeTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.and(cb.equal(root.get("id").get("josuKbn"), josuKbn), cb.equal(root.get("id").get("josuCode"), josuCode));
					}
				};
		// データ更新日
		Specification<MsCodeTbl> whereUpdDate = DateUtil.isNull(updDate) ? null
				: new Specification<MsCodeTbl>() {
					@Override
					public Predicate toPredicate(Root<MsCodeTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("updDate"), updDate);
					}
				};

		MsCodeTbl m = msCodeTblRepository.findOne(Specifications.where(wherePkKey).and(whereUpdDate));

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return m;
	}
}
