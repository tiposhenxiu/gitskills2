/*
* ファイル名：EventForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.event;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonForm;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>EventFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class EventForm extends CommonForm {

	private String searchDateType; // 検索条件：期間

	private String searchPublicFlag; // 検索条件：公開フラグ

	private String eventKey;

	@NotNull
	private String eventMemo;

	private String eventPlace;

	private String eventRecruit;

	@NotNull
	private String eventStartDate;

	@NotNull
	private String eventSendDate;

	@NotNull
	@CharLength(max = 100)
	private String eventTelno;

	@NotNull
	@CharLength(max = 100)
	private String eventTitle;

	private String eventUnit;

	@NotNull
	@CharLength(max = 100)
	private String partyCode;

	private String partyName;

	private String publicFlag;

	private String subjectInsKbn;

	private Timestamp updDate;

	private String updUserKey;

	private String publicKbn;

	private String role;

	private String publicDisabled;

	public String getEventKey() {
		return this.eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getEventMemo() {
		return this.eventMemo;
	}

	public void setEventMemo(String eventMemo) {
		this.eventMemo = eventMemo;
	}

	public String getEventPlace() {
		return this.eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public String getEventRecruit() {
		return this.eventRecruit;
	}

	public void setEventRecruit(String eventRecruit) {
		this.eventRecruit = eventRecruit;
	}

	public String getEventTelno() {
		return this.eventTelno;
	}

	public void setEventTelno(String eventTelno) {
		this.eventTelno = eventTelno;
	}

	public String getEventTitle() {
		return this.eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventUnit() {
		return this.eventUnit;
	}

	public void setEventUnit(String eventUnit) {
		this.eventUnit = eventUnit;
	}

	public String getPartyCode() {
		return this.partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getSubjectInsKbn() {
		return this.subjectInsKbn;
	}

	public void setSubjectInsKbn(String subjectInsKbn) {
		this.subjectInsKbn = subjectInsKbn;
	}

	public String getUpdUserKey() {
		return this.updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public String getEventStartDate() {
		return eventStartDate;
	}

	public Timestamp getEventStartDateAsTimestamp() {
		return DateUtil.getTimestamp(this.eventStartDate, CommonConst.DEFAULT_YYYYMMDD);
	}

	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = StringUtil.htmlFilter(eventStartDate);
	}

	public String getEventSendDate() {
		return eventSendDate;
	}

	public Timestamp getEventSendDateAsTimestamp() {
		return DateUtil.getTimestamp(this.eventSendDate, CommonConst.DEFAULT_YYYYMMDD);
	}

	public void setEventSendDate(String eventSendDate) {
		this.eventSendDate = StringUtil.htmlFilter(eventSendDate);
	}

	public String getPublicKbn() {
		return publicKbn;
	}

	public void setPublicKbn(String publicKbn) {
		this.publicKbn = publicKbn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPublicDisabled() {
		return publicDisabled;
	}

	public void setPublicDisabled(String publicDisabled) {
		this.publicDisabled = publicDisabled;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getSearchDateType() {
		return searchDateType;
	}

	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}

	public String getSearchPublicFlag() {
		return searchPublicFlag;
	}

	public void setSearchPublicFlag(String searchPublicFlag) {
		this.searchPublicFlag = searchPublicFlag;
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

	public String getPublicPartyList() {
		return publicPartyList;
	}

	public void setPublicPartyList(String publicPartyList) {
		this.publicPartyList = publicPartyList;
	}

	public String[] getPublicPartyArray() {
		String[] array = new String[] {};
		if (this.publicPartyList != null)
			array = this.publicPartyList.replace("，", ",").split(",");
		return array;
	}
}
