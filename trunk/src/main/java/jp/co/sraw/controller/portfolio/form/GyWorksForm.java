/*
* ファイル名：GyWorksForm.java
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
import jp.co.sraw.entity.GyWorksTbl;

/**
 * <B>GySocietyFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyWorksForm extends PortfolioForm {

	public GyWorksForm() {
		super();
	}

	public String getWorksLanguage() {
		return this.getLanguage();
	}

	public void setWorksLanguage(String worksLanguage) {
		this.setLanguage(worksLanguage);
	}

	@CharLength(max = 3000)
	private String author;

	@CharLength(max = 8)
	@Pattern(regexp = "[0-9]*")
	private String fromdate;

	@CharLength(max = 8)
	@Pattern(regexp = "[0-9]*")
	private String todate;

	@CharLength(max = 255)
	private String venue;

	@CharLength(max = 1)
	private String worktype;

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

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return this.todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getVenue() {
		return this.venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getWorktype() {
		return getContent(this.worktype, "0213");
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyWorksTbl();
	}

}
