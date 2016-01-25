package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the us_internship_tbl database table.
 * 
 */
@Embeddable
public class UsInternshipTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private java.util.Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="to_date")
	private java.util.Date toDate;

	@Column(name="party_name")
	private String partyName;

	public UsInternshipTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public java.util.Date getFromDate() {
		return this.fromDate;
	}
	public void setFromDate(java.util.Date fromDate) {
		this.fromDate = fromDate;
	}
	public java.util.Date getToDate() {
		return this.toDate;
	}
	public void setToDate(java.util.Date toDate) {
		this.toDate = toDate;
	}
	public String getPartyName() {
		return this.partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsInternshipTblPK)) {
			return false;
		}
		UsInternshipTblPK castOther = (UsInternshipTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.fromDate.equals(castOther.fromDate)
			&& this.toDate.equals(castOther.toDate)
			&& this.partyName.equals(castOther.partyName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.fromDate.hashCode();
		hash = hash * prime + this.toDate.hashCode();
		hash = hash * prime + this.partyName.hashCode();
		
		return hash;
	}
}