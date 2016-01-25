/*
* ファイル名：FileUtil.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2011/02/01   H.Iwanaga  新規作成
*/
package jp.co.sraw.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import jp.co.sraw.common.CommonConst;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

/**
 * <B>ユーティリティ（ファイル操作関連）クラス</B>
 * <P>
 * ファイル操作関連のスタティックメソッドを提供する
 *
 */
public final class FileUtil {

	/**
	 * パス名の末尾がパス区切り文字でないときパス区切り文字を付加する
	 * <P>
	 *
	 * @param name
	 *            パス名
	 * @return パス
	 */
	public static String getPath(String name) {

		if (name.endsWith(CommonConst.PATH_CHAR)) {
			return name;
		} else {
			return name + CommonConst.PATH_CHAR;
		}
	}

	/**
	 * ファイルの存在チェックを行う
	 * <P>
	 *
	 * @param path
	 *            パス
	 * @return 存在する:true 存在しない:false
	 */
	public static boolean exists(String path) {

		// ファイル生成
		File file = new File(path);

		// ファイル存在チェック
		return file.exists();
	}

	/**
	 * ディレクトリが存在しないならディレクトリを作成する
	 * <P>
	 *
	 * @param path
	 *            パス
	 */
	public static void mkdir(String path) {

		// ディレクトリ生成
		File dir = new File(path);

		// ディレクトリが存在しないならディレクトリを作成する
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 入力ストリームよりファイルを出力する
	 *
	 * @param input
	 * @param pathName
	 * @return
	 */
	public static boolean putFile(MultipartFile input, String pathName) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			// 入力ストリーム作成
			bis = new BufferedInputStream(input.getInputStream());

			// 出力ストリーム作成
			bos = new BufferedOutputStream(new FileOutputStream(pathName));

			// バッファ出力

			int c = 0;
			while ((c = bis.read()) != -1) {
				bos.write(c);
			}
			bos.flush();
			return true;

		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * ファイルより出力ストリームへ出力する
	 *
	 * @param pathName
	 * @param fileName
	 * @param res
	 * @return
	 */
	public static boolean putFile(String pathName, String fileName, HttpServletResponse res) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(pathName));
			bos = new BufferedOutputStream(res.getOutputStream());

			res.setContentType("application/octet-stream");
			// res.setHeader("Content-disposition","attachment; filename=\""
			// + URLEncoder.encode(fileName,"UTF-8") + "\"");
			res.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

			int c = 0;
			while ((c = bis.read()) != -1) {
				bos.write(c);
			}
			bos.flush();
			return true;

		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * ファイルを削除する
	 * <P>
	 *
	 * @param path
	 *            パス
	 */
	public static void deleteFile(String path) {

		// ファイル生成
		File file = new File(path);

		// ファイル削除
		file.delete();
	}

	/**
	 * ブラウザを判断しエンコードを行う<BR>
	 * ※日本語ファイル名への対応
	 * <P>
	 *
	 * @param req
	 *            リクエスト情報
	 * @param fileName
	 *            ファイル名称
	 * @return エンコード後のファイル名称
	 */
	public static String encodeFileName(HttpServletRequest req, String fileName) {
		// User-Agentの取得

		String agent = req.getHeader("User-Agent").toUpperCase();
		String encodeFile = null;

		try {
			// ブラウザの判定

			if (agent.indexOf("MSIE") > -1) {
				// Internet Explorer
				encodeFile = URLEncoder.encode(fileName, "UTF-8");
			} else if (agent.indexOf("FIREFOX") > -1 || agent.indexOf("CHROME") > -1) {
				// FireFox, Google Chrome
				encodeFile = MimeUtility.encodeWord(fileName, "ISO-2022-JP", "B");
			} else if (agent.indexOf("SAFARI") > -1 || agent.indexOf("MAC") > -1) { // Safari、OSがMacの場合
				encodeFile = new String(fileName.getBytes("UTF-8"), "8859_1");
			} else {
				encodeFile = fileName;
			}
		} catch (UnsupportedEncodingException e) {
			encodeFile = fileName;
		}
		return encodeFile;
	}

