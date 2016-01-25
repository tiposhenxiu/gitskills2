/*
* ファイル名：IndexController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonService;
import jp.co.sraw.dto.BatchPublicDto;
import jp.co.sraw.dto.EmailDto;
import jp.co.sraw.dto.EventDto;
import jp.co.sraw.dto.InternshipViewDto;
import jp.co.sraw.dto.NewsDto;
import jp.co.sraw.dto.SpSupportDto;
import jp.co.sraw.entity.CmBatchTargetTbl;
import jp.co.sraw.entity.CmBatchTargetTblPK;
import jp.co.sraw.entity.CmInfoPublicTbl;
import jp.co.sraw.entity.CmInfoPublicTblPK;
import jp.co.sraw.entity.CmInfoTbl;
import jp.co.sraw.entity.CmSchedulePublicTbl;
import jp.co.sraw.entity.CmSchedulePublicTblPK;
import jp.co.sraw.entity.CmScheduleTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.CmBatchTargetTblRepository;
import jp.co.sraw.repository.CmInfoPublicTblRepository;
import jp.co.sraw.repository.CmInfoTblRepository;
import jp.co.sraw.repository.CmSchedulePublicTblRepository;
import jp.co.sraw.repository.CmScheduleTblRepository;
import jp.co.sraw.repository.EvEventPublicTblRepository;
import jp.co.sraw.repository.EvEventTblRepository;
import jp.co.sraw.repository.ItInternPublicTblRepository;
import jp.co.sraw.repository.ItInternTblRepository;
import jp.co.sraw.repository.SpSupportTblRepository;
import jp.co.sraw.repository.UsUserTblRepository;
import jp.co.sraw.util.DateUtil;

/**
 * <B>BatchTargetServiceクラス</B>
 * <P>
 */
@Service
@Transactional(readOnly = true)
public class BatchTargetService extends CommonService {

	@Override
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(BatchTargetService.class);

	@Autowired
	private ItInternTblRepository itInternTblRepository;

	@Autowired
	private ItInternPublicTblRepository itInternPublicTblRepository;

	@Autowired
	private EvEventPublicTblRepository evEventPublicTblRepository;

	@Autowired
	private SpSupportTblRepository spSupportTblRepository;

	@Autowired
	private EvEventTblRepository evEventTblRepository;

	@Autowired
	private UsUserTblRepository usUserTblRepository;

	@Autowired
	private CmBatchTargetTblRepository cmBatchTargetTblRepository;

	@Autowired
	private CmInfoTblRepository cmInfoTblRepository;

	@Autowired
	private CmInfoPublicTblRepository cmInfoPublicTblRepository;

	@Autowired
	private CmScheduleTblRepository cmScheduleTblRepository;

	@Autowired
	private CmSchedulePublicTblRepository cmSchedulePublicTblRepository;

	@Autowired
	private EntityManager entityManager;

	public List<NewsDto> findAllBatchSupport() {
		logger.infoCode("I0001");

		String sqlName = "SpSupportTbl.nativeFindAllBatchSupport";

		List<NewsDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			NewsDto dto = new NewsDto();
			dto = (NewsDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	public List<EmailDto> findAllEmailBatch(String infoKey) {
		logger.infoCode("I0001");

		String sqlName = "";
		if ("%03%".equals(infoKey)) {
			sqlName = "UsUserTbl.nativeFindAllEmailBatchCase1";
		} else if ("%01%".equals(infoKey) || "%02%".equals(infoKey)) {
			sqlName = "UsUserTbl.nativeFindAllEmailBatchCase2";
		}

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("infoKey", infoKey);

		List<EmailDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			EmailDto dto = new EmailDto();
			dto = (EmailDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	public List<BatchPublicDto> findAllNewsBatch(String infoRefKey) {
		logger.infoCode("I0001");

		String sqlName = "CmInfoTbl.nativeFindAllNewsBatch";

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("infoRefKey", infoRefKey);

		List<BatchPublicDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			BatchPublicDto dto = new BatchPublicDto();
			dto = (BatchPublicDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	public List<BatchPublicDto> findAllEventPublicBatch(String infoRefKey) {
		logger.infoCode("I0001");

		String sqlName = "EvEventPublicTbl.nativeFindAllEventPublicBatch";

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("infoRefKey", infoRefKey);

		List<BatchPublicDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			BatchPublicDto dto = new BatchPublicDto();
			dto = (BatchPublicDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	public List<BatchPublicDto> findAllInternshipPublicBatch(String infoRefKey) {
		logger.infoCode("I0001");

		String sqlName = "ItInternPublicTbl.nativeFindAllInternshipPublicBatch";

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("infoRefKey", infoRefKey);

		List<BatchPublicDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			BatchPublicDto dto = new BatchPublicDto();
			dto = (BatchPublicDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	public List<NewsDto> findAllBatchEvent() {
		logger.infoCode("I0001");

		String sqlName = "EvEventTbl.nativeFindAllBatchEvent";

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List<NewsDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			NewsDto dto = new NewsDto();
			dto = (NewsDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	public List<NewsDto> findAllBatchInternship() {
		logger.infoCode("I0001");

		String sqlName = "ItInternTbl.nativeFindAllBatchInternship";

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List<NewsDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			NewsDto dto = new NewsDto();
			dto = (NewsDto) objectUtil.setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	@Transactional
	public boolean insertNewsBatch(String sendDate, String title, String dataKbn, String opeKbn, String refKey,
			String batchFlag, int seqNo, String publicKbn, String role, String partyCode, String newsOrScheduleFlag,
			int smallint) throws Exception {
		boolean result = false;
		String sendDateEnd = null;
		logger.infoCode("I0001");
		if (refKey.isEmpty()) {
			return false;
		}
		try {
			// お知らせ情報作成
			if ("1".equals(newsOrScheduleFlag)) {
				// CmInfoTbl
				CmInfoTbl info = new CmInfoTbl();
				info.setSendDate(
						DateUtil.formatTimestampStart(DateUtil.getTimestamp(sendDate, CommonConst.DEFAULT_YYYYMMDD)));
				info.setTitle(title);
				info.setDataKbn(dataKbn);
				info.setOpeKbn(opeKbn);
				info.setInfoRefKey(refKey);
				info.setMakeUserKey("BATCH");
				info.setUpdUserKey("BATCH");
				info.setUpdDate(DateUtil.getNowTimestamp());
				CmInfoTbl infoResult = cmInfoTblRepository.save(info);

				// CmInfoPublicTbl
				CmInfoPublicTbl infoPublic = new CmInfoPublicTbl();
				CmInfoPublicTblPK infoPublicId = new CmInfoPublicTblPK();
				infoPublicId.setInfoKey(infoResult.getInfoKey());

				// supportの場合
				if ("1".equals(batchFlag)) {
					infoPublicId.setSeqNo(1);
					infoPublic.setId(infoPublicId);
					infoPublic.setPublicKbn("4");
					infoPublic.setRole(null);
					infoPublic.setPartyCode(null);
					// eventの場合 と intershipの場合
				} else if ("2".equals(batchFlag) || "3".equals(batchFlag)) {
					infoPublicId.setSeqNo(seqNo);
					infoPublic.setId(infoPublicId);
					infoPublic.setPublicKbn(publicKbn);
					infoPublic.setRole(role);
					infoPublic.setPartyCode(partyCode);
				}

				infoPublic.setUpdUserKey("BATCH");
				infoPublic.setUpdDate(DateUtil.getNowTimestamp());
				CmInfoPublicTbl infoPublicResult = cmInfoPublicTblRepository.save(infoPublic);
				cmInfoTblRepository.flush();
				cmInfoPublicTblRepository.flush();
				result = true;
				// スケジュール作成
			} else if ("2".equals(newsOrScheduleFlag)) {
				// CmInfoTbl
				CmScheduleTbl info = new CmScheduleTbl();
				info.setSuhduleDate(
						DateUtil.formatTimestampStart(DateUtil.getTimestamp(sendDate, CommonConst.DEFAULT_YYYYMMDD)));
				info.setStartTime(null);
				info.setEndTime(null);
				info.setTitle(title);
				info.setDataKbn(dataKbn);
				info.setSchduleRefKey(refKey);
				info.setMakeUserKey("BATCH");
				info.setUpdUserKey("BATCH");
				info.setUpdDate(DateUtil.getNowTimestamp());
				CmScheduleTbl infoResult = cmScheduleTblRepository.save(info);

				// CmSchedulePublicTbl
				CmSchedulePublicTbl infoPublic = new CmSchedulePublicTbl();
				CmSchedulePublicTblPK infoPublicId = new CmSchedulePublicTblPK();
				infoPublicId.setSuhduleKey(infoResult.getSuhduleKey());

				// supportの場合
				if ("1".equals(batchFlag)) {
					infoPublicId.setSeqNo(1);
					infoPublic.setId(infoPublicId);
					infoPublic.setPublicKbn("4");
					infoPublic.setRole(null);
					infoPublic.setPartyCode(null);
					// eventの場合 と intershipの場合
				} else if ("2".equals(batchFlag) || "3".equals(batchFlag)) {
					infoPublicId.setSeqNo(seqNo);
					infoPublic.setId(infoPublicId);
					infoPublic.setPublicKbn(publicKbn);
					infoPublic.setRole(role);
					infoPublic.setPartyCode(partyCode);
				}

				infoPublic.setUpdUserKey("BATCH");
				infoPublic.setUpdDate(DateUtil.getNowTimestamp());
				CmSchedulePublicTbl infoPublicResult = cmSchedulePublicTblRepository.save(infoPublic);
				cmScheduleTblRepository.flush();
				cmSchedulePublicTblRepository.flush();
				result = true;
				// スケジュール作成 応募期間（FROM）と応募期間（TO）用の２セット作成用
			} else if ("3".equals(newsOrScheduleFlag)) {

				// CmScheduleTbl
				CmScheduleTbl infoFrom = new CmScheduleTbl();
				CmScheduleTbl infoTo = new CmScheduleTbl();
				infoFrom.setSuhduleDate(
						DateUtil.formatTimestampStart(DateUtil.getTimestamp(sendDate, CommonConst.DEFAULT_YYYYMMDD)));
				infoFrom.setStartTime(null);
				infoFrom.setEndTime(null);
				infoFrom.setTitle(title);
				infoFrom.setDataKbn(dataKbn);
				infoFrom.setSchduleRefKey(refKey);
				infoFrom.setMakeUserKey("BATCH");
				infoFrom.setUpdUserKey("BATCH");
				infoFrom.setUpdDate(DateUtil.getNowTimestamp());

				sendDateEnd = sendDate.substring(0, 4) + sendDate.substring(5, 7) + sendDate.substring(8, 10);
				infoTo.setSuhduleDate(DateUtil.formatTimestampStart(
						DateUtil.getTimestamp(DateUtil.addDay(sendDateEnd, smallint), CommonConst.DEFAULT_YYYYMMDD)));
				infoTo.setStartTime(null);
				infoTo.setEndTime(null);
				infoTo.setTitle(title);
				infoTo.setDataKbn(dataKbn);
				infoTo.setSchduleRefKey(refKey);
				infoTo.setMakeUserKey("BATCH");
				infoTo.setUpdUserKey("BATCH");
				infoTo.setUpdDate(DateUtil.getNowTimestamp());

				CmScheduleTbl infoFromResult = cmScheduleTblRepository.save(infoFrom);
				CmScheduleTbl infoToResult = cmScheduleTblRepository.save(infoTo);

				// CmSchedulePublicTbl
				CmSchedulePublicTbl infoFromPublic = new CmSchedulePublicTbl();
				CmSchedulePublicTbl infoToPublic = new CmSchedulePublicTbl();
				CmSchedulePublicTblPK infoFromPublicId = new CmSchedulePublicTblPK();
				CmSchedulePublicTblPK infoToPublicId = new CmSchedulePublicTblPK();
				infoFromPublicId.setSuhduleKey(infoFromResult.getSuhduleKey());
				infoToPublicId.setSuhduleKey(infoToResult.getSuhduleKey());

				// supportの場合
				if ("1".equals(batchFlag)) {
					infoFromPublicId.setSeqNo(1);
					infoFromPublic.setId(infoFromPublicId);
					infoFromPublic.setPublicKbn("4");
					infoFromPublic.setRole(null);
					infoFromPublic.setPartyCode(null);

					infoToPublicId.setSeqNo(1);
					infoToPublic.setId(infoToPublicId);
					infoToPublic.setPublicKbn("4");
					infoToPublic.setRole(null);
					infoToPublic.setPartyCode(null);
					// eventの場合 と intershipの場合
				} else if ("2".equals(batchFlag) || "3".equals(batchFlag)) {
					infoFromPublicId.setSeqNo(seqNo);
					infoFromPublic.setId(infoFromPublicId);
					infoFromPublic.setPublicKbn(publicKbn);
					infoFromPublic.setRole(role);
					infoFromPublic.setPartyCode(partyCode);

					infoToPublicId.setSeqNo(seqNo);
					infoToPublic.setId(infoToPublicId);
					infoToPublic.setPublicKbn(publicKbn);
					infoToPublic.setRole(role);
					infoToPublic.setPartyCode(partyCode);
				}

				infoFromPublic.setUpdUserKey("BATCH");
				infoFromPublic.setUpdDate(DateUtil.getNowTimestamp());
				infoToPublic.setUpdUserKey("BATCH");
				infoToPublic.setUpdDate(DateUtil.getNowTimestamp());
				CmSchedulePublicTbl infoFromPublicResult = cmSchedulePublicTblRepository.save(infoFromPublic);
				CmSchedulePublicTbl infoToPublicResult = cmSchedulePublicTblRepository.save(infoToPublic);
				cmScheduleTblRepository.flush();
				cmSchedulePublicTblRepository.flush();
				result = true;
			}
		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
			throw e;
		}
		return result;
	}

	@Transactional
	public boolean updateNewsBatch(String refKey, String dataKbn) throws Exception {
		logger.infoCode("I0001");
		if (refKey.isEmpty()) {
			return true;
		}
		try {
			CmBatchTargetTbl entity = new CmBatchTargetTbl();
			CmBatchTargetTblPK id = new CmBatchTargetTblPK();
			id.setRefDataKey(refKey);
			id.setMakeDate(DateUtil.getNowTimestamp());
			id.setDataKbn(dataKbn);
			entity.setId(id);
			entity.setInfoMakeFlag("1");
			entity.setMailMakeFlag("0");
			entity.setScheduleMakeFlag("0");
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity.setUpdUserKey("BATCH");
			entity = cmBatchTargetTblRepository.saveAndFlush(entity);
			if (entity != null) {
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1008", e); // E1008=更新に失敗しました。{0}
			throw e;
		}
		return false;
	}

	@Transactional
	public boolean updateEmailBatch(String refKey, String dataKbn) throws Exception {
		logger.infoCode("I0001");
		if (refKey.isEmpty()) {
			return true;
		}
		try {
			CmBatchTargetTbl entity = new CmBatchTargetTbl();
			CmBatchTargetTblPK id = new CmBatchTargetTblPK();
			id.setMakeDate(DateUtil.getNowTimestamp());
			id.setRefDataKey(refKey);
			id.setDataKbn(dataKbn);
			entity.setId(id);
			entity.setInfoMakeFlag("0");
			entity.setMailMakeFlag("1");
			entity.setScheduleMakeFlag("0");
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity.setUpdUserKey("BATCH");
			entity = cmBatchTargetTblRepository.saveAndFlush(entity);
			if (entity != null) {
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1008", e); // E1008=更新に失敗しました。{0}
			throw e;
		}
		return false;
	}

	@Transactional
	public boolean updateScheduleBatch(String refKey, String dataKbn) throws Exception {
		logger.infoCode("I0001");
		if (refKey.isEmpty()) {
			return true;
		}
		try {
			CmBatchTargetTbl entity = new CmBatchTargetTbl();
			CmBatchTargetTblPK id = new CmBatchTargetTblPK();
			id.setMakeDate(DateUtil.getNowTimestamp());
			id.setRefDataKey(refKey);
			id.setDataKbn(dataKbn);
			entity.setId(id);
			entity.setInfoMakeFlag("0");
			entity.setMailMakeFlag("0");
			entity.setScheduleMakeFlag("1");
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity.setUpdUserKey("BATCH");
			entity = cmBatchTargetTblRepository.saveAndFlush(entity);
			if (entity != null) {
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1008", e); // E1008=更新に失敗しました。{0}
			throw e;
		}
		return false;
	}

	@Transactional
	public boolean updateEmailTitleBatch(String title) throws Exception {
		logger.infoCode("I0001");
		if (title.isEmpty()) {
			return true;
		}
		try {
			UsUserTbl entity = new UsUserTbl();
			entity.setResearchSubject(title);
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity = usUserTblRepository.saveAndFlush(entity);
			if (entity != null) {
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1008", e); // E1008=更新に失敗しました。{0}
			throw e;
		}
		return false;
	}

	// バッチ処理用抽出データの編集
	@Transactional
	public boolean updateSupportBatchTarget(List<SpSupportDto> spSupportDtoList) throws Exception {
		logger.infoCode("I0001");
		if (spSupportDtoList.size() == 0) {
			return true;
		}
		try {
			int i = 0;
			List<CmBatchTargetTbl> entityList = new ArrayList<>();
			for (i = 0; i < spSupportDtoList.size(); i++) {
				CmBatchTargetTbl entity = new CmBatchTargetTbl();
				CmBatchTargetTblPK id = new CmBatchTargetTblPK();
				id.setMakeDate(spSupportDtoList.get(i).getSupportStartDate());
				if (spSupportDtoList.get(i).getSupportSpkikiKbn() != null) {
					id.setDataKbn("1");
				} else {
					id.setDataKbn("2");
				}
				id.setRefDataKey(spSupportDtoList.get(i).getSupportKey());
				entity.setId(id);
				entity.setUpdUserKey(spSupportDtoList.get(i).getUpdUserKey());
				entity.setInfoMakeFlag("1");
				entity.setMailMakeFlag("0");
				entity.setScheduleMakeFlag("0");
				entity.setUpdDate(DateUtil.getNowTimestamp());
				entityList.add(entity);
			}
			entityList = cmBatchTargetTblRepository.save(entityList);
			if (entityList != null) {
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
			throw e;
		}
		return false;
	}

	@Transactional
	public boolean updateEventBatchTarget(List<EventDto> eventDtoList) throws Exception {
		logger.infoCode("I0001");
		if (eventDtoList.size() == 0) {
			return true;
		}
		try {
			int i = 0;
			List<CmBatchTargetTbl> entityList = new ArrayList<>();
			for (i = 0; i < eventDtoList.size(); i++) {
				CmBatchTargetTbl entity = new CmBatchTargetTbl();
				CmBatchTargetTblPK id = new CmBatchTargetTblPK();
				id.setMakeDate(eventDtoList.get(i).getEventStartDate());
				id.setDataKbn("3");
				id.setRefDataKey(eventDtoList.get(i).getEventKey());
				entity.setId(id);
				entity.setUpdUserKey(eventDtoList.get(i).getUpdUserKey());
				entity.setInfoMakeFlag("0");
				entity.setMailMakeFlag("0");
				entity.setScheduleMakeFlag("0");
				entity.setUpdDate(DateUtil.getNowTimestamp());
				entityList.add(entity);
			}
			entityList = cmBatchTargetTblRepository.save(entityList);
			if (entityList != null) {
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
			throw e;
		}
		return false;
	}

	@Transactional
	public boolean updateInternshipBatchTarget(List<InternshipViewDto> internshipViewDtoList) throws Exception {
		logger.infoCode("I0001");
		if (internshipViewDtoList.size() == 0) {
			return true;
		}
		try {
			int i = 0;
			List<CmBatchTargetTbl> entityList = new ArrayList<>();
			for (i = 0; i < internshipViewDtoList.size(); i++) {
				CmBatchTargetTbl entity = new CmBatchTargetTbl();
				CmBatchTargetTblPK id = new CmBatchTargetTblPK();
				id.setMakeDate(internshipViewDtoList.get(i).getInternshipStartDate());
				id.setDataKbn("4");
				id.setRefDataKey(internshipViewDtoList.get(i).getInternshipKey());
				entity.setId(id);
				entity.setUpdUserKey(internshipViewDtoList.get(i).getUpdUserKey());
				entity.setInfoMakeFlag("0");
				entity.setMailMakeFlag("0");
				entity.setScheduleMakeFlag("0");
				entity.setUpdDate(DateUtil.getNowTimestamp());
				entityList.add(entity);
			}
			entityList = cmBatchTargetTblRepository.save(entityList);
			if (entityList != null) {
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
			throw e;
		}
		return false;
	}

	@Transactional
	public boolean deleteScheduleBatch(String refKey, String dataKbn) throws Exception {
		logger.infoCode("I0001");
		if (refKey.isEmpty()) {
			return true;
		}
		try {
			int c1 = cmScheduleTblRepository.delete(refKey, dataKbn);
			int c2 = cmSchedulePublicTblRepository.delete(refKey);

			if (c1 > 0 && c2 > 0) {
				cmScheduleTblRepository.flush();
				cmSchedulePublicTblRepository.flush();
				logger.infoCode("I0002"); // I0002=メソッド終了:{0}
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=削除に失敗しました。{0}
			throw e;
		}
		return false;
	}

	// TODO
	@Transactional
	public boolean sendMailBatch(String title) throws Exception {
		logger.infoCode("I0001");
		if (title.isEmpty()) {
			return true;
		}
		try {
			// TODO発信の処理を呼び出す、成功なら、DB更新、失敗なら、ロールバック(ROLLBACK)
			// int c1 =sendMail();
			// if (c1 > 0 )
			// TblRepository.flush();
			// 発信成功
			logger.infoCode("I0002"); // I0002=メソッド終了:{0}
			return true;
			// }
		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=発信に失敗しました。{0}
			throw e;
		}
	}

}
