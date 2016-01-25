/*
* ファイル名：AddLoggerContextListener.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.listener;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.ContextUtil;

/**
* <B>AddLoggerContextListenerクラス</B>
* <P>
* 独自loggerのクラスを出力しないためのListener
*/
public class AddLoggerContextListener extends ContextAwareBase implements LoggerContextListener {

	@Override
	public void setContext(Context context) {
		super.setContext(context);

		String packageName = "jp.co.sraw.logger";
		addInfo("Adding framework package [" + packageName + "]");

		LoggerContext lctx = (LoggerContext) getContext();
		ContextUtil contextUtil = new ContextUtil(context);
		contextUtil.addFrameworkPackage(lctx.getFrameworkPackages(), packageName);
	}

	@Override
	public boolean isResetResistant() {
		return false;
	}

	@Override
	public void onStart(LoggerContext context) {

	}

	@Override
	public void onReset(LoggerContext context) {

	}

	@Override
	public void onStop(LoggerContext context) {

	}

	@Override
	public void onLevelChange(Logger logger, Level level) {

	}

}
