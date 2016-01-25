package jp.co.sraw.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the nr_subject_answer_bkup_tbl database table.
 * 
 */
@Embeddable
public class NrSubjectAnswerBkupTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="subject_code")
	private String subjectCode;

	@Temporal(TemporalType.DATE)
	@Column(name="save_date")
	private java.util.Date saveDate;

	public NrSubjectAnswerBkupTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getRubricKey() {
		return this.rubricKey;
	}
	public void setRubricKey(String rubricKey) {
		this.rubricKey = rubricKey;
	}
	public String getSubjectCode() {
		return this.subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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
		if (!(other instanceof NrSubjectAnswerBkupTblPK)) {
			return false;
		}
		NrSubjectAnswerBkupTblPK castOther = (NrSubjectAnswerBkupTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.rubricKey.equals(castOther.rubricKey)
			&& this.subjectCode.equals(castOther.subjectCode)
			&& this.saveDate.equals(castOther.saveDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.subjectCode.hashCode();
		hash = hash * prime + this.saveDate.hashCode();
		
		return hash;
	}
}