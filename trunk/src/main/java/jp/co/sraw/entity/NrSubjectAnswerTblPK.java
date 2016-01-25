package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The primary key class for the nr_subject_answer_tbl database table.
 * 
 */
@Embeddable
public class NrSubjectAnswerTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="subject_code")
	private String subjectCode;

	public NrSubjectAnswerTblPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NrSubjectAnswerTblPK)) {
			return false;
		}
		NrSubjectAnswerTblPK castOther = (NrSubjectAnswerTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.rubricKey.equals(castOther.rubricKey)
			&& this.subjectCode.equals(castOther.subjectCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.subjectCode.hashCode();
		
		return hash;
	}
}