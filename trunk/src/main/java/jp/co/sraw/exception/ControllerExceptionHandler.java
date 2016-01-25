/*
* ファイル名：ControllerExceptionHandler.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.exception;

import java.sql.Timestamp;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;

/**
* <B>ControllerExceptionHandler</B>
* <P>
* Httpエラーのハンドラーを提供する
*/
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	/** Logger */
	private final LoggerWrapper logger = LoggerWrapperFactory.getLogger(getClass());

	@ExceptionHandler(value = ApplicationException.class)
	@ResponseBody
	public ResponseEntity<RestError> handleAppException(HttpServletRequest request, ApplicationException ex) {
		return handleError(request, ex.getError(), ex, ex.getArgs());
	}

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public ResponseEntity<RestError> handleException(HttpServletRequest request, RuntimeException ex) {
		return handleError(request, Errors.UNEXPECTED, ex, ex.toString());
	}


	/**
	 * Restエラーハンドラー
	 * 同じ結果を返すためのメソッド
	 *
	 * @param request
	 * @param error
	 * @param ex
	 * @param args
	 * @return
	 */
	protected ResponseEntity<RestError> handleError(HttpServletRequest request, HttpErrors error, Exception ex, Object... args) {
		String message = MessageFormat.format(error.getMessage(), args);
		if (error.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
			logger.error(message, ex);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(message, ex);
			}
		}
		if (error.getStatus() == HttpStatus.UNAUTHORIZED) {
			return new ResponseEntity<>(error.getStatus());
		}
		RestError restError = new RestError();
		restError.setTime((new Timestamp(System.currentTimeMillis())).toString());
		restError.setPath(request.getRequestURI());
		restError.setError(error.name());
		restError.setStatus(error.getStatus().value());
		restError.setMessage(message);
		restError.setException(ex.getClass().getName());
		return new ResponseEntity<>(restError, error.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		RestError restError = new RestError();
		if (request instanceof ServletWebRequest) {
			restError.setPath(((ServletWebRequest) request).getRequest() .getRequestURI());
		} else {
			restError.setPath(request.getContextPath());
		}
		restError.setTime((new Timestamp(System.currentTimeMillis())).toString());
		restError.setError(status.getReasonPhrase());
		restError.setStatus(status.value());
		restError.setMessage(ex.getMessage());
		restError.setException(ex.getClass().getName());
		return new ResponseEntity<>(restError, status);
	}
}
