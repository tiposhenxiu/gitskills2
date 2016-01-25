package jp.co.sraw.dto;

import java.io.Serializable;

public class BatchPublicDto implements Serializable {

	private static long serialVersionUID = 1L;

	private String infoKey;

	private String eventKey;

	private String internshipKey;

	private int seqNo;

	private String publicKbn;

	private String role;

	private String partyCode;

	public String getPublicKbn() {
		return publicKbn;
	}

	public void setPublicKbn(String publicKbn) {
		this.publicKbn = publicKbn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getInfoKey() {
		return infoKey;
	}

	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getInternshipKey() {
		return internshipKey;
	}

	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
	}

}
