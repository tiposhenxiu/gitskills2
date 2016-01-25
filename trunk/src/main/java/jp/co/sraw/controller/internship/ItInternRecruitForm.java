/*
* ファイル名：HomeForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.internship;

import java.sql.Timestamp;

import jp.co.sraw.common.CommonForm;
import jp.co.sraw.entity.ItInternRecruitTblPK;

/**
 * <B>InternshipFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class ItInternRecruitForm extends CommonForm {

	private String internshipKey;

	private String userKey;

	public String getInternshipKey() {
		return this.internshipKey;
	}

	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
	}

	public String getUserKey() {
		return this.userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	private Timestamp decisionDate;

	private String decisionKbn;

	private String decisionUserKey;

	private String gkFileInsKbn;

	private String partyCode;

	private String rcFileInsKbn;

	private Timestamp recruitDate;

	private String recruitEndDate;

	private String recruitGrade;

	private String recruitMemo;

	private String recruitPartyName;

	private String recruitStartDate;

	private String recruitStatus;

	private String recruitTeacher;

	private Timestamp selectionDate;

	private String selectionKbn;

	private String selectionUserKey;

	private Timestamp updDate;

	private String updUserKey;

	public ItInternRecruitTblPK getId() {
		ItInternRecruitTblPK pk = new ItInternRecruitTblPK();
		pk.setInternshipKey(internshipKey);
		pk.setUserKey(userKey);
		return pk;
	}

	public void setId(ItInternRecruitTblPK id) {
		this.internshipKey=id.getInternshipKey();
		this.userKey=id.getUserKey();
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

	public String getRecruitEndDate() {
		return this.recruitEndDate;
	}

	public void setRecruitEndDate(String recruitEndDate) {
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

	public String getRecruitStartDate() {
		return this.recruitStartDate;
	}

	public void setRecruitStartDate(String recruitStartDate) {
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
}
