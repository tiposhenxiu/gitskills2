package jp.co.sraw.controller.portfolio.form;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.maru.m4hv.extensions.constraints.CharLength;
import org.maru.m4hv.extensions.constraints.Katakana;

import jp.co.sraw.common.CommonForm;

/**
 * <B>ProfileFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */

public class ProfileForm extends CommonForm {

	//グループの定義
	public interface Doctor {} // 若手研究員
	public interface Teacher {} // 教職員
	public interface Consul {} // 相談員
	public interface Mgmt {} // 事務局・大学
	public interface Party {} // 企業

	private String userKey;

	private String affiliationName;

	private String country;

	@NotNull(groups = {Doctor.class, Teacher.class })
	private String degree;

	@CharLength(max = 10000, groups = { Doctor.class , Teacher.class , Mgmt.class , Party.class })
	private String freeInput1;

	private String freeInput10;

	@CharLength(max = 10000, groups = { Consul.class , Mgmt.class , Party.class })
	private String freeInput2;

	@CharLength(max = 10000, groups = { Party.class })
	private String freeInput3;

	@CharLength(max = 10000, groups = { Party.class })
	private String freeInput4;

	@CharLength(max = 10000, groups = { Party.class })
	private String freeInput5;

	private String freeInput6;

	private String freeInput7;

	private String freeInput8;

	private String freeInput9;

	private Timestamp insDate;

	private String jstCode;

	private String mailAddress;

	private String mailSetting;

	private String partyCode;

	private String researchArea;

	@NotNull(groups = { Mgmt.class , Party.class })
	@CharLength(max = 100, groups = {Doctor.class, Teacher.class, Mgmt.class , Party.class })
	private String researchSubject;

	@NotNull(groups = { Doctor.class , Teacher.class })
	private String sex;

	@Pattern(regexp = "[a-zA-Z0-9]*", groups = {Doctor.class, Teacher.class })
	private String studentId;

	private String teacherName;

	@NotNull(groups = { Mgmt.class , Party.class })
	@Pattern(regexp = "[a-zA-Z0-9]*", groups = { Mgmt.class , Party.class })
	@CharLength(max = 12, groups = { Mgmt.class , Party.class })
	private String telno;

	private Timestamp updDate;

	private String updUserKey;

	private String uploadKey;

	private String imageFileName;

	private String url;

	@NotNull(groups = { Doctor.class, Teacher.class, Consul.class, Mgmt.class , Party.class })
	@CharLength(max = 100, groups = { Doctor.class, Teacher.class, Consul.class, Mgmt.class , Party.class })
	private String userFamilyName;

	@NotNull(groups = { Doctor.class, Teacher.class, Consul.class })
	@Pattern(regexp = "[a-zA-Z0-9]*", groups = { Doctor.class, Teacher.class, Consul.class })
	@CharLength(max = 100, groups = { Doctor.class, Teacher.class, Consul.class })
	private String userFamilyNameEn;

	@NotNull(groups = { Doctor.class, Teacher.class, Consul.class })
	@Katakana(groups = { Doctor.class, Teacher.class, Consul.class })
	@CharLength(max = 100, groups = { Doctor.class, Teacher.class, Consul.class })
	private String userFamilyNameKn;

	private String userKbn;

	@CharLength(max = 100, groups = { Doctor.class, Teacher.class, Consul.class })
	private String userMiddleName;

	@CharLength(max = 100, groups = { Doctor.class, Teacher.class, Consul.class })
	@Pattern(regexp = "[a-zA-Z0-9]*", groups = { Doctor.class, Teacher.class, Consul.class })
	private String userMiddleNameEn;

	@Katakana(groups = { Doctor.class, Teacher.class, Consul.class })
	@CharLength(max = 100, groups = { Doctor.class, Teacher.class, Consul.class })
	private String userMiddleNameKn;

	@NotNull(groups = { Doctor.class, Teacher.class, Consul.class })
	@CharLength(max = 100, groups = { Doctor.class, Teacher.class, Consul.class })
	private String userName;

	@NotNull(groups = { Doctor.class, Teacher.class, Consul.class })
	@Pattern(regexp = "[a-zA-Z0-9]*", groups = { Doctor.class, Teacher.class, Consul.class })
	@CharLength(max = 100)
	private String userNameEn;

	@NotNull(groups = { Doctor.class, Teacher.class, Consul.class })
	@Katakana(groups = { Doctor.class, Teacher.class, Consul.class })
	@CharLength(max = 100, groups = { Doctor.class, Teacher.class, Consul.class })
	private String userNameKn;

	private String userPublicFlag;

	public String getUserKey() {
		return this.userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
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

	public Timestamp getInsDate() {
		return this.insDate;
	}

	public void setInsDate(Timestamp insDate) {
		this.insDate = insDate;
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

	public String getResearchArea() {
		return this.researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
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

	public String getUploadKey() {
		return this.uploadKey;
	}

	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
	}

	public String getImageFileName() {
		return this.imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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