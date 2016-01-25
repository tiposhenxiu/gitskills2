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
 * The persistent class for the nr_lesson_course_tbl database table.
 *
 */
@Entity
@Table(name="nr_lesson_course_tbl")
@NamedQuery(name="NrLessonCourseTbl.findAll", query="SELECT n FROM NrLessonCourseTbl n")
public class NrLessonCourseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NrLessonCourseTblPK id;

	@Column(name="course_status")
	private String courseStatus;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrLessonTbl
	@ManyToOne
	@JoinColumn(name="lesson_key", insertable=false, updatable=false)
	private NrLessonTbl nrLessonTbl;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key", insertable=false, updatable=false)
	private UsUserTbl usUserTbl;

	public NrLessonCourseTbl() {
	}

	public NrLessonCourseTblPK getId() {
		return this.id;
	}

	public void setId(NrLessonCourseTblPK id) {
		this.id = id;
	}

	public String getCourseStatus() {
		return this.courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
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

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}