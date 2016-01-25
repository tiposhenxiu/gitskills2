package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_society_tbl database table.
 *
 */
@Entity
@Table(name = "gy_society_tbl")
@NamedQuery(name = "GySocietyTbl.findAll", query = "SELECT g FROM GySocietyTbl g")
public class GySocietyTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "society_language")
	private String societyLanguage;

	public GySocietyTbl() {
	}

	public String getSocietyLanguage() {
		return this.societyLanguage;
	}

	public void setSocietyLanguage(String societyLanguage) {
		this.societyLanguage = societyLanguage;
	}

}