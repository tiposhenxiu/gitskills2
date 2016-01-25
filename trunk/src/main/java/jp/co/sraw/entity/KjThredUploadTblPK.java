package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the kj_thred_upload_tbl database table.
 * 
 */
@Embeddable
public class KjThredUploadTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="thred_key")
	private String thredKey;

	@Column(name="seq_no")
	private Integer seqNo;

	@Column(name="upload_key")
	private String uploadKey;

	public KjThredUploadTblPK() {
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
	public String getUploadKey() {
		return this.uploadKey;
	}
	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KjThredUploadTblPK)) {
			return false;
		}
		KjThredUploadTblPK castOther = (KjThredUploadTblPK)other;
		return 
			this.thredKey.equals(castOther.thredKey)
			&& this.seqNo.equals(castOther.seqNo)
			&& this.uploadKey.equals(castOther.uploadKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.thredKey.hashCode();
		hash = hash * prime + this.seqNo.hashCode();
		hash = hash * prime + this.uploadKey.hashCode();
		
		return hash;
	}
}