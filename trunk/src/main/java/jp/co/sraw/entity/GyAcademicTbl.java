package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_academic_tbl database table.
 *
 */
@Entity
@Table(name = "gy_academic_tbl")
@NamedQuery(name = "GyAcademicTbl.findAll", query = "SELECT g FROM GyAcademicTbl g")
public class GyAcademicTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "academic_language")
	private String academicLanguage;

	private String country;

	private String departmentname;

	private String fromdate;

	private String subjectname;

	private String todate;

	public GyAcademicTbl() {
	}

	public String getAcademicLanguage() {
		return this.academicLanguage;
	}

	public void setAcademicLanguage(String academicLanguage) {
		this.academicLanguage = academicLanguage;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDepartmentname() {
		return this.departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getTodate() {
		return this.todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}
}