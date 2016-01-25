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
 * The persistent class for the kj_group_common_file_tbl database table.
 *
 */
@Entity
@Table(name="kj_group_common_file_tbl")
@NamedQuery(name="KjGroupCommonFileTbl.findAll", query="SELECT k FROM KjGroupCommonFileTbl k")
public class KjGroupCommonFileTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KjGroupCommonFileTblPK id;

	private String title;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to KjThredTbl
	@ManyToOne
	@JoinColumn(name="thred_key", insertable=false, updatable=false)
	private KjThredTbl kjThredTbl;

	public KjGroupCommonFileTbl() {
	}

	public KjGroupCommonFileTblPK getId() {
		return this.id;
	}

	public void setId(KjGroupCommonFileTblPK id) {
		this.id = id;
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

	public KjThredTbl getKjThredTbl() {
		return this.kjThredTbl;
	}

	public void setKjThredTbl(KjThredTbl kjThredTbl) {
		this.kjThredTbl = kjThredTbl;
	}

}