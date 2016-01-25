package jp.co.sraw.dto;

public class PartyDto {

	private String code; // 組織コード
	private String kbn;  // 組織区分
	private String name; // 組織名
	private String domain; //ドメイン
	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code セットする code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return kbn
	 */
	public String getKbn() {
		return kbn;
	}
	/**
	 * @param kbn セットする kbn
	 */
	public void setKbn(String kbn) {
		this.kbn = kbn;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return domain
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @param domain セットする domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}


}
