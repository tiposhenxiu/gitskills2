package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_career_tbl database table.
 *
 */
@Entity
@Table(name = "gy_career_tbl")
@NamedQuery(name = "GyCareerTbl.findAll", query = "SELECT g FROM GyCareerTbl g")
public class GyCareerTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "career_language")
	private String careerLanguage;

	private String division;

	private String fromdate;

	private String job;

	private String section;

	private String todate;

	public GyCareerTbl() {
	}

	public String getCareerLanguage() {
		return this.careerLanguage;
	}

	public void setCareerLanguage(String careerLanguage) {
		this.careerLanguage = careerLanguage;
	}

	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getTodate() {
		return this.todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

}