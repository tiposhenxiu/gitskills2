/*
* ファイル名：GyBiblioForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.entity.GyBiblioTbl;
import jp.co.sraw.entity.GyCommonTbl;

/**
 * <B>GyBiblioFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyBiblioForm extends PortfolioForm {

	public GyBiblioForm() {
		super();
	}

	public String getBiblioLanguage() {
		return this.getLanguage();
	}

	public void setBiblioLanguage(String biblioLanguage) {
		this.setLanguage(biblioLanguage);
	}

	@CharLength(max = 3000)
	private String author;

	private String authortypeid;

	@CharLength(max = 100)
	private String isbn;

	private String wlanguage;

	@CharLength(max = 8)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String publicationdate;

	@CharLength(max = 255)
	private String publisher;

	private BigDecimal reppagenumber;

	private BigDecimal totalpagenumber;

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

	public String getAuthortypeid() {
		return this.authortypeid;
	}

	public void setAuthortypeid(String authortypeid) {
		this.authortypeid = authortypeid;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getWlanguage() {
		return this.wlanguage;
	}

	public void setWlanguage(String wlanguage) {
		this.wlanguage = wlanguage;
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

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyBiblioTbl();
	}

}
