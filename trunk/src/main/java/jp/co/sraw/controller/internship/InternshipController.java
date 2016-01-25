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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.dto.InternshipViewDto;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.ItInternRecruitTblPK;
import jp.co.sraw.entity.ItInternView;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.InternshipServiceImpl;
import jp.co.sraw.util.DbUtil;

/**
 * <B>InternshipControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/internship")
public class InternshipController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(InternshipController.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@Autowired
	private InternshipServiceImpl internshipServiceImpl;

	private static final String LIST_PAGE = "internship/list";

	private static final String REDIRECT_LIST = "redirect:/internship/";
	// インターンシップ区分
	private static final String KBN = "0002";

	private static final String PUBLIC_FLAG = "1"; // 固定値: 公開


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

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		List<MsCodeDto> kbnList = DbUtil.getJosuList(KBN, locale);

		List<ItInternView> resultList = new ArrayList<>();

		if (userInfo.isUser1() || userInfo.isUser2() || userInfo.isUser3() || userInfo.isUser4() || userInfo.isUser5()) {
			// 個人ユーザ
			resultList = internshipServiceImpl.findAllInternshipView(InternshipServiceImpl.SEARCH_VIEW_TYPE_1,
					userInfo.getTargetPartyCode(), null, PUBLIC_FLAG, InternshipServiceImpl.SEARCH_DATE_TYPE_CURRENT);
		} else if (userInfo.isMgmt1()) {
			// 事務局
			resultList = internshipServiceImpl.findAllInternshipView(InternshipServiceImpl.SEARCH_VIEW_TYPE_2, null,
					null, null, null);
		} else if (userInfo.isMgmt2() || userInfo.isMgmt3()) {
			// 共同実施機関窓口、連携大学
			resultList = internshipServiceImpl.findAllInternshipView(InternshipServiceImpl.SEARCH_VIEW_TYPE_3,
					userInfo.getTargetPartyCode(), null, PUBLIC_FLAG, InternshipServiceImpl.SEARCH_DATE_TYPE_CURRENT);
		} else if (userInfo.isMgmt4()) {
			// 上記以外
			resultList = internshipServiceImpl.findAllInternshipView(InternshipServiceImpl.SEARCH_VIEW_TYPE_4,
					userInfo.getTargetPartyCode(), userInfo.getTargetRole().getAuthority(), PUBLIC_FLAG,
					InternshipServiceImpl.SEARCH_DATE_TYPE_CURRENT);
		}

		Map<String, List<InternshipViewDto>> mapList = new HashMap<String, List<InternshipViewDto>>();
		// 区分から一覧を生成
		for (int i = 0; i < kbnList.size(); i++) {
			MsCodeDto dto = kbnList.get(i);
			//
			List<InternshipViewDto> inteList = new ArrayList<InternshipViewDto>();
			for (int j = 0; j < resultList.size(); j++) {
				ItInternView itInternView = resultList.get(j);
				// 区分毎に振り分け
				if (itInternView.getInternshipKbn().contains(dto.getCode())) {
					InternshipViewDto row = new InternshipViewDto();
					row.setInternshipKey(itInternView.getInternshipKey());
					row.setInternshipKbn(itInternView.getInternshipKbn());
					row.setEventUnit(itInternView.getEventUnit());
					row.setInternshipEndDate(itInternView.getInternshipEndDate());
					row.setPartyCode(itInternView.getPartyCode());
					row.setSubjectInsKbn(itInternView.getSubjectInsKbn());
					row.setInternshipTelno(itInternView.getInternshipTelno());
					row.setInternshipRange(itInternView.getInternshipRange());
					row.setInternshipStartDate(itInternView.getInternshipStartDate());
					row.setInternshipMemo(itInternView.getInternshipMemo());
					row.setUpdDate(itInternView.getUpdDate());
					row.setUpdUserKey(itInternView.getUpdUserKey());
					row.setInternshipGkFileNeedKbn(itInternView.getInternshipGkFileNeedKbn());
					row.setInternshipTitle(itInternView.getInternshipTitle());
					row.setPartyName(itInternView.getPartyName());
					row.setPublicFlag(itInternView.getPublicFlag());
					row.setInternshipSendDate(itInternView.getInternshipSendDate());
					row.setInternshipRcFileNeedKbn(itInternView.getInternshipRcFileNeedKbn());
					row.setPartyNameEn(itInternView.getPartyNameEn());
					row.setInternshipPartyName(itInternView.getInternshipPartyName());
					inteList.add(row);
				}
			}
			mapList.put(dto.getCode(), inteList);
		}

		model.addAttribute("kbnList", kbnList);
		model.addAttribute("mapList", mapList);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_PAGE;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public String download(@ModelAttribute(CommonConst.FORM_NAME) final InternshipForm form, Model model,
			RedirectAttributes attributes) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		if (internshipServiceImpl.download(userInfo, form, "1")) {
			// 成功の場合
		} else {
			// 失敗の場合
		}

		return LIST_PAGE;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public String upload(@ModelAttribute(CommonConst.FORM_NAME) final InternshipForm form, Model model,
			RedirectAttributes attributes) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "internshipKey=" + form.getInternshipKey() + ", userKey=" + userInfo.getTargetUserKey();

		ItInternRecruitTblPK pk = new ItInternRecruitTblPK();
		pk.setInternshipKey(form.getInternshipKey());
		pk.setUserKey(userInfo.getTargetUserKey());

		// 3-1． 応募済みのチェック
		if (internshipServiceImpl.getOneInternRecruit(pk) != null) {

			// 登録テーブル：インターンシップ応募者テーブル
			if (internshipServiceImpl.insertInternRecruit(userInfo, form)) {
				// DB更新が成功した場合
				logger.infoCode("I1005", key); // I1005=新規作成しました。{0}
			} else {
				// DB更新が失敗した場合
				logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
				model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
			}
		}

		// 応募書類をアップロードした場合
		if ("1".equals(form.getInternshipRcFileNeedKbn())) {
			// 更新テーブル：インターンシップ応募者添付ファイル
			if (internshipServiceImpl.updateInternRecruitUpload(userInfo, form)) {
				// DB更新が成功した場合
				logger.infoCode("I1004", key); // I1004=更新しました。{0}
			} else {
				// DB更新が失敗した場合
				logger.errorCode("E1008", key); // E1008=更新に失敗しました。{0}
				model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
			}
		}

		// お知らせ情報、お知らせ情報公開範囲登録
		if (internshipServiceImpl.insertCmInfo(userInfo, form, "202", "ROLE_MGMT2", userInfo.getTargetPartyCode())) {
			// DB更新が成功した場合
			logger.infoCode("I1005", key); // I1005=新規作成しました。{0}
		} else {
			// DB更新が失敗した場合
			logger.errorCode("E1007", key); // E1007=登録に失敗しました。{0}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
		}

		return LIST_PAGE;
	}

	/**
	 * ダイレクトアクセス対策
	 *
	 * @return
	 */
	@RequestMapping(value = { "/edit", "/copy", "/create", "/update", "/delete", "/apply" }, method = RequestMethod.GET)
	public String redirect() {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。
		return CommonConst.REDIRECT_INDEX;
	}

}
