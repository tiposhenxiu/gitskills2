/*
* ファイル名：StringUtil.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2007/05/16   Y.Sakamoto  新規作成
*/
package jp.co.sraw.util;

import java.util.regex.Pattern;

/**
 * <B>ユーティリティ（文字操作関連）クラス</B>
 * <P>
 * 文字列操作関連のスタティックメソッドを提供する
 *
 */
public final class StringUtil {
	/*---------------------------------------------------------------------- 定数 */
	/** 半角スペース */
	public static final String HALF_SPACE = " ";
	/** 全角スペース */
	public static final String SPACE = "　";

	/*----------------------------------------------------------- public メソッド */
	/**
	 * NULL値を変換する
	 * <P>
	 *
	 * @param str
	 *            NULLチェックする文字列
	 * @param val
	 *            NULL時にセットする値
	 * @return NULLチェック後の文字列
	 */
	public static String nvl(String str, String val) {
		return (str == null) ? val : str;
	}

	/**
	 * NULLチェックを行う
	 * <P>
	 *
	 * @param str
	 *            NULLチェックする文字列
	 * @return 判定
	 */
	public static boolean isNull(String str) {
		return (str == null || "".equals(str));
	}

	/**
	 * NULLチェックを行う
	 * <P>
	 *
	 * @param str
	 *            NULLチェックする文字列
	 * @return 判定
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 文字列の右側に空白（半角スペース）を詰めて指定サイズの文字列にする
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・変換前の文字列がNULLの場合はNULLを返す
	 *   ・変換前の文字列が指定サイズより長い場合は何もしない
	 * </PRE>
	 *
	 * @param str
	 *            変換前の文字列
	 * @param len
	 *            変換後の文字列の長さ
	 * @return 変換後の文字列
	 */
	public static String rpad(String str, int len) {
		return rpad(str, len, HALF_SPACE);
	}

	/**
	 * 文字列の左側に空白（半角スペース）を詰めて指定サイズの文字列にする
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・変換前の文字列がNULLの場合はNULLを返す
	 *   ・変換前の文字列が指定サイズより長い場合は何もしない
	 * </PRE>
	 *
	 * @param str
	 *            変換前の文字列
	 * @param len
	 *            変換後の文字列の長さ
	 * @return 変換後の文字列
	 */
	public static String lpad(String str, int len) {
		return lpad(str, len, HALF_SPACE);
	}

	/**
	 * 文字列の右側に指定文字を詰めて指定サイズの文字列にする
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・変換前の文字列がNULLの場合はNULLを返す
	 *   ・変換前の文字列が指定サイズより長い場合は何もしない
	 * </PRE>
	 *
	 * @param str
	 *            変換前の文字列
	 * @param len
	 *            変換後の文字列の長さ
	 * @param add
	 *            パディングする文字
	 * @return 変換後の文字列
	 */
	public static String rpad(String str, int len, String add) {
		int i;
		StringBuffer sb;

		if (str == null) {
			return null;
		}
		if (str.length() >= len) {
			return str;
		}

		sb = new StringBuffer(str);
		for (i = str.length(); i < len; i++) {
			sb.append(add); // 指定文字を追加
		}
		return sb.toString();
	}

	/**
	 * 文字列の左側に指定文字を詰めて指定サイズの文字列にする
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・変換前の文字列がNULLの場合はNULLを返す
	 *   ・変換前の文字列が指定サイズより長い場合は何もしない
	 * </PRE>
	 *
	 * @param str
	 *            変換前の文字列
	 * @param len
	 *            変換後の文字列の長さ
	 * @param add
	 *            パディングする文字
	 * @return 変換後の文字列
	 */
	public static String lpad(String str, int len, String add) {
		int i;
		StringBuffer sb;

		if (str == null) {
			return null;
		}
		if (str.length() >= len) {
			return str;
		}

		sb = new StringBuffer(str);
		for (i = str.length(); i < len; i++) {
			sb.insert(0, add); // 先頭に指定文字を追加
		}
		return sb.toString();
	}

