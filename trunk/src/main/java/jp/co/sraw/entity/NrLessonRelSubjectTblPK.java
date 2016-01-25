package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the nr_lesson_rel_subject_tbl database table.
 * 
 */
@Embeddable
public class NrLessonRelSubjectTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="lesson_key")
	private String lessonKey;

	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="subject_code")
	private String subjectCode;

	public NrLessonRelSubjectTblPK() {
	}
	public String getLessonKey() {
		return this.lessonKey;
	}
	public void setLessonKey(String lessonKey) {
		this.lessonKey = lessonKey;
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
		if (!(other instanceof NrLessonRelSubjectTblPK)) {
			return false;
		}
		NrLessonRelSubjectTblPK castOther = (NrLessonRelSubjectTblPK)other;
		return 
			this.lessonKey.equals(castOther.lessonKey)
			&& this.rubricKey.equals(castOther.rubricKey)
			&& this.subjectCode.equals(castOther.subjectCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lessonKey.hashCode();
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.subjectCode.hashCode();
		
		return hash;
	}
}