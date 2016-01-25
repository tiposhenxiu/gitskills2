/*
* ファイル名：NumberUtil.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2007/05/16   Y.Sakamoto  新規作成
*/
package jp.co.sraw.util;

import java.text.DecimalFormat;

/**
* <B>ユーティリティ（数値操作関連）クラス</B>
* <P>
* 数値操作関連のスタティックメソッドを提供する
*/
public final class NumberUtil
{
	/**
	* 数値編集を行う(int)
	* <P>
	* @param  num 数値
	* @param  fmt 編集形式("###,##0"など)
	* @return     編集文字列
	*/
	public static String fmt(int num, String fmt) {
		DecimalFormat df = new DecimalFormat(fmt);
		return df.format(num);
	}

	/**
	* 数値編集を行う(long)
	* <P>
	* @param  num 数値
	* @param  fmt 編集形式("###,##0"など)
	* @return     編集文字列
	*/
	public static String fmt(long num, String fmt) {
		DecimalFormat df = new DecimalFormat(fmt);
		return df.format(num);
	}

	/**
	* 数値編集を行う(double)
	* <P>
	* @param  num 数値
	* @param  fmt 編集形式("###,##0"など)
	* @return     編集文字列
	*/
	public static String fmt(double num, String fmt) {
		DecimalFormat df = new DecimalFormat(fmt);
		return df.format(num);
	}
}
