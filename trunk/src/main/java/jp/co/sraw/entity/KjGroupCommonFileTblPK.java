package jp.co.sraw.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the kj_group_common_file_tbl database table.
 * 
 */
@Embeddable
public class KjGroupCommonFileTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="thred_key")
	private String thredKey;

	@Column(name="upload_key")
	private String uploadKey;

	public KjGroupCommonFileTblPK() {
	}
	public String getThredKey() {
		return this.thredKey;
	}
	public void setThredKey(String thredKey) {
		this.thredKey = thredKey;
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
		if (!(other instanceof KjGroupCommonFileTblPK)) {
			return false;
		}
		KjGroupCommonFileTblPK castOther = (KjGroupCommonFileTblPK)other;
		return 
			this.thredKey.equals(castOther.thredKey)
			&& this.uploadKey.equals(castOther.uploadKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.thredKey.hashCode();
		hash = hash * prime + this.uploadKey.hashCode();
		
		return hash;
	}
}