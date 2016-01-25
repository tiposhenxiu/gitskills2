package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the us_lesson_tbl database table.
 * 
 */
@Embeddable
public class UsLessonTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lesson_date")
	private java.util.Date lessonDate;

	@Column(name="lesson_key")
	private String lessonKey;

	public UsLessonTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public java.util.Date getLessonDate() {
		return this.lessonDate;
	}
	public void setLessonDate(java.util.Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	public String getLessonKey() {
		return this.lessonKey;
	}
	public void setLessonKey(String lessonKey) {
		this.lessonKey = lessonKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsLessonTblPK)) {
			return false;
		}
		UsLessonTblPK castOther = (UsLessonTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.lessonDate.equals(castOther.lessonDate)
			&& this.lessonKey.equals(castOther.lessonKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.lessonDate.hashCode();
		hash = hash * prime + this.lessonKey.hashCode();
		
		return hash;
	}
}