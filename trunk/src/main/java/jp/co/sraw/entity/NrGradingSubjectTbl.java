package jp.co.sraw.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the nr_grading_subject_tbl database table.
 *
 */
@Entity
@Table(name="nr_grading_subject_tbl")
@NamedQuery(name="NrGradingSubjectTbl.findAll", query="SELECT n FROM NrGradingSubjectTbl n")
public class NrGradingSubjectTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrGradingSubjectTblPK id;

	@Column(name="average_point")
	private BigDecimal averagePoint;

	@Column(name="level_no")
	private Integer levelNo;

	@Column(name="oya_subject_code")
	private String oyaSubjectCode;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrGradingAnswerTbl
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="grading_date", referencedColumnName="grading_date", insertable=false, updatable=false),
		@JoinColumn(name="grading_kbn", referencedColumnName="grading_kbn", insertable=false, updatable=false),
		@JoinColumn(name="rubric_key", referencedColumnName="rubric_key", insertable=false, updatable=false),
		@JoinColumn(name="subject_code", referencedColumnName="subject_code", insertable=false, updatable=false),
		@JoinColumn(name="user_key", referencedColumnName="user_key", insertable=false, updatable=false)
		})
	private NrGradingAnswerTbl nrGradingAnswerTbl;

	public NrGradingSubjectTbl() {
	}

	public NrGradingSubjectTblPK getId() {
		return this.id;
	}

	public void setId(NrGradingSubjectTblPK id) {
		this.id = id;
	}

	public BigDecimal getAveragePoint() {
		return this.averagePoint;
	}

	public void setAveragePoint(BigDecimal averagePoint) {
		this.averagePoint = averagePoint;
	}

	public Integer getLevelNo() {
		return this.levelNo;
	}

	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}

	public String getOyaSubjectCode() {
		return this.oyaSubjectCode;
	}

	public void setOyaSubjectCode(String oyaSubjectCode) {
		this.oyaSubjectCode = oyaSubjectCode;
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

	public NrGradingAnswerTbl getNrGradingAnswerTbl() {
		return this.nrGradingAnswerTbl;
	}

	public void setNrGradingAnswerTbl(NrGradingAnswerTbl nrGradingAnswerTbl) {
		this.nrGradingAnswerTbl = nrGradingAnswerTbl;
	}

}