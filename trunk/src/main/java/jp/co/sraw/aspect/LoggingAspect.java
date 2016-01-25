/*
* ファイル名：LoggingAspect.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.aspect;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;

/**
* <B>LoggingAspectクラス</B>
* <P>
* Aspectを提供する
*/
@Aspect
@Component
public class LoggingAspect {

	@Autowired
	private MessageSource messageSource;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(LoggingAspect.class);

	private static final String MessageFormat = "{}, {}, {}";

	@PostConstruct
	private void init() {
		logger.setMessageSource(messageSource);
	}

	@Around("execution(* jp.co.sraw..*.*(..)) && (bean(*Controller) || bean(*ServiceImpl) || bean(*Batch))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {

		// 呼び出し前ログ出力
		loggerMessage("###START###", toCall(pjp), "-", LogLevel.DEBUG);

		// Start Time
		long startTime = System.currentTimeMillis();

		Object retVal = null;

		try {

			loggerMessage("###Args###", toCall(pjp), "with args="+ ToStringBuilder.reflectionToString(pjp.getArgs(), ToStringStyle.SHORT_PREFIX_STYLE), LogLevel.DEBUG);

			retVal = pjp.proceed();
			//retVal = pjp.proceed(pjp.getArgs());
			//loggerMessage("-", "-", "with return="+ ToStringBuilder.reflectionToString(retVal, ToStringStyle.SHORT_PREFIX_STYLE), LogLevel.TRACE);

			return retVal;
		} catch (Throwable e) {

			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			return retVal;

		} finally {
			// End Time
			long endTime = System.currentTimeMillis();
			String diffTime = DurationFormatUtils.formatPeriod(startTime, endTime, "HH:mm:ss.SSS");

			// 呼び出し後ログ出力
			loggerMessage("###END###[Time " + diffTime +"]", toCall(pjp), retVal, LogLevel.DEBUG);
		}
	}

	@AfterReturning(pointcut="execution(* jp.co.sraw..*.*(..)) && (bean(*Controller) || bean(*ServiceImpl) || bean(*Batch))", returning= "result")
	public void afterReturning(JoinPoint jp, Object result) {
		loggerMessage("###Returning###", toCall(jp), result, LogLevel.DEBUG);
	}

	@AfterThrowing(pointcut="execution(* jp.co.sraw..*.*(..)) && (bean(*Controller) || bean(*ServiceImpl) || bean(*Batch))", throwing= "error")
	public void afterThrowing(JoinPoint jp, Throwable error) {
		loggerMessage("###Throwing###", toCall(jp), error, LogLevel.ERROR);
	}

	/**
	 * 呼び出しクラスとメソッド
	 *
	 * @param jp
	 * @return
	 */
	private String toCall(JoinPoint jp) {
		String c = jp.getTarget().getClass().getName(); //クラス名
		String m = jp.getSignature().getName(); //メソッド名
		return "Call class="+ c +", method="+ m;
	}

	/**
	 * ログ出力
	 *
	 * @param head
	 * @param call
	 * @param msg
	 * @param lvl
	 */
	private void loggerMessage(String head, String call, Object obj, LogLevel lvl) {
		//logger.infoCode("I0001");  // message.propertiesテスト
		//
		switch (lvl) {
		case TRACE:
			if (logger.isTraceEnabled()) {
				logger.trace(MessageFormat, head, call, obj);
			}
			break;
		case DEBUG:
			if (logger.isDebugEnabled()) {
				logger.debug(MessageFormat, head, call, obj);
			}
			break;
		case INFO:
			logger.info(MessageFormat, head, call, obj);
			break;
		case WARN:
			logger.warn(MessageFormat, head, call, obj);
			break;
		case ERROR:
			logger.error(MessageFormat, head, call, obj);
			break;
		case FATAL:
			break;
		case OFF:
			break;
		default:
			break;
		}
	}

	/**
	 * Throwable時の出力
	 *
	 * @param head
	 * @param th
	 */
	private void loggerMessageThrowable(Throwable th) {
		logger.error("Exception: ", th);
	}
}
