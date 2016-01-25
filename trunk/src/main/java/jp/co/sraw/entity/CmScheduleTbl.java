package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;

/**
 * The persistent class for the cm_schedule_tbl database table.
 * 
 */
@Entity
@Table(name="cm_schedule_tbl")
@NamedQuery(name="CmScheduleTbl.findAll", query="SELECT c FROM CmScheduleTbl c")
public class CmScheduleTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="CM_SCHEDULE_TBL_SUHDULEKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="CM_SCHEDULE_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CM_SCHEDULE_TBL_SUHDULEKEY_GENERATOR")
	@Column(name="suhdule_key")
	private String suhduleKey;

	@Column(name="data_kbn")
	private String dataKbn;

	@Column(name="end_time")
	private String endTime;

	@Column(name="make_user_key")
	private String makeUserKey;

	@Column(name="schdule_ref_key")
	private String schduleRefKey;

	@Column(name="start_time")
	private String startTime;

	@Temporal(TemporalType.DATE)
	@Column(name="suhdule_date")
	private Date suhduleDate;

	private String title;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to CmSchedulePublicTbl
	@OneToMany(mappedBy="cmScheduleTbl")
	private List<CmSchedulePublicTbl> cmSchedulePublicTbls;

	public CmScheduleTbl() {
	}

	public String getSuhduleKey() {
		return this.suhduleKey;
	}

	public void setSuhduleKey(String suhduleKey) {
		this.suhduleKey = suhduleKey;
	}

	public String getDataKbn() {
		return this.dataKbn;
	}

	public void setDataKbn(String dataKbn) {
		this.dataKbn = dataKbn;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMakeUserKey() {
		return this.makeUserKey;
	}

	public void setMakeUserKey(String makeUserKey) {
		this.makeUserKey = makeUserKey;
	}

	public String getSchduleRefKey() {
		return this.schduleRefKey;
	}

	public void setSchduleRefKey(String schduleRefKey) {
		this.schduleRefKey = schduleRefKey;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Date getSuhduleDate() {
		return this.suhduleDate;
	}

	public void setSuhduleDate(Date suhduleDate) {
		this.suhduleDate = suhduleDate;
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

	public List<CmSchedulePublicTbl> getCmSchedulePublicTbls() {
		return this.cmSchedulePublicTbls;
	}

	public void setCmSchedulePublicTbls(List<CmSchedulePublicTbl> cmSchedulePublicTbls) {
		this.cmSchedulePublicTbls = cmSchedulePublicTbls;
	}

	public CmSchedulePublicTbl addCmSchedulePublicTbl(CmSchedulePublicTbl cmSchedulePublicTbl) {
		getCmSchedulePublicTbls().add(cmSchedulePublicTbl);
		cmSchedulePublicTbl.setCmScheduleTbl(this);

		return cmSchedulePublicTbl;
	}

	public CmSchedulePublicTbl removeCmSchedulePublicTbl(CmSchedulePublicTbl cmSchedulePublicTbl) {
		getCmSchedulePublicTbls().remove(cmSchedulePublicTbl);
		cmSchedulePublicTbl.setCmScheduleTbl(null);

		return cmSchedulePublicTbl;
	}

}