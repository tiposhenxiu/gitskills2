/*
* ファイル名：GyCompetitionForm.java
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

import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.GyCompetitionTbl;

/**
 * <B>GyCompetitionFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyCompetitionForm extends PortfolioForm {

	public GyCompetitionForm() {
		super();
	}

	public String getCompetitionLanguage() {
		return this.getLanguage();
	}

	public void setCompetitionLanguage(String competitionLanguage) {
		this.setLanguage(competitionLanguage);
	}

	private BigDecimal amounttotal;

	@CharLength(max = 255)
	private String author;

	@CharLength(max = 255)
	private String category;

	@CharLength(max = 255)
	private String competitionLanguage;

	@CharLength(max = 255)
	private String field;

	@CharLength(max = 8)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String fromdate;

	@CharLength(max = 255)
	private String member;

	@CharLength(max = 255)
	private String provider;

	@CharLength(max = 255)
	private String system;

	@CharLength(max = 8)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String todate;

	@NotNull
	@CharLength(max = 255)
	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyCompetitionTbl();
	}

}
