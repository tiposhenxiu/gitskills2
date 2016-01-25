package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the it_intern_recruit_tbl database table.
 *
 */
@Entity
@Table(name="it_intern_recruit_tbl")
@NamedQuery(name="ItInternRecruitTbl.findAll", query="SELECT i FROM ItInternRecruitTbl i")
public class ItInternRecruitTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItInternRecruitTblPK id;

	@Column(name="decision_date")
	private Timestamp decisionDate;

	@Column(name="decision_kbn")
	private String decisionKbn;

	@Column(name="decision_user_key")
	private String decisionUserKey;

	@Column(name="gk_file_ins_kbn")
	private String gkFileInsKbn;

	@Column(name="party_code")
	private String partyCode;

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

	@Column(name="selection_date")
	private Timestamp selectionDate;

	@Column(name="selection_kbn")
	private String selectionKbn;

	@Column(name="selection_user_key")
	private String selectionUserKey;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to ItInternTbl
	@ManyToOne
	@JoinColumn(name="internship_key", insertable=false, updatable=false)
	private ItInternTbl itInternTbl;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key", insertable=false, updatable=false)
	private UsUserTbl usUserTbl;

	//bi-directional many-to-one association to ItInternRecruitUploadTbl
	@OneToMany(mappedBy="itInternRecruitTbl")
	private List<ItInternRecruitUploadTbl> itInternRecruitUploadTbls;

	public ItInternRecruitTbl() {
	}

	public ItInternRecruitTblPK getId() {
		return this.id;
	}

	public void setId(ItInternRecruitTblPK id) {
		this.id = id;
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

	public String getPartyCode() {
		return this.partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
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

	public Timestamp getSelectionDate() {
		return this.selectionDate;
	}

	public void setSelectionDate(Timestamp selectionDate) {
		this.selectionDate = selectionDate;
	}

	public String getSelectionKbn() {
		return this.selectionKbn;
	}

	public void setSelectionKbn(String selectionKbn) {
		this.selectionKbn = selectionKbn;
	}

	public String getSelectionUserKey() {
		return this.selectionUserKey;
	}

	public void setSelectionUserKey(String selectionUserKey) {
		this.selectionUserKey = selectionUserKey;
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

	public ItInternTbl getItInternTbl() {
		return this.itInternTbl;
	}

	public void setItInternTbl(ItInternTbl itInternTbl) {
		this.itInternTbl = itInternTbl;
	}

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

	public List<ItInternRecruitUploadTbl> getItInternRecruitUploadTbls() {
		return this.itInternRecruitUploadTbls;
	}

	public void setItInternRecruitUploadTbls(List<ItInternRecruitUploadTbl> itInternRecruitUploadTbls) {
		this.itInternRecruitUploadTbls = itInternRecruitUploadTbls;
	}

	public ItInternRecruitUploadTbl addItInternRecruitUploadTbl(ItInternRecruitUploadTbl itInternRecruitUploadTbl) {
		getItInternRecruitUploadTbls().add(itInternRecruitUploadTbl);
		itInternRecruitUploadTbl.setItInternRecruitTbl(this);

		return itInternRecruitUploadTbl;
	}

	public ItInternRecruitUploadTbl removeItInternRecruitUploadTbl(ItInternRecruitUploadTbl itInternRecruitUploadTbl) {
		getItInternRecruitUploadTbls().remove(itInternRecruitUploadTbl);
		itInternRecruitUploadTbl.setItInternRecruitTbl(null);

		return itInternRecruitUploadTbl;
	}

}