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
 * The persistent class for the ev_event_rel_subject_tbl database table.
 *
 */
@Entity
@Table(name="ev_event_rel_subject_tbl")
@NamedQuery(name="EvEventRelSubjectTbl.findAll", query="SELECT e FROM EvEventRelSubjectTbl e")
public class EvEventRelSubjectTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EvEventRelSubjectTblPK id;

	@Column(name="relation_level")
	private String relationLevel;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to EvEventTbl
	@ManyToOne
	@JoinColumn(name="event_key", insertable=false, updatable=false)
	private EvEventTbl evEventTbl;

	public EvEventRelSubjectTbl() {
	}

	public EvEventRelSubjectTblPK getId() {
		return this.id;
	}

	public void setId(EvEventRelSubjectTblPK id) {
		this.id = id;
	}

	public String getRelationLevel() {
		return this.relationLevel;
	}

	public void setRelationLevel(String relationLevel) {
		this.relationLevel = relationLevel;
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