package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String infoKey;

	private String dataKbn;

	private String userKey;

	private String infoRefKey;

	private String makeUserKey;

	private String opeKbn;

	private String title;

	private Timestamp sendDate;

	private Timestamp updDate;

	private String updUserKey;

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

	public String getUserKey() {
		return this.userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
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

}