package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageBoxDto implements Serializable {

	private String messageKey;

	private String userKey;

	private String userFamilyName;

	private String userMiddleName;

	private String userName;

	private String makeUserKey;

	private String messageContents;

	private String messageTitle;

	private String refMessageKey;

	private Timestamp sendDate;

	private Timestamp updDate;

	private String updUserKey;

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMakeUserKey() {
		return makeUserKey;
	}

	public void setMakeUserKey(String makeUserKey) {
		this.makeUserKey = makeUserKey;
	}

	public String getMessageContents() {
		return messageContents;
	}

	public void setMessageContents(String messageContents) {
		this.messageContents = messageContents;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getRefMessageKey() {
		return refMessageKey;
	}

	public void setRefMessageKey(String refMessageKey) {
		this.refMessageKey = refMessageKey;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUserKey() {
		return updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFamilyName() {
		return userFamilyName;
	}

	public void setUserFamilyName(String userFamilyName) {
		this.userFamilyName = userFamilyName;
	}

	public String getUserMiddleName() {
		return userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

}
