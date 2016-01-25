/*
* ファイル名：RubricSelForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/05   etoh        新規作成
*/
package jp.co.sraw.controller.skill;

import jp.co.sraw.common.CommonForm;

/**
 * <B>ルーブリック選択用のフォーム</B>
 * <P>
 * ルーブリック一覧画面でルーブリックを(EditやDeleteのために)選択するときに使う。
 */
public class RubricSelForm extends CommonForm {
	private String[] keyToDelete;
	private String keyToEdit;

	public String[] getKeyToDelete() {
		return keyToDelete;
	}

	public void setKeyToDelete(String[] keyToDelete) {
		this.keyToDelete = keyToDelete;
	}

	public String getKeyToEdit() {
		return keyToEdit;
	}

	public void setKeyToEdit(String keyToEdit) {
		this.keyToEdit = keyToEdit;
	}

}
