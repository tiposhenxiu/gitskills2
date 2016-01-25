package jp.co.sraw.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.dto.BatchPublicDto;
import jp.co.sraw.dto.EmailDto;
import jp.co.sraw.dto.NewsDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.BatchTargetService;

@Component
public class EmailBatch implements BatchRunner {
	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(EmailBatch.class);

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
		List<EmailDto> supportResultList = new ArrayList<>();
		List<EmailDto> eventResultList = new ArrayList<>();
		List<EmailDto> internshipResultList = new ArrayList<>();
		List<BatchPublicDto> evnetPublicResultList = new ArrayList<>();
		List<BatchPublicDto> internshipPublicResultList = new ArrayList<>();

		int i = 0;
		int j = 0;
		int k = 0;
		String infoKey = null;
		String role = null;
		String partyCode = null;

		// バッチ処理用抽出データ、支援制度からの情報抽出
		batchSupportList = batchTargetService.findAllBatchSupport();

		for (i = 0; i < batchSupportList.size(); i++) {
			String infoRefKey = batchSupportList.get(i).getRefDataKey();
			String dataKbn = batchSupportList.get(i).getDataKbn();
			infoKey = "%03%";
			// ユーザ情報の取得
			supportResultList = batchTargetService.findAllEmailBatch(infoKey);
			// 発信の処理
			batchTargetService.sendMailBatch(CommonConst.EMAIL_TITLE_SUPPORT);
			// バッチ処理用抽出データの更新
			batchTargetService.updateEmailBatch(infoRefKey, dataKbn);
		}

		// バッチ処理用抽出データ、イベントからの情報抽出
		batchEventList = batchTargetService.findAllBatchEvent();

		for (i = 0; i < batchEventList.size(); i++) {
			String infoRefKey = batchEventList.get(i).getRefDataKey();
			String dataKbn = batchEventList.get(i).getDataKbn();

			infoKey = "%01%";
			// ユーザ情報の取得
			eventResultList = batchTargetService.findAllEmailBatch(infoKey);
			// イベント公開範囲の参照
			evnetPublicResultList = batchTargetService.findAllEventPublicBatch(infoRefKey);

			for (j = 0; j < evnetPublicResultList.size(); j++) {
				role = evnetPublicResultList.get(j).getRole();
				partyCode = evnetPublicResultList.get(j).getPartyCode();

				// 公開区分が、1:ROLEの場合、ユーザ情報で取得したロールとイベント公開範囲で取得したロールが等しい。
				if ("1".equals(dataKbn)) {
					for (k = 0; k < eventResultList.size(); k++) {
						if (role.equals(eventResultList.get(k).getRole())) {
							batchTargetService.sendMailBatch(CommonConst.EMAIL_TITLE_EVENT);
						}
					}

					// 公開区分が、2:組織の場合、ユーザ情報で取得した組織コードとイベント公開範囲で取得した組織コードが等しい。
				} else if ("2".equals(dataKbn)) {
					for (k = 0; k < eventResultList.size(); k++) {
						if (partyCode.equals(eventResultList.get(k).getPartyCode())) {
							batchTargetService.sendMailBatch(CommonConst.EMAIL_TITLE_EVENT);
						}
					}
				}
			}
			// バッチ処理用抽出データの更新
			batchTargetService.updateEmailBatch(infoRefKey, dataKbn);
		}

		// バッチ処理用抽出データ、インターンシップからの情報抽出
		batchInternshipList = batchTargetService.findAllBatchInternship();

		for (i = 0; i < batchInternshipList.size(); i++) {
			String infoRefKey = batchInternshipList.get(i).getRefDataKey();
			String dataKbn = batchInternshipList.get(i).getDataKbn();
			infoKey = "%02%";
			// ユーザ情報の取得
			internshipResultList = batchTargetService.findAllEmailBatch(infoKey);
			// インターンシップ公開範囲の参照
			internshipPublicResultList = batchTargetService.findAllInternshipPublicBatch(infoRefKey);

			for (j = 0; j < internshipPublicResultList.size(); j++) {
				role = internshipPublicResultList.get(j).getRole();
				partyCode = internshipPublicResultList.get(j).getPartyCode();
				// 公開区分が、1:ROLEの場合、ユーザ情報で取得したロールとイベント公開範囲で取得したロールが等しい
				if ("1".equals(dataKbn)) {
					for (k = 0; k < internshipResultList.size(); k++) {
						if (role.equals(internshipResultList.get(k).getRole())) {
							batchTargetService.sendMailBatch(CommonConst.EMAIL_TITLE_INTERNSHIP);
						}
					}

					// 公開区分が、2:組織の場合、ユーザ情報で取得した組織コードとイベント公開範囲で取得した組織コードが等しい
				} else if ("2".equals(dataKbn)) {
					for (k = 0; k < internshipResultList.size(); k++) {
						if (partyCode.equals(internshipResultList.get(k).getPartyCode())) {
							batchTargetService.sendMailBatch(CommonConst.EMAIL_TITLE_INTERNSHIP);
						}
					}
				}
			}
			// バッチ処理用抽出データの更新
			batchTargetService.updateEmailBatch(infoRefKey, dataKbn);
		}
		return true;
	}
}
