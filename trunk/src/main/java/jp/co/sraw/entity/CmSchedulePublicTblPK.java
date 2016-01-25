package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the cm_schedule_public_tbl database table.
 *
 */
@Embeddable
public class CmSchedulePublicTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="suhdule_key")
	private String suhduleKey;

	@Column(name="seq_no")
	private Integer seqNo;

	public CmSchedulePublicTblPK() {
	}
	public String getSuhduleKey() {
		return this.suhduleKey;
	}
	public void setSuhduleKey(String suhduleKey) {
		this.suhduleKey = suhduleKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CmSchedulePublicTblPK)) {
			return false;
		}
		CmSchedulePublicTblPK castOther = (CmSchedulePublicTblPK)other;
		return
			this.suhduleKey.equals(castOther.suhduleKey)
			&& this.seqNo.equals(castOther.seqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.suhduleKey.hashCode();
		hash = hash * prime + this.seqNo.hashCode();

		return hash;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
}