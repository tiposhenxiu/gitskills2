/*
* ファイル名：OthersForm.java
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
import jp.co.sraw.entity.GyPatentTbl;

/**
 * <B>GyPatentFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyPatentForm extends PortfolioForm {

	public GyPatentForm() {
		super();
	}

	public String getPatentLanguage() {
		return this.getLanguage();
	}

	public void setPatentLanguage(String patentLanguage) {
		this.setLanguage(patentLanguage);
	}

	@Pattern(regexp = "[0-9]*")
	@CharLength(max = 8)
	private String applicationdate;

	@CharLength(max = 64)
	private String applicationid;

	@CharLength(max = 255)
	private String applicationperson;

	@CharLength(max = 1200)
	private String author;

	@Pattern(regexp = "[0-9]*")
	@CharLength(max = 8)
	private String patentdate;

	@CharLength(max = 64)
	private String patentid;

	@Pattern(regexp = "[0-9]*")
	@CharLength(max = 8)
	private String publicdate;

	private String publicid;

	@Pattern(regexp = "[0-9]*")
	@CharLength(max = 8)
	private String translationdate;

	@CharLength(max = 64)
	private String translationid;

	@NotNull
	private String kbn;

	public String getKbn() {
		return getContent(this.kbn, "0017");
	}

	public void setKbn(String kbn) {
		this.kbn = kbn;
	}

	@NotNull
	@CharLength(max = 64)
	private String bango;

	public String getBango() {
		return this.bango;
	}

	public void setBango(String bango) {
		this.bango = bango;
	}

	@NotNull
	@Pattern(regexp = "[0-9]*")
	private String busday;

	public String getBusday() {
		return this.busday;
	}

	public void setBusday(String busday) {
		this.busday = busday;
	}

	@NotNull
	@CharLength(max = 1200)
	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyPatentTbl();
	}

}
