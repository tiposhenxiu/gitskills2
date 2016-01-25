package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;


/**
 * The persistent class for the cm_file_upload_tbl database table.
 * 
 */
@Entity
@Table(name="cm_file_upload_tbl")
@NamedQuery(name="CmFileUploadTbl.findAll", query="SELECT c FROM CmFileUploadTbl c")
public class CmFileUploadTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="CM_FILE_UPLOAD_TBL_UPLOADKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="CM_FILE_UPLOAD_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CM_FILE_UPLOAD_TBL_UPLOADKEY_GENERATOR")
	@Column(name="upload_key")
	private String uploadKey;

	@Column(name="calc_flag")
	private String calcFlag;

	@Column(name="file_kbn")
	private String fileKbn;

	@Column(name="file_name")
	private String fileName;

	@Column(name="file_put_path")
	private String filePutPath;

	@Column(name="file_size")
	private Long fileSize;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	@Column(name="user_key")
	private String userKey;

	public String getRealPath() {
		if ( filePutPath == null ) {
			return null;
		}
		return filePutPath + "/" + uploadKey;
	}
	
	public CmFileUploadTbl() {
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