	/**
	 * ファイル移動
	 *
	 * @param orgFilePath
	 *            移動元ファイルパス
	 * @param destDir
	 *            移動先ディレクトリ
	 */
	public static boolean moveFile(String orgFilePath, String destDir) {
		// 移動もとなるファイルパス
		File file = new File(orgFilePath);

		// 移動先ディレクトリ
		File dir = new File(destDir);

		// 移動

		return file.renameTo(new File(dir, file.getName()));
	}

	/**
	 * ディレクトリ内ファイル検索
	 *
	 * @param fileDir
	 *            ディレクトリパス
	 */
	public static File[] searchDir(String fileDir) {
		File dir = new File(fileDir);
		File[] files = dir.listFiles();
		return files;
	}

	/**
	 * ファイルコピー
	 *
	 * @param sf
	 *            コピー元ファイル
	 * @param df
	 *            コピー先ファイル名
	 */
	@SuppressWarnings("resource")
	public static void fileCopy(File sf, File df) {
		FileChannel sc = null, dc = null;
		try {
			sc = new FileInputStream(sf).getChannel();
			dc = new FileOutputStream(df).getChannel();
			dc.transferFrom(sc, 0, sc.size());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dc != null)
				try {
					dc.close();
				} catch (IOException e) {
				}
			if (sc != null)
				try {
					sc.close();
				} catch (IOException e) {
				}
		}
	}

	/**
	 * ディレクトリ削除
	 *
	 * @param dirOrFile
	 *            ディレクトリパス
	 */
	public static void deleteFileOrDir(File dirOrFile) {
		if (dirOrFile.isDirectory()) {
			String[] children = dirOrFile.list();
			if (children != null) {
				for (int i = 0; i < children.length; i++) {
					deleteFileOrDir(new File(dirOrFile, children[i]));
				}
			}
		}
		// 削除
		dirOrFile.delete();
	}

	/**
	 * ディレクトリやファイルサイズを取得
	 *
	 * @param file
	 *            ディレクトリまたはファイル
	 */
	public static long getDirSize(String path) {
		File file = new File(path);
		return getSize(file);
	}

	/**
	 * ディレクトリやファイルサイズを取得
	 *
	 * @param file
	 *            ディレクトリまたはファイル
	 */
	public static long getSize(File file) {
		long size = 0L;
		if (file == null) {
			return size;
		}
		if (file.isDirectory()) {
			File files[] = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					size += getSize(files[i]);
				}
			}
		} else {
			size = file.length();
		}
		return size;
	}

	/**
	 * ファイル名変更
	 *
	 * @param orgFilePath
	 *            変更元ファイルパス
	 * @param newFilePath
	 *            変更先ファイルパス
	 */
	public static boolean renameFile(String orgFilePath, String newFilePath) {
		// 変更元ファイルパス
		File orgfile = new File(orgFilePath);
		// 変更先ファイルパス
		File newFile = new File(newFilePath);
		// ファイル名変更
		return orgfile.renameTo(newFile);
	}

	public static ZipFile getDownloadFile(List<String> filelist) {

		return null;
	}

	public static boolean makeUploadFile(String action, String uploadKey, String path, File uploadFile, String fileType,
			String userKey) {

		return true;
	}

	public static ZipFile makeZipFile(File file, String password) throws Exception {
		//
		if (file == null) {
			throw new Exception();
		}
		//
		if (StringUtil.isNull(password)) {
			throw new Exception();
		}
		ZipFile zipFile = new ZipFile("");
		ZipParameters parameters = new ZipParameters();
		parameters.setEncryptFiles(true);
		parameters.setPassword(password);
		zipFile.createZipFile(file, parameters);
		return zipFile;
	}
}
