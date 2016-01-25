package jp.co.sraw.controller.skill;

import java.util.List;

/**
 * <B>能力診断の回答用フォーム</B>
 * <P>
 * 全中項目、小項目の回答をまとめる。
 */
public class DiagForm {
	List<AnswerForm> categoryList;

	public List<AnswerForm> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<AnswerForm> categoryList) {
		this.categoryList = categoryList;
	}

}
