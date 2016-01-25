package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the it_intern_recruit_view database table.
 * 
 */
@Entity
@Table(name="it_intern_recruit_view")
@NamedQuery(name="ItInternRecruitView.findAll", query="SELECT i FROM ItInternRecruitView i")
public class ItInternRecruitView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String pkey;
	
	@Column(name="decision_date")
	private Timestamp decisionDate;

	@Column(name="decision_kbn")
	private String decisionKbn;

	@Column(name="decision_user_key")
	private String decisionUserKey;

	@Column(name="gk_file_ins_kbn")
	private String gkFileInsKbn;

	@Column(name="internship_key")
	private String internshipKey;

	@Column(name="party_code")
	private String partyCode;

	@Column(name="party_name")
	private String partyName;

	@Column(name="party_name_en")
	private String partyNameEn;
	
	@Column(name="rc_file_ins_kbn")
	private String rcFileInsKbn;

	@Column(name="recruit_date")
	private Timestamp recruitDate;

	@Column(name="recruit_end_date")
	private Timestamp recruitEndDate;

	@Column(name="recruit_grade")
	private String recruitGrade;

	@Column(name="recruit_memo")
	private String recruitMemo;

	@Column(name="recruit_party_name")
	private String recruitPartyName;

	@Column(name="recruit_start_date")
	private Timestamp recruitStartDate;

	@Column(name="recruit_status")
	private String recruitStatus;

	@Column(name="recruit_teacher")
	private String recruitTeacher;

	private String sex;

	@Column(name="user_family_name")
	private String userFamilyName;

	@Column(name="user_family_name_en")
	private String userFamilyNameEn;

	@Column(name="user_family_name_kn")
	private String userFamilyNameKn;

	@Column(name="user_key")
	private String userKey;

	@Column(name="user_middle_name")
	private String userMiddleName;

	@Column(name="user_middle_name_en")
	private String userMiddleNameEn;

	@Column(name="user_middle_name_kn")
	private String userMiddleNameKn;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_name_en")
	private String userNameEn;

	@Column(name="user_name_kn")
	private String userNameKn;

	public ItInternRecruitView() {
	}

	public Timestamp getDecisionDate() {
		return this.decisionDate;
	}

	public void setDecisionDate(Timestamp decisionDate) {
		this.decisionDate = decisionDate;
	}

	public String getDecisionKbn() {
		return this.decisionKbn;
	}

	public void setDecisionKbn(String decisionKbn) {
		this.decisionKbn = decisionKbn;
	}

	public String getDecisionUserKey() {
		return this.decisionUserKey;
	}

	public void setDecisionUserKey(String decisionUserKey) {
		this.decisionUserKey = decisionUserKey;
	}

	public String getGkFileInsKbn() {
		return this.gkFileInsKbn;
	}

	public void setGkFileInsKbn(String gkFileInsKbn) {
		this.gkFileInsKbn = gkFileInsKbn;
	}

	public String getInternshipKey() {
		return this.internshipKey;
	}

	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
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

	public String getPkey() {
		return this.pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getRcFileInsKbn() {
		return this.rcFileInsKbn;
	}

	public void setRcFileInsKbn(String rcFileInsKbn) {
		this.rcFileInsKbn = rcFileInsKbn;
	}

	public Timestamp getRecruitDate() {
		return this.recruitDate;
	}

	public void setRecruitDate(Timestamp recruitDate) {
		this.recruitDate = recruitDate;
	}

	public Timestamp getRecruitEndDate() {
		return this.recruitEndDate;
	}

	public void setRecruitEndDate(Timestamp recruitEndDate) {
		this.recruitEndDate = recruitEndDate;
	}

	public String getRecruitGrade() {
		return this.recruitGrade;
	}

	public void setRecruitGrade(String recruitGrade) {
		this.recruitGrade = recruitGrade;
	}

	public String getRecruitMemo() {
		return this.recruitMemo;
	}

	public void setRecruitMemo(String recruitMemo) {
		this.recruitMemo = recruitMemo;
	}

	public String getRecruitPartyName() {
		return this.recruitPartyName;
	}

	public void setRecruitPartyName(String recruitPartyName) {
		this.recruitPartyName = recruitPartyName;
	}

	public Timestamp getRecruitStartDate() {
		return this.recruitStartDate;
	}

	public void setRecruitStartDate(Timestamp recruitStartDate) {
		this.recruitStartDate = recruitStartDate;
	}

	public String getRecruitStatus() {
		return this.recruitStatus;
	}

	public void setRecruitStatus(String recruitStatus) {
		this.recruitStatus = recruitStatus;
	}

	public String getRecruitTeacher() {
		return this.recruitTeacher;
	}

	public void setRecruitTeacher(String recruitTeacher) {
		this.recruitTeacher = recruitTeacher;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserFamilyName() {
		return this.userFamilyName;
	}

	public void setUserFamilyName(String userFamilyName) {
		this.userFamilyName = userFamilyName;
	}

	public String getUserFamilyNameEn() {
		return this.userFamilyNameEn;
	}

	public void setUserFamilyNameEn(String userFamilyNameEn) {
		this.userFamilyNameEn = userFamilyNameEn;
	}

	public String getUserFamilyNameKn() {
		return this.userFamilyNameKn;
	}

	public void setUserFamilyNameKn(String userFamilyNameKn) {
		this.userFamilyNameKn = userFamilyNameKn;
	}

	public String getUserKey() {
		return this.userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUserMiddleName() {
		return this.userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserMiddleNameEn() {
		return this.userMiddleNameEn;
	}

	public void setUserMiddleNameEn(String userMiddleNameEn) {
		this.userMiddleNameEn = userMiddleNameEn;
	}

	public String getUserMiddleNameKn() {
		return this.userMiddleNameKn;
	}

	public void setUserMiddleNameKn(String userMiddleNameKn) {
		this.userMiddleNameKn = userMiddleNameKn;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameEn() {
		return this.userNameEn;
	}

	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}

	public String getUserNameKn() {
		return this.userNameKn;
	}

	public void setUserNameKn(String userNameKn) {
		this.userNameKn = userNameKn;
	}

}