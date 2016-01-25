/*
* ファイル名：HomeController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.home;


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
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.dto.CmInfoDto;
import jp.co.sraw.dto.UsOperationHistoryDto;
import jp.co.sraw.entity.CmInfoTbl;
import jp.co.sraw.entity.MsRoleTbl;
import jp.co.sraw.entity.UsInfoTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.HomeServiceImpl;
import jp.co.sraw.service.MsRoleServiceImpl;
import jp.co.sraw.service.UserInfoServiceImpl;
import jp.co.sraw.util.ObjectUtil;
import jp.co.sraw.util.StringUtil;


/**
* <B>HomeControllerクラス</B>
* <P>
* Controllerのメソッドを提供する
*/
@Controller
@RequestMapping("/home")
public class HomeController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(HomeController.class);

	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;

	@Autowired
	private MsRoleServiceImpl msRoleServiceImpl;

	@Autowired
	private HomeServiceImpl homeServiceImpl;

	private static final String LIST_PAGE = "home/home";

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * 全RoleCode一覧を取得(表示用)
	 *
	 * @param locale
	 * @return
	 */
	@ModelAttribute("roleCodeMap")
	protected Map<String, String> roleCodeMap(Locale locale){

		List<MsRoleTbl> roleList = msRoleServiceImpl.findAll();
		Map<String, String> map = new HashMap<String, String>();
		for (MsRoleTbl r : roleList) {
			String name = r.getRoleName();
			if (!CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
				name = r.getRoleNameEn();
			}
			map.put(r.getRoleCode(), name);
		}

		return map;
	}

	/**
	 * Index画面
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({"", "/"})
	public String list(Model model, Locale locale) {

		logger.infoCode("I0001", "index"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey="+ userInfo.getLoginUserKey());
			logger.debug("TargetUserKey="+ userInfo.getTargetUserKey());
		}

		//1-2.個人用お知らせ情報取得
		List<UsInfoTbl> usInfoList = homeServiceImpl.findAllUsInfoByUserKey(userInfo.getTargetUserKey());

		//1-3.組織。及び、ロール向けお知らせ情報取得
		List<CmInfoTbl> cmInfoList = homeServiceImpl.findAllCmInfoByPartyOrRoll(userInfo.getTargetPartyCode(), userInfo.getTargetRole().getAuthority());

		//1-2、1-3で取得した情報をマージする。
		ObjectUtil objectUtil = new ObjectUtil();
		List<CmInfoDto> infoList = new ArrayList<CmInfoDto>();

		for (UsInfoTbl usInfoTbl : usInfoList){
			CmInfoDto cmInfoDto = new CmInfoDto();
			objectUtil.getObjectCopyValue(cmInfoDto, usInfoTbl);
			infoList.add(cmInfoDto);
		}
		for (CmInfoTbl cmInfoTbl : cmInfoList){
			CmInfoDto cmInfoDto = new CmInfoDto();
			objectUtil.getObjectCopyValue(cmInfoDto, cmInfoTbl);
			infoList.add(cmInfoDto);
		}

		//1-6.操作履歴
		List<UsOperationHistoryDto> operationHistoryList = homeServiceImpl.findAllUsOperationHistory(userInfo.getTargetUserKey());

		HomeForm from = new HomeForm();
		model.addAttribute("form", from);
		model.addAttribute("infoList", infoList);
		model.addAttribute("operationHistoryList", operationHistoryList);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}
		return LIST_PAGE;
	}

	/**
	 * RoleCode＆メニューの切り替え
	 *
	 * @param selectedRoleCode
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/changeRole", method = RequestMethod.POST)
	public String changeRole(@RequestParam(value="r") String selectedRoleCode, Model model, Locale locale) {
		logger.infoCode("I0001", "changeRole"); // I0001=メソッド開始:{0}

		if (StringUtil.isNotNull(selectedRoleCode)) {

			logger.infoCode("I1008", selectedRoleCode); // I1008=選択したデータ。key={0}

			// UserInfo
			if (userInfo != null) {
				// RoleCode切り替え
				userInfo.setTarget(userInfo.getTargetUserKey(), userInfo.getTargetUserName(), userInfo.getTargetUserNameEn(),
						userInfo.getTargetPartyCode(), userInfo.getTargetPartyName(), userInfo.getTargetDegree(),
						userInfo.getTargetPartyNameEn(), selectedRoleCode, userInfoServiceImpl.getRole(userInfo.getTargetUserKey(), selectedRoleCode), userInfoServiceImpl.getPermissions(userInfo.getTargetUserKey(), selectedRoleCode));
			}
		}
		// dump
		modelDump(logger, model, "changeRole");

		logger.infoCode("I0002", "changeRole"); // I0002=メソッド終了:{0}
		return CommonConst.REDIRECT_INDEX;
	}

}
