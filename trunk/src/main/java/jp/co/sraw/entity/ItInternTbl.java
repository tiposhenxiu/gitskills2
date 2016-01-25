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
 * The persistent class for the it_intern_tbl database table.
 * 
 */
@Entity
@Table(name="it_intern_tbl")
@NamedQuery(name="ItInternTbl.findAll", query="SELECT i FROM ItInternTbl i")
public class ItInternTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="IT_INTERN_TBL_INTERNSHIPKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="IT_INTERN_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IT_INTERN_TBL_INTERNSHIPKEY_GENERATOR")
	@Column(name="internship_key")
	private String internshipKey;

	@Column(name="batch_status")
	private String batchStatus;

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

	@Column(name="public_flag")
	private String publicFlag;

	@Column(name="subject_ins_kbn")
	private String subjectInsKbn;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to ItInternPublicTbl
	@OneToMany(mappedBy="itInternTbl")
	private List<ItInternPublicTbl> itInternPublicTbls;

	//bi-directional many-to-one association to ItInternRecruitTbl
	@OneToMany(mappedBy="itInternTbl")
	private List<ItInternRecruitTbl> itInternRecruitTbls;

	//bi-directional many-to-one association to ItInternRelSubjectTbl
	@OneToMany(mappedBy="itInternTbl")
	private List<ItInternRelSubjectTbl> itInternRelSubjectTbls;

	//bi-directional many-to-one association to ItInternUploadTbl
	@OneToMany(mappedBy="itInternTbl")
	private List<ItInternUploadTbl> itInternUploadTbls;
	
	public ItInternTbl() {
	}

	public String getInternshipKey() {
		return this.internshipKey;
	}

	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
	}

	public String getBatchStatus() {
		return this.batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
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
	public List<ItInternPublicTbl> getItInternPublicTbls() {
		return this.itInternPublicTbls;
	}

	public void setItInternPublicTbls(List<ItInternPublicTbl> itInternPublicTbls) {
		this.itInternPublicTbls = itInternPublicTbls;
	}

	public ItInternPublicTbl addItInternPublicTbl(ItInternPublicTbl itInternPublicTbl) {
		getItInternPublicTbls().add(itInternPublicTbl);
		itInternPublicTbl.setItInternTbl(this);

		return itInternPublicTbl;
	}

	public ItInternPublicTbl removeItInternPublicTbl(ItInternPublicTbl itInternPublicTbl) {
		getItInternPublicTbls().remove(itInternPublicTbl);
		itInternPublicTbl.setItInternTbl(null);

		return itInternPublicTbl;
	}

	public List<ItInternRecruitTbl> getItInternRecruitTbls() {
		return this.itInternRecruitTbls;
	}

	public void setItInternRecruitTbls(List<ItInternRecruitTbl> itInternRecruitTbls) {
		this.itInternRecruitTbls = itInternRecruitTbls;
	}

	public ItInternRecruitTbl addItInternRecruitTbl(ItInternRecruitTbl itInternRecruitTbl) {
		getItInternRecruitTbls().add(itInternRecruitTbl);
		itInternRecruitTbl.setItInternTbl(this);

		return itInternRecruitTbl;
	}

	public ItInternRecruitTbl removeItInternRecruitTbl(ItInternRecruitTbl itInternRecruitTbl) {
		getItInternRecruitTbls().remove(itInternRecruitTbl);
		itInternRecruitTbl.setItInternTbl(null);

		return itInternRecruitTbl;
	}

	public List<ItInternRelSubjectTbl> getItInternRelSubjectTbls() {
		return this.itInternRelSubjectTbls;
	}

	public void setItInternRelSubjectTbls(List<ItInternRelSubjectTbl> itInternRelSubjectTbls) {
		this.itInternRelSubjectTbls = itInternRelSubjectTbls;
	}

	public ItInternRelSubjectTbl addItInternRelSubjectTbl(ItInternRelSubjectTbl itInternRelSubjectTbl) {
		getItInternRelSubjectTbls().add(itInternRelSubjectTbl);
		itInternRelSubjectTbl.setItInternTbl(this);

		return itInternRelSubjectTbl;
	}

	public ItInternRelSubjectTbl removeItInternRelSubjectTbl(ItInternRelSubjectTbl itInternRelSubjectTbl) {
		getItInternRelSubjectTbls().remove(itInternRelSubjectTbl);
		itInternRelSubjectTbl.setItInternTbl(null);

		return itInternRelSubjectTbl;
	}

	public List<ItInternUploadTbl> getItInternUploadTbls() {
		return this.itInternUploadTbls;
	}

	public void setItInternUploadTbls(List<ItInternUploadTbl> itInternUploadTbls) {
		this.itInternUploadTbls = itInternUploadTbls;
	}

	public ItInternUploadTbl addItInternUploadTbl(ItInternUploadTbl itInternUploadTbl) {
		getItInternUploadTbls().add(itInternUploadTbl);
		itInternUploadTbl.setItInternTbl(this);

		return itInternUploadTbl;
	}

	public ItInternUploadTbl removeItInternUploadTbl(ItInternUploadTbl itInternUploadTbl) {
		getItInternUploadTbls().remove(itInternUploadTbl);
		itInternUploadTbl.setItInternTbl(null);

		return itInternUploadTbl;
	}
}