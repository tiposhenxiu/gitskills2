package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class EmailDto implements Serializable {

	private static long serialVersionUID = 1L;

	private String userPublicFlag;

	private String mailAddress;

	private String partyCode;

	private String url;

	private String researchSubject;

	private String userKey;

	private String affiliationName;

	private String country;

	private String degree;

	private String freeInput1;

	private String jstCode;

	private String mailSetteing;

	private String researchArea;

	private String telno;

	private String role;

	private Timestamp updDate;

	public String getUserPublicFlag() {
		return userPublicFlag;
	}

	public void setUserPublicFlag(String userPublicFlag) {
		this.userPublicFlag = userPublicFlag;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResearchSubject() {
		return researchSubject;
	}

	public void setResearchSubject(String researchSubject) {
		this.researchSubject = researchSubject;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getAffiliationName() {
		return affiliationName;
	}

	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getFreeInput1() {
		return freeInput1;
	}

	public void setFreeInput1(String freeInput1) {
		this.freeInput1 = freeInput1;
	}

	public String getJstCode() {
		return jstCode;
	}

	public void setJstCode(String jstCode) {
		this.jstCode = jstCode;
	}

	public String getMailSetteing() {
		return mailSetteing;
	}

	public void setMailSetteing(String mailSetteing) {
		this.mailSetteing = mailSetteing;
	}

	public String getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
