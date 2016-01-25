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
 * The persistent class for the us_operation_history_tbl database table.
 *
 */
@Entity
@Table(name="us_operation_history_tbl")
@NamedQuery(name="UsOperationHistoryTbl.findAll", query="SELECT u FROM UsOperationHistoryTbl u")
public class UsOperationHistoryTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsOperationHistoryTblPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="operation_user_key", insertable=false, updatable=false)
	private UsUserTbl usUserTbl;

	public UsOperationHistoryTbl() {
	}

	public UsOperationHistoryTblPK getId() {
		return this.id;
	}

	public void setId(UsOperationHistoryTblPK id) {
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

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}