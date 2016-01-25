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
 * The persistent class for the cr_consul_tbl database table.
 * 
 */
@Entity
@Table(name="cr_consul_tbl")
@NamedQuery(name="CrConsulTbl.findAll", query="SELECT c FROM CrConsulTbl c")
public class CrConsulTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="CR_CONSUL_TBL_CONSULTATIONKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="CR_CONSUL_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CR_CONSUL_TBL_CONSULTATIONKEY_GENERATOR")
	@Column(name="consultation_key")
	private String consultationKey;

	@Column(name="advicer_user_key")
	private String advicerUserKey;

	@Column(name="consultation_date")
	private Timestamp consultationDate;

	@Column(name="consultation_date_info")
	private String consultationDateInfo;

	@Column(name="consultation_end")
	private String consultationEnd;

	@Column(name="consultation_ins_date")
	private Timestamp consultationInsDate;

	@Column(name="consultation_kbn")
	private String consultationKbn;

	@Column(name="consultation_memo")
	private String consultationMemo;

	@Column(name="consultation_organization")
	private String consultationOrganization;

	@Column(name="consultation_room")
	private String consultationRoom;

	@Column(name="consultation_start")
	private String consultationStart;

	@Column(name="consultation_status")
	private String consultationStatus;

	@Column(name="consultation_telno")
	private String consultationTelno;

	@Column(name="consultation_user_info")
	private String consultationUserInfo;

	@Column(name="consultation_user_key")
	private String consultationUserKey;

	@Column(name="request_kbn")
	private String requestKbn;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="user_key")
	private String userKey;

	@Column(name="user_memo")
	private String userMemo;

	//bi-directional many-to-one association to CrConsulPublicUserTbl
	@OneToMany(mappedBy="crConsulTbl")
	private List<CrConsulPublicUserTbl> crConsulPublicUserTbls;

	public CrConsulTbl() {
	}

	public String getConsultationKey() {
		return this.consultationKey;
	}

	public void setConsultationKey(String consultationKey) {
		this.consultationKey = consultationKey;
	}

	public String getAdvicerUserKey() {
		return this.advicerUserKey;
	}

	public void setAdvicerUserKey(String advicerUserKey) {
		this.advicerUserKey = advicerUserKey;
	}

	public Timestamp getConsultationDate() {
		return this.consultationDate;
	}

	public void setConsultationDate(Timestamp consultationDate) {
		this.consultationDate = consultationDate;
	}

	public String getConsultationDateInfo() {
		return this.consultationDateInfo;
	}

	public void setConsultationDateInfo(String consultationDateInfo) {
		this.consultationDateInfo = consultationDateInfo;
	}

	public String getConsultationEnd() {
		return this.consultationEnd;
	}

	public void setConsultationEnd(String consultationEnd) {
		this.consultationEnd = consultationEnd;
	}

	public Timestamp getConsultationInsDate() {
		return this.consultationInsDate;
	}

	public void setConsultationInsDate(Timestamp consultationInsDate) {
		this.consultationInsDate = consultationInsDate;
	}

	public String getConsultationKbn() {
		return this.consultationKbn;
	}

	public void setConsultationKbn(String consultationKbn) {
		this.consultationKbn = consultationKbn;
	}

	public String getConsultationMemo() {
		return this.consultationMemo;
	}

	public void setConsultationMemo(String consultationMemo) {
		this.consultationMemo = consultationMemo;
	}

	public String getConsultationOrganization() {
		return this.consultationOrganization;
	}

	public void setConsultationOrganization(String consultationOrganization) {
		this.consultationOrganization = consultationOrganization;
	}

	public String getConsultationRoom() {
		return this.consultationRoom;
	}

	public void setConsultationRoom(String consultationRoom) {
		this.consultationRoom = consultationRoom;
	}

	public String getConsultationStart() {
		return this.consultationStart;
	}

	public void setConsultationStart(String consultationStart) {
		this.consultationStart = consultationStart;
	}

	public String getConsultationStatus() {
		return this.consultationStatus;
	}

	public void setConsultationStatus(String consultationStatus) {
		this.consultationStatus = consultationStatus;
	}

	public String getConsultationTelno() {
		return this.consultationTelno;
	}

	public void setConsultationTelno(String consultationTelno) {
		this.consultationTelno = consultationTelno;
	}

	public String getConsultationUserInfo() {
		return this.consultationUserInfo;
	}

	public void setConsultationUserInfo(String consultationUserInfo) {
		this.consultationUserInfo = consultationUserInfo;
	}

	public String getConsultationUserKey() {
		return this.consultationUserKey;
	}

	public void setConsultationUserKey(String consultationUserKey) {
		this.consultationUserKey = consultationUserKey;
	}

	public String getRequestKbn() {
		return this.requestKbn;
	}

	public void setRequestKbn(String requestKbn) {
		this.requestKbn = requestKbn;
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

	public String getUserMemo() {
		return this.userMemo;
	}

	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}

	public List<CrConsulPublicUserTbl> getCrConsulPublicUserTbls() {
		return this.crConsulPublicUserTbls;
	}

	public void setCrConsulPublicUserTbls(List<CrConsulPublicUserTbl> crConsulPublicUserTbls) {
		this.crConsulPublicUserTbls = crConsulPublicUserTbls;
	}

	public CrConsulPublicUserTbl addCrConsulPublicUserTbl(CrConsulPublicUserTbl crConsulPublicUserTbl) {
		getCrConsulPublicUserTbls().add(crConsulPublicUserTbl);
		crConsulPublicUserTbl.setCrConsulTbl(this);

		return crConsulPublicUserTbl;
	}

	public CrConsulPublicUserTbl removeCrConsulPublicUserTbl(CrConsulPublicUserTbl crConsulPublicUserTbl) {
		getCrConsulPublicUserTbls().remove(crConsulPublicUserTbl);
		crConsulPublicUserTbl.setCrConsulTbl(null);

		return crConsulPublicUserTbl;
	}

}