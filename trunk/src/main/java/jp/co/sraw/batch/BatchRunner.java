/*
* ファイル名：BatchRunner.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/13   toishigawa  新規作成
*/
package jp.co.sraw.batch;

import java.util.Map;

/**
* <B>BatchRunnerインターフェイス</B>
* <P>
* バッチ起動のインターフェイスを提供する
*/
public interface BatchRunner {
	/**
	 * バッチの起動処理です。<br>
	 * バッチ起動コントローラから与えられた引数をバッチアプリケーションに引き継ぎます。<br>
	 * @param parameters	バッチ固有引数
	 * @throws Exception	エラー発生時
	 */
	boolean run(Map<String, String> parameters) throws Exception;
}
