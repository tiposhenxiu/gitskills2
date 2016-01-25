/*
* ファイル名：DateUtil.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2007/05/16   Y.Sakamoto  新規作成
*/
package jp.co.sraw.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <B>ユーティリティ（日付関連）クラス</B>
 * <P>
 * 日付関連のスタティックメソッドを提供する
 *
 */
public final class DateUtil {
	/*----------------------------------------------------------- システム日付関連 */
	/**
	 * システム日時（Date型）を取得する
	 * <P>
	 *
	 * @return システム日付（Date型）
	 */
	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * システム日時（書式：YYYYMMDDHH24MISS）を取得する
	 * <P>
	 *
	 * @return システム日付（String型）
	 */
	public static String getSysdate() {
		return getSysdate("yyyyMMddHHmmss");
	}

	/**
	 * システム日時（指定書式）を取得する
	 * <P>
	 *
	 * 指定書式はSimpleDateFormatの指定パターンに準ずる
	 *
	 * @param fmt
	 *            日付書式
	 * @return システム日付（String型）
	 */
	public static String getSysdate(String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(DateUtil.now());
	}

	/*--------------------------------------------------------- String ⇒ Date変換 */
	/**
	 * 年月日（YYYYMMDD）の文字列をDate型に変換する
	 * <P>
	 *
	 * 変換前文字列は『YYYYMMDD』の8文字でなければならない
	 *
	 * @param ymd
	 *            YYYYMMDD形式の文字列
	 * @return Date型に変換した値
	 */
	public static Date toDate(String ymd) {
		return toDate(ymd.substring(0, 4), ymd.substring(4, 6), ymd.substring(6, 8));
	}

	/**
	 * 年、月、日の文字列をDate型に変換する
	 * <P>
	 *
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return Date型に変換した値
	 */
	public static Date toDate(String year, String month, String day) {
		return Date.valueOf(year + "-" + month + "-" + day);
	}

	/*----------------------------------------------------------------- 月末日取得 */
	/**
	 * 指定日付の月末日を取得する
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・先頭6桁がYYYYMMであれば桁数は問わない
	 *     ※最低でも年月（YYYYMM）が必要でそれ以降は無視
	 * </PRE>
	 *
	 * @param ym
	 *            年月（String型）
	 * @return 指定日付の月末日
	 */
	public static int getLastDay(String ym) {
		return getLastDay(toDate(ym.substring(0, 4), ym.substring(4, 6), "1"));
	}

	/**
	 * 指定日付の月末日を取得する
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・先頭6桁がYYYYMMであれば桁数は問わない
	 *     ※最低でも年月（YYYYMM）が必要でそれ以降は無視
	 * </PRE>
	 *
	 * @param ym
	 *            年月（String型）
	 * @return 指定日付の月末日(String型 YYYYMMDD)
	 */
	public static String getLastDayStr(String ym) {
		return ym.substring(0, 6) + String.valueOf(getLastDay(ym));
	}

	/**
	 * 指定日付の月末日を取得する
	 * <P>
	 *
	 * @param yyyy
	 *            年（String型）
	 * @param mm
	 *            月（String型）
	 * @return 指定日付の月末日
	 */
	public static int getLastDay(String yyyy, String mm) {
		return getLastDay(toDate(yyyy, mm, "01"));
	}

