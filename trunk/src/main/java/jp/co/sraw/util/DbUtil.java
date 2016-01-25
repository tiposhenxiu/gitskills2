/*
* ファイル名：DbUtil.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.MsCodeTbl;
import jp.co.sraw.service.MsCodeServiceImpl;

/**
 * <B>DbUtilクラス</B>
 * <P>
 * DbUtilを提供する
 */
@Component
public final class DbUtil {

	private static MsCodeServiceImpl msCodeServiceImpl;

	@Autowired
	private void setMsCodeServiceImpl(MsCodeServiceImpl msCodeServiceImpl) {
		DbUtil.msCodeServiceImpl = msCodeServiceImpl;
	}

	/**
	 * 定数コードの名称を取得(Locale指定)
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @param locale
	 * @return
	 */
	public static String getJosuName(String josuKbn, String josuCode, Locale locale) {
		MsCodeTbl m = msCodeServiceImpl.findOne(josuKbn, josuCode);
		if (m == null) {
			return null;
		}
		if (CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
			return m.getJosuName();
		}
		return m.getJosuNameEn();
	}

	/**
	 * 定数コードの名称を取得(日本語)
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @return
	 */
	public static String getJosuName(String josuKbn, String josuCode) {
		return DbUtil.getJosuName(josuKbn, josuCode, CommonConst.DEFAULT_LOCALE);
	}

	/**
	 * 定数コードの名称を取得(英語)
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @return
	 */
	public static String getJosuNameEn(String josuKbn, String josuCode) {
		return DbUtil.getJosuName(josuKbn, josuCode, Locale.ENGLISH);
	}

	/**
	 * その他属性テキスト1を取得
	 *
	 * @param josuKbn
	 * @param josuCode
	 * @return
	 */
	public static String getAttrText1(String josuKbn, String josuCode) {
		MsCodeTbl m = msCodeServiceImpl.findOne(josuKbn, josuCode);
		if (m == null) {
			return null;
		}
		return m.getSntaZksei1Txt();
	}

	/**
	 * 定数コードの一覧Mapを取得(Locale指定)[全て取得]
	 *
	 * @param josuKbn
	 * @param locale
	 * @return key=code, value=名称
	 */
	public static Map<String, MsCodeDto> getJosuMap(String josuKbn, Locale locale) {
		List<MsCodeTbl> l = msCodeServiceImpl.findAllJosuKbn(josuKbn);
		Map<String, MsCodeDto> map = new HashMap<String, MsCodeDto>();
		if (CollectionUtils.isEmpty(l)) {
			return map;
		}
		for (MsCodeTbl m : l) {
			MsCodeDto data = new MsCodeDto();
			if (CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
				data.setCode(m.getId().getJosuCode());
				data.setValue(m.getJosuName());
				data.setUseFlag(m.getUseFlag());
				data.setCommentProperty(m.getCommentProperty());
				map.put(m.getId().getJosuCode(), data);
			} else {
				data.setCode(m.getId().getJosuCode());
				data.setValue(m.getJosuNameEn());
				data.setUseFlag(m.getUseFlag());
				data.setCommentProperty(m.getCommentProperty());
				map.put(m.getId().getJosuCode(), data);
			}
		}
		return map;
	}

	/**
	 * 定数コードの略名称一覧Mapを取得(Locale指定)
	 *
	 * @param josuKbn
	 * @param locale
	 * @return key=code, value=名称
	 */
	public static Map<String, String> getJosuAbbrMap(String josuKbn, Locale locale) {
		List<MsCodeTbl> l = msCodeServiceImpl.findAllJosuKbn(josuKbn);
		Map<String, String> map = new HashMap<String, String>();
		if (CollectionUtils.isEmpty(l)) {
			return map;
		}
		for (MsCodeTbl m : l) {
			if (CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
				map.put(m.getId().getJosuCode(), m.getJosuNameAbbr());
			} else {
				map.put(m.getId().getJosuCode(), m.getJosuNameAbbrEn());
			}
		}
		return map;
	}

	/**
	 * 定数コードの一覧Mapを取得(日本語)
	 *
	 * @param josuKbn
	 * @return
	 */
	public static Map<String, MsCodeDto> getJosuMap(String josuKbn) {
		return DbUtil.getJosuMap(josuKbn, CommonConst.DEFAULT_LOCALE);
	}

	/**
	 * 定数コードの一覧Mapを取得(英語)
	 *
	 * @param josuKbn
	 * @return
	 */
	public static Map<String, MsCodeDto> getJosuMapEn(String josuKbn) {
		return DbUtil.getJosuMap(josuKbn, Locale.ENGLISH);
	}

	/**
	 * 定数コードの一覧Listを取得(Locale指定)[有効のみ取得]
	 *
	 * @param josuKbn
	 * @param locale
	 * @return
	 */
	public static List<MsCodeDto> getJosuList(String josuKbn, Locale locale) {
		return DbUtil.getJosuListUseFlag(josuKbn, CommonConst.USE_FALG_ACTIVE, locale);
	}

	/**
	 * 定数コードの一覧Listを取得(Locale指定)[全て取得]
	 *
	 * @param josuKbn
	 * @param locale
	 * @return
	 */
	public static List<MsCodeDto> getJosuListAll(String josuKbn, Locale locale) {
		return DbUtil.getJosuListUseFlag(josuKbn, null, locale);
	}

	/**
	 * 定数コードの一覧Listを取得(Locale指定)[有効フラグ指定]
	 *
	 * @param josuKbn
	 * @param locale
	 * @return
	 */
	public static List<MsCodeDto> getJosuListUseFlag(String josuKbn, String useFlag, Locale locale) {
		List<MsCodeTbl> l = msCodeServiceImpl.findAllJosuKbnAndUseFlag(josuKbn, useFlag);
		List<MsCodeDto> list = new ArrayList<MsCodeDto>();
		if (CollectionUtils.isEmpty(l)) {
			return list;
		}
		for (MsCodeTbl m : l) {
			MsCodeDto data = new MsCodeDto();
			if (CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
				data.setCode(m.getId().getJosuCode());
				data.setValue(m.getJosuName());
				data.setUseFlag(m.getUseFlag());
				data.setCommentProperty(m.getCommentProperty());
			} else {
				data.setCode(m.getId().getJosuCode());
				data.setValue(m.getJosuNameEn());
				data.setUseFlag(m.getUseFlag());
				data.setCommentProperty(m.getCommentProperty());
			}
			list.add(data);
		}
		return list;
	}

	/**
	 * 定数コードの一覧Listを取得(日本語)
	 *
	 * @param josuKbn
	 * @return
	 */
	public static List<MsCodeDto> getJosuList(String josuKbn) {
		return DbUtil.getJosuList(josuKbn, CommonConst.DEFAULT_LOCALE);
	}

	/**
	 * 定数コードの一覧Listを取得(英語)
	 *
	 * @param josuKbn
	 * @return
	 */
	public static List<MsCodeDto> getJosuListEn(String josuKbn) {
		return DbUtil.getJosuList(josuKbn, Locale.ENGLISH);
	}

}
