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
 * The persistent class for the it_intern_public_tbl database table.
 * 
 */
@Entity
@Table(name="it_intern_public_tbl")
@NamedQuery(name="ItInternPublicTbl.findAll", query="SELECT i FROM ItInternPublicTbl i")
public class ItInternPublicTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItInternPublicTblPK id;

	@Column(name="party_code")
	private String partyCode;

	@Column(name="public_kbn")
	private String publicKbn;

	private String role;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;
	
	//bi-directional many-to-one association to ItInternTbl
	@ManyToOne
	@JoinColumn(name="internship_key", insertable=false, updatable=false)
	private ItInternTbl itInternTbl;

	public ItInternPublicTbl() {
	}

	public ItInternPublicTblPK getId() {
		return this.id;
	}

	public void setId(ItInternPublicTblPK id) {
		this.id = id;
	}

	public String getPartyCode() {
		return this.partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPublicKbn() {
		return this.publicKbn;
	}

	public void setPublicKbn(String publicKbn) {
		this.publicKbn = publicKbn;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
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
	public ItInternTbl getItInternTbl() {
		return this.itInternTbl;
	}

	public void setItInternTbl(ItInternTbl itInternTbl) {
		this.itInternTbl = itInternTbl;
	}
}