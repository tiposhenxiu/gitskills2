/*
* ファイル名：HomeController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio;


import java.util.Locale;

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
import jp.co.sraw.controller.portfolio.form.ProfileForm;
import jp.co.sraw.entity.CmFileUploadTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.CmFileUploadServiceImpl;
import jp.co.sraw.service.UserServiceImpl;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;


/**
* <B>ProfileControllerクラス</B>
* <P>
* Controllerのメソッドを提供する
*/
@Controller
@RequestMapping("/portfolio/profile")
public class ProfileController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(ProfileController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private CmFileUploadServiceImpl cmFileUploadServiceImpl;

	private static final String REDIRECT_LIST = "redirect:list";
	private static final String LIST_PAGE = "portfolio/mgmt/list";
	private static final String DOCTOR_PAGE = "portfolio/profile/editDoctor";
	private static final String TEACHER_PAGE = "portfolio/profile/editTeacher";
	private static final String CONSUL_PAGE = "portfolio/profile/editConsul";
	private static final String MGMT_PAGE = "portfolio/profile/editMgmt";
	private static final String PARTY_PAGE = "portfolio/profile/editParty";
	// 公開設定区分
	private static final String KBN_USERPUBLICFLAG = "0024";
	// 学年／職位区分
	private static final String KBN_DEGREE = "0010";
	// 性別区分
	private static final String KBN_SEX = "0025";
	// URL
	private String page = "";

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@RequestMapping(value = { "", "/", "/edit" })
	public String list(Model model, Locale locale) {
		//ユーザ情報取得
		UsUserTbl usUserTbl = userServiceImpl.findOne(userInfo.getTargetUserKey());
		//ファイルアップロード取得
		CmFileUploadTbl cmFileUploadTbl = new CmFileUploadTbl();
		if (usUserTbl.getUploadKey() != null && StringUtil.isNotNull(usUserTbl.getUploadKey())) {
			cmFileUploadTbl = cmFileUploadServiceImpl.findOne(usUserTbl.getUploadKey());
		}

		// 公開設定区分
		model.addAttribute("listUserPublicFlag", DbUtil.getJosuList(KBN_USERPUBLICFLAG, locale));
		// 学年／職位区分
		model.addAttribute("listDegree", DbUtil.getJosuList(KBN_DEGREE, locale));
		// 性別区分
		model.addAttribute("listSex", DbUtil.getJosuList(KBN_SEX, locale));
		ProfileForm form = new ProfileForm();
		form.setUserPublicFlag(usUserTbl.getUserPublicFlag());
		form.setDegree(usUserTbl.getDegree());
		form.setUserFamilyName(usUserTbl.getUserFamilyName());
		form.setUserMiddleName(usUserTbl.getUserMiddleName());
		form.setUserName(usUserTbl.getUserName());
		form.setUserFamilyNameKn(usUserTbl.getUserFamilyNameKn());
		form.setUserMiddleNameKn(usUserTbl.getUserMiddleNameKn());
		form.setUserNameKn(usUserTbl.getUserNameKn());
		form.setUserFamilyNameEn(usUserTbl.getUserFamilyNameEn());
		form.setUserMiddleNameEn(usUserTbl.getUserMiddleNameEn());
		form.setUserNameEn(usUserTbl.getUserNameEn());
		form.setSex(usUserTbl.getSex());
		form.setPartyCode(usUserTbl.getPartyCode());
		form.setAffiliationName(usUserTbl.getAffiliationName());
		form.setResearchSubject(usUserTbl.getResearchSubject());
		form.setStudentId(usUserTbl.getStudentId());
		form.setCountry(usUserTbl.getCountry());
		form.setFreeInput1(usUserTbl.getFreeInput1());
		form.setFreeInput2(usUserTbl.getFreeInput2());
		form.setUploadKey(usUserTbl.getUploadKey());
		form.setImageFileName(cmFileUploadTbl.getFileName());
		form.setUpdDate(usUserTbl.getUpdDate());
		model.addAttribute(CommonConst.FORM_NAME, form);

		if ("11".equals(usUserTbl.getUserKbn()) || "12".equals(usUserTbl.getUserKbn()) || "13".equals(usUserTbl.getUserKbn())) {
			//若手研究員
			page = DOCTOR_PAGE;
		}else if ("21".equals(usUserTbl.getUserKbn())) {
			//教職員
			page = TEACHER_PAGE;
		}else if ("31".equals(usUserTbl.getUserKbn())) {
			//相談員
			page = CONSUL_PAGE;
		}else if ("40".equals(usUserTbl.getUserKbn())) {
			//事務局・大学
			page = MGMT_PAGE;
		}else if ("50".equals(usUserTbl.getUserKbn())) {
			//企業
			page = PARTY_PAGE;
		}
		return page;
	}

	@RequestMapping(value = { "/update/11" , "/update/12" , "/update/13" }, method = RequestMethod.POST)
	public String updateDoctor(@Validated(ProfileForm.Doctor.class) @ModelAttribute(CommonConst.FORM_NAME) final ProfileForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001");
		String key = "userKey="+ userInfo.getTargetUserKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		// 公開設定区分
		model.addAttribute("listUserPublicFlag", DbUtil.getJosuList(KBN_USERPUBLICFLAG, locale));
		// 学年／職位区分
		model.addAttribute("listDegree", DbUtil.getJosuList(KBN_DEGREE, locale));
		// 性別区分
		model.addAttribute("listSex", DbUtil.getJosuList(KBN_SEX, locale));

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
			}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// DB登録
		///////////////////////////////////////////////////////////////////////////////////
		if (userServiceImpl.update(userInfo.getTargetUserKey(), form, userInfo)) {
			///////////////////////////////////////////////////////////////////////////////////
			// DB更新が成功した場合
			logger.infoCode("I1004", key); // I1004=更新しました。{0}
			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。
			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		// DB更新が失敗した場合
		logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。

		logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		return page;
	}

	@RequestMapping(value = "/update/21", method = RequestMethod.POST)
	public String updateTeacher(@Validated(ProfileForm.Teacher.class) @ModelAttribute(CommonConst.FORM_NAME) final ProfileForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001");
		String key = "userKey="+ userInfo.getTargetUserKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		// 公開設定区分
		model.addAttribute("listUserPublicFlag", DbUtil.getJosuList(KBN_USERPUBLICFLAG, locale));
		// 学年／職位区分
		model.addAttribute("listDegree", DbUtil.getJosuList(KBN_DEGREE, locale));
		// 性別区分
		model.addAttribute("listSex", DbUtil.getJosuList(KBN_SEX, locale));

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
			}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// DB登録
		///////////////////////////////////////////////////////////////////////////////////
		if (userServiceImpl.update(userInfo.getTargetUserKey(), form, userInfo)) {
			///////////////////////////////////////////////////////////////////////////////////
			// DB更新が成功した場合
			logger.infoCode("I1004", key); // I1004=更新しました。{0}
			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。
			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		// DB更新が失敗した場合
		logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。

		logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		return page;
	}

	@RequestMapping(value = "/update/31", method = RequestMethod.POST)
	public String updateConsul(@Validated(ProfileForm.Consul.class) @ModelAttribute(CommonConst.FORM_NAME) final ProfileForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001");
		String key = "userKey="+ userInfo.getTargetUserKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		// 公開設定区分
		model.addAttribute("listUserPublicFlag", DbUtil.getJosuList(KBN_USERPUBLICFLAG, locale));
		// 学年／職位区分
		model.addAttribute("listDegree", DbUtil.getJosuList(KBN_DEGREE, locale));
		// 性別区分
		model.addAttribute("listSex", DbUtil.getJosuList(KBN_SEX, locale));

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
			}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// DB登録
		///////////////////////////////////////////////////////////////////////////////////
		if (userServiceImpl.update(userInfo.getTargetUserKey(), form, userInfo)) {
			///////////////////////////////////////////////////////////////////////////////////
			// DB更新が成功した場合
			logger.infoCode("I1004", key); // I1004=更新しました。{0}
			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。
			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		// DB更新が失敗した場合
		logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。

		logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		return page;
	}

	@RequestMapping(value = "/update/40", method = RequestMethod.POST)
	public String updateMgmt(@Validated(ProfileForm.Mgmt.class) @ModelAttribute(CommonConst.FORM_NAME) final ProfileForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001");
		String key = "userKey="+ userInfo.getTargetUserKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		// 公開設定区分
		model.addAttribute("listUserPublicFlag", DbUtil.getJosuList(KBN_USERPUBLICFLAG, locale));
		// 学年／職位区分
		model.addAttribute("listDegree", DbUtil.getJosuList(KBN_DEGREE, locale));
		// 性別区分
		model.addAttribute("listSex", DbUtil.getJosuList(KBN_SEX, locale));

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
			}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// DB登録
		///////////////////////////////////////////////////////////////////////////////////
		if (userServiceImpl.update(userInfo.getTargetUserKey(), form, userInfo)) {
			///////////////////////////////////////////////////////////////////////////////////
			// DB更新が成功した場合
			logger.infoCode("I1004", key); // I1004=更新しました。{0}
			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。
			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		// DB更新が失敗した場合
		logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。

		logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		return page;
	}

	@RequestMapping(value = "/update/50", method = RequestMethod.POST)
	public String updateParty(@Validated(ProfileForm.Party.class) @ModelAttribute(CommonConst.FORM_NAME) final ProfileForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001");
		String key = "userKey="+ userInfo.getTargetUserKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		// 公開設定区分
		model.addAttribute("listUserPublicFlag", DbUtil.getJosuList(KBN_USERPUBLICFLAG, locale));
		// 学年／職位区分
		model.addAttribute("listDegree", DbUtil.getJosuList(KBN_DEGREE, locale));
		// 性別区分
		model.addAttribute("listSex", DbUtil.getJosuList(KBN_SEX, locale));

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
			}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// DB登録
		///////////////////////////////////////////////////////////////////////////////////
		if (userServiceImpl.update(userInfo.getTargetUserKey(), form, userInfo)) {
			///////////////////////////////////////////////////////////////////////////////////
			// DB更新が成功した場合
			logger.infoCode("I1004", key); // I1004=更新しました。{0}
			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。
			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return page;
		}

		// DB更新が失敗した場合
		logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。

		logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		return page;
	}

	/**
	 * ダイレクトアクセス対策
	 *
	 * @return
	 */
	@RequestMapping(value = {"/copy", "/create", "/update", "/delete"}, method = RequestMethod.GET)
	public String redirect() {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。
		return CommonConst.REDIRECT_INDEX;
	}

}
