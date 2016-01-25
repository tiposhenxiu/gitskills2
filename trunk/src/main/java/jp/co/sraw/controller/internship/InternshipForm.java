/*
* ファイル名：HomeForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.internship;

import java.sql.Timestamp;

import jp.co.sraw.common.CommonForm;

/**
 * <B>InternshipFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class InternshipForm extends CommonForm {

	private String searchDateType; // 検索条件：期間

	private String searchPublicFlag; // 検索条件：公開フラグ

	private String internshipKey;

	private String eventUnit;

	private Timestamp internshipSendDate;

	private Timestamp internshipEndDate;

	private String internshipGkFileNeedKbn;

	private String internshipKbn;

	private String internshipMemo;

	private String internshipPartyName;

	private String internshipRange;

	private String internshipRcFile;

	private String internshipGkFile;

	private String internshipRcFileNeedKbn;

	private Timestamp internshipStartDate;

	private String internshipTelno;

	private String internshipTitle;

	private String partyCode;

	private String publicFlag;

	private String publicKbn;

	private String subjectInsKbn;

	private Timestamp updDate;

	private String updUserKey;

	public String getInternshipKey() {
		return internshipKey;
	}

	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
	}

	public String getEventUnit() {
		return eventUnit;
	}

	public void setEventUnit(String eventUnit) {
		this.eventUnit = eventUnit;
	}

	public Timestamp getInternshipSendDate() {
		return internshipSendDate;
	}

	public void setInternshipSendDate(Timestamp internshipSendDate) {
		this.internshipSendDate = internshipSendDate;
	}

	public Timestamp getInternshipEndDate() {
		return internshipEndDate;
	}

	public void setInternshipEndDate(Timestamp internshipEndDate) {
		this.internshipEndDate = internshipEndDate;
	}

	public String getInternshipGkFileNeedKbn() {
		return internshipGkFileNeedKbn;
	}

	public void setInternshipGkFileNeedKbn(String internshipGkFileNeedKbn) {
		this.internshipGkFileNeedKbn = internshipGkFileNeedKbn;
	}

	public String getInternshipGkFile() {
		return internshipGkFile;
	}

	public void setInternshipGkFile(String internshipGkFile) {
		this.internshipGkFile = internshipGkFile;
	}

	public String getInternshipKbn() {
		return internshipKbn;
	}

	public void setInternshipKbn(String internshipKbn) {
		this.internshipKbn = internshipKbn;
	}

	public String getInternshipMemo() {
		return internshipMemo;
	}

	public void setInternshipMemo(String internshipMemo) {
		this.internshipMemo = internshipMemo;
	}

	public String getInternshipPartyName() {
		return internshipPartyName;
	}

	public void setInternshipPartyName(String internshipPartyName) {
		this.internshipPartyName = internshipPartyName;
	}

	public String getInternshipRange() {
		return internshipRange;
	}

	public void setInternshipRange(String internshipRange) {
		this.internshipRange = internshipRange;
	}

	public String getInternshipRcFileNeedKbn() {
		return internshipRcFileNeedKbn;
	}

	public void setInternshipRcFileNeedKbn(String internshipRcFileNeedKbn) {
		this.internshipRcFileNeedKbn = internshipRcFileNeedKbn;
	}

	public String getInternshipRcFile() {
		return internshipRcFile;
	}

	public void setInternshipRcFile(String internshipRcFile) {
		this.internshipRcFile = internshipRcFile;
	}

	public Timestamp getInternshipStartDate() {
		return internshipStartDate;
	}

	public void setInternshipStartDate(Timestamp internshipStartDate) {
		this.internshipStartDate = internshipStartDate;
	}

	public String getInternshipTelno() {
		return internshipTelno;
	}

	public void setInternshipTelno(String internshipTelno) {
		this.internshipTelno = internshipTelno;
	}

	public String getInternshipTitle() {
		return internshipTitle;
	}

	public void setInternshipTitle(String internshipTitle) {
		this.internshipTitle = internshipTitle;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPublicFlag() {
		return publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getPublicKbn() {
		return publicKbn;
	}

	public void setPublicKbn(String publicKbn) {
		this.publicKbn = publicKbn;
	}

	public String getSubjectInsKbn() {
		return subjectInsKbn;
	}

	public void setSubjectInsKbn(String subjectInsKbn) {
		this.subjectInsKbn = subjectInsKbn;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUserKey() {
		return updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public String getSearchPublicFlag() {
		return searchPublicFlag;
	}

	public void setSearchPublicFlag(String searchPublicFlag) {
		this.searchPublicFlag = searchPublicFlag;
	}

	public String getSearchDateType() {
		return searchDateType;
	}

	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}

	private String publicItemArray;

	/**
	 * @return publicItemArray
	 */
	public String[] getPublicItemArray() {
		String[] array = new String[] {};
		if (this.publicItemArray != null)
			array = this.publicItemArray.replace("，", ",").split(",");
		return array;
	}

	/**
	 * @param publicItemArray
	 *            セットする publicItemArray
	 */
	public void setPublicItemArray(String[] publicItemArray) {
		String separator = "";
		String sybCode = "";
		for (int i = 0; i < publicItemArray.length; i++) {
			sybCode = sybCode + separator + publicItemArray[i];
			separator = ",";
		}
		this.publicItemArray = sybCode;
	}

	public boolean checkHasPublicItem(String kbh) {
		return this.publicItemArray.contains(kbh);
	}

	private String publicPartyList;

	public void setPublicPartyList(String publicPartyList) {
		this.publicPartyList = publicPartyList;
	}

	public String[] getPublicPartyList() {
		String[] array = new String[] {};
		if (this.publicPartyList != null)
			array = this.publicPartyList.replace("，", ",").split(",");
		return array;
	}
}
