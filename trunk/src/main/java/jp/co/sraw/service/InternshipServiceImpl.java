/*
* ファイル名：InternshipServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
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
import jp.co.sraw.controller.internship.InternshipForm;
import jp.co.sraw.controller.internship.ItInternRecruitForm;
import jp.co.sraw.dto.InternshipApplicantDto;
import jp.co.sraw.dto.InternshipViewDto;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.CmInfoPublicTbl;
import jp.co.sraw.entity.CmInfoPublicTblPK;
import jp.co.sraw.entity.CmInfoTbl;
import jp.co.sraw.entity.ItInternPublicTbl;
import jp.co.sraw.entity.ItInternPublicTblPK;
import jp.co.sraw.entity.ItInternRecruitTbl;
import jp.co.sraw.entity.ItInternRecruitTblPK;
import jp.co.sraw.entity.ItInternRecruitUploadTbl;
import jp.co.sraw.entity.ItInternRecruitUploadTblPK;
import jp.co.sraw.entity.ItInternRecruitView;
import jp.co.sraw.entity.ItInternRelSubjectTbl;
import jp.co.sraw.entity.ItInternTbl;
import jp.co.sraw.entity.ItInternUploadTbl;
import jp.co.sraw.entity.ItInternView;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.CmInfoPublicTblRepository;
import jp.co.sraw.repository.CmInfoTblRepository;
import jp.co.sraw.repository.ItInternPublicTblRepository;
import jp.co.sraw.repository.ItInternRecruitTblRepository;
import jp.co.sraw.repository.ItInternRecruitUploadTblRepository;
import jp.co.sraw.repository.ItInternRecruitViewRepository;
import jp.co.sraw.repository.ItInternRelSubjectTblRepository;
import jp.co.sraw.repository.ItInternTblRepository;
import jp.co.sraw.repository.ItInternUploadTblRepository;
import jp.co.sraw.repository.ItInternViewRepository;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>EventServiceクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = false)
public class InternshipServiceImpl extends CommonService {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(InternshipServiceImpl.class);

	@Autowired
	private ItInternViewRepository itInternViewRepository;

	@Autowired
	private ItInternRecruitViewRepository itInternRecruitViewRepository;

	@Autowired
	private ItInternTblRepository itInternTblRepository;

	@Autowired
	private ItInternUploadTblRepository itInternUploadTblRepository;

	@Autowired
	private ItInternPublicTblRepository itInternPublicTblRepository;

	@Autowired
	private ItInternRecruitTblRepository itInternRecruitTblRepository;

	@Autowired
	private ItInternRecruitUploadTblRepository itInternRecruitUploadTblRepository;

	@Autowired
	private ItInternRelSubjectTblRepository itInternRelSubjectTblRepository;

	@Autowired
	private CmInfoTblRepository cmInfoTblRepository;

	@Autowired
	private CmInfoPublicTblRepository cmInfoPublicTblRepository;

	@Autowired
	private EntityManager entityManager;

	public static final String SEARCH_DATE_TYPE_BATCH = "batch"; // batch処理
	public static final String SEARCH_DATE_TYPE_CURRENT = "current"; // 公開期間中
	public static final String SEARCH_DATE_TYPE_PAST = "past"; // 過去情報
	public static final String SEARCH_DATE_TYPE_PRESENT = "present"; // 終了前

	public static final String SEARCH_VIEW_TYPE_1 = "1"; // 個人ユーザの権限
	public static final String SEARCH_VIEW_TYPE_2 = "2"; // HIRAKU 運営協議会事務局の権限
	public static final String SEARCH_VIEW_TYPE_3 = "3"; // 共同実施機関窓口（山大・徳大）、連携大学の権限
	public static final String SEARCH_VIEW_TYPE_4 = "4"; // 連携期間窓口（企業、研究所）の権限

	// インターンシップ区分
	private static final String KBN = "0002";

	private static final String DECISION_KBN = "0026"; // 定数区分[0026]＝0:未判定。1:合格。2:不合格。3:辞退。

	private static final String PUBLIC_FLAG = "1"; // 固定値: 公開

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<InternshipViewDto> getDtoList(List<ItInternTbl> list, Locale locale) {
		List<InternshipViewDto> dtoList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			InternshipViewDto dto = new InternshipViewDto();
			dto = (InternshipViewDto) objectUtil.getObjectCopyValue(dto, list.get(i));
			dtoList.add(dto);
		}
		return dtoList;
	}

	/**
	 *
	 * @param userInfo
	 * @param locale
	 * @return
	 */
	public Map<String, List<InternshipViewDto>> getDataList(UserInfo userInfo, Locale locale) {

		Map<String, List<InternshipViewDto>> mapList = new HashMap<String, List<InternshipViewDto>>();

		List<MsCodeDto> kbnList = DbUtil.getJosuList(KBN, locale);

		List<ItInternView> resultList = new ArrayList<>();

		String type = SEARCH_VIEW_TYPE_1;
		String searchPartyCode = userInfo.getTargetPartyCode();
		String searchRole = null;
		String searchDateType = SEARCH_DATE_TYPE_CURRENT;
		String searchPublicFlag = PUBLIC_FLAG;

		if (userInfo.isUser1() || userInfo.isUser2() || userInfo.isUser3() || userInfo.isUser4() || userInfo.isUser5()) {
			// 個人ユーザ
			searchDateType = SEARCH_DATE_TYPE_CURRENT;
		} else if (userInfo.isMgmt1()) {
			// 事務局
			type = SEARCH_VIEW_TYPE_2;
			searchPublicFlag = null;
		} else if (userInfo.isMgmt2() || userInfo.isMgmt3()) {
			// 共同実施機関窓口、連携大学
			type = SEARCH_VIEW_TYPE_3;
		} else if (userInfo.isMgmt4()) {
			// 上記以外
			type = SEARCH_VIEW_TYPE_4;
		}

		resultList = this.findAllInternshipView(type, searchPartyCode, searchRole, searchPublicFlag, searchDateType);

		// 区分から一覧を生成
		for (int i = 0; i < kbnList.size(); i++) {
			MsCodeDto dto = kbnList.get(i);
			//
			List<InternshipViewDto> inteList = new ArrayList<InternshipViewDto>();
			for (int j = 0; j < resultList.size(); j++) {
				ItInternView itInternView = resultList.get(j);
				// 区分毎に振り分け
				if (itInternView.getInternshipKbn().contains(dto.getCode())) {
					InternshipViewDto row = getViewDto(itInternView);
					inteList.add(row);
				}
			}
			mapList.put(dto.getCode(), inteList);
		}
		return mapList;
	}

	public InternshipViewDto getViewDto(ItInternView itInternView) {
		if (itInternView == null)
			return null;
		InternshipViewDto row = new InternshipViewDto();
		row.setInternshipKey(itInternView.getInternshipKey());
		row.setInternshipKbn(itInternView.getInternshipKbn());
		row.setEventUnit(itInternView.getEventUnit());
		row.setInternshipEndDate(itInternView.getInternshipEndDate());
		row.setPartyCode(itInternView.getPartyCode());
		row.setSubjectInsKbn(itInternView.getSubjectInsKbn());
		row.setInternshipTelno(itInternView.getInternshipTelno());
		row.setInternshipRange(itInternView.getInternshipRange());
		row.setInternshipStartDate(itInternView.getInternshipStartDate());
		row.setInternshipMemo(itInternView.getInternshipMemo());
		row.setUpdDate(itInternView.getUpdDate());
		row.setUpdUserKey(itInternView.getUpdUserKey());
		row.setInternshipGkFileNeedKbn(itInternView.getInternshipGkFileNeedKbn());
		row.setInternshipTitle(itInternView.getInternshipTitle());
		row.setPartyName(itInternView.getPartyName());
		row.setPublicFlag(itInternView.getPublicFlag());
		row.setInternshipSendDate(itInternView.getInternshipSendDate());
		row.setInternshipRcFileNeedKbn(itInternView.getInternshipRcFileNeedKbn());
		row.setPartyNameEn(itInternView.getPartyNameEn());
		row.setInternshipPartyName(itInternView.getInternshipPartyName());
		return row;
	}

	/**
	 * 一覧取得
	 *
	 * @param form
	 * @param locale
	 * @return
	 */
	public List<InternshipViewDto> findInternshipBatchTarget(InternshipForm form, Locale locale) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		// 取得条件：公開
		Specification<ItInternTbl> wherePublicFlag = StringUtil.isNull(form.getSearchPublicFlag()) ? null
				: new Specification<ItInternTbl>() {
					@Override
					public Predicate toPredicate(Root<ItInternTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("publicFlag"), form.getSearchPublicFlag());
					}
				};

		// 取得条件：日付
		Specification<ItInternTbl> whereEnDay = StringUtil.isNull(form.getSearchDateType()) ? null
				: new Specification<ItInternTbl>() {
					@Override
					public Predicate toPredicate(Root<ItInternTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						// batch処理
						if (SEARCH_DATE_TYPE_BATCH.equals(form.getSearchDateType().toLowerCase())) {
							Predicate predicate = cb.conjunction();
							Predicate predicate2 = cb.conjunction();
							predicate2 = cb.and(predicate2, cb.equal(root.get("updDate").as(Date.class), today));
							predicate2 = cb.and(predicate2,
									cb.lessThan(root.get("internshipStartDate").as(Date.class), today));

							predicate = cb.and(predicate,
									cb.equal(root.get("internshipStartDate").as(Date.class), today));
							predicate = cb.or(predicate2);
							return predicate;
						}
						return null;
					}
				};

		List<ItInternTbl> list = itInternTblRepository.findAll(Specifications.where(wherePublicFlag).and(whereEnDay));

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return getDtoList(list, locale);
	}

	public List<ItInternRecruitView> findAllItInternRecruitView(String internshipKey, String userKey) {

		// 取得条件：インターンシップキー
		Specification<ItInternRecruitView> whereIntershipKey = StringUtil.isNull(internshipKey) ? null
				: new Specification<ItInternRecruitView>() {
					@Override
					public Predicate toPredicate(Root<ItInternRecruitView> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						return cb.equal(root.get("internshipKey"), internshipKey);
					}
				};
		// 取得条件：ユーザキー
		Specification<ItInternRecruitView> whereUserKey = StringUtil.isNull(userKey) ? null
				: new Specification<ItInternRecruitView>() {
					@Override
					public Predicate toPredicate(Root<ItInternRecruitView> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						return cb.equal(root.get("userKey"), userKey);
					}
				};
		// Query
		List<ItInternRecruitView> resultList = itInternRecruitViewRepository
				.findAll(Specifications.where(whereIntershipKey).and(whereUserKey));

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}

		return resultList;

	}

	public InternshipViewDto findOneInternshipViewDto(String internshipKey) {
		return this.getViewDto(findOneItInternView(internshipKey));
	}

	public ItInternView findOneItInternView(String internshipKey) {

		// Query
		ItInternView itInternView = itInternViewRepository.findOne(internshipKey);

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}

		return itInternView;

	}

	/**
	 * orderBy
	 *
	 * @return
	 */
	private Sort orderBy() {
		// 公開日（降順）
		return new Sort(Sort.Direction.DESC, "internshipSendDate");
	}

	/**
	 * インターンシップ養成能力リレーション
	 *
	 * @return
	 */
	public List<ItInternRelSubjectTbl> findAllItInternRelSubject(String internshipKey) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 取得条件：支援制度種別コード
		Specification<ItInternRelSubjectTbl> whereInternshipKey = StringUtil.isNull(internshipKey) ? null
				: new Specification<ItInternRelSubjectTbl>() {
					@Override
					public Predicate toPredicate(Root<ItInternRelSubjectTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("id").get("internshipKey"), internshipKey);
					}
				};

		List<ItInternRelSubjectTbl> list = itInternRelSubjectTblRepository.findAll(Specifications.where(whereInternshipKey), orderByItInternRelSubject());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	/**
	 * orderByItInternRelSubject
	 *
	 * @return
	 */
	private Sort orderByItInternRelSubject() {
		// ルーブリックキー、養成能力コード
		return new Sort("id.rubricKey", "id.subjectCode");
	}

	public List<ItInternView> findAllInternshipView(String type, String searchPartyCode, String searchRole,
			String searchPublicFlag, String searchDateType) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		Specification<ItInternView> whereViewType = null;
		if (SEARCH_VIEW_TYPE_1.equals(type)) {
			// 取得条件：個人ユーザの権限（ROLE_USER1、ROLE_USER2、ROLE_USER3、ROLE_USER4）
			// WHERE
			// CURRENT_DATE >= A.INTERNSHIP_START_DATE
			// AND CURRENT_DATE <= A.INTERNSHIP_END_DATE
			// AND A.PUBLIC_FLAG = '1'
			// AND B.PARTY_CODE = :partyCode
			whereViewType = StringUtil.isNull(searchPartyCode) ? null : new Specification<ItInternView>() {
				@Override
				public Predicate toPredicate(Root<ItInternView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.like(root.get("searchPartyCode"), "%[" + searchPartyCode + "]%");
				}
			};
		} else if (SEARCH_VIEW_TYPE_2.equals(type)) {
			// 取得条件：HIRAKU 運営協議会事務局の権限（ROLE_MGMT1）
			// WHERE
			// 無し
			whereViewType = null;
		} else if (SEARCH_VIEW_TYPE_3.equals(type)) {
			// 取得条件：共同実施機関窓口（山大・徳大）、連携大学の権限（ROLE_MGMT2、ROLE_MGMT3）
			// WHERE
			// CURRENT_DATE >= A.INTERNSHIP_START_DATE
			// AND CURRENT_DATE <= A.INTERNSHIP_END_DATE
			// AND A.PUBLIC_FLAG = '1'
			// AND (A.PARTY_CODE = :partyCode OR B.PARTY_CODE = :partyCode)
			whereViewType = StringUtil.isNull(searchPartyCode) ? null : new Specification<ItInternView>() {

				@Override
				public Predicate toPredicate(Root<ItInternView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicate = cb.conjunction();
					predicate = cb.and(predicate, cb.equal(root.get("partyCode"), searchPartyCode));
					predicate = cb.or(predicate, cb.like(root.get("searchPartyCode"), "%[" + searchPartyCode + "]%"));
					return predicate;
				}
			};
		} else if (SEARCH_VIEW_TYPE_4.equals(type)) {
			// 取得条件：連携期間窓口（企業、研究所）の権限（ROLE_MGMT4）
			// WHERE
			// CURRENT_DATE >= A.INTERNSHIP_START_DATE
			// AND CURRENT_DATE <= A.INTERNSHIP_END_DATE
			// AND A.PUBLIC_FLAG = '1'
			// AND (A.PARTY_CODE = ? OR B.ROLE = ?)
			whereViewType = (StringUtil.isNull(searchPartyCode) || StringUtil.isNull(searchRole)) ? null
					: new Specification<ItInternView>() {
						@Override
						public Predicate toPredicate(Root<ItInternView> root, CriteriaQuery<?> query,
								CriteriaBuilder cb) {
							Predicate predicate = cb.conjunction();
							predicate = cb.and(predicate, cb.equal(root.get("partyCode"), searchPartyCode));
							predicate = cb.or(predicate, cb.like(root.get("searchRole"), "%[" + searchRole + "]%"));
							return predicate;
						}
					};
		}

		// 取得条件：公開
		Specification<ItInternView> wherePublicFlag = StringUtil.isNull(searchPublicFlag) ? null
				: new Specification<ItInternView>() {
					@Override
					public Predicate toPredicate(Root<ItInternView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("publicFlag"), searchPublicFlag);
					}
				};

		// 取得条件：日付(期間)
		Specification<ItInternView> whereDate = StringUtil.isNull(searchDateType) ? null
				: new Specification<ItInternView>() {
					@Override
					public Predicate toPredicate(Root<ItInternView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						// 期間中
						if (SEARCH_DATE_TYPE_CURRENT.equals(searchDateType)) {
							Predicate predicate = cb.conjunction();
							predicate = cb.and(predicate,
									cb.lessThanOrEqualTo(root.get("internshipStartDate").as(Date.class), today));
							predicate = cb.and(predicate,
									cb.greaterThanOrEqualTo(root.get("internshipEndDate").as(Date.class), today));
							return predicate;
						}
						// 過去情報
						if (SEARCH_DATE_TYPE_PAST.equals(searchDateType)) {
							return cb.lessThan(root.get("internshipEndDate").as(Date.class), today);
						}
						// 終了前
						if (SEARCH_DATE_TYPE_PRESENT.equals(searchDateType)) {
							return cb.greaterThanOrEqualTo(root.get("internshipEndDate").as(Date.class), today);
						}
						return null;
					}
				};

		// Query
		List<ItInternView> resultList = itInternViewRepository
				.findAll(Specifications.where(wherePublicFlag).and(whereDate).and(whereViewType), orderBy());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}

		return resultList;

	}

	/**
	 *
	 * @param internshipKey
	 * @return
	 */
	public InternshipViewDto findOneInternshipView(String internshipKey) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 取得条件：公開
		Specification<ItInternView> whereIntershipKey = StringUtil.isNull(internshipKey) ? null
				: new Specification<ItInternView>() {
					@Override
					public Predicate toPredicate(Root<ItInternView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("internshipKey"), internshipKey);
					}
				};

		// Query
		List<ItInternView> resultList = itInternViewRepository.findAll(Specifications.where(whereIntershipKey));
		InternshipViewDto dto = null;
		if (resultList.size() > 0) {
			dto = getViewDto(resultList.get(0));
		}

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}

		return dto;

	}

	/**
	 *
	 * @param internshipKey
	 * @return
	 */
	public List<ItInternPublicTbl> findAllInternPublic(String internshipKey) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 取得条件：公開
		Specification<ItInternPublicTbl> whereIntershipKey = StringUtil.isNull(internshipKey) ? null
				: new Specification<ItInternPublicTbl>() {
					@Override
					public Predicate toPredicate(Root<ItInternPublicTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("id").get("internshipKey"), internshipKey);
					}
				};

		// Query
		List<ItInternPublicTbl> resultList = itInternPublicTblRepository.findAll(Specifications.where(whereIntershipKey));

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}

		return resultList;

	}

	/**
	 * ３．８．合否結果閲覧（一覧）
	 *
	 * @param targetUserKey
	 * @param locale
	 * @return
	 */
	public List<InternshipApplicantDto> findAllInternshipForApplication(String targetUserKey, Locale locale) {

		// 合否判定
		Map<String, MsCodeDto> decisionKbnMap = DbUtil.getJosuMap(DECISION_KBN, locale);

		List<InternshipApplicantDto> resultList = itInternViewRepository.findAllInternshipForApplication(targetUserKey);

		// 合否判定名称設定
		for (InternshipApplicantDto data : resultList) {
			if(decisionKbnMap.get(data.getDecisionKbn()) != null) {
				data.setDecisionKbnName(decisionKbnMap.get(data.getDecisionKbn()).getValue());
			}
		}

		return resultList;

	}

	public boolean download(UserInfo userInfo, InternshipForm form, String uploadKbn) {

		// 取得条件
		Specification<ItInternUploadTbl> whereInternshipKey = StringUtil.isNull(form.getInternshipKey()) ? null
				: new Specification<ItInternUploadTbl>() {
					@Override
					public Predicate toPredicate(Root<ItInternUploadTbl> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						return cb.equal(root.get("id").get("internshipKey"), form.getInternshipKey());
					}
				};
		Specification<ItInternUploadTbl> whereUploadKbn = new Specification<ItInternUploadTbl>() {
			@Override
			public Predicate toPredicate(Root<ItInternUploadTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("uploadKbn"), uploadKbn);
			}
		};

		List<ItInternUploadTbl> list = new ArrayList<ItInternUploadTbl>();
		list = itInternUploadTblRepository.findAll(Specifications.where(whereInternshipKey).and(whereUploadKbn));

		return true;
	}

	public ItInternRecruitTbl getOneInternRecruit(ItInternRecruitTblPK id) {
		return itInternRecruitTblRepository.findOne(id);
	}

	public ItInternRecruitTbl findOneInternRecruit(ItInternRecruitForm form) {
		boolean result = false;

		ItInternRecruitTblPK itInternRecruitTblPK = form.getId();
		// 取得条件
		Specification<ItInternRecruitTbl> whereInternshipKey = StringUtil
				.isNull(itInternRecruitTblPK.getInternshipKey()) ? null : new Specification<ItInternRecruitTbl>() {
					@Override
					public Predicate toPredicate(Root<ItInternRecruitTbl> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						return cb.equal(root.get("id").get("internshipKey"), itInternRecruitTblPK.getInternshipKey());
					}
				};
		Specification<ItInternRecruitTbl> whereUserKey = new Specification<ItInternRecruitTbl>() {
			@Override
			public Predicate toPredicate(Root<ItInternRecruitTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("id").get("userKey"), itInternRecruitTblPK.getUserKey());
			}
		};
		Specification<ItInternRecruitTbl> whereUpdDate = new Specification<ItInternRecruitTbl>() {
			@Override
			public Predicate toPredicate(Root<ItInternRecruitTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("updDate"), form.getUpdDate());
			}
		};

		ItInternRecruitTbl itInternRecruitTbl = itInternRecruitTblRepository
				.findOne(Specifications.where(whereInternshipKey).and(whereUserKey).and(whereUpdDate));

		return itInternRecruitTbl;
	}

	public ItInternTbl findOneIntern(InternshipForm form) {
		// 取得条件
		Specification<ItInternTbl> whereInternshipKey = StringUtil.isNull(form.getInternshipKey()) ? null
				: new Specification<ItInternTbl>() {
					@Override
					public Predicate toPredicate(Root<ItInternTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("internshipKey"), form.getInternshipKey());
					}
				};
		Specification<ItInternTbl> whereUpdDate = new Specification<ItInternTbl>() {
			@Override
			public Predicate toPredicate(Root<ItInternTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("updDate"), form.getUpdDate());
			}
		};

		ItInternTbl itInternTbl = itInternTblRepository
				.findOne(Specifications.where(whereInternshipKey).and(whereUpdDate));

		return itInternTbl;
	}

	public boolean insertInternRecruit(UserInfo userInfo, InternshipForm form) {
		boolean result = false;

		try {
			ItInternRecruitTbl entity = new ItInternRecruitTbl();
			ItInternRecruitTblPK id = new ItInternRecruitTblPK();
			id.setInternshipKey(form.getInternshipKey());
			id.setUserKey(userInfo.getTargetUserKey());
			entity.setId(id);
			entity.setPartyCode(userInfo.getTargetPartyCode());
			entity.setRecruitDate(DateUtil.getNowTimestamp());
			entity.setSelectionKbn("0");
			entity.setDecisionKbn("0");
			entity.setRcFileInsKbn(form.getInternshipRcFileNeedKbn());
			entity.setGkFileInsKbn("0");
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity.setUpdUserKey(userInfo.getLoginUserKey());

			itInternRecruitTblRepository.saveAndFlush(entity);
			result = true;

		} catch (Exception e) {
			logger.errorCode("E1008", e); // E1008=更新に失敗しました。{0}
		}

		return result;
	}

	/**
	 * 新規追加、更新
	 *
	 * @param form
	 * @param userInfo
	 * @return
	 */
	@Transactional
	public boolean update(InternshipForm form, UserInfo userInfo) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}
		try {
			ItInternTbl entity = this.updateIntern(form, userInfo);
			// 添付ファイルある場合登録
			if (StringUtil.isNotNull(form.getInternshipRcFile()) || StringUtil.isNotNull(form.getInternshipGkFile())) {
				//削除
				itInternUploadTblRepository.delete(form.getInternshipKey());

				//************************************
				//登録
				//ファイル数分で登録するため、未解決
				//************************************
			}

			//公開範囲
			if (form.getPageMode().equals(CommonConst.PAGE_MODE_EDIT)) {
				//削除
				itInternPublicTblRepository.delete(form.getInternshipKey());
			}
			List<ItInternPublicTbl> publicList = new ArrayList<>();
			int count = 1;

			// 企業・研究所などがチェックＯＮの場合、１件出力する。
			if (form.checkHasPublicItem("1")) {
				ItInternPublicTbl tbl = new ItInternPublicTbl();
				ItInternPublicTblPK pk = new ItInternPublicTblPK();
				pk.setInternshipKey(entity.getInternshipKey());
				pk.setSeqNo(count);
				tbl.setId(pk);

				// 企業・研究所等が、設定されている分の出力を行う場合、１：ROLE。
				tbl.setPublicKbn("1");
				// 企業・研究所等が、設定されている分の出力を行う場合、ROLE_MGMT1。
				tbl.setRole(userInfo.getTargetRole().getAuthority());
				// 企業・研究所等が、設定されている分の出力を行う場合、NULL。
				tbl.setPartyCode(null);
				// 現在時刻を設定する。
				tbl.setUpdDate(DateUtil.getNowTimestamp());
				// ログイン者のユーザキーの値を設定する。
				tbl.setUpdUserKey(userInfo.getLoginUserKey());
				publicList.add(tbl);
				count = count + 1;
			}
			if (form.checkHasPublicItem("2")) {
				// 大學がチェックＯＮの場合、公開範囲（公開）で選択されている件数出力する。
				for (int i = 0; i < form.getPublicPartyList().length; i++) {
					ItInternPublicTbl tbl = new ItInternPublicTbl();
					ItInternPublicTblPK pk = new ItInternPublicTblPK();
					pk.setInternshipKey(entity.getInternshipKey());
					pk.setSeqNo(count);
					tbl.setId(pk);

					// 大學が、設定されている分の出力を行う場合、２：組織。
					tbl.setPublicKbn("2");
					// 大學が、設定されている分の出力を行う場合、NULL。
					tbl.setRole(null);
					// 大學が、設定されている分の出力を行う場合、対象大学の組織コード。
					tbl.setPartyCode(form.getPublicPartyList()[i]);
					// 現在時刻を設定する。
					tbl.setUpdDate(DateUtil.getNowTimestamp());
					// ログイン者のユーザキーの値を設定する。
					tbl.setUpdUserKey(userInfo.getLoginUserKey());
					publicList.add(tbl);
					count = count + 1;
				}
			}
			itInternPublicTblRepository.save(publicList);

			//************************************
			//能力養成未作成
			//************************************

			//お知らせ情報
			//新規、コピー
			boolean result = false;
			if (!CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
				// お知らせ情報、お知らせ情報公開範囲登録
				result = this.insertCmInfo(userInfo, form, "201", userInfo.getTargetRole().getAuthority(), null);
			}

			if (result) {
				itInternTblRepository.flush();
				itInternUploadTblRepository.flush();
				itInternPublicTblRepository.flush();
			}

			logger.infoCode("I0002"); // I0002=メソッド終了:{0}
			return true;

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

	public ItInternTbl updateIntern(InternshipForm form, UserInfo userInfo) throws Exception {

		try {
			ItInternTbl entity = new ItInternTbl();

			// 更新の場合
			if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
				entity = this.findOneIntern(form);
				if (entity == null) {
					throw new Exception();
				}
			}
			entity.setInternshipKbn(form.getInternshipKbn());
			entity.setInternshipStartDate(form.getInternshipStartDate());
			entity.setInternshipEndDate(form.getInternshipEndDate());
			entity.setInternshipTitle(form.getInternshipTitle());
			entity.setInternshipSendDate(form.getInternshipSendDate());
			entity.setInternshipRange(form.getInternshipRange());
			entity.setInternshipPartyName(form.getInternshipPartyName());
			entity.setInternshipTelno(form.getInternshipTelno());
			if (StringUtil.isNotNull(form.getInternshipRcFile())) {
				entity.setInternshipRcFileNeedKbn("1");
			} else {
				entity.setInternshipRcFileNeedKbn("0");
			}
			if (StringUtil.isNotNull(form.getInternshipGkFile())) {
				entity.setInternshipGkFileNeedKbn("1");
			} else {
				entity.setInternshipGkFileNeedKbn("0");
			}
			entity.setPartyCode(userInfo.getTargetPartyCode());
			entity.setEventUnit(form.getEventUnit());

			//*****************************************************
			// 養成能力未完成？？？？
			entity.setSubjectInsKbn("0");
			//*****************************************************

			entity.setInternshipMemo(form.getInternshipMemo());
			if (userInfo.isMgmt1()) {
				entity.setPublicFlag(form.getPublicFlag());
			} else {
				entity.setPublicFlag("0");
			}
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity.setUpdUserKey(userInfo.getLoginUserKey());

			entity = itInternTblRepository.save(entity);

			return entity;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * インターンシップ応募者テーブル
	 *
	 * @param userInfo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean updateInternRecruit(UserInfo userInfo, ItInternRecruitForm form) {
		boolean result = false;

		try {
			ItInternRecruitTbl entity = new ItInternRecruitTbl();
			entity = this.findOneInternRecruit(form);

			if (entity != null) {
				entity.setId(form.getId());
				entity.setDecisionKbn("3");
				entity.setDecisionDate(DateUtil.getNowTimestamp());
				entity.setDecisionUserKey(userInfo.getLoginUserKey());
				entity.setUpdDate(DateUtil.getNowTimestamp());
				entity.setUpdUserKey(userInfo.getLoginUserKey());
				itInternRecruitTblRepository.saveAndFlush(entity);
			}
			result = true;

		} catch (Exception e) {
			logger.errorCode("E1008", e); // E1008=更新に失敗しました。{0}
		}

		return result;
	}

	/**
	 * インターンシップ応募者テーブル：辞退
	 *
	 * @param userInfo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean refuseInternRecruit(UserInfo userInfo, ItInternRecruitForm form) throws Exception {
		boolean result = false;

		try {
			ItInternRecruitTbl entity = new ItInternRecruitTbl();
			entity = this.findOneInternRecruit(form);

			if (entity != null) {
				// インターンシップ応募者テーブル：辞退
				entity.setId(form.getId());
				entity.setDecisionKbn("3");
				entity.setDecisionDate(DateUtil.getNowTimestamp());
				entity.setDecisionUserKey(userInfo.getLoginUserKey());
				entity.setUpdDate(DateUtil.getNowTimestamp());
				entity.setUpdUserKey(userInfo.getLoginUserKey());
				itInternRecruitTblRepository.save(entity);


				// インターンシップ応募者添付ファイル：取得
				// 取得条件
				Specification<ItInternRecruitUploadTbl> whereInternship = StringUtil
						.isNull(form.getInternshipKey()) ? null : new Specification<ItInternRecruitUploadTbl>() {
							@Override
							public Predicate toPredicate(Root<ItInternRecruitUploadTbl> root, CriteriaQuery<?> query,
									CriteriaBuilder cb) {
								return cb.and(cb.equal(root.get("id").get("internshipKey"), form.getInternshipKey()),
										cb.equal(root.get("id").get("userKey"), userInfo.getTargetUserKey()));
							}
						};
				// アップロードファイル一覧取得
				List<ItInternRecruitUploadTbl> itInternRecruitUploadList = itInternRecruitUploadTblRepository.findAll(Specifications.where(whereInternship));
				List<String> uploadKeyList = new ArrayList<String>();
				for (ItInternRecruitUploadTbl u : itInternRecruitUploadList) {
					uploadKeyList.add(u.getUpdUserKey());
				}
				// TODO: ファイル削除処理完成まで保留
				// 物理ファイル削除
				// DBファイル削除
				// uploadKeyListを渡して一括削除：成功？

				// インターンシップ応募者添付ファイル：削除
				itInternRecruitUploadTblRepository.delete(itInternRecruitUploadList);

				// コミット
				itInternRecruitTblRepository.flush();
				itInternRecruitUploadTblRepository.flush();

				result = true;
			}

		} catch (Exception e) {
			logger.errorCode("E1008", e); // E1008=更新に失敗しました。{0}
			// rollback
			throw e;
		}

		return result;
	}

	public boolean updateInternRecruitForGohiKeka(UserInfo userInfo, InternshipForm form) {
		boolean result = false;

		try {
			ItInternRecruitTbl entity = new ItInternRecruitTbl();
			ItInternRecruitTblPK id = new ItInternRecruitTblPK();
			id.setInternshipKey(form.getInternshipKey());
			id.setUserKey(userInfo.getTargetUserKey());
			entity = itInternRecruitTblRepository.findOne(id);

			if (entity != null) {
				entity.setId(id);
				entity.setGkFileInsKbn("1");
				entity.setUpdDate(DateUtil.getNowTimestamp());
				entity.setUpdUserKey(userInfo.getLoginUserKey());

				itInternRecruitTblRepository.saveAndFlush(entity);
			}
			result = true;

		} catch (Exception e) {
			logger.errorCode("E1008", e); // E1008=更新に失敗しました。{0}
		}

		return result;
	}

	public boolean updateInternRecruitUpload(UserInfo userInfo, InternshipForm form) {
		boolean result = false;

		try {
			ItInternRecruitUploadTbl entity = new ItInternRecruitUploadTbl();
			ItInternRecruitUploadTblPK id = new ItInternRecruitUploadTblPK();
			id.setInternshipKey(form.getInternshipKey());
			id.setUserKey(userInfo.getTargetUserKey());
			// ファイル個数分？？？

			itInternRecruitUploadTblRepository.saveAndFlush(entity);
			result = true;

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}

		return result;
	}

	public boolean updateInternRecruitUploadForGohiKeka(UserInfo userInfo, InternshipForm form) {
		boolean result = false;

		try {
			ItInternRecruitUploadTbl entity = new ItInternRecruitUploadTbl();
			ItInternRecruitUploadTblPK id = new ItInternRecruitUploadTblPK();
			id.setInternshipKey(form.getInternshipKey());
			id.setUserKey(userInfo.getTargetUserKey());
			entity.setId(id);

			// ファイル個数分？？？

			entity.setUploadKbn("2");

			itInternRecruitUploadTblRepository.saveAndFlush(entity);
			result = true;

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}

		return result;
	}

	public boolean insertCmInfo(UserInfo userInfo, InternshipForm form, String opeKbn, String roleCode,
			String partyCode) {
		boolean result = false;

		try {
			CmInfoTbl entity = new CmInfoTbl();
			entity.setSendDate(DateUtil.getNowTimestamp());
			entity.setTitle(form.getInternshipTitle());
			entity.setDataKbn("2");
			entity.setOpeKbn(opeKbn);
			entity.setInfoRefKey(form.getInternshipKey());
			entity.setMakeUserKey(userInfo.getLoginUserKey());
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity.setUpdUserKey(userInfo.getLoginUserKey());

			entity = cmInfoTblRepository.save(entity);
			String infoKey = entity.getInfoKey();

			if (infoKey != null && StringUtil.isNotNull(infoKey)) {
				CmInfoPublicTbl entitySub = new CmInfoPublicTbl();
				CmInfoPublicTblPK id = new CmInfoPublicTblPK();
				id.setInfoKey(infoKey);
				id.setSeqNo(1);
				entitySub.setId(id);
				entitySub.setPublicKbn("1");
				entitySub.setRole(roleCode);
				entitySub.setPartyCode(partyCode);
				entitySub.setUpdDate(DateUtil.getNowTimestamp());
				entitySub.setUpdUserKey(userInfo.getLoginUserKey());

				cmInfoPublicTblRepository.save(entitySub);

				cmInfoTblRepository.flush();
				cmInfoPublicTblRepository.flush();

				result = true;
			}

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}

		return result;
	}

	/**
	 *
	 *
	 *
	 * @param userInfo
	 * @param form
	 * @return
	 */
	public boolean deleteOneInternRecruitUpload(UserInfo userInfo, InternshipForm form) {
		boolean result = false;

		try {
			ItInternRecruitUploadTblPK id = new ItInternRecruitUploadTblPK();
			id.setInternshipKey(form.getInternshipKey());
			id.setUserKey(userInfo.getTargetUserKey());
			itInternRecruitUploadTblRepository.delete(id);
			itInternRecruitUploadTblRepository.flush();

			result = true;

		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=削除に失敗しました。{0}
		}

		return result;
	}

	public boolean deleteIntern(UserInfo userInfo, InternshipForm form) {
		boolean result = false;

		try {
			int a = deleteInternByInternKey(userInfo, form);

			if (a > 0) {
				int b = deleteInternUploadByInternKey(userInfo, form);
				int c = deleteInternPublicByInternKey(userInfo, form);

				itInternTblRepository.flush();
				itInternUploadTblRepository.flush();
				itInternPublicTblRepository.flush();

				result = true;
			}

		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=削除に失敗しました。{0}
		}

		return result;
	}

	public int deleteInternByInternKey(UserInfo userInfo, InternshipForm form) throws Exception {
		try {
			ItInternTbl itInternTbl = findOneIntern(form);
			if (itInternTbl == null) {
				return 0;
			} else {
				return itInternTblRepository.delete(form.getInternshipKey(), form.getUpdDate());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteInternUploadByInternKey(UserInfo userInfo, InternshipForm form) throws Exception {
		try {
			return itInternUploadTblRepository.delete(form.getInternshipKey());
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteInternPublicByInternKey(UserInfo userInfo, InternshipForm form) throws Exception {
		try {
			return itInternPublicTblRepository.delete(form.getInternshipKey());
		} catch (Exception e) {
			throw e;
		}
	}

	public List<ItInternTbl> findAll() {
		return itInternTblRepository.findAll();
	}

	public String setValue(Object obj) {
		if (obj == null) {
			return "";
		}

		return String.valueOf(obj);
	}

}
