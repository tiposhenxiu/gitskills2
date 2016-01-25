package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * The persistent class for the us_login_info_tbl database table.
 * 
 */
@Entity
@Table(name="us_login_info_tbl")
@NamedQuery(name="UsLoginInfoTbl.findAll", query="SELECT u FROM UsLoginInfoTbl u")
public class UsLoginInfoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="portal_login_id")
	private String portalLoginId;

	private String password;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="use_flag")
	private String useFlag;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key")
	private UsUserTbl usUserTbl;

	public UsLoginInfoTbl() {
	}

	public String getPortalLoginId() {
		return this.portalLoginId;
	}

	public void setPortalLoginId(String portalLoginId) {
		this.portalLoginId = portalLoginId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUseFlag() {
		return this.useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}