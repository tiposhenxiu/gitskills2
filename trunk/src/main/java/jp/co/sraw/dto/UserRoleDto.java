package jp.co.sraw.dto;

import java.io.Serializable;

public class UserRoleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleCode; //ロールコード
	private String role; //ロール
	private String name; //ロールコード名称
	private String nameEn; //ロールコード名称(英)

	public UserRoleDto() {
		super();
	}

	public UserRoleDto(String roleCode, String role, String name, String nameEn) {
		super();
		setRoleCode(roleCode);
		setRole(role);
		setName(name);
		setNameEn(nameEn);
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
	 * @return role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role セットする role
	 */
	public void setRole(String role) {
		this.role = role;
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


}
