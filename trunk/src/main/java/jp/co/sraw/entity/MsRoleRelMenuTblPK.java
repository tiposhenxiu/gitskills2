package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ms_role_rel_menu_tbl database table.
 * 
 */
@Embeddable
public class MsRoleRelMenuTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_code")
	private String roleCode;

	@Column(name="menu_id")
	private Integer menuId;

	public MsRoleRelMenuTblPK() {
	}
	public String getRoleCode() {
		return this.roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Integer getMenuId() {
		return this.menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MsRoleRelMenuTblPK)) {
			return false;
		}
		MsRoleRelMenuTblPK castOther = (MsRoleRelMenuTblPK)other;
		return 
			this.roleCode.equals(castOther.roleCode)
			&& this.menuId.equals(castOther.menuId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleCode.hashCode();
		hash = hash * prime + this.menuId.hashCode();
		
		return hash;
	}
}