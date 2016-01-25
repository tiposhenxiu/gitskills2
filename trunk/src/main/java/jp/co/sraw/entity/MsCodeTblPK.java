package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ms_code_tbl database table.
 * 
 */
@Embeddable
public class MsCodeTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="josu_kbn")
	private String josuKbn;

	@Column(name="josu_code")
	private String josuCode;

	public MsCodeTblPK() {
	}
	public String getJosuKbn() {
		return this.josuKbn;
	}
	public void setJosuKbn(String josuKbn) {
		this.josuKbn = josuKbn;
	}
	public String getJosuCode() {
		return this.josuCode;
	}
	public void setJosuCode(String josuCode) {
		this.josuCode = josuCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MsCodeTblPK)) {
			return false;
		}
		MsCodeTblPK castOther = (MsCodeTblPK)other;
		return 
			this.josuKbn.equals(castOther.josuKbn)
			&& this.josuCode.equals(castOther.josuCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.josuKbn.hashCode();
		hash = hash * prime + this.josuCode.hashCode();
		
		return hash;
	}
}