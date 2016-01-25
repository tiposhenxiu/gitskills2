/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

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
import jp.co.sraw.entity.MsPartyTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.MsPartyTblRepository;

/**
* <B>UserServiceクラス</B>
* <P>
* ユーザーサービスのメソッドを提供する
*/
@Service
@Transactional(readOnly = true)
public class MsPartyServiceImpl extends CommonService {

	@Autowired
	private MsPartyTblRepository msPartyTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MsPartyServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<MsPartyTbl> findAll() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<MsPartyTbl> list = msPartyTblRepository.findAll();

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	public List<MsPartyTbl> findAllByPartyKbn(String[] param) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 取得条件：組織区分
		Specification<MsPartyTbl> wherePartyKbn = (param == null || param.length == 0) ? null
				: new Specification<MsPartyTbl>() {
					@Override
					public Predicate toPredicate(Root<MsPartyTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						Predicate predicate = cb.disjunction();
						for (int i = 0; i < param.length; i++) {
							String partyKbn = param[i];
							predicate = cb.or(predicate, cb.equal(root.get("partyKbn"), partyKbn));
						}
						return predicate;
					}
				};

		List<MsPartyTbl> list = msPartyTblRepository.findAll(Specifications.where(wherePartyKbn), orderBy());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	/**
	 * orderBy
	 *
	 * @return
	 */
	private Sort orderBy() {
		// 組織コード昇順
		return new Sort("partyKbn", "partyCode");
	}

}
