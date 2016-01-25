/*
* ファイル名：ScheduleApiController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/19               新規作成
*/
package jp.co.sraw.controller.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.dto.CmScheduleDto;
import jp.co.sraw.entity.CmScheduleInfoView;
import jp.co.sraw.entity.CmScheduleTbl;
import jp.co.sraw.entity.UsScheduleTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.HomeServiceImpl;
import jp.co.sraw.util.StringUtil;

/**
* <B>ScheduleApiControllerクラス</B>
* <P>
* ポータルのスケジュールAPIのメソッドを提供する
*/
@RestController
@RequestMapping(CommonConst.PATH_API +"/schedule")
public class ScheduleApiController extends CommonController {
	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(PartyApiController.class);

	@Autowired
	private HomeServiceImpl homeServiceImpl;

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * ポータル用スケジュールを取得(表示用)
	 *
	 * @param locale
	 * @return
	 */
	@RequestMapping("/{dateRange}")
	protected List<CmScheduleDto> scheduleList(@PathVariable String dateRange, Locale locale){

		logger.infoCode("I0001", "scheduleList"); // I0001=メソッド開始:{0}

		String[] dateArray = null;
		if (StringUtil.isNotNull(dateRange)) {
			dateArray = dateRange.split(",");
		}

		List<UsScheduleTbl> usScheduleList = homeServiceImpl.findAllUsSchedule(dateArray, userInfo.getTargetUserKey());
		List<CmScheduleTbl> cmScheduleList = homeServiceImpl.findAllCmSchedule(dateArray, userInfo.getTargetPartyCode(), userInfo.getTargetRole().getAuthority());

		//取得した情報をマージする。
		List<CmScheduleDto> resultList = new ArrayList<CmScheduleDto>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (UsScheduleTbl usScheduleTbl : usScheduleList){
			CmScheduleDto cmScheduleDto = new CmScheduleDto();
			String suhduleDate = sdf.format(usScheduleTbl.getSuhduleDate());
			CmScheduleInfoView detail = homeServiceImpl.findOneCmScheduleInfoView(usScheduleTbl.getDataKbn(), usScheduleTbl.getSchduleRefKey());

			cmScheduleDto.setTitle(usScheduleTbl.getTitle());
			if (usScheduleTbl.getStartTime() != null && StringUtil.isNotNull(usScheduleTbl.getStartTime())) {
				cmScheduleDto.setStart(suhduleDate + " " + usScheduleTbl.getStartTime());
			} else {
				cmScheduleDto.setStart(suhduleDate);
			}
			if (usScheduleTbl.getEndTime() != null && StringUtil.isNotNull(usScheduleTbl.getEndTime())) {
				cmScheduleDto.setEnd(suhduleDate + " " + usScheduleTbl.getEndTime());
			} else {
				cmScheduleDto.setEnd(suhduleDate);
			}
			cmScheduleDto.setBackgroundColor("#00c0ef");
			cmScheduleDto.setBorderColor("#00c0ef");
			cmScheduleDto.setDataKbn(usScheduleTbl.getDataKbn());
			if (detail != null) {
				cmScheduleDto.setScheduleTitle(detail.getScheduleTitle());
				cmScheduleDto.setScheduleTelno(detail.getScheduleTelno());
				cmScheduleDto.setScheduleMemo(detail.getScheduleMemo());
				cmScheduleDto.setUrl(detail.getScheduleUrl());
			}
			resultList.add(cmScheduleDto);
		}
		for (CmScheduleTbl cmScheduleTbl : cmScheduleList){
			CmScheduleDto cmScheduleDto = new CmScheduleDto();
			String suhduleDate = sdf.format(cmScheduleTbl.getSuhduleDate());
			CmScheduleInfoView detail = homeServiceImpl.findOneCmScheduleInfoView(cmScheduleTbl.getDataKbn(), cmScheduleTbl.getSchduleRefKey());

			cmScheduleDto.setTitle(cmScheduleTbl.getTitle());
			if (cmScheduleTbl.getStartTime() != null && StringUtil.isNotNull(cmScheduleTbl.getStartTime())) {
				cmScheduleDto.setStart(suhduleDate + " " + cmScheduleTbl.getStartTime());
			} else {
				cmScheduleDto.setStart(suhduleDate);
			}
			if (cmScheduleTbl.getEndTime() != null && StringUtil.isNotNull(cmScheduleTbl.getEndTime())) {
				cmScheduleDto.setEnd(suhduleDate + " " + cmScheduleTbl.getEndTime());
			} else {
				cmScheduleDto.setEnd(suhduleDate);
			}
			cmScheduleDto.setBackgroundColor("#00c0ef");
			cmScheduleDto.setBorderColor("#00c0ef");
			cmScheduleDto.setDataKbn(cmScheduleTbl.getDataKbn());
			if (detail != null) {
				cmScheduleDto.setScheduleTitle(detail.getScheduleTitle());
				cmScheduleDto.setScheduleTelno(detail.getScheduleTelno());
				cmScheduleDto.setScheduleMemo(detail.getScheduleMemo());
				cmScheduleDto.setUrl(detail.getScheduleUrl());
			}
			resultList.add(cmScheduleDto);
		}

		logger.infoCode("I0002", "scheduleList"); // I0002=メソッド終了:{0}
		return resultList;
	}

}
