/*
* ファイル名：SkillBuildServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/18   tsutsumi    新規作成
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

import jp.co.sraw.common.CommonService;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.controller.skill.BuildForm;
import jp.co.sraw.controller.skill.CurriculumForm;
import jp.co.sraw.dto.SkillBuildDto;
import jp.co.sraw.entity.NrLessonCourseTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.NrLessonCourseTblRepository;
import jp.co.sraw.util.DateUtil;

/**
 * <B>能力養成科目のビジネスロジック</B>
 * <P>
 */
@Service
@Transactional(readOnly = false)
public class SkillBuildServiceImpl extends CommonService {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(SkillBuildServiceImpl.class);

	@Override
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private NrLessonCourseTblRepository nrLessonCourseTblRepository;

	// 能力養成科目一覧取得SQL
	private final String FIND_NR_LESSON_TBL_FOR_LESSON_CODE = "NrLessonTbl.findLessonTblForLessonCode";

	/**
	 * 能力養成科目一覧取得
	 * @param userKey 履修状況を取得する対象ユーザキー
	 * @param rubKey ルーブリックキー
	 * @param subjectCode 能力養成コード（RDF No.）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillBuildDto> searchNrLessonTblForLessonCode (String userKey, String rubKey, String subjectCode, String chkSts) {
		// 検索（能力養成科目一覧）
		logger.infoCode("I0001");

		String sqlName = FIND_NR_LESSON_TBL_FOR_LESSON_CODE;

		Query query = entityManager.createNamedQuery(sqlName);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// パラメータ設定
		query.setParameter("userKey", userKey);
		query.setParameter("rubricKey", rubKey);
		query.setParameter("subjectCode", subjectCode);

		// TODO 表示条件のやつをいれる

		List<SkillBuildDto> list = new ArrayList<>();
		List<Map> resultList = query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			SkillBuildDto dto = new SkillBuildDto();
			dto = (SkillBuildDto) setMapCopyValue(dto, resultList.get(i));
			list.add(dto);
		}

		logger.infoCode("I0002");
		return list;
	}

	/**
	 * 履修状況テーブルの該当科目の履修状態を指定した値で更新します。
	 * @param courseStatus 履修状態
	 * @param lessonKey 要請科目コード
	 * @param userKey ユーザキー
	 * @return 更新件数
	 */
	public int updateForCourseStatus(BuildForm form, UserInfo userInfo, String courseStatus) {
		logger.infoCode("I0001", form.getPageMode());

		int cnt = 0;

		try {
			for (String rowNum : form.getSelRow()) {
				// 更新前データ取得
				CurriculumForm curriculum = form.getCurriculumList().get(Integer.parseInt(rowNum));
				NrLessonCourseTbl entity = nrLessonCourseTblRepository.findByIdLessonKeyAndIdUserKeyAndUpdDate(
						curriculum.getLessonKey()
						, userInfo.getTargetUserKey()
						, curriculum.getUpdDate());
				if (entity == null) {
					throw new RuntimeException(rowNum + ": not such rubric, or edited by someone else.");
				}

				// 更新データ設定
				entity.setCourseStatus(courseStatus);
				entity.setUpdUserKey(userInfo.getLoginUserKey());
				entity.setUpdDate(DateUtil.getNowTimestamp());
				entity = nrLessonCourseTblRepository.saveAndFlush(entity);
				if (entity == null) {
					throw new RuntimeException("failed to saveAndFlush().");
				}
				cnt++;
			}
			logger.infoCode("I0002");
			return cnt;
		} catch (Exception e) {
			logger.errorCode("E1007", e);
		}

//		return nrlessonCourseTblRepository.updateForCourseStatus(courseStatus, lessonKey, userKey);
		return cnt;
	}
}
