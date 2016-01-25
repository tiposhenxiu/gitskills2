/*
* ファイル名：MyCustomDialect.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.tags.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import jp.co.sraw.tags.dialect.processor.FormProcessor;
import jp.co.sraw.tags.dialect.processor.TextRemoveTagsProcessor;

/**
* <B>MyCustomDialectクラス</B>
* <P>
* thymeleafのカスタムタグを提供する
*/
public class MyCustomDialect extends AbstractDialect {

	public static final String DEFAULT_PREFIX = "my";
	/**
	 * 	All of this dialect's attributes and/or tags
	 *  will start with 'my:'
	 */
	@Override
	public String getPrefix() {
		return DEFAULT_PREFIX;
	}

	/**
	 * processors
	 */
	@Override
	public Set<IProcessor> getProcessors() {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new FormProcessor());
		processors.add(new TextRemoveTagsProcessor());
		return processors;
	}

}
