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
public enum Permission {
	NOT("NOT_ACCESS", 0), // Not access
	R("READ_ONLY", 10), // read only
	RW("READ_WRITE", 20), // read write
	;

	private final String permission;
	private final int rank;

	private Permission(final String permission, final int rank) {
		this.permission = permission;
		this.rank = rank;
	}

	/**
	 * Permission存在判定
	 *
	 * @param role
	 * @return
	 */
	public static boolean isPermission(String permission) {
		return LazyInitializHolder.roleToEnum.get(permission) != null;
	}

	/**
	 * Permission値取得
	 *
	 * @return
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * ランク値取得(大きいほど強い)
	 * 複数権限があった場合の選択用
	 *
	 * @return
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * アクセス権限の有無(有:ture、無:false)
	 *
	 * @return
	 */
	public boolean isAccess() {
		return Permission.RW.getPermission().equals(permission) || Permission.R.getPermission().equals(permission);
	}

	/**
	 * 書き込み権限の有無(有:ture、無:false)
	 *
	 * @return
	 */
	public boolean isWrite() {
		return Permission.RW.getPermission().equals(permission);
	}

	/**
	 * String to Permission
	 *
	 * @param role
	 * @return
	 */
	public static Permission permissionOf(final String permission) {
		return LazyInitializHolder.roleToEnum.get(permission);
	}

	private static class LazyInitializHolder {
		static final Map<String, Permission> roleToEnum = new HashMap<String, Permission>() {
			{
				for (Permission permissions : Permission.values())
					put(permissions.getPermission(), permissions);
			}
		};
	}

}
