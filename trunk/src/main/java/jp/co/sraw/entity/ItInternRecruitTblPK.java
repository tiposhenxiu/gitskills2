package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the it_intern_recruit_tbl database table.
 * 
 */
@Embeddable
public class ItInternRecruitTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="internship_key")
	private String internshipKey;

	@Column(name="user_key")
	private String userKey;

	public ItInternRecruitTblPK() {
	}
	public String getInternshipKey() {
		return this.internshipKey;
	}
	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
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
		if (!(other instanceof ItInternRecruitTblPK)) {
			return false;
		}
		ItInternRecruitTblPK castOther = (ItInternRecruitTblPK)other;
		return 
			this.internshipKey.equals(castOther.internshipKey)
			&& this.userKey.equals(castOther.userKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.internshipKey.hashCode();
		hash = hash * prime + this.userKey.hashCode();
		
		return hash;
	}
}