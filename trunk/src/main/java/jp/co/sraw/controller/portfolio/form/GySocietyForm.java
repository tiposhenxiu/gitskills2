/*
* ファイル名：GySocietyForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.GySocietyTbl;

/**
 * <B>GySocietyFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class GySocietyForm extends PortfolioForm {

	public GySocietyForm() {
		super();
	}

	public String getSocietyLanguage() {
		return this.getLanguage();
	}

	public void setSocietyLanguage(String societyLanguage) {
		this.setLanguage(societyLanguage);
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
		// TODO 自動生成されたメソッド・スタブ
		return new GySocietyTbl();
	}

}
