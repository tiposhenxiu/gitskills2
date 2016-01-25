/*
* ファイル名：MsCodeController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.entity.MsCodeTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.MsCodeServiceImpl;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.MessageUtil;
import jp.co.sraw.util.StringUtil;


/**
* <B>MsCodeControllerクラス</B>
* <P>
* Controllerのメソッドを提供する
*/
@Controller
@RequestMapping(CommonConst.PATH_ADMIN+"/mscode")
public class MsCodeController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MsCodeController.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@Autowired
	private MsCodeServiceImpl msCodeServiceImpl;

	private static final String REDIRECT_LIST = "redirect:list";
	private static final String REDIRECT_EDIT = "redirect:edit";
	private static final String LIST_PAGE = "admin/mscode/list";
	private static final String EDIT_PAGE = "admin/mscode/edit";

	private static final String MAIN_KBN = "0000";
	private static final String USE_KBN = "0023";

	private static final Long CODE_SIZE_MIN = Long.valueOf(1); //コード桁数 ※FormのValidationも直すこと！
	private static final Long CODE_SIZE_MAX = Long.valueOf(6); //コード桁数 ※FormのValidationも直すこと！
	private static final Long NUM_SIZE_MIN = Long.valueOf(Long.MIN_VALUE); //コード桁数 ※FormのValidationも直すこと！
	private static final Long NUM_SIZE_MAX = Long.valueOf(Long.MAX_VALUE); //コード桁数 ※FormのValidationも直すこと！

	private static final String SELECTED_JOSU_KBN = "selectedJosuKbn";
	private static final String SIZE_MAP = "sizMap";


	@ModelAttribute(CommonConst.FORM_NAME)
	public MsCodeForm setupForm() {
		MsCodeForm form = new MsCodeForm();
		return form;
	}

	/**
	 * 入力最大値設定を取得
	 *
	 * @param josuKbn
	 * @return
	 */
	private Map<String, Long> getSizeMap(String josuKbn) {
		Map<String, Long> sizeMap = new HashMap<String, Long>();
		// 最大値設定を取得
		MsCodeTbl m = msCodeServiceImpl.findOne(MAIN_KBN, josuKbn);
		// 親コード[0000]の場合
		if (MAIN_KBN.equals(josuKbn)) {
			sizeMap.put("codeMin", m.getSntaZksei1Num());
			sizeMap.put("codeMax", m.getSntaZksei1Num());
			sizeMap.put("numMin", CODE_SIZE_MIN);
			sizeMap.put("numMax", CODE_SIZE_MAX);
		} else {
			sizeMap.put("codeMin", m.getSntaZksei1Num());
			sizeMap.put("codeMax", m.getSntaZksei1Num());
			sizeMap.put("numMin", NUM_SIZE_MIN);
			sizeMap.put("numMax", NUM_SIZE_MAX);
		}
		return sizeMap;
	}

	/**
	 * 一覧画面表示
	 *
	 * @param selectedJosuKbn
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping({"","/", "/list"})
	public String list(@RequestParam(value=SELECTED_JOSU_KBN, required=false, defaultValue=MAIN_KBN) String selectedJosuKbn, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}


		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey="+ userInfo.getLoginUserKey());
			logger.debug("TargetUserKey="+ userInfo.getTargetUserKey());
			logger.debug("selectedJosuKbn="+ selectedJosuKbn);
		}

		// 定数区分一覧取得
		List<MsCodeTbl> kbnList = msCodeServiceImpl.findAllJosuKbn(MAIN_KBN);
		if (CollectionUtils.isEmpty(kbnList)) {
			model.addAttribute("emptyMessageKbn", "error.data.message.empty.data"); // error.data.message.empty.data=データがありません。
		}

		// 選択した定数区分の定数コード一覧を取得
		List<MsCodeTbl> codeList = msCodeServiceImpl.findAllJosuKbn(selectedJosuKbn);
		if (CollectionUtils.isEmpty(codeList)) {
			model.addAttribute(CommonConst.PAGE_INFO_MESSAGE, "error.data.message.empty.data"); // error.data.message.empty.data=データがありません。
		}

		// 定数区分一覧
		model.addAttribute(SELECTED_JOSU_KBN, selectedJosuKbn);
		model.addAttribute("kbnList", kbnList);
		// 定数コード一覧
		model.addAttribute("codeList", codeList);
		//model.addAttribute(FORM_NAME, this.setupForm());

		// 定数コード一覧Map
		model.addAttribute("useFlagMap", DbUtil.getJosuMap(USE_KBN, locale));

		// dump
		modelDump(logger, model, "list");


		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}
		return LIST_PAGE;

	}

	/**
	 * 新規作成、編集画面表示(Edit, Copy)
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/edit", "/copy"}, method = RequestMethod.POST)
	public String edit(@RequestParam String josuKbn, @RequestParam String josuCode, @RequestParam String pageMode, Model model, Locale locale) {
		logger.infoCode("I0001", "edit"); // I0001=メソッド開始:{0}

		String key = "josuKbn="+ josuKbn +", josuCode="+ josuCode;
		logger.infoCode("I1008", key); // I1008=選択したデータ。key={0}

		MsCodeForm form = setupForm();

		// 定数コード一覧List
		model.addAttribute("useFlagList", DbUtil.getJosuList(USE_KBN, locale));
		model.addAttribute(SELECTED_JOSU_KBN, josuKbn);

		// 最大値設定を取得
		model.addAttribute(SIZE_MAP, getSizeMap(josuKbn));


		///////////////////////////////////////////////////////////////////////////////////
		// 編集、コピーの場合
		///////////////////////////////////////////////////////////////////////////////////
		if ((CommonConst.PAGE_MODE_EDIT.equals(pageMode) || CommonConst.PAGE_MODE_COPY.equals(pageMode)) &&
				(StringUtil.isNotNull(josuKbn) && StringUtil.isNotNull(josuCode))) {

			// DB取得
			MsCodeTbl m = msCodeServiceImpl.findOne(josuKbn, josuCode);
			if (m == null) {
				model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.empty.data"); // error.data.message.empty.data=データがありません。
			} else {
				form.setJosuKbn(m.getId().getJosuKbn());
				form.setJosuCode(m.getId().getJosuCode());
				form.setJosuName(m.getJosuName());
				form.setJosuNameEn(m.getJosuNameEn());
				form.setJosuNameAbbr(m.getJosuNameAbbr());
				form.setJosuNameAbbrEn(m.getJosuNameAbbrEn());
				form.setSntaZksei1Txt(m.getSntaZksei1Txt());
				form.setSntaZksei2Txt(m.getSntaZksei2Txt());
				form.setSntaZksei3Txt(m.getSntaZksei3Txt());
				form.setSntaZksei1Num(m.getSntaZksei1Num());
				form.setSntaZksei2Num(m.getSntaZksei2Num());
				form.setSntaZksei3Num(m.getSntaZksei3Num());
				form.setCommentProperty(m.getCommentProperty());
				form.setUseFlag(m.getUseFlag());
				form.setHyojiSrt(m.getHyojiSrt());
				form.setUpdDate(m.getUpdDate());
				form.setUpdUserKey(m.getUpdUserKey());
			}
			// 編集の場合
			form.setPageActionUrl(CommonConst.ACTION_URL_UPDATE); //update
			if (CommonConst.PAGE_MODE_COPY.equals(pageMode)) {
				// コピーの場合
				form.setPageActionUrl(CommonConst.ACTION_URL_CREATE); //create
			}
			form.setPageMode(pageMode);
			model.addAttribute(CommonConst.FORM_NAME, form);
			logger.infoCode("I0002", "edit"); // I0002=メソッド終了:{0}
			return EDIT_PAGE;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// 新規追加の場合
		///////////////////////////////////////////////////////////////////////////////////
		if (CommonConst.PAGE_MODE_ADD.equals(pageMode) && StringUtil.isNotNull(josuKbn)) {

			// 新規Form設定
			form.setJosuKbn(josuKbn);
			form.setUseFlag(CommonConst.USE_FALG_ACTIVE); // 初期選択:有効=1
			//
			form.setPageActionUrl(CommonConst.ACTION_URL_CREATE); //create
			form.setPageMode(pageMode);
			model.addAttribute(CommonConst.FORM_NAME, form);
			//
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
	 * 新規データ追加、データ更新
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/create", "/update"}, method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute(CommonConst.FORM_NAME) final MsCodeForm form, BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001", form.getPageActionUrl()); // I0001=メソッド開始:{0}

		// 定数コード一覧List
		model.addAttribute("useFlagList", DbUtil.getJosuList(USE_KBN, locale));
		model.addAttribute(SELECTED_JOSU_KBN, form.getJosuKbn());
		attributes.addAttribute(SELECTED_JOSU_KBN, form.getJosuKbn());  // リダイレクト用
		// 最大値設定を取得
		model.addAttribute(SIZE_MAP, getSizeMap(form.getJosuKbn()));

		String key = "josuKbn="+ form.getJosuKbn() +", josuCode="+ form.getJosuCode();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}


		///////////////////////////////////////////////////////////////////////////////////
		// バリデーション
		///////////////////////////////////////////////////////////////////////////////////
		// 共通
		// 親コード[0000]の場合
		if (MAIN_KBN.equals(form.getJosuKbn())) {
			// 定数コードサイズ指定「その他数値属性１」の値チェック、数値が1から6の間
			if (form.getSntaZksei1Num() == null || (form.getSntaZksei1Num() != null && (CODE_SIZE_MIN.compareTo(form.getSntaZksei1Num()) > 0 || CODE_SIZE_MAX.compareTo(form.getSntaZksei1Num()) < 0))) {
				// エラーメッセージ設定
				bindingResult.rejectValue("sntaZksei1Num", "message.validate.range", new Object[]{MessageUtil.getCodes("sntaZksei1Num"), CODE_SIZE_MIN, CODE_SIZE_MAX}, null);
			}
		}

		// 新規追加、コピーの場合
		if (CommonConst.PAGE_MODE_ADD.equals(form.getPageMode()) || CommonConst.PAGE_MODE_COPY.equals(form.getPageMode())) {
			// 定数コードサイズチェック 親の「その他数値属性１」の値チェック
			MsCodeTbl m = msCodeServiceImpl.findOne(MAIN_KBN, form.getJosuKbn());
			if (StringUtil.isNotNull(form.getJosuCode()) && !m.getSntaZksei1Num().equals(Long.valueOf(form.getJosuCode().length()))) {
				// エラーメッセージ設定
				bindingResult.rejectValue("josuCode", "message.validate.equal.length", new Object[]{MessageUtil.getCodes("josuCode"), m.getSntaZksei1Num()}, null);
			}

			// データ存在チェック
			if (StringUtil.isNotNull(form.getJosuKbn()) && StringUtil.isNotNull(form.getJosuCode()) && msCodeServiceImpl.findOne(form.getJosuKbn(), form.getJosuCode()) != null) {
				// エラーメッセージ設定
				bindingResult.rejectValue("josuCode", "error.data.message.input.data", new Object[]{MessageUtil.getCodes("josuCode")}, null);
			}
		}
		///////////////////////////////////////////////////////////////////////////////////
		// 更新の場合
		if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
			// 無効の場合、親コード[0000]の場合は関連データに有効が存在しないかチェック
			if (CommonConst.USE_FALG_INACTIVE.equals(form.getUseFlag()) &&  MAIN_KBN.equals(form.getJosuKbn())
					&& !CollectionUtils.isEmpty(msCodeServiceImpl.findAllJosuKbnAndUseFlag(form.getJosuCode(), CommonConst.USE_FALG_ACTIVE))) {
				// エラーメッセージ設定
				bindingResult.rejectValue("useFlag", "error.data.message.subdata.exist.active", new Object[]{MessageUtil.getCodes("useFlag")}, null);
			}
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
		// 新規追加、コピーの場合
		if (CommonConst.PAGE_MODE_ADD.equals(form.getPageMode()) || CommonConst.PAGE_MODE_COPY.equals(form.getPageMode())) {
			if (msCodeServiceImpl.update(form, userInfo.getLoginUserKey())) {
				// DB更新が成功した場合
				logger.infoCode("I1005", key); // I1005=新規作成しました。{0}
				super.operationHistory(CommonConst.OP_FUNC_ADMIN_MSCODE, CommonConst.OP_ACTION_INSERT); // 操作ログ保存

				// 一覧画面に戻る
				attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.create.success"); // message.data.create.success=データを新規追加しました。

				logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
				return REDIRECT_LIST;
			}
		}

		///////////////////////////////////////////////////////////////////////////////////
		// 更新の場合
		if (CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode())) {
			if (msCodeServiceImpl.update(form, userInfo.getLoginUserKey())) {
				// DB更新が成功した場合
				logger.infoCode("I1004", key); // I1004=更新しました。{0}
				super.operationHistory(CommonConst.OP_FUNC_ADMIN_MSCODE, CommonConst.OP_ACTION_UPDATE); // 操作ログ保存

				attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。

				logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
				return REDIRECT_LIST;

			}
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
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(CommonConst.FORM_NAME) final MsCodeForm form, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001", form.getPageActionUrl()); // I0001=メソッド開始:{0}

		String key = "josuKbn="+ form.getJosuKbn() +", josuCode="+ form.getJosuCode();
		logger.infoCode("I1008", key); // I1008=選択したデータ。key={0}

		// 定数コード一覧List
		attributes.addAttribute(SELECTED_JOSU_KBN, form.getJosuKbn());

		// 無効の場合、削除可能
		if (CommonConst.USE_FALG_ACTIVE.equals(form.getUseFlag())) {
			attributes.addFlashAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.active"); // error.data.message.active=状態が有効なデータです。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return REDIRECT_LIST;
		}

		// 親コード[0000]の場合は関連データが存在しないかチェック
		if (MAIN_KBN.equals(form.getJosuKbn())
				&& !CollectionUtils.isEmpty(msCodeServiceImpl.findAllJosuKbn(form.getJosuCode()))) {
			attributes.addFlashAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.subdata.exist"); // error.data.message.subdata.exist=関連データが存在します。

			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
			return REDIRECT_LIST;
		}

		// 削除の場合
		if (CommonConst.PAGE_MODE_DELETE.equals(form.getPageMode()) && msCodeServiceImpl.detele(form, userInfo.getLoginUserKey())) {
			// DB更新が成功した場合
			logger.infoCode("I1003", key); // I1003=削除しました。{0}
			super.operationHistory(CommonConst.OP_FUNC_ADMIN_MSCODE, CommonConst.OP_ACTION_DELETE); // 操作ログ保存

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
	@RequestMapping(value = {"/edit", "/copy", "/create", "/update", "/delete"}, method = RequestMethod.GET)
	public String redirect() {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。
		return CommonConst.REDIRECT_INDEX;
	}

}
