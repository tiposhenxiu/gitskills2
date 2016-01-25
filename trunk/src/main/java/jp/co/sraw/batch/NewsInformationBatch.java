package jp.co.sraw.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sraw.dto.BatchPublicDto;
import jp.co.sraw.dto.NewsDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.BatchTargetService;

@Component
public class NewsInformationBatch implements BatchRunner {
	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(NewsInformationBatch.class);

	@Autowired
	private BatchTargetService batchTargetService;

	public boolean run(Map<String, String> parameters) throws Exception {
		String targetDays = parameters.get("targetdays");
		int smallint = 0;
		try {
			smallint = Integer.parseInt(targetDays);
		} catch (Exception e) {
			throw e;
		}
		List<NewsDto> batchSupportList = new ArrayList<>();
		List<NewsDto> batchEventList = new ArrayList<>();
		List<NewsDto> batchInternshipList = new ArrayList<>();
		List<BatchPublicDto> supportResultList = new ArrayList<>();
		List<BatchPublicDto> evnetResultList = new ArrayList<>();
		List<BatchPublicDto> internshipResultList = new ArrayList<>();
		List<BatchPublicDto> evnetPublicResultList = new ArrayList<>();
		List<BatchPublicDto> internshipPublicResultList = new ArrayList<>();

		String opeKbn = null;
		String supportBatchFlag = "1";
		String eventBatchFlag = "2";
		String internshipBatchFlag = "3";
		String newsOrScheduleFlag = "1";
		int seqNo = 0;
		String publicKbn = null;
		String role = null;
		String partyCode = null;
		int i = 0;
		int j = 0;
		int k = 0;
		int m = 0;
		int n = 0;

		//バッチ処理用抽出データ、支援制度からの情報抽出
		batchSupportList = batchTargetService.findAllBatchSupport();

		for (i = 0; i < batchSupportList.size(); i++) {
			String infoRefKey = batchSupportList.get(i).getRefDataKey();
			String sendDate = batchSupportList.get(i).getSupportStartDate().toString();
			String title = batchSupportList.get(i).getSupportTitle();
			String dataKbn = batchSupportList.get(i).getDataKbn();
			supportResultList = batchTargetService.findAllNewsBatch(infoRefKey);
			//データが取得できなかった場合、０１．以外の場合、０２。
			if (supportResultList.size() > 0) {
				opeKbn = "02";
			} else {
				opeKbn = "01";
			}
			try {
				//お知らせ情報の登録、お知らせ情報公開範囲の登録
				batchTargetService.insertNewsBatch(sendDate, title, dataKbn, opeKbn, infoRefKey, supportBatchFlag,
						seqNo, publicKbn, role, partyCode, newsOrScheduleFlag, smallint);
			} catch (Exception e) {
				logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
				throw e;
			}
			//バッチ処理用抽出データの更新
			batchTargetService.updateNewsBatch(infoRefKey, dataKbn);
		}

		//バッチ処理用抽出データ、イベントからの情報抽出
		batchEventList = batchTargetService.findAllBatchEvent();

		for (j = 0; j < batchEventList.size(); j++) {
			String infoRefKey = batchEventList.get(i).getRefDataKey();
			String sendDate = batchEventList.get(i).getEventSendDate().toString();
			String title = batchEventList.get(i).getEventTitle();
			String dataKbn = batchEventList.get(i).getDataKbn();
			evnetResultList = batchTargetService.findAllNewsBatch(infoRefKey);
			evnetPublicResultList = batchTargetService.findAllEventPublicBatch(infoRefKey);
			//データが取得できなかった場合、０１．以外の場合、０２。
			if (evnetResultList.size() > 0) {
				opeKbn = "01";
			} else {
				opeKbn = "02";
			}

			try {
				for (m = 0; m < evnetPublicResultList.size(); m++) {
					seqNo = evnetPublicResultList.get(m).getSeqNo();
					publicKbn = evnetPublicResultList.get(m).getPublicKbn();
					role = evnetPublicResultList.get(m).getRole();
					partyCode = evnetPublicResultList.get(m).getPartyCode();
					//お知らせ情報の登録、お知らせ情報公開範囲の登録
					batchTargetService.insertNewsBatch(sendDate, title, dataKbn, opeKbn, infoRefKey, eventBatchFlag,
							seqNo, publicKbn, role, partyCode, newsOrScheduleFlag, smallint);
				}
			} catch (Exception e) {
				logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
				throw e;
			}
			//バッチ処理用抽出データの更新
			batchTargetService.updateNewsBatch(infoRefKey, dataKbn);
		}

		//バッチ処理用抽出データ、インターンシップからの情報抽出
		batchInternshipList = batchTargetService.findAllBatchInternship();

		for (k = 0; k < batchInternshipList.size(); k++) {
			String infoRefKey = batchInternshipList.get(i).getRefDataKey();
			String sendDate = batchInternshipList.get(i).getInternshipSendDate().toString();
			String title = batchInternshipList.get(i).getInternshipTitle();
			String dataKbn = batchInternshipList.get(i).getDataKbn();
			internshipResultList = batchTargetService.findAllNewsBatch(infoRefKey);
			internshipPublicResultList = batchTargetService.findAllInternshipPublicBatch(infoRefKey);
			//データが取得できなかった場合、０１．以外の場合、０２。
			if (internshipResultList.size() > 0) {
				opeKbn = "01";
			} else {
				opeKbn = "02";
			}
			try {
				for (n = 0; n < internshipPublicResultList.size(); n++) {
					seqNo = internshipPublicResultList.get(n).getSeqNo();
					publicKbn = internshipPublicResultList.get(n).getPublicKbn();
					role = internshipPublicResultList.get(n).getRole();
					partyCode = internshipPublicResultList.get(n).getPartyCode();
					//お知らせ情報の登録、お知らせ情報公開範囲の登録
					batchTargetService.insertNewsBatch(sendDate, title, dataKbn, opeKbn, infoRefKey,
							internshipBatchFlag, seqNo, publicKbn, role, partyCode, newsOrScheduleFlag, smallint);
				}
			} catch (Exception e) {
				logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
				throw e;
			}
			//バッチ処理用抽出データの更新
			batchTargetService.updateNewsBatch(infoRefKey, dataKbn);
		}

		return true;
	}
}
