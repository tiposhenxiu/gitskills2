package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;


/**
 * The persistent class for the sp_support_tbl database table.
 * 
 */
@Entity
@Table(name="sp_support_tbl")
@NamedQuery(name="SpSupportTbl.findAll", query="SELECT s FROM SpSupportTbl s")
public class SpSupportTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="SP_SUPPORT_TBL_SUPPORTKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="SP_SUPPORT_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SP_SUPPORT_TBL_SUPPORTKEY_GENERATOR")
	@Column(name="support_key")
	private String supportKey;

	@Column(name="batch_status")
	private String batchStatus;

	@Column(name="party_code")
	private String partyCode;

	@Column(name="public_flag")
	private String publicFlag;

	@Column(name="support_area_kbn")
	private String supportAreaKbn;

	@Column(name="support_content")
	private String supportContent;

	@Column(name="support_end_date")
	private Timestamp supportEndDate;

	@Column(name="support_hiraku_kbn")
	private String supportHirakuKbn;

	@Column(name="support_ins_date")
	private Timestamp supportInsDate;

	@Column(name="support_keyword")
	private String supportKeyword;

	@Column(name="support_spkiki_kbn")
	private String supportSpkikiKbn;

	@Column(name="support_start_date")
	private Timestamp supportStartDate;

	@Column(name="support_syb_code")
	private String supportSybCode;

	@Column(name="support_title")
	private String supportTitle;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	private String url;

	public SpSupportTbl() {
	}

	public String getSupportKey() {
		return this.supportKey;
	}

	public void setSupportKey(String supportKey) {
		this.supportKey = supportKey;
	}

	public String getBatchStatus() {
		return this.batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}

	public String getPartyCode() {
		return this.partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getSupportAreaKbn() {
		return this.supportAreaKbn;
	}

	public void setSupportAreaKbn(String supportAreaKbn) {
		this.supportAreaKbn = supportAreaKbn;
	}

	public String getSupportContent() {
		return this.supportContent;
	}

	public void setSupportContent(String supportContent) {
		this.supportContent = supportContent;
	}

	public Timestamp getSupportEndDate() {
		return this.supportEndDate;
	}

	public void setSupportEndDate(Timestamp supportEndDate) {
		this.supportEndDate = supportEndDate;
	}

	public String getSupportHirakuKbn() {
		return this.supportHirakuKbn;
	}

	public void setSupportHirakuKbn(String supportHirakuKbn) {
		this.supportHirakuKbn = supportHirakuKbn;
	}

	public Timestamp getSupportInsDate() {
		return this.supportInsDate;
	}

	public void setSupportInsDate(Timestamp supportInsDate) {
		this.supportInsDate = supportInsDate;
	}

	public String getSupportKeyword() {
		return this.supportKeyword;
	}

	public void setSupportKeyword(String supportKeyword) {
		this.supportKeyword = supportKeyword;
	}

	public String getSupportSpkikiKbn() {
		return this.supportSpkikiKbn;
	}

	public void setSupportSpkikiKbn(String supportSpkikiKbn) {
		this.supportSpkikiKbn = supportSpkikiKbn;
	}

	public Timestamp getSupportStartDate() {
		return this.supportStartDate;
	}

	public void setSupportStartDate(Timestamp supportStartDate) {
		this.supportStartDate = supportStartDate;
	}

	public String getSupportSybCode() {
		return this.supportSybCode;
	}

	public void setSupportSybCode(String supportSybCode) {
		this.supportSybCode = supportSybCode;
	}

	public String getSupportTitle() {
		return this.supportTitle;
	}

	public void setSupportTitle(String supportTitle) {
		this.supportTitle = supportTitle;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}