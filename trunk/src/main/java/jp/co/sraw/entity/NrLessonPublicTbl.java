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
 * The persistent class for the nr_lesson_public_tbl database table.
 *
 */
@Entity
@Table(name="nr_lesson_public_tbl")
@NamedQuery(name="NrLessonPublicTbl.findAll", query="SELECT n FROM NrLessonPublicTbl n")
public class NrLessonPublicTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrLessonPublicTblPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrLessonTbl
	@ManyToOne
	@JoinColumn(name="lesson_key", insertable=false, updatable=false)
	private NrLessonTbl nrLessonTbl;

	public NrLessonPublicTbl() {
	}

	public NrLessonPublicTblPK getId() {
		return this.id;
	}

	public void setId(NrLessonPublicTblPK id) {
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

	public NrLessonTbl getNrLessonTbl() {
		return this.nrLessonTbl;
	}

	public void setNrLessonTbl(NrLessonTbl nrLessonTbl) {
		this.nrLessonTbl = nrLessonTbl;
	}

}