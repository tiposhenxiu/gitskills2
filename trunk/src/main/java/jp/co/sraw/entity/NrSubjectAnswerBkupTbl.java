package jp.co.sraw.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the nr_subject_answer_bkup_tbl database table.
 * 
 */
@Entity
@Table(name="nr_subject_answer_bkup_tbl")
@NamedQuery(name="NrSubjectAnswerBkupTbl.findAll", query="SELECT n FROM NrSubjectAnswerBkupTbl n")
public class NrSubjectAnswerBkupTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrSubjectAnswerBkupTblPK id;

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

	public NrSubjectAnswerBkupTbl() {
	}

	public NrSubjectAnswerBkupTblPK getId() {
		return this.id;
	}

	public void setId(NrSubjectAnswerBkupTblPK id) {
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

}