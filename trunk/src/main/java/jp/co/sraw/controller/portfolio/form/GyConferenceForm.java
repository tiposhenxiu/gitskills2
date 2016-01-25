/*
* ファイル名：GyConferenceForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.GyConferenceTbl;

/**
 * <B>GyConferenceFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyConferenceForm extends PortfolioForm {

	public GyConferenceForm() {
		super();
	}

	public String getResearchKeywordLanguage() {
		return this.getLanguage();
	}

	public void setResearchKeywordLanguage(String researchKeywordLanguage) {
		this.setLanguage(researchKeywordLanguage);
	}

	@NotNull
	@CharLength(max = 255)
	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@CharLength(max = 3000)
	private String author;

	@CharLength(max = 1)
	private String conferenceclass;

	@CharLength(max = 1)
	private String conferencetype;

	@CharLength(max = 1)
	private String invited;

	@CharLength(max = 255)
	private String journal;

	@CharLength(max = 1)
	private String language;

	@CharLength(max = 255)
	private String promoter;

	@CharLength(max = 8)
	@Pattern(regexp = "[0-9]*")
	private String publicationdate;

	@CharLength(max = 255)
	private String venue;

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getConferenceLanguage() {
		return this.getLanguage();
	}

	public void setConferenceLanguage(String conferenceLanguage) {
		this.setLanguage(conferenceLanguage);
	}

	public String getConferenceclass() {
		return getContent(this.conferenceclass, "0209");
	}

	public void setConferenceclass(String conferenceclass) {
		this.conferenceclass = conferenceclass;
	}

	public String getConferencetype() {
		return getContent(this.conferencetype, "0210");
	}

	public void setConferencetype(String conferencetype) {
		this.conferencetype = conferencetype;
	}

	public String getInvited() {
		return this.getContent(this.invited, "0208");
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
		return getContent(this.language, "0206");
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

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyConferenceTbl();
	}

}
