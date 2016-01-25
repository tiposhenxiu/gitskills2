/*
* ファイル名：SupportMgmtController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.internship;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.dto.InternshipViewDto;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.ItInternRecruitTbl;
import jp.co.sraw.entity.ItInternRecruitView;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.InternshipServiceImpl;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.ObjectUtil;

/**
 * <B>InternshipMgmtApplicationControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping(CommonConst.PATH_MGMT + "/internship/application")
public class InternshipMgmtApplicationController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory
			.getLogger(InternshipMgmtApplicationController.class);

	@Autowired
	private InternshipServiceImpl internshipServiceImpl;

	private static final String ITINTERNRECRUITFORM_NAME = "ItInternRecruitForm";

	private static final String REDIRECT_LIST = "redirect:list";
	private static final String REDIRECT_APPLICANT = "redirect:" + CommonConst.PATH_MGMT
			+ "/internship/application/applicant/list";
	private static final String LIST_PAGE = "internship/mgmt/application/list";
	private static final String APPLICANT_PAGE = "internship/mgmt/application/applicantlist";
	private static final String APPLICANT_EDIT = "internship/mgmt/application/applicantedit";

	private static final String ACTION_URL_UPDATE = "applicant/update";

	// インターンシップ区分
	private static final String KBN = "0002";

	private static final String PUBLIC_FLAG = "1"; // 固定値: 公開

	private ObjectUtil objectUtil = new ObjectUtil();

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@ModelAttribute(CommonConst.FORM_NAME)
	public InternshipForm setupForm() {
		InternshipForm form = new InternshipForm();
		return form;
	}

	@ModelAttribute(ITINTERNRECRUITFORM_NAME)
	public ItInternRecruitForm setupInternshiprecuitForm() {
		ItInternRecruitForm form = new ItInternRecruitForm();
		return form;
	}

	/**
	 * 一覧画面表示
	 *
	 * @param form
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String list(@ModelAttribute(CommonConst.FORM_NAME) InternshipForm form, Model model, Locale locale) {

		logger.infoCode("I0001");

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		List<MsCodeDto> kbnList = DbUtil.getJosuList(KBN, locale);

		Map<String, List<InternshipViewDto>> mapList = internshipServiceImpl.getDataList(userInfo, locale);

		model.addAttribute("kbnList", kbnList);
		model.addAttribute("list", mapList);

		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", LIST_PAGE); // I0002=メソッド終了:{0}
		return LIST_PAGE;
	}

	/**
	 * 【応募状況閲覧（応募者一覧） 画面】
	 *
	 * @return
	 */
	@RequestMapping(value = { "/applicant/list", "/applicant/list/{key}" }, method = RequestMethod.GET)
	public String applicantlist(@ModelAttribute(CommonConst.FORM_NAME) InternshipForm form, Model model,
			Locale locale) {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。

		InternshipViewDto dto=internshipServiceImpl.findOneInternshipViewDto(form.getInternshipKey());

		model.addAttribute("dto", dto);

		List<ItInternRecruitView> entityList = internshipServiceImpl.findAllItInternRecruitView(form.getInternshipKey(), null);

		model.addAttribute("list", entityList);

		return APPLICANT_PAGE;
	}

	/**
	 * 【応募状況閲覧（応募者一覧） 画面】
	 *
	 * @return
	 */
	@RequestMapping(value = { "/applicant/edit" }, method = RequestMethod.POST)
	public String edit(@ModelAttribute(ITINTERNRECRUITFORM_NAME) ItInternRecruitForm form, Model model, Locale locale) {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。

		ItInternRecruitTbl itInternRecruitTbl = internshipServiceImpl.getOneInternRecruit(form.getId());

		if (itInternRecruitTbl == null) {
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.empty.data"); // error.data.message.empty.data=データがありません。
		} else {
			form = (ItInternRecruitForm) objectUtil.getObjectCopyValue(form, itInternRecruitTbl);
			form.setRecruitStartDate(
					DateUtil.getDate(itInternRecruitTbl.getRecruitStartDate(), CommonConst.DEFAULT_YYYYMMDD));
			form.setRecruitEndDate(
					DateUtil.getDate(itInternRecruitTbl.getRecruitEndDate(), CommonConst.DEFAULT_YYYYMMDD));
		}

		// 編集の場合
		form.setPageMode(CommonConst.PAGE_MODE_EDIT);
		form.setPageActionUrl(ACTION_URL_UPDATE); // update

		model.addAttribute(CommonConst.FORM_NAME, form);
		logger.infoCode("I0002", "edit"); // I0002=メソッド終了:{0}

		return APPLICANT_EDIT;
	}

	/**
	 * 【応募状況閲覧（応募者一覧） 画面】
	 *
	 * @return
	 */
	@RequestMapping(value = { "/applicant/update" }, method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute(ITINTERNRECRUITFORM_NAME) final ItInternRecruitForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "internshipKey=" + form.getInternshipKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
			}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return APPLICANT_EDIT;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// DB登録
		///////////////////////////////////////////////////////////////////////////////////
		if (internshipServiceImpl.updateInternRecruit(userInfo, form)) {
			///////////////////////////////////////////////////////////////////////////////////
			// 更新の場合
			if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
				// DB更新が成功した場合
				logger.infoCode("I1004", key); // I1004=更新しました。{0}
				attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。
			}

			// 新規追加の場合
			if (CommonConst.PAGE_MODE_ADD.equals(form.getPageMode())) {
				// DB更新が成功した場合
				logger.infoCode("I1005", key); // I1005=新規作成しました。{0}
				// 一覧画面に戻る
				attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.create.success"); // message.data.create.success=データを新規追加しました。
			}

			// 検索条件

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return REDIRECT_APPLICANT;
		}

		// DB更新が失敗した場合
		logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。

		logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		return APPLICANT_EDIT;
	}

	/**
	 * ダイレクトアクセス対策
	 *
	 * @return
	 */
	@RequestMapping(value = { "/edit", "/copy", "/create", "/update", "/delete" }, method = RequestMethod.GET)
	public String redirect() {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。
		return CommonConst.REDIRECT_INDEX;
	}

}
