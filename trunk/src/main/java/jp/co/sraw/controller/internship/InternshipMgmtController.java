/*
* ファイル名：IndexController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.internship;

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
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.ItInternPublicTbl;
import jp.co.sraw.entity.ItInternRelSubjectTbl;
import jp.co.sraw.entity.ItInternTbl;
import jp.co.sraw.entity.ItInternView;
import jp.co.sraw.entity.MsPartyTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.ItInternUploadTblRepository;
import jp.co.sraw.service.InternshipServiceImpl;
import jp.co.sraw.service.MsPartyServiceImpl;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>InternshipMgmtControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/mgmt/internship")
public class InternshipMgmtController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(InternshipMgmtController.class);

	@Autowired
	private InternshipServiceImpl internshipServiceImpl;
	@Autowired
	private MsPartyServiceImpl msPartyServiceImpl;
	@Autowired
	private ItInternUploadTblRepository itInternUploadTblRepository;

	private static final String REDIRECT_LIST = "redirect:list";
	private static final String LIST_PAGE = "internship/mgmt/list";
	private static final String EDIT_PAGE = "internship/mgmt/edit";
	// インターンシップ区分
	private static final String KBN_INTERN = "0002";
	// 公開フラグ区分(管理向け)
	private static final String KBN_PUBLICFLAG = "0019";

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@ModelAttribute(CommonConst.FORM_NAME)
	public InternshipForm setupForm() {
		InternshipForm form = new InternshipForm();
		return form;
	}

	/**
	 *
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String list(Model model, Locale locale) {

		logger.infoCode("I0001", "list");

		List<ItInternView> currentList = new ArrayList<>();
		List<ItInternView> pastList = new ArrayList<>();

		currentList = internshipServiceImpl.findAllInternshipView(InternshipServiceImpl.SEARCH_VIEW_TYPE_2,
																  userInfo.getLoginPartyCode(),
																  userInfo.getTargetRole().getAuthority(),
																  null,
																  InternshipServiceImpl.SEARCH_DATE_TYPE_CURRENT);

		String type = "";
		if (userInfo.isMgmt1()) {
			// 事務局
			type = InternshipServiceImpl.SEARCH_VIEW_TYPE_2;
		} else if (userInfo.isMgmt2() || userInfo.isMgmt3()) {
			// 共同実施機関窓口、連携大学
			type = InternshipServiceImpl.SEARCH_VIEW_TYPE_3;
		} else if (userInfo.isMgmt4()) {
			// 上記以外
			type = InternshipServiceImpl.SEARCH_VIEW_TYPE_4;
		}

		pastList = internshipServiceImpl.findAllInternshipView(type,
															   userInfo.getLoginPartyCode(),
															   userInfo.getTargetRole().getAuthority(),
															   null,
															   InternshipServiceImpl.SEARCH_DATE_TYPE_PAST);

		if (logger.isDebugEnabled()) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		List<MsCodeDto> kbnList = DbUtil.getJosuList(KBN_INTERN, locale);

		Map<String, List<ItInternView>> mapCurrentList = new HashMap<String, List<ItInternView>>();
		Map<String, List<ItInternView>> mapPastList = new HashMap<String, List<ItInternView>>();

		// 区分から一覧を生成
		for (int i = 0; i < kbnList.size(); i++) {
			MsCodeDto dto = kbnList.get(i);
			List<ItInternView> tmpList = new ArrayList<ItInternView>();
			for (int j = 0; j < currentList.size(); j++) {
				// 区分毎に振り分け
				if (currentList.get(j).getInternshipKbn().contains(dto.getCode())) {
					tmpList.add(currentList.get(j));
				}
			}
			mapCurrentList.put(dto.getCode(), tmpList);

			tmpList = new ArrayList<ItInternView>();
			for (int j = 0; j < pastList.size(); j++) {
				// 区分毎に振り分け
				if (pastList.get(j).getInternshipKbn().contains(dto.getCode())) {
					tmpList.add(pastList.get(j));
				}
			}
			mapPastList.put(dto.getCode(), tmpList);
		}

		model.addAttribute("kbnList", kbnList);
		model.addAttribute("mapCurrentList", mapCurrentList);
		model.addAttribute("mapPastList", mapPastList);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_PAGE;
	}

	@RequestMapping(value = {"/edit", "/copy"}, method = RequestMethod.POST)
	public String edit(@Validated @ModelAttribute(FORM_NAME) final InternshipForm form, Model model, Locale locale) {
		logger.infoCode("I0001");

		Map<String, String> publicKbnMap = new HashMap<String, String>();

		List<ItInternPublicTbl> publicList = new ArrayList<>();
		// 公開範囲(初期は全部非公開)
		String[] partyKbn = new String[2];
		// 組織区分 ＝’5’、’6’
		partyKbn[0] = "5";
		partyKbn[1] = "6";
		List<MsPartyTbl> listParty = msPartyServiceImpl.findAllByPartyKbn(partyKbn);

		///////////////////////////////////////////////////////////////////////////////////
		// 編集、コピーの場合
		///////////////////////////////////////////////////////////////////////////////////
		if ((CommonConst.PAGE_MODE_EDIT.equals(form.getPageMode()) || CommonConst.PAGE_MODE_COPY.equals(form.getPageMode()))
				&& StringUtil.isNotNull(form.getInternshipKey())) {
			// 公開設定
			if (userInfo.isMgmt1()) {
				model.addAttribute("listPublicFlag", DbUtil.getJosuList(KBN_PUBLICFLAG, locale));
			}
			// 種別
			model.addAttribute("listClassification", DbUtil.getJosuList(KBN_INTERN, locale));

			ItInternTbl itInternTbl = internshipServiceImpl.findOneIntern(form);
			if (itInternTbl == null) {
				model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.empty.data"); // error.data.message.empty.data=データがありません。
			} else {
				// インターン公開範囲取得
				List<ItInternPublicTbl> listItInternPublic = internshipServiceImpl.findAllInternPublic(form.getInternshipKey());

				form.setInternshipKbn(itInternTbl.getInternshipKbn());
				form.setInternshipStartDate(itInternTbl.getInternshipStartDate());
				form.setInternshipEndDate(itInternTbl.getInternshipEndDate());
				form.setInternshipTitle(itInternTbl.getInternshipTitle());
				form.setInternshipSendDate(itInternTbl.getInternshipSendDate());
				form.setInternshipRange(itInternTbl.getInternshipRange());
				form.setInternshipPartyName(itInternTbl.getInternshipPartyName());
				form.setInternshipTelno(itInternTbl.getInternshipTelno());
				form.setPublicFlag(itInternTbl.getPublicFlag());
				form.setEventUnit(itInternTbl.getEventUnit());
				form.setInternshipMemo(itInternTbl.getInternshipMemo());
				form.setUpdDate(itInternTbl.getUpdDate());
				form.setUpdUserKey(itInternTbl.getUpdUserKey());

				publicList = itInternTbl.getItInternPublicTbls();

				Map<String, ItInternPublicTbl> publicMap = new HashMap<>();
				for (int i = 0; i < publicList.size(); i++) {
					ItInternPublicTbl tbl = publicList.get(i);
					publicMap.put(tbl.getPartyCode(), tbl);
					publicKbnMap.put(tbl.getPublicKbn(), tbl.getPublicKbn());
				}

				List<MsPartyTbl> listPartyClose = new ArrayList<>();
				List<MsPartyTbl> listPartyOpen = new ArrayList<>();
				for (int i = 0; i < listParty.size(); i++) {
					MsPartyTbl tbl = listParty.get(i);
					if (!publicMap.containsKey(tbl.getPartyCode())) {
						listPartyClose.add(tbl);
					}else{
						listPartyOpen.add(tbl);
					}
				}

				model.addAttribute("publicKbnMap", publicKbnMap);
				model.addAttribute("listPartyOpen", listPartyOpen);
				model.addAttribute("listPartyClose", listPartyClose);

			}

			form.setPageMode(CommonConst.PAGE_MODE_EDIT);
			form.setPageActionUrl(CommonConst.ACTION_URL_UPDATE); //update

			model.addAttribute(CommonConst.FORM_NAME, form);
			logger.infoCode("I0002", CommonConst.PAGE_MODE_EDIT); // I0002=メソッド終了:{0}
			return EDIT_PAGE;
		}

		///////////////////////////////////////////////////////////////////////////////////
		// 新規追加の場合
		///////////////////////////////////////////////////////////////////////////////////
		if (CommonConst.PAGE_MODE_ADD.equals(form.getPageMode())) {
			// 公開設定
			if (userInfo.isMgmt1()) {
				model.addAttribute("listPublicFlag", DbUtil.getJosuList(KBN_PUBLICFLAG, locale));
			}
			// 種別
			model.addAttribute("listClassification", DbUtil.getJosuList(KBN_INTERN, locale));
			model.addAttribute("listPartyClose", listParty);
			// 能力養成紐付け情報取得
			List<ItInternRelSubjectTbl> listInternRelSubject = internshipServiceImpl.findAllItInternRelSubject(form.getInternshipKey());

			InternshipForm createForm = setupForm();
			publicKbnMap.put("1", "1");
			publicKbnMap.put("2", "2");
			model.addAttribute("publicKbnMap", publicKbnMap);
			createForm.setPageMode(CommonConst.PAGE_MODE_ADD);
			createForm.setPageActionUrl(CommonConst.ACTION_URL_CREATE); //create
			model.addAttribute(CommonConst.FORM_NAME, createForm);
			logger.infoCode("I0002", CommonConst.ACTION_URL_CREATE); // I0002=メソッド終了:{0}
			return EDIT_PAGE;
		}

		// 項目が選択されていない場合
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.not.select"); // error.data.message.not.select=項目が選択されていません。
		logger.errorCode("E0014", "edit"); // E0014=メソッド異常終了:{0}
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute(CommonConst.FORM_NAME) final InternshipForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001");

		String key = "internshipKey="+ form.getInternshipKey();
		logger.infoCode("I1009", key); // I1009=入力したデータ。key={0}

		// 公開設定
		model.addAttribute("listPublicFlag", DbUtil.getJosuList(KBN_PUBLICFLAG, locale));
		// 種別
		model.addAttribute("listClassification", DbUtil.getJosuList(KBN_INTERN, locale));
		// 公開範囲(初期は全部非公開)
		String[] partyKbn = new String[2];
		// 組織区分	＝’5’、’6’
		partyKbn[0] = "5";
		partyKbn[1] = "6";
		List<MsPartyTbl> listPartyClose = msPartyServiceImpl.findAllByPartyKbn(partyKbn);
		model.addAttribute("listPartyClose", listPartyClose);
		// 能力養成紐付け情報取得
		List<ItInternRelSubjectTbl> listInternRelSubject = internshipServiceImpl.findAllItInternRelSubject(form.getInternshipKey());

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
		if (internshipServiceImpl.update(form, userInfo)) {
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

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute(FORM_NAME) final InternshipForm form, Model model) {
		return REDIRECT_LIST;
	}

	private static final String FORM_NAME = "form";

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteInternByKey(@Validated @ModelAttribute(FORM_NAME) final InternshipForm form, Model model, RedirectAttributes attributes) {

		logger.infoCode("I0001");

		String key = "internshipKey="+ form.getInternshipKey() +", updDate="+ form.getUpdDate();

		//インターンシップ、インターンシップ添付ファイル、インターンシップ公開範囲からデータをDELETEする。
		if (internshipServiceImpl.deleteIntern(userInfo, form)) {
			// DB更新が成功した場合
			logger.infoCode("I1003", key); // I1003=削除しました。{0}
			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.delete.success"); // message.data.delete.success=データを削除しました。
			logger.infoCode("I0002", form.getPageActionUrl()); // I0002=メソッド終了:{0}
		} else {
			// DB更新が失敗した場合
			logger.errorCode("E1009", key); // E1009=削除に失敗しました。{0}
			attributes.addFlashAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.remove"); // error.data.message.db.remove=削除が失敗しました。
			logger.errorCode("E0014", form.getPageActionUrl()); // E0014=メソッド異常終了:{0}
		}

		logger.infoCode("I0002", LIST_PAGE); // I0002=メソッド終了:{0}

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
