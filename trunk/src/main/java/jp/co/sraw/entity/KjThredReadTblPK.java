package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the kj_thred_read_tbl database table.
 * 
 */
@Embeddable
public class KjThredReadTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="thred_key")
	private String thredKey;

	@Column(name="user_key")
	private String userKey;

	public KjThredReadTblPK() {
	}
	public String getThredKey() {
		return this.thredKey;
	}
	public void setThredKey(String thredKey) {
		this.thredKey = thredKey;
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KjThredReadTblPK)) {
			return false;
		}
		KjThredReadTblPK castOther = (KjThredReadTblPK)other;
		return 
			this.thredKey.equals(castOther.thredKey)
			&& this.userKey.equals(castOther.userKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.thredKey.hashCode();
		hash = hash * prime + this.userKey.hashCode();
		
		return hash;
	}
}