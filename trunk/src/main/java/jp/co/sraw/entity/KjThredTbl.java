package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;


/**
 * The persistent class for the kj_thred_tbl database table.
 * 
 */
@Entity
@Table(name="kj_thred_tbl")
@NamedQuery(name="KjThredTbl.findAll", query="SELECT k FROM KjThredTbl k")
public class KjThredTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="KJ_THRED_TBL_THREDKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="KJ_THRED_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KJ_THRED_TBL_THREDKEY_GENERATOR")
	@Column(name="thred_key")
	private String thredKey;

	@Column(name="ins_date")
	private Timestamp insDate;

	@Column(name="ins_user_key")
	private String insUserKey;

	@Column(name="public_flag")
	private String publicFlag;

	@Column(name="thred_name")
	private String thredName;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to KjThredContributionTbl
	@OneToMany(mappedBy="kjThredTbl")
	private List<KjThredContributionTbl> kjThredContributionTbls;

	//bi-directional many-to-one association to KjThredReadTbl
	@OneToMany(mappedBy="kjThredTbl")
	private List<KjThredReadTbl> kjThredReadTbls;
	
	//bi-directional many-to-one association to KjBoardGroupTbl
	@ManyToOne
	@JoinColumn(name="board_group_key")
	private KjBoardGroupTbl kjBoardGroupTbl;

	//bi-directional many-to-one association to KjGroupCommonFileTbl
	@OneToMany(mappedBy="kjThredTbl")
	private List<KjGroupCommonFileTbl> kjGroupCommonFileTbls;

	public KjThredTbl() {
	}

	public String getThredKey() {
		return this.thredKey;
	}

	public void setThredKey(String thredKey) {
		this.thredKey = thredKey;
	}

	public Timestamp getInsDate() {
		return this.insDate;
	}

	public void setInsDate(Timestamp insDate) {
		this.insDate = insDate;
	}

	public String getInsUserKey() {
		return this.insUserKey;
	}

	public void setInsUserKey(String insUserKey) {
		this.insUserKey = insUserKey;
	}

	public String getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getThredName() {
		return this.thredName;
	}

	public void setThredName(String thredName) {
		this.thredName = thredName;
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
	public List<KjThredContributionTbl> getKjThredContributionTbls() {
		return this.kjThredContributionTbls;
	}

	public void setKjThredContributionTbls(List<KjThredContributionTbl> kjThredContributionTbls) {
		this.kjThredContributionTbls = kjThredContributionTbls;
	}

	public KjThredContributionTbl addKjThredContributionTbl(KjThredContributionTbl kjThredContributionTbl) {
		getKjThredContributionTbls().add(kjThredContributionTbl);
		kjThredContributionTbl.setKjThredTbl(this);

		return kjThredContributionTbl;
	}

	public KjThredContributionTbl removeKjThredContributionTbl(KjThredContributionTbl kjThredContributionTbl) {
		getKjThredContributionTbls().remove(kjThredContributionTbl);
		kjThredContributionTbl.setKjThredTbl(null);

		return kjThredContributionTbl;
	}

	public List<KjThredReadTbl> getKjThredReadTbls() {
		return this.kjThredReadTbls;
	}

	public void setKjThredReadTbls(List<KjThredReadTbl> kjThredReadTbls) {
		this.kjThredReadTbls = kjThredReadTbls;
	}

	public KjThredReadTbl addKjThredReadTbl(KjThredReadTbl kjThredReadTbl) {
		getKjThredReadTbls().add(kjThredReadTbl);
		kjThredReadTbl.setKjThredTbl(this);

		return kjThredReadTbl;
	}

	public KjThredReadTbl removeKjThredReadTbl(KjThredReadTbl kjThredReadTbl) {
		getKjThredReadTbls().remove(kjThredReadTbl);
		kjThredReadTbl.setKjThredTbl(null);

		return kjThredReadTbl;
	}
	
	public KjBoardGroupTbl getKjBoardGroupTbl() {
		return this.kjBoardGroupTbl;
	}

	public void setKjBoardGroupTbl(KjBoardGroupTbl kjBoardGroupTbl) {
		this.kjBoardGroupTbl = kjBoardGroupTbl;
	}

	public List<KjGroupCommonFileTbl> getKjGroupCommonFileTbls() {
		return this.kjGroupCommonFileTbls;
	}

	public void setKjGroupCommonFileTbls(List<KjGroupCommonFileTbl> kjGroupCommonFileTbls) {
		this.kjGroupCommonFileTbls = kjGroupCommonFileTbls;
	}

	public KjGroupCommonFileTbl addKjGroupCommonFileTbl(KjGroupCommonFileTbl kjGroupCommonFileTbl) {
		getKjGroupCommonFileTbls().add(kjGroupCommonFileTbl);
		kjGroupCommonFileTbl.setKjThredTbl(this);

		return kjGroupCommonFileTbl;
	}

	public KjGroupCommonFileTbl removeKjGroupCommonFileTbl(KjGroupCommonFileTbl kjGroupCommonFileTbl) {
		getKjGroupCommonFileTbls().remove(kjGroupCommonFileTbl);
		kjGroupCommonFileTbl.setKjThredTbl(null);

		return kjGroupCommonFileTbl;
	}

}