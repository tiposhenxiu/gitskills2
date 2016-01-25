/*
* ファイル名：Errors.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.exception;

import org.springframework.http.HttpStatus;

/**
* <B>Errors enum</B>
* <P>
* Httpエラーのenumを提供する
*/
public enum Errors implements HttpErrors {

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "UserNotFound={0}"),
	UNEXPECTED(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected={0}");

	protected HttpStatus status;

	protected String message;

	Errors(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
