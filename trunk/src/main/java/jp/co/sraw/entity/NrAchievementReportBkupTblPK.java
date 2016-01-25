package jp.co.sraw.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the nr_achievement_report_bkup_tbl database table.
 * 
 */
@Embeddable
public class NrAchievementReportBkupTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Temporal(TemporalType.DATE)
	@Column(name="save_date")
	private java.util.Date saveDate;

	public NrAchievementReportBkupTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public java.util.Date getSaveDate() {
		return this.saveDate;
	}
	public void setSaveDate(java.util.Date saveDate) {
		this.saveDate = saveDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NrAchievementReportBkupTblPK)) {
			return false;
		}
		NrAchievementReportBkupTblPK castOther = (NrAchievementReportBkupTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.saveDate.equals(castOther.saveDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.saveDate.hashCode();
		
		return hash;
	}
}