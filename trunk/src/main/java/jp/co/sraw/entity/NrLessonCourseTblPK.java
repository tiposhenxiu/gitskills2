package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the nr_lesson_course_tbl database table.
 * 
 */
@Embeddable
public class NrLessonCourseTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="lesson_key")
	private String lessonKey;

	@Column(name="user_key")
	private String userKey;

	public NrLessonCourseTblPK() {
	}
	public String getLessonKey() {
		return this.lessonKey;
	}
	public void setLessonKey(String lessonKey) {
		this.lessonKey = lessonKey;
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
		if (!(other instanceof NrLessonCourseTblPK)) {
			return false;
		}
		NrLessonCourseTblPK castOther = (NrLessonCourseTblPK)other;
		return 
			this.lessonKey.equals(castOther.lessonKey)
			&& this.userKey.equals(castOther.userKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lessonKey.hashCode();
		hash = hash * prime + this.userKey.hashCode();
		
		return hash;
	}
}