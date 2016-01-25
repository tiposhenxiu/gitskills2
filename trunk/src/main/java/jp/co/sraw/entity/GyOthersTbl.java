package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_others_tbl database table.
 *
 */
@Entity
@Table(name = "gy_others_tbl")
@NamedQuery(name = "GyOthersTbl.findAll", query = "SELECT g FROM GyOthersTbl g")
public class GyOthersTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "others_language")
	private String othersLanguage;

	private String publicationdate;

	private String summary;

	public GyOthersTbl() {
	}

	public String getOthersLanguage() {
		return this.othersLanguage;
	}

	public void setOthersLanguage(String othersLanguage) {
		this.othersLanguage = othersLanguage;
	}

	public String getPublicationdate() {
		return this.publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}