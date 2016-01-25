package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the nr_subject_answer_tbl database table.
 *
 */
@Entity
@Table(name="nr_subject_answer_tbl")
@NamedQuery(name="NrSubjectAnswerTbl.findAll", query="SELECT n FROM NrSubjectAnswerTbl n")
public class NrSubjectAnswerTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrSubjectAnswerTblPK id;

	@Column(name="achieve_phase")
	private String achievePhase;

	@Column(name="action_plan")
	private String actionPlan;

	private Integer answer;

	private Integer depth;

	private String evidence;

	@Column(name="evidence_file_key")
	private String evidenceFileKey;

	@Column(name="parent_subject_code")
	private String parentSubjectCode;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrSubjectUploadTbl
	@OneToMany(mappedBy="nrSubjectAnswerTbl")
	private List<NrSubjectUploadTbl> nrSubjectUploadTbls;

	public NrSubjectAnswerTbl() {
	}

	public NrSubjectAnswerTblPK getId() {
		return this.id;
	}

	public void setId(NrSubjectAnswerTblPK id) {
		this.id = id;
	}

	public String getAchievePhase() {
		return this.achievePhase;
	}

	public void setAchievePhase(String achievePhase) {
		this.achievePhase = achievePhase;
	}

	public String getActionPlan() {
		return this.actionPlan;
	}

	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}

	public Integer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	public Integer getDepth() {
		return this.depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getEvidence() {
		return this.evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public String getEvidenceFileKey() {
		return this.evidenceFileKey;
	}

	public void setEvidenceFileKey(String evidenceFileKey) {
		this.evidenceFileKey = evidenceFileKey;
	}

	public String getParentSubjectCode() {
		return this.parentSubjectCode;
	}

	public void setParentSubjectCode(String parentSubjectCode) {
		this.parentSubjectCode = parentSubjectCode;
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

	public List<NrSubjectUploadTbl> getNrSubjectUploadTbls() {
		return this.nrSubjectUploadTbls;
	}

	public void setNrSubjectUploadTbls(List<NrSubjectUploadTbl> nrSubjectUploadTbls) {
		this.nrSubjectUploadTbls = nrSubjectUploadTbls;
	}

	public NrSubjectUploadTbl addNrSubjectUploadTbl(NrSubjectUploadTbl nrSubjectUploadTbl) {
		getNrSubjectUploadTbls().add(nrSubjectUploadTbl);
		nrSubjectUploadTbl.setNrSubjectAnswerTbl(this);

		return nrSubjectUploadTbl;
	}

	public NrSubjectUploadTbl removeNrSubjectUploadTbl(NrSubjectUploadTbl nrSubjectUploadTbl) {
		getNrSubjectUploadTbls().remove(nrSubjectUploadTbl);
		nrSubjectUploadTbl.setNrSubjectAnswerTbl(null);

		return nrSubjectUploadTbl;
	}

}