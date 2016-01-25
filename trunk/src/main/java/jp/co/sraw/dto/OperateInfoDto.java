package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class OperateInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String operationUserKey;

	private Timestamp operationDate;

	private String operationFuncId;

	private String operationActionId;

	private Timestamp updDate;

	private String updUserKey;

	/**
	 * @return operationUserKey
	 */
	public String getOperationUserKey() {
		return operationUserKey;
	}

	/**
	 * @param operationUserKey
	 *            セットする operationUserKey
	 */
	public void setOperationUserKey(String operationUserKey) {
		this.operationUserKey = operationUserKey;
	}

	/**
	 * @return operationDate
	 */
	public Timestamp getOperationDate() {
		return operationDate;
	}

	/**
	 * @param operationDate
	 *            セットする operationDate
	 */
	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	/**
	 * @return operationFuncId
	 */
	public String getOerationFuncId() {
		return operationFuncId;
	}

	/**
	 * @param operationFuncId
	 *            セットする operationFuncId
	 */
	public void setOerationFuncId(String operationFuncId) {
		this.operationFuncId = operationFuncId;
	}

	/**
	 * @return operationActionId
	 */
	public String getOperationActionId() {
		return operationActionId;
	}

	/**
	 * @param operationActionId
	 *            セットする operationActionId
	 */
	public void setOperationActionId(String operationActionId) {
		this.operationActionId = operationActionId;
	}

	/**
	 * @return updDate
	 */
	public Timestamp getUpdDate() {
		return updDate;
	}

	/**
	 * @param updDate
	 *            セットする updDate
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
	 * @param updUserKey
	 *            セットする updUserKey
	 */
	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}
}
