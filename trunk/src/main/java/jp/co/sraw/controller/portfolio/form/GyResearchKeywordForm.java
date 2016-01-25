/*
* ファイル名：GyResearchKeywordForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.GyResearchKeywordTbl;

/**
 * <B>GyResearchKeywordFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GyResearchKeywordForm extends PortfolioForm {

	public GyResearchKeywordForm() {
		super();
	}

	public String getResearchKeywordLanguage() {
		return this.getLanguage();
	}

	public void setResearchKeywordLanguage(String researchKeywordLanguage) {
		this.setLanguage(researchKeywordLanguage);
	}

	@NotNull
	@CharLength(max = 255)
	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public GyCommonTbl getNewTbl() {
		return new GyResearchKeywordTbl();
	}

}
