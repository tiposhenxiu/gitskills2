/*
* ファイル名：ViewServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/12   toishigawa  新規作成
*/
package jp.co.sraw.service;

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

import jp.co.sraw.common.CommonService;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.dto.ViewDto;
import jp.co.sraw.entity.ViewTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.ViewRepository;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>ViewServiceImplクラス</B>
 * <P>
 * Viewサービスのメソッドを提供する
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(readOnly = true)
public class ViewServiceImpl<T extends ViewTbl, D extends ViewDto, R extends ViewRepository> extends CommonService {

	@Autowired
	private R repository;

	public static final String SEARCH_DATE_TYPE_BATCH = "batch"; // batch処理
	public static final String SEARCH_DATE_TYPE_CURRENT = "current"; // 公開期間中
	public static final String SEARCH_DATE_TYPE_PAST = "past"; // 過去情報
	public static final String SEARCH_DATE_TYPE_PRESENT = "present"; // 終了前

	public static final String SEARCH_VIEW_TYPE_1 = "1"; // 個人ユーザの権限
	public static final String SEARCH_VIEW_TYPE_2 = "2"; // HIRAKU 運営協議会事務局の権限
	public static final String SEARCH_VIEW_TYPE_3 = "3"; // 共同実施機関窓口（山大・徳大）、連携大学の権限
	public static final String SEARCH_VIEW_TYPE_4 = "4"; // 連携期間窓口（企業、研究所）の権限

	private static final String PUBLIC_FLAG = "1"; // 固定値: 公開

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(ViewServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	private String code = "default";

	public void setCode(String code) {
		this.code = code;
	}

	private String order;

	public void setOrder(String order) {
		this.order = order;
	}

	private String startField;
	private String endField;

	public void setStartField(String startField) {
		this.startField = startField;
	}

	public void setEndField(String endField) {
		this.endField = endField;
	}

	private D viewDto;

	public void setDto(D viewDto) {
		this.viewDto = viewDto;
	}

	@SuppressWarnings("unchecked")
	public D getViewDto(T tbl) {
		if (tbl == null || viewDto == null)
			return null;
		D newDto = (D) viewDto.getViewDto();
		return (D) objectUtil.getObjectCopyValue(newDto, tbl);
	}

	public void initService(D viewDto, String startField, String endField, String order) {
		this.viewDto = viewDto;
		this.startField = startField;
		this.endField = endField;
		this.order = order;
	}

	/**
	 *
	 *
	 * @param userInfo
	 * @param locale
	 * @return
	 */
	public Map<String, List<D>> getDtoList(UserInfo userInfo, Locale locale) {

		Map<String, List<D>> mapList = new HashMap<String, List<D>>();

		List<MsCodeDto> kbnList = DbUtil.getJosuList(code, locale);

		List<T> resultList = new ArrayList<>();

		resultList = getViewList(userInfo, locale);

		if (kbnList.size() == 0) {
			List<D> inteList = new ArrayList<D>();
			for (int j = 0; j < resultList.size(); j++) {
				T T = resultList.get(j);
				D row = getViewDto(T);
				inteList.add(row);
			}
			mapList.put(code, inteList);
		}

		// 区分から一覧を生成
		for (int i = 0; i < kbnList.size(); i++) {
			MsCodeDto dto = kbnList.get(i);
			//
			List<D> inteList = new ArrayList<D>();
			for (int j = 0; j < resultList.size(); j++) {
				T t = resultList.get(j);
				// 区分毎に振り分け
				String kbn = viewDto.getKbn(t);
				if (kbn != null && kbn.contains(dto.getCode())) {
					D row = getViewDto(t);
					inteList.add(row);
				}
			}
			mapList.put(dto.getCode(), inteList);
		}
		return mapList;
	}

	private String searchDateType;

	public void setSearchDateType(String searchDateType){
		this.searchDateType=searchDateType;
	}


	/**
	 *
	 * /**
	 *
	 * @param userInfo
	 * @param locale
	 * @return
	 */
	public List<T> getViewList(UserInfo userInfo, Locale locale) {

		List<T> resultList = new ArrayList<>();

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

		if(StringUtil.isNotNull(searchDateType)){
			searchDateType=this.searchDateType;
		}

		resultList = this.findAllView(type, searchPartyCode, searchRole, searchPublicFlag, searchDateType);

		return resultList;
	}

	public List<T> findAllView(String type, String searchPartyCode, String searchRole, String searchPublicFlag,
			String searchDateType) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		Specification<T> whereViewType = null;
		if (SEARCH_VIEW_TYPE_1.equals(type)) {
			// 取得条件：個人ユーザの権限（ROLE_USER1、ROLE_USER2、ROLE_USER3、ROLE_USER4）
			// WHERE
			// CURRENT_DATE >= A.INTERNSHIP_START_DATE
			// AND CURRENT_DATE <= A.INTERNSHIP_END_DATE
			// AND A.PUBLIC_FLAG = '1'
			// AND B.PARTY_CODE = :partyCode
			whereViewType = StringUtil.isNull(searchPartyCode) ? null : new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
			whereViewType = StringUtil.isNull(searchPartyCode) ? null : new Specification<T>() {

				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
					: new Specification<T>() {
						@Override
						public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
							Predicate predicate = cb.conjunction();
							predicate = cb.and(predicate, cb.equal(root.get("partyCode"), searchPartyCode));
							predicate = cb.or(predicate, cb.like(root.get("searchRole"), "%[" + searchRole + "]%"));
							return predicate;
						}
					};
		}

		// 取得条件：公開
		Specification<T> wherePublicFlag = StringUtil.isNull(searchPublicFlag) ? null : new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("publicFlag"), searchPublicFlag);
			}
		};

		// 取得条件：日付(期間)
		Specification<T> whereDate = StringUtil.isNull(searchDateType) || StringUtil.isNull(startField)
				|| StringUtil.isNull(endField) ? null : new Specification<T>() {
					@Override
					public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						// 期間中
						if (SEARCH_DATE_TYPE_CURRENT.equals(searchDateType)) {
							Predicate predicate = cb.conjunction();
							predicate = cb.and(predicate,
									cb.lessThanOrEqualTo(root.get(startField).as(Date.class), today));
							predicate = cb.and(predicate,
									cb.greaterThanOrEqualTo(root.get(endField).as(Date.class), today));
							return predicate;
						}
						// 過去情報
						if (SEARCH_DATE_TYPE_PAST.equals(searchDateType)) {
							return cb.lessThan(root.get(endField).as(Date.class), today);
						}
						// 終了前
						if (SEARCH_DATE_TYPE_PRESENT.equals(searchDateType)) {
							return cb.greaterThanOrEqualTo(root.get(endField).as(Date.class), today);
						}
						return null;
					}
				};

		// Query
		List<T> resultList = repository.findAll(Specifications.where(wherePublicFlag).and(whereDate).and(whereViewType),
				orderBy());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}

		return resultList;

	}

	/**
	 * orderBy
	 *
	 * @return
	 */
	protected Sort orderBy() {
		// 公開日（降順）
		return new Sort(Sort.Direction.DESC, order);
	}

}
