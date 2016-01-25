/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.controller.support.SupportForm;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.dto.SpSupportDto;
import jp.co.sraw.entity.SpSupportTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.SpSupportTblRepository;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>UserServiceクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class SupportServiceImpl extends CommonService {

	@Autowired
	private SpSupportTblRepository spSupportTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(SupportServiceImpl.class);

	private static final String KBN1 = "1"; // 定数区分[0005]＝1:支援制度。2:機器設備利用
	private static final String KBN2 = "2"; // 定数区分[0005]＝1:支援制度。2:機器設備利用
	private static final String CODE_SYBCODE1 = "0021"; // 定数区分[0021] 支援制度
	private static final String CODE_SYBCODE2 = "0022"; // 定数区分[0022] 機器設備利用

	public static final String SEARCH_DATE_TYPE_BATCH = "batch"; // batch処理
	public static final String SEARCH_DATE_TYPE_CURRENT = "current"; // 公開期間中
	public static final String SEARCH_DATE_TYPE_PAST = "past"; // 過去情報
	public static final String SEARCH_DATE_TYPE_PRESENT = "present"; // 終了前

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<SpSupportDto> getDtoList(List<SpSupportTbl> list, Locale locale) {
		List<SpSupportDto> dtoList = new ArrayList<>();

		Map<String, MsCodeDto> sybCodeMap = new HashMap<String, MsCodeDto>();
		Map<String, String> sybCodeAbbrMap = new HashMap<String, String>();

		if (list.size() > 0) {
			SpSupportTbl tbl = list.get(0);
			String code = "";
			// 支援制度機器設備区分
			if (KBN1.equals(tbl.getSupportSpkikiKbn())) {
				code = CODE_SYBCODE1;
			}
			if (KBN2.equals(tbl.getSupportSpkikiKbn())) {
				code = CODE_SYBCODE2;
			}
			sybCodeMap = DbUtil.getJosuMap(code, locale);
			sybCodeAbbrMap = DbUtil.getJosuAbbrMap(code, locale);
		}

		for (int i = 0; i < list.size(); i++) {
			SpSupportDto dto = new SpSupportDto();
			dto = (SpSupportDto) objectUtil.getObjectCopyValue(dto, list.get(i));

			//
			if (StringUtil.isNotNull(dto.getSupportSybCode())) {
				String[] syblist = dto.getSupportSybCode().split(",");
				String separator = "";
				String name = "";
				String nameAbbr = "";
				for (int s = 0; s < syblist.length; s++) {
					String syb = syblist[s];
					name = name + separator + sybCodeMap.get(syb).getValue();
					nameAbbr = nameAbbr + separator + sybCodeAbbrMap.get(syb);
					separator = "/";
				}
				dto.setSupportSybCodeName(name);
				dto.setSupportSybCodeAbbrName(nameAbbr);
			}

			dtoList.add(dto);
		}
		return dtoList;
	}

	/**
	 * orderBy
	 *
	 * @return
	 */
	private Sort orderBy() {
		// 公開日（降順）
		return new Sort(Sort.Direction.DESC, "supportStartDate");
	}

	/**
	 * 一覧取得
	 *
	 * @param form
	 * @param locale
	 * @return
	 */
	public List<SpSupportDto> findSupportBatchTarget(SupportForm form, Locale locale) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		// 取得条件：公開
		Specification<SpSupportTbl> wherePublicFlag = StringUtil.isNull(form.getSearchPublicFlag()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("publicFlag"), form.getSearchPublicFlag());
					}
				};

		// 取得条件：日付
		Specification<SpSupportTbl> whereEnDay = StringUtil.isNull(form.getSearchDateType()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						// batch処理
						if (SEARCH_DATE_TYPE_BATCH.equals(form.getSearchDateType().toLowerCase())) {
							Predicate predicate = cb.conjunction();
							Predicate predicate2 = cb.conjunction();
							predicate2 = cb.and(predicate2, cb.equal(root.get("updDate").as(Date.class), today));
							predicate2 = cb.and(predicate2,
									cb.lessThan(root.get("supportStartDate").as(Date.class), today));

							predicate = cb.and(predicate, cb.equal(root.get("supportStartDate").as(Date.class), today));
							predicate = cb.or(predicate2);
							return predicate;
						}
						return null;
					}
				};

		List<SpSupportTbl> list = spSupportTblRepository.findAll(Specifications.where(wherePublicFlag).and(whereEnDay));

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return getDtoList(list, locale);
	}

	/**
	 * 一覧取得
	 *
	 * @param form
	 * @param locale
	 * @return
	 */
	public List<SpSupportDto> findAllLikeKeyWords(SupportForm form, Locale locale) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		// 取得条件：区分
		Specification<SpSupportTbl> whereSupportSpkikiKbn = StringUtil.isNull(form.getSearchSpkikiKbn()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("supportSpkikiKbn"), form.getSearchSpkikiKbn());
					}
				};

		// 取得条件：公開
		Specification<SpSupportTbl> wherePublicFlag = StringUtil.isNull(form.getSearchPublicFlag()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("publicFlag"), form.getSearchPublicFlag());
					}
				};

		// 取得条件：HIRAKUリンク区分
		Specification<SpSupportTbl> whereSupportHirakuKbn = StringUtil.isNull(form.getSearchHirakuKbn()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("supportHirakuKbn"), form.getSearchHirakuKbn());
					}
				};

		// 取得条件：地域区分
		Specification<SpSupportTbl> whereSupportAreaKbn = StringUtil.isNull(form.getSearchAreaKbn()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("supportAreaKbn"), form.getSearchAreaKbn());
					}
				};

		// 取得条件：組織コード
		Specification<SpSupportTbl> wherePartyCode = StringUtil.isNull(form.getSearchPartyCode()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("partyCode"), form.getSearchPartyCode());
					}
				};

		// 取得条件：キーワード
		Specification<SpSupportTbl> whereKeywords = StringUtil.isNull(form.getSearchKeyword()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						String[] keywords = form.getSearchKeyword().split(",");
						Predicate predicate = cb.conjunction();
						for (int i = 0; i < keywords.length; i++) {
							String keyword = keywords[i];
							predicate = cb.and(predicate, cb.like(root.get("supportKeyword"), "%" + keyword + "%"));
						}
						return predicate;
					}
				};

		// 取得条件：支援制度種別コード
		Specification<SpSupportTbl> whereSyb = StringUtil.isNull(form.getSearchSybCode()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						String[] sybcodes = form.getSearchSybCodeArray();
						Predicate predicate = cb.conjunction();
						for (int i = 0; i < sybcodes.length; i++) {
							String sybcode = sybcodes[i];
							predicate = cb.and(predicate, cb.like(root.get("supportSybCode"), "%" + sybcode + "%"));
						}
						return predicate;
					}
				};

		// 取得条件：日付(期間)
		Specification<SpSupportTbl> whereDate = StringUtil.isNull(form.getSearchDateType()) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						// batch処理
						if (SEARCH_DATE_TYPE_BATCH.equals(form.getSearchDateType().toLowerCase())) {
							Predicate predicate = cb.conjunction();
							Predicate predicate2 = cb.conjunction();
							predicate2 = cb.and(predicate2, cb.equal(root.get("updDate").as(Date.class), today));
							predicate2 = cb.and(predicate2,
									cb.lessThan(root.get("supportStartDate").as(Date.class), today));
							predicate = cb.and(predicate, cb.equal(root.get("supportStartDate").as(Date.class), today));
							predicate = cb.or(predicate2);
							return predicate;
						}
						// 期間中
						if (SEARCH_DATE_TYPE_CURRENT.equals(form.getSearchDateType())) {
							Predicate predicate = cb.conjunction();
							predicate = cb.and(predicate,
									cb.lessThanOrEqualTo(root.get("supportStartDate").as(Date.class), today));
							predicate = cb.and(predicate,
									cb.greaterThanOrEqualTo(root.get("supportEndDate").as(Date.class), today));
							return predicate;
						}
						// 過去情報
						if (SEARCH_DATE_TYPE_PAST.equals(form.getSearchDateType())) {
							return cb.lessThan(root.get("supportEndDate").as(Date.class), today);
						}
						// 終了前
						if (SEARCH_DATE_TYPE_PRESENT.equals(form.getSearchDateType())) {
							return cb.greaterThanOrEqualTo(root.get("supportEndDate").as(Date.class), today);
						}
						return null;
					}
				};

		List<SpSupportTbl> list = spSupportTblRepository.findAll(
				Specifications.where(whereSupportSpkikiKbn).and(whereSupportHirakuKbn).and(whereSupportAreaKbn)
						.and(wherePublicFlag).and(wherePartyCode).and(whereKeywords).and(whereSyb).and(whereDate),
				orderBy());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return getDtoList(list, locale);
	}

	/**
	 * 削除
	 *
	 * @param form
	 * @return
	 */
	@Transactional
	public boolean delete(SupportForm form) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}
		try {

			int c = spSupportTblRepository.delete(form.getSupportKey(), form.getUpdDate());

			if (c > 0) {
				spSupportTblRepository.flush();
				logger.infoCode("I0002"); // I0002=メソッド終了:{0}
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=削除に失敗しました。{0}
		}
		return false;
	}

	/**
	 * 新規追加、更新
	 *
	 * @param form
	 * @param userInfo
	 * @return
	 */
	@Transactional
	public boolean update(SupportForm form, UserInfo userInfo) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}
		try {
			SpSupportTbl entity = new SpSupportTbl();

			// 更新の場合
			if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
				entity = findOne(form.getSupportKey(), form.getUpdDate());
				if (entity == null) {
					throw new Exception();
				}
			}
			//
			entity.setSupportSpkikiKbn(form.getSupportSpkikiKbn());
			entity.setSupportHirakuKbn(form.getSupportHirakuKbn());
			entity.setSupportSybCode(form.getSupportSybCode());
			entity.setSupportAreaKbn(form.getSupportAreaKbn());
			entity.setSupportKeyword(form.getSupportKeyword());
			entity.setSupportStartDate(DateUtil.formatTimestampStart(
					DateUtil.getTimestamp(form.getSupportStartDate(), CommonConst.DEFAULT_YYYYMMDD)));
			entity.setSupportEndDate(DateUtil
					.formatTimestampEnd(DateUtil.getTimestamp(form.getSupportEndDate(), CommonConst.DEFAULT_YYYYMMDD)));
			entity.setSupportTitle(form.getSupportTitle());
			entity.setSupportContent(form.getSupportContent());
			entity.setUrl(form.getUrl());
			entity.setPartyCode(userInfo.getTargetPartyCode());

			// 更新以外の場合(新規、コピー)
			if (!CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
				entity.setSupportInsDate(DateUtil.getNowTimestamp());
			}
			entity.setPublicFlag(form.getPublicFlag());
			//
			entity.setUpdUserKey(userInfo.getLoginUserKey());
			entity.setUpdDate(DateUtil.getNowTimestamp());
			//
			entity = spSupportTblRepository.saveAndFlush(entity);

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
	 * 支援制度キー指定取得
	 *
	 * @param supportKey
	 * @return
	 */
	public SpSupportTbl findOne(final String supportKey) {
		return spSupportTblRepository.getOne(supportKey);
	}

	/**
	 * 支援制度キーとデータ更新日指定取得
	 *
	 * @param supportKey
	 * @param updDate
	 * @return
	 */
	public SpSupportTbl findOne(final String supportKey, final Timestamp updDate) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// キー
		Specification<SpSupportTbl> whereSupportKey = StringUtil.isNull(supportKey) ? null
				: new Specification<SpSupportTbl>() {
					@Override
					public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("supportKey"), supportKey);
					}
				};
		// データ更新日
		Specification<SpSupportTbl> whereUpdDate = DateUtil.isNull(updDate) ? null : new Specification<SpSupportTbl>() {
			@Override
			public Predicate toPredicate(Root<SpSupportTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("updDate"), updDate);
			}
		};

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return spSupportTblRepository.findOne(Specifications.where(whereSupportKey).and(whereUpdDate));
	}

}
