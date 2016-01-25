package jp.co.sraw.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_biblio_tbl database table.
 *
 */
@Entity
@Table(name = "gy_biblio_tbl")
@NamedQuery(name = "GyBiblioTbl.findAll", query = "SELECT g FROM GyBiblioTbl g")
public class GyBiblioTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String author;

	private String authortypeid;

	@Column(name = "biblio_language")
	private String biblioLanguage;

	private String isbn;

	private String language;

	private String publicationdate;

	private String publisher;

	private BigDecimal reppagenumber;

	private BigDecimal totalpagenumber;

	public GyBiblioTbl() {
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthortypeid() {
		return this.authortypeid;
	}

	public void setAuthortypeid(String authortypeid) {
		this.authortypeid = authortypeid;
	}

	public String getBiblioLanguage() {
		return this.biblioLanguage;
	}

	public void setBiblioLanguage(String biblioLanguage) {
		this.biblioLanguage = biblioLanguage;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getWlanguage() {
		return this.language;
	}

	public void setWlanguage(String language) {
		this.language = language;
	}

	public String getPublicationdate() {
		return this.publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public BigDecimal getReppagenumber() {
		return this.reppagenumber;
	}

	public void setReppagenumber(BigDecimal reppagenumber) {
		this.reppagenumber = reppagenumber;
	}

	public BigDecimal getTotalpagenumber() {
		return this.totalpagenumber;
	}

	public void setTotalpagenumber(BigDecimal totalpagenumber) {
		this.totalpagenumber = totalpagenumber;
	}

}