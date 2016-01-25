package jp.co.sraw.entity;

import java.io.Serializable;
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
 * The persistent class for the nr_subject_upload_tbl database table.
 *
 */
@Entity
@Table(name="nr_subject_upload_tbl")
@NamedQuery(name="NrSubjectUploadTbl.findAll", query="SELECT n FROM NrSubjectUploadTbl n")
public class NrSubjectUploadTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrSubjectUploadTblPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrSubjectAnswerTbl
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="rubric_key", referencedColumnName="rubric_key", insertable=false, updatable=false),
		@JoinColumn(name="subject_code", referencedColumnName="subject_code", insertable=false, updatable=false),
		@JoinColumn(name="user_key", referencedColumnName="user_key", insertable=false, updatable=false)
		})
	private NrSubjectAnswerTbl nrSubjectAnswerTbl;

	public NrSubjectUploadTbl() {
	}

	public NrSubjectUploadTblPK getId() {
		return this.id;
	}

	public void setId(NrSubjectUploadTblPK id) {
		this.id = id;
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

	public NrSubjectAnswerTbl getNrSubjectAnswerTbl() {
		return this.nrSubjectAnswerTbl;
	}

	public void setNrSubjectAnswerTbl(NrSubjectAnswerTbl nrSubjectAnswerTbl) {
		this.nrSubjectAnswerTbl = nrSubjectAnswerTbl;
	}

}