/*
* ファイル名：SystemSetting.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/14   toishigawa  新規作成
*/
package jp.co.sraw.common;

import java.util.List;

/**
* <B>SystemSettingクラス</B>
* <P>
* システムのパラメータを提供する
*/
public class SystemSetting {

	/** Batch起動のためのパラメータ */
	private String batchAccessKey;

	/** 学認認証結果の項目名 */
	private String shibbolethLoginid;

	/** アクセス許可URI(メニュー存在しない画面) */
	private List<String> accessUriList;
	/**
	 * @return batchAccessKey
	 */
	public String getBatchAccessKey() {
		return batchAccessKey;
	}
	/**
	 * @param batchAccessKey セットする batchAccessKey
	 */
	public void setBatchAccessKey(String batchAccessKey) {
		this.batchAccessKey = batchAccessKey;
	}
	/**
	 * @return shibbolethLoginid
	 */
	public String getShibbolethLoginid() {
		return shibbolethLoginid;
	}
	/**
	 * @param shibbolethLoginid セットする shibbolethLoginid
	 */
	public void setShibbolethLoginid(String shibbolethLoginid) {
		this.shibbolethLoginid = shibbolethLoginid;
	}
	/**
	 * @return accessUriList
	 */
	public List<String> getAccessUriList() {
		return accessUriList;
	}
	/**
	 * @param accessUriList セットする accessUriList
	 */
	public void setAccessUriList(List<String> accessUriList) {
		this.accessUriList = accessUriList;
	}



}
