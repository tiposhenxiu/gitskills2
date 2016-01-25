package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SpSupportDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String supportKey;

	private String partyCode;

	private String publicFlag;

	private String supportAreaKbn;

	private String supportContent;

	private Timestamp supportEndDate;

	private Timestamp supportInsDate;

	private String supportSpkikiKbn;

	private String supportHirakuKbn;

	private String supportSybCode;

	private String supportSybCodeName;

	private String supportSybCodeAbbrName;

	private String supportKeyword;

	private Timestamp supportStartDate;

	private String supportTitle;

	private Timestamp updDate;

	private String updUserKey;

	private String url;

	/**
	 * @return supportKey
	 */
	public String getSupportKey() {
		return supportKey;
	}

	/**
	 * @param supportKey セットする supportKey
	 */
	public void setSupportKey(String supportKey) {
		this.supportKey = supportKey;
	}

	/**
	 * @return partyCode
	 */
	public String getPartyCode() {
		return partyCode;
	}

	/**
	 * @param partyCode セットする partyCode
	 */
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	/**
	 * @return publicFlag
	 */
	public String getPublicFlag() {
		return publicFlag;
	}

	/**
	 * @param publicFlag セットする publicFlag
	 */
	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	/**
	 * @return supportContent
	 */
	public String getSupportContent() {
		return supportContent;
	}

	/**
	 * @param supportContent セットする supportContent
	 */
	public void setSupportContent(String supportContent) {
		this.supportContent = supportContent;
	}

	/**
	 * @return supportEndDate
	 */
	public Timestamp getSupportEndDate() {
		return supportEndDate;
	}

	/**
	 * @param supportEndDate セットする supportEndDate
	 */
	public void setSupportEndDate(Timestamp supportEndDate) {
		this.supportEndDate = supportEndDate;
	}

	/**
	 * @return supportInsDate
	 */
	public Timestamp getSupportInsDate() {
		return supportInsDate;
	}

	/**
	 * @param supportInsDate セットする supportInsDate
	 */
	public void setSupportInsDate(Timestamp supportInsDate) {
		this.supportInsDate = supportInsDate;
	}

	/**
	 * @return supportSpkikiKbn
	 */
	public String getSupportSpkikiKbn() {
		return supportSpkikiKbn;
	}

	/**
	 * @param supportSpkikiKbn セットする supportSpkikiKbn
	 */
	public void setSupportSpkikiKbn(String supportSpkikiKbn) {
		this.supportSpkikiKbn = supportSpkikiKbn;
	}

	/**
	 * @return supportHirakuKbn
	 */
	public String getSupportHirakuKbn() {
		return supportHirakuKbn;
	}

	/**
	 * @param supportHirakuKbn セットする supportHirakuKbn
	 */
	public void setSupportHirakuKbn(String supportHirakuKbn) {
		this.supportHirakuKbn = supportHirakuKbn;
	}

	/**
	 * @return supportSybCode
	 */
	public String getSupportSybCode() {
		return supportSybCode;
	}

	/**
	 * @param supportSybCode セットする supportSybCode
	 */
	public void setSupportSybCode(String supportSybCode) {
		this.supportSybCode = supportSybCode;
	}

	/**
	 * @return supportSybCodeAbbrName
	 */
	public String getSupportSybCodeAbbrName() {
		return supportSybCodeAbbrName;
	}

	/**
	 * @param supportSybCodeAbbrName セットする supportSybCodeAbbrName
	 */
	public void setSupportSybCodeAbbrName(String supportSybCodeAbbrName) {
		this.supportSybCodeAbbrName = supportSybCodeAbbrName;
	}

	/**
	 * @return supportKeyword
	 */
	public String getSupportKeyword() {
		return supportKeyword;
	}

	/**
	 * @param supportKeyword セットする supportKeyword
	 */
	public void setSupportKeyword(String supportKeyword) {
		this.supportKeyword = supportKeyword;
	}

	/**
	 * @return supportStartDate
	 */
	public Timestamp getSupportStartDate() {
		return supportStartDate;
	}

	/**
	 * @param supportStartDate セットする supportStartDate
	 */
	public void setSupportStartDate(Timestamp supportStartDate) {
		this.supportStartDate = supportStartDate;
	}

	/**
	 * @return supportTitle
	 */
	public String getSupportTitle() {
		return supportTitle;
	}

	/**
	 * @param supportTitle セットする supportTitle
	 */
	public void setSupportTitle(String supportTitle) {
		this.supportTitle = supportTitle;
	}

	/**
	 * @return updDate
	 */
	public Timestamp getUpdDate() {
		return updDate;
	}

	/**
	 * @param updDate セットする updDate
	 */
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	/**
	 * @return updUserKey
	 */
	public String getUpdUserKey() {
		return updUserKey;
	}

	/**
	 * @param updUserKey セットする updUserKey
	 */
	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url セットする url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return supportAreaKbn
	 */
	public String getSupportAreaKbn() {
		return supportAreaKbn;
	}

	/**
	 * @param supportAreaKbn セットする supportAreaKbn
	 */
	public void setSupportAreaKbn(String supportAreaKbn) {
		this.supportAreaKbn = supportAreaKbn;
	}

	/**
	 * @return supportSybCodeName
	 */
	public String getSupportSybCodeName() {
		return supportSybCodeName;
	}

	/**
	 * @param supportSybCodeName セットする supportSybCodeName
	 */
	public void setSupportSybCodeName(String supportSybCodeName) {
		this.supportSybCodeName = supportSybCodeName;
	}
}