	/**
	 * 文字列のHTML特殊文字を変換する
	 * <P>
	 *
	 * <PRE>
	 *   '<' ==> '&lt;'
	 *   '>' ==> '&gt;'
	 *   '\'' ==> '&#39;'
	 *   '"' ==> '&quot;'
	 *   '&' ==> '&amp;'
	 * </PRE>
	 *
	 * @param input
	 *            変換前の文字列
	 * @return 変換後の文字列
	 */
	public static String htmlFilter(String input) {
		StringBuilder sb = new StringBuilder();
		if (input == null)
			return input;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == '<') {
				sb.append("&lt;");
			} else if (c == '>') {
				sb.append("&gt;");
			} else if (c == '\'') {
				sb.append("&#39;");
			} else if (c == '"') {
				sb.append("&quot;");
			} else if (c == '&') {
				sb.append("&amp;");
			} else {
				sb.append(c);
			}
		}

		// return "<![CDATA[" + sb.toString() + "]]>";
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
	public static String htmlConv(String input) {
		String s = input;

		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&#39;", "'");
		s = s.replaceAll("&quot;", "\"");
		s = s.replaceAll("&amp;", "&");

		return s;

	}

	/**
	 * タグを除去した文字を戻す
	 *
	 * @param input 変換前の文字列
	 * @return 変換された文字列
	 */
	public static String removeTags(String input) {

		String s = input;
		try {
			Pattern pattern = Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>", Pattern.DOTALL);
			s = pattern.matcher(input).replaceAll(" ");
		} catch (Exception e) {
			;
		}
		return s;
	}

	/**
	 * ユーザーの氏名を連結して返します。
	 *
	 * @param userKbn
	 * @param userFamilyName
	 * @param userMiddleName
	 * @param userName
	 * @return
	 */
	public static String getUserName(String userKbn, String userFamilyName, String userMiddleName, String userName) {
		// ユーザー区分
		// 10:個人ユーザ（若手研究者）
		// 20:個人ユーザ（教職員）
		// 30:個人ユーザ（相談員）
		// 40:運営協議会事務局・共同実施機関・連携大学
		// 50:連携機関窓口（企業、研究所）
		String loginUserName = "";
		if (StringUtil.isNotNull(userFamilyName)) {
			loginUserName = userFamilyName;
		}
		if (StringUtil.isNotNull(userMiddleName)) {
			if (StringUtil.isNotNull(loginUserName)) {
				loginUserName = loginUserName + " ";
			}
			loginUserName = loginUserName + userMiddleName;
		}
		if (StringUtil.isNotNull(userName)) {
			if (StringUtil.isNotNull(loginUserName)) {
				loginUserName = loginUserName + " ";
			}
			loginUserName = loginUserName + userName;
		}
		return loginUserName;
	}

	/**
	 * ユーザーの氏名(英語)を連結して返します。
	 *
	 * @param userKbn
	 * @param userFamilyNameEn
	 * @param userMiddleNameEn
	 * @param userNameEn
	 * @return
	 */
	public static String getUserNameEn(String userKbn, String userFamilyNameEn, String userMiddleNameEn, String userNameEn) {
		// ユーザー区分
		// 10:個人ユーザ（若手研究者）
		// 20:個人ユーザ（教職員）
		// 30:個人ユーザ（相談員）
		// 40:運営協議会事務局・共同実施機関・連携大学
		// 50:連携機関窓口（企業、研究所）
		String loginUserNameEn = "";
		if (StringUtil.isNotNull(userNameEn)) {
			loginUserNameEn = userNameEn;
		}
		if (StringUtil.isNotNull(userMiddleNameEn)) {
			if (StringUtil.isNotNull(loginUserNameEn)) {
				loginUserNameEn = loginUserNameEn + " ";
			}
			loginUserNameEn = loginUserNameEn + userMiddleNameEn;
		}
		if (StringUtil.isNotNull(userFamilyNameEn)) {
			if (StringUtil.isNotNull(loginUserNameEn)) {
				loginUserNameEn = loginUserNameEn + " ";
			}
			loginUserNameEn = loginUserNameEn + userFamilyNameEn;
		}
		return loginUserNameEn;
	}

}
