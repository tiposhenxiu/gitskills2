/*
* ファイル名：SkillDiagServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/05   etoh        新規作成
*/
package jp.co.sraw.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonService;
import jp.co.sraw.controller.skill.AnswerForm;
import jp.co.sraw.controller.skill.DiagForm;
import jp.co.sraw.dto.SkillAnswerDto;
import jp.co.sraw.entity.CmFileUploadTbl;
import jp.co.sraw.entity.NrLessonRelSubjectTbl;
import jp.co.sraw.entity.NrSubjectAnswerBkupTbl;
import jp.co.sraw.entity.NrSubjectAnswerBkupTblPK;
import jp.co.sraw.entity.NrSubjectAnswerTbl;
import jp.co.sraw.entity.NrSubjectAnswerTblPK;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.oxm.rubric.Rubric;
import jp.co.sraw.oxm.rubric.RubricCategory;
import jp.co.sraw.oxm.rubric.RubricPhase;
import jp.co.sraw.repository.NrLessonRelSubjectTblRepository;
import jp.co.sraw.repository.NrSubjectAnswerBkupTblRepository;
import jp.co.sraw.repository.NrSubjectAnswerTblRepository;
import jp.co.sraw.util.StringUtil;

/**
 * <B>能力診断関連のビジネスロジック</B>
 * <P>
 */
@Service
@Transactional(readOnly = false)
public class SkillDiagServiceImpl extends CommonService {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(RubricServiceImpl.class);

	@Autowired
	private NrSubjectAnswerTblRepository answerRepository;

	@Autowired
	private CmFileUploadServiceImpl fileService;

	@Autowired
	private NrLessonRelSubjectTblRepository lessonSubjectRepository;

	@Autowired
	private NrSubjectAnswerBkupTblRepository backupRepository;

	@Override
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<NrSubjectAnswerTbl> findAnswers(String userKey, String rubricKey) {
		return answerRepository.findByIdUserKeyAndIdRubricKeyOrderByUpdDateDesc(userKey, rubricKey);
	}

	public List<NrSubjectAnswerBkupTbl> findPastAnswers(String userKey, String rubricKey) {
		return backupRepository.findByIdUserKeyAndIdRubricKey(userKey, rubricKey);
	}

	public List<NrSubjectAnswerBkupTbl> findPastAnswers(String userKey, String rubricKey, Date savedDate) {
		return backupRepository.findByIdUserKeyAndIdRubricKeyAndIdSaveDateOrderByUpdDateDesc(userKey, rubricKey,
				savedDate);
	}

	public NrSubjectAnswerTbl findAnswerByAbilityCode(String userKey, String rubricKey, String abilityCode) {
		return answerRepository.findByIdUserKeyAndIdRubricKeyAndIdSubjectCode(userKey, rubricKey, abilityCode);
	}

	public NrSubjectAnswerBkupTbl findPastAnswerByAbilityCode(String userKey, String rubricKey, String abilityCode,
			Date savedDate) {
		return backupRepository.findByIdUserKeyAndIdRubricKeyAndIdSubjectCodeAndIdSaveDate(userKey, rubricKey,
				abilityCode, savedDate);
	}

	public List<NrLessonRelSubjectTbl> findAllLessons(String rubricKey) {
		return lessonSubjectRepository.findByIdRubricKeyOrderByIdSubjectCode(rubricKey);
	}

	private class Answer {
		private NrSubjectAnswerTbl ans;
		private NrSubjectAnswerBkupTbl past;

		public Answer(NrSubjectAnswerTbl ans) {
			this.ans = ans;
		}

		public Answer(NrSubjectAnswerBkupTbl past) {
			this.past = past;
		}

		public AnswerPK getId() {
			if (ans != null) {
				return new AnswerPK(ans.getId());
			}
			return new AnswerPK(past.getId());
		}

		public String getActionPlan() {
			if (ans != null) {
				return ans.getActionPlan();
			}
			return past.getActionPlan();
		}

		public String getEvidence() {
			if (ans != null) {
				return ans.getEvidence();
			}
			return past.getEvidence();
		}

		public String fileIdIfAny() {
			if (ans != null) {
				if (ans.getNrSubjectUploadTbls().isEmpty()) {
					return null;
				}
				return ans.getNrSubjectUploadTbls().get(0).getId().getUploadKey();
			}
			// TODO: for past answer?
			return null;
		}

		public Integer getAnswer() {
			if (ans != null) {
				return ans.getAnswer();
			}
			return past.getAnswer();
		}

		public String getAchievePhase() {
			if (ans != null) {
				return ans.getAchievePhase();
			}
			return past.getAchievePhase();
		}
	}

	private class AnswerPK {
		private NrSubjectAnswerTblPK ans;
		private NrSubjectAnswerBkupTblPK past;

		public AnswerPK(NrSubjectAnswerTblPK ans) {
			this.ans = ans;
		}

		public AnswerPK(NrSubjectAnswerBkupTblPK past) {
			this.past = past;
		}

		public String getSubjectCode() {
			if (ans != null) {
				return ans.getSubjectCode();
			}
			return past.getSubjectCode();
		}
	}

	/**
	 * フォームに、回答をセットする。
	 * 
	 * @param form
	 * @param answers
	 * @param rub
	 */
	public void populateForm(DiagForm form, List<NrSubjectAnswerTbl> answers, Rubric rub) {
		populateForm(form, answers.stream().map(Answer::new), rub);
	}

	public void populateFormForPast(DiagForm form, List<NrSubjectAnswerBkupTbl> answers, Rubric rub) {
		populateForm(form, answers.stream().map(Answer::new), rub);
	}

