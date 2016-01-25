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
 * The persistent class for the us_user_tbl database table.
 * 
 */
@Entity
@Table(name="us_user_tbl")
@NamedQuery(name="UsUserTbl.findAll", query="SELECT u FROM UsUserTbl u")
public class UsUserTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="US_USER_TBL_USERKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="US_USER_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="US_USER_TBL_USERKEY_GENERATOR")
	@Column(name="user_key")
	private String userKey;

	@Column(name="affiliation_name")
	private String affiliationName;

	private String country;

	private String degree;

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

	@Column(name="ins_date")
	private Timestamp insDate;

	@Column(name="jst_code")
	private String jstCode;

	@Column(name="mail_address")
	private String mailAddress;

	@Column(name="mail_setting")
	private String mailSetting;

	@Column(name="party_code")
	private String partyCode;

	@Column(name="research_area")
	private String researchArea;

	@Column(name="research_subject")
	private String researchSubject;

	private String sex;

	@Column(name="student_id")
	private String studentId;

	@Column(name="teacher_name")
	private String teacherName;

	private String telno;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="upload_key")
	private String uploadKey;

	private String url;

	@Column(name="user_family_name")
	private String userFamilyName;

	@Column(name="user_family_name_en")
	private String userFamilyNameEn;

	@Column(name="user_family_name_kn")
	private String userFamilyNameKn;

	@Column(name="user_kbn")
	private String userKbn;

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

	//bi-directional many-to-one association to NrAchievementReportBkupTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<NrAchievementReportBkupTbl> nrAchievementReportBkupTbls;

	//bi-directional many-to-one association to CrConsulPublicUserTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<CrConsulPublicUserTbl> crConsulPublicUserTbls;

	//bi-directional many-to-one association to GyAcademicTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyAcademicTbl> gyAcademicTbls;

	//bi-directional many-to-one association to GyBiblioTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyBiblioTbl> gyBiblioTbls;

	//bi-directional many-to-one association to GyCareerTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyCareerTbl> gyCareerTbls;

	//bi-directional many-to-one association to GyCompetitionTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyCompetitionTbl> gyCompetitionTbls;

	//bi-directional many-to-one association to GyConferenceTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyConferenceTbl> gyConferenceTbls;

	//bi-directional many-to-one association to GyDegreeTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyDegreeTbl> gyDegreeTbls;

	//bi-directional many-to-one association to GyOthersTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyOthersTbl> gyOthersTbls;

	//bi-directional many-to-one association to GyPaperTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyPaperTbl> gyPaperTbls;

	//bi-directional many-to-one association to GyPatentTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyPatentTbl> gyPatentTbls;

	//bi-directional many-to-one association to GyPrizeTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyPrizeTbl> gyPrizeTbls;

	//bi-directional many-to-one association to GyResearchAreaTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyResearchAreaTbl> gyResearchAreaTbls;

	//bi-directional many-to-one association to GyResearchKeywordTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyResearchKeywordTbl> gyResearchKeywordTbls;

	//bi-directional many-to-one association to GySocietyTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GySocietyTbl> gySocietyTbls;

	//bi-directional many-to-one association to GyWorksTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<GyWorksTbl> gyWorksTbls;

	//bi-directional many-to-one association to ItInternRecruitTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<ItInternRecruitTbl> itInternRecruitTbls;

	//bi-directional many-to-one association to NrLessonCourseTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<NrLessonCourseTbl> nrLessonCourseTbls;

	//bi-directional many-to-one association to NrRubricRelUserTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<NrRubricRelUserTbl> nrRubricRelUserTbls;

	//bi-directional many-to-one association to UsCompetitionTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsCompetitionTbl> usCompetitionTbls;

	//bi-directional many-to-one association to UsInfoTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsInfoTbl> usInfoTbls;

	//bi-directional many-to-one association to UsInternshipTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsInternshipTbl> usInternshipTbls;

	//bi-directional many-to-one association to UsLessonTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsLessonTbl> usLessonTbls;

	//bi-directional many-to-one association to UsLoginInfoTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsLoginInfoTbl> usLoginInfoTbls;

	//bi-directional many-to-one association to UsMessageBoxTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsMessageBoxTbl> usMessageBoxTbls;

	//bi-directional many-to-one association to UsOperationHistoryTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsOperationHistoryTbl> usOperationHistoryTbls;

	//bi-directional many-to-one association to UsPrmovieUploadTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsPrmovieUploadTbl> usPrmovieUploadTbls;

	//bi-directional many-to-one association to UsResultUploadTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsResultUploadTbl> usResultUploadTbls;

	//bi-directional many-to-one association to UsScheduleTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsScheduleTbl> usScheduleTbls;

	//bi-directional many-to-one association to UsUserRelRoleTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<UsUserRelRoleTbl> usUserRelRoleTbls;

	//bi-directional many-to-one association to NrAchievementReportTbl
	@OneToMany(mappedBy="usUserTbl")
	private List<NrAchievementReportTbl> nrAchievementReportTbls;
	
	public UsUserTbl() {
	}

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

	public List<NrAchievementReportBkupTbl> getNrAchievementReportBkupTbls() {
		return this.nrAchievementReportBkupTbls;
	}

	public void setNrAchievementReportBkupTbls(List<NrAchievementReportBkupTbl> nrAchievementReportBkupTbls) {
		this.nrAchievementReportBkupTbls = nrAchievementReportBkupTbls;
	}

	public NrAchievementReportBkupTbl addNrAchievementReportBkupTbl(NrAchievementReportBkupTbl nrAchievementReportBkupTbl) {
		getNrAchievementReportBkupTbls().add(nrAchievementReportBkupTbl);
		nrAchievementReportBkupTbl.setUsUserTbl(this);

		return nrAchievementReportBkupTbl;
	}

	public NrAchievementReportBkupTbl removeNrAchievementReportBkupTbl(NrAchievementReportBkupTbl nrAchievementReportBkupTbl) {
		getNrAchievementReportBkupTbls().remove(nrAchievementReportBkupTbl);
		nrAchievementReportBkupTbl.setUsUserTbl(null);

		return nrAchievementReportBkupTbl;
	}
	
	public List<CrConsulPublicUserTbl> getCrConsulPublicUserTbls() {
		return this.crConsulPublicUserTbls;
	}

	public void setCrConsulPublicUserTbls(List<CrConsulPublicUserTbl> crConsulPublicUserTbls) {
		this.crConsulPublicUserTbls = crConsulPublicUserTbls;
	}

	public CrConsulPublicUserTbl addCrConsulPublicUserTbl(CrConsulPublicUserTbl crConsulPublicUserTbl) {
		getCrConsulPublicUserTbls().add(crConsulPublicUserTbl);
		crConsulPublicUserTbl.setUsUserTbl(this);

		return crConsulPublicUserTbl;
	}

	public CrConsulPublicUserTbl removeCrConsulPublicUserTbl(CrConsulPublicUserTbl crConsulPublicUserTbl) {
		getCrConsulPublicUserTbls().remove(crConsulPublicUserTbl);
		crConsulPublicUserTbl.setUsUserTbl(null);

		return crConsulPublicUserTbl;
	}

	public List<GyAcademicTbl> getGyAcademicTbls() {
		return this.gyAcademicTbls;
	}

	public void setGyAcademicTbls(List<GyAcademicTbl> gyAcademicTbls) {
		this.gyAcademicTbls = gyAcademicTbls;
	}

	public GyAcademicTbl addGyAcademicTbl(GyAcademicTbl gyAcademicTbl) {
		getGyAcademicTbls().add(gyAcademicTbl);
		gyAcademicTbl.setUsUserTbl(this);

		return gyAcademicTbl;
	}

	public GyAcademicTbl removeGyAcademicTbl(GyAcademicTbl gyAcademicTbl) {
		getGyAcademicTbls().remove(gyAcademicTbl);
		gyAcademicTbl.setUsUserTbl(null);

		return gyAcademicTbl;
	}

	public List<GyBiblioTbl> getGyBiblioTbls() {
		return this.gyBiblioTbls;
	}

	public void setGyBiblioTbls(List<GyBiblioTbl> gyBiblioTbls) {
		this.gyBiblioTbls = gyBiblioTbls;
	}

	public GyBiblioTbl addGyBiblioTbl(GyBiblioTbl gyBiblioTbl) {
		getGyBiblioTbls().add(gyBiblioTbl);
		gyBiblioTbl.setUsUserTbl(this);

		return gyBiblioTbl;
	}

	public GyBiblioTbl removeGyBiblioTbl(GyBiblioTbl gyBiblioTbl) {
		getGyBiblioTbls().remove(gyBiblioTbl);
		gyBiblioTbl.setUsUserTbl(null);

		return gyBiblioTbl;
	}

	public List<GyCareerTbl> getGyCareerTbls() {
		return this.gyCareerTbls;
	}

	public void setGyCareerTbls(List<GyCareerTbl> gyCareerTbls) {
		this.gyCareerTbls = gyCareerTbls;
	}

	public GyCareerTbl addGyCareerTbl(GyCareerTbl gyCareerTbl) {
		getGyCareerTbls().add(gyCareerTbl);
		gyCareerTbl.setUsUserTbl(this);

		return gyCareerTbl;
	}

	public GyCareerTbl removeGyCareerTbl(GyCareerTbl gyCareerTbl) {
		getGyCareerTbls().remove(gyCareerTbl);
		gyCareerTbl.setUsUserTbl(null);

		return gyCareerTbl;
	}

	public List<GyCompetitionTbl> getGyCompetitionTbls() {
		return this.gyCompetitionTbls;
	}

	public void setGyCompetitionTbls(List<GyCompetitionTbl> gyCompetitionTbls) {
		this.gyCompetitionTbls = gyCompetitionTbls;
	}

	public GyCompetitionTbl addGyCompetitionTbl(GyCompetitionTbl gyCompetitionTbl) {
		getGyCompetitionTbls().add(gyCompetitionTbl);
		gyCompetitionTbl.setUsUserTbl(this);

		return gyCompetitionTbl;
	}

	public GyCompetitionTbl removeGyCompetitionTbl(GyCompetitionTbl gyCompetitionTbl) {
		getGyCompetitionTbls().remove(gyCompetitionTbl);
		gyCompetitionTbl.setUsUserTbl(null);

		return gyCompetitionTbl;
	}

	public List<GyConferenceTbl> getGyConferenceTbls() {
		return this.gyConferenceTbls;
	}

	public void setGyConferenceTbls(List<GyConferenceTbl> gyConferenceTbls) {
		this.gyConferenceTbls = gyConferenceTbls;
	}

	public GyConferenceTbl addGyConferenceTbl(GyConferenceTbl gyConferenceTbl) {
		getGyConferenceTbls().add(gyConferenceTbl);
		gyConferenceTbl.setUsUserTbl(this);

		return gyConferenceTbl;
	}

	public GyConferenceTbl removeGyConferenceTbl(GyConferenceTbl gyConferenceTbl) {
		getGyConferenceTbls().remove(gyConferenceTbl);
		gyConferenceTbl.setUsUserTbl(null);

		return gyConferenceTbl;
	}

	public List<GyDegreeTbl> getGyDegreeTbls() {
		return this.gyDegreeTbls;
	}

	public void setGyDegreeTbls(List<GyDegreeTbl> gyDegreeTbls) {
		this.gyDegreeTbls = gyDegreeTbls;
	}

	public GyDegreeTbl addGyDegreeTbl(GyDegreeTbl gyDegreeTbl) {
		getGyDegreeTbls().add(gyDegreeTbl);
		gyDegreeTbl.setUsUserTbl(this);

		return gyDegreeTbl;
	}

	public GyDegreeTbl removeGyDegreeTbl(GyDegreeTbl gyDegreeTbl) {
		getGyDegreeTbls().remove(gyDegreeTbl);
		gyDegreeTbl.setUsUserTbl(null);

		return gyDegreeTbl;
	}

	public List<GyOthersTbl> getGyOthersTbls() {
		return this.gyOthersTbls;
	}

	public void setGyOthersTbls(List<GyOthersTbl> gyOthersTbls) {
		this.gyOthersTbls = gyOthersTbls;
	}

	public GyOthersTbl addGyOthersTbl(GyOthersTbl gyOthersTbl) {
		getGyOthersTbls().add(gyOthersTbl);
		gyOthersTbl.setUsUserTbl(this);

		return gyOthersTbl;
	}

	public GyOthersTbl removeGyOthersTbl(GyOthersTbl gyOthersTbl) {
		getGyOthersTbls().remove(gyOthersTbl);
		gyOthersTbl.setUsUserTbl(null);

		return gyOthersTbl;
	}

	public List<GyPaperTbl> getGyPaperTbls() {
		return this.gyPaperTbls;
	}

	public void setGyPaperTbls(List<GyPaperTbl> gyPaperTbls) {
		this.gyPaperTbls = gyPaperTbls;
	}

	public GyPaperTbl addGyPaperTbl(GyPaperTbl gyPaperTbl) {
		getGyPaperTbls().add(gyPaperTbl);
		gyPaperTbl.setUsUserTbl(this);

		return gyPaperTbl;
	}

	public GyPaperTbl removeGyPaperTbl(GyPaperTbl gyPaperTbl) {
		getGyPaperTbls().remove(gyPaperTbl);
		gyPaperTbl.setUsUserTbl(null);

		return gyPaperTbl;
	}

	public List<GyPatentTbl> getGyPatentTbls() {
		return this.gyPatentTbls;
	}

	public void setGyPatentTbls(List<GyPatentTbl> gyPatentTbls) {
		this.gyPatentTbls = gyPatentTbls;
	}

	public GyPatentTbl addGyPatentTbl(GyPatentTbl gyPatentTbl) {
		getGyPatentTbls().add(gyPatentTbl);
		gyPatentTbl.setUsUserTbl(this);

		return gyPatentTbl;
	}

	public GyPatentTbl removeGyPatentTbl(GyPatentTbl gyPatentTbl) {
		getGyPatentTbls().remove(gyPatentTbl);
		gyPatentTbl.setUsUserTbl(null);

		return gyPatentTbl;
	}

	public List<GyPrizeTbl> getGyPrizeTbls() {
		return this.gyPrizeTbls;
	}

	public void setGyPrizeTbls(List<GyPrizeTbl> gyPrizeTbls) {
		this.gyPrizeTbls = gyPrizeTbls;
	}

	public GyPrizeTbl addGyPrizeTbl(GyPrizeTbl gyPrizeTbl) {
		getGyPrizeTbls().add(gyPrizeTbl);
		gyPrizeTbl.setUsUserTbl(this);

		return gyPrizeTbl;
	}

	public GyPrizeTbl removeGyPrizeTbl(GyPrizeTbl gyPrizeTbl) {
		getGyPrizeTbls().remove(gyPrizeTbl);
		gyPrizeTbl.setUsUserTbl(null);

		return gyPrizeTbl;
	}

	public List<GyResearchAreaTbl> getGyResearchAreaTbls() {
		return this.gyResearchAreaTbls;
	}

	public void setGyResearchAreaTbls(List<GyResearchAreaTbl> gyResearchAreaTbls) {
		this.gyResearchAreaTbls = gyResearchAreaTbls;
	}

	public GyResearchAreaTbl addGyResearchAreaTbl(GyResearchAreaTbl gyResearchAreaTbl) {
		getGyResearchAreaTbls().add(gyResearchAreaTbl);
		gyResearchAreaTbl.setUsUserTbl(this);

		return gyResearchAreaTbl;
	}

	public GyResearchAreaTbl removeGyResearchAreaTbl(GyResearchAreaTbl gyResearchAreaTbl) {
		getGyResearchAreaTbls().remove(gyResearchAreaTbl);
		gyResearchAreaTbl.setUsUserTbl(null);

		return gyResearchAreaTbl;
	}

	public List<GyResearchKeywordTbl> getGyResearchKeywordTbls() {
		return this.gyResearchKeywordTbls;
	}

	public void setGyResearchKeywordTbls(List<GyResearchKeywordTbl> gyResearchKeywordTbls) {
		this.gyResearchKeywordTbls = gyResearchKeywordTbls;
	}

	public GyResearchKeywordTbl addGyResearchKeywordTbl(GyResearchKeywordTbl gyResearchKeywordTbl) {
		getGyResearchKeywordTbls().add(gyResearchKeywordTbl);
		gyResearchKeywordTbl.setUsUserTbl(this);

		return gyResearchKeywordTbl;
	}

	public GyResearchKeywordTbl removeGyResearchKeywordTbl(GyResearchKeywordTbl gyResearchKeywordTbl) {
		getGyResearchKeywordTbls().remove(gyResearchKeywordTbl);
		gyResearchKeywordTbl.setUsUserTbl(null);

		return gyResearchKeywordTbl;
	}

	public List<GySocietyTbl> getGySocietyTbls() {
		return this.gySocietyTbls;
	}

	public void setGySocietyTbls(List<GySocietyTbl> gySocietyTbls) {
		this.gySocietyTbls = gySocietyTbls;
	}

	public GySocietyTbl addGySocietyTbl(GySocietyTbl gySocietyTbl) {
		getGySocietyTbls().add(gySocietyTbl);
		gySocietyTbl.setUsUserTbl(this);

		return gySocietyTbl;
	}

	public GySocietyTbl removeGySocietyTbl(GySocietyTbl gySocietyTbl) {
		getGySocietyTbls().remove(gySocietyTbl);
		gySocietyTbl.setUsUserTbl(null);

		return gySocietyTbl;
	}

	public List<GyWorksTbl> getGyWorksTbls() {
		return this.gyWorksTbls;
	}

	public void setGyWorksTbls(List<GyWorksTbl> gyWorksTbls) {
		this.gyWorksTbls = gyWorksTbls;
	}

	public GyWorksTbl addGyWorksTbl(GyWorksTbl gyWorksTbl) {
		getGyWorksTbls().add(gyWorksTbl);
		gyWorksTbl.setUsUserTbl(this);

		return gyWorksTbl;
	}

	public GyWorksTbl removeGyWorksTbl(GyWorksTbl gyWorksTbl) {
		getGyWorksTbls().remove(gyWorksTbl);
		gyWorksTbl.setUsUserTbl(null);

		return gyWorksTbl;
	}

	public List<ItInternRecruitTbl> getItInternRecruitTbls() {
		return this.itInternRecruitTbls;
	}

	public void setItInternRecruitTbls(List<ItInternRecruitTbl> itInternRecruitTbls) {
		this.itInternRecruitTbls = itInternRecruitTbls;
	}

	public ItInternRecruitTbl addItInternRecruitTbl(ItInternRecruitTbl itInternRecruitTbl) {
		getItInternRecruitTbls().add(itInternRecruitTbl);
		itInternRecruitTbl.setUsUserTbl(this);

		return itInternRecruitTbl;
	}

	public ItInternRecruitTbl removeItInternRecruitTbl(ItInternRecruitTbl itInternRecruitTbl) {
		getItInternRecruitTbls().remove(itInternRecruitTbl);
		itInternRecruitTbl.setUsUserTbl(null);

		return itInternRecruitTbl;
	}

	public List<NrLessonCourseTbl> getNrLessonCourseTbls() {
		return this.nrLessonCourseTbls;
	}

	public void setNrLessonCourseTbls(List<NrLessonCourseTbl> nrLessonCourseTbls) {
		this.nrLessonCourseTbls = nrLessonCourseTbls;
	}

	public NrLessonCourseTbl addNrLessonCourseTbl(NrLessonCourseTbl nrLessonCourseTbl) {
		getNrLessonCourseTbls().add(nrLessonCourseTbl);
		nrLessonCourseTbl.setUsUserTbl(this);

		return nrLessonCourseTbl;
	}

	public NrLessonCourseTbl removeNrLessonCourseTbl(NrLessonCourseTbl nrLessonCourseTbl) {
		getNrLessonCourseTbls().remove(nrLessonCourseTbl);
		nrLessonCourseTbl.setUsUserTbl(null);

		return nrLessonCourseTbl;
	}

	public List<NrRubricRelUserTbl> getNrRubricRelUserTbls() {
		return this.nrRubricRelUserTbls;
	}

	public void setNrRubricRelUserTbls(List<NrRubricRelUserTbl> nrRubricRelUserTbls) {
		this.nrRubricRelUserTbls = nrRubricRelUserTbls;
	}

	public NrRubricRelUserTbl addNrRubricRelUserTbl(NrRubricRelUserTbl nrRubricRelUserTbl) {
		getNrRubricRelUserTbls().add(nrRubricRelUserTbl);
		nrRubricRelUserTbl.setUsUserTbl(this);

		return nrRubricRelUserTbl;
	}

	public NrRubricRelUserTbl removeNrRubricRelUserTbl(NrRubricRelUserTbl nrRubricRelUserTbl) {
		getNrRubricRelUserTbls().remove(nrRubricRelUserTbl);
		nrRubricRelUserTbl.setUsUserTbl(null);

		return nrRubricRelUserTbl;
	}

	public List<UsCompetitionTbl> getUsCompetitionTbls() {
		return this.usCompetitionTbls;
	}

	public void setUsCompetitionTbls(List<UsCompetitionTbl> usCompetitionTbls) {
		this.usCompetitionTbls = usCompetitionTbls;
	}

	public UsCompetitionTbl addUsCompetitionTbl(UsCompetitionTbl usCompetitionTbl) {
		getUsCompetitionTbls().add(usCompetitionTbl);
		usCompetitionTbl.setUsUserTbl(this);

		return usCompetitionTbl;
	}

	public UsCompetitionTbl removeUsCompetitionTbl(UsCompetitionTbl usCompetitionTbl) {
		getUsCompetitionTbls().remove(usCompetitionTbl);
		usCompetitionTbl.setUsUserTbl(null);

		return usCompetitionTbl;
	}

	public List<UsInfoTbl> getUsInfoTbls() {
		return this.usInfoTbls;
	}

	public void setUsInfoTbls(List<UsInfoTbl> usInfoTbls) {
		this.usInfoTbls = usInfoTbls;
	}

	public UsInfoTbl addUsInfoTbl(UsInfoTbl usInfoTbl) {
		getUsInfoTbls().add(usInfoTbl);
		usInfoTbl.setUsUserTbl(this);

		return usInfoTbl;
	}

	public UsInfoTbl removeUsInfoTbl(UsInfoTbl usInfoTbl) {
		getUsInfoTbls().remove(usInfoTbl);
		usInfoTbl.setUsUserTbl(null);

		return usInfoTbl;
	}

	public List<UsInternshipTbl> getUsInternshipTbls() {
		return this.usInternshipTbls;
	}

	public void setUsInternshipTbls(List<UsInternshipTbl> usInternshipTbls) {
		this.usInternshipTbls = usInternshipTbls;
	}

	public UsInternshipTbl addUsInternshipTbl(UsInternshipTbl usInternshipTbl) {
		getUsInternshipTbls().add(usInternshipTbl);
		usInternshipTbl.setUsUserTbl(this);

		return usInternshipTbl;
	}

	public UsInternshipTbl removeUsInternshipTbl(UsInternshipTbl usInternshipTbl) {
		getUsInternshipTbls().remove(usInternshipTbl);
		usInternshipTbl.setUsUserTbl(null);

		return usInternshipTbl;
	}

	public List<UsLessonTbl> getUsLessonTbls() {
		return this.usLessonTbls;
	}

	public void setUsLessonTbls(List<UsLessonTbl> usLessonTbls) {
		this.usLessonTbls = usLessonTbls;
	}

	public UsLessonTbl addUsLessonTbl(UsLessonTbl usLessonTbl) {
		getUsLessonTbls().add(usLessonTbl);
		usLessonTbl.setUsUserTbl(this);

		return usLessonTbl;
	}

	public UsLessonTbl removeUsLessonTbl(UsLessonTbl usLessonTbl) {
		getUsLessonTbls().remove(usLessonTbl);
		usLessonTbl.setUsUserTbl(null);

		return usLessonTbl;
	}

	public List<UsLoginInfoTbl> getUsLoginInfoTbls() {
		return this.usLoginInfoTbls;
	}

	public void setUsLoginInfoTbls(List<UsLoginInfoTbl> usLoginInfoTbls) {
		this.usLoginInfoTbls = usLoginInfoTbls;
	}

	public UsLoginInfoTbl addUsLoginInfoTbl(UsLoginInfoTbl usLoginInfoTbl) {
		getUsLoginInfoTbls().add(usLoginInfoTbl);
		usLoginInfoTbl.setUsUserTbl(this);

		return usLoginInfoTbl;
	}

	public UsLoginInfoTbl removeUsLoginInfoTbl(UsLoginInfoTbl usLoginInfoTbl) {
		getUsLoginInfoTbls().remove(usLoginInfoTbl);
		usLoginInfoTbl.setUsUserTbl(null);

		return usLoginInfoTbl;
	}

	public List<UsMessageBoxTbl> getUsMessageBoxTbls() {
		return this.usMessageBoxTbls;
	}

	public void setUsMessageBoxTbls(List<UsMessageBoxTbl> usMessageBoxTbls) {
		this.usMessageBoxTbls = usMessageBoxTbls;
	}

	public UsMessageBoxTbl addUsMessageBoxTbl(UsMessageBoxTbl usMessageBoxTbl) {
		getUsMessageBoxTbls().add(usMessageBoxTbl);
		usMessageBoxTbl.setUsUserTbl(this);

		return usMessageBoxTbl;
	}

	public UsMessageBoxTbl removeUsMessageBoxTbl(UsMessageBoxTbl usMessageBoxTbl) {
		getUsMessageBoxTbls().remove(usMessageBoxTbl);
		usMessageBoxTbl.setUsUserTbl(null);

		return usMessageBoxTbl;
	}

	public List<UsOperationHistoryTbl> getUsOperationHistoryTbls() {
		return this.usOperationHistoryTbls;
	}

	public void setUsOperationHistoryTbls(List<UsOperationHistoryTbl> usOperationHistoryTbls) {
		this.usOperationHistoryTbls = usOperationHistoryTbls;
	}

	public UsOperationHistoryTbl addUsOperationHistoryTbl(UsOperationHistoryTbl usOperationHistoryTbl) {
		getUsOperationHistoryTbls().add(usOperationHistoryTbl);
		usOperationHistoryTbl.setUsUserTbl(this);

		return usOperationHistoryTbl;
	}

	public UsOperationHistoryTbl removeUsOperationHistoryTbl(UsOperationHistoryTbl usOperationHistoryTbl) {
		getUsOperationHistoryTbls().remove(usOperationHistoryTbl);
		usOperationHistoryTbl.setUsUserTbl(null);

		return usOperationHistoryTbl;
	}

	public List<UsPrmovieUploadTbl> getUsPrmovieUploadTbls() {
		return this.usPrmovieUploadTbls;
	}

	public void setUsPrmovieUploadTbls(List<UsPrmovieUploadTbl> usPrmovieUploadTbls) {
		this.usPrmovieUploadTbls = usPrmovieUploadTbls;
	}

	public UsPrmovieUploadTbl addUsPrmovieUploadTbl(UsPrmovieUploadTbl usPrmovieUploadTbl) {
		getUsPrmovieUploadTbls().add(usPrmovieUploadTbl);
		usPrmovieUploadTbl.setUsUserTbl(this);

		return usPrmovieUploadTbl;
	}

	public UsPrmovieUploadTbl removeUsPrmovieUploadTbl(UsPrmovieUploadTbl usPrmovieUploadTbl) {
		getUsPrmovieUploadTbls().remove(usPrmovieUploadTbl);
		usPrmovieUploadTbl.setUsUserTbl(null);

		return usPrmovieUploadTbl;
	}

	public List<UsResultUploadTbl> getUsResultUploadTbls() {
		return this.usResultUploadTbls;
	}

	public void setUsResultUploadTbls(List<UsResultUploadTbl> usResultUploadTbls) {
		this.usResultUploadTbls = usResultUploadTbls;
	}

	public UsResultUploadTbl addUsResultUploadTbl(UsResultUploadTbl usResultUploadTbl) {
		getUsResultUploadTbls().add(usResultUploadTbl);
		usResultUploadTbl.setUsUserTbl(this);

		return usResultUploadTbl;
	}

	public UsResultUploadTbl removeUsResultUploadTbl(UsResultUploadTbl usResultUploadTbl) {
		getUsResultUploadTbls().remove(usResultUploadTbl);
		usResultUploadTbl.setUsUserTbl(null);

		return usResultUploadTbl;
	}

	public List<UsScheduleTbl> getUsScheduleTbls() {
		return this.usScheduleTbls;
	}

	public void setUsScheduleTbls(List<UsScheduleTbl> usScheduleTbls) {
		this.usScheduleTbls = usScheduleTbls;
	}

	public UsScheduleTbl addUsScheduleTbl(UsScheduleTbl usScheduleTbl) {
		getUsScheduleTbls().add(usScheduleTbl);
		usScheduleTbl.setUsUserTbl(this);

		return usScheduleTbl;
	}

	public UsScheduleTbl removeUsScheduleTbl(UsScheduleTbl usScheduleTbl) {
		getUsScheduleTbls().remove(usScheduleTbl);
		usScheduleTbl.setUsUserTbl(null);

		return usScheduleTbl;
	}

	public List<UsUserRelRoleTbl> getUsUserRelRoleTbls() {
		return this.usUserRelRoleTbls;
	}

	public void setUsUserRelRoleTbls(List<UsUserRelRoleTbl> usUserRelRoleTbls) {
		this.usUserRelRoleTbls = usUserRelRoleTbls;
	}

	public UsUserRelRoleTbl addUsUserRelRoleTbl(UsUserRelRoleTbl usUserRelRoleTbl) {
		getUsUserRelRoleTbls().add(usUserRelRoleTbl);
		usUserRelRoleTbl.setUsUserTbl(this);

		return usUserRelRoleTbl;
	}

	public UsUserRelRoleTbl removeUsUserRelRoleTbl(UsUserRelRoleTbl usUserRelRoleTbl) {
		getUsUserRelRoleTbls().remove(usUserRelRoleTbl);
		usUserRelRoleTbl.setUsUserTbl(null);

		return usUserRelRoleTbl;
	}

	public List<NrAchievementReportTbl> getNrAchievementReportTbls() {
		return this.nrAchievementReportTbls;
	}

	public void setNrAchievementReportTbls(List<NrAchievementReportTbl> nrAchievementReportTbls) {
		this.nrAchievementReportTbls = nrAchievementReportTbls;
	}

	public NrAchievementReportTbl addNrAchievementReportTbl(NrAchievementReportTbl nrAchievementReportTbl) {
		getNrAchievementReportTbls().add(nrAchievementReportTbl);
		nrAchievementReportTbl.setUsUserTbl(this);

		return nrAchievementReportTbl;
	}

	public NrAchievementReportTbl removeNrAchievementReportTbl(NrAchievementReportTbl nrAchievementReportTbl) {
		getNrAchievementReportTbls().remove(nrAchievementReportTbl);
		nrAchievementReportTbl.setUsUserTbl(null);

		return nrAchievementReportTbl;
	}

}