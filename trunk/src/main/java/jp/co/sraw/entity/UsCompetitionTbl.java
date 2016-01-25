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
 * The persistent class for the us_competition_tbl database table.
 *
 */
@Entity
@Table(name="us_competition_tbl")
@NamedQuery(name="UsCompetitionTbl.findAll", query="SELECT u FROM UsCompetitionTbl u")
public class UsCompetitionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsCompetitionTblPK id;

	@Column(name="file_kbn")
	private String fileKbn;

	@Column(name="ins_date")
	private Timestamp insDate;

	@Column(name="ins_kbn")
	private String insKbn;

	private String link;

	@Column(name="public_flag")
	private String publicFlag;

	private String title;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="upload_key")
	private String uploadKey;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key", insertable=false, updatable=false)
	private UsUserTbl usUserTbl;

	public UsCompetitionTbl() {
	}

	public UsCompetitionTblPK getId() {
		return this.id;
	}

	public void setId(UsCompetitionTblPK id) {
		this.id = id;
	}

	public String getFileKbn() {
		return this.fileKbn;
	}

	public void setFileKbn(String fileKbn) {
		this.fileKbn = fileKbn;
	}

	public Timestamp getInsDate() {
		return this.insDate;
	}

	public void setInsDate(Timestamp insDate) {
		this.insDate = insDate;
	}

	public String getInsKbn() {
		return this.insKbn;
	}

	public void setInsKbn(String insKbn) {
		this.insKbn = insKbn;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getUploadKey() {
		return this.uploadKey;
	}

	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
	}

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}