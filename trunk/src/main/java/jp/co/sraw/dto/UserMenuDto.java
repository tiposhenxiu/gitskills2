package jp.co.sraw.dto;

import java.io.Serializable;

public class UserMenuDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleCode; //ロールコード
	private Integer menuId; //メニューID
	private String permission; //パーミッション
	private String name; //メニュー名称
	private String nameEn; //メニュー名称(英)
	private String actionUrl; //アクションURL

	public UserMenuDto() {
		super();
	}

	public UserMenuDto(String roleCode, Integer menuId, String permission, String name, String nameEn, String actionUrl) {
		super();
		setRoleCode(roleCode);
		setMenuId(menuId);
		setPermission(permission);
		setName(name);
		setNameEn(nameEn);
		setActionUrl(actionUrl);
	}

	/**
	 * @return roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * @param roleCode セットする roleCode
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * @return menuId
	 */
	public Integer getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId セットする menuId
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return permission
	 */
	public String getPermission() {
		return permission;
	}
	/**
	 * @param permission セットする permission
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}
	/**
	 * @param nameEn セットする nameEn
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	/**
	 * @return actionUrl
	 */
	public String getActionUrl() {
		return actionUrl;
	}
	/**
	 * @param actionUrl セットする actionUrl
	 */
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}


}
