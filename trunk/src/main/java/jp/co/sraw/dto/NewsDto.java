package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class NewsDto implements Serializable {

	private static long serialVersionUID = 1L;

	private String refDataKey;

	private String supportTitle;

	private String supportContent;

	private Timestamp supportStartDate;

	private String eventTelno;

	private String eventPlace;

	private String partyName;

	private String eventMemo;

	private String eventTitle;

	private Timestamp eventStartDate;

	private Timestamp eventSendDate;

	private String internshipTelno;

	private String internshipRange;

	private String internshipMemo;

	private String internshipTitle;

	private String internshipPartyName;

	private Timestamp internshipStartDate;

	private Timestamp internshipEndDate;

	private Timestamp internshipSendDate;

	private String dataKbn;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRefDataKey() {
		return refDataKey;
	}

	public void setRefDataKey(String refDataKey) {
		this.refDataKey = refDataKey;
	}

	public String getSupportTitle() {
		return supportTitle;
	}

	public void setSupportTitle(String supportTitle) {
		this.supportTitle = supportTitle;
	}

	public String getSupportContent() {
		return supportContent;
	}

	public void setSupportContent(String supportContent) {
		this.supportContent = supportContent;
	}

	public String getDataKbn() {
		return dataKbn;
	}

	public void setDataKbn(String dataKbn) {
		this.dataKbn = dataKbn;
	}

	public void setSupportStartDate(Timestamp supportStartDate) {
		this.supportStartDate = supportStartDate;
	}

	public Timestamp getSupportStartDate() {
		return supportStartDate;
	}

	public String getEventTelno() {
		return eventTelno;
	}

	public void setEventTelno(String eventTelno) {
		this.eventTelno = eventTelno;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getEventMemo() {
		return eventMemo;
	}

	public void setEventMemo(String eventMemo) {
		this.eventMemo = eventMemo;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public Timestamp getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Timestamp eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public Timestamp getEventSendDate() {
		return eventSendDate;
	}

	public void setEventSendDate(Timestamp eventSendDate) {
		this.eventSendDate = eventSendDate;
	}

	public String getInternshipTelno() {
		return internshipTelno;
	}

	public void setInternshipTelno(String internshipTelno) {
		this.internshipTelno = internshipTelno;
	}

	public String getInternshipRange() {
		return internshipRange;
	}

	public void setInternshipRange(String internshipRange) {
		this.internshipRange = internshipRange;
	}

	public String getInternshipMemo() {
		return internshipMemo;
	}

	public void setInternshipMemo(String internshipMemo) {
		this.internshipMemo = internshipMemo;
	}

	public String getInternshipTitle() {
		return internshipTitle;
	}

	public void setInternshipTitle(String internshipTitle) {
		this.internshipTitle = internshipTitle;
	}

	public String getInternshipPartyName() {
		return internshipPartyName;
	}

	public void setInternshipPartyName(String internshipPartyName) {
		this.internshipPartyName = internshipPartyName;
	}

	public Timestamp getInternshipStartDate() {
		return internshipStartDate;
	}

	public void setInternshipStartDate(Timestamp internshipStartDate) {
		this.internshipStartDate = internshipStartDate;
	}

	public Timestamp getInternshipEndDate() {
		return internshipEndDate;
	}

	public void setInternshipEndDate(Timestamp internshipEndDate) {
		this.internshipEndDate = internshipEndDate;
	}

	public Timestamp getInternshipSendDate() {
		return internshipSendDate;
	}

	public void setInternshipSendDate(Timestamp internshipSendDate) {
		this.internshipSendDate = internshipSendDate;
	}

}
