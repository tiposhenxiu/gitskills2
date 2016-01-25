package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the gy_prize_tbl database table.
 *
 */
@Entity
@Table(name="gy_prize_tbl")
@NamedQuery(name="GyPrizeTbl.findAll", query="SELECT g FROM GyPrizeTbl g")
public class GyPrizeTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String association;

	private String country;

	private String partner;

	@Column(name="prize_language")
	private String prizeLanguage;

	private String prizetype;

	private String publicationdate;

	private String subtitle;

	public GyPrizeTbl() {
	}

	public String getAssociation() {
		return this.association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPartner() {
		return this.partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPrizeLanguage() {
		return this.prizeLanguage;
	}

	public void setPrizeLanguage(String prizeLanguage) {
		this.prizeLanguage = prizeLanguage;
	}

	public String getPrizetype() {
		return this.prizetype;
	}

	public void setPrizetype(String prizetype) {
		this.prizetype = prizetype;
	}

	public String getPublicationdate() {
		return this.publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

}