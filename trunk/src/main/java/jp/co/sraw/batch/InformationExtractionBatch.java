package jp.co.sraw.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.controller.event.EventForm;
import jp.co.sraw.controller.internship.InternshipForm;
import jp.co.sraw.controller.support.SupportForm;
import jp.co.sraw.dto.EventDto;
import jp.co.sraw.dto.InternshipViewDto;
import jp.co.sraw.dto.SpSupportDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.BatchTargetService;
import jp.co.sraw.service.EventServiceImpl;
import jp.co.sraw.service.InternshipServiceImpl;
import jp.co.sraw.service.SupportServiceImpl;

@Component
public class InformationExtractionBatch implements BatchRunner {
	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(InformationExtractionBatch.class);

	@Autowired
	private BatchTargetService batchTargetService;// batch処理のサービス

	@Autowired
	private EventServiceImpl eventServiceImpl;// イベントのサービス

	@Autowired
	private SupportServiceImpl supportServiceImpl;// 支援制度のサービス

	@Autowired
	private InternshipServiceImpl internshipServiceImpl;// インターンシップのサービス

	@Override
	public boolean run(Map<String, String> parameters) throws Exception {

		// 引数：調整日数
		String targetDays = parameters.get("targetdays");
		int smallint = 0;
		try {
			smallint = Integer.parseInt(targetDays);
		} catch (Exception e) {
			throw e;
		}

		// 支援制度からの情報抽出
		List<SpSupportDto> spSupportDtoList = this.findSupportBatchTarget(smallint);
		// イベントからの情報抽出
		List<EventDto> eventDtoList = this.findEventBatchTarget(smallint);
		// インターンシップからの情報抽出
		List<InternshipViewDto> internshipViewDtoList = this.findInternshipBatchTarget(smallint);

		//バッチ処理用抽出データの編集
		Boolean supportFlag = batchTargetService.updateSupportBatchTarget(spSupportDtoList);
		Boolean eventFlag = batchTargetService.updateEventBatchTarget(eventDtoList);
		Boolean internshipFlag = batchTargetService.updateInternshipBatchTarget(internshipViewDtoList);

		// すべてupdate成功なら、trueを戻す
		if (supportFlag && eventFlag && internshipFlag) {
			return true;
		}
		return false;
	}

	// 支援制度からの情報抽出の方法
	public List<SpSupportDto> findSupportBatchTarget(int smallint) {
		List<SpSupportDto> spSupportDtoList = new ArrayList<>();
		SupportForm form = new SupportForm();
		form.setSearchPublicFlag("1");
		form.setSearchDateType("batch");
		spSupportDtoList = supportServiceImpl.findSupportBatchTarget(form, CommonConst.DEFAULT_LOCALE);
		return spSupportDtoList;
	}

	// イベントからの情報抽出の方法
	public List<EventDto> findEventBatchTarget(int smallint) {
		List<EventDto> eventDtoList = new ArrayList<>();
		EventForm form = new EventForm();
		form.setSearchPublicFlag("1");
		form.setSearchDateType("batch");
		eventDtoList = eventServiceImpl.findEventBatchTarget(form, CommonConst.DEFAULT_LOCALE);
		return eventDtoList;
	}

	// インターンシップからの情報抽出の方法
	public List<InternshipViewDto> findInternshipBatchTarget(int smallint) {
		List<InternshipViewDto> internshipViewDtoList = new ArrayList<>();
		InternshipForm form = new InternshipForm();
		form.setSearchPublicFlag("1");
		form.setSearchDateType("batch");
		internshipViewDtoList = internshipServiceImpl.findInternshipBatchTarget(form, CommonConst.DEFAULT_LOCALE);
		return internshipViewDtoList;
	}

}
