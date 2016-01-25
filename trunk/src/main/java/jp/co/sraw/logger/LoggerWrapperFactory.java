/*
* ファイル名：LoggerWrapperFactory.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
* <B>LoggerWrapperFactoryクラス</B>
* <P>
* LoggerWrapperを提供する
*/
public class LoggerWrapperFactory {

	private static final ConcurrentMap<String, LoggerWrapper> loggers = new ConcurrentHashMap<String, LoggerWrapper>();
	static LoggerWrapperFactory TEMP_FACTORY = new LoggerWrapperFactory();

	public static LoggerWrapper getLogger(String name) {
		LoggerWrapper logger = loggers.get(name);
		if (logger == null) {
			logger = new LoggerWrapper(name);
			LoggerWrapper oldLogger = loggers.putIfAbsent(name, logger);
			if (oldLogger != null)
				logger = oldLogger;
		}
		return logger;
	}

	public static LoggerWrapper getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	public List<String> getLoggerNames() {
		return new ArrayList<String>(loggers.keySet());
	}

	public List<LoggerWrapper> getLoggers() {
		return new ArrayList<LoggerWrapper>(loggers.values());
	}

	public void clear() {
		loggers.clear();
	}


//	public static LoggerWapper getLogger(String name) {
//		return new LoggerWapper(name);
//	}

//	public static LoggerWapper getLogger(Class<?> clazz) {
//		return new LoggerWapper(clazz);
//	}

//	public static LoggerWapper getLogger(String name, MessageSource messageSource) {
//		return new LoggerWapper(name, messageSource);
//	}
//
//	public static LoggerWapper getLogger(Class<?> clazz, MessageSource messageSource) {
//		return new LoggerWapper(clazz, messageSource);
//	}
}
