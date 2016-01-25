/*
* ファイル名：SupportForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.search;

import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.common.CommonForm;

/**
 * <B>SupportFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class SearchForm extends CommonForm {

	// 検索条件
	@CharLength(max = 1000)
	private String searchKeyword; // 検索条件：キーワード

	private String searchAffiliation; // 検索条件：所属機関
	private String searchBigField; // 検索条件：研究分野（大分類）
	private String searchMiddleField; // 検索条件：研究分野（中分類）
	private String searchSmallField; // 検索条件：研究分野（小分類）

	private String searchUserName; // 検索条件：検索用利用者氏名

	private String searchPartyKbn;

	private String searchPartyCode;

	// 入力データ
	@NotNull
	private String userKey;

	private String userPublicFlag;

	private String publicFlag;

	private String partyCode;

	private String userFamilyName;

	private String userFamilyNameEn;

	private String userMiddleName;

	private String userMiddleNameEn;

	private String userName;

	private String userNameEn;

	private String affiliationName;

	private String degree;

	private String freeInput1;

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchAffiliation() {
		return searchAffiliation;
	}

	public void setSearchAffiliation(String searchAffiliation) {
		this.searchAffiliation = searchAffiliation;
	}

	public String getSearchBigField() {
		return searchBigField;
	}

	public void setSearchBigField(String searchBigField) {
		this.searchBigField = searchBigField;
	}

	public String getSearchMiddleField() {
		return searchMiddleField;
	}

	public void setSearchMiddleField(String searchMiddleField) {
		this.searchMiddleField = searchMiddleField;
	}

	public String getSearchSmallField() {
		return searchSmallField;
	}

	public void setSearchSmallField(String searchSmallField) {
		this.searchSmallField = searchSmallField;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUserFamilyName() {
		return userFamilyName;
	}

	public void setUserFamilyName(String userFamilyName) {
		this.userFamilyName = userFamilyName;
	}

	public String getUserFamilyNameEn() {
		return userFamilyNameEn;
	}

	public void setUserFamilyNameEn(String userFamilyNameEn) {
		this.userFamilyNameEn = userFamilyNameEn;
	}

	public String getUserMiddleName() {
		return userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserMiddleNameEn() {
		return userMiddleNameEn;
	}

	public void setUserMiddleNameEn(String userMiddleNameEn) {
		this.userMiddleNameEn = userMiddleNameEn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameEn() {
		return userNameEn;
	}

	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}

	public String getAffiliationName() {
		return affiliationName;
	}

	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getFreeInput1() {
		return freeInput1;
	}

	public void setFreeInput1(String freeInput1) {
		this.freeInput1 = freeInput1;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getUserPublicFlag() {
		return userPublicFlag;
	}

	public void setUserPublicFlag(String userPublicFlag) {
		this.userPublicFlag = userPublicFlag;
	}

	public String getPublicFlag() {
		return publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getSearchPartyKbn() {
		return searchPartyKbn;
	}

	public void setSearchPartyKbn(String searchPartyKbn) {
		this.searchPartyKbn = searchPartyKbn;
	}

	public String getSearchPartyCode() {
		return searchPartyCode;
	}

	public void setSearchPartyCode(String searchPartyCode) {
		this.searchPartyCode = searchPartyCode;
	}


}
