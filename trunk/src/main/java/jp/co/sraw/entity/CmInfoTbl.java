package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;


/**
 * The persistent class for the cm_info_tbl database table.
 *
 */
@Entity
@Table(name="cm_info_tbl")
@NamedQuery(name="CmInfoTbl.findAll", query="SELECT c FROM CmInfoTbl c")
public class CmInfoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="CM_INFO_TBL_INFOKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="CM_INFO_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CM_INFO_TBL_INFOKEY_GENERATOR")
	@Column(name="info_key")
	private String infoKey;

	@Column(name="data_kbn")
	private String dataKbn;

	@Column(name="info_ref_key")
	private String infoRefKey;

	@Column(name="make_user_key")
	private String makeUserKey;

	@Column(name="ope_kbn")
	private String opeKbn;

	@Column(name="title")
	private String title;

	@Column(name="send_date")
	private Timestamp sendDate;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to CmInfoPublicTbl
	@OneToMany(mappedBy="cmInfoTbl")
	private List<CmInfoPublicTbl> cmInfoPublicTbls;

	public CmInfoTbl() {
	}

	public String getInfoKey() {
		return this.infoKey;
	}

	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}

	public String getDataKbn() {
		return this.dataKbn;
	}

	public void setDataKbn(String dataKbn) {
		this.dataKbn = dataKbn;
	}

	public String getInfoRefKey() {
		return this.infoRefKey;
	}

	public void setInfoRefKey(String infoRefKey) {
		this.infoRefKey = infoRefKey;
	}

	public String getMakeUserKey() {
		return this.makeUserKey;
	}

	public void setMakeUserKey(String makeUserKey) {
		this.makeUserKey = makeUserKey;
	}

	public String getOpeKbn() {
		return this.opeKbn;
	}

	public void setOpeKbn(String opeKbn) {
		this.opeKbn = opeKbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
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

	public List<CmInfoPublicTbl> getCmInfoPublicTbls() {
		return this.cmInfoPublicTbls;
	}

	public void setCmInfoPublicTbls(List<CmInfoPublicTbl> cmInfoPublicTbls) {
		this.cmInfoPublicTbls = cmInfoPublicTbls;
	}

	public CmInfoPublicTbl addCmInfoPublicTbl(CmInfoPublicTbl cmInfoPublicTbl) {
		getCmInfoPublicTbls().add(cmInfoPublicTbl);
		cmInfoPublicTbl.setCmInfoTbl(this);

		return cmInfoPublicTbl;
	}

	public CmInfoPublicTbl removeCmInfoPublicTbl(CmInfoPublicTbl cmInfoPublicTbl) {
		getCmInfoPublicTbls().remove(cmInfoPublicTbl);
		cmInfoPublicTbl.setCmInfoTbl(null);

		return cmInfoPublicTbl;
	}

}