package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the it_intern_view database table.
 * 
 */
@Entity
@Table(name="it_intern_view")
@NamedQuery(name="ItInternView.findAll", query="SELECT i FROM ItInternView i")
public class ItInternView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="internship_key")
	private String internshipKey;
	
	@Column(name="event_unit")
	private String eventUnit;

	@Column(name="internship_end_date")
	private Timestamp internshipEndDate;

	@Column(name="internship_gk_file_need_kbn")
	private String internshipGkFileNeedKbn;

	@Column(name="internship_kbn")
	private String internshipKbn;

	@Column(name="internship_memo")
	private String internshipMemo;

	@Column(name="internship_party_name")
	private String internshipPartyName;

	@Column(name="internship_range")
	private String internshipRange;

	@Column(name="internship_rc_file_need_kbn")
	private String internshipRcFileNeedKbn;

	@Column(name="internship_send_date")
	private Timestamp internshipSendDate;

	@Column(name="internship_start_date")
	private Timestamp internshipStartDate;

	@Column(name="internship_telno")
	private String internshipTelno;

	@Column(name="internship_title")
	private String internshipTitle;

	@Column(name="party_code")
	private String partyCode;

	@Column(name="party_name")
	private String partyName;

	@Column(name="party_name_en")
	private String partyNameEn;

	@Column(name="public_flag")
	private String publicFlag;

	@Column(name="search_party_code")
	private String searchPartyCode;

	@Column(name="search_role")
	private String searchRole;

	@Column(name="subject_ins_kbn")
	private String subjectInsKbn;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	public ItInternView() {
	}

	public String getEventUnit() {
		return this.eventUnit;
	}

	public void setEventUnit(String eventUnit) {
		this.eventUnit = eventUnit;
	}

	public Timestamp getInternshipEndDate() {
		return this.internshipEndDate;
	}

	public void setInternshipEndDate(Timestamp internshipEndDate) {
		this.internshipEndDate = internshipEndDate;
	}

	public String getInternshipGkFileNeedKbn() {
		return this.internshipGkFileNeedKbn;
	}

	public void setInternshipGkFileNeedKbn(String internshipGkFileNeedKbn) {
		this.internshipGkFileNeedKbn = internshipGkFileNeedKbn;
	}

	public String getInternshipKbn() {
		return this.internshipKbn;
	}

	public void setInternshipKbn(String internshipKbn) {
		this.internshipKbn = internshipKbn;
	}

	public String getInternshipKey() {
		return this.internshipKey;
	}

	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
	}

	public String getInternshipMemo() {
		return this.internshipMemo;
	}

	public void setInternshipMemo(String internshipMemo) {
		this.internshipMemo = internshipMemo;
	}

	public String getInternshipPartyName() {
		return this.internshipPartyName;
	}

	public void setInternshipPartyName(String internshipPartyName) {
		this.internshipPartyName = internshipPartyName;
	}

	public String getInternshipRange() {
		return this.internshipRange;
	}

	public void setInternshipRange(String internshipRange) {
		this.internshipRange = internshipRange;
	}

	public String getInternshipRcFileNeedKbn() {
		return this.internshipRcFileNeedKbn;
	}

	public void setInternshipRcFileNeedKbn(String internshipRcFileNeedKbn) {
		this.internshipRcFileNeedKbn = internshipRcFileNeedKbn;
	}

	public Timestamp getInternshipSendDate() {
		return this.internshipSendDate;
	}

	public void setInternshipSendDate(Timestamp internshipSendDate) {
		this.internshipSendDate = internshipSendDate;
	}

	public Timestamp getInternshipStartDate() {
		return this.internshipStartDate;
	}

	public void setInternshipStartDate(Timestamp internshipStartDate) {
		this.internshipStartDate = internshipStartDate;
	}

	public String getInternshipTelno() {
		return this.internshipTelno;
	}

	public void setInternshipTelno(String internshipTelno) {
		this.internshipTelno = internshipTelno;
	}

	public String getInternshipTitle() {
		return this.internshipTitle;
	}

	public void setInternshipTitle(String internshipTitle) {
		this.internshipTitle = internshipTitle;
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