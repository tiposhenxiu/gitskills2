package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the nr_grading_answer_tbl database table.
 * 
 */
@Embeddable
public class NrGradingAnswerTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="grading_date")
	private java.util.Date gradingDate;

	@Column(name="grading_kbn")
	private String gradingKbn;

	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="subject_code")
	private String subjectCode;

	public NrGradingAnswerTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public java.util.Date getGradingDate() {
		return this.gradingDate;
	}
	public void setGradingDate(java.util.Date gradingDate) {
		this.gradingDate = gradingDate;
	}
	public String getGradingKbn() {
		return this.gradingKbn;
	}
	public void setGradingKbn(String gradingKbn) {
		this.gradingKbn = gradingKbn;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NrGradingAnswerTblPK)) {
			return false;
		}
		NrGradingAnswerTblPK castOther = (NrGradingAnswerTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.gradingDate.equals(castOther.gradingDate)
			&& this.gradingKbn.equals(castOther.gradingKbn)
			&& this.rubricKey.equals(castOther.rubricKey)
			&& this.subjectCode.equals(castOther.subjectCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.gradingDate.hashCode();
		hash = hash * prime + this.gradingKbn.hashCode();
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.subjectCode.hashCode();
		
		return hash;
	}
}