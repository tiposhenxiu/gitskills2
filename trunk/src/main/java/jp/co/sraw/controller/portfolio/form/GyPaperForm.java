/*
* ファイル名：PaperForm.java
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
import jp.co.sraw.entity.GyPaperTbl;

/**
 * <B>GyPaperFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyPaperForm extends PortfolioForm {

	@NotNull
	@CharLength(max = 3000)
	private String author;

	@NotNull
	@CharLength(max = 100)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String doi;

	@NotNull
	@CharLength(max = 30)
	private String endingpage;

	@NotNull
	@CharLength(max = 255)
	private String journal;

	@NotNull
	private String wlanguage;

	@NotNull
	@CharLength(max = 30)
	private String number;

	@NotNull
	private String papertypeid;

	@CharLength(max = 1)
	private String referee;

	@NotNull
	@CharLength(max = 30)
	private String startingpage;

	@NotNull
	@CharLength(max = 30)
	private String volume;

	@NotNull
	@CharLength(max = 8)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String publicationdate;

	@NotNull
	@CharLength(max = 255)
	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return getContent(this.wlanguage, "0206");
	}

	public void setWlanguage(String wlanguage) {
		this.wlanguage = wlanguage;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPaperLanguage() {
		return this.getLanguage();
	}

	public void setPaperLanguage(String paperLanguage) {
		this.setLanguage(paperLanguage);
	}

	public String getPapertypeid() {
		return getContent(this.papertypeid, "0203");
	}

	public void setPapertypeid(String papertypeid) {
		this.papertypeid = papertypeid;
	}

	public String getReferee() {
		return getContent(this.referee, "0204");
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

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getPublicationdate() {
		return this.publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyPaperTbl();
	}
}
