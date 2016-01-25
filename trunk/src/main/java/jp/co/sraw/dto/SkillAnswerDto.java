package jp.co.sraw.dto;

import java.io.Serializable;

public class SkillAnswerDto implements Serializable {

	private String abilityCode;
	private String name;
	private String[] phaseTargets;

	private Boolean done;
	private Integer phase;

	private String actionPlan;
	private String evidence;
	private String evidenceFileName;
	private String evidenceFileId;

	private int yourTargetPhase;
	private int[] colSpans;
	
	public String getAbilityCode() {
		return abilityCode;
	}

	public void setAbilityCode(String abilityCode) {
		this.abilityCode = abilityCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getPhaseTargets() {
		return phaseTargets;
	}

	public void setPhaseTargets(String[] phaseTargets) {
		this.phaseTargets = phaseTargets;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Integer getPhase() {
		return phase;
	}

	public void setPhase(Integer phase) {
		this.phase = phase;
	}

	public String getActionPlan() {
		return actionPlan;
	}

	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public String getEvidenceFileName() {
		return evidenceFileName;
	}

	public void setEvidenceFileName(String evidenceFileName) {
		this.evidenceFileName = evidenceFileName;
	}

	public String getEvidenceFileId() {
		return evidenceFileId;
	}

	public void setEvidenceFileId(String evidenceFileId) {
		this.evidenceFileId = evidenceFileId;
	}

	public int getYourTargetPhase() {
		return yourTargetPhase;
	}

	public void setYourTargetPhase(int yourTargetPhase) {
		this.yourTargetPhase = yourTargetPhase;
	}

	public int[] getColSpans() {
		return colSpans;
	}

	public void setColSpans(int[] colSpans) {
		this.colSpans = colSpans;
	}

}
