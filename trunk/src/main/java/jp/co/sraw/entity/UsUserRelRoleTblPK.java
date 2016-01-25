package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the us_user_rel_role_tbl database table.
 * 
 */
@Embeddable
public class UsUserRelRoleTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Column(name="role_code")
	private String roleCode;

	public UsUserRelRoleTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getRoleCode() {
		return this.roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsUserRelRoleTblPK)) {
			return false;
		}
		UsUserRelRoleTblPK castOther = (UsUserRelRoleTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.roleCode.equals(castOther.roleCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.roleCode.hashCode();
		
		return hash;
	}
}