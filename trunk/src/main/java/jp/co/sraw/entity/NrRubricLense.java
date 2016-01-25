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
 * The persistent class for the nr_rubric_lense database table.
 * 
 */
@Entity
@Table(name="nr_rubric_lense")
@NamedQuery(name="NrRubricLense.findAll", query="SELECT n FROM NrRubricLense n")
public class NrRubricLense implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrRubricLensePK id;

	@Column(name="disp_option")
	private String dispOption;

	@Column(name="lense_image_key")
	private String lenseImageKey;

	@Column(name="lense_name")
	private String lenseName;

	private String memo;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrRubricTbl
	@ManyToOne
	@JoinColumn(name="rubric_key", insertable=false, updatable=false)
	private NrRubricTbl nrRubricTbl;
	
	public NrRubricLense() {
	}

	public NrRubricLensePK getId() {
		return this.id;
	}

	public void setId(NrRubricLensePK id) {
		this.id = id;
	}

	public String getDispOption() {
		return this.dispOption;
	}

	public void setDispOption(String dispOption) {
		this.dispOption = dispOption;
	}

	public String getLenseImageKey() {
		return this.lenseImageKey;
	}

	public void setLenseImageKey(String lenseImageKey) {
		this.lenseImageKey = lenseImageKey;
	}

	public String getLenseName() {
		return this.lenseName;
	}

	public void setLenseName(String lenseName) {
		this.lenseName = lenseName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
	
}