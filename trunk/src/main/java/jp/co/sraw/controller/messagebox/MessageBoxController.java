/*
* ファイル名：IndexController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.messagebox;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

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
import jp.co.sraw.dto.MessageBoxDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.MessageBoxServiceImpl;
import jp.co.sraw.util.DateUtil;

/**
 * <B>SupportControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/messagebox")
public class MessageBoxController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MessageBoxController.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@Autowired
	private MessageBoxServiceImpl messageBoxServiceImpl;

	private static final String REDIRECT_LIST = "redirect:/messagebox/";

	private static final String LIST_PAGE = "messagebox/list";
	private static final String LIST_PAGE_SEND = "messagebox/sendlist";

	private static final String EDIT_PAGE = "messagebox/edit";

	private static final String COPY_PAGE = "messagebox/copy";

	private static final String FORM_NAME = "form";

	List<MessageBoxDto> slist = new ArrayList<>();

	Boolean messageSendFlag = false;
	Boolean partyCodeFlag = false;
	Boolean roleFlag = false;

	/**
	 *
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String index(@ModelAttribute(FORM_NAME) final MessageBoxForm form, Model model) {

		logger.infoCode("I0001");

		List<MessageBoxDto> messageboxList = new ArrayList<>();

		messageboxList = messageBoxServiceImpl.findAllMessage(userInfo,messageSendFlag);
//		}

		model.addAttribute("messageboxList", messageboxList);

		if (logger.isDebugEnabled()) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		// dump
		modelDump(logger, model, "index");
		if (messageSendFlag) {
			return LIST_PAGE_SEND;
		} else {
			return LIST_PAGE;
		}

	}

	/**
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute(FORM_NAME) MessageBoxForm form, Model model, Locale locale) {
		logger.infoCode("I0001");

		if (form.getMakeUserKey() == null && form.getUpdDate() == null) {
			// DB更新が失敗した場合
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}

			return REDIRECT_LIST;
		}

		form.setSendDate(DateUtil.getSysdate());

		model.addAttribute(CommonConst.FORM_NAME, form);
		logger.infoCode("I0002", EDIT_PAGE); // I0002=メソッド終了:{0}
		return EDIT_PAGE;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	public String copy(@ModelAttribute(FORM_NAME) MessageBoxForm form, Model model, Locale locale) {
		logger.infoCode("I0001");

		if (form.getMakeUserKey() == null && form.getUpdDate() == null) {
			// DB更新が失敗した場合
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}

			return REDIRECT_LIST;
		}

		form.setSendDate(DateUtil.getSysdate());

		model.addAttribute(CommonConst.FORM_NAME, form);
		logger.infoCode("I0002", COPY_PAGE); // I0002=メソッド終了:{0}
		return COPY_PAGE;
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Model model, Locale locale) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		MessageBoxForm form = new MessageBoxForm();

		form.setSendDate(DateUtil.getSysdate());

		form.setPageMode(CommonConst.PAGE_MODE_ADD);
		model.addAttribute(CommonConst.FORM_NAME, form);

		logger.infoCode("I0002", EDIT_PAGE); // I0002=メソッド終了:{0}
		return EDIT_PAGE;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @Validated @ModelAttribute(FORM_NAME) final MessageBoxForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
//		if (bindingResult.hasErrors()) {
//			if (logger.isDebugEnabled()) {
//				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
//			}
//			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。
//
//			model.addAttribute(CommonConst.FORM_NAME, form);
//
//			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
//
//			return EDIT_PAGE;
//		}

		if (messageBoxServiceImpl.update(userInfo, form)) {
			// DB更新が成功した場合
			logger.infoCode("I1004", ""); // I1004=更新しました。{0}

			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return REDIRECT_LIST;
		}

		logger.infoCode("I0002", LIST_PAGE); // I0002=メソッド終了:{0}

		// DB更新が失敗した場合
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
		logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		return REDIRECT_LIST;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(FORM_NAME) final MessageBoxForm form, Model model,
			RedirectAttributes attributes) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "messageKey=" + form.getMessageKey();

//		if (messageBoxServiceImpl.delete(userInfo, form)) {
		if (messageBoxServiceImpl.delete(userInfo, form)) {
			// DB更新が成功した場合
			logger.infoCode("I1003", key); // I1003=削除しました。{0}

			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.delete.success"); // message.data.delete.success=データを削除しました。

			attributes.addFlashAttribute(CommonConst.FORM_NAME, form);
			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return REDIRECT_LIST;
		} else {

			attributes.addFlashAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.remove"); // error.data.message.db.remove=削除が失敗しました。

			model.addAttribute(CommonConst.FORM_NAME, form);
			logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
			return REDIRECT_LIST;
		}
	}

	/**
	 * ダイレクトアクセス対策
	 *
	 * @return
	 */
	@RequestMapping(value = {"/edit", "/copy", "/create", "/update", "/delete"}, method = RequestMethod.GET)
	public String redirect() {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。
		return CommonConst.REDIRECT_INDEX;
	}

}
