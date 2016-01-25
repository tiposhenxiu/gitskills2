/*
* ファイル名：LoggerWrapper.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.logger;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.helpers.NOPLogger;
import org.springframework.context.MessageSource;

import jp.co.sraw.common.CommonConst;

/**
* <B>LoggerWrapperクラス</B>
* <P>
* Loggerのメソッドを提供する
*/
public class LoggerWrapper {

	//private static final String FQCN = Commondelegate().class.getName() + ".";

	private final String name;

	private volatile Logger _delegate;

	private MessageSource _messageSource;

	public LoggerWrapper(Class<?> clazz) {
		this(clazz.getName());
	}

	public LoggerWrapper(String name) {
		this(name, null);
	}

	public LoggerWrapper(Class<?> clazz, MessageSource messageSource) {
		this(clazz.getName(), messageSource);
	}

	public LoggerWrapper(String name, MessageSource messageSource) {
		this.name = name;
		setDelegate(LoggerFactory.getLogger(name));
		setMessageSource(messageSource);
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LoggerWrapper that = (LoggerWrapper) o;

		if (!name.equals(that.name))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/**
	 * Return the delegate logger instance if set. Otherwise, return a
	 * {@link NOPLogger} instance.
	 */
	Logger delegate() {
		return _delegate != null ? _delegate : NOPLogger.NOP_LOGGER;
	}

	/**
	 * Typically called after the {@link org.slf4j.LoggerFactory} initialization
	 * phase is completed.
	 *
	 * @param delegate
	 */
	public void setDelegate(Logger delegate) {
		this._delegate = delegate;
	}

	/**
	 * @return String
	 */
	private String getMessage(String code, Object[] args, Locale locale) {
		try {
			return _messageSource.getMessage(code, args, locale);
		} catch (Exception e) {
			return getMessage(code, args, "[UNKNOWN CODE] {0}", locale);
		}
	}

	private String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return _messageSource.getMessage(code, args, defaultMessage, locale);
	}

	/**
	 * @param messageSource
	 *            セットする messageSource
	 */
	public void setMessageSource(MessageSource messageSource) {
		this._messageSource = messageSource;

	}

