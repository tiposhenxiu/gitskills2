/*
* ファイル名：ResearchAreaServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/16   toishigawa  新規作成
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
import jp.co.sraw.entity.MsCodeTbl;
import jp.co.sraw.entity.MsResearchAreaTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.MsCodeTblRepository;
import jp.co.sraw.repository.MsResearchAreaTblRepository;
import jp.co.sraw.util.StringUtil;

/**
 * <B>ResearchAreaServiceImplクラス</B>
 * <P>
 * 研究分野サービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class MsResearchAreaServiceImpl extends CommonService {

	@Autowired
	private MsResearchAreaTblRepository msResearchAreaTblRepository;

	@Autowired
	private MsCodeTblRepository msCodeTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MsResearchAreaServiceImpl.class);

	public static final String FIELD_CODE_KBN = "0221"; // 大分類リスト（定数区分＝0221）
	public static final String SUBJECT_CODE_KBN = "0222"; // 中分類リスト（定数区分＝0222）

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * 定数テーブルrderBy
	 *
	 * @return
	 */
	private Sort msCodeTblOrderBy() {
		return new Sort(Sort.Direction.ASC, "hyojiSrt", "id.josuKbn", "id.josuCode");
	}

	/**
	 * 研究分野テーブルorderBy
	 *
	 * @return
	 */
	private Sort researchAreaTblOrderBy() {
		return new Sort(Sort.Direction.ASC, "fieldCode", "subjectCode", "researchAreaCode");
	}

	/**
	 * 研究分野コードを取得
	 *
	 * @param researchAreaCode
	 * @return
	 */
	public MsResearchAreaTbl findOne(String researchAreaCode) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		MsResearchAreaTbl m = msResearchAreaTblRepository.findOne(researchAreaCode);

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return m;
	}

	/**
	 * 大分類一覧を取得
	 *
	 * @param josuKbn
	 * @param useFlag
	 * @return
	 */
	public List<MsCodeTbl> findAllFieldCodeList(final String josuKbn, final String useFlag) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<MsCodeTbl> list = findAllSubjectCodeList(josuKbn, null, useFlag);

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	/**
	 * 中分類一覧を取得
	 *
	 * @param kbn
	 * @param fieldCode
	 * @param useFlag
	 * @return
	 */
	public List<MsCodeTbl> findAllSubjectCodeList(final String josuKbn, final String fieldCode, final String useFlag) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 定数区分
		Specification<MsCodeTbl> whereKbn = StringUtil.isNull(josuKbn) ? null : new Specification<MsCodeTbl>() {
			@Override
			public Predicate toPredicate(Root<MsCodeTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("id").get("josuKbn"), josuKbn);
			}
		};
		// その他文字属性１(大分類)
		Specification<MsCodeTbl> whereSntaZksei1Txt = StringUtil.isNull(fieldCode) ? null
				: new Specification<MsCodeTbl>() {
					@Override
					public Predicate toPredicate(Root<MsCodeTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return cb.equal(root.get("sntaZksei1Txt"), fieldCode);
					}
				};
		Specification<MsCodeTbl> whereUseFlag = StringUtil.isNull(useFlag) ? null : new Specification<MsCodeTbl>() {
			@Override
			public Predicate toPredicate(Root<MsCodeTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("useFlag"), useFlag);
			}
		};
		List<MsCodeTbl> list = msCodeTblRepository
				.findAll(Specifications.where(whereKbn).and(whereSntaZksei1Txt).and(whereUseFlag), msCodeTblOrderBy());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	/**
	 * 研究分野コード(小分類)一覧を取得
	 *
	 * @param fieldCode
	 * @param subjectCode
	 * @return
	 */
	public List<MsResearchAreaTbl> findAllResearchAreaCodeList(final String fieldCode, final String subjectCode,
			final String useFlag) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 取得条件：分類コード（レベル１）
		Specification<MsResearchAreaTbl> whereFieldCode = StringUtil.isNull(fieldCode) ? null
				: new Specification<MsResearchAreaTbl>() {
					@Override
					public Predicate toPredicate(Root<MsResearchAreaTbl> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						return cb.equal(root.get("fieldCode"), fieldCode);
					}
				};

		// 取得条件：分科コード（レベル２）
		Specification<MsResearchAreaTbl> whereSubjectCode = StringUtil.isNull(subjectCode) ? null
				: new Specification<MsResearchAreaTbl>() {
					@Override
					public Predicate toPredicate(Root<MsResearchAreaTbl> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						return cb.equal(root.get("subjectCode"), subjectCode);
					}
				};

		// 取得条件：有効／無効フラグ
		Specification<MsResearchAreaTbl> whereUseFlag = StringUtil.isNull(useFlag) ? null
				: new Specification<MsResearchAreaTbl>() {
					@Override
					public Predicate toPredicate(Root<MsResearchAreaTbl> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						return cb.equal(root.get("useFlag"), useFlag);
					}
				};

		List<MsResearchAreaTbl> list = msResearchAreaTblRepository.findAll(
				Specifications.where(whereFieldCode).and(whereSubjectCode).and(whereUseFlag), researchAreaTblOrderBy());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	public List<MsResearchAreaTbl> findAllResearchArea() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		// 取得条件：有効／無効フラグ
		Specification<MsResearchAreaTbl> whereUseFlag = new Specification<MsResearchAreaTbl>() {
			@Override
			public Predicate toPredicate(Root<MsResearchAreaTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("useFlag"), "1");
			}
		};

		List<MsResearchAreaTbl> list = msResearchAreaTblRepository.findAll(Specifications.where(whereUseFlag),
				researchAreaTblOrderBy());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

}
