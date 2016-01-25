package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class InternshipApplicantDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String internshipKey;

	private String eventUnit;

	private Timestamp internshipSendDate;

	private Timestamp internshipEndDate;

	private String internshipGkFileNeedKbn;

	private String internshipKbn;

	private String internshipMemo;

	private String internshipPartyName;

	private String internshipRange;

	private String internshipRcFileNeedKbn;

	private Timestamp internshipStartDate;

	private String internshipTelno;

	private String internshipTitle;

	private String partyCode;

	private String publicFlag;

	private String subjectInsKbn;

	private Timestamp updDate;

	private String updUserKey;

	private String decisionKbn;

	private String decisionKbnName;

	private String partyKbn;

	private String partyNameEn;

	private String partyName;

	public InternshipApplicantDto() {
		super();
	}

	public InternshipApplicantDto(String internshipKey,
			String internshipKbn,
			Date internshipStartDate,
			Date internshipEndDate,
			String internshipTitle,
			Date internshipSendDate,
			String internshipRange,
			String internshipPartyName,
			String internshipTelno,
			String internshipRcFileNeedKbn,
			String internshipGkFileNeedKbn,
			String internshipMemo,
			String publicFlag,
			String eventUnit,
			String subjectInsKbn,
			Date updDate,
			String updUserKey,
			String decisionKbn,
			String decisionKbnName,
			String partyCode,
			String partyName,
			String partyNameEn){

		super();

		this.internshipKey = internshipKey;
		this.internshipKbn = internshipKbn;
		// java.util.Date to Timestamp変換
		if (internshipStartDate != null) {
			this.internshipStartDate = new Timestamp(DateUtils.truncate(internshipStartDate, Calendar.MILLISECOND).getTime());
		}
		// java.util.Date to Timestamp変換
		if (internshipEndDate != null) {
			this.internshipEndDate = new Timestamp(DateUtils.truncate(internshipEndDate, Calendar.MILLISECOND).getTime());
		}
		// java.util.Date to Timestamp変換
		this.internshipTitle = internshipTitle;
		if (internshipSendDate != null) {
			this.internshipSendDate = new Timestamp(DateUtils.truncate(internshipSendDate, Calendar.MILLISECOND).getTime());
		}
		this.internshipRange = internshipRange;
		this.internshipPartyName = internshipPartyName;
		this.internshipTelno = internshipTelno;
		this.internshipRcFileNeedKbn = internshipRcFileNeedKbn;
		this.internshipGkFileNeedKbn = internshipGkFileNeedKbn;
		this.internshipMemo = internshipMemo;
		this.publicFlag = publicFlag;
		this.eventUnit = eventUnit;
		this.subjectInsKbn = subjectInsKbn;
		// java.util.Date to Timestamp変換
		if (updDate != null) {
			this.updDate = new Timestamp(DateUtils.truncate(updDate, Calendar.MILLISECOND).getTime());
		}
		this.updUserKey = updUserKey;
		this.decisionKbn = decisionKbn;
		this.decisionKbnName = decisionKbnName;
		this.partyCode = partyCode;
		this.partyName = partyName;
		this.partyNameEn = partyNameEn;

	}

	public String getInternshipKey() {
		return this.internshipKey;
	}

	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
	}

	public String getEventUnit() {
		return this.eventUnit;
	}

	public void setEventUnit(String eventUnit) {
		this.eventUnit = eventUnit;
	}

	public Timestamp getInternshipSendDate() {
		return this.internshipSendDate;
	}

	public void setInternshipSendDate(Timestamp internshipSendDate) {
		this.internshipSendDate = internshipSendDate;
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

	public String getDecisionKbn() {
		return this.decisionKbn;
	}

	public void setDecisionKbn(String decisionKbn) {
		this.decisionKbn = decisionKbn;
	}

	public String getPartyKbn() {
		return this.partyKbn;
	}

	public void setPartyKbn(String partyKbn) {
		this.partyKbn = partyKbn;
	}

	public String getPartyNameEn() {
		return this.partyNameEn;
	}

	public void setPartyNameEn(String partyNameEn) {
		this.partyNameEn = partyNameEn;
	}

	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/**
	 * @return decisionKbnName
	 */
	public String getDecisionKbnName() {
		return decisionKbnName;
	}

	/**
	 * @param decisionKbnName セットする decisionKbnName
	 */
	public void setDecisionKbnName(String decisionKbnName) {
		this.decisionKbnName = decisionKbnName;
	}

}