	private void populateForm(DiagForm form, Stream<Answer> answers, Rubric rub) {
		Map<String, List<Answer>> map = answers.collect(Collectors.groupingBy(ans -> ans.getId().getSubjectCode()));
		List<AnswerForm> cats = new ArrayList<AnswerForm>();
		rub.getCategoryList().forEach(cat -> {
			AnswerForm f = new AnswerForm(); // いまのところ大項目に対する回答は無いのだが、構造上、必要。
			populateFormCategory(f, map, cat);
			cats.add(f);
		});
		form.setCategoryList(cats);
	}

	private void populateFormCategory(AnswerForm form, Map<String, List<Answer>> map, RubricCategory cat) {
		form.setSubjectCode(cat.getAbilityCode());

		// 各中項目。
		List<AnswerForm> subcs = new ArrayList<AnswerForm>();
		cat.getChildList().forEach(subc -> {
			AnswerForm f = new AnswerForm();
			populateFormSubCat(f, map, subc);
			subcs.add(f);
		});
		form.setChildList(subcs);
	}

	private void populateFormSubCat(AnswerForm form, Map<String, List<Answer>> map, RubricCategory subc) {
		form.setSubjectCode(subc.getAbilityCode());

		// 中項目の回答。
		if (map.containsKey(subc.getAbilityCode())) {
			populateFormWithBasicAnswer(form, map.get(subc.getAbilityCode()).get(0));
		}

		// 各小項目。
		List<AnswerForm> items = new ArrayList<AnswerForm>();
		subc.getChildList().forEach(item -> {
			AnswerForm f = new AnswerForm();
			if (map.containsKey(item.getAbilityCode())) {
				populateFormItem(f, map.get(item.getAbilityCode()).get(0));
			}
			items.add(f);
		});
		form.setChildList(items);

	}

	/**
	 * 回答の中でも、中項目と小項目に共通するフィールドをセットする。
	 * 
	 * @param form
	 * @param ans
	 */
	private void populateFormWithBasicAnswer(AnswerForm form, Answer ans) {
		form.setActionPlan(ans.getActionPlan());
		form.setEvidence(ans.getEvidence());
		String uploadKey = ans.fileIdIfAny();
		if (uploadKey != null) {
			CmFileUploadTbl file = fileService.getOne(uploadKey);
			form.setEvidenceFileName(file.getFileName());
			form.setEvidenceFileId(file.getUploadKey());
		}
	}

	private void populateFormItem(AnswerForm form, Answer ans) {
		form.setSubjectCode(ans.getId().getSubjectCode());

		populateFormWithBasicAnswer(form, ans);

		// 小項目独自の項目。
		form.setDone(ansToBoolean(ans.getAnswer()));
		form.setPhase(phaseToInteger(ans.getAchievePhase()));
	}

	private Boolean ansToBoolean(Integer ans) {
		if (ans == null) {
			return null;
		}
		return ans == 1 ? true : false;
	}

	private Integer phaseToInteger(String phase) {
		if (StringUtil.isNull(phase)) {
			return null;
		}
		return Integer.valueOf(phase);
	}

	/**
	 * Dtoに小項目の情報と回答内容をセットする。
	 * 
	 * @param dto
	 * @param ans
	 * @param item
	 */
	public void populateDto(SkillAnswerDto dto, String downloadBasePath, String position, NrSubjectAnswerTbl ans,
			RubricCategory item) {
		populateDto(dto, downloadBasePath, position, ans != null ? new Answer(ans) : null, item);
	}

	public void populateDto(SkillAnswerDto dto, String downloadBasePath, String position, NrSubjectAnswerBkupTbl ans,
			RubricCategory item) {
		populateDto(dto, downloadBasePath, position, ans != null ? new Answer(ans) : null, item);
	}

	private void populateDto(SkillAnswerDto dto, String downloadBasePath, String position, Answer ans,
			RubricCategory item) {
		// 小項目の情報。
		dto.setAbilityCode(item.getAbilityCode());
		dto.setName(item.getName());
		RubricPhase[] pmap = item.getPhaseMap();
		dto.setPhaseTargets(
				(String[]) Arrays.stream(pmap).map(p -> p == null ? null : p.getTarget()).toArray(String[]::new));
		dto.setYourTargetPhase(item.getTargetMap().get(position));
		dto.setColSpans(colSpans(pmap));

		// 回答内容。
		if (ans != null) {
			dto.setDone(ansToBoolean(ans.getAnswer()));
			dto.setPhase(phaseToInteger(ans.getAchievePhase()));
			dto.setActionPlan(ans.getActionPlan());
			dto.setEvidence(ans.getEvidence());
			String uploadKey = ans.fileIdIfAny();
			if (uploadKey != null) {
				CmFileUploadTbl file = fileService.getOne(uploadKey);
				dto.setEvidenceFileName(file.getFileName());
				dto.setEvidenceFileId(file.getUploadKey());
			}
		}
	}

	private int[] colSpans(RubricPhase[] pmap) {
		int[] cp = new int[pmap.length];
		Arrays.fill(cp, 1);
		for (int i = cp.length - 1; i > 0; i--) {
			if (pmap[i] == null) {
				cp[i - 1] += cp[i];
				cp[i] = 0;
			}
		}
		return cp;
	}

	public void backup() {
		Date now = new Date(System.currentTimeMillis());
		backupRepository.backup(now);
	}

	public boolean isAchieved(NrSubjectAnswerBkupTbl ans) {
		Integer a = ans.getAnswer();
		return a != null && a == 1;
	}
}
