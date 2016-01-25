package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the it_intern_rel_subject_tbl database table.
 * 
 */
@Embeddable
public class ItInternRelSubjectTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="internship_key")
	private String internshipKey;

	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="subject_code")
	private String subjectCode;

	public ItInternRelSubjectTblPK() {
	}
	public String getInternshipKey() {
		return this.internshipKey;
	}
	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
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
		if (!(other instanceof ItInternRelSubjectTblPK)) {
			return false;
		}
		ItInternRelSubjectTblPK castOther = (ItInternRelSubjectTblPK)other;
		return 
			this.internshipKey.equals(castOther.internshipKey)
			&& this.rubricKey.equals(castOther.rubricKey)
			&& this.subjectCode.equals(castOther.subjectCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.internshipKey.hashCode();
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.subjectCode.hashCode();
		
		return hash;
	}
}