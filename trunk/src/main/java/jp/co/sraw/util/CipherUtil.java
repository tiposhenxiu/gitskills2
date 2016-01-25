/*
* ファイル名：CipherUtil.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2011/02/01   iwanaga  新規作成
*/
package jp.co.sraw.util;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
* <B>暗号化クラス</B>
* <P>
* 暗号化関連のスタティックメソッドを提供する*/
public final class CipherUtil
{
	/**
	 * 鍵を生成する
	 *
	 * @param 鍵生成用文字列
	 */
	public static SecretKey getSecretKey(String key) {
		SecretKey skey = null;
		try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 md.update(key.getBytes());
			 byte[] pKey = md.digest();
			 DESKeySpec dkey = new DESKeySpec(pKey);
			 SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
			 skey = kf.generateSecret(dkey);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return skey;

	}

	/**
	 * 暗号化する
	 *
	 * <P>
	 * @return 暗号化した文字列
	 */
	public static byte[] encode(byte[] src, Key skey) {
		byte[] code = null;
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			code = cipher.doFinal(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	/**
	* 復号化する
	*
	* <P>
	* @return 復号化した文字列
	*/
	public static byte[] decode(byte[] src, Key skey) {
		byte[] code = null;
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			code =  cipher.doFinal(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}
}
