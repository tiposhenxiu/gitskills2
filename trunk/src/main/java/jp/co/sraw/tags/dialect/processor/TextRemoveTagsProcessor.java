/*
* ファイル名：TextRemoveTagsProcessor.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/12   toishigawa  新規作成
*/
package jp.co.sraw.tags.dialect.processor;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractTextChildModifierAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

import jp.co.sraw.util.StringUtil;

/**
* <B>TextRemoveTagsProcessorクラス</B>
* <P>
* Textからタグを除去を提供する
*/
public class TextRemoveTagsProcessor extends AbstractTextChildModifierAttrProcessor {

	public static final int ATTR_PRECEDENCE = 1300;
	public static final String ATTR_NAME = "textRemoveTags";

	public TextRemoveTagsProcessor() {
		super(ATTR_NAME);
	}

	protected TextRemoveTagsProcessor(String attributeName) {
		super(attributeName);
	}

	@Override
	public int getPrecedence() {
		return ATTR_PRECEDENCE;
	}

	@Override
	public String getText(Arguments arguments, Element element, String attributeName) {
		final String attributeValue = element.getAttributeValue(attributeName);

		final Configuration configuration = arguments.getConfiguration();
		final IStandardExpressionParser expressionParser = StandardExpressions.getExpressionParser(configuration);

		final IStandardExpression expression = expressionParser.parseExpression(configuration, arguments,
				attributeValue);

		final Object result = expression.execute(configuration, arguments);

		String t = result == null ? "" : result.toString();
		return StringUtil.removeTags(t);
	}
}
