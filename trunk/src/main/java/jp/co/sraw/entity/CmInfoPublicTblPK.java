package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the cm_info_public_tbl database table.
 * 
 */
@Embeddable
public class CmInfoPublicTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="info_key")
	private String infoKey;

	@Column(name="seq_no")
	private Integer seqNo;

	public CmInfoPublicTblPK() {
	}
	public String getInfoKey() {
		return this.infoKey;
	}
	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
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
		if (!(other instanceof CmInfoPublicTblPK)) {
			return false;
		}
		CmInfoPublicTblPK castOther = (CmInfoPublicTblPK)other;
		return 
			this.infoKey.equals(castOther.infoKey)
			&& this.seqNo.equals(castOther.seqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.infoKey.hashCode();
		hash = hash * prime + this.seqNo.hashCode();
		
		return hash;
	}
}