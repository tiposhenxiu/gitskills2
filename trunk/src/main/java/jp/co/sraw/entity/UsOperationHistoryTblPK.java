package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the us_operation_history_tbl database table.
 * 
 */
@Embeddable
public class UsOperationHistoryTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="operation_user_key")
	private String operationUserKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="operation_date")
	private java.util.Date operationDate;

	@Column(name="operation_func_id")
	private String operationFuncId;

	@Column(name="operation_action_id")
	private String operationActionId;

	public UsOperationHistoryTblPK() {
	}
	public String getOperationUserKey() {
		return this.operationUserKey;
	}
	public void setOperationUserKey(String operationUserKey) {
		this.operationUserKey = operationUserKey;
	}
	public java.util.Date getOperationDate() {
		return this.operationDate;
	}
	public void setOperationDate(java.util.Date operationDate) {
		this.operationDate = operationDate;
	}
	public String getOperationFuncId() {
		return this.operationFuncId;
	}
	public void setOperationFuncId(String operationFuncId) {
		this.operationFuncId = operationFuncId;
	}
	public String getOperationActionId() {
		return this.operationActionId;
	}
	public void setOperationActionId(String operationActionId) {
		this.operationActionId = operationActionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsOperationHistoryTblPK)) {
			return false;
		}
		UsOperationHistoryTblPK castOther = (UsOperationHistoryTblPK)other;
		return 
			this.operationUserKey.equals(castOther.operationUserKey)
			&& this.operationDate.equals(castOther.operationDate)
			&& this.operationFuncId.equals(castOther.operationFuncId)
			&& this.operationActionId.equals(castOther.operationActionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.operationUserKey.hashCode();
		hash = hash * prime + this.operationDate.hashCode();
		hash = hash * prime + this.operationFuncId.hashCode();
		hash = hash * prime + this.operationActionId.hashCode();
		
		return hash;
	}
}