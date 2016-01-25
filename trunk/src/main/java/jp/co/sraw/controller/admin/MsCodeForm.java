/*
* ファイル名：MsCodeForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.admin;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.AlphabetNumber;
import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.common.CommonForm;
import jp.co.sraw.validation.AlphaNumberSymbol;

/**
* <B>MsCodeFormクラス</B>
* <P>
* Formのメソッドを提供する
*/
public class MsCodeForm extends CommonForm {

	@NotNull
	@CharLength(min=1, max=4)
	private String josuKbn;

	@NotNull
	@AlphabetNumber
	@CharLength(min=1, max=6)
	private String josuCode;

	@NotNull
	@CharLength(min=1, max=600)
	private String josuName;

	@CharLength(max=600)
	@AlphaNumberSymbol
	private String josuNameEn;

	@CharLength(max=300)
	private String josuNameAbbr;

	@CharLength(max=300)
	@AlphaNumberSymbol
	private String josuNameAbbrEn;

	@CharLength(max=300)
	private String sntaZksei1Txt;

	@CharLength(max=300)
	private String sntaZksei2Txt;

	@CharLength(max=300)
	private String sntaZksei3Txt;

	@Max(Long.MAX_VALUE)
	@Min(Long.MIN_VALUE)
	private Long sntaZksei1Num;

	@Max(Long.MAX_VALUE)
	@Min(Long.MIN_VALUE)
	private Long sntaZksei2Num;

	@Max(Long.MAX_VALUE)
	@Min(Long.MIN_VALUE)
	private Long sntaZksei3Num;

	@CharLength(max=256)
	private String commentProperty;

	@NotNull
	private String useFlag;

	@NotNull
	@Max(9999)
	@Min(1)
	private Integer hyojiSrt;

	private Timestamp updDate;

	private String updUserKey;

	/**
	 * @return josuKbn
	 */
	public String getJosuKbn() {
		return josuKbn;
	}

	/**
	 * @param josuKbn セットする josuKbn
	 */
	public void setJosuKbn(String josuKbn) {
		this.josuKbn = josuKbn;
	}

	/**
	 * @return josuCode
	 */
	public String getJosuCode() {
		return josuCode;
	}

	/**
	 * @param josuCode セットする josuCode
	 */
	public void setJosuCode(String josuCode) {
		this.josuCode = josuCode;
	}

	/**
	 * @return josuName
	 */
	public String getJosuName() {
		return josuName;
	}

	/**
	 * @param josuName セットする josuName
	 */
	public void setJosuName(String josuName) {
		this.josuName = josuName;
	}

	/**
	 * @return josuNameEn
	 */
	public String getJosuNameEn() {
		return josuNameEn;
	}

	/**
	 * @param josuNameEn セットする josuNameEn
	 */
	public void setJosuNameEn(String josuNameEn) {
		this.josuNameEn = josuNameEn;
	}

	/**
	 * @return josuNameAbbr
	 */
	public String getJosuNameAbbr() {
		return josuNameAbbr;
	}

	/**
	 * @param josuNameAbbr セットする josuNameAbbr
	 */
	public void setJosuNameAbbr(String josuNameAbbr) {
		this.josuNameAbbr = josuNameAbbr;
	}

	/**
	 * @return josuNameAbbrEn
	 */
	public String getJosuNameAbbrEn() {
		return josuNameAbbrEn;
	}

	/**
	 * @param josuNameAbbrEn セットする josuNameAbbrEn
	 */
	public void setJosuNameAbbrEn(String josuNameAbbrEn) {
		this.josuNameAbbrEn = josuNameAbbrEn;
	}

	/**
	 * @return sntaZksei1Txt
	 */
	public String getSntaZksei1Txt() {
		return sntaZksei1Txt;
	}

	/**
	 * @param sntaZksei1Txt セットする sntaZksei1Txt
	 */
	public void setSntaZksei1Txt(String sntaZksei1Txt) {
		this.sntaZksei1Txt = sntaZksei1Txt;
	}

	/**
	 * @return sntaZksei2Txt
	 */
	public String getSntaZksei2Txt() {
		return sntaZksei2Txt;
	}

	/**
	 * @param sntaZksei2Txt セットする sntaZksei2Txt
	 */
	public void setSntaZksei2Txt(String sntaZksei2Txt) {
		this.sntaZksei2Txt = sntaZksei2Txt;
	}

	/**
	 * @return sntaZksei3Txt
	 */
	public String getSntaZksei3Txt() {
		return sntaZksei3Txt;
	}

	/**
	 * @param sntaZksei3Txt セットする sntaZksei3Txt
	 */
	public void setSntaZksei3Txt(String sntaZksei3Txt) {
		this.sntaZksei3Txt = sntaZksei3Txt;
	}

	/**
	 * @return sntaZksei1Num
	 */
	public Long getSntaZksei1Num() {
		return sntaZksei1Num;
	}

	/**
	 * @param sntaZksei1Num セットする sntaZksei1Num
	 */
	public void setSntaZksei1Num(Long sntaZksei1Num) {
		this.sntaZksei1Num = sntaZksei1Num;
	}

	/**
	 * @return sntaZksei2Num
	 */
	public Long getSntaZksei2Num() {
		return sntaZksei2Num;
	}

	/**
	 * @param sntaZksei2Num セットする sntaZksei2Num
	 */
	public void setSntaZksei2Num(Long sntaZksei2Num) {
		this.sntaZksei2Num = sntaZksei2Num;
	}

	/**
	 * @return sntaZksei3Num
	 */
	public Long getSntaZksei3Num() {
		return sntaZksei3Num;
	}

	/**
	 * @param sntaZksei3Num セットする sntaZksei3Num
	 */
	public void setSntaZksei3Num(Long sntaZksei3Num) {
		this.sntaZksei3Num = sntaZksei3Num;
	}

	/**
	 * @return useFlag
	 */
	public String getUseFlag() {
		return useFlag;
	}

	/**
	 * @param useFlag セットする useFlag
	 */
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	/**
	 * @return hyojiSrt
	 */
	public Integer getHyojiSrt() {
		return hyojiSrt;
	}

	/**
	 * @param hyojiSrt セットする hyojiSrt
	 */
	public void setHyojiSrt(Integer hyojiSrt) {
		this.hyojiSrt = hyojiSrt;
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
	 * @return commentProperty
	 */
	public String getCommentProperty() {
		return commentProperty;
	}

	/**
	 * @param commentProperty セットする commentProperty
	 */
	public void setCommentProperty(String commentProperty) {
		this.commentProperty = commentProperty;
	}

}
