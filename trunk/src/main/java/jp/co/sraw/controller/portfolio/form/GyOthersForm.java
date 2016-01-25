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
import jp.co.sraw.entity.GyOthersTbl;

/**
 * <B>GyOthersFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyOthersForm extends PortfolioForm {

	public GyOthersForm() {
		super();
	}

	@NotNull
	@CharLength(max = 6)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String publicationdate;

	public String getPublicationdate() {
		return this.publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	@NotNull
	@CharLength(max = 255)
	private String summary;

	public String getOthersLanguage() {
		return this.getLanguage();
	}

	public void setOthersLanguage(String othersLanguage) {
		this.setLanguage(othersLanguage);
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public GyCommonTbl getNewTbl() {
		// TODO 自動生成されたメソッド・スタブ
		return new GyOthersTbl();
	}

}
