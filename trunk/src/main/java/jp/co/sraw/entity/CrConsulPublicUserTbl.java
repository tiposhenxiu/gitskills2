package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cr_consul_public_user_tbl database table.
 *
 */
@Entity
@Table(name="cr_consul_public_user_tbl")
@NamedQuery(name="CrConsulPublicUserTbl.findAll", query="SELECT c FROM CrConsulPublicUserTbl c")
public class CrConsulPublicUserTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="consultation_key")
	private String consultationKey;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to CrConsulTbl
	@ManyToOne
	@JoinColumn(name="consultation_key", insertable=false, updatable=false)
	private CrConsulTbl crConsulTbl;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key")
	private UsUserTbl usUserTbl;

	public CrConsulPublicUserTbl() {
	}

	public String getConsultationKey() {
		return this.consultationKey;
	}

	public void setConsultationKey(String consultationKey) {
		this.consultationKey = consultationKey;
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

	public CrConsulTbl getCrConsulTbl() {
		return this.crConsulTbl;
	}

	public void setCrConsulTbl(CrConsulTbl crConsulTbl) {
		this.crConsulTbl = crConsulTbl;
	}

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}