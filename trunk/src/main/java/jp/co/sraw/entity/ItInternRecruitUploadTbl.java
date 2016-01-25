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
 * The persistent class for the it_intern_recruit_upload_tbl database table.
 *
 */
@Entity
@Table(name="it_intern_recruit_upload_tbl")
@NamedQuery(name="ItInternRecruitUploadTbl.findAll", query="SELECT i FROM ItInternRecruitUploadTbl i")
public class ItInternRecruitUploadTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItInternRecruitUploadTblPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="upload_kbn")
	private String uploadKbn;

	//bi-directional many-to-one association to ItInternRecruitTbl
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="internship_key", referencedColumnName="internship_key", insertable=false, updatable=false),
		@JoinColumn(name="user_key", referencedColumnName="user_key", insertable=false, updatable=false)
		})
	private ItInternRecruitTbl itInternRecruitTbl;

	public ItInternRecruitUploadTbl() {
	}

	public ItInternRecruitUploadTblPK getId() {
		return this.id;
	}

	public void setId(ItInternRecruitUploadTblPK id) {
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

	public String getUploadKbn() {
		return this.uploadKbn;
	}

	public void setUploadKbn(String uploadKbn) {
		this.uploadKbn = uploadKbn;
	}

	public ItInternRecruitTbl getItInternRecruitTbl() {
		return this.itInternRecruitTbl;
	}

	public void setItInternRecruitTbl(ItInternRecruitTbl itInternRecruitTbl) {
		this.itInternRecruitTbl = itInternRecruitTbl;
	}

}