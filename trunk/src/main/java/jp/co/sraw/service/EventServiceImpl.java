/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import jp.co.sraw.controller.event.EventForm;
import jp.co.sraw.dto.EvEventViewDto;
import jp.co.sraw.dto.EventDto;
import jp.co.sraw.entity.CmInfoPublicTbl;
import jp.co.sraw.entity.CmInfoPublicTblPK;
import jp.co.sraw.entity.CmInfoTbl;
import jp.co.sraw.entity.EvEventPublicTbl;
import jp.co.sraw.entity.EvEventPublicTblPK;
import jp.co.sraw.entity.EvEventTbl;
import jp.co.sraw.entity.EvEventView;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.CmInfoPublicTblRepository;
import jp.co.sraw.repository.CmInfoTblRepository;
import jp.co.sraw.repository.EvEventPublicTblRepository;
import jp.co.sraw.repository.EvEventTblRepository;
import jp.co.sraw.repository.EvEventViewRepository;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>UserServiceクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class EventServiceImpl extends CommonService {

	public static final String SEARCH_DATE_TYPE_BATCH = "batch"; // batch処理

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private EvEventTblRepository evEventTblRepository;

	@Autowired
	private EvEventPublicTblRepository evEventPublicTblRepository;

	@Autowired
	private CmInfoTblRepository cmInfoTblRepository;

	@Autowired
	private CmInfoPublicTblRepository cmInfoPublicTblRepository;

	@Autowired
	private ViewServiceImpl<EvEventView, EvEventViewDto, EvEventViewRepository> viewServiceImpl;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(EventServiceImpl.class);

	private static final String CODE_SYBCODE = "0021";

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<EventDto> getDtoList(List<EvEventTbl> list, Locale locale) {
		List<EventDto> dtoList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			EventDto dto = new EventDto();
			dto = (EventDto) objectUtil.getObjectCopyValue(dto, list.get(i));
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<EvEventViewDto> findAllEventViewDto(UserInfo userInfo, String searchDateType, Locale locale) {
		viewServiceImpl.initService(new EvEventViewDto(), "eventStartDate", "eventSendDate", "eventSendDate");
		viewServiceImpl.setSearchDateType(searchDateType);
		Map<String, List<EvEventViewDto>> map = viewServiceImpl.getDtoList(userInfo, locale);
		return map.get("default");
	}

	public List<EvEventViewDto> findAllEventViewDto(UserInfo userInfo, Locale locale) {
		viewServiceImpl.initService(new EvEventViewDto(), "eventStartDate", "eventSendDate", "eventSendDate");
		Map<String, List<EvEventViewDto>> map = viewServiceImpl.getDtoList(userInfo, locale);
		return map.get("default");
	}

	/**
	 * 一覧取得
	 *
	 * @param form
	 * @param locale
	 * @return
	 */
	public List<EventDto> findEventBatchTarget(EventForm form, Locale locale) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		// 取得条件：公開
		Specification<EvEventTbl> wherePublicFlag = StringUtil.isNull(form.getSearchPublicFlag()) ? null
				: new Specification<EvEventTbl>() {
					@Override
					public Predicate toPredicate(Root<EvEventTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("publicFlag"), form.getSearchPublicFlag());
					}
				};

		// 取得条件：日付
		Specification<EvEventTbl> whereEnDay = StringUtil.isNull(form.getSearchDateType()) ? null
				: new Specification<EvEventTbl>() {
					@Override
					public Predicate toPredicate(Root<EvEventTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						// batch処理
						if (SEARCH_DATE_TYPE_BATCH.equals(form.getSearchDateType().toLowerCase())) {
							Predicate predicate = cb.conjunction();
							Predicate predicate2 = cb.conjunction();
							predicate2 = cb.and(predicate2, cb.equal(root.get("updDate").as(Date.class), today));
							predicate2 = cb.and(predicate2,
									cb.lessThan(root.get("eventSendDate").as(Date.class), today));

							predicate = cb.and(predicate, cb.equal(root.get("eventSendDate").as(Date.class), today));
							predicate = cb.or(predicate2);
							return predicate;
						}
						return null;
					}
				};

		List<EvEventTbl> list = evEventTblRepository.findAll(Specifications.where(wherePublicFlag).and(whereEnDay));

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return getDtoList(list, locale);
	}

	@SuppressWarnings("unchecked")
	public List<EventDto> findAllEvent(UserInfo userInfo, Boolean publicFlag, Boolean partyCodeFlag, Boolean roleFlag,
			boolean past) {
		logger.infoCode("I0001");
		String partyCode = null;
		String roleCode = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append("    A.EVENT_KEY");
		sb.append("  , A.EVENT_TITLE");
		sb.append("  , A.EVENT_SEND_DATE");
		sb.append("  , A.EVENT_RECRUIT");
		sb.append("  , A.EVENT_TELNO");
		sb.append("  , A.PARTY_CODE");
		sb.append("  , A.PARTY_NAME");
		sb.append("  , A.EVENT_START_DATE");
		sb.append("  , A.EVENT_PLACE");
		sb.append("  , A.EVENT_MEMO");
		sb.append("  , A.EVENT_UNIT");
		sb.append("  , A.SUBJECT_INS_KBN");
		sb.append("  , A.PUBLIC_FLAG");
		sb.append("  , A.UPD_DATE");
		sb.append("  , A.UPD_USER_KEY");
		sb.append("  , ARRAY_TO_STRING(ARRAY_AGG(C.PARTY_NAME), ',') AS PARTY_NAME");
		sb.append("  , ARRAY_TO_STRING(ARRAY_AGG(C.PARTY_NAME_EN), ',') AS PARTY_NAME_EN");
		sb.append(" FROM");
		sb.append("  EV_EVENT_TBL A ");
		sb.append("  INNER JOIN EV_EVENT_PUBLIC_TBL B ");
		sb.append("    ON A.EVENT_KEY = B.EVENT_KEY ");

		sb.append("  INNER JOIN MS_PARTY_TBL C ");
		sb.append("    ON B.PARTY_CODE = C.PARTY_CODE ");
		sb.append(" WHERE");

		if (past) {
			sb.append("   CURRENT_DATE < A.EVENT_START_DATE ");
		} else {
			sb.append("  CURRENT_DATE >= A.EVENT_START_DATE ");
			sb.append("  AND CURRENT_DATE <= A.EVENT_SEND_DATE ");
		}

		if (publicFlag) {
			sb.append("  AND A.PUBLIC_FLAG = '1' ");
		}

		if (partyCodeFlag) {
			sb.append("  AND  (B.PARTY_CODE = :partyCode ");
			sb.append("    OR");
			sb.append("    A.PARTY_CODE = :partyCode )");
		}

		if (roleFlag) {
			sb.append("  AND  (B.ROLE = :role ");
			sb.append("    OR");
			sb.append("    A.PARTY_CODE = :partyCode )");
		}

		sb.append(" GROUP BY");
		sb.append("    A.EVENT_KEY");
		sb.append("  , A.EVENT_TITLE");
		sb.append("  , A.EVENT_SEND_DATE");
		sb.append("  , A.EVENT_RECRUIT");
		sb.append("  , A.EVENT_TELNO");
		sb.append("  , A.PARTY_CODE");
		sb.append("  , A.PARTY_NAME");
		sb.append("  , A.EVENT_START_DATE");
		sb.append("  , A.EVENT_PLACE");
		sb.append("  , A.EVENT_MEMO");
		sb.append("  , A.EVENT_UNIT");
		sb.append("  , A.SUBJECT_INS_KBN");
		sb.append("  , A.PUBLIC_FLAG");
		sb.append("  , A.UPD_DATE");
		sb.append("  , A.UPD_USER_KEY");
		sb.append(" ORDER BY");
		sb.append("  A.EVENT_SEND_DATE DESC");
		String sql = sb.toString();

		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (partyCodeFlag) {
			query.setParameter("partyCode", userInfo.getTargetPartyCode());
		}
		if (roleFlag) {
			query.setParameter("partyCode", userInfo.getTargetPartyCode());
			query.setParameter("role", userInfo.getTargetRole());
		}

		List<EventDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			EventDto dto = new EventDto();
			dto = (EventDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	/**
	 * eventKey指定取得
	 *
	 * @param eventKey
	 * @return
	 */
	public EvEventTbl getOne(final String eventKey) {
		return evEventTblRepository.getOne(eventKey);
	}

	public EvEventTbl findOne(UserInfo userInfo, EventForm form) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 定数区分
		Specification<EvEventTbl> whereEventKey = StringUtil.isNull(form.getEventKey()) ? null
				: new Specification<EvEventTbl>() {
					@Override
					public Predicate toPredicate(Root<EvEventTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("eventKey"), form.getEventKey());
					}
				};
		Specification<EvEventTbl> whereUpdDate = DateUtil.isNull(form.getUpdDate()) ? null
				: new Specification<EvEventTbl>() {
					@Override
					public Predicate toPredicate(Root<EvEventTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("updDate"), form.getUpdDate());
					}
				};

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return evEventTblRepository.findOne(Specifications.where(whereEventKey).and(whereUpdDate));
	}

	@Transactional
	public boolean delete(UserInfo userInfo, EventForm form) {
		logger.infoCode("I0001");
		try {

			if (userInfo.isMgmt2() || userInfo.isMgmt3() || userInfo.isMgmt4()) {
				logger.infoCode("I0002");
				return false;
			}

			EvEventTbl entity = findOne(userInfo, form);

			evEventPublicTblRepository.delete(entity.getEvEventPublicTbls());

			int c1 = evEventTblRepository.delete(form.getEventKey(), form.getUpdDate());

			if (c1 > 0) {
				evEventPublicTblRepository.flush();
				evEventTblRepository.flush();
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=削除に失敗しました。{0}
		}
		return false;
	}

	@Transactional
	public boolean update(EventForm form, UserInfo userInfo) {
		logger.infoCode("I0001");
		try {
			EvEventTbl entity = new EvEventTbl();

			if (form.getPageMode().equals(CommonConst.PAGE_MODE_EDIT)) {
				entity = findOne(userInfo, form);
				if (entity == null) {
					throw new Exception();
				}
			}

			entity.setUpdUserKey(userInfo.getLoginUserKey());
			entity.setPartyCode(userInfo.getLoginPartyCode());
			entity.setPartyName(userInfo.getLoginPartyName());
			//
			entity.setEventKey(form.getEventKey());
			entity.setEventMemo(form.getEventMemo());
			entity.setEventPlace(form.getEventPlace());
			entity.setEventRecruit(form.getEventRecruit());
			entity.setEventSendDate(form.getEventSendDateAsTimestamp());
			entity.setEventStartDate(form.getEventStartDateAsTimestamp());
			entity.setEventTelno(form.getEventTelno());
			entity.setEventTitle(form.getEventTitle());
			entity.setEventUnit(form.getEventUnit());

			entity.setSubjectInsKbn(form.getSubjectInsKbn());
			entity.setPublicFlag(form.getPublicFlag());
			entity.setUpdDate(DateUtil.getNowTimestamp());

			// 更新テーブル：イベント公開範囲テーブル
			if (form.getPageMode().equals(CommonConst.PAGE_MODE_EDIT)) {
				// イベント公開範囲テーブルから対象データをDELETEする。
				evEventPublicTblRepository.delete(entity.getEvEventPublicTbls());
			}

			entity = evEventTblRepository.save(entity);

			List<EvEventPublicTbl> publicList = new ArrayList<>();
			int count = 1;

			// 企業・研究所などがチェックＯＮの場合、１件出力する。
			if (form.checkHasPublicItem("1")) {
				EvEventPublicTbl tbl = new EvEventPublicTbl();
				EvEventPublicTblPK pk = new EvEventPublicTblPK();
				pk.setEventKey(entity.getEventKey());
				pk.setSeqNo(count);
				tbl.setId(pk);

				// 企業・研究所等が、設定されている分の出力を行う場合、１：ROLE。
				tbl.setPublicKbn("1");
				// 企業・研究所等が、設定されている分の出力を行う場合、１：ROLE。
				tbl.setRole("1");
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
				for (int i = 0; i < form.getPublicPartyArray().length; i++) {
					EvEventPublicTbl tbl = new EvEventPublicTbl();
					EvEventPublicTblPK pk = new EvEventPublicTblPK();
					pk.setEventKey(entity.getEventKey());
					pk.setSeqNo(count);
					tbl.setId(pk);

					// 大學が、設定されている分の出力を行う場合、２：組織。
					tbl.setPublicKbn("2");
					// 大學が、設定されている分の出力を行う場合、NULL。
					tbl.setRole(null);
					// 大學が、設定されている分の出力を行う場合、対象大学の組織コード。
					tbl.setPartyCode(form.getPublicPartyArray()[i]);
					// 現在時刻を設定する。
					tbl.setUpdDate(DateUtil.getNowTimestamp());
					// ログイン者のユーザキーの値を設定する。
					tbl.setUpdUserKey(userInfo.getLoginUserKey());
					publicList.add(tbl);
					count = count + 1;
				}
			}
			if (form.getPageMode().equals(CommonConst.PAGE_MODE_ADD)
					|| form.getPageMode().equals(CommonConst.PAGE_MODE_COPY)) {
				// お知らせ情報、お知らせ情報公開範囲登録
				this.insertCmInfo(userInfo, form, "201", userInfo.getTargetRole().getAuthority(), null);
			}

			evEventPublicTblRepository.save(publicList);

			if (entity != null) {
				logger.infoCode("I0002");
				return true;
			}

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

	public List<EvEventPublicTbl> findAll() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<EvEventPublicTbl> list = evEventPublicTblRepository.findAll();

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	public boolean insertCmInfo(UserInfo userInfo, EventForm form, String opeKbn, String roleCode, String partyCode) {
		boolean result = false;

		try {
			CmInfoTbl entity = new CmInfoTbl();
			entity.setSendDate(DateUtil.getNowTimestamp());
			entity.setTitle(form.getEventTitle());
			entity.setDataKbn("2");
			entity.setOpeKbn(opeKbn);
			entity.setInfoRefKey(form.getEventKey());
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

}
