package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the gy_works_tbl database table.
 *
 */
@Entity
@Table(name="gy_works_tbl")
@NamedQuery(name="GyWorksTbl.findAll", query="SELECT g FROM GyWorksTbl g")
public class GyWorksTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String author;

	private String fromdate;

	private String todate;

	private String venue;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="works_language")
	private String worksLanguage;

	private String worktype;

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return this.todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getVenue() {
		return this.venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getWorksLanguage() {
		return this.worksLanguage;
	}

	public void setWorksLanguage(String worksLanguage) {
		this.worksLanguage = worksLanguage;
	}

	public String getWorktype() {
		return this.worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

}