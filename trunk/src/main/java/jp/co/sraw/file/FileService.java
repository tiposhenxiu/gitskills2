package jp.co.sraw.file;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonService;
import jp.co.sraw.dto.CmFileUploadDto;
import jp.co.sraw.entity.CmFileUploadTbl;
import jp.co.sraw.repository.CmFileUploadTblRepository;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;
import net.lingala.zip4j.core.ZipFile;

@Service
public class FileService extends CommonService {

	/** 当日日付の年月フォーマット */
	public static final String DEFAULT_YYYYMM = "yyyyMM";

	/** ファイル格納先のフォルダ名 */
	public static final String PATH_ROOT = "/opt/eportfolio/data";

	@Autowired
	private CmFileUploadTblRepository repository;

	@Autowired
	private EntityManager entityManager;

	@Override
	protected void init() {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * ユーザキーを元にファイルの格納先となるファイル格納パスを生成する
	 *
	 * @param operationUserKey
	 *            操作対象者ユーザキー
	 * @return ファイル格納パス
	 */
	public String makeFilePath(String operationUserKey) {
		String filePath = null;
		filePath = PATH_ROOT;
		filePath = filePath + CommonConst.PATH_CHAR + operationUserKey.substring(1);
		filePath = filePath + CommonConst.PATH_CHAR + operationUserKey.substring(2, 4);
		filePath = filePath + CommonConst.PATH_CHAR + operationUserKey.substring(5, 7);
		filePath = filePath + CommonConst.PATH_CHAR + operationUserKey.substring(8, 10);
		filePath = filePath + CommonConst.PATH_CHAR + DateUtil.getSysdate(DEFAULT_YYYYMM);
		return filePath;
	}

	/**
	 * ファイルアップロードテーブルから対象データの取得を行う。
	 *
	 * @param uploadKey
	 *            アップロードキー
	 * @return ファイルアップロードテーブルDTO
	 */
	public CmFileUploadDto getFileUploadTbl(String uploadKey) {
		if (StringUtil.isNull(uploadKey))
			return null;
		CmFileUploadTbl tbl = repository.findOne(uploadKey);
		if (tbl == null)
			return null;
		CmFileUploadDto dto = new CmFileUploadDto();
		objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

	/**
	 * ファイルアップロードテーブルから対象データの削除を行う。
	 *
	 * @param uploadKey
	 *            アップロードキー
	 */
	public void deleteFileUploadTbl(String uploadKey) {
		repository.delete(uploadKey);
	}

	/**
	 *
	 * ファイルアップロードテーブルの登録を行う。
	 *
	 * @param uploadKey
	 *            アップロードキー
	 * @param fileKbn
	 *            ファイル区分
	 * @param fileName
	 *            ファイル名
	 * @param filePath
	 *            ファイル格納パス
	 * @param fileSize
	 *            ファイル容量
	 * @param operationUserKey
	 *            操作対象者ユーザキー
	 * @param calcKbn
	 *            容量計算対象区分
	 * @param updUserKey
	 *            ログイン者ユーザキー
	 * @return true:正常終了。false:エラー。
	 */
	public boolean putFileUploadTbl(String uploadKey, String fileKbn, String fileName, String filePath, Long fileSize,
			String operationUserKey, String calcKbn, String updUserKey) {
		if (StringUtil.isNull(uploadKey) || StringUtil.isNull(fileKbn) || StringUtil.isNull(fileName)
				|| StringUtil.isNull(filePath) || StringUtil.isNull(operationUserKey) || StringUtil.isNull(updUserKey)
				|| fileSize == 0l)
			return false;
		CmFileUploadTbl entity = new CmFileUploadTbl();
		entity.setUploadKey(uploadKey);
		entity.setFileKbn(fileKbn);
		entity.setFileName(fileName);
		entity.setFilePutPath(filePath);
		entity.setUpdUserKey(operationUserKey);
		entity.setCalcFlag(calcKbn);
		entity.setFileSize(fileSize);
		repository.saveAndFlush(entity);
		return true;
	}

	/**
	 * ファイルアップロードテーブルから容量制限対象ファイルの使用済み容量を取得する。
	 *
	 * @param operationUserKey
	 *            操作対象者のユーザキー
	 * @return 使用済み容量
	 */
	public long getUserUsedFileSize(String operationUserKey) {
		String fileSizeValue = getSqlValue("FileService.getUserUsedFileSize", "userKey", operationUserKey);
		if (StringUtil.isNotNull(fileSizeValue)) {
			try {
				long fileSize = Long.parseLong(fileSizeValue);
				return fileSize;
			} catch (Exception e) {
				return 0l;
			}
		}
		return 0l;
	}

	/**
	 * 定数テーブルからファイル使用容量制限値を取得する。
	 *
	 * @param operationUserKey
	 *            操作対象者のユーザ区分
	 * @return 使用容量制限値
	 */
	public long getFileSizeLimit(String operationUserKbn) {
		String fileSizeValue = getSqlValue("FileService.getUserUsedFileSize", "userKbn", operationUserKbn);
		if (StringUtil.isNotNull(fileSizeValue)) {
			try {
				long fileSize = Long.parseLong(fileSizeValue);
				return fileSize;
			} catch (Exception e) {
				return 0l;
			}
		}
		return 0l;
	}

	private String getSqlValue(String sqlName, String paraName,String paraValue) {

		Query query = entityManager.createNamedQuery(sqlName);

		query.setParameter(paraName, paraValue);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List<Map> resultList =  query.getResultList();

		for (int i = 0; i < resultList.size(); i++) {
			return (String) resultList.get(i).get("VALUE");
		}

		return null;
	}

	/**
	 * 指定したファイルを元にファイルサーバ共有フォルダからファイルを取得し、解凍を行いファイルを返す
	 *
	 * @param filePath
	 *            ファイル格納パス
	 * @param uploadKey
	 *            ファイル名（アップロードキー）
	 * @return
	 * @throws Exception
	 */
	public ZipFile getFile(String filePath, String uploadKey) throws Exception {

		return null;
	}

}
