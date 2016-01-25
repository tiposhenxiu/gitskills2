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
 * The persistent class for the kj_thred_upload_tbl database table.
 *
 */
@Entity
@Table(name="kj_thred_upload_tbl")
@NamedQuery(name="KjThredUploadTbl.findAll", query="SELECT k FROM KjThredUploadTbl k")
public class KjThredUploadTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KjThredUploadTblPK id;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to KjThredContributionTbl
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="seq_no", referencedColumnName="seq_no", insertable=false, updatable=false),
		@JoinColumn(name="thred_key", referencedColumnName="thred_key", insertable=false, updatable=false)
		})
	private KjThredContributionTbl kjThredContributionTbl;

	public KjThredUploadTbl() {
	}

	public KjThredUploadTblPK getId() {
		return this.id;
	}

	public void setId(KjThredUploadTblPK id) {
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

	public KjThredContributionTbl getKjThredContributionTbl() {
		return this.kjThredContributionTbl;
	}

	public void setKjThredContributionTbl(KjThredContributionTbl kjThredContributionTbl) {
		this.kjThredContributionTbl = kjThredContributionTbl;
	}

}