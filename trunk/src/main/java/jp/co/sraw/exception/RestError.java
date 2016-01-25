/*
* ファイル名：RestError.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.exception;

/**
* <B>RestErrorクラス</B>
* <P>
* Restエラーのメソッドを提供する
*/
public class RestError {

	/**
	 * 時刻
	 */
	private String time;
	/**
	 * URI
	 */
	private String path;
	/**
	 * エラー名
	 */
	private String error;
	/**
	 * HTTPステータス
	 */
	private int status;
	/**
	 * メッセージ
	 */
	private String message;
	/**
	 * 例外名
	 */
	private String exception;
	/**
	 * @return path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path セットする path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error セットする error
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status セットする status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message セットする message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return exception
	 */
	public String getException() {
		return exception;
	}
	/**
	 * @param exception セットする exception
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
	/**
	 * @return time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time セットする time
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
