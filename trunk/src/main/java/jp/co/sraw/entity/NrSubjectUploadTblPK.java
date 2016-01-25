package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the nr_subject_upload_tbl database table.
 * 
 */
@Embeddable
public class NrSubjectUploadTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="answer_date")
	private java.util.Date answerDate;

	@Column(name="rubric_key")
	private String rubricKey;

	private Integer lense;

	@Column(name="subject_code")
	private String subjectCode;

	@Column(name="upload_key")
	private String uploadKey;

	public NrSubjectUploadTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public java.util.Date getAnswerDate() {
		return this.answerDate;
	}
	public void setAnswerDate(java.util.Date answerDate) {
		this.answerDate = answerDate;
	}
	public String getRubricKey() {
		return this.rubricKey;
	}
	public void setRubricKey(String rubricKey) {
		this.rubricKey = rubricKey;
	}
	public Integer getLense() {
		return this.lense;
	}
	public void setLense(Integer lense) {
		this.lense = lense;
	}
	public String getSubjectCode() {
		return this.subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getUploadKey() {
		return this.uploadKey;
	}
	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NrSubjectUploadTblPK)) {
			return false;
		}
		NrSubjectUploadTblPK castOther = (NrSubjectUploadTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.answerDate.equals(castOther.answerDate)
			&& this.rubricKey.equals(castOther.rubricKey)
			&& this.lense.equals(castOther.lense)
			&& this.subjectCode.equals(castOther.subjectCode)
			&& this.uploadKey.equals(castOther.uploadKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.answerDate.hashCode();
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.lense.hashCode();
		hash = hash * prime + this.subjectCode.hashCode();
		hash = hash * prime + this.uploadKey.hashCode();
		
		return hash;
	}
}