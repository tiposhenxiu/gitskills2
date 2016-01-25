/*
* ファイル名：AccountController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.account;

import java.util.Locale;

import javax.annotation.PostConstruct;

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
import jp.co.sraw.config.WebSecurityConfig;
import jp.co.sraw.controller.login.LoginController;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;

/**
* <B>AccountControllerクラス</B>
* <P>
* Controllerのメソッドを提供する
*/
@Controller
@RequestMapping("/account")
public class AccountController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(LoginController.class);

	private static final String REDIRECT_INDEX = "redirect:index";
	private static final String REDIRECT_REGIST = "redirect:regist";
	private static final String REDIRECT_HOME = "redirect:"+ WebSecurityConfig.DEFAULT_SUCCESS_URL;
	private static final String INDEX_PAGE = "account/index";
	private static final String PROVISIONAL_PAGE = "account/provisional";
	private static final String REGIST_PAGE = "account/regist";
	private static final String CONFIRM_PAGE = "account/confirm";

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@ModelAttribute(CommonConst.FORM_NAME)
	public AccountForm setupForm() {
		AccountForm form = new AccountForm();
		return form;
	}

	/**
	 * アカウント登録 初期
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping({"", "/"})
	public String index(@ModelAttribute(CommonConst.FORM_NAME) AccountForm form, Model model) {
		logger.infoCode("I0001", "index"); // I0001=メソッド開始:{0}

		logger.infoCode("I0002", "index"); // I0002=メソッド終了:{0}
		return INDEX_PAGE;
	}


	/**
	 * アカウント登録 申請テーブル保存＆メール配信
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/provisional", method = RequestMethod.POST)
	public String provisional(@Validated(AccountForm.Step1.class) @ModelAttribute(CommonConst.FORM_NAME) AccountForm form, BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001", "provisional"); // I0001=メソッド開始:{0}

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
			}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return INDEX_PAGE;
		}

		logger.infoCode("I0002", "provisional"); // I0002=メソッド終了:{0}
		return PROVISIONAL_PAGE;
	}


	/**
	 * アカウント本登録 初期＆入力
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping("/regist/{requestId}")
	public String regist(@ModelAttribute(CommonConst.FORM_NAME) AccountForm form, BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001", "regist"); // I0001=メソッド開始:{0}


		logger.infoCode("I0002", "regist"); // I0002=メソッド終了:{0}
		return REGIST_PAGE;
	}

	/**
	 * アカウント本登録 学認 確認
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/ssoCconfirm", method = RequestMethod.POST)
	public String ssoConfirm(@Validated(AccountForm.Step2.class)  @ModelAttribute(CommonConst.FORM_NAME) AccountForm form, BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001", "ssoConfirm"); // I0001=メソッド開始:{0}


		logger.infoCode("I0002", "ssoConfirm"); // I0002=メソッド終了:{0}
		return CONFIRM_PAGE;
	}


	/**
	 * アカウント本登録 DB認証 確認
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String dbConfirm(@Validated(AccountForm.Step3.class)  @ModelAttribute(CommonConst.FORM_NAME) AccountForm form, BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001", "dbConfirm"); // I0001=メソッド開始:{0}


		logger.infoCode("I0002", "dbConfirm"); // I0002=メソッド終了:{0}
		return CONFIRM_PAGE;
	}

	/**
	 * アカウント本登録 完了
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute(CommonConst.FORM_NAME) final AccountForm form, BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001", "create"); // I0001=メソッド開始:{0}


		logger.infoCode("I0002", "create"); // I0002=メソッド終了:{0}
		return REDIRECT_HOME;
	}

}
