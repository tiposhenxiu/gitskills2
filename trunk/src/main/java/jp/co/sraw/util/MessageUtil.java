/*
* ファイル名：MessageUtil.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.util;

import jp.co.sraw.common.CommonConst;

/**
* <B>メッセージFieldError設定クラス</B>
* <P>
* メッセージFieldError設定関連のスタティックメソッドを提供する
*/


public final class MessageUtil {

	/**
	 * FieldError設定用のメッセージソースのcodesメッセージ一覧生成
	 * DefaultMessageSourceResolvable
	 *
	 * @param fieldName inputフィールド名
	 * @return
	 */
	public static String[] getCodes(String fieldName) {
		return getCodes(fieldName, CommonConst.FORM_NAME);
	}

	/**
	 * FieldError設定用のメッセージソースのcodesメッセージ一覧生成
	 * DefaultMessageSourceResolvable
	 *
	 * @param fieldName inputフィールド名
	 * @param formName Formタグ名
	 * @return
	 */
	public static String[] getCodes(String fieldName, String formName) {
		return new String[]{formName+"."+fieldName, fieldName};
	}

}
