package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import jp.co.sraw.entity.EvEventView;
import jp.co.sraw.entity.ViewTbl;

public class EvEventViewDto extends ViewDto implements Serializable {

	private static final long serialVersionUID = 1L;

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

	private String partyName;

	private String partyNameEn;

	private String publicFlag;

	private String searchPartyCode;

	private String searchRole;

	private String subjectInsKbn;

	private Timestamp updDate;

	private String updUserKey;

	public EvEventViewDto() {
	}

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

	public Timestamp getEventSendDate() {
		return this.eventSendDate;
	}

	public void setEventSendDate(Timestamp eventSendDate) {
		this.eventSendDate = eventSendDate;
	}

	public Timestamp getEventStartDate() {
		return this.eventStartDate;
	}

	public void setEventStartDate(Timestamp eventStartDate) {
		this.eventStartDate = eventStartDate;
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

	public String getPartyNameEn() {
		return this.partyNameEn;
	}

	public void setPartyNameEn(String partyNameEn) {
		this.partyNameEn = partyNameEn;
	}

	public String getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getSearchPartyCode() {
		return this.searchPartyCode;
	}

	public void setSearchPartyCode(String searchPartyCode) {
		this.searchPartyCode = searchPartyCode;
	}

	public String getSearchRole() {
		return this.searchRole;
	}

	public void setSearchRole(String searchRole) {
		this.searchRole = searchRole;
	}

	public String getSubjectInsKbn() {
		return this.subjectInsKbn;
	}

	public void setSubjectInsKbn(String subjectInsKbn) {
		this.subjectInsKbn = subjectInsKbn;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUserKey() {
		return this.updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	@Override
	public ViewDto getViewDto() {
		EvEventViewDto dto = new EvEventViewDto();
		return dto;
	}

	@Override
	public String getKbn(ViewTbl tbl) {
		return ((EvEventView) tbl).getPartyCode();
	}

}
