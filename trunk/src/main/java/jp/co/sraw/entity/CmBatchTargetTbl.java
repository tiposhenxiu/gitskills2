package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cm_batch_target_tbl database table.
 * 
 */
@Entity
@Table(name="cm_batch_target_tbl")
@NamedQuery(name="CmBatchTargetTbl.findAll", query="SELECT c FROM CmBatchTargetTbl c")
public class CmBatchTargetTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CmBatchTargetTblPK id;

	@Column(name="info_make_flag")
	private String infoMakeFlag;

	@Column(name="mail_make_flag")
	private String mailMakeFlag;

	@Column(name="schedule_make_flag")
	private String scheduleMakeFlag;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	public CmBatchTargetTbl() {
	}

	public CmBatchTargetTblPK getId() {
		return this.id;
	}

	public void setId(CmBatchTargetTblPK id) {
		this.id = id;
	}

	public String getInfoMakeFlag() {
		return this.infoMakeFlag;
	}

	public void setInfoMakeFlag(String infoMakeFlag) {
		this.infoMakeFlag = infoMakeFlag;
	}

	public String getMailMakeFlag() {
		return this.mailMakeFlag;
	}

	public void setMailMakeFlag(String mailMakeFlag) {
		this.mailMakeFlag = mailMakeFlag;
	}

	public String getScheduleMakeFlag() {
		return this.scheduleMakeFlag;
	}

	public void setScheduleMakeFlag(String scheduleMakeFlag) {
		this.scheduleMakeFlag = scheduleMakeFlag;
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

}