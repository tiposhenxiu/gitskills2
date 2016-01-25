package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the ev_event_view database table.
 *
 */
@Entity
@Table(name = "ev_event_view")
@NamedQuery(name = "EvEventView.findAll", query = "SELECT e FROM EvEventView e")
public class EvEventView extends ViewTbl implements Serializable

{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "event_key")
	private String eventKey;

	@Column(name = "event_memo")
	private String eventMemo;

	@Column(name = "event_place")
	private String eventPlace;

	@Column(name = "event_recruit")
	private String eventRecruit;

	@Column(name = "event_send_date")
	private Timestamp eventSendDate;

	@Column(name = "event_start_date")
	private Timestamp eventStartDate;

	@Column(name = "event_telno")
	private String eventTelno;

	@Column(name = "event_title")
	private String eventTitle;

	@Column(name = "event_unit")
	private String eventUnit;

	@Column(name = "party_code")
	private String partyCode;

	@Column(name = "party_name")
	private String partyName;

	@Column(name = "party_name_en")
	private String partyNameEn;

	@Column(name = "public_flag")
	private String publicFlag;

	@Column(name = "search_party_code")
	private String searchPartyCode;

	@Column(name = "search_role")
	private String searchRole;

	@Column(name = "subject_ins_kbn")
	private String subjectInsKbn;

	@Column(name = "upd_date")
	private Timestamp updDate;

	@Column(name = "upd_user_key")
	private String updUserKey;

	public EvEventView() {
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

}