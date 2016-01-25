/*
* ファイル名：MsRoleServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.Date;
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

import jp.co.sraw.common.CommonService;
import jp.co.sraw.dto.UsOperationHistoryDto;
import jp.co.sraw.entity.CmInfoTbl;
import jp.co.sraw.entity.CmScheduleInfoView;
import jp.co.sraw.entity.CmScheduleTbl;
import jp.co.sraw.entity.UsInfoTbl;
import jp.co.sraw.entity.UsScheduleTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.CmInfoTblRepository;
import jp.co.sraw.repository.CmScheduleInfoViewRepository;
import jp.co.sraw.repository.CmScheduleTblRepository;
import jp.co.sraw.repository.UsInfoTblRepository;
import jp.co.sraw.repository.UsOperationHistoryTblRepository;
import jp.co.sraw.repository.UsScheduleTblRepository;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;

/**
* <B>HomeServiceクラス</B>
* <P>
* ホーム(ポータル)サービスのメソッドを提供する
*/
@Service
@Transactional(readOnly = true)
public class HomeServiceImpl extends CommonService {

	@Autowired
	private UsInfoTblRepository usInfoTblRepository;

	@Autowired
	private CmInfoTblRepository cmInfoTblRepository;

	@Autowired
	private UsScheduleTblRepository usScheduleTblRepository;

	@Autowired
	private CmScheduleTblRepository cmScheduleTblRepository;

	@Autowired
	private UsOperationHistoryTblRepository usOperationHistoryTblRepository;

	@Autowired
	private CmScheduleInfoViewRepository cmScheduleInfoViewRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MsRoleServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * 個人用お知らせ情報取得
	 *
	 * @param userKey
	 * @return
	 */
	public List<UsInfoTbl> findAllUsInfoByUserKey(String userKey) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 取得条件：操作者のユーザキー
		Specification<UsInfoTbl> whereUserKey = StringUtil.isNull(userKey) ? null
				: new Specification<UsInfoTbl>() {
					@Override
					public Predicate toPredicate(Root<UsInfoTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("usUserTbl").get("userKey"), userKey);
					}
				};

		List<UsInfoTbl> resultList = usInfoTblRepository.findAll(Specifications.where(whereUserKey), orderBySendDate());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return resultList;
	}

	/**
	 * orderBySendDate
	 *
	 * @return
	 */
	private Sort orderBySendDate() {
		// 配信日（降順）
		return new Sort(Sort.Direction.DESC, "sendDate");
	}

	/**
	 * 組織。及び、ロール向けお知らせ情報取得
	 *
	 * @param partyCode
	 * @param role
	 * @return
	 */
	public List<CmInfoTbl> findAllCmInfoByPartyOrRoll(String partyCode, String role) {
		return cmInfoTblRepository.findAllByPartyOrRoll(partyCode, role);
	}

	/**
	 * 個人用スケジュール取得
	 *
	 * @param dateArray
	 * @param userKey
	 * @return
	 */
	public List<UsScheduleTbl> findAllUsSchedule(String[] dateArray, String userKey) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		Date startDate = DateUtil.toDate(dateArray[0]);
		Date endDate = DateUtil.toDate(dateArray[1]);

		// 取得条件：操作者のユーザキー
		Specification<UsScheduleTbl> whereUserKey = StringUtil.isNull(userKey) ? null
				: new Specification<UsScheduleTbl>() {
					@Override
					public Predicate toPredicate(Root<UsScheduleTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("usUserTbl").get("userKey"), userKey);
					}
				};

		// 取得条件：スケジュール日付 between
		Specification<UsScheduleTbl> whereSuhduleDate = dateArray.length == 0 ? null
				: new Specification<UsScheduleTbl>() {
					@Override
					public Predicate toPredicate(Root<UsScheduleTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.between(root.get("suhduleDate").as(Date.class), startDate, endDate);
					}
				};

		List<UsScheduleTbl> resultList = usScheduleTblRepository.findAll(Specifications.where(whereUserKey).and(whereSuhduleDate), orderByUsSchedule());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return resultList;
	}

	/**
	 * orderBySendDate
	 *
	 * @return
	 */
	private Sort orderByUsSchedule() {
		//スケジュール日付
		//開始時間
		//終了時間
		return new Sort("suhduleDate", "startTime", "endTime");
	}

	/**
	 * 組織。及び、ロール向けスケジュール情報取得
	 *
	 * @param dateArray
	 * @param partyCode
	 * @param role
	 * @return
	 */
	public List<CmScheduleTbl> findAllCmSchedule(String[] dateArray, String partyCode, String role) {
		Date startDate = DateUtil.toDate(dateArray[0]);
		Date endDate = DateUtil.toDate(dateArray[1]);

		return cmScheduleTblRepository.findAllByPartyOrRoll(startDate, endDate, partyCode, role);
	}

	/**
	 * スケジュール詳細情報取得
	 *
	 * @param
	 * @return
	 */
	public CmScheduleInfoView findOneCmScheduleInfoView(String dataKbn, String schduleRefKey) {

		// 取得条件：データ区分
		Specification<CmScheduleInfoView> whereDataKbn = StringUtil.isNull(dataKbn) ? null
				: new Specification<CmScheduleInfoView>() {
					@Override
					public Predicate toPredicate(Root<CmScheduleInfoView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("dataKbn"), dataKbn);
					}
				};

		// 取得条件：参照キー
		Specification<CmScheduleInfoView> whereSchduleRefKey = StringUtil.isNull(schduleRefKey) ? null
				: new Specification<CmScheduleInfoView>() {
					@Override
					public Predicate toPredicate(Root<CmScheduleInfoView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("schduleRefKey"), schduleRefKey);
					}
				};

		return cmScheduleInfoViewRepository.findOne(Specifications.where(whereDataKbn).and(whereSchduleRefKey));
	}

	/**
	 * 操作履歴取得
	 *
	 * @param
	 * @return
	 */
	public List<UsOperationHistoryDto> findAllUsOperationHistory(String operationUserKey) {
		return usOperationHistoryTblRepository.findAllByUserKey(operationUserKey);
	}

}
