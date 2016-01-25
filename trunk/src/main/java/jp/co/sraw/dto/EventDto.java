package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class EventDto implements Serializable {

	private static long serialVersionUID = 1L;

	private String keywords;

	private String eventKey;

	private String eventMemo;

	private String eventPlace;

	private String eventRecruit;

	private Timestamp eventSendDate;

	private Timestamp eventStartDate;

	private String eventTelno;

	private String eventTitle;

	private String eventUnit;

	private String partyCode;

	private Timestamp updDate;

	private String partyName;

	private String publicFlag;

	private String subjectInsKbn;

	private String updUserKey;

	private String role;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getEventMemo() {
		return eventMemo;
	}

	public void setEventMemo(String eventMemo) {
		this.eventMemo = eventMemo;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public String getEventRecruit() {
		return eventRecruit;
	}

	public void setEventRecruit(String eventRecruit) {
		this.eventRecruit = eventRecruit;
	}

	public Timestamp getEventSendDate() {
		return eventSendDate;
	}

	public void setEventSendDate(Timestamp eventSendDate) {
		this.eventSendDate = eventSendDate;
	}

	public Timestamp getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Timestamp eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public String getEventTelno() {
		return eventTelno;
	}

	public void setEventTelno(String eventTelno) {
		this.eventTelno = eventTelno;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventUnit() {
		return eventUnit;
	}

	public void setEventUnit(String eventUnit) {
		this.eventUnit = eventUnit;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPublicFlag() {
		return publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getSubjectInsKbn() {
		return subjectInsKbn;
	}

	public void setSubjectInsKbn(String subjectInsKbn) {
		this.subjectInsKbn = subjectInsKbn;
	}

	public String getUpdUserKey() {
		return updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
