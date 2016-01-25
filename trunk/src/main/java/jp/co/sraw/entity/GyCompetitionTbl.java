package jp.co.sraw.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_competition_tbl database table.
 *
 */
@Entity
@Table(name = "gy_competition_tbl")
@NamedQuery(name = "GyCompetitionTbl.findAll", query = "SELECT g FROM GyCompetitionTbl g")
public class GyCompetitionTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private BigDecimal amounttotal;

	private String author;

	private String category;

	@Column(name = "competition_language")
	private String competitionLanguage;

	private String field;

	private String fromdate;

	private String member;

	private String provider;

	private String system;

	private String todate;

	public GyCompetitionTbl() {
	}

	public BigDecimal getAmounttotal() {
		return this.amounttotal;
	}

	public void setAmounttotal(BigDecimal amounttotal) {
		this.amounttotal = amounttotal;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompetitionLanguage() {
		return this.competitionLanguage;
	}

	public void setCompetitionLanguage(String competitionLanguage) {
		this.competitionLanguage = competitionLanguage;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getMember() {
		return this.member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getTodate() {
		return this.todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}
}