package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ev_event_upload_tbl database table.
 * 
 */
@Embeddable
public class EvEventUploadTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="event_key")
	private String eventKey;

	@Column(name="upload_key")
	private String uploadKey;

	public EvEventUploadTblPK() {
	}
	public String getEventKey() {
		return this.eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
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
		if (!(other instanceof EvEventUploadTblPK)) {
			return false;
		}
		EvEventUploadTblPK castOther = (EvEventUploadTblPK)other;
		return 
			this.eventKey.equals(castOther.eventKey)
			&& this.uploadKey.equals(castOther.uploadKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.eventKey.hashCode();
		hash = hash * prime + this.uploadKey.hashCode();
		
		return hash;
	}
}