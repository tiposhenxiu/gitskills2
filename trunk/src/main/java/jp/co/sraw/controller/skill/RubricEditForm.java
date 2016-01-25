/*
* ファイル名：RubricEditForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/05   etoh        新規作成
*/
package jp.co.sraw.controller.skill;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonForm;
import jp.co.sraw.oxm.rubric.Rubric;

/**
 * <B>ルーブリック編集用のフォーム</B>
 * <P>
 */
public class RubricEditForm extends CommonForm {

	private String key;
	@NotBlank
	private String name;
	@NotBlank
	private String summary;
	private Rubric rubric;
	private Timestamp updDate;
	private String updUserKey;

	public RubricEditForm() {
	}

	public RubricEditForm(CommonForm selForm) {
		setPageMode(selForm.getPageMode());
		if (getPageMode().equals(CommonConst.PAGE_MODE_ADD)) {
			setPageActionUrl(CommonConst.ACTION_URL_CREATE);
		} else {
			setPageActionUrl(CommonConst.ACTION_URL_UPDATE);
		}
	}

	/**
	 * 新規作成ならtrue, 既存編集ならfalse。Thymeleaf内で使う便利メソッド。
	 *
	 * @return
	 */
	public boolean isCreating() {
		return getPageMode().equals(CommonConst.PAGE_MODE_ADD);
	}

	/**
	 * POSTデータとRubricEditFormの構造の違いを調整する。
	 *
	 */
	public void normalize() {
		// itemのphaseListの要素数は可変だが、
		// POSTデータとしては、常に固定数が返ってくる。
		// それを可変に戻す。
		rubric.getCategoryList().forEach(
				cat -> cat.getChildList().forEach(subc -> subc.getChildList().forEach(item -> item.normalize())));
	}

	// ------------------ これ以降、getter/setterです。

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUserKey() {
		return updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public Rubric getRubric() {
		return rubric;
	}

	public void setRubric(Rubric rubric) {
		this.rubric = rubric;
	}

}
