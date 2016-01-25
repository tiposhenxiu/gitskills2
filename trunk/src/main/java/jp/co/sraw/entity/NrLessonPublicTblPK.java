package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the nr_lesson_public_tbl database table.
 * 
 */
@Embeddable
public class NrLessonPublicTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="lesson_key")
	private String lessonKey;

	@Column(name="party_code")
	private String partyCode;

	public NrLessonPublicTblPK() {
	}
	public String getLessonKey() {
		return this.lessonKey;
	}
	public void setLessonKey(String lessonKey) {
		this.lessonKey = lessonKey;
	}
	public String getPartyCode() {
		return this.partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NrLessonPublicTblPK)) {
			return false;
		}
		NrLessonPublicTblPK castOther = (NrLessonPublicTblPK)other;
		return 
			this.lessonKey.equals(castOther.lessonKey)
			&& this.partyCode.equals(castOther.partyCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lessonKey.hashCode();
		hash = hash * prime + this.partyCode.hashCode();
		
		return hash;
	}
}