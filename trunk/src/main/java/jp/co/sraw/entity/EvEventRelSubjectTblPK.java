package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ev_event_rel_subject_tbl database table.
 * 
 */
@Embeddable
public class EvEventRelSubjectTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="event_key")
	private String eventKey;

	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="subject_code")
	private String subjectCode;

	public EvEventRelSubjectTblPK() {
	}
	public String getEventKey() {
		return this.eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
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
		if (!(other instanceof EvEventRelSubjectTblPK)) {
			return false;
		}
		EvEventRelSubjectTblPK castOther = (EvEventRelSubjectTblPK)other;
		return 
			this.eventKey.equals(castOther.eventKey)
			&& this.rubricKey.equals(castOther.rubricKey)
			&& this.subjectCode.equals(castOther.subjectCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.eventKey.hashCode();
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.subjectCode.hashCode();
		
		return hash;
	}
}