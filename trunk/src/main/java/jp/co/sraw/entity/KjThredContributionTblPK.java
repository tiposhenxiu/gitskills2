package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the kj_thred_contribution_tbl database table.
 * 
 */
@Embeddable
public class KjThredContributionTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="thred_key")
	private String thredKey;

	@Column(name="seq_no")
	private Integer seqNo;

	public KjThredContributionTblPK() {
	}
	public String getThredKey() {
		return this.thredKey;
	}
	public void setThredKey(String thredKey) {
		this.thredKey = thredKey;
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
		if (!(other instanceof KjThredContributionTblPK)) {
			return false;
		}
		KjThredContributionTblPK castOther = (KjThredContributionTblPK)other;
		return 
			this.thredKey.equals(castOther.thredKey)
			&& this.seqNo.equals(castOther.seqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.thredKey.hashCode();
		hash = hash * prime + this.seqNo.hashCode();
		
		return hash;
	}
}