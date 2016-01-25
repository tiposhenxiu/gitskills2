/*
* ファイル名：Role.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.security;

import java.util.HashMap;
import java.util.Map;

/**
* <B>Roleクラス</B>
* <P>
* Roleのメソッドを提供する
*/
public enum Role {
	ROLE_UNKNOWN("ROLE_UNKNOWN", 9000), // ログイン前、認証前
	ROLE_OTHER("ROLE_OTHER", 900), // コンソーシアム外
	ROLE_USER1("ROLE_USER1", 140), // 個人ユーザ（任期付研究員）
	ROLE_USER2("ROLE_USER2", 130), // 個人ユーザ（博士課程後期・一貫）
	ROLE_USER3("ROLE_USER3", 120), // 個人ユーザ（博士課程前期）
	ROLE_USER4("ROLE_USER4", 110), // 個人ユーザ（教職員）
	ROLE_USER5("ROLE_USER5", 100), // 個人ユーザ（相談員）
	ROLE_MGMT1("ROLE_MGMT1", 10), // HIRAKU 運営協議会事務局
	ROLE_MGMT2("ROLE_MGMT2", 20), // 共同実施機関窓口（山大・徳大）
	ROLE_MGMT3("ROLE_MGMT3", 30), // 連携大学
	ROLE_MGMT4("ROLE_MGMT4", 40), // 連携期間窓口（企業、研究所）
	ROLE_ADMIN("ROLE_ADMIN", -1), // システム管理者
	;

	private final String role;
	private final int rank;

	private Role(final String role, int rank) {
		this.role = role;
		this.rank = rank;
	}

	/**
	 * Role存在判定
	 *
	 * @param role
	 * @return
	 */
	public static boolean isRole(String role) {
		return LazyInitializHolder.roleToEnum.get(role) != null;
	}

	/**
	 * Role値取得
	 *
	 * @return
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Role値取得(ROLE_を除いた値)
	 *
	 * @return
	 */
	public String getShortRole() {
		return role.replaceFirst("ROLE_", "");
	}

	/**
	 * ランク値取得(小さいほど強い)
	 * 複数権限があった場合の初期選択用
	 *
	 * @return
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * String to Role
	 *
	 * @param role
	 * @return
	 */
	public static Role roleOf(final String role) {
		return LazyInitializHolder.roleToEnum.get(role);
	}

	private static class LazyInitializHolder {
		static final Map<String, Role> roleToEnum = new HashMap<String, Role>() {
			{
				for (Role roles : Role.values())
					put(roles.getRole(), roles);
			}
		};
	}

}
