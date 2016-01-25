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
import jp.co.sraw.dto.InternshipApplicantDto;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.ItInternRecruitTblPK;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.InternshipServiceImpl;
import jp.co.sraw.util.DbUtil;

/**
 * <B>InternshipApplicationControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/internship/application")
public class InternshipApplicationController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(InternshipController.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@Autowired
	private InternshipServiceImpl internshipServiceImpl;

	private static final String LIST_PAGE = "internship/application/list";

	private static final String REDIRECT_LIST = "redirect:/internship/application/";

	// インターンシップ区分
	private static final String KBN = "0002"; //インターンシップ区分

	private static final String PUBLIC_FLAG = "1"; // 固定値: 公開

	@ModelAttribute(CommonConst.FORM_NAME)
	public InternshipForm setupForm() {
		InternshipForm form = new InternshipForm();
		return form;
	}

	/**
	 * ３．８．合否結果閲覧（一覧）
	 *
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String index(Model model, Locale locale) {

		logger.infoCode("I0001");



		if (logger.isDebugEnabled()) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		List<MsCodeDto> kbnList = DbUtil.getJosuList(KBN, locale);

		List<InternshipApplicantDto> resultList = internshipServiceImpl.findAllInternshipForApplication(userInfo.getTargetUserKey(), locale);

		Map<String, List<InternshipApplicantDto>> mapList = new HashMap<String, List<InternshipApplicantDto>>();
		// 区分から一覧を生成
		for (int i = 0; i < kbnList.size(); i++) {
			MsCodeDto dto = kbnList.get(i);
			//
			List<InternshipApplicantDto> inteList = new ArrayList<InternshipApplicantDto>();
			for (int j = 0; j < resultList.size(); j++) {
				InternshipApplicantDto data = resultList.get(j);
				// 区分毎に振り分け
				if (data.getInternshipKbn().contains(dto.getCode())) {
					InternshipApplicantDto row = new InternshipApplicantDto();
					row.setInternshipKey(data.getInternshipKey());
					row.setEventUnit(data.getEventUnit());
					row.setInternshipSendDate(data.getInternshipSendDate());
					row.setInternshipEndDate(data.getInternshipEndDate());
					row.setInternshipGkFileNeedKbn(data.getInternshipGkFileNeedKbn());
					row.setInternshipKbn(data.getInternshipKbn());
					row.setInternshipMemo(data.getInternshipMemo());
					row.setInternshipPartyName(data.getInternshipPartyName());
					row.setInternshipRange(data.getInternshipRange());
					row.setInternshipRcFileNeedKbn(data.getInternshipRcFileNeedKbn());
					row.setInternshipStartDate(data.getInternshipStartDate());
					row.setInternshipTelno(data.getInternshipTelno());
					row.setInternshipTitle(data.getInternshipTitle());
					row.setPartyCode(data.getPartyCode());
					row.setPublicFlag(data.getPublicFlag());
					row.setSubjectInsKbn(data.getSubjectInsKbn());
					row.setUpdDate(data.getUpdDate());
					row.setUpdUserKey(data.getUpdUserKey());
					row.setDecisionKbn(data.getDecisionKbn());
					row.setDecisionKbnName(data.getDecisionKbnName());
					row.setPartyKbn(data.getPartyKbn());
					row.setPartyNameEn(data.getPartyNameEn());
					row.setPartyName(data.getPartyName());
					inteList.add(row);
				}
			}
			mapList.put(dto.getCode(), inteList);
		}

		model.addAttribute("kbnList", kbnList);
		model.addAttribute("mapList", mapList);

		// dump
		modelDump(logger, model, "index");

		return LIST_PAGE;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/refuse", method = RequestMethod.POST)
	public String refuse(@ModelAttribute(CommonConst.FORM_NAME) final InternshipForm form, Model model,
			RedirectAttributes attributes) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "internshipKey="+ form.getInternshipKey() +", userKey="+ userInfo.getTargetUserKey();

		ItInternRecruitForm recruitForm = new ItInternRecruitForm();
		ItInternRecruitTblPK pk=new ItInternRecruitTblPK();
		pk.setInternshipKey(form.getInternshipKey());
		pk.setUserKey(userInfo.getTargetUserKey());
		recruitForm.setId(pk);

		try {
			//更新テーブル：インターンシップ応募者テーブル ＆ 削除テーブル：インターンシップ応募者添付ファイル
			if (internshipServiceImpl.refuseInternRecruit(userInfo, recruitForm)) {
				// DB更新が成功した場合
				logger.infoCode("I1004", key); // I1004=更新しました。{0}
				attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。
			} else {
				// DB更新が失敗した場合
				logger.errorCode("E1008", key); // E1008=更新に失敗しました。{0}
				model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
			}
		} catch (Exception e) {
			// DB更新が失敗した場合
			logger.errorCode("E1008", key); // E1008=更新に失敗しました。{0}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
		}

		return REDIRECT_LIST;
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

		if (internshipServiceImpl.download(userInfo, form, "2")) {
			//成功の場合
		} else {
			//失敗の場合
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
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@ModelAttribute(CommonConst.FORM_NAME) final InternshipForm form, Model model,
			RedirectAttributes attributes) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "internshipKey="+ form.getInternshipKey() +", userKey="+ userInfo.getTargetUserKey();

		//更新テーブル：インターンシップ応募者テーブル
		if (internshipServiceImpl.updateInternRecruitForGohiKeka(userInfo, form)) {
			// DB更新が成功した場合
			logger.infoCode("I1004", key); // I1004=更新しました。{0}
		} else {
			// DB更新が失敗した場合
			logger.errorCode("E1008", key); // E1008=更新に失敗しました。{0}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
		}

		//更新テーブル：インターンシップ応募者添付ファイル
		if (internshipServiceImpl.updateInternRecruitUploadForGohiKeka(userInfo, form)) {
			// DB更新が成功した場合
			logger.infoCode("I1004", key); // I1004=更新しました。{0}
		} else {
			// DB更新が失敗した場合
			logger.errorCode("E1008", key); // E1008=更新に失敗しました。{0}
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
		}

		// お知らせ情報、お知らせ情報公開範囲登録
		if (internshipServiceImpl.insertCmInfo(userInfo, form, "205", "ROLE_MGMT2", userInfo.getTargetPartyCode())) {
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
	@RequestMapping(value = {"/edit", "/copy", "/create", "/update", "/delete", "/upload", "/refuse", "/download"}, method = RequestMethod.GET)
	public String redirect() {
		logger.warnCode("W1009"); // W1009=URLダイレクトアクセスがありました。
		return CommonConst.REDIRECT_INDEX;
	}

}
