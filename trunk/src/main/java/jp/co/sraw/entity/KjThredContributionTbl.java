package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the kj_thred_contribution_tbl database table.
 *
 */
@Entity
@Table(name="kj_thred_contribution_tbl")
@NamedQuery(name="KjThredContributionTbl.findAll", query="SELECT k FROM KjThredContributionTbl k")
public class KjThredContributionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KjThredContributionTblPK id;

	private String memo;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="user_key")
	private String userKey;

	//bi-directional many-to-one association to KjThredTbl
	@ManyToOne
	@JoinColumn(name="thred_key", insertable=false, updatable=false)
	private KjThredTbl kjThredTbl;

	//bi-directional many-to-one association to KjThredUploadTbl
	@OneToMany(mappedBy="kjThredContributionTbl")
	private List<KjThredUploadTbl> kjThredUploadTbls;

	public KjThredContributionTbl() {
	}

	public KjThredContributionTblPK getId() {
		return this.id;
	}

	public void setId(KjThredContributionTblPK id) {
		this.id = id;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getUserKey() {
		return this.userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public KjThredTbl getKjThredTbl() {
		return this.kjThredTbl;
	}

	public void setKjThredTbl(KjThredTbl kjThredTbl) {
		this.kjThredTbl = kjThredTbl;
	}

	public List<KjThredUploadTbl> getKjThredUploadTbls() {
		return this.kjThredUploadTbls;
	}

	public void setKjThredUploadTbls(List<KjThredUploadTbl> kjThredUploadTbls) {
		this.kjThredUploadTbls = kjThredUploadTbls;
	}

	public KjThredUploadTbl addKjThredUploadTbl(KjThredUploadTbl kjThredUploadTbl) {
		getKjThredUploadTbls().add(kjThredUploadTbl);
		kjThredUploadTbl.setKjThredContributionTbl(this);

		return kjThredUploadTbl;
	}

	public KjThredUploadTbl removeKjThredUploadTbl(KjThredUploadTbl kjThredUploadTbl) {
		getKjThredUploadTbls().remove(kjThredUploadTbl);
		kjThredUploadTbl.setKjThredContributionTbl(null);

		return kjThredUploadTbl;
	}

}