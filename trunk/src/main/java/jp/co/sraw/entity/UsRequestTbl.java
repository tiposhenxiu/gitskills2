package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the us_request_tbl database table.
 * 
 */
@Entity
@Table(name="us_request_tbl")
@NamedQuery(name="UsRequestTbl.findAll", query="SELECT u FROM UsRequestTbl u")
public class UsRequestTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="request_id")
	private String requestId;

	@Column(name="limit_date")
	private Timestamp limitDate;

	@Column(name="mail_address")
	private String mailAddress;

	@Column(name="request_date")
	private Timestamp requestDate;

	@Column(name="upd_date")
	private Timestamp updDate;

	public UsRequestTbl() {
	}

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Timestamp getLimitDate() {
		return this.limitDate;
	}

	public void setLimitDate(Timestamp limitDate) {
		this.limitDate = limitDate;
	}

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public Timestamp getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}