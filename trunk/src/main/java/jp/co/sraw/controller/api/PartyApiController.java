/*
* ファイル名：MsPartyApiController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/16   toishigawa  新規作成
*/
package jp.co.sraw.controller.api;

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
import jp.co.sraw.dto.PartyDto;
import jp.co.sraw.entity.MsPartyTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.MsPartyServiceImpl;
import jp.co.sraw.util.StringUtil;

/**
* <B>MsPartyApiControllerクラス</B>
* <P>
* 組織APIのメソッドを提供する
*/
@RestController
@RequestMapping(CommonConst.PATH_API +"/party")
public class PartyApiController extends CommonController {
	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(PartyApiController.class);

	@Autowired
	private MsPartyServiceImpl msPartyServiceImpl;

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * 組織リストを取得(表示用)
	 *
	 * @param locale
	 * @return
	 */
	@RequestMapping({"/all"})
	protected List<PartyDto> msPartyAllList(Locale locale){

		logger.infoCode("I0001", "msPartyAllList"); // I0001=メソッド開始:{0}

		List<PartyDto> resultList = msPartyKbnList(null, locale);

		logger.infoCode("I0002", "msPartyAllList"); // I0002=メソッド終了:{0}
		return resultList;
	}

	/**
	 * 組織区分の組織リストを取得(表示用)
	 *
	 * @param locale
	 * @return
	 */
	@RequestMapping("/kbn/{partyKbn}")
	protected List<PartyDto> msPartyKbnList(@PathVariable String partyKbn, Locale locale){

		logger.infoCode("I0001", "msPartyKbnList"); // I0001=メソッド開始:{0}

		String[] kbn = null;
		if (StringUtil.isNotNull(partyKbn)) {
			kbn = new String[]{partyKbn};
		}
		List<MsPartyTbl> mList = msPartyServiceImpl.findAllByPartyKbn(kbn);
		List<PartyDto> resultList = new ArrayList<PartyDto>();
		for (MsPartyTbl m : mList) {
			String name = m.getPartyName();
			if (!CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
				name = m.getPartyNameEn();
			}
			PartyDto dto = new PartyDto();
			dto.setCode(m.getPartyCode());
			dto.setName(name);
			dto.setKbn(m.getPartyKbn());
			dto.setDomain(m.getDomain());
			resultList.add(dto);
		}

		logger.infoCode("I0002", "msPartyKbnList"); // I0002=メソッド終了:{0}
		return resultList;
	}
}
