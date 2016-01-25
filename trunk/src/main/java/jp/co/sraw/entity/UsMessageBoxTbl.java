package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;

/**
 * The persistent class for the us_message_box_tbl database table.
 *
 */
@Entity
@Table(name="us_message_box_tbl")
@NamedQuery(name="UsMessageBoxTbl.findAll", query="SELECT u FROM UsMessageBoxTbl u")
public class UsMessageBoxTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="US_MESSAGE_BOX_TBL_MESSAGEKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="US_MESSAGE_BOX_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="US_MESSAGE_BOX_TBL_MESSAGEKEY_GENERATOR")
	@Column(name="message_key")
	private String messageKey;

	@Column(name="make_user_key")
	private String makeUserKey;

	@Column(name="message_contents")
	private String messageContents;

	@Column(name="message_title")
	private String messageTitle;

	@Column(name="ref_message_key")
	private String refMessageKey;

	@Column(name="send_date")
	private Timestamp sendDate;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to UsUserTbl
	@ManyToOne
	@JoinColumn(name="user_key")
	private UsUserTbl usUserTbl;

	public UsMessageBoxTbl() {
	}

	public String getMessageKey() {
		return this.messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMakeUserKey() {
		return this.makeUserKey;
	}

	public void setMakeUserKey(String makeUserKey) {
		this.makeUserKey = makeUserKey;
	}

	public String getMessageContents() {
		return this.messageContents;
	}

	public void setMessageContents(String messageContents) {
		this.messageContents = messageContents;
	}

	public String getMessageTitle() {
		return this.messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getRefMessageKey() {
		return this.refMessageKey;
	}

	public void setRefMessageKey(String refMessageKey) {
		this.refMessageKey = refMessageKey;
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

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

}