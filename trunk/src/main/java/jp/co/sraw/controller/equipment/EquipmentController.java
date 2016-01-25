/*
* ファイル名：EquipmentController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.equipment;

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

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.controller.support.SupportForm;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.dto.SpSupportDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.SupportServiceImpl;
import jp.co.sraw.util.DbUtil;

/**
 * <B>EquipmentControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/equipment")
public class EquipmentController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(EquipmentController.class);

	@Autowired
	private SupportServiceImpl supportServiceImpl; // 支援制度と共通

	private static final String LIST_PAGE = "equipment/list";

	private static final String KBN = "2"; // 定数区分[0005]＝1:支援制度。2:機器設備利用

	private static final String HIRAKU_KBN = "0027"; // 定数区分[0027]＝1:HIRAKUリンク集。2:HIRAKUリンク集外

	//private static final String CODE_SYBCODE = "0022"; // 定数区分[0022] 機器設備利用の場合

	private static final String AREA_KBN = "0030"; // 定数区分[0030]

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
	 * 一覧
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String list(@ModelAttribute(CommonConst.FORM_NAME) SupportForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		List<SpSupportDto> slist = new ArrayList<>();

		//List<MsCodeDto> sybList = DbUtil.getJosuList(CODE_SYBCODE, locale);

		List<MsCodeDto> hirakukbnList = DbUtil.getJosuList(HIRAKU_KBN, locale);

		List<MsCodeDto> areaList = DbUtil.getJosuList(AREA_KBN, locale);

		// 初期値
		form.setSearchSpkikiKbn(KBN);
		form.setSearchPublicFlag(null);
		// 期間中
		form.setSearchDateType(SupportServiceImpl.SEARCH_DATE_TYPE_CURRENT);

		if (userInfo.isMgmt1()) {
			// 運営協議会事務局の場合
			form.setSearchDateType(null);
			slist = supportServiceImpl.findAllLikeKeyWords(form, locale);
		} else if (userInfo.isMgmt2() || userInfo.isMgmt3() || userInfo.isMgmt4()) {
			// 連携大学の場合
			slist = supportServiceImpl.findAllLikeKeyWords(form, locale);
		} else {
			// 運営協議会事務局、連携大学以外の場合
			form.setSearchPublicFlag(PUBLIC_FLAG); // 定数区分[0019]＝0:非公開。1:公開。
			slist = supportServiceImpl.findAllLikeKeyWords(form, locale);
		}

		Map<String, List<SpSupportDto>> mapList = new HashMap<String, List<SpSupportDto>>();

		// HIRAKUリンク区分から一覧を生成
		for (int i = 0; i < hirakukbnList.size(); i++) {
			MsCodeDto dto = hirakukbnList.get(i);
			List<SpSupportDto> list = new ArrayList<>();

			for (int j = 0; j < slist.size(); j++) {
				SpSupportDto sdto = slist.get(j);
				// HIRAKUリンク区分毎に振り分け
				if (sdto.getSupportHirakuKbn().contains(dto.getCode()))
					list.add(sdto);
			}
			mapList.put(dto.getCode(), list);
		}

		//model.addAttribute("sybList", sybList);
		model.addAttribute("hirakukbnList", hirakukbnList);
		model.addAttribute("areaList", areaList);
		model.addAttribute("mapList", mapList);
		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_PAGE;
	}
}
