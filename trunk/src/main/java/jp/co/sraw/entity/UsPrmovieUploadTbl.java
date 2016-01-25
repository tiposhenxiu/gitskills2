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
 * The persistent class for the us_prmovie_upload_tbl database table.
 * 
 */
@Entity
@Table(name="us_prmovie_upload_tbl")
@NamedQuery(name="UsPrmovieUploadTbl.findAll", query="SELECT u FROM UsPrmovieUploadTbl u")
public class UsPrmovieUploadTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsPrmovieUploadTblPK id;

	@Column(name="file_kbn")
	private String fileKbn;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key", insertable=false, updatable=false)
	private UsUserTbl usUserTbl;

	public UsPrmovieUploadTbl() {
	}

	public UsPrmovieUploadTblPK getId() {
		return this.id;
	}

	public void setId(UsPrmovieUploadTblPK id) {
		this.id = id;
	}

	public String getFileKbn() {
		return this.fileKbn;
	}

	public void setFileKbn(String fileKbn) {
		this.fileKbn = fileKbn;
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

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}