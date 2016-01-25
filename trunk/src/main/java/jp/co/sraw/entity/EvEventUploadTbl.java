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
 * The persistent class for the ev_event_upload_tbl database table.
 *
 */
@Entity
@Table(name="ev_event_upload_tbl")
@NamedQuery(name="EvEventUploadTbl.findAll", query="SELECT e FROM EvEventUploadTbl e")
public class EvEventUploadTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EvEventUploadTblPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to EvEventTbl
	@ManyToOne
	@JoinColumn(name="event_key", insertable=false, updatable=false)
	private EvEventTbl evEventTbl;

	public EvEventUploadTbl() {
	}

	public EvEventUploadTblPK getId() {
		return this.id;
	}

	public void setId(EvEventUploadTblPK id) {
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

	public EvEventTbl getEvEventTbl() {
		return this.evEventTbl;
	}

	public void setEvEventTbl(EvEventTbl evEventTbl) {
		this.evEventTbl = evEventTbl;
	}

}