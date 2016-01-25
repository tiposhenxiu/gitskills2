/*
* ファイル名：SupportForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.support;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.common.CommonForm;

/**
 * <B>SupportFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class SupportForm extends CommonForm {

	// 検索条件
	@CharLength(max = 1000)
	private String searchKeyword; // 検索条件：キーワード
	@CharLength(max = 1000)
	private String searchSybCode; // 検索条件：支援制度種別コード
	private String searchDateType; // 検索条件：期間タイプ
	private String searchPartyCode; // 検索条件：組織コード
	private String searchSpkikiKbn; // 検索条件：区分
	private String searchHirakuKbn; // 検索条件：HIRAKUリンク区分
	private String searchAreaKbn; // 検索条件：地域区分
	private String searchPublicFlag; // 検索条件：公開フラグ


	// 入力データ
	@NotNull
	private String supportSpkikiKbn;

	private String supportHirakuKbn;

	private String supportKey;

	private String partyCode;

	@CharLength(max = 256)
	private String supportSybCode;

	@CharLength(max = 2)
	private String supportAreaKbn;

	@CharLength(max = 1000)
	private String supportKeyword;

	/**
	 * yyyyMMdd
	 */
	@NotNull
	private String supportStartDate;

	/**
	 * yyyyMMdd
	 */
	@NotNull
	private String supportEndDate;

	@NotNull
	@CharLength(max = 100)
	private String supportTitle;

	@CharLength(max = 2000)
	private String url;

	@NotNull
	private String supportContent;

	@NotNull
	private String publicFlag;

	private Timestamp supportInsDate;

	private Timestamp updDate;

	private String updUserKey;

	/**
	 * @return supportKeyword
	 */
	public String getSupportKeyword() {
		return supportKeyword;
	}
	/**
	 * @param supportKeyword セットする supportKeyword
	 */
	public void setSupportKeyword(String supportKeyword) {
		if (supportKeyword != null) {
			this.supportKeyword = supportKeyword.replace("，", ",");
		}
	}
	/**
	 * @return searchKeyword
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}
	/**
	 * @param searchKeyword セットする searchKeyword
	 */
	public void setSearchKeyword(String searchKeyword) {
		if (searchKeyword != null) {
			this.searchKeyword = searchKeyword.replace("，", ",");
		}
	}
	/**
	 * @return supportSybCode
	 */
	public String[] getSupportSybCodeArray() {
		String[] array = new String[] {};
		if (this.supportSybCode != null)
			array = this.supportSybCode.replace("，", ",").split(",");
		return array;
	}

	/**
	 * @param supportSybCode セットする supportSybCode
	 */
	public void setSupportSybCodeArray(String[] supportSybCode) {
		String separator = "";
		String sybCode = "";
		for (int i = 0; i < supportSybCode.length; i++) {
			sybCode = sybCode + separator + supportSybCode[i];
			separator = ",";
		}
		this.supportSybCode = sybCode;
	}
	/**
	 * @return searchSybCode
	 */
	public String[] getSearchSybCodeArray() {
		String[] array = new String[] {};
		if (this.searchSybCode != null)
			array = this.searchSybCode.replace("，", ",").split(",");
		return array;
	}

	/**
	 * @param searchSybCode セットする searchSybCode
	 */
	public void setSearchSybCodeArray(String[] searchSybCode) {
		String separator = "";
		String sybCode = "";
		for (int i = 0; i < searchSybCode.length; i++) {
			sybCode = sybCode + separator + searchSybCode[i];
			separator = ",";
		}
		this.searchSybCode = sybCode;
	}

	/**
	 * @return searchSybCode
	 */
	public String getSearchSybCode() {
		return searchSybCode;
	}
	/**
	 * @param searchSybCode セットする searchSybCode
	 */
	public void setSearchSybCode(String searchSybCode) {
		this.searchSybCode = searchSybCode;
	}
	/**
	 * @return searchDateType
	 */
	public String getSearchDateType() {
		return searchDateType;
	}
	/**
	 * @param searchDateType セットする searchDateType
	 */
	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}
	/**
	 * @return searchPartyCode
	 */
	public String getSearchPartyCode() {
		return searchPartyCode;
	}
	/**
	 * @param searchPartyCode セットする searchPartyCode
	 */
	public void setSearchPartyCode(String searchPartyCode) {
		this.searchPartyCode = searchPartyCode;
	}
	/**
	 * @return supportSpkikiKbn
	 */
	public String getSupportSpkikiKbn() {
		return supportSpkikiKbn;
	}
	/**
	 * @param supportSpkikiKbn セットする supportSpkikiKbn
	 */
	public void setSupportSpkikiKbn(String supportSpkikiKbn) {
		this.supportSpkikiKbn = supportSpkikiKbn;
	}
	/**
	 * @return supportKey
	 */
	public String getSupportKey() {
		return supportKey;
	}
	/**
	 * @param supportKey セットする supportKey
	 */
	public void setSupportKey(String supportKey) {
		this.supportKey = supportKey;
	}
	/**
	 * @return partyCode
	 */
	public String getPartyCode() {
		return partyCode;
	}
	/**
	 * @param partyCode セットする partyCode
	 */
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	/**
	 * @return supportSybCode
	 */
	public String getSupportSybCode() {
		return supportSybCode;
	}
	/**
	 * @param supportSybCode セットする supportSybCode
	 */
	public void setSupportSybCode(String supportSybCode) {
		this.supportSybCode = supportSybCode;
	}
	/**
	 * @return supportTitle
	 */
	public String getSupportTitle() {
		return supportTitle;
	}
	/**
	 * @param supportTitle セットする supportTitle
	 */
	public void setSupportTitle(String supportTitle) {
		this.supportTitle = supportTitle;
	}
	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url セットする url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return supportContent
	 */
	public String getSupportContent() {
		return supportContent;
	}
	/**
	 * @param supportContent セットする supportContent
	 */
	public void setSupportContent(String supportContent) {
		this.supportContent = supportContent;
	}
	/**
	 * @return publicFlag
	 */
	public String getPublicFlag() {
		return publicFlag;
	}
	/**
	 * @param publicFlag セットする publicFlag
	 */
	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}
	/**
	 * @return updUserKey
	 */
	public String getUpdUserKey() {
		return updUserKey;
	}
	/**
	 * @param updUserKey セットする updUserKey
	 */
	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}
	/**
	 * @return searchSpkikiKbn
	 */
	public String getSearchSpkikiKbn() {
		return searchSpkikiKbn;
	}
	/**
	 * @param searchSpkikiKbn セットする searchSpkikiKbn
	 */
	public void setSearchSpkikiKbn(String searchSpkikiKbn) {
		this.searchSpkikiKbn = searchSpkikiKbn;
	}
	/**
	 * @return searchPublicFlag
	 */
	public String getSearchPublicFlag() {
		return searchPublicFlag;
	}
	/**
	 * @param searchPublicFlag セットする searchPublicFlag
	 */
	public void setSearchPublicFlag(String searchPublicFlag) {
		this.searchPublicFlag = searchPublicFlag;
	}
	/**
	 * @return supportHirakuKbn
	 */
	public String getSupportHirakuKbn() {
		return supportHirakuKbn;
	}
	/**
	 * @param supportHirakuKbn セットする supportHirakuKbn
	 */
	public void setSupportHirakuKbn(String supportHirakuKbn) {
		this.supportHirakuKbn = supportHirakuKbn;
	}
	/**
	 * @return supportStartDate
	 */
	public String getSupportStartDate() {
		return supportStartDate;
	}
	/**
	 * @param supportStartDate セットする supportStartDate
	 */
	public void setSupportStartDate(String supportStartDate) {
		this.supportStartDate = supportStartDate;
	}
	/**
	 * @return supportEndDate
	 */
	public String getSupportEndDate() {
		return supportEndDate;
	}
	/**
	 * @param supportEndDate セットする supportEndDate
	 */
	public void setSupportEndDate(String supportEndDate) {
		this.supportEndDate = supportEndDate;
	}
	/**
	 * @return supportInsDate
	 */
	public Timestamp getSupportInsDate() {
		return supportInsDate;
	}
	/**
	 * @param supportInsDate セットする supportInsDate
	 */
	public void setSupportInsDate(Timestamp supportInsDate) {
		this.supportInsDate = supportInsDate;
	}
	/**
	 * @return updDate
	 */
	public Timestamp getUpdDate() {
		return updDate;
	}
	/**
	 * @param updDate セットする updDate
	 */
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
	/**
	 * @return supportAreaKbn
	 */
	public String getSupportAreaKbn() {
		return supportAreaKbn;
	}
	/**
	 * @param supportAreaKbn セットする supportAreaKbn
	 */
	public void setSupportAreaKbn(String supportAreaKbn) {
		this.supportAreaKbn = supportAreaKbn;
	}
	/**
	 * @return searchHirakuKbn
	 */
	public String getSearchHirakuKbn() {
		return searchHirakuKbn;
	}
	/**
	 * @param searchHirakuKbn セットする searchHirakuKbn
	 */
	public void setSearchHirakuKbn(String searchHirakuKbn) {
		this.searchHirakuKbn = searchHirakuKbn;
	}
	/**
	 * @return searchAreaKbn
	 */
	public String getSearchAreaKbn() {
		return searchAreaKbn;
	}
	/**
	 * @param searchAreaKbn セットする searchAreaKbn
	 */
	public void setSearchAreaKbn(String searchAreaKbn) {
		this.searchAreaKbn = searchAreaKbn;
	}
}
