/*
* ファイル名：OthersForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.GyResearchAreaTbl;

/**
 * <B>GyResearchAreaFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyResearchAreaForm extends PortfolioForm {

	public GyResearchAreaForm() {
		super();
	}

	public String getResearchAreaLanguage() {
		return this.getLanguage();
	}

	public void setResearchAreaLanguage(String researchAreaLanguage) {
		this.setLanguage(researchAreaLanguage);
	}

	@CharLength(max = 255)
	private String fieldid;

	@CharLength(max = 255)
	private String fieldname;

	@CharLength(max = 255)
	private String subjectid;

	@CharLength(max = 255)
	private String subjectname;

	@CharLength(max = 255)
	private String summary;

	@CharLength(max = 255)
	private String summaryid;

	public String getFieldid() {
		return getContent(this.fieldid, "0221");
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

	public String getFieldname() {
		return this.fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getSubjectid() {
		return getContent(this.subjectid, "0222");
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummaryid() {
		return this.summaryid;
	}

	public void setSummaryid(String summaryid) {
		this.summaryid = summaryid;
	}

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyResearchAreaTbl();
	}

}