	public void traceCode(String code) {
		delegate().trace("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE));
	}

	public void traceCode(String code, Object arg) {
		delegate().trace("[" + code + "] "+ getMessage(code, new Object[] { arg }, CommonConst.DEFAULT_LOCALE));
	}

	public void traceCode(String code, Object... arguments) {
		delegate().trace("[" + code + "] " + getMessage(code, arguments, CommonConst.DEFAULT_LOCALE));
	}

	public void traceCode(String code, Throwable t) {
		delegate().trace("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE), t);
	}

	public void debugCode(String code) {
		delegate().debug("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE));
	}

	public void debugCode(String code, Object arg) {
		delegate().debug("[" + code + "] " + getMessage(code, new Object[] { arg }, CommonConst.DEFAULT_LOCALE));
	}

	public void debugCode(String code, Object... arguments) {
		delegate().debug("[" + code + "] " + getMessage(code, arguments, CommonConst.DEFAULT_LOCALE));
	}

	public void debugCode(String code, Throwable t) {
		delegate().debug("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE), t);
	}

	public void infoCode(String code) {
		delegate().info("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE));
	}

	public void infoCode(String code, Object arg) {
		delegate().info("[" + code + "] " + getMessage(code, new Object[] { arg }, CommonConst.DEFAULT_LOCALE));
	}

	public void infoCode(String code, Object... arguments) {
		delegate().info("[" + code + "] " + getMessage(code, arguments, CommonConst.DEFAULT_LOCALE));
	}

	public void infoCode(String code, Throwable t) {
		delegate().info("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE), t);
	}

	public void warnCode(String code) {
		delegate().warn("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE));
	}

	public void warnCode(String code, Object arg) {
		delegate().warn("[" + code + "] " + getMessage(code, new Object[] { arg }, CommonConst.DEFAULT_LOCALE));
	}

	public void warnCode(String code, Object... arguments) {
		delegate().warn("[" + code + "] " + getMessage(code, arguments, CommonConst.DEFAULT_LOCALE));
	}

	public void warnCode(String code, Throwable t) {
		delegate().warn("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE), t);
	}

	public void errorCode(String code) {
		delegate().error("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE));
	}

	public void errorCode(String code, Object arg) {
		delegate().error("[" + code + "] " + getMessage(code, new Object[] { arg }, CommonConst.DEFAULT_LOCALE));
	}

	public void errorCode(String code, Object... arguments) {
		delegate().error("[" + code + "] " + getMessage(code, arguments, CommonConst.DEFAULT_LOCALE));
	}

	public void errorCode(String code, Throwable t) {
		delegate().error("[" + code + "] " + getMessage(code, new Object[] { "" }, CommonConst.DEFAULT_LOCALE), t);
	}

	/*
	 * SubstituteLogger, NOPLogger
	 *
	 */

    public boolean isTraceEnabled() {
        return delegate().isTraceEnabled();
    }

    public void trace(String msg) {
        delegate().trace(msg);
    }

    public void trace(String format, Object arg) {
        delegate().trace(format, arg);
    }

    public void trace(String format, Object arg1, Object arg2) {
        delegate().trace(format, arg1, arg2);
    }

    public void trace(String format, Object... arguments) {
        delegate().trace(format, arguments);
    }

    public void trace(String msg, Throwable t) {
        delegate().trace(msg, t);
    }

    public boolean isTraceEnabled(Marker marker) {
        return delegate().isTraceEnabled(marker);
    }

    public void trace(Marker marker, String msg) {
        delegate().trace(marker, msg);
    }

    public void trace(Marker marker, String format, Object arg) {
        delegate().trace(marker, format, arg);
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        delegate().trace(marker, format, arg1, arg2);
    }

    public void trace(Marker marker, String format, Object... arguments) {
        delegate().trace(marker, format, arguments);
    }

    public void trace(Marker marker, String msg, Throwable t) {
        delegate().trace(marker, msg, t);
    }

    public boolean isDebugEnabled() {
        return delegate().isDebugEnabled();
    }

    public void debug(String msg) {
        delegate().debug(msg);
    }

    public void debug(String format, Object arg) {
        delegate().debug(format, arg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        delegate().debug(format, arg1, arg2);
    }

    public void debug(String format, Object... arguments) {
        delegate().debug(format, arguments);
    }

    public void debug(String msg, Throwable t) {
        delegate().debug(msg, t);
    }

    public boolean isDebugEnabled(Marker marker) {
        return delegate().isDebugEnabled(marker);
    }

    public void debug(Marker marker, String msg) {
        delegate().debug(marker, msg);
    }

    public void debug(Marker marker, String format, Object arg) {
        delegate().debug(marker, format, arg);
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        delegate().debug(marker, format, arg1, arg2);
    }

    public void debug(Marker marker, String format, Object... arguments) {
        delegate().debug(marker, format, arguments);
    }

    public void debug(Marker marker, String msg, Throwable t) {
        delegate().debug(marker, msg, t);
    }

    public boolean isInfoEnabled() {
        return delegate().isInfoEnabled();
    }

    public void info(String msg) {
        delegate().info(msg);
    }

    public void info(String format, Object arg) {
        delegate().info(format, arg);
    }

    public void info(String format, Object arg1, Object arg2) {
        delegate().info(format, arg1, arg2);
    }

    public void info(String format, Object... arguments) {
        delegate().info(format, arguments);
    }

    public void info(String msg, Throwable t) {
        delegate().info(msg, t);
    }

    public boolean isInfoEnabled(Marker marker) {
        return delegate().isInfoEnabled(marker);
    }

    public void info(Marker marker, String msg) {
        delegate().info(marker, msg);
    }

    public void info(Marker marker, String format, Object arg) {
        delegate().info(marker, format, arg);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        delegate().info(marker, format, arg1, arg2);
    }

    public void info(Marker marker, String format, Object... arguments) {
        delegate().info(marker, format, arguments);
    }

    public void info(Marker marker, String msg, Throwable t) {
        delegate().info(marker, msg, t);
    }

    public boolean isWarnEnabled() {
        return delegate().isWarnEnabled();
    }

    public void warn(String msg) {
        delegate().warn(msg);
    }

    public void warn(String format, Object arg) {
        delegate().warn(format, arg);
    }

    public void warn(String format, Object arg1, Object arg2) {
        delegate().warn(format, arg1, arg2);
    }

    public void warn(String format, Object... arguments) {
        delegate().warn(format, arguments);
    }

    public void warn(String msg, Throwable t) {
        delegate().warn(msg, t);
    }

    public boolean isWarnEnabled(Marker marker) {
        return delegate().isWarnEnabled(marker);
    }

    public void warn(Marker marker, String msg) {
        delegate().warn(marker, msg);
    }

    public void warn(Marker marker, String format, Object arg) {
        delegate().warn(marker, format, arg);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        delegate().warn(marker, format, arg1, arg2);
    }

    public void warn(Marker marker, String format, Object... arguments) {
        delegate().warn(marker, format, arguments);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        delegate().warn(marker, msg, t);
    }

    public boolean isErrorEnabled() {
        return delegate().isErrorEnabled();
    }

    public void error(String msg) {
        delegate().error(msg);
    }

    public void error(String format, Object arg) {
        delegate().error(format, arg);
    }

    public void error(String format, Object arg1, Object arg2) {
        delegate().error(format, arg1, arg2);
    }

    public void error(String format, Object... arguments) {
        delegate().error(format, arguments);
    }

    public void error(String msg, Throwable t) {
        delegate().error(msg, t);
    }

    public boolean isErrorEnabled(Marker marker) {
        return delegate().isErrorEnabled(marker);
    }

    public void error(Marker marker, String msg) {
        delegate().error(marker, msg);
    }

    public void error(Marker marker, String format, Object arg) {
        delegate().error(marker, format, arg);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        delegate().error(marker, format, arg1, arg2);
    }

    public void error(Marker marker, String format, Object... arguments) {
        delegate().error(marker, format, arguments);
    }

    public void error(Marker marker, String msg, Throwable t) {
        delegate().error(marker, msg, t);
    }

}
