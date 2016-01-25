/*
* ファイル名：HttpErrors.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.exception;

import org.springframework.http.HttpStatus;

/**
* <B>HttpErrorsインターフェイス</B>
* <P>
* Httpエラーのインターフェイスを提供する
*/
public interface HttpErrors {
	/**
	 * HTTPステータスを取得します。
	 * @return HTTPステータス
	 */
	HttpStatus getStatus();

	/**
	 * メッセージを取得します。
	 * @return メッセージ
	 */
	String getMessage();

	/**
	 * エラー名を取得します。
	 *  @return エラー名
	 */
	String name();

}
