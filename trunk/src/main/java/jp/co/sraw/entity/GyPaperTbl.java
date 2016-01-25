package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_paper_tbl database table.
 *
 */
@Entity
@Table(name = "gy_paper_tbl")
@NamedQuery(name = "GyPaperTbl.findAll", query = "SELECT g FROM GyPaperTbl g")
public class GyPaperTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String author;

	private String doi;

	private String endingpage;

	private String journal;

	@Column(name = "language")
	private String language;

	private String number;

	@Column(name = "paper_language")
	private String paperLanguage;

	private String papertypeid;

	private String publicationdate;

	private String referee;

	private String startingpage;

	private String title;

	private String volume;

	public GyPaperTbl() {
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDoi() {
		return this.doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getEndingpage() {
		return this.endingpage;
	}

	public void setEndingpage(String endingpage) {
		this.endingpage = endingpage;
	}

	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getWlanguage() {
		return this.language;
	}

	public void setWlanguage(String language) {
		this.language = language;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPaperLanguage() {
		return this.paperLanguage;
	}

	public void setPaperLanguage(String paperLanguage) {
		this.paperLanguage = paperLanguage;
	}

	public String getPapertypeid() {
		return this.papertypeid;
	}

	public void setPapertypeid(String papertypeid) {
		this.papertypeid = papertypeid;
	}

	public String getPublicationdate() {
		return this.publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getReferee() {
		return this.referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public String getStartingpage() {
		return this.startingpage;
	}

	public void setStartingpage(String startingpage) {
		this.startingpage = startingpage;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

}