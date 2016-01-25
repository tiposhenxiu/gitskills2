package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the nr_rubric_lense database table.
 * 
 */
@Embeddable
public class NrRubricLensePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="lense_id")
	private String lenseId;

	public NrRubricLensePK() {
	}
	public String getRubricKey() {
		return this.rubricKey;
	}
	public void setRubricKey(String rubricKey) {
		this.rubricKey = rubricKey;
	}
	public String getLenseId() {
		return this.lenseId;
	}
	public void setLenseId(String lenseId) {
		this.lenseId = lenseId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NrRubricLensePK)) {
			return false;
		}
		NrRubricLensePK castOther = (NrRubricLensePK)other;
		return 
			this.rubricKey.equals(castOther.rubricKey)
			&& this.lenseId.equals(castOther.lenseId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.lenseId.hashCode();
		
		return hash;
	}
}