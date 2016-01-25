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
 * The persistent class for the nr_rubric_rel_user_tbl database table.
 *
 */
@Entity
@Table(name="nr_rubric_rel_user_tbl")
@NamedQuery(name="NrRubricRelUserTbl.findAll", query="SELECT n FROM NrRubricRelUserTbl n")
public class NrRubricRelUserTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrRubricRelUserTblPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrRubricTbl
	@ManyToOne
	@JoinColumn(name="rubric_key", insertable=false, updatable=false)
	private NrRubricTbl nrRubricTbl;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key", insertable=false, updatable=false)
	private UsUserTbl usUserTbl;

	public NrRubricRelUserTbl() {
	}

	public NrRubricRelUserTblPK getId() {
		return this.id;
	}

	public void setId(NrRubricRelUserTblPK id) {
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

	public NrRubricTbl getNrRubricTbl() {
		return this.nrRubricTbl;
	}

	public void setNrRubricTbl(NrRubricTbl nrRubricTbl) {
		this.nrRubricTbl = nrRubricTbl;
	}

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}