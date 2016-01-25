package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ms_research_area_tbl database table.
 * 
 */
@Entity
@Table(name="ms_research_area_tbl")
@NamedQuery(name="MsResearchAreaTbl.findAll", query="SELECT m FROM MsResearchAreaTbl m")
public class MsResearchAreaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="research_area_code")
	private String researchAreaCode;

	@Column(name="code_f")
	private String codeF;

	@Column(name="field_code")
	private String fieldCode;

	@Column(name="research_area_name")
	private String researchAreaName;

	@Column(name="research_area_name_en")
	private String researchAreaNameEn;

	@Column(name="subject_code")
	private String subjectCode;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="use_flag")
	private String useFlag;

	public MsResearchAreaTbl() {
	}

	public String getResearchAreaCode() {
		return this.researchAreaCode;
	}

	public void setResearchAreaCode(String researchAreaCode) {
		this.researchAreaCode = researchAreaCode;
	}

	public String getCodeF() {
		return this.codeF;
	}

	public void setCodeF(String codeF) {
		this.codeF = codeF;
	}

	public String getFieldCode() {
		return this.fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getResearchAreaName() {
		return this.researchAreaName;
	}

	public void setResearchAreaName(String researchAreaName) {
		this.researchAreaName = researchAreaName;
	}

	public String getResearchAreaNameEn() {
		return this.researchAreaNameEn;
	}

	public void setResearchAreaNameEn(String researchAreaNameEn) {
		this.researchAreaNameEn = researchAreaNameEn;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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

	public String getUseFlag() {
		return this.useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

}