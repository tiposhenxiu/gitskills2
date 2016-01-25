package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the nr_achievement_report_bkup_tbl database table.
 *
 */
@Entity
@Table(name="nr_achievement_report_bkup_tbl")
@NamedQuery(name="NrAchievementReportBkupTbl.findAll", query="SELECT n FROM NrAchievementReportBkupTbl n")
public class NrAchievementReportBkupTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrAchievementReportBkupTblPK id;

	@Column(name="all_achievement")
	private String allAchievement;

	@Column(name="all_input_date")
	private Timestamp allInputDate;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="yearly_achievement")
	private String yearlyAchievement;

	@Column(name="yearly_input_date")
	private Timestamp yearlyInputDate;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key", insertable=false, updatable=false)
	private UsUserTbl usUserTbl;

	public NrAchievementReportBkupTbl() {
	}

	public NrAchievementReportBkupTblPK getId() {
		return this.id;
	}

	public void setId(NrAchievementReportBkupTblPK id) {
		this.id = id;
	}

	public String getAllAchievement() {
		return this.allAchievement;
	}

	public void setAllAchievement(String allAchievement) {
		this.allAchievement = allAchievement;
	}

	public Timestamp getAllInputDate() {
		return this.allInputDate;
	}

	public void setAllInputDate(Timestamp allInputDate) {
		this.allInputDate = allInputDate;
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

	public String getYearlyAchievement() {
		return this.yearlyAchievement;
	}

	public void setYearlyAchievement(String yearlyAchievement) {
		this.yearlyAchievement = yearlyAchievement;
	}

	public Timestamp getYearlyInputDate() {
		return this.yearlyInputDate;
	}

	public void setYearlyInputDate(Timestamp yearlyInputDate) {
		this.yearlyInputDate = yearlyInputDate;
	}

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}