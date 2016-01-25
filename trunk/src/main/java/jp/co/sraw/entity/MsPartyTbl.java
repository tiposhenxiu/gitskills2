package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ms_party_tbl database table.
 * 
 */
@Entity
@Table(name="ms_party_tbl")
@NamedQuery(name="MsPartyTbl.findAll", query="SELECT m FROM MsPartyTbl m")
public class MsPartyTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="party_code")
	private String partyCode;

	private String domain;

	@Column(name="entity_id")
	private String entityId;

	@Column(name="party_kbn")
	private String partyKbn;

	@Column(name="party_name")
	private String partyName;

	@Column(name="party_name_abbr")
	private String partyNameAbbr;

	@Column(name="party_name_abbr_en")
	private String partyNameAbbrEn;

	@Column(name="party_name_en")
	private String partyNameEn;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	public MsPartyTbl() {
	}

	public String getPartyCode() {
		return this.partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEntityId() {
		return this.entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getPartyKbn() {
		return this.partyKbn;
	}

	public void setPartyKbn(String partyKbn) {
		this.partyKbn = partyKbn;
	}

	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyNameAbbr() {
		return this.partyNameAbbr;
	}

	public void setPartyNameAbbr(String partyNameAbbr) {
		this.partyNameAbbr = partyNameAbbr;
	}

	public String getPartyNameAbbrEn() {
		return this.partyNameAbbrEn;
	}

	public void setPartyNameAbbrEn(String partyNameAbbrEn) {
		this.partyNameAbbrEn = partyNameAbbrEn;
	}

	public String getPartyNameEn() {
		return this.partyNameEn;
	}

	public void setPartyNameEn(String partyNameEn) {
		this.partyNameEn = partyNameEn;
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

}