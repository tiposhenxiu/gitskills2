package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ms_code_tbl database table.
 * 
 */
@Entity
@Table(name="ms_code_tbl")
@NamedQuery(name="MsCodeTbl.findAll", query="SELECT m FROM MsCodeTbl m")
public class MsCodeTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MsCodeTblPK id;

	@Column(name="comment_property")
	private String commentProperty;

	@Column(name="hyoji_srt")
	private Integer hyojiSrt;

	@Column(name="josu_name")
	private String josuName;

	@Column(name="josu_name_abbr")
	private String josuNameAbbr;

	@Column(name="josu_name_abbr_en")
	private String josuNameAbbrEn;

	@Column(name="josu_name_en")
	private String josuNameEn;

	@Column(name="snta_zksei_1_num")
	private Long sntaZksei1Num;

	@Column(name="snta_zksei_1_txt")
	private String sntaZksei1Txt;

	@Column(name="snta_zksei_2_num")
	private Long sntaZksei2Num;

	@Column(name="snta_zksei_2_txt")
	private String sntaZksei2Txt;

	@Column(name="snta_zksei_3_num")
	private Long sntaZksei3Num;

	@Column(name="snta_zksei_3_txt")
	private String sntaZksei3Txt;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="use_flag")
	private String useFlag;

	public MsCodeTbl() {
	}

	public MsCodeTblPK getId() {
		return this.id;
	}

	public void setId(MsCodeTblPK id) {
		this.id = id;
	}

	public String getCommentProperty() {
		return this.commentProperty;
	}

	public void setCommentProperty(String commentProperty) {
		this.commentProperty = commentProperty;
	}

	public Integer getHyojiSrt() {
		return this.hyojiSrt;
	}

	public void setHyojiSrt(Integer hyojiSrt) {
		this.hyojiSrt = hyojiSrt;
	}

	public String getJosuName() {
		return this.josuName;
	}

	public void setJosuName(String josuName) {
		this.josuName = josuName;
	}

	public String getJosuNameAbbr() {
		return this.josuNameAbbr;
	}

	public void setJosuNameAbbr(String josuNameAbbr) {
		this.josuNameAbbr = josuNameAbbr;
	}

	public String getJosuNameAbbrEn() {
		return this.josuNameAbbrEn;
	}

	public void setJosuNameAbbrEn(String josuNameAbbrEn) {
		this.josuNameAbbrEn = josuNameAbbrEn;
	}

	public String getJosuNameEn() {
		return this.josuNameEn;
	}

	public void setJosuNameEn(String josuNameEn) {
		this.josuNameEn = josuNameEn;
	}

	public Long getSntaZksei1Num() {
		return this.sntaZksei1Num;
	}

	public void setSntaZksei1Num(Long sntaZksei1Num) {
		this.sntaZksei1Num = sntaZksei1Num;
	}

	public String getSntaZksei1Txt() {
		return this.sntaZksei1Txt;
	}

	public void setSntaZksei1Txt(String sntaZksei1Txt) {
		this.sntaZksei1Txt = sntaZksei1Txt;
	}

	public Long getSntaZksei2Num() {
		return this.sntaZksei2Num;
	}

	public void setSntaZksei2Num(Long sntaZksei2Num) {
		this.sntaZksei2Num = sntaZksei2Num;
	}

	public String getSntaZksei2Txt() {
		return this.sntaZksei2Txt;
	}

	public void setSntaZksei2Txt(String sntaZksei2Txt) {
		this.sntaZksei2Txt = sntaZksei2Txt;
	}

	public Long getSntaZksei3Num() {
		return this.sntaZksei3Num;
	}

	public void setSntaZksei3Num(Long sntaZksei3Num) {
		this.sntaZksei3Num = sntaZksei3Num;
	}

	public String getSntaZksei3Txt() {
		return this.sntaZksei3Txt;
	}

	public void setSntaZksei3Txt(String sntaZksei3Txt) {
		this.sntaZksei3Txt = sntaZksei3Txt;
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

}