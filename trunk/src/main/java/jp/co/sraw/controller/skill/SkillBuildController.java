/*
* ファイル名：SkillBuildController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/14   tsutsumi    新規作成
*/
package jp.co.sraw.controller.skill;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.dto.SkillBuildDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.oxm.rubric.Rubric;
import jp.co.sraw.oxm.rubric.RubricCategory;
import jp.co.sraw.service.RubricServiceImpl;
import jp.co.sraw.service.SkillBuildServiceImpl;

@Controller
@RequestMapping("/skill/build")
public class SkillBuildController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(SkillBuildController.class);

	// 遷移先。
	private static final String INDEX_PAGE = "skill/build/index";
	private static final String LIST_PAGE = "/skill/build/list";
	private static final String DETAIL_PAGE = "/skill/build/detail";

	// 履修状況
	/** 履修状況：参加確定 */
	private static final String COURSE_STATUS_JOIN = "2";

	@Autowired
	private RubricServiceImpl rubricService;

	@Autowired
	private SkillBuildServiceImpl skillBuildService;

	@Override
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	// 全能力養成科目画面 表示
	@RequestMapping({ "", "/", "/index" })
	public String index(HttpServletRequest request
			, @PathVariable("rkey") String rkey
			, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// ルーブリック内容取得
		Rubric rub = rubricService.findOne(rkey);
		model.addAttribute("rubric", rub);

		// TODO:

		logger.infoCode("I0002", INDEX_PAGE);
		return INDEX_PAGE;
	}

	/**
	 * 能力養成科目一覧画面 表示
	 * @param request
	 * @param rkey ルーブリックキー
	 * @param chkSts 表示条件
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping("/list/{rkey}")
	public String list(HttpServletRequest request
			, @PathVariable("rkey") String rkey
			, @ModelAttribute(CommonConst.FORM_NAME) BuildForm selForm
			, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// ルーブリック内容取得
		Rubric rub = rubricService.findOne(rkey);
		model.addAttribute("rubric", rub);


		// TODO
		String chkSts = "";

		// 履修状況・推奨科目情報取得
		Map<String, List<SkillBuildDto>> subjectMap = new HashMap<String, List<SkillBuildDto>>();
		for (RubricCategory rubCt : rub.getCategoryList()) {
			for (RubricCategory subCt : rubCt.getChildList()) {
				for (RubricCategory grantCt : subCt.getChildList()) {
					List<SkillBuildDto> list = skillBuildService
							.searchNrLessonTblForLessonCode(userInfo.getTargetUserKey(), rkey, grantCt.getAbilityCode(), chkSts);
					subjectMap.put(grantCt.getAbilityCode(), list);
				}
			}
		}
		model.addAttribute("subjectMap", subjectMap);

		logger.infoCode("I0002", LIST_PAGE);
		return LIST_PAGE;
	}

	/**
	 * 能力養成科目一覧画面 参加確定処理
	 *
	 * @param request
	 * @param selForm
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/fix", method = RequestMethod.POST)
	public String fix(HttpServletRequest request
			, @ModelAttribute(CommonConst.FORM_NAME) BuildForm selForm
			, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// チェックしている養成科目を「参加確定」に更新する。
		skillBuildService.updateForCourseStatus(selForm, userInfo, COURSE_STATUS_JOIN);

		logger.infoCode("I0002", LIST_PAGE);
		return LIST_PAGE;
	}

	// 能力養成科目詳細画面 表示
	@RequestMapping("/detail/{lessonKey}")
	public String detail(HttpServletRequest request, @PathVariable("lessonKey") String lessonKey
			, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// TODO:

		logger.infoCode("I0002", DETAIL_PAGE);
		return DETAIL_PAGE;
	}

	// 能力養成科目詳細画面 参加予約
	@RequestMapping("/reserve/{lessonKey}")
	public String reserve(HttpServletRequest request, @PathVariable("lessonKey") String lessonKey
			, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// TODO:

		logger.infoCode("I0002", DETAIL_PAGE);
		return DETAIL_PAGE;
	}

	// 能力養成科目詳細画面 参加取消
	@RequestMapping("/cancel/{lessonKey}")
	public String cancel(HttpServletRequest request, @PathVariable("lessonKey") String lessonKey
			, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// TODO:

		logger.infoCode("I0002", DETAIL_PAGE);
		return DETAIL_PAGE;
	}
}
