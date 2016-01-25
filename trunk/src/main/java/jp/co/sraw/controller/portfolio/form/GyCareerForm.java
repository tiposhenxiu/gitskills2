/*
* ファイル名：GyCareerForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import javax.validation.constraints.Pattern;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.entity.GyCareerTbl;
import jp.co.sraw.entity.GyCommonTbl;

/**
 * <B>GyCareerFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyCareerForm extends PortfolioForm {

	public GyCareerForm() {
		super();
	}

	public String getCareerLanguage() {
		return this.getLanguage();
	}

	public void setCareerLanguage(String careerLanguage) {
		this.setLanguage(careerLanguage);
	}

	private String careerLanguage;

	@CharLength(max = 255)
	private String division;

	@CharLength(max = 8)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String fromdate;

	@CharLength(max = 255)
	private String job;

	@CharLength(max = 255)
	private String section;

	@CharLength(max = 8)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String todate;

	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
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
		return new GyCareerTbl();
	}

}
