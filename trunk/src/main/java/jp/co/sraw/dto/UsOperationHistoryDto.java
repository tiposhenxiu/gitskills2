package jp.co.sraw.dto;

import java.io.Serializable;
import java.util.Date;


public class UsOperationHistoryDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String operationUserKey;

	private Date operationDate;

	private String operationFuncId;

	private String operationActionId;

	private String updUserKey;

	private String userFamilyName;

	private String userFamilyNameEn;

	private String userMiddleName;

	private String userMiddleNameEn;

	private String userName;

	private String userNameEn;

	public UsOperationHistoryDto() {
		super();
	}

	public UsOperationHistoryDto(String operationUserKey,
								 Date operationDate,
								 String operationFuncId,
								 String operationActionId,
								 String updUserKey,
								 String userFamilyName,
								 String userFamilyNameEn,
								 String userMiddleName,
								 String userMiddleNameEn,
								 String userName,
								 String userNameEn) {
		super();
		this.operationUserKey = operationUserKey;
		this.operationDate = operationDate;
		this.operationFuncId = operationFuncId;
		this.operationActionId = operationActionId;
		this.updUserKey = updUserKey;
		this.userFamilyName = userFamilyName;
		this.userFamilyNameEn = userFamilyNameEn;
		this.userMiddleName = userMiddleName;
		this.userMiddleNameEn = userMiddleNameEn;
		this.userName = userName;
		this.userNameEn = userNameEn;
	}
	public String getOperationUserKey() {
		return this.operationUserKey;
	}
	public void setOperationUserKey(String operationUserKey) {
		this.operationUserKey = operationUserKey;
	}
	public Date getOperationDate() {
		return this.operationDate;
	}
	public void setOperationDate(Date operationDate) {
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

	public String getUpdUserKey() {
		return this.updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public String getUserFamilyName() {
		return this.userFamilyName;
	}

	public void setUserFamilyName(String userFamilyName) {
		this.userFamilyName = userFamilyName;
	}

	public String getUserFamilyNameEn() {
		return this.userFamilyNameEn;
	}

	public void setUserFamilyNameEn(String userFamilyNameEn) {
		this.userFamilyNameEn = userFamilyNameEn;
	}

	public String getUserMiddleName() {
		return this.userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserMiddleNameEn() {
		return this.userMiddleNameEn;
	}

	public void setUserMiddleNameEn(String userMiddleNameEn) {
		this.userMiddleNameEn = userMiddleNameEn;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameEn() {
		return this.userNameEn;
	}

	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}

}