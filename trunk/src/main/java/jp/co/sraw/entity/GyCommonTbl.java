package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;

@MappedSuperclass
public class GyCommonTbl implements Serializable {

	@Id
	@GenericGenerator(name = "GY_COMMON_TBL_GYOSEKIKEY_GENERATOR", strategy = "jp.co.sraw.generator.SmartIdSequenceGenerator", parameters = {
			@Parameter(name = "sequence", value = "GY_GYOSEKI_SEQ"), @Parameter(name = "allocationSize", value = "1"),
			@Parameter(name = "format", value = CommonConst.SEQUENCE_FORMAT) })
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GY_COMMON_TBL_GYOSEKIKEY_GENERATOR")
	@Column(name = "gyoseki_key")
	private String gyosekiKey;

	public String getGyosekiKey() {
		return this.gyosekiKey;
	}

	public void setGyosekiKey(String gyosekiKey) {
		this.gyosekiKey = gyosekiKey;
	}

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key")
	private UsUserTbl usUserTbl;

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;


	@Column(name="public_flag")
	private String publicFlag;


	public String getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
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
