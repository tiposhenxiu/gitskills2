package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_degree_tbl database table.
 *
 */
@Entity
@Table(name = "gy_degree_tbl")
@NamedQuery(name = "GyDegreeTbl.findAll", query = "SELECT g FROM GyDegreeTbl g")
public class GyDegreeTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "degree_language")
	private String degreeLanguage;

	private String degreename;

	private String organization;

	public GyDegreeTbl() {
	}

	public String getDegreeLanguage() {
		return this.degreeLanguage;
	}

	public void setDegreeLanguage(String degreeLanguage) {
		this.degreeLanguage = degreeLanguage;
	}

	public String getDegreename() {
		return this.degreename;
	}

	public void setDegreename(String degreename) {
		this.degreename = degreename;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}