package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the us_competition_tbl database table.
 * 
 */
@Embeddable
public class UsCompetitionTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_key")
	private String userKey;

	@Column(name="seq_no")
	private Integer seqNo;

	public UsCompetitionTblPK() {
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
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
		if (!(other instanceof UsCompetitionTblPK)) {
			return false;
		}
		UsCompetitionTblPK castOther = (UsCompetitionTblPK)other;
		return 
			this.userKey.equals(castOther.userKey)
			&& this.seqNo.equals(castOther.seqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userKey.hashCode();
		hash = hash * prime + this.seqNo.hashCode();
		
		return hash;
	}
}