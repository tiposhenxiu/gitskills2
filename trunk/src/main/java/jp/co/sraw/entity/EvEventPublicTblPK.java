package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ev_event_public_tbl database table.
 * 
 */
@Embeddable
public class EvEventPublicTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="event_key")
	private String eventKey;

	@Column(name="seq_no")
	private Integer seqNo;

	public EvEventPublicTblPK() {
	}
	public String getEventKey() {
		return this.eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
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
		if (!(other instanceof EvEventPublicTblPK)) {
			return false;
		}
		EvEventPublicTblPK castOther = (EvEventPublicTblPK)other;
		return 
			this.eventKey.equals(castOther.eventKey)
			&& this.seqNo.equals(castOther.seqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.eventKey.hashCode();
		hash = hash * prime + this.seqNo.hashCode();
		
		return hash;
	}
}