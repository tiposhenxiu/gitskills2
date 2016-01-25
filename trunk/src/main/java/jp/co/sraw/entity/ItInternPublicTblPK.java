package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the it_intern_public_tbl database table.
 * 
 */
@Embeddable
public class ItInternPublicTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="internship_key")
	private String internshipKey;

	@Column(name="seq_no")
	private Integer seqNo;

	public ItInternPublicTblPK() {
	}
	public String getInternshipKey() {
		return this.internshipKey;
	}
	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
	}
	public Integer getSeqNo() {
		return this.seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItInternPublicTblPK)) {
			return false;
		}
		ItInternPublicTblPK castOther = (ItInternPublicTblPK)other;
		return 
			this.internshipKey.equals(castOther.internshipKey)
			&& this.seqNo.equals(castOther.seqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.internshipKey.hashCode();
		hash = hash * prime + this.seqNo.hashCode();
		
		return hash;
	}
}