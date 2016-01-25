package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the us_result_upload_tbl database table.
 * 
 */
@Embeddable
public class UsResultUploadTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Column(name="upload_key")
	private String uploadKey;

	public UsResultUploadTblPK() {
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
		if (!(other instanceof UsResultUploadTblPK)) {
			return false;
		}
		UsResultUploadTblPK castOther = (UsResultUploadTblPK)other;
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