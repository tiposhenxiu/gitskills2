package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;


/**
 * The persistent class for the nr_lesson_tbl database table.
 *
 */
@Entity
@Table(name="nr_lesson_tbl")
@NamedQuery(name="NrLessonTbl.findAll", query="SELECT n FROM NrLessonTbl n")
public class NrLessonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="NR_LESSON_TBL_LESSONKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="NR_LESSON_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NR_LESSON_TBL_LESSONKEY_GENERATOR")
	@Column(name="lesson_key")
	private String lessonKey;

	@Column(name="e_link")
	private String eLink;

	@Column(name="lesson_base")
	private String lessonBase;

	@Column(name="lesson_code")
	private String lessonCode;

	@Column(name="lesson_compulsory")
	private String lessonCompulsory;

	@Column(name="lesson_date")
	private Timestamp lessonDate;

	@Column(name="lesson_department")
	private String lessonDepartment;

	@Column(name="lesson_intention")
	private String lessonIntention;

	@Column(name="lesson_kbn")
	private String lessonKbn;

	@Column(name="lesson_memo")
	private String lessonMemo;

	@Column(name="lesson_name")
	private String lessonName;

	@Column(name="lesson_period")
	private String lessonPeriod;

	@Column(name="lesson_plan")
	private String lessonPlan;

	@Column(name="lesson_target")
	private String lessonTarget;

	@Column(name="listen_flag")
	private String listenFlag;

	@ManyToOne
	@JoinColumn(name="party_code", insertable=false, updatable=false)
	private MsPartyTbl party;

	@Column(name="relay_flag")
	private String relayFlag;

	@Column(name="relay_place")
	private String relayPlace;

	@Column(name="s_link")
	private String sLink;

	private Integer unit;

	@Column(name="unit_interchangeable")
	private String unitInterchangeable;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to NrLessonCourseTbl
	@OneToMany(mappedBy="nrLessonTbl")
	private List<NrLessonCourseTbl> nrLessonCourseTbls;

	//bi-directional many-to-one association to NrLessonPublicTbl
	@OneToMany(mappedBy="nrLessonTbl")
	private List<NrLessonPublicTbl> nrLessonPublicTbls;

	//bi-directional many-to-one association to NrLessonRelSubjectTbl
	@OneToMany(mappedBy="nrLessonTbl")
	private List<NrLessonRelSubjectTbl> nrLessonRelSubjectTbls;

	public NrLessonTbl() {
	}

	public String getLessonKey() {
		return this.lessonKey;
	}

	public void setLessonKey(String lessonKey) {
		this.lessonKey = lessonKey;
	}

	public String getELink() {
		return this.eLink;
	}

	public void setELink(String eLink) {
		this.eLink = eLink;
	}

	public String getLessonBase() {
		return this.lessonBase;
	}

	public void setLessonBase(String lessonBase) {
		this.lessonBase = lessonBase;
	}

	public String getLessonCode() {
		return this.lessonCode;
	}

	public void setLessonCode(String lessonCode) {
		this.lessonCode = lessonCode;
	}

	public String getLessonCompulsory() {
		return this.lessonCompulsory;
	}

	public void setLessonCompulsory(String lessonCompulsory) {
		this.lessonCompulsory = lessonCompulsory;
	}

	public Timestamp getLessonDate() {
		return this.lessonDate;
	}

	public void setLessonDate(Timestamp lessonDate) {
		this.lessonDate = lessonDate;
	}

	public String getLessonDepartment() {
		return this.lessonDepartment;
	}

	public void setLessonDepartment(String lessonDepartment) {
		this.lessonDepartment = lessonDepartment;
	}

	public String getLessonIntention() {
		return this.lessonIntention;
	}

	public void setLessonIntention(String lessonIntention) {
		this.lessonIntention = lessonIntention;
	}

	public String getLessonKbn() {
		return this.lessonKbn;
	}

	public void setLessonKbn(String lessonKbn) {
		this.lessonKbn = lessonKbn;
	}

	public String getLessonMemo() {
		return this.lessonMemo;
	}

	public void setLessonMemo(String lessonMemo) {
		this.lessonMemo = lessonMemo;
	}

	public String getLessonName() {
		return this.lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getLessonPeriod() {
		return this.lessonPeriod;
	}

	public void setLessonPeriod(String lessonPeriod) {
		this.lessonPeriod = lessonPeriod;
	}

	public String getLessonPlan() {
		return this.lessonPlan;
	}

	public void setLessonPlan(String lessonPlan) {
		this.lessonPlan = lessonPlan;
	}

	public String getLessonTarget() {
		return this.lessonTarget;
	}

	public void setLessonTarget(String lessonTarget) {
		this.lessonTarget = lessonTarget;
	}

	public String getListenFlag() {
		return this.listenFlag;
	}

	public void setListenFlag(String listenFlag) {
		this.listenFlag = listenFlag;
	}


	public MsPartyTbl getParty() {
		return party;
	}

	public void setParty(MsPartyTbl party) {
		this.party = party;
	}

	public String getRelayFlag() {
		return this.relayFlag;
	}

	public void setRelayFlag(String relayFlag) {
		this.relayFlag = relayFlag;
	}

	public String getRelayPlace() {
		return this.relayPlace;
	}

	public void setRelayPlace(String relayPlace) {
		this.relayPlace = relayPlace;
	}

	public String getSLink() {
		return this.sLink;
	}

	public void setSLink(String sLink) {
		this.sLink = sLink;
	}

	public Integer getUnit() {
		return this.unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getUnitInterchangeable() {
		return this.unitInterchangeable;
	}

	public void setUnitInterchangeable(String unitInterchangeable) {
		this.unitInterchangeable = unitInterchangeable;
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

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<NrLessonCourseTbl> getNrLessonCourseTbls() {
		return this.nrLessonCourseTbls;
	}

	public void setNrLessonCourseTbls(List<NrLessonCourseTbl> nrLessonCourseTbls) {
		this.nrLessonCourseTbls = nrLessonCourseTbls;
	}

	public NrLessonCourseTbl addNrLessonCourseTbl(NrLessonCourseTbl nrLessonCourseTbl) {
		getNrLessonCourseTbls().add(nrLessonCourseTbl);
		nrLessonCourseTbl.setNrLessonTbl(this);

		return nrLessonCourseTbl;
	}

	public NrLessonCourseTbl removeNrLessonCourseTbl(NrLessonCourseTbl nrLessonCourseTbl) {
		getNrLessonCourseTbls().remove(nrLessonCourseTbl);
		nrLessonCourseTbl.setNrLessonTbl(null);

		return nrLessonCourseTbl;
	}

	public List<NrLessonPublicTbl> getNrLessonPublicTbls() {
		return this.nrLessonPublicTbls;
	}

	public void setNrLessonPublicTbls(List<NrLessonPublicTbl> nrLessonPublicTbls) {
		this.nrLessonPublicTbls = nrLessonPublicTbls;
	}

	public NrLessonPublicTbl addNrLessonPublicTbl(NrLessonPublicTbl nrLessonPublicTbl) {
		getNrLessonPublicTbls().add(nrLessonPublicTbl);
		nrLessonPublicTbl.setNrLessonTbl(this);

		return nrLessonPublicTbl;
	}

	public NrLessonPublicTbl removeNrLessonPublicTbl(NrLessonPublicTbl nrLessonPublicTbl) {
		getNrLessonPublicTbls().remove(nrLessonPublicTbl);
		nrLessonPublicTbl.setNrLessonTbl(null);

		return nrLessonPublicTbl;
	}

	public List<NrLessonRelSubjectTbl> getNrLessonRelSubjectTbls() {
		return this.nrLessonRelSubjectTbls;
	}

	public void setNrLessonRelSubjectTbls(List<NrLessonRelSubjectTbl> nrLessonRelSubjectTbls) {
		this.nrLessonRelSubjectTbls = nrLessonRelSubjectTbls;
	}

	public NrLessonRelSubjectTbl addNrLessonRelSubjectTbl(NrLessonRelSubjectTbl nrLessonRelSubjectTbl) {
		getNrLessonRelSubjectTbls().add(nrLessonRelSubjectTbl);
		nrLessonRelSubjectTbl.setNrLessonTbl(this);

		return nrLessonRelSubjectTbl;
	}

	public NrLessonRelSubjectTbl removeNrLessonRelSubjectTbl(NrLessonRelSubjectTbl nrLessonRelSubjectTbl) {
		getNrLessonRelSubjectTbls().remove(nrLessonRelSubjectTbl);
		nrLessonRelSubjectTbl.setNrLessonTbl(null);

		return nrLessonRelSubjectTbl;
	}

}