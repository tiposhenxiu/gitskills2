package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItInternUploadDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String internshipKey;

	private String uploadKey;

	private String uploadKbn;

	private String fileKbn;

	private String fileName;

	private String filePutPath;

	private Timestamp updDate;

	private String updUserKey;

	public ItInternUploadDto() {
		super();
	}

	public ItInternUploadDto(String uploadKey, String fileName, String uploadKbn) {
		super();
		this.uploadKey = uploadKey;
		this.fileName = fileName;
		this.uploadKbn = uploadKbn;
	}

	public String getUploadKey() {
		return this.uploadKey;
	}

	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
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

	public String getUploadKbn() {
		return this.uploadKbn;
	}

	public void setUploadKbn(String uploadKbn) {
		this.uploadKbn = uploadKbn;
	}

	public String getInternshipKey() {
		return this.internshipKey;
	}
	public void setInternshipKey(String internshipKey) {
		this.internshipKey = internshipKey;
	}
}