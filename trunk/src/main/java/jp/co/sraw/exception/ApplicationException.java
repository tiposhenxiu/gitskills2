/*
* ファイル名：ApplicationException.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.exception;

import java.text.MessageFormat;

/**
* <B>ApplicationException</B>
* <P>
* HttpのExceptionエラーの共通化
*/
public class ApplicationException extends RuntimeException {

	Throwable cause;
	Object[] args;
	private HttpErrors error;

	ApplicationException(HttpErrors error, Throwable cause, String... args) {
		super();
		this.error = error;
		this.args = args;
		this.cause = cause;
	}

	/**
	 * Message
	 */
	public String getMessage() {
		if (args != null) {
			return "[" + error.name() + "]" + MessageFormat.format(error.getMessage(), args);
		}
		return "[" + error.name() + "]" + error.getMessage();
	}

	/**
	 * @return cause
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause セットする cause
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	/**
	 * @return args
	 */
	public Object[] getArgs() {
		return args;
	}

	/**
	 * @param args セットする args
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}

	/**
	 * @return error
	 */
	public HttpErrors getError() {
		return error;
	}

	/**
	 * @param error セットする error
	 */
	public void setError(HttpErrors error) {
		this.error = error;
	}
}