package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the nr_rubric_rel_user_tbl database table.
 * 
 */
@Embeddable
public class NrRubricRelUserTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="user_key")
	private String userKey;

	public NrRubricRelUserTblPK() {
	}
	public String getRubricKey() {
		return this.rubricKey;
	}
	public void setRubricKey(String rubricKey) {
		this.rubricKey = rubricKey;
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
		if (!(other instanceof NrRubricRelUserTblPK)) {
			return false;
		}
		NrRubricRelUserTblPK castOther = (NrRubricRelUserTblPK)other;
		return 
			this.rubricKey.equals(castOther.rubricKey)
			&& this.userKey.equals(castOther.userKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rubricKey.hashCode();
		hash = hash * prime + this.userKey.hashCode();
		
		return hash;
	}
}