package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cm_schedule_info_view database table.
 *
 */
@Entity
@Table(name="cm_schedule_info_view")
@NamedQuery(name="CmScheduleInfoView.findAll", query="SELECT c FROM CmScheduleInfoView c")
public class CmScheduleInfoView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String pkey;

	@Column(name="data_kbn")
	private String dataKbn;

	@Column(name="party_code")
	private String partyCode;

	@Column(name="ref_key")
	private String refKey;

	@Column(name="schedule_end_date")
	private Timestamp scheduleEndDate;

	@Column(name="schedule_memo")
	private String scheduleMemo;

	@Column(name="schedule_place")
	private String schedulePlace;

	@Column(name="schedule_start_date")
	private Timestamp scheduleStartDate;

	@Column(name="schedule_telno")
	private String scheduleTelno;

	@Column(name="schedule_title")
	private String scheduleTitle;

	@Column(name="schedule_url")
	private String scheduleUrl;

	public CmScheduleInfoView() {
	}

	public String getDataKbn() {
		return this.dataKbn;
	}

	public void setDataKbn(String dataKbn) {
		this.dataKbn = dataKbn;
	}

	public String getPartyCode() {
		return this.partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPkey() {
		return this.pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getRefKey() {
		return this.refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}

	public Timestamp getScheduleEndDate() {
		return this.scheduleEndDate;
	}

	public void setScheduleEndDate(Timestamp scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public String getScheduleMemo() {
		return this.scheduleMemo;
	}

	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}

	public String getSchedulePlace() {
		return this.schedulePlace;
	}

	public void setSchedulePlace(String schedulePlace) {
		this.schedulePlace = schedulePlace;
	}

	public Timestamp getScheduleStartDate() {
		return this.scheduleStartDate;
	}

	public void setScheduleStartDate(Timestamp scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getScheduleTelno() {
		return this.scheduleTelno;
	}

	public void setScheduleTelno(String scheduleTelno) {
		this.scheduleTelno = scheduleTelno;
	}

	public String getScheduleTitle() {
		return this.scheduleTitle;
	}

	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}

	public String getScheduleUrl() {
		return this.scheduleUrl;
	}

	public void setScheduleUrl(String scheduleUrl) {
		this.scheduleUrl = scheduleUrl;
	}

}