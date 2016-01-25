package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the cm_batch_target_tbl database table.
 * 
 */
@Embeddable
public class CmBatchTargetTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="make_date")
	private java.util.Date makeDate;

	@Column(name="data_kbn")
	private String dataKbn;

	@Column(name="ref_data_key")
	private String refDataKey;

	public CmBatchTargetTblPK() {
	}
	public java.util.Date getMakeDate() {
		return this.makeDate;
	}
	public void setMakeDate(java.util.Date makeDate) {
		this.makeDate = makeDate;
	}
	public String getDataKbn() {
		return this.dataKbn;
	}
	public void setDataKbn(String dataKbn) {
		this.dataKbn = dataKbn;
	}
	public String getRefDataKey() {
		return this.refDataKey;
	}
	public void setRefDataKey(String refDataKey) {
		this.refDataKey = refDataKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CmBatchTargetTblPK)) {
			return false;
		}
		CmBatchTargetTblPK castOther = (CmBatchTargetTblPK)other;
		return 
			this.makeDate.equals(castOther.makeDate)
			&& this.dataKbn.equals(castOther.dataKbn)
			&& this.refDataKey.equals(castOther.refDataKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.makeDate.hashCode();
		hash = hash * prime + this.dataKbn.hashCode();
		hash = hash * prime + this.refDataKey.hashCode();
		
		return hash;
	}
}