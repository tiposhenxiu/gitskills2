/*
* ファイル名：RubricUploadForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/05   etoh        新規作成
*/
package jp.co.sraw.controller.skill;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import jp.co.sraw.common.CommonForm;

/**
 * <B>ルーブリックアップロード用のフォーム</B>
 * <P>
 * ルーブリック編集画面でルーブリックXMLをアップロードするときに使う。
 */
public class RubricUploadForm extends CommonForm {
	private String key;
	private Timestamp updDate;
	private MultipartFile doc;

	public MultipartFile getDoc() {
		return doc;
	}

	public void setDoc(MultipartFile doc) {
		this.doc = doc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
