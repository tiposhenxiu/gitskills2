/*
* ファイル名：ResearchAreaApiController.java
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
import jp.co.sraw.dto.ResearchAreaDto;
import jp.co.sraw.entity.MsCodeTbl;
import jp.co.sraw.entity.MsResearchAreaTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.MsResearchAreaServiceImpl;

/**
* <B>ResearchAreaApiControllerクラス</B>
* <P>
* 研究分野APIのメソッドを提供する
*/
@RestController
@RequestMapping(CommonConst.PATH_API +"/researchAreaCode")
public class ResearchAreaApiController extends CommonController {
	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(ResearchAreaApiController.class);

	@Autowired
	private MsResearchAreaServiceImpl msResearchAreaServiceImpl;

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * 研究分野の大分類リストを取得(表示用)
	 *
	 * @param locale
	 * @return
	 */
	@RequestMapping({"/fieldCode"})
	protected List<ResearchAreaDto> researchFieldCodeList(Locale locale){

		logger.infoCode("I0001", "researchFieldCodeList"); // I0001=メソッド開始:{0}

		List<MsCodeTbl> mList = msResearchAreaServiceImpl.findAllFieldCodeList(MsResearchAreaServiceImpl.FIELD_CODE_KBN, CommonConst.USE_FALG_ACTIVE);
		List<ResearchAreaDto> resultList = new ArrayList<ResearchAreaDto>();
		for (MsCodeTbl m : mList) {
			String name = m.getJosuName();
			if (!CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
				name = m.getJosuNameEn();
			}
			ResearchAreaDto dto = new ResearchAreaDto();
			dto.setCode(m.getId().getJosuCode());
			dto.setName(name);
			resultList.add(dto);
		}

		logger.infoCode("I0002", "researchFieldCodeList"); // I0002=メソッド終了:{0}
		return resultList;
	}

	/**
	 * 研究分野の中分類リストを取得(表示用)
	 *
	 * @param locale
	 * @return
	 */
	@RequestMapping("/fieldCode/{fieldCode}")
	protected List<ResearchAreaDto> researchSubjectCodeList(@PathVariable String fieldCode, Locale locale){

		logger.infoCode("I0001", "researchSubjectCodeList"); // I0001=メソッド開始:{0}

		List<MsCodeTbl> mList = msResearchAreaServiceImpl.findAllSubjectCodeList(MsResearchAreaServiceImpl.SUBJECT_CODE_KBN, fieldCode, CommonConst.USE_FALG_ACTIVE);
		List<ResearchAreaDto> resultList = new ArrayList<ResearchAreaDto>();
		for (MsCodeTbl m : mList) {
			String name = m.getJosuName();
			if (!CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
				name = m.getJosuNameEn();
			}
			ResearchAreaDto dto = new ResearchAreaDto();
			dto.setCode(m.getId().getJosuCode());
			dto.setName(name);
			resultList.add(dto);
		}

		logger.infoCode("I0002", "researchSubjectCodeList"); // I0002=メソッド終了:{0}
		return resultList;
	}

	/**
	 * 研究分野の小分類リストを取得(表示用)
	 *
	 * @param locale
	 * @return
	 */
	@RequestMapping("/fieldCode/{fieldCode}/{subjectCode}")
	protected List<ResearchAreaDto> researchAreaCodeList(@PathVariable String fieldCode, @PathVariable String subjectCode, Locale locale){

		logger.infoCode("I0001", "researchAreaCodeList"); // I0001=メソッド開始:{0}

		List<MsResearchAreaTbl> mList = msResearchAreaServiceImpl.findAllResearchAreaCodeList(fieldCode, subjectCode, CommonConst.USE_FALG_ACTIVE);
		List<ResearchAreaDto> resultList = new ArrayList<ResearchAreaDto>();
		for (MsResearchAreaTbl m : mList) {
			String name = m.getResearchAreaName();
			if (!CommonConst.DEFAULT_LOCALE.getLanguage().equals(locale.getLanguage())) {
				name = m.getResearchAreaNameEn();
			}
			ResearchAreaDto dto = new ResearchAreaDto();
			dto.setCode(m.getResearchAreaCode());
			dto.setName(name);
			resultList.add(dto);
		}

		logger.infoCode("I0002", "researchAreaCodeList"); // I0002=メソッド終了:{0}
		return resultList;
	}

}
