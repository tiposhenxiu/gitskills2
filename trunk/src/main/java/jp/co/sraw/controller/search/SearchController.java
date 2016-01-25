/*
* ファイル名：SupportController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.dto.SearchDto;
import jp.co.sraw.entity.MsPartyTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.MsPartyServiceImpl;
import jp.co.sraw.service.SearchServiceImpl;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>SupportControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/search")
public class SearchController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(SearchController.class);

	@Autowired
	private SearchServiceImpl searchServiceImpl;

	@Autowired
	private MsPartyServiceImpl msPartyServiceImpl;

	private static final String LIST_PAGE = "search/researchersList";

	private static final String LIST_PAGE_COMPANY = "search/companyList";

	private static final String LIST_PAGE_COUNSELING = "search/counselingList";

	// private static final String KBN = "1"; // 定数区分[0005]＝1:支援制度。2:機器設備利用

	private static final String CODE_COMPANY_CODE = "0007"; // 定数区分[0007]
															// 企業と大学の場合

	private static final String CODE_COUNSELING_SYBCODE = "0037"; // 定数区分[0037]
																	// 相談員、教職員の場合

	private static final String PUBLIC_FLAG = "1"; // 固定値: 公開

	String partyKbn = null;
	String userKbn = null;

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * 一覧
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/researchersList" })
	public String list(@ModelAttribute(CommonConst.FORM_NAME) SearchForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		// 公開範囲(初期は全部非公開)
		String[] partyKbn = new String[2];
		// 組織区分 ＝’5’、’6’
		partyKbn[0] = "5";
		partyKbn[1] = "6";
		List<MsPartyTbl> listParty = msPartyServiceImpl.findAllByPartyKbn(partyKbn);
		model.addAttribute("listParty", listParty);

		this.setListToModel(model, "0221", locale);
		this.setListToModel(model, "0222", locale);
		this.setListToModel(model, "0023", locale);


		// // 初期値
		// if (form.getSearchKeyword().equals(null)) {
		// form.setSearchKeyword("%%");
		// } else {
		//// String searchKeyword = form.getSearchKeyword().toString();
		//// form.setSearchKeyword("'%" +searchKeyword +"%'");
		// }
		// form.setSearchPartyCode("null");
		// form.setSearchBigField("null");
		// form.setSearchMiddleField("null");
		// form.setSearchSmallField("null");
		// 検索条件：flag
		List<SearchDto> slist = new ArrayList<>();
		String searchKeyword = null;
		String searchPartyCode = null;
		String searchBigField = null;
		String searchMiddleField = null;
		String searchSmallField = null;

		if (StringUtil.isNull(form.getSearchKeyword())) {
//			searchKeyword = "%%";
			slist = searchServiceImpl.searchAllUserNameNull();
		} else {
			searchKeyword ="%" + form.getSearchKeyword() + "%";
//			searchKeyword = form.getSearchKeyword();

			String searchAffiliation = form.getSearchAffiliation(); // 検索条件：所属機関
			if (StringUtil.isNull(form.getSearchPartyCode())) {
				searchPartyCode = "'%%'";
			} else {
				searchPartyCode =  "%" +form.getSearchPartyCode()+ "%";
			}

			if (StringUtil.isNull(form.getSearchBigField())) {
				searchBigField = "'%%'";
			} else {
				searchBigField =  "%" + form.getSearchBigField()+ "%"; // 検索条件：研究分野（大分類）
			}

			if (StringUtil.isNull(form.getSearchMiddleField())) {
				searchMiddleField = "'%%'";
			} else {
				searchMiddleField = "%" + form.getSearchMiddleField()+ "%"; // 検索条件：研究分野（中分類）
			}

			if (StringUtil.isNull(form.getSearchSmallField())) {
				searchSmallField = "'%%'";
			} else {
				searchSmallField =  "%" + form.getSearchSmallField()+ "%"; // 検索条件：研究分野（小分類）
			}
			// form.setSearchBigField("null");
			// form.setSearchMiddleField("null");
			// form.setSearchSmallField("null");

			// List<MsCodeDto> searchPartyCodeList
			// =searchServiceImpl.searchAllCompanyCode(searchAffiliation);

			slist = searchServiceImpl.searchAllUserName(searchKeyword, searchPartyCode, searchBigField, searchMiddleField,
					searchSmallField);

		}

		// Map<String, List<SpSupportDto>> mapList = new HashMap<String,
		// List<SpSupportDto>>();
		//
		// List<SearchDto> list = new ArrayList<SearchDto>();
		// for (int j = 0; j < slist.size(); j++) {
		// SearchDto sdto = slist.get(j);
		// // 種別毎に振り分け
		// list.add(sdto);
		// }
		// mapList.put(dto.getCode(), list);
		//

		model.addAttribute("slist", slist);
		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_PAGE;
	}

	/**
	 * 一覧
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/counselingList" })
	public String list2(@ModelAttribute(CommonConst.FORM_NAME) SearchForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		List<SearchDto> slist = new ArrayList<>();

		List<MsCodeDto> sybList = DbUtil.getJosuList(CODE_COUNSELING_SYBCODE, locale);

		Map<String, List<SearchDto>> mapList = new HashMap<String, List<SearchDto>>();

		List<MsCodeDto> sybList2 = new ArrayList<>();

		// 種別から一覧を生成
		for (int i = 0; i < sybList.size(); i++) {
			MsCodeDto dto = sybList.get(i);
			// 検索条件の選択
			dto.setSelected(true);
			userKbn = sybList.get(i).getCode();
			slist = searchServiceImpl.searchAllFacultyMember(userKbn);
			if (slist.size() > 0) {
				sybList2.add(dto);
			}
			List<SearchDto> list = new ArrayList<SearchDto>();
			for (int j = 0; j < slist.size(); j++) {
				SearchDto sdto = slist.get(j);
				// 種別毎に振り分け
				list.add(sdto);
			}
			mapList.put(dto.getCode(), list);
		}

		model.addAttribute("sybList", sybList);
		model.addAttribute("sybList2", sybList2);
		model.addAttribute("mapList", mapList);
		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_PAGE_COUNSELING;
	}

	/**
	 * 一覧
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/companyList" })
	public String list3(@ModelAttribute(CommonConst.FORM_NAME) SearchForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}
		List<SearchDto> slist = new ArrayList<>();

		List<MsCodeDto> sybList = DbUtil.getJosuList(CODE_COMPANY_CODE, locale);

		Map<String, List<SearchDto>> mapList = new HashMap<String, List<SearchDto>>();

		// 種別から一覧を生成
		for (int i = 0; i < sybList.size(); i++) {
			MsCodeDto dto = sybList.get(i);
			// 検索条件の選択
			dto.setSelected(true);
			partyKbn = sybList.get(i).getCode();
			slist = searchServiceImpl.searchAllCompany(partyKbn);
			List<SearchDto> list = new ArrayList<SearchDto>();
			for (int j = 0; j < slist.size(); j++) {
				SearchDto sdto = slist.get(j);
				// 種別毎に振り分け
				list.add(sdto);
			}
			mapList.put(dto.getCode(), list);
		}

		model.addAttribute("sybList", sybList);
		model.addAttribute("mapList", mapList);
		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_PAGE_COMPANY;
	}

	// /**
	// * 一覧
	// *
	// * @param name
	// * @param model
	// * @return
	// */
	// @RequestMapping({"/counselingList" })
	//// public String list3(@ModelAttribute(CommonConst.FORM_NAME) SearchForm
	// form, Model model, Locale locale) {
	//
	// logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}
	//
	// if (logger.isDebugEnabled() && userInfo != null) {
	// logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
	// logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
	// }
	// String partyCode = null;
	// List<SearchDto> slist = new ArrayList<>();
	//
	// List<MsCodeDto> sybList = DbUtil.getJosuList(CODE_SYBCODE, locale);
	//
	// // 初期値
	//// form.setSearchSpkikiKbn(KBN);
	//// form.setSearchPublicFlag(null);
	// // 期間中
	//// form.setSearchDateType(SupportServiceImpl.SEARCH_DATE_TYPE_CURRENT);
	//
	//// if (userInfo.isMgmt1()) {
	//// // 運営協議会事務局の場合
	//// form.setSearchDateType(null);
	//
	// for (int i = 0; i < sybList.size(); i++) {
	// partyCode = sybList.get(i).getCode();
	// slist = searchServiceImpl.searchAllCompany(partyCode);
	// }
	//// }
	//
	//
	// model.addAttribute("sybList", sybList);
	// model.addAttribute("slist", slist);
	// model.addAttribute(CommonConst.FORM_NAME, form);
	//
	// // dump
	// modelDump(logger, model, "list");
	//
	// logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}
	//
	// return LIST_PAGE_COUNSELING;
	// }

	protected void setListToModel(Model model, String listName, Locale locale) {
		List<MsCodeDto> list = DbUtil.getJosuList(listName, locale);
		model.addAttribute("list" + listName, list);
	}

}
