package jp.co.sraw.util;

public final class XmlUtil {

	/**
	 * 文字列のXML特殊文字を変換する
	 * <P>
	 *
	 * <PRE>
	 *   '<' ==> '&lt;'
	 *   '>' ==> '&gt;'
	 *   '"' ==> '&quot;'
	 *   '&' ==> '&amp;'
	 *   '\'' ==> '&apos;'
	 * </PRE>
	 *
	 * @param input
	 *            変換前の文字列
	 * @return 変換後の文字列
	 */
	public static String xmlFilter(String input) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == '<') {
				sb.append("&lt;");
			} else if (c == '>') {
				sb.append("&gt;");
			} else if (c == '\'') {
				sb.append("&apos;");
			} else if (c == '"') {
				sb.append("&quot;");
			} else if (c == '&') {
				sb.append("&amp;");
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 変換されたHTML特殊文字を戻す
	 * <P>
	 *
	 * <PRE>
	 * '&lt;' ==> '<'
	 * '&gt;' ==> '>'
	 * '&#39;' ==> '\''
	 * '&quot;' ==> '"'
	 * '&amp;'==> '&'
	 * </PRE>
	 *
	 * @param input
	 *            変換前の文字列
	 * @return 変換された文字列
	 */
	public static String xmlConv(String input) {
		String s = input;

		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&apos;", "'");
		s = s.replaceAll("&quot;", "\"");

		return s;

	}
}
