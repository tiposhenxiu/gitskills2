package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_research_area_tbl database table.
 *
 */
@Entity
@Table(name = "gy_research_area_tbl")
@NamedQuery(name = "GyResearchAreaTbl.findAll", query = "SELECT g FROM GyResearchAreaTbl g")
public class GyResearchAreaTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldid;

	private String fieldname;

	@Column(name = "research_area_language")
	private String researchAreaLanguage;

	private String subjectid;

	private String subjectname;

	private String summary;

	private String summaryid;

	public GyResearchAreaTbl() {
	}

	public String getFieldid() {
		return this.fieldid;
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

	public String getFieldname() {
		return this.fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getResearchAreaLanguage() {
		return this.researchAreaLanguage;
	}

	public void setResearchAreaLanguage(String researchAreaLanguage) {
		this.researchAreaLanguage = researchAreaLanguage;
	}

	public String getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummaryid() {
		return this.summaryid;
	}

	public void setSummaryid(String summaryid) {
		this.summaryid = summaryid;
	}

}