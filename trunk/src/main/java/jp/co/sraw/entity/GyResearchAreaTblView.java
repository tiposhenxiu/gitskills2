package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the gy_research_area_tbl_view database table.
 * 
 */
@Entity
@Table(name="gy_research_area_tbl_view")
@NamedQuery(name="GyResearchAreaTblView.findAll", query="SELECT g FROM GyResearchAreaTblView g")
public class GyResearchAreaTblView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gyoseki_key")
	private String gyosekiKey;

	@Column(name="affiliation_name")
	private String affiliationName;

	private String country;

	private String degree;

	private String fieldid;

	private String fieldname;

	@Column(name="free_input1")
	private String freeInput1;

	@Column(name="free_input10")
	private String freeInput10;

	@Column(name="free_input2")
	private String freeInput2;

	@Column(name="free_input3")
	private String freeInput3;

	@Column(name="free_input4")
	private String freeInput4;

	@Column(name="free_input5")
	private String freeInput5;

	@Column(name="free_input6")
	private String freeInput6;

	@Column(name="free_input7")
	private String freeInput7;

	@Column(name="free_input8")
	private String freeInput8;

	@Column(name="free_input9")
	private String freeInput9;

	@Column(name="jst_code")
	private String jstCode;

	@Column(name="mail_address")
	private String mailAddress;

	@Column(name="mail_setting")
	private String mailSetting;

	@Column(name="party_code")
	private String partyCode;

	@Column(name="public_flag")
	private String publicFlag;

	@Column(name="research_area")
	private String researchArea;

	@Column(name="research_area_language")
	private String researchAreaLanguage;

	@Column(name="research_subject")
	private String researchSubject;

	private String sex;

	@Column(name="student_id")
	private String studentId;

	private String subjectid;

	private String subjectname;

	private String summary;

	private String summaryid;

	@Column(name="teacher_name")
	private String teacherName;

	private String telno;

	@Column(name="upload_key")
	private String uploadKey;

	@Column(name="user_family_name")
	private String userFamilyName;

	@Column(name="user_family_name_en")
	private String userFamilyNameEn;

	@Column(name="user_family_name_kn")
	private String userFamilyNameKn;

	@Column(name="user_kbn")
	private String userKbn;

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

	@Column(name="user_public_flag")
	private String userPublicFlag;

	public GyResearchAreaTblView() {
	}

	public String getAffiliationName() {
		return this.affiliationName;
	}

	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getFieldid() {
		return this.fieldid;
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

	public String getFieldname() {
		return this.fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getFreeInput1() {
		return this.freeInput1;
	}

	public void setFreeInput1(String freeInput1) {
		this.freeInput1 = freeInput1;
	}

	public String getFreeInput10() {
		return this.freeInput10;
	}

	public void setFreeInput10(String freeInput10) {
		this.freeInput10 = freeInput10;
	}

	public String getFreeInput2() {
		return this.freeInput2;
	}

	public void setFreeInput2(String freeInput2) {
		this.freeInput2 = freeInput2;
	}

	public String getFreeInput3() {
		return this.freeInput3;
	}

	public void setFreeInput3(String freeInput3) {
		this.freeInput3 = freeInput3;
	}

	public String getFreeInput4() {
		return this.freeInput4;
	}

	public void setFreeInput4(String freeInput4) {
		this.freeInput4 = freeInput4;
	}

	public String getFreeInput5() {
		return this.freeInput5;
	}

	public void setFreeInput5(String freeInput5) {
		this.freeInput5 = freeInput5;
	}

	public String getFreeInput6() {
		return this.freeInput6;
	}

	public void setFreeInput6(String freeInput6) {
		this.freeInput6 = freeInput6;
	}

	public String getFreeInput7() {
		return this.freeInput7;
	}

	public void setFreeInput7(String freeInput7) {
		this.freeInput7 = freeInput7;
	}

	public String getFreeInput8() {
		return this.freeInput8;
	}

	public void setFreeInput8(String freeInput8) {
		this.freeInput8 = freeInput8;
	}

	public String getFreeInput9() {
		return this.freeInput9;
	}

	public void setFreeInput9(String freeInput9) {
		this.freeInput9 = freeInput9;
	}

	public String getGyosekiKey() {
		return this.gyosekiKey;
	}

	public void setGyosekiKey(String gyosekiKey) {
		this.gyosekiKey = gyosekiKey;
	}

	public String getJstCode() {
		return this.jstCode;
	}

	public void setJstCode(String jstCode) {
		this.jstCode = jstCode;
	}

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getMailSetting() {
		return this.mailSetting;
	}

	public void setMailSetting(String mailSetting) {
		this.mailSetting = mailSetting;
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

	public String getResearchArea() {
		return this.researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}

	public String getResearchAreaLanguage() {
		return this.researchAreaLanguage;
	}

	public void setResearchAreaLanguage(String researchAreaLanguage) {
		this.researchAreaLanguage = researchAreaLanguage;
	}

	public String getResearchSubject() {
		return this.researchSubject;
	}

	public void setResearchSubject(String researchSubject) {
		this.researchSubject = researchSubject;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummaryid() {
		return this.summaryid;
	}

	public void setSummaryid(String summaryid) {
		this.summaryid = summaryid;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTelno() {
		return this.telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getUploadKey() {
		return this.uploadKey;
	}

	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
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

	public String getUserKbn() {
		return this.userKbn;
	}

	public void setUserKbn(String userKbn) {
		this.userKbn = userKbn;
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

	public String getUserPublicFlag() {
		return this.userPublicFlag;
	}

	public void setUserPublicFlag(String userPublicFlag) {
		this.userPublicFlag = userPublicFlag;
	}

}