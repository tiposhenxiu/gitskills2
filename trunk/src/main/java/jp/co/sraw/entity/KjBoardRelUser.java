package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the kj_board_rel_user database table.
 * 
 */
@Entity
@Table(name="kj_board_rel_user")
@NamedQuery(name="KjBoardRelUser.findAll", query="SELECT k FROM KjBoardRelUser k")
public class KjBoardRelUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KjBoardRelUserPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="user_status_kbn")
	private String userStatusKbn;

	//bi-directional many-to-one association to KjBoardGroupTbl
	@ManyToOne
	@JoinColumn(name="board_group_key", insertable=false, updatable=false)
	private KjBoardGroupTbl kjBoardGroupTbl;

	public KjBoardRelUser() {
	}

	public KjBoardRelUserPK getId() {
		return this.id;
	}

	public void setId(KjBoardRelUserPK id) {
		this.id = id;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUserKey() {
		return this.updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public String getUserStatusKbn() {
		return this.userStatusKbn;
	}

	public void setUserStatusKbn(String userStatusKbn) {
		this.userStatusKbn = userStatusKbn;
	}

	public KjBoardGroupTbl getKjBoardGroupTbl() {
		return this.kjBoardGroupTbl;
	}

	public void setKjBoardGroupTbl(KjBoardGroupTbl kjBoardGroupTbl) {
		this.kjBoardGroupTbl = kjBoardGroupTbl;
	}

}