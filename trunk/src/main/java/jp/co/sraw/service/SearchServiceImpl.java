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
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonService;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.dto.SearchDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.EvEventPublicTblRepository;
import jp.co.sraw.repository.EvEventTblRepository;
import jp.co.sraw.repository.UsUserTblRepository;
import jp.co.sraw.repository.UsUserViewRepository;
import jp.co.sraw.util.DateUtil;

/**
 * <B>UserServiceクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class SearchServiceImpl extends CommonService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private EvEventTblRepository evEventTblRepository;

	@Autowired
	private UsUserViewRepository usUserViewRepository;

	@Autowired
	private UsUserTblRepository usUserTblRepository;

	@Autowired
	private EvEventPublicTblRepository evEventPublicTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(SearchServiceImpl.class);

	private static final String CODE_SYBCODE = "0021";

	private static final String PUBLIC_USER_OPEN_FLAG="1";
	private static final String INSIDE_USER_OPEN_FLAG="2";
	private static final String PUBLIC_OPEN_FLAG="1";
	private static final String INSIDE_OPEN_FLAG="2";
	private static final String KENKYU_USER_KBN="10";
	private static final String HAKASEGO_USER_KBN="20";
	private static final String HAKASEZEN_USER_KBN="30";

	String sqlName = null;

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@SuppressWarnings("unchecked")
	public List<MsCodeDto> searchAllCompanyCode(String josuKbn) {
		logger.infoCode("I0001");
//		String searchAffiliation
		sqlName = "MsCodeTbl.searchAllCompanyCode";

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("josuKbn", josuKbn);

		List<MsCodeDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			MsCodeDto dto = new MsCodeDto();
			dto = (MsCodeDto) setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SearchDto> searchAllFacultyMember(String userKbn) {
		logger.infoCode("I0001");
		@SuppressWarnings("unused")
		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		StringBuilder sb = new StringBuilder();

		List<SearchDto> list = new ArrayList<>();

		sqlName = "UsUserTbl.searchAllFacultyMember";

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("userKbn", userKbn);

		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			SearchDto dto = new SearchDto();
			dto = (SearchDto) setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SearchDto> searchAllUserNameNull() {
		logger.infoCode("I0001");
		@SuppressWarnings("unused")
		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		StringBuilder sb = new StringBuilder();

		List<SearchDto> list = new ArrayList<>();

		sqlName = "UsUserTbl.searchAllUserNameNull";

		Query query = entityManager.createNamedQuery(sqlName);

		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			SearchDto dto = new SearchDto();
			dto = (SearchDto) setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SearchDto> searchAllUserName(String searchKeyword, String searchPartyCode,  String searchBigField, String searchMiddleField,
			String searchSmallField) {
		logger.infoCode("I0001");
		@SuppressWarnings("unused")
		Date today = DateUtil.toDate(DateUtil.getSysdate("yyyyMMdd"));

		StringBuilder sb = new StringBuilder();

		List<SearchDto> list = new ArrayList<>();

		sqlName = "UsUserTbl.searchAllUserName";

		Query query = entityManager.createNamedQuery(sqlName);
		query.setParameter("searchKeyword", searchKeyword);
		query.setParameter("searchPartyCode", searchPartyCode);
		query.setParameter("searchBigField", searchBigField);
		query.setParameter("searchMiddleField", searchMiddleField);
		query.setParameter("searchSmallField", searchSmallField);

		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);


		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			SearchDto dto = new SearchDto();
			dto = (SearchDto) setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SearchDto> searchAllCompany(String partyKbn) {
		// 検索（企業と大学）
		logger.infoCode("I0001");

		sqlName = "UsUserTbl.searchAllCompany";

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("partyKbn", partyKbn);

		List<SearchDto> list = new ArrayList<>();
		List<Map> resultList = new ArrayList<>();
		resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			SearchDto dto = new SearchDto();
			dto = (SearchDto) setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

}
