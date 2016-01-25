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
 * The persistent class for the it_intern_upload_tbl database table.
 *
 */
@Entity
@Table(name="it_intern_upload_tbl")
@NamedQuery(name="ItInternUploadTbl.findAll", query="SELECT i FROM ItInternUploadTbl i")
public class ItInternUploadTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItInternUploadTblPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="upload_kbn")
	private String uploadKbn;

	//bi-directional many-to-one association to ItInternTbl
	@ManyToOne
	@JoinColumn(name="internship_key", insertable=false, updatable=false)
	private ItInternTbl itInternTbl;

	public ItInternUploadTbl() {
	}

	public ItInternUploadTblPK getId() {
		return this.id;
	}

	public void setId(ItInternUploadTblPK id) {
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

	public ItInternTbl getItInternTbl() {
		return this.itInternTbl;
	}

	public void setItInternTbl(ItInternTbl itInternTbl) {
		this.itInternTbl = itInternTbl;
	}

}