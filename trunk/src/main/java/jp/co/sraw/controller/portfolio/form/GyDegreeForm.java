/*
* ファイル名：GyDegreeForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.GyDegreeTbl;

/**
 * <B>GyDegreeFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyDegreeForm extends PortfolioForm {

	public GyDegreeForm() {
		super();
	}

	public String getDegreeLanguage() {
		return this.getLanguage();
	}

	public void setDegreeLanguage(String degreeLanguage) {
		this.setLanguage(degreeLanguage);
	}

	@CharLength(max = 255)
	private String degreename;

	@CharLength(max = 255)
	private String organization;

	public String getDegreename() {
		return this.degreename;
	}

	public void setDegreename(String degreename) {
		this.degreename = degreename;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyDegreeTbl();
	}

}
