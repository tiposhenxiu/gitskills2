/*
* ファイル名：UserInfoServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonService;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.dto.UserMenuDto;
import jp.co.sraw.dto.UserRoleDto;
import jp.co.sraw.entity.MsPartyTbl;
import jp.co.sraw.entity.UsLoginInfoTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.MsPartyTblRepository;
import jp.co.sraw.repository.UsLoginInfoTblRepository;
import jp.co.sraw.repository.UsUserRelRoleTblRepository;
import jp.co.sraw.security.Permission;
import jp.co.sraw.security.Role;
import jp.co.sraw.util.StringUtil;

/**
* <B>UserInfoServiceクラス</B>
* <P>
* ユーザー認証サービスのメソッドを提供する
*/
//@Scope("prototype")
//@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="request")
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="prototype")
@Service
@Transactional(readOnly = true)
public class UserInfoServiceImpl extends CommonService implements UserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;

	//@Autowired
	//private UsUserTblRepository usUserTblRepository;
	@Autowired
	private UsLoginInfoTblRepository usLoginInfoTblRepository;
	@Autowired
	private UsUserRelRoleTblRepository usUserRelRoleTblRepository;
	@Autowired
	private MsPartyTblRepository msPartyTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(UserInfoServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@Override
	//@Transactional(readOnly = true)
	public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		if (StringUtil.isNull(username)) {
			logger.warnCode("W0001", username); // W0001=ログイン失敗: ログインIDとパスワードが正しくありません。username={0}
			throw new UsernameNotFoundException("username is empty");
		}

		logger.infoCode("I0004", username); // I0004=ログインユーザーusername={0}

		// ログイン取得
		UsLoginInfoTbl loginInfo = usLoginInfoTblRepository.findOne(username);
		if (loginInfo == null) {
			logger.warnCode("W0001", username); // W0001=ログイン失敗: ログインIDとパスワードが正しくありません。username={0}
			throw new UsernameNotFoundException(username + " is not found");
		}
		boolean accountNonExpired = true; // アカウント有効期限
		boolean accountNonLocked = true; // アカウントロック
		boolean credentialsNonExpired = true; // パスワード有効期限
		boolean accountEnabled = (CommonConst.USE_FALG_ACTIVE.equals(loginInfo.getUseFlag())); // アカウント有効

		// ユーザー情報取得
		UsUserTbl user = loginInfo.getUsUserTbl();
		logger.infoCode("I0005", user.getUserKey()); // I0005=ログインユーザーuser_key={0}

		// 権限
		// ユーザーロールコード取得(ROLE_CODEとROLEを取得)
		List<UserRoleDto> userRoles = usUserRelRoleTblRepository.findAllUserRole(user.getUserKey());
		// 全ROLE_CODE用
		Set<String> roleCodes = new HashSet<String>();
		// 初期選択のROLE_CODE
		String selectedRoleCode = "";
		// 初期選択のROLE
		GrantedAuthority selectedRole = null;
		// 全ROLE用
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (UserRoleDto r : userRoles) {
			roleCodes.add(r.getRoleCode());
			logger.infoCode("I0009", r.getRoleCode()); // I0009=ログインユーザーrolecode={0}
			if (Role.isRole(r.getRole())) {
				if (StringUtil.isNull(selectedRoleCode)) {
					selectedRoleCode = r.getRoleCode();
					selectedRole = new SimpleGrantedAuthority(r.getRole());
				}
				authorities.add(new SimpleGrantedAuthority(r.getRole()));
				logger.infoCode("I0007", r.getRole()); // I0007=ログインユーザーauthority={0}
			} else {
				// 不明な権限
				logger.warnCode("I0007", r.getRole()); // I0007=ログインユーザーauthority={0}
			}
		}
		// ROLE_CODEの画面(メニュー)アクセス権用
		Map<String, Permission> permissions = getPermissions(user.getUserKey(), selectedRoleCode);

		// 所属
		MsPartyTbl party = msPartyTblRepository.findOne(user.getPartyCode());
		String partyCode = "";
		String partyName = "";
		String partyNameEng = "";
		if (party != null) {
			logger.infoCode("I0008", party.getPartyCode()); // I0008=ログインユーザーparty={0}
			partyCode = party.getPartyCode();
			partyName = party.getPartyName();
			partyNameEng = party.getPartyNameEn();
		}

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		// ユーザー
		return new UserInfo(user.getUserKey(), StringUtil.getUserName(user.getUserKbn(), user.getUserFamilyName(), user.getUserMiddleName(), user.getUserName()),
				StringUtil.getUserNameEn(user.getUserKbn(), user.getUserFamilyNameEn(), user.getUserMiddleNameEn(), user.getUserNameEn()),
				username, loginInfo.getPassword(), partyCode, partyName, partyNameEng, user.getDegree(), roleCodes, selectedRoleCode, selectedRole, permissions,
				accountEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

	}

	/**
	 * RoleCodeに対する画面アクセス権を取得
	 * @param userKey
	 * @param roleCode
	 * @return
	 */
	public Map<String, Permission> getPermissions(String userKey, String roleCode) {
		// メニューのアクセス権を取得(MENU_ID, ACTION_URL, PERMISSIONを取得)
		List<UserMenuDto> userMenus = usUserRelRoleTblRepository.findAllUserMenu(userKey, roleCode);
		// ROLE_CODEの画面(メニュー)アクセス権用
		Map<String, Permission> permissions = new HashMap<String, Permission>();
		for (UserMenuDto r : userMenus) {
			if (r != null) {
				String str = "[Action="+ r.getActionUrl() +", Permission="+ r.getPermission() +"]";
				if (Permission.isPermission(r.getPermission())) {
					Permission p = Permission.permissionOf(r.getPermission());
					if (permissions.containsKey(r.getActionUrl())) {
						// すでにパーミッションが存在していた場合に上位権限を選択
						if (permissions.get(r.getActionUrl()).getRank() < p.getRank()) {
							permissions.put(r.getActionUrl(), p);
						}
					} else {
						// 未登録なので追加
						permissions.put(r.getActionUrl(), p);
					}
					logger.infoCode("I0010", str); // I0010=ログインユーザーpermission={0}
				} else {
					// 不明なパーミッション
					logger.warnCode("I0010", str); // I0010=ログインユーザーpermission={0}
				}
			}
		}
		return permissions;
	}

	/**
	 * RoleCodeからRoleを取得
	 * @param roleCode
	 * @return
	 */
	public GrantedAuthority getRole(String userKey, String roleCode) {
		GrantedAuthority rtn = null;
		UserRoleDto r = usUserRelRoleTblRepository.findUserRole(userKey, roleCode);
		if (r !=null) {
			rtn = new SimpleGrantedAuthority(r.getRole());
		}
		return rtn;
	}

}
