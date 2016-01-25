package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_patent_tbl database table.
 *
 */
@Entity
@Table(name = "gy_patent_tbl")
@NamedQuery(name = "GyPatentTbl.findAll", query = "SELECT g FROM GyPatentTbl g")
public class GyPatentTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String applicationdate;

	private String applicationid;

	private String applicationperson;

	private String author;

	@Column(name = "patent_language")
	private String patentLanguage;

	private String patentdate;

	private String patentid;

	private String publicdate;

	private String publicid;

	private String translationdate;

	private String translationid;

	public GyPatentTbl() {
	}

	public String getApplicationdate() {
		return this.applicationdate;
	}

	public void setApplicationdate(String applicationdate) {
		this.applicationdate = applicationdate;
	}

	public String getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getApplicationperson() {
		return this.applicationperson;
	}

	public void setApplicationperson(String applicationperson) {
		this.applicationperson = applicationperson;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPatentLanguage() {
		return this.patentLanguage;
	}

	public void setPatentLanguage(String patentLanguage) {
		this.patentLanguage = patentLanguage;
	}

	public String getPatentdate() {
		return this.patentdate;
	}

	public void setPatentdate(String patentdate) {
		this.patentdate = patentdate;
	}

	public String getPatentid() {
		return this.patentid;
	}

	public void setPatentid(String patentid) {
		this.patentid = patentid;
	}

	public String getPublicdate() {
		return this.publicdate;
	}

	public void setPublicdate(String publicdate) {
		this.publicdate = publicdate;
	}

	public String getPublicid() {
		return this.publicid;
	}

	public void setPublicid(String publicid) {
		this.publicid = publicid;
	}

	public String getTranslationdate() {
		return this.translationdate;
	}

	public void setTranslationdate(String translationdate) {
		this.translationdate = translationdate;
	}

	public String getTranslationid() {
		return this.translationid;
	}

	public void setTranslationid(String translationid) {
		this.translationid = translationid;
	}
}