package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;



/**
 * The persistent class for the ev_event_tbl database table.
 * 
 */
@Entity
@Table(name="ev_event_tbl")
@NamedQuery(name="EvEventTbl.findAll", query="SELECT e FROM EvEventTbl e")
public class EvEventTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="EV_EVENT_TBL_EVENTKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="EV_EVENT_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EV_EVENT_TBL_EVENTKEY_GENERATOR")
	@Column(name="event_key")
	private String eventKey;

	@Column(name="batch_status")
	private String batchStatus;

	@Column(name="event_memo")
	private String eventMemo;

	@Column(name="event_place")
	private String eventPlace;

	@Column(name="event_recruit")
	private String eventRecruit;

	@Column(name="event_send_date")
	private Timestamp eventSendDate;

	@Column(name="event_start_date")
	private Timestamp eventStartDate;

	@Column(name="event_telno")
	private String eventTelno;

	@Column(name="event_title")
	private String eventTitle;

	@Column(name="event_unit")
	private String eventUnit;

	@Column(name="party_code")
	private String partyCode;

	@Column(name="party_name")
	private String partyName;

	@Column(name="public_flag")
	private String publicFlag;

	@Column(name="subject_ins_kbn")
	private String subjectInsKbn;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to EvEventPublicTbl
	@OneToMany(mappedBy="evEventTbl")
	private List<EvEventPublicTbl> evEventPublicTbls;

	//bi-directional many-to-one association to EvEventRelSubjectTbl
	@OneToMany(mappedBy="evEventTbl")
	private List<EvEventRelSubjectTbl> evEventRelSubjectTbls;

	//bi-directional many-to-one association to EvEventUploadTbl
	@OneToMany(mappedBy="evEventTbl")
	private List<EvEventUploadTbl> evEventUploadTbls;
	
	public EvEventTbl() {
	}

	public String getEventKey() {
		return this.eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getBatchStatus() {
		return this.batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
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
	
	public List<EvEventPublicTbl> getEvEventPublicTbls() {
		return this.evEventPublicTbls;
	}

	public void setEvEventPublicTbls(List<EvEventPublicTbl> evEventPublicTbls) {
		this.evEventPublicTbls = evEventPublicTbls;
	}

	public EvEventPublicTbl addEvEventPublicTbl(EvEventPublicTbl evEventPublicTbl) {
		getEvEventPublicTbls().add(evEventPublicTbl);
		evEventPublicTbl.setEvEventTbl(this);

		return evEventPublicTbl;
	}

	public EvEventPublicTbl removeEvEventPublicTbl(EvEventPublicTbl evEventPublicTbl) {
		getEvEventPublicTbls().remove(evEventPublicTbl);
		evEventPublicTbl.setEvEventTbl(null);

		return evEventPublicTbl;
	}

	public List<EvEventRelSubjectTbl> getEvEventRelSubjectTbls() {
		return this.evEventRelSubjectTbls;
	}

	public void setEvEventRelSubjectTbls(List<EvEventRelSubjectTbl> evEventRelSubjectTbls) {
		this.evEventRelSubjectTbls = evEventRelSubjectTbls;
	}

	public EvEventRelSubjectTbl addEvEventRelSubjectTbl(EvEventRelSubjectTbl evEventRelSubjectTbl) {
		getEvEventRelSubjectTbls().add(evEventRelSubjectTbl);
		evEventRelSubjectTbl.setEvEventTbl(this);

		return evEventRelSubjectTbl;
	}

	public EvEventRelSubjectTbl removeEvEventRelSubjectTbl(EvEventRelSubjectTbl evEventRelSubjectTbl) {
		getEvEventRelSubjectTbls().remove(evEventRelSubjectTbl);
		evEventRelSubjectTbl.setEvEventTbl(null);

		return evEventRelSubjectTbl;
	}

	public List<EvEventUploadTbl> getEvEventUploadTbls() {
		return this.evEventUploadTbls;
	}

	public void setEvEventUploadTbls(List<EvEventUploadTbl> evEventUploadTbls) {
		this.evEventUploadTbls = evEventUploadTbls;
	}

	public EvEventUploadTbl addEvEventUploadTbl(EvEventUploadTbl evEventUploadTbl) {
		getEvEventUploadTbls().add(evEventUploadTbl);
		evEventUploadTbl.setEvEventTbl(this);

		return evEventUploadTbl;
	}

	public EvEventUploadTbl removeEvEventUploadTbl(EvEventUploadTbl evEventUploadTbl) {
		getEvEventUploadTbls().remove(evEventUploadTbl);
		evEventUploadTbl.setEvEventTbl(null);

		return evEventUploadTbl;
	}

}