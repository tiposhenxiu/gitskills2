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
 * The persistent class for the nr_grading_answer_tbl database table.
 *
 */
@Entity
@Table(name="nr_grading_answer_tbl")
@NamedQuery(name="NrGradingAnswerTbl.findAll", query="SELECT n FROM NrGradingAnswerTbl n")
public class NrGradingAnswerTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrGradingAnswerTblPK id;

	@Column(name="subject_answer")
	private Integer subjectAnswer;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrGradingSubjectTbl
	@OneToMany(mappedBy="nrGradingAnswerTbl")
	private List<NrGradingSubjectTbl> nrGradingSubjectTbls;

	public NrGradingAnswerTbl() {
	}

	public NrGradingAnswerTblPK getId() {
		return this.id;
	}

	public void setId(NrGradingAnswerTblPK id) {
		this.id = id;
	}

	public Integer getSubjectAnswer() {
		return this.subjectAnswer;
	}

	public void setSubjectAnswer(Integer subjectAnswer) {
		this.subjectAnswer = subjectAnswer;
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

	public List<NrGradingSubjectTbl> getNrGradingSubjectTbls() {
		return this.nrGradingSubjectTbls;
	}

	public void setNrGradingSubjectTbls(List<NrGradingSubjectTbl> nrGradingSubjectTbls) {
		this.nrGradingSubjectTbls = nrGradingSubjectTbls;
	}

	public NrGradingSubjectTbl addNrGradingSubjectTbl(NrGradingSubjectTbl nrGradingSubjectTbl) {
		getNrGradingSubjectTbls().add(nrGradingSubjectTbl);
		nrGradingSubjectTbl.setNrGradingAnswerTbl(this);

		return nrGradingSubjectTbl;
	}

	public NrGradingSubjectTbl removeNrGradingSubjectTbl(NrGradingSubjectTbl nrGradingSubjectTbl) {
		getNrGradingSubjectTbls().remove(nrGradingSubjectTbl);
		nrGradingSubjectTbl.setNrGradingAnswerTbl(null);

		return nrGradingSubjectTbl;
	}

}