	/**
	 * 指定日付の月末日を取得する
	 * <P>
	 *
	 * @param ymd
	 *            年月日（Date型）
	 * @return 指定日付の月末日
	 */
	public static int getLastDay(Date ymd) {
		Calendar c = Calendar.getInstance();
		c.setTime(ymd);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/*--------------------------------------------------------------- 日付書式編集 */
	/**
	 * 指定文字列を指定書式文字列に編集する
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・編集書式は以下のものを指定可能とする
	 *      "YYYY/MM/DD"、"YYYY/MM"、"MM/DD"
	 *   ・規定書式以外が与えられた場合、編集前文字列を返す
	 *   ・編集書式は大文字、小文字の区別はしない
	 * </PRE>
	 *
	 * @param ymd
	 *            日付
	 * @param fmt
	 *            編集書式
	 * @return 編集後日付文字列
	 */
	public static String format(String ymd, String fmt) {
		String str = null;
		String yyyy = ymd.substring(0, 4); // 年取得
		String mm = ymd.substring(4, 6); // 月取得
		String dd = ymd.substring(6, 8); // 日取得
		if (fmt.toUpperCase().equals("YYYY/MM/DD")) {
			str = yyyy + "/" + mm + "/" + dd;
		} else if (fmt.toUpperCase().equals("YYYY/MM")) {
			str = yyyy + "/" + mm;
		} else if (fmt.toUpperCase().equals("MM/DD")) {
			str = mm + "/" + dd;
		} else {
			str = ymd;
		}
		return str;
	}

	/**
	 * DateやTimestampを、"2016/01/15 14:46"などへ書式化する。秒の桁を付けるかどうか選択可能。
	 * 
	 * @param dt
	 *            日時
	 * @param withSec
	 *            秒を付けるかどうか
	 * @return
	 */
	public static String dateTimeFormat(java.util.Date dt, boolean withSec) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm" + (withSec ? ":ss" : ""));
		return sdf.format(dt);
	}

	/**
	 * 日付文字列をISO書式（PostgreSQL標準日付書式）文字列に編集する
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・入力文字列（引数）が'YYYY-MM-DD HH24:MI:SS'に変換される
	 *   ・入力書式（引数）が'YYYYMMDDHH24MISS'の文字列であること
	 * </PRE>
	 *
	 * @param ymd
	 *            日付
	 * @return 編集後日付文字列
	 */
	public static String isoFormat(String ymd) {
		StringBuffer sb = new StringBuffer();

		sb.append(ymd.substring(0, 4)); // 年
		sb.append("-");
		sb.append(ymd.substring(4, 6)); // 月

		sb.append("-");
		sb.append(ymd.substring(6, 8)); // 日
		sb.append(" ");
		sb.append(ymd.substring(8, 10)); // 時

		sb.append(":");
		sb.append(ymd.substring(10, 12)); // 分

		sb.append(":");
		sb.append(ymd.substring(12, 14)); // 秒

		return sb.toString();
	}

	/*--------------------------------------------------------------- 日付演算関連 */
	/**
	 * ±n月の年月計算を行う
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・引数はYYYYMMDDの書式とする
	 *   ・最低YYYYMMが必
	 *   ・引数に指定された精度（桁数）で結果を返す
	 * </PRE>
	 *
	 * @param ymd
	 *            年月日（最低YYYYMMが必要）
	 * @return 計算後の日付
	 */
	public static String addMonth(String ymd, int months) {
		int len = ymd.length();
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(ymd.substring(0, 4)), // 年
				Integer.parseInt(ymd.substring(4, 6)) - 1, // 月（ゼロオリジン）
				(len > 6) ? Integer.parseInt(ymd.substring(6, 8)) : 1); // 日（未指定時は月初）
		c.add(Calendar.MONTH, months);
		String s = new SimpleDateFormat((len > 6) ? "yyyyMMdd" : "yyyyMM").format(c.getTime());
		return s;
	}

	/*--------------------------------------------------------------- 日付演算関連 */
	/**
	 * ±n月の年月計算を行う
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 * </PRE>
	 *
	 * @param date
	 *            日付
	 * @return 計算後の日付
	 */
	public static Date addMonth(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return new Date(c.getTimeInMillis());
	}

	/**
	 * ±n日の日付計算を行う
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 *   ・引数はYYYYMMDDの書式とする
	 *   ・最低YYYYMMDDが必要
	 *   ・YYYYMMDD以降が指定（時間部）された場合、それらは無視する
	 * </PRE>
	 *
	 * @param ymd
	 *            年月日（最低YYYYMMDDが必要）
	 * @return 計算後の日付
	 */
	public static String addDay(String ymd, int days) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(ymd.substring(0, 4)), // 年
				Integer.parseInt(ymd.substring(4, 6)) - 1, // 月（ゼロオリジン）
				Integer.parseInt(ymd.substring(6, 8))); // 日
		c.add(Calendar.DATE, days);
		String s = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return s;
	}

	/*--------------------------------------------------------------- 日付演算関連 */
	/**
	 * ±n日の計算を行う
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 * </PRE>
	 *
	 * @param date
	 *            日付
	 * @return 計算後の日付
	 */
	public static Date addDay(Date date, int days) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return new Date(c.getTimeInMillis());
	}

	/*--------------------------------------------------------------- 日付演算関連 */
	/**
	 * 日数の計算を行う
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 * </PRE>
	 *
	 * @param beginDate
	 *            開始日付
	 * @param endDate
	 *            終了日付
	 * @return long 日数
	 */
	public static long dateDiff(Date beginDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(beginDate);
		c2.setTime(endDate);
		return c2.getTimeInMillis() - c1.getTimeInMillis() / 86400000;
	}

	/*--------------------------------------------------------------- 日付演算関連 */
	/**
	 * 日数の計算を行う
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 * </PRE>
	 *
	 * @param time
	 *            時間
	 * @return Timestamp 時間
	 */
	public static Timestamp getTimestamp(String time) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(time);
		} catch (Exception e) {

		}
		return ts;
	}

	/*--------------------------------------------------------------- 日付演算関連 */
	/**
	 * 日数の計算を行う
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 * </PRE>
	 *
	 * @param time
	 *            時間
	 * @return Timestamp 時間
	 */
	public static String getDate(Timestamp time, String fmt) {
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		String str = getSysdate(fmt);
		try {
			str = df.format(time);
		} catch (Exception e) {

		}
		return str;
	}

	/**
	 * 現在時刻を取得
	 *
	 * @return
	 */
	public static Timestamp getNowTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * NULLチェックを行う
	 * <P>
	 *
	 * @param ts
	 *            NULLチェックするTimestamp
	 * @return 判定
	 */
	public static boolean isNull(Timestamp ts) {
		return (ts == null);
	}

	/**
	 * NULLチェックを行う
	 * <P>
	 *
	 * @param ts
	 *            NULLチェックするTimestamp
	 * @return 判定
	 */
	public static boolean isNotNull(Timestamp ts) {
		return !isNull(ts);
	}

	/**
	 * 指定日の時分秒を00:00:00して返す。
	 *
	 * @param ts
	 * @return
	 */
	public static Timestamp formatTimestampStart(Timestamp ts) {
		return new Timestamp(formatDateStart(new Date(ts.getTime())).getTime());
	}

	/**
	 * 指定日の時分秒を23:59:59して返す。
	 *
	 * @param ts
	 * @return
	 */
	public static Timestamp formatTimestampEnd(Timestamp ts) {
		return new Timestamp(formatDateEnd(new Date(ts.getTime())).getTime());
	}

	/**
	 * 指定日の時分秒を00:00:00して返す。
	 *
	 * @param date
	 * @return
	 */
	public static Date formatDateStart(Date date) {
		return formatDate(date, 0, 0, 0, 0);
	}

	/**
	 * 指定日の時分秒を23:59:59して返す。
	 *
	 * @param date
	 * @return
	 */
	public static Date formatDateEnd(Date date) {
		return formatDate(date, 23, 59, 59, 999);
	}

	/**
	 * 指定時分秒にして返す。
	 *
	 * @param date
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date formatDate(Date date, int hourOfDay, int minute, int second, int mlli) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hourOfDay);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, mlli);
		return new Date(c.getTimeInMillis());
	}

	public static Date getDate(String dateStr, String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		try {
			return new java.sql.Date(sdf.parse(dateStr).getTime());
		} catch (ParseException e) {
			return now();
		}
	}

	public static Timestamp getTimeFromDate(Date date) {
		return new Timestamp(date.getTime());
	}

	public static Timestamp getTimestamp(String dateStr, String fmt) {
		return getTimeFromDate(getDate(dateStr, fmt));
	}

	/**
	 * 日数の計算を行う
	 * <P>
	 *
	 * <PRE>
	 * 【特記事項】
	 * </PRE>
	 *
	 * @param time
	 *            時間
	 * @return Timestamp 時間
	 */
	public static Timestamp getTimeFromString(String time) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(time);
		} catch (Exception e) {

		}
		return ts;
	}

	public static String getDateStrFromTimeStr(String time, String fmt) {
		Timestamp t = getTimeFromString(time);
		return getDate(t, fmt);
	}
}
