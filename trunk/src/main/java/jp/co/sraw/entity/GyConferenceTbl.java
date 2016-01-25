package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_conference_tbl database table.
 *
 */
@Entity
@Table(name = "gy_conference_tbl")
@NamedQuery(name = "GyConferenceTbl.findAll", query = "SELECT g FROM GyConferenceTbl g")
public class GyConferenceTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String author;

	@Column(name = "conference_language")
	private String conferenceLanguage;

	private String conferenceclass;

	private String conferencetype;

	private String invited;

	private String journal;

	private String language;

	private String promoter;

	private String publicationdate;

	private String venue;

	public GyConferenceTbl() {
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getConferenceLanguage() {
		return this.conferenceLanguage;
	}

	public void setConferenceLanguage(String conferenceLanguage) {
		this.conferenceLanguage = conferenceLanguage;
	}

	public String getConferenceclass() {
		return this.conferenceclass;
	}

	public void setConferenceclass(String conferenceclass) {
		this.conferenceclass = conferenceclass;
	}

	public String getConferencetype() {
		return this.conferencetype;
	}

	public void setConferencetype(String conferencetype) {
		this.conferencetype = conferencetype;
	}

	public String getInvited() {
		return this.invited;
	}

	public void setInvited(String invited) {
		this.invited = invited;
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

	public String getPromoter() {
		return this.promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	public String getPublicationdate() {
		return this.publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getVenue() {
		return this.venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
}