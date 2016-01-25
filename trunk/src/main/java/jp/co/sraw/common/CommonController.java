/*
* ファイル名：CommonController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sraw.dto.MenuDto;
import jp.co.sraw.dto.OperateInfoDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.service.MenuServiceImpl;
import jp.co.sraw.service.OperationHistoryServiceImpl;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.ObjectUtil;

/**
 * <B>CommonControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
public abstract class CommonController {

	// ユーザー情報
	protected UserInfo userInfo;

	// メニュー一覧
	protected List<MenuDto> menuList;

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected SystemSetting systemSetting;

	@Autowired
	protected MenuServiceImpl menuServiceImpl;

	@Autowired
	protected OperationHistoryServiceImpl operationHistoryServiceImpl;

	@PostConstruct
	protected abstract void init();

	/**
	 * ログインユーザー情報を取得しuserInfoをModelにセット
	 *
	 * @return
	 */
	@ModelAttribute("userInfo")
	public UserInfo userInfo() {
		//
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserInfo) {
				this.userInfo = (UserInfo) principal;
				return this.userInfo;
			}
			return null;
		}
		return null;
	}

	/**
	 * 設定ロールに合わせてメニュー一覧を取得しmenuListをModelにセット
	 *
	 * @return
	 */
	@ModelAttribute("menuList")
	protected List<MenuDto> menuList() {

		if (userInfo != null) {
			menuList = menuServiceImpl.findAllRoleMenu(userInfo.getTargetRoleCode());
		} else {
			menuList = new ArrayList<MenuDto>();
		}
		return menuList;
	}

	/**
	 * 設定ロールに合わせてURIアクセス許可チェックをModelにセット
	 *
	 * @return
	 */
	@ModelAttribute("accessUri")
	protected boolean accessUri(HttpServletRequest request) {

		boolean rlt = false;
		// 対象外(メニューテーブルに存在しないURI)
		Set<String> na = new HashSet<String>();
		if (!CollectionUtils.isEmpty(systemSetting.getAccessUriList())) {
			for (String u : systemSetting.getAccessUriList()) {
				if (!u.startsWith("/")) {
					u = "/" + u;
				}
				if (!u.endsWith("/")) {
					u = u + "/";
				}
				na.add(u);
			}
		}
		// ログイン済み
		if (userInfo != null) {
			Set<String> accessUriList = menuServiceImpl.findAllRoleUri(userInfo.getTargetRoleCode());
			accessUriList.addAll(na);  //対象外追加

			// 最後に「/」があるかチェック
			String rContext = request.getContextPath();
			String rUri = request.getRequestURI();
			rUri = rUri.replaceFirst(rContext, "");
			if (!rUri.endsWith("/")) {
				rUri = rUri + "/";
			}
			// メニュー存在チェック
			for (String u : accessUriList) {
				if (rUri.startsWith(u)) {
					rlt = true;
					break;
				}
			}
			// 「/」の場合
			if ("/".equals(rUri)) {
				rlt = true;
			}
			// システム管理者の場合
			if (userInfo.isAdmin()) {
				rlt = true;
			}
		}
		return rlt;
	}

	/**
	 * for dubug
	 *
	 * @param model
	 * @param m
	 */
	public void modelDump(LoggerWrapper logger, Model model, String modelName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Model:{}", modelName);
			if (model != null) {
				Map<String, Object> mm = model.asMap();
				for (Entry<String, Object> entry : mm.entrySet()) {
					logger.debug("  key:{}=value:{}", entry.getKey(), mm.get(entry.getKey()));
				}
			} else {
				logger.debug("  Model is ", model);
			}
		}
	}

	/**
	 * 操作履歴
	 *
	 * @param operationFuncId 操作機能ID
	 * @param operationActionId 操作ID
	 */
	public void operationHistory(String operationFuncId, String operationActionId) {
		putOperationHistory(userInfo.getTargetUserKey(), operationFuncId, operationActionId, userInfo.getLoginUserKey());
	}

	/**
	 * 操作履歴テーブルに操作履歴を出力する。
	 *
	 * @param operationUserKey
	 * @param operationFuncId
	 * @param operationActionId
	 * @param updUserKey
	 */
	private void putOperationHistory(String operationUserKey, String operationFuncId,
			String operationActionId, String updUserKey) {
		try {
			OperateInfoDto operateInfoDto = new OperateInfoDto();
			operateInfoDto.setOerationFuncId(operationFuncId);
			operateInfoDto.setOperationActionId(operationActionId);
			operateInfoDto.setOperationDate(DateUtil.getNowTimestamp());
			operateInfoDto.setOperationUserKey(operationUserKey);
			operateInfoDto.setUpdDate(DateUtil.getNowTimestamp());
			operateInfoDto.setUpdUserKey(updUserKey);
			operationHistoryServiceImpl.insert(operateInfoDto);
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
	}

	public Object setMapCopyValue(Object targetObject, Map sourceObject){
		ObjectUtil util=new ObjectUtil();
		return util.setMapCopyValue(targetObject,sourceObject);
	}

	public Object getObjectCopyValue(Object targetObject, Object sourceObject){
		ObjectUtil util=new ObjectUtil();
		return util.getObjectCopyValue(targetObject,sourceObject);
	}

}
