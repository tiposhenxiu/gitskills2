package jp.co.sraw.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the us_prmovie_upload_tbl database table.
 * 
 */
@Embeddable
public class UsPrmovieUploadTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Column(name="upload_key")
	private String uploadKey;

	public UsPrmovieUploadTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
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
		if (!(other instanceof UsPrmovieUploadTblPK)) {
			return false;
		}
		UsPrmovieUploadTblPK castOther = (UsPrmovieUploadTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.uploadKey.equals(castOther.uploadKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.uploadKey.hashCode();
		
		return hash;
	}
}