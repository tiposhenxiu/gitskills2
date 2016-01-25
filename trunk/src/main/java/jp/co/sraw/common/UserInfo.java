/*
* ファイル名：UserInfo.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.CollectionUtils;

import jp.co.sraw.security.Permission;
import jp.co.sraw.security.Role;

/**
* <B>UserInfoクラス</B>
* <P>
* UserInfoのメソッドを提供する
*/
public class UserInfo extends User {

	private static final long serialVersionUID = 1L;

	/** 画面入力ユーザーキー */
	private String targetUserKey;
	/** 画面入力組織/団体コード */
	private String targetPartyCode;
	/** 画面入力ターゲットユーザー名 */
	private String targetUserName;
	/** 画面入力ターゲットユーザー名(英) */
	private String targetUserNameEn;
	/** 画面入力ターゲット組織/団体名 */
	private String targetPartyName;
	/** 画面入力ターゲット組織/団体名(英) */
	private String targetPartyNameEn;
	private String targetDegree;
	/** 画面入力ターゲットロールコード(選択中のロールコード＝メニュー切り替え用) */
	private String targetRoleCode;
	/** 画面入力ターゲットロール(選択中のロールコードの役割) */
	private GrantedAuthority targetRole;
	/**
	 * 画面入力ターゲット操作パーミッションマップ(例：画面(URL)に対してアクセス、書き込み権限の有無)
	 * 例: (key="/mgmt/support", value=Permission.RW) => 支援制度管理の書き込み権限あり。
	 */
	private Map<String, Permission> targetPermissions;

	/** ログインユーザーID */
	private String loginUserId;
	/** ログインユーザーキー */
	private String loginUserKey;
	/** ログイン組織/団体コード */
	private String loginPartyCode;
	/** ログイン組織/団体名 */
	private String loginPartyName;
	/** ログイン組織/団体名(英) */
	private String loginPartyNameEn;
	/** ログインユーザー名 */
	private String loginUserName;
	/** ログインユーザー名(英) */
	private String loginUserNameEn;
	/** ログインユーザーのROLE_CODE一覧 */
	private Set<String> loginRoleCodes;

	/** ログインユーザーのROLE一覧(ROLE_xxの形式)(親クラスにある) */
	//private final Set<GrantedAuthority> authorities;

	/**
	 * コンストラクタ
	 * 無名
	 */
	public UserInfo() {
		this("INVALID", "INVALID", "INVALID", "INVALID", "INVALID", "INVALID", "INVALID", "INVALID", "INVALID", null, null, null, null, false, false, false, false, new ArrayList<GrantedAuthority>());
	}
	/**
	 * コンストラクタ
	 */
	public UserInfo(String userKey, String username, String usernameEn, String loginid, String password, String partyCode, String partyName, String partyNameEn, String degree,
			Set<String>roleCodes, String selectedRoleCode, GrantedAuthority selectedRole, Map<String, Permission> permissions, Collection<? extends GrantedAuthority> authorities) {
		this(userKey, username, usernameEn, loginid, password, partyCode, partyName, partyNameEn, degree, roleCodes, selectedRoleCode, selectedRole, permissions, true, false, false, false, authorities);
	}
	/**
	 * コンストラクタ
	 */
	public UserInfo(String userKey, String username, String usernameEn, String loginid, String password, String partyCode, String partyName, String partyNameEn, String degree,
			Set<String>roleCodes, String selectedRoleCode, GrantedAuthority selectedRole, Map<String, Permission> permissions, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		// USER
		super(loginid, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		// ログイン
		this.loginUserKey = userKey;
		this.loginUserId = loginid;
		this.loginUserName = username;
		this.loginUserNameEn = usernameEn;
		this.loginPartyCode = partyCode;
		this.loginPartyName = partyName;
		this.loginPartyNameEn = partyNameEn;
		this.loginRoleCodes = roleCodes;

		//ターゲット
		setTarget(userKey, username, usernameEn, partyCode, partyName, partyNameEn, degree,
				selectedRoleCode, selectedRole, permissions);

	}
	/**
	 * ターゲット
	 *
	 * @param targetUserKey
	 * @param targetUserName
	 * @param targetUserNameEn
	 * @param targetPartyCode
	 * @param targetPartyName
	 * @param targetPartyNameEn
	 * @param targetRoleCode
	 * @param targetPermissions
	 */
	public void setTarget(String targetUserKey, String targetUserName, String targetUserNameEn,
			String targetPartyCode, String targetPartyName, String targetPartyNameEn, String targetDegree,
			String targetRoleCode, GrantedAuthority targetRole, Map<String, Permission> targetPermissions) {

		this.targetUserKey = targetUserKey;
		this.targetUserName= targetUserName;
		this.targetUserNameEn = targetUserNameEn;
		this.targetPartyCode = targetPartyCode;
		this.targetPartyName = targetPartyName;
		this.targetPartyNameEn = targetPartyNameEn;
		this.targetDegree = targetDegree;
		this.targetRoleCode = targetRoleCode;
		this.targetRole = targetRole;
		this.targetPermissions = targetPermissions;
	}

	/**
	 * ターゲットの役割がシステム管理者か？
	 *
	 * @return
	 */
	public boolean isAdmin() {
		if (getTargetRole() != null) {
			return Role.ROLE_ADMIN.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割がHIRAKU 運営協議会事務局か？
	 *
	 * @return
	 */
	public boolean isMgmt1() {
		if (getTargetRole() != null) {
			return Role.ROLE_MGMT1.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割が共同実施機関窓口（山大・徳大）か？
	 *
	 * @return
	 */
	public boolean isMgmt2() {
		if (getTargetRole() != null) {
			return Role.ROLE_MGMT2.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割が連携大学か？
	 *
	 * @return
	 */
	public boolean isMgmt3() {
		if (getTargetRole() != null) {
			return Role.ROLE_MGMT3.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割が連携期間窓口（企業、研究所）か？
	 *
	 * @return
	 */
	public boolean isMgmt4() {
		if (getTargetRole() != null) {
			return Role.ROLE_MGMT4.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割が個人ユーザ（任期付研究員）か？
	 *
	 * @return
	 */
	public boolean isUser1() {
		if (getTargetRole() != null) {
			return Role.ROLE_USER1.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割が個人ユーザ（博士課程後期・一貫）か？
	 *
	 * @return
	 */
	public boolean isUser2() {
		if (getTargetRole() != null) {
			return Role.ROLE_USER2.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割が個人ユーザ（博士課程前期）か？
	 *
	 * @return
	 */
	public boolean isUser3() {
		if (getTargetRole() != null) {
			return Role.ROLE_USER3.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割が個人ユーザ（教職員）か？
	 *
	 * @return
	 */
	public boolean isUser4() {
		if (getTargetRole() != null) {
			return Role.ROLE_USER4.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割が個人ユーザ（相談員）か？
	 *
	 * @return
	 */
	public boolean isUser5() {
		if (getTargetRole() != null) {
			return Role.ROLE_USER5.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * ターゲットの役割がコンソーシアム外か？
	 *
	 * @return
	 */
	public boolean isOther() {
		if (getTargetRole() != null) {
			return Role.ROLE_OTHER.getRole().equals(getTargetRole().getAuthority());
		}
		return false;
	}

	/**
	 * システム管理者の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasAdmin() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_ADMIN.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * HIRAKU 運営協議会事務局の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasMgmt1() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_MGMT1.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * 共同実施機関窓口（山大・徳大）の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasMgmt2() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_MGMT2.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * 連携大学の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasMgmt3() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_MGMT3.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * 連携期間窓口（企業、研究所）の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasMgmt4() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_MGMT4.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * 個人ユーザ（任期付研究員）の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasUser1() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_USER1.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * 個人ユーザ（博士課程後期・一貫）の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasUser2() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_USER2.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * 個人ユーザ（博士課程前期）の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasUser3() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_USER3.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * 個人ユーザ（教職員）の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasUser4() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_USER4.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * 個人ユーザ（相談員）の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasUser5() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_USER5.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	/**
	 * コンソーシアム外の役割を持っているか
	 *
	 * @return
	 */
	public boolean hasOther() {
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			for (GrantedAuthority g : getAuthorities()) {
				return Role.ROLE_OTHER.getRole().equals(g.getAuthority());
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		//ターゲット
		sb.append("TargetUserKey: ").append(this.targetUserKey).append("; ");
		sb.append("TargetPartyCode: ").append(this.targetPartyCode).append("; ");
		sb.append("TargetUserName: ").append(this.targetUserName).append("; ");
		sb.append("TargetUserNameEn: ").append(this.targetUserNameEn).append("; ");
		sb.append("TargetPartyName: ").append(this.targetPartyName).append("; ");
		sb.append("TargetPartyNameEn: ").append(this.targetPartyNameEn).append("; ");
		sb.append("TargetRoleCode: ").append(this.targetRoleCode).append("; ");
		sb.append("TargetRole: ").append(this.targetRole).append("; ");
		//
		if (this.targetPermissions != null && !this.targetPermissions.isEmpty()) {
			sb.append("TargetPermissions: ");

			boolean first = true;
			for (Entry<String, Permission> e : this.targetPermissions.entrySet()) {
				if (!first) {
					sb.append(",");
				}
				first = false;
				sb.append(e.getKey() +"="+ e.getValue());
			}
		}
		else {
			sb.append("Not Permission");
		}
		sb.append("; ");

		//ログイン
		sb.append("LoginUserId: ").append(this.loginUserId).append("; ");
		sb.append("LoginUserKey: ").append(this.loginUserKey).append("; ");
		sb.append("LoginPartyCode: ").append(this.loginPartyCode).append("; ");
		sb.append("LoginPartyName: ").append(this.loginPartyName).append("; ");
		sb.append("LoginPartyNameEn: ").append(this.loginPartyNameEn).append("; ");
		sb.append("LoginUserName: ").append(this.loginUserName).append("; ");
		sb.append("LoginUserNameEn: ").append(this.loginUserNameEn).append("; ");
		//
		if (this.loginRoleCodes != null && !this.loginRoleCodes.isEmpty()) {
			sb.append("LoginRoleCodes: ");

			boolean first = true;
			for (String e : this.loginRoleCodes) {
				if (!first) {
					sb.append(",");
				}
				first = false;
				sb.append(e);
			}
		}
		else {
			sb.append("Not RoleCodes");
		}
		sb.append("; ");

		//
		return sb.toString();
	}
	/**
	 * @return targetUserKey
	 */
	public String getTargetUserKey() {
		return targetUserKey;
	}
	/**
	 * @return targetPartyCode
	 */
	public String getTargetPartyCode() {
		return targetPartyCode;
	}
	/**
	 * @return targetUserName
	 */
	public String getTargetUserName() {
		return targetUserName;
	}
	/**
	 * @return targetUserNameEn
	 */
	public String getTargetUserNameEn() {
		return targetUserNameEn;
	}
	/**
	 * @return targetPartyName
	 */
	public String getTargetPartyName() {
		return targetPartyName;
	}
	/**
	 * @return targetPartyNameEn
	 */
	public String getTargetPartyNameEn() {
		return targetPartyNameEn;
	}
	/**
	 * @return targetRoleCode
	 */
	public String getTargetRoleCode() {
		return targetRoleCode;
	}
	/**
	 * @return targetRole
	 */
	public GrantedAuthority getTargetRole() {
		return targetRole;
	}
	/**
	 * @return targetPermissions
	 */
	public Map<String, Permission> getTargetPermissions() {
		return targetPermissions;
	}
	/**
	 * @return loginUserId
	 */
	public String getLoginUserId() {
		return loginUserId;
	}
	/**
	 * @return loginUserKey
	 */
	public String getLoginUserKey() {
		return loginUserKey;
	}
	/**
	 * @return loginPartyCode
	 */
	public String getLoginPartyCode() {
		return loginPartyCode;
	}
	/**
	 * @return loginPartyName
	 */
	public String getLoginPartyName() {
		return loginPartyName;
	}
	/**
	 * @return loginPartyNameEn
	 */
	public String getLoginPartyNameEn() {
		return loginPartyNameEn;
	}
	/**
	 * @return loginUserName
	 */
	public String getLoginUserName() {
		return loginUserName;
	}
	/**
	 * @return loginUserNameEn
	 */
	public String getLoginUserNameEn() {
		return loginUserNameEn;
	}
	/**
	 * @return loginRoleCodes
	 */
	public Set<String> getLoginRoleCodes() {
		return loginRoleCodes;
	}
	public String getTargetDegree() {
		return targetDegree;
	}
}
