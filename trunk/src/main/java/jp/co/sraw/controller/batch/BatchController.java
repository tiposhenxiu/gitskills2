/*
* ファイル名：BatchController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/13   toishigawa  新規作成
*/
package jp.co.sraw.controller.batch;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sraw.batch.BatchRunner;
import jp.co.sraw.batch.EmailBatch;
import jp.co.sraw.batch.InformationExtractionBatch;
import jp.co.sraw.batch.NewsInformationBatch;
import jp.co.sraw.batch.ScheduleBatch;
import jp.co.sraw.batch.SkillDiagBackupBatch;
import jp.co.sraw.batch.TestUserBatch;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;

/**
 * <B>BatchControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/service/batch")
public class BatchController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(BatchController.class);

	private static final String RESULT_OK = "OK";
	private static final String RESULT_NG = "NG";

	private static final String BATCH_TYPE_01 = "01"; // バッチ名=処理対象データ抽出
	private static final String BATCH_TYPE_02 = "02"; // バッチ名=お知らせ情報作成
	private static final String BATCH_TYPE_03 = "03"; // バッチ名=スケジュール作成
	private static final String BATCH_TYPE_04 = "04"; // バッチ名=eメール送信
	private static final String BATCH_TYPE_05 = "05"; // バッチ名=能力診断回答のバックアップ
	private static final String BATCH_TYPE_06 = "06"; // バッチ名=
	private static final String BATCH_TYPE_TEST = "test"; // バッチ名=テストバッチ

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@Autowired
	private TestUserBatch testUserBatch; // テストバッチ

	@Autowired
	private InformationExtractionBatch informationExtractionBatch;// 処理対象データ抽出

	@Autowired
	private NewsInformationBatch newsInformationBatch;// お知らせ情報作成

	@Autowired
	private ScheduleBatch scheduleBatch;// お知らせ情報作成

	@Autowired
	private EmailBatch emailBatch;// お知らせ情報作成

	@Autowired
	private SkillDiagBackupBatch skillDiagBackupBatch; // 能力診断回答のバックアップ
	
	/**
	 * Batch呼び出し
	 *
	 * @param accessKey
	 *            Batch起動のためのパラメータ
	 * @param batchcode
	 *            バッチ呼び出しコード
	 * @param parameters
	 *            バッチに渡す引数Map{param = value, param = value, ...}
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/{batchcode}" })
	public String batchCall(@RequestHeader(value = "Access-Key", defaultValue = "dummy") String accessKey,
			@PathVariable("batchcode") String batchcode, @RequestParam Map<String, String> parameters,
			HttpServletResponse response) {

		logger.infoCode("I0001", "batchCall"); // I0001=メソッド開始:{0}

		String rlt = RESULT_NG;

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Setting.AccessKey=" + systemSetting.getBatchAccessKey());
			logger.debug("AccessKey=" + accessKey);
			logger.debug("Batchcode=" + batchcode);
			logger.debug("Parameters=" + parameters);
		}

		// accessKeyが正しければ実行。applicationContext.xml参照
		if (systemSetting.getBatchAccessKey() != null && systemSetting.getBatchAccessKey().equals(accessKey)) {
			try {
				// 成功/失敗
				if (runner(batchcode, parameters)) {
					// 成功の場合
					rlt = RESULT_OK;
				} else {
					// 失敗の場合
					rlt = RESULT_NG;
				}
			} catch (Exception e) {
				// 失敗の場合
				rlt = RESULT_NG;
				logger.errorCode("E1013", e); // E1013=エラーとなりました。{0}
			}
		} else {
			rlt = RESULT_NG;
			try {
				// accessKeyが正しくなければ404を返す。
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		logger.infoCode("I0002", "batchCall"); // I0002=メソッド終了:{0}
		return rlt;
	}

	/**
	 * Batch起動
	 *
	 * @param batchcode
	 * @param args
	 * @return
	 * @throws Exception
	 */
	private boolean runner(String batchcode, Map<String, String> parameters) throws Exception {
		try {

			BatchRunner runner = null;

			if (batchcode == null) {
				return false;
			} else {
				switch (batchcode) {
				case BATCH_TYPE_TEST:
					 runner = testUserBatch;
					break;
				case BATCH_TYPE_01:
					 runner = informationExtractionBatch;
					break;
				case BATCH_TYPE_02:
					 runner = newsInformationBatch;
					break;
				case BATCH_TYPE_03:
					 runner = scheduleBatch;
					break;
				case BATCH_TYPE_04:
					runner = emailBatch;
					break;
				case BATCH_TYPE_05:
					runner = skillDiagBackupBatch;
					break;
				case BATCH_TYPE_06:
					// runner = xxxxxBatch;
					break;
				default:
					return false;
				}
			}

			// バッチアプリケーションを実行する
			boolean rlt = runner.run(parameters);

			return rlt;
		} catch (Exception e) {
			throw e;
		} finally {
			;
		}
	}
}
