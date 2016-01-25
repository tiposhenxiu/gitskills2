package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the kj_board_rel_user database table.
 * 
 */
@Embeddable
public class KjBoardRelUserPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="board_group_key")
	private String boardGroupKey;

	@Column(name="user_key")
	private String userKey;

	public KjBoardRelUserPK() {
	}
	public String getBoardGroupKey() {
		return this.boardGroupKey;
	}
	public void setBoardGroupKey(String boardGroupKey) {
		this.boardGroupKey = boardGroupKey;
	}
	public String getUserKey() {
		return this.userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KjBoardRelUserPK)) {
			return false;
		}
		KjBoardRelUserPK castOther = (KjBoardRelUserPK)other;
		return 
			this.boardGroupKey.equals(castOther.boardGroupKey)
			&& this.userKey.equals(castOther.userKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.boardGroupKey.hashCode();
		hash = hash * prime + this.userKey.hashCode();
		
		return hash;
	}
}