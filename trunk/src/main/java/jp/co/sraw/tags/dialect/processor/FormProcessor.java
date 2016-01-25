/*
* ファイル名：FormProcessor.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.tags.dialect.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.element.AbstractMarkupSubstitutionElementProcessor;
import org.thymeleaf.spring4.requestdata.RequestDataValueProcessorUtils;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

import jp.co.sraw.common.CommonConst;

/**
* <B>FormProcessorクラス</B>
* <P>
* 共通Formを提供する
*/
public class FormProcessor extends AbstractMarkupSubstitutionElementProcessor {

	public static final int ATTR_PRECEDENCE = 1000;
	public static final String ATTR_NAME = "form";

	public FormProcessor() {
		super(ATTR_NAME);
	}


	@Override
	public int getPrecedence() {
		return ATTR_PRECEDENCE;
	}

	/**
	 * 共通のFormタグを生成します。
	 *
	 */
	@Override
	public List<Node> getMarkupSubstitutes(final Arguments arguments, final Element element) {

		List<String> forms = new ArrayList<String>();
		List<Attribute> attributes = new ArrayList<Attribute>();
		//
		for (Entry<String, Attribute> map : element.getAttributeMap().entrySet()) {
			Attribute attr = map.getValue();
			String attributeName = attr.getOriginalName();
			if (attributeName.startsWith("form")) {
				forms.add(attributeName);
			} else {
				attributes.add(attr);
			}
		}

		//
		for (String formName : forms) {
			final String action = element.getAttributeValue(formName);
			final String mode = CommonConst.PAGE_MODE + formName.replaceFirst("form", "");

			Element form = new Element(ATTR_NAME);
			form.setProcessable(true);
			form.setAttribute("name", CommonConst.FORM_NAME);
			// 属性 上書き
			for (Attribute attr : attributes) {
				form.setAttribute(attr.getOriginalName(), attr.isOnlyName(), attr.getOriginalValue());
			}
			// 固定 上書き
			form.setAttribute("method", "post");
			form.setAttribute("role", "form");
			form.setAttribute("id", formName);
			form.setAttribute("action", action);

			// actionを上書き
			String url = getTargetAttributeValue(arguments, form, "action");
			form.setAttribute("action", url);

			doCsrfAdditionalProcess(arguments, form);
			doModeAdditionalProcess(form, mode);
			// 子タグを追加
			for (Element ec : element.getElementChildren()) {
				ec.setProcessable(true);
				form.addChild(ec);
			}

			element.getParent().insertAfter(element, form);
		}

		return element.getParent().getChildren();
	}

	/**
	 * 変換されたURLを取得します。
	 *
	 * @see org.thymeleaf.spring4.processor.attr.SpringActionAttrProcessor
	 * @param arguments
	 * @param element
	 * @param attributeName
	 * @return
	 */
	protected String getTargetAttributeValue(final Arguments arguments, final Element element, final String attributeName) {

		final String attributeValue = getTargetAttributeValueSuper(arguments, element, attributeName);
		final String httpMethod = element.getAttributeValueFromNormalizedName("method");
		return RequestDataValueProcessorUtils.processAction(arguments.getConfiguration(), arguments, attributeValue, httpMethod);
	}

	/**
	 * 変換されたURLを取得します。
	 *
	 * @see org.thymeleaf.spring4.processor.attr.SpringActionAttrProcessor
	 * @param arguments
	 * @param element
	 * @param attributeName
	 * @return
	 */
	protected String getTargetAttributeValueSuper(final Arguments arguments, final Element element, final String attributeName) {

		final String attributeValue = element.getAttributeValue(attributeName);
		final Configuration configuration = arguments.getConfiguration();
		final IStandardExpressionParser expressionParser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = expressionParser.parseExpression(configuration, arguments, attributeValue);
		final Object result = expression.execute(configuration, arguments);
		return (result == null ? "" : result.toString());
	}

	/**
	 * Csrfのinputのhiddenを追加します。
	 *
	 * @see org.thymeleaf.spring4.processor.attr.SpringActionAttrProcessor
	 * @param arguments
	 * @param element
	 */
	protected void doCsrfAdditionalProcess(final Arguments arguments, final Element element) {

		if ("form".equals(element.getNormalizedName())) {
			final Map<String, String> extraHiddenFields = RequestDataValueProcessorUtils
					.getExtraHiddenFields(arguments.getConfiguration(), arguments);
			if (extraHiddenFields != null && extraHiddenFields.size() > 0) {
				for (final Map.Entry<String, String> extraHiddenField : extraHiddenFields.entrySet()) {

					final Element extraHiddenElement = new Element("input");
					extraHiddenElement.setAttribute("type", "hidden");
					extraHiddenElement.setAttribute("name", extraHiddenField.getKey());
					extraHiddenElement.setAttribute("value", extraHiddenField.getValue()); // no need to re-apply the processor here

					element.insertChild(element.numChildren(), extraHiddenElement);
				}
			}
		}
	}

	/**
	 * PageModeのinputのhiddenを追加します。
	 *
	 * @param element
	 * @param value
	 */
	protected void doModeAdditionalProcess(final Element element, final String value) {

		if ("form".equals(element.getNormalizedName())) {
			final Element extraHiddenElement = new Element("input");
			extraHiddenElement.setAttribute("type", "hidden");
			extraHiddenElement.setAttribute("name", CommonConst.PAGE_MODE);
			extraHiddenElement.setAttribute("value", value);

			element.insertChild(element.numChildren(), extraHiddenElement);
		}
	}

}
