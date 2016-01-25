/*
* ファイル名：SupportMgmtController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.support;

import java.util.ArrayList;
import java.util.List;
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
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.dto.SpSupportDto;
import jp.co.sraw.entity.SpSupportTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.SupportServiceImpl;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>SupportMgmtControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping(CommonConst.PATH_MGMT+"/support")
public class SupportMgmtController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(SupportMgmtController.class);

	@Autowired
	private SupportServiceImpl supportServiceImpl;

	private static final String REDIRECT_LIST = "redirect:list";
	private static final String LIST_PAGE = "support/mgmt/list";
	private static final String EDIT_PAGE = "support/mgmt/edit";

	// 連携機関支援
	private static final String KBN = "1"; // 定数区分[0005]＝1:支援制度。2:機器設備利用
	// 支援制度区分
	private static final String CODE_KBN = "0005";
	//
	private static final String CODE_SYBCODE = "0021";

	private static final String PUBLIC_FLAG ="1"; // 固定値: 公開

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@ModelAttribute(CommonConst.FORM_NAME)
	public SupportForm setupForm() {
		SupportForm form = new SupportForm();
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
	public String list(@ModelAttribute(CommonConst.FORM_NAME) SupportForm form, Model model, Locale locale) {

		logger.infoCode("I0001");

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		List<MsCodeDto> sybList = DbUtil.getJosuListAll(CODE_SYBCODE, locale);

		List<SpSupportDto> renkeiKikanPresentList = new ArrayList<>();
		List<SpSupportDto> renkeiKikanPastList = new ArrayList<>();

		form.setSearchSpkikiKbn(KBN);
		form.setSearchPublicFlag(null);

		if (userInfo != null) {

			if (userInfo.isMgmt1()) {
				// 運営協議会事務局の場合
				form.setSearchPartyCode(null);

				// 支援制度情報（終了前）
				form.setSearchDateType(SupportServiceImpl.SEARCH_DATE_TYPE_PRESENT);
				renkeiKikanPresentList = supportServiceImpl.findAllLikeKeyWords(form, locale);

				// 支援制度情報（過去情報）
				form.setSearchDateType(SupportServiceImpl.SEARCH_DATE_TYPE_PAST);
				renkeiKikanPastList = supportServiceImpl.findAllLikeKeyWords(form, locale);

			} else if (userInfo.isMgmt2() || userInfo.isMgmt3() || userInfo.isMgmt4()) {
				// 連携大学の場合
				form.setSearchPartyCode(userInfo.getTargetPartyCode());

				// 支援制度情報（終了前）
				form.setSearchDateType(SupportServiceImpl.SEARCH_DATE_TYPE_PRESENT);
				renkeiKikanPresentList = supportServiceImpl.findAllLikeKeyWords(form, locale);

				// 支援制度情報（過去情報）
				form.setSearchDateType(SupportServiceImpl.SEARCH_DATE_TYPE_PAST);
				renkeiKikanPastList = supportServiceImpl.findAllLikeKeyWords(form, locale);

			}
		}

		model.addAttribute("sybList", sybList);
		model.addAttribute("renkeiKikanPresentList", renkeiKikanPresentList);
		model.addAttribute("renkeiKikanPastList", renkeiKikanPastList);

		form.setSearchDateType(null);  // 初期化
		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

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
	@RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
	public String edit(@ModelAttribute(CommonConst.FORM_NAME) SupportForm form, Model model, Locale locale) {
		logger.infoCode("I0001");


		List<MsCodeDto> sybList = DbUtil.getJosuListAll(CODE_SYBCODE, locale);
		model.addAttribute("sybList", sybList);

		///////////////////////////////////////////////////////////////////////////////////
		// 編集、コピーの場合
		///////////////////////////////////////////////////////////////////////////////////
		if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode()) && StringUtil.isNotNull(form.getSupportKey())) {

			SpSupportTbl spSupportTbl = supportServiceImpl.findOne(form.getSupportKey());

			if (spSupportTbl == null) {
				model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.empty.data"); // error.data.message.empty.data=データがありません。
			} else {
				//form = (SupportForm) ObjectUtil.getObjectCopyValue(form, spSupportTbl);
				form.setSupportKey(spSupportTbl.getSupportKey());
				form.setPartyCode(spSupportTbl.getPartyCode());
				form.setPublicFlag(spSupportTbl.getPublicFlag());
				form.setSupportContent(spSupportTbl.getSupportContent());
				form.setSupportStartDate(DateUtil.getDate(spSupportTbl.getSupportStartDate(), CommonConst.DEFAULT_YYYYMMDD));
				form.setSupportEndDate(DateUtil.getDate(spSupportTbl.getSupportEndDate(), CommonConst.DEFAULT_YYYYMMDD));
				form.setSupportHirakuKbn(spSupportTbl.getSupportHirakuKbn());
				form.setSupportInsDate(spSupportTbl.getSupportInsDate());
				form.setSupportKeyword(spSupportTbl.getSupportKeyword());
				form.setSupportSpkikiKbn(spSupportTbl.getSupportSpkikiKbn());
				form.setSupportSybCode(spSupportTbl.getSupportSybCode());
				form.setSupportTitle(spSupportTbl.getSupportTitle());
				form.setUrl(spSupportTbl.getUrl());
				form.setUpdDate(spSupportTbl.getUpdDate());
				form.setUpdUserKey(spSupportTbl.getUpdUserKey());
			}

			// 編集の場合
			form.setSupportSpkikiKbn(KBN);
			form.setPageMode(CommonConst.PAGE_MODE_EDIT);
			form.setPageActionUrl(CommonConst.ACTION_URL_UPDATE); //update

			model.addAttribute(CommonConst.FORM_NAME, form);
			logger.infoCode("I0002", "edit"); // I0002=メソッド終了:{0}
			return EDIT_PAGE;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// 新規追加の場合
		///////////////////////////////////////////////////////////////////////////////////
		if (CommonConst.PAGE_MODE_ADD.equals(form.getPageMode())) {

			String searchKeyword = form.getSearchKeyword();
			String searchSybCode = form.getSearchSybCode();
			String searchDateType = form.getSearchDateType();
			String searchPartyCode = form.getSearchPartyCode();
			String searchSpkikiKbn = form.getSearchSpkikiKbn();
			String searchPublicFlag = form.getSearchPublicFlag();

			SupportForm createForm = setupForm();

			// 検索条件
			createForm.setSearchKeyword(searchKeyword);
			createForm.setSearchSybCode(searchSybCode);
			createForm.setSearchDateType(searchDateType);
			createForm.setSearchPartyCode(searchPartyCode);
			createForm.setSearchSpkikiKbn(searchSpkikiKbn);
			createForm.setSearchPublicFlag(searchPublicFlag);

			// 初期データ
			createForm.setSupportSpkikiKbn(KBN);
			createForm.setPublicFlag(PUBLIC_FLAG);
			createForm.setSupportStartDate(DateUtil.getSysdate(CommonConst.DEFAULT_YYYYMMDD));
			createForm.setSupportEndDate(DateUtil.getSysdate(CommonConst.DEFAULT_YYYYMMDD));

			createForm.setPageMode(CommonConst.PAGE_MODE_ADD);
			createForm.setPageActionUrl(CommonConst.ACTION_URL_CREATE); //create

			model.addAttribute(CommonConst.FORM_NAME, createForm);

			logger.infoCode("I0002", "edit"); // I0002=メソッド終了:{0}
			return EDIT_PAGE;

		}

		// 項目が選択されていない場合
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.not.select"); // error.data.message.not.select=項目が選択されていません。
		logger.errorCode("E0014", "edit"); // E0014=メソッド異常終了:{0}
		return REDIRECT_LIST;
	}

	/* ************************************************************************* */
	/* DB更新                                                                    */
	/* ************************************************************************* */

	/**
	 * 新規データ追加
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute(CommonConst.FORM_NAME) final SupportForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "supportKey="+ form.getSupportKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		List<MsCodeDto> sybList = DbUtil.getJosuListAll(CODE_SYBCODE, locale);
		model.addAttribute("sybList", sybList);

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
		if (supportServiceImpl.update(form, userInfo)) {
			///////////////////////////////////////////////////////////////////////////////////
			// 更新の場合
			if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
				// DB更新が成功した場合
				logger.infoCode("I1004", key); // I1004=更新しました。{0}
				super.operationHistory(CommonConst.OP_FUNC_SUPPORT_SUPPORT, CommonConst.OP_ACTION_INSERT); // 操作ログ保存

				attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。
			}

			// 新規追加の場合
			if (CommonConst.PAGE_MODE_ADD.equals(form.getPageMode())) {
				// DB更新が成功した場合
				logger.infoCode("I1005", key); // I1005=新規作成しました。{0}
				super.operationHistory(CommonConst.OP_FUNC_SUPPORT_SUPPORT, CommonConst.OP_ACTION_UPDATE); // 操作ログ保存

				attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.create.success"); // message.data.create.success=データを新規追加しました。
			}

			// 検索条件
			SupportForm searchForm = setupForm();
			searchForm.setSearchKeyword(form.getSearchKeyword());
			searchForm.setSearchSybCode(form.getSearchSybCode());
			attributes.addFlashAttribute(CommonConst.FORM_NAME, searchForm);

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
	 * データ更新
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute(CommonConst.FORM_NAME) final SupportForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		return create(form, bindingResult, model, attributes, locale);
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
	public String delete(@ModelAttribute(CommonConst.FORM_NAME) final SupportForm form, Model model,
			RedirectAttributes attributes) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "supportKey=" + form.getSupportKey();
		logger.infoCode("I1008", key); // I1008=選択したデータ。key={0}

		if (supportServiceImpl.delete(form)) {
			// DB更新が成功した場合
			logger.infoCode("I1003", key); // I1003=削除しました。{0}
			super.operationHistory(CommonConst.OP_FUNC_SUPPORT_SUPPORT, CommonConst.OP_ACTION_DELETE); // 操作ログ保存

			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.delete.success"); // message.data.delete.success=データを削除しました。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
		} else {
			// DB更新が失敗した場合
			logger.errorCode("E1009", key); // E1009=削除に失敗しました。{0}

			attributes.addFlashAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.remove"); // error.data.message.db.remove=削除が失敗しました。

			logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		}

		// 検索条件
		SupportForm searchForm = setupForm();
		searchForm.setSearchKeyword(form.getSearchKeyword());
		searchForm.setSearchSybCode(form.getSearchSybCode());
		attributes.addFlashAttribute(CommonConst.FORM_NAME, searchForm);

		return REDIRECT_LIST;
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
