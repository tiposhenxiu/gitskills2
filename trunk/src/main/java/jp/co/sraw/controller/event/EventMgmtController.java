/*
* ファイル名：IndexController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.event;

import java.util.ArrayList;
import java.util.HashMap;
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
import jp.co.sraw.dto.EvEventViewDto;
import jp.co.sraw.entity.EvEventPublicTbl;
import jp.co.sraw.entity.EvEventTbl;
import jp.co.sraw.entity.MsPartyTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.EventServiceImpl;
import jp.co.sraw.service.MsPartyServiceImpl;
import jp.co.sraw.service.ViewServiceImpl;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>EventMgmtControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping(CommonConst.PATH_MGMT + "/event")
public class EventMgmtController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(EventMgmtController.class);

	@Autowired
	private EventServiceImpl eventServiceImpl;
	@Autowired
	private MsPartyServiceImpl msPartyServiceImpl;

	private static final String REDIRECT_LIST = "redirect:list";
	private static final String LIST_PAGE = "event/mgmt/list";
	private static final String EDIT_PAGE = "event/mgmt/edit";

	// 連携機関支援
	private static final String KBN = "1"; // 定数区分[0005]＝1:イベント。2:機器設備利用

	private static final String PUBLIC_FLAG = "1";

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@ModelAttribute(CommonConst.FORM_NAME)
	public EventForm setupForm() {
		EventForm form = new EventForm();
		return form;
	}

	Boolean publicFlag = false;
	Boolean partyCodeFlag = false;
	Boolean roleFlag = false;

	/**
	 * 一覧画面表示
	 *
	 * @param form
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String list(@ModelAttribute(CommonConst.FORM_NAME) EventForm form, Model model, Locale locale) {

		logger.infoCode("I0001");

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		List<EvEventViewDto> eventPresentList = new ArrayList<>();
		List<EvEventViewDto> eventPastList = new ArrayList<>();

		// form.setPublicKbn(publicKbn);
		form.setPublicFlag(null);

		// イベント情報（限りなく）
		form.setPageMode("current");
		eventPresentList = eventServiceImpl.findAllEventViewDto(userInfo, ViewServiceImpl.SEARCH_DATE_TYPE_CURRENT,
				locale);

		// イベント情報（過去情報）
		form.setPageMode("past");
		eventPastList = eventServiceImpl.findAllEventViewDto(userInfo, ViewServiceImpl.SEARCH_DATE_TYPE_PAST, locale);

		model.addAttribute("eventPresentList", eventPresentList);
		model.addAttribute("eventPastList", eventPastList);

		// dump
		modelDump(logger, model, "index");

		model.addAttribute(CommonConst.FORM_NAME, form);
		logger.infoCode("I0002", EDIT_PAGE); // I0002=メソッド終了:{0}
		return LIST_PAGE;
	}

	/**
	 * 新規作成、編集画面表示(Edit)
	 *
	 * @param form
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
	public String edit(@ModelAttribute(CommonConst.FORM_NAME) EventForm form, Model model, Locale locale) {
		logger.infoCode("I0001");

		// 公開範囲(初期は全部非公開)
		String[] partyKbn = new String[2];
		// 組織区分 ＝’5’、’6’
		partyKbn[0] = "5";
		partyKbn[1] = "6";
		List<MsPartyTbl> listParty = msPartyServiceImpl.findAllByPartyKbn(partyKbn);
		model.addAttribute("listParty", listParty);

		Map<String, String> publicKbnMap = new HashMap<String, String>();

		List<EvEventPublicTbl> publicList = new ArrayList<>();

		if (userInfo.hasMgmt1()) {
			form.setPublicDisabled("disabled");
		}

		///////////////////////////////////////////////////////////////////////////////////
		// 編集、コピーの場合
		///////////////////////////////////////////////////////////////////////////////////
		if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode()) && StringUtil.isNotNull(form.getEventKey())) {

			EvEventTbl eventTbl = eventServiceImpl.getOne(form.getEventKey());

			if (eventTbl == null) {
				model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.empty.data"); // error.data.message.empty.data=データがありません。
			} else {
				form.setEventStartDate(DateUtil.getDate(eventTbl.getEventStartDate(), CommonConst.DEFAULT_YYYYMMDD));
				form.setEventSendDate(DateUtil.getDate(eventTbl.getEventSendDate(), CommonConst.DEFAULT_YYYYMMDD));
				form.setUpdDate(eventTbl.getUpdDate());
				form.setUpdUserKey(eventTbl.getUpdUserKey());

				//
				publicList = eventTbl.getEvEventPublicTbls();

				Map<String, EvEventPublicTbl> publicMap = new HashMap<>();
				for (int i = 0; i < publicList.size(); i++) {
					EvEventPublicTbl tbl = publicList.get(i);
					publicMap.put(tbl.getPartyCode(), tbl);
					publicKbnMap.put(tbl.getPublicKbn(), tbl.getPublicKbn());
				}

				List<MsPartyTbl> noPublicPartyList = new ArrayList<>();
				List<MsPartyTbl> publicPartyList = new ArrayList<>();
				for (int i = 0; i < listParty.size(); i++) {
					MsPartyTbl tbl = listParty.get(i);
					if (!publicMap.containsKey(tbl.getPartyCode())) {
						noPublicPartyList.add(tbl);
					} else {
						publicPartyList.add(tbl);
					}
				}

				// form.setPublicItemArray();
				model.addAttribute("publicKbnMap", publicKbnMap);

				model.addAttribute("publicList", publicPartyList);
				model.addAttribute("listParty", noPublicPartyList);
			}

			// 編集の場合
			// form.setSupportSpkikiKbn(KBN);
			form.setPageMode(CommonConst.PAGE_MODE_EDIT);
			form.setPageActionUrl(CommonConst.ACTION_URL_UPDATE); // update

			model.addAttribute(CommonConst.FORM_NAME, form);
			logger.infoCode("I0002", "edit"); // I0002=メソッド終了:{0}
			return EDIT_PAGE;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// 新規追加の場合
		///////////////////////////////////////////////////////////////////////////////////
		if (CommonConst.PAGE_MODE_ADD.equals(form.getPageMode())) {

			form = setupForm();

			form.setPublicFlag(PUBLIC_FLAG);
			form.setEventStartDate(DateUtil.getSysdate(CommonConst.DEFAULT_YYYYMMDD));
			form.setEventSendDate(DateUtil.getSysdate(CommonConst.DEFAULT_YYYYMMDD));

			publicKbnMap.put("1", "1");
			publicKbnMap.put("2", "2");
			model.addAttribute("publicKbnMap", publicKbnMap);
			model.addAttribute("publicList", publicList);

			form.setPageMode(CommonConst.PAGE_MODE_ADD);
			form.setPageActionUrl(CommonConst.ACTION_URL_CREATE); // create

			model.addAttribute(CommonConst.FORM_NAME, form);

			logger.infoCode("I0002", "edit"); // I0002=メソッド終了:{0}
			return EDIT_PAGE;

		}

		// 項目が選択されていない場合
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.not.select"); // error.data.message.not.select=項目が選択されていません。
		logger.errorCode("E0014", "edit"); // E0014=メソッド異常終了:{0}
		return REDIRECT_LIST;
	}

	/*
	 * *************************************************************************
	 */
	/* DB更新 */
	/*
	 * *************************************************************************
	 */

	/**
	 * 新規データ追加、データ更新
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = { "/update", "/create" }, method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute(CommonConst.FORM_NAME) final EventForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "eventKey=" + form.getEventKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		if (userInfo.hasMgmt1()) {
			form.setPublicDisabled("disabled");
		}

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debugCode("W1010", bindingResult.getFieldError()); // W1010=Validationチェックエラーがありました。
			}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return EDIT_PAGE;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// DB登録
		///////////////////////////////////////////////////////////////////////////////////
		if (eventServiceImpl.update(form, userInfo)) {
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

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return REDIRECT_LIST;
		}

		// DB更新が失敗した場合
		logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。

		logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		return EDIT_PAGE;
	}

	/**
	 * データ削除
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(CommonConst.FORM_NAME) final EventForm form, Model model,
			RedirectAttributes attributes) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "eventKey=" + form.getEventKey();
		logger.infoCode("I1008", key); // I1008=選択したデータ。key={0}

		if (eventServiceImpl.delete(userInfo, form)) {
			// DB更新が成功した場合
			logger.infoCode("I1003", key); // I1003=削除しました。{0}

			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.delete.success"); // message.data.delete.success=データを削除しました。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return REDIRECT_LIST;
		} else {
			// DB更新が失敗した場合
			logger.errorCode("E1009", key); // E1009=削除に失敗しました。{0}

			attributes.addFlashAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.remove"); // error.data.message.db.remove=削除が失敗しました。

			logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
			return REDIRECT_LIST;
		}
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
