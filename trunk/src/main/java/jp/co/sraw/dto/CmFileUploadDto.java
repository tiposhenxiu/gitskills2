package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmFileUploadDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uploadKey;

	private String calcFlag;

	private String fileKbn;

	private String fileName;

	private String filePutPath;

	private Long fileSize;

	private Timestamp updDate;

	private String updUserKey;

	private String userKey;

	public String getRealPath() {
		if (filePutPath == null) {
			return null;
		}
		return filePutPath + "/" + uploadKey;
	}

	public String getUploadKey() {
		return this.uploadKey;
	}

	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
	}

	public String getCalcFlag() {
		return this.calcFlag;
	}

	public void setCalcFlag(String calcFlag) {
		this.calcFlag = calcFlag;
	}

	public String getFileKbn() {
		return this.fileKbn;
	}

	public void setFileKbn(String fileKbn) {
		this.fileKbn = fileKbn;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePutPath() {
		return this.filePutPath;
	}

	public void setFilePutPath(String filePutPath) {
		this.filePutPath = filePutPath;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
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

	public String getUserKey() {
		return this.userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

}