/*
* ファイル名：GyAcademicForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.entity.GyAcademicTbl;
import jp.co.sraw.entity.GyCommonTbl;

/**
 * <B>GyAcademicFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyAcademicForm extends PortfolioForm {

	public GyAcademicForm() {
		super();
	}

	public String getAcademicLanguage() {
		return this.getLanguage();
	}

	public void setAcademicLanguage(String academicLanguage) {
		this.setLanguage(academicLanguage);
	}

	@CharLength(max = 255)
	private String country;

	@CharLength(max = 255)
	private String departmentname;

	@CharLength(max = 255)
	private String subjectname;

	@CharLength(max = 8)
	@Pattern(regexp = "[0-9]*")
	private String fromdate;

	@CharLength(max = 8)
	@Pattern(regexp = "[0-9]*")
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDepartmentname() {
		return this.departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
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
		return new GyAcademicTbl();
	}

}
