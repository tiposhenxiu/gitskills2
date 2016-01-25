/*
* ファイル名：SkillDiagController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/05   etoh        新規作成
*/
package jp.co.sraw.controller.skill;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import jp.co.sraw.common.CommonConst;
/**
 * <B>能力診断・結果表示機能用コントローラ</B>
 * <P>
 * アクションプランやエビデンスの登録、表示など。
 */
import jp.co.sraw.common.CommonController;
import jp.co.sraw.dto.SkillAnswerDto;
import jp.co.sraw.dto.SkillPastAnswerDto;
import jp.co.sraw.entity.CmFileUploadTbl;
import jp.co.sraw.entity.NrLessonRelSubjectTbl;
import jp.co.sraw.entity.NrSubjectAnswerBkupTbl;
import jp.co.sraw.entity.NrSubjectAnswerTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.oxm.rubric.Rubric;
import jp.co.sraw.oxm.rubric.RubricCategory;
import jp.co.sraw.service.CmFileUploadServiceImpl;
import jp.co.sraw.service.RubricServiceImpl;
import jp.co.sraw.service.SkillDiagServiceImpl;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

@Controller
@RequestMapping("/skill/diag")
public class SkillDiagController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(RubricMgmtController.class);

	// 遷移先。
	private static final String INDEX_PAGE = "skill/diag/index";
	private static final String VIEW_LIST_PAGE = "skill/diag/view_list";
	private static final String VIEW_WHOLE_PAGE = "skill/diag/view_whole";
	private static final String VIEW_PAST_PAGE = "skill/diag/view_past";

	// 定数区分/コード。
	private static final String JKBN_LENSNAME = "0001";
	private static final String JKBN_DEGREE = "0006";

	@Autowired
	private RubricServiceImpl rubricService;

	@Autowired
	private SkillDiagServiceImpl diagService;

	@Autowired
	private CmFileUploadServiceImpl fileService;

	@Override
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	// 能力診断トップ画面表示。
	@RequestMapping({ "", "/", "/index" })
	public String index(HttpServletRequest request, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// TODO: how to get rkey?
		String rkey = "1000000033";
		Rubric rub = rubricService.findOne(rkey);

		model.addAttribute("rubricKey", rkey);
		model.addAttribute("rubricName", rub.getName());
		model.addAttribute("rubricSummary", rub.getSummary());

		logger.infoCode("I0002", INDEX_PAGE);
		return INDEX_PAGE;
	}

	// 能力診断入力画面表示。
	@RequestMapping("/edit/{rkey}/{lensId}")
	public String edit(HttpServletRequest request, @PathVariable("rkey") String rkey,
			@PathVariable("lensId") String lensId, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// TODO:

		logger.infoCode("I0002", INDEX_PAGE);
		return INDEX_PAGE;
	}

	// 能力診断結果一覧画面表示。
	@RequestMapping("/viewList/{rkey}/{lensId}")
	public String viewList(HttpServletRequest request, @PathVariable("rkey") String rkey,
			@PathVariable("lensId") String lensId, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		prepareModelForView(model, rkey, lensId, locale, null);

		logger.infoCode("I0002", VIEW_LIST_PAGE);
		return VIEW_LIST_PAGE;
	}

	// 能力診断結果一覧(過去)画面表示。
	@RequestMapping("/viewList/{rkey}/{lensId}/{savedDate}")
	public String viewList(HttpServletRequest request, @PathVariable("rkey") String rkey,
			@PathVariable("lensId") String lensId, @PathVariable("savedDate") String savedDate, Model model,
			Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		prepareModelForView(model, rkey, lensId, locale, savedDate);

		logger.infoCode("I0002", VIEW_LIST_PAGE);
		return VIEW_LIST_PAGE;
	}

	private void prepareModelForView(Model model, String rkey, String lensId, Locale locale, String savedDate) {
		// ルーブリック情報(レンズを通す)。
		Rubric rub = rubricService.findOne(rkey);
		rubricService.filterByLens(rub, Integer.valueOf(lensId));
		model.addAttribute("rubric", rub);

		// 回答をDiagFormに詰めて。
		DiagForm form = new DiagForm();
		if (savedDate == null) { // 最新結果を表示?
			List<NrSubjectAnswerTbl> answers = diagService.findAnswers(userInfo.getTargetUserKey(), rkey);
			diagService.populateForm(form, answers, rub);
			model.addAttribute("lastModifiedStr",
					answers.isEmpty() ? "" : DateUtil.dateTimeFormat(answers.get(0).getUpdDate(), false));
		} else { // 過去結果を表示。
			List<NrSubjectAnswerBkupTbl> answers = diagService.findPastAnswers(userInfo.getTargetUserKey(), rkey,
					DateUtil.toDate(savedDate));
			diagService.populateFormForPast(form, answers, rub);
			model.addAttribute("lastModifiedStr",
					answers.isEmpty() ? "" : DateUtil.dateTimeFormat(answers.get(0).getUpdDate(), false));
			model.addAttribute("savedDate", savedDate);
		}
		model.addAttribute("answers", form);

		// そのほか。
		model.addAttribute("lensName", DbUtil.getJosuName(JKBN_LENSNAME, lensId, locale));
		model.addAttribute("degreeName", lookupDegreeName(locale));
	}

	private String lookupDegreeName(Locale locale) {
		String name = DbUtil.getJosuName(JKBN_DEGREE, userInfo.getTargetDegree(), locale);
		if (StringUtil.isNull(name)) {
			return "";
		}
		return name + "(" + DbUtil.getAttrText1(JKBN_DEGREE, userInfo.getTargetDegree()) + ")";
	}

	// 能力診断結果全体画面表示。
	@RequestMapping("/viewWhole/{rkey}/{lensId}")
	public String viewWhole(HttpServletRequest request, @PathVariable("rkey") String rkey,
			@PathVariable("lensId") String lensId, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		prepareModelForView(model, rkey, lensId, locale, null);
		model.addAttribute("lessons", lessonMap(rkey)); // 推奨科目。

		logger.infoCode("I0002", VIEW_WHOLE_PAGE);
		return VIEW_WHOLE_PAGE;
	}

	// 能力診断結果全体(過去)画面表示。
	@RequestMapping("/viewWhole/{rkey}/{lensId}/{savedDate}")
	public String viewWhole(HttpServletRequest request, @PathVariable("rkey") String rkey,
			@PathVariable("lensId") String lensId, @PathVariable("savedDate") String savedDate, Model model,
			Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		prepareModelForView(model, rkey, lensId, locale, savedDate);
		model.addAttribute("lessons", lessonMap(rkey)); // 推奨科目。

		logger.infoCode("I0002", VIEW_WHOLE_PAGE);
		return VIEW_WHOLE_PAGE;
	}

	private Map<String, String> lessonMap(String rkey) {
		return diagService.findAllLessons(rkey).stream()
				.collect(Collectors.groupingBy(les -> les.getId().getSubjectCode(),
						Collectors.mapping(SkillDiagController::lessonPartyString, Collectors.joining("<br/>"))));
	}

	private static String lessonPartyString(NrLessonRelSubjectTbl ls) {
		return HtmlUtils.htmlEscape(ls.getNrLessonTbl().getLessonName()) + "("
				+ HtmlUtils.htmlEscape(ls.getNrLessonTbl().getParty().getPartyName()) + ")";
	}

	// 能力診断結果 能力別表示。
	// ↓URLパスの最後にピリオドが入る場合、Springがトリムしてしまう(books.jsonとか)ので、正規表現でマッチさせる。
	@RequestMapping(value = "/viewItem/{rkey}/{abilityCode:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SkillAnswerDto viewItem(HttpServletRequest request, @PathVariable("rkey") String rkey,
			@PathVariable("abilityCode") String abilityCode, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		SkillAnswerDto dto = buildSkillAnswer(request, rkey, abilityCode, null);

		logger.infoCode("I0002");
		return dto;
	}

	// 能力診断結果 能力別表示(過去)。
	@RequestMapping(value = "/viewItem/{rkey}/{abilityCode}/{savedDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SkillAnswerDto viewItem(HttpServletRequest request, @PathVariable("rkey") String rkey,
			@PathVariable("abilityCode") String abilityCode, @PathVariable("savedDate") String savedDate, Model model,
			Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		SkillAnswerDto dto = buildSkillAnswer(request, rkey, abilityCode, savedDate);

		logger.infoCode("I0002");
		return dto;
	}

	private SkillAnswerDto buildSkillAnswer(HttpServletRequest request, String rkey, String abilityCode,
			String savedDate) {
		Rubric rub = rubricService.findOne(rkey);
		RubricCategory item = rub.buildMap().get(abilityCode);

		SkillAnswerDto dto = new SkillAnswerDto();
		if (savedDate == null) { // 最新結果を表示?
			NrSubjectAnswerTbl ans = diagService.findAnswerByAbilityCode(userInfo.getTargetUserKey(), rkey,
					abilityCode);
			diagService.populateDto(dto, downloadBasePath(request),
					DbUtil.getAttrText1(JKBN_DEGREE, userInfo.getTargetDegree()), ans, item);
		} else { // 過去結果を表示。
			NrSubjectAnswerBkupTbl ans = diagService.findPastAnswerByAbilityCode(userInfo.getTargetUserKey(), rkey,
					abilityCode, DateUtil.toDate(savedDate));
			diagService.populateDto(dto, downloadBasePath(request),
					DbUtil.getAttrText1(JKBN_DEGREE, userInfo.getTargetDegree()), ans, item);
		}
		return dto;
	}

	// 過去の診断結果表示。
	@RequestMapping("/pastIndex")
	public String pastIndex(HttpServletRequest request, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		// TODO: how to get rkey?
		String rkey = "1000000033";
		Rubric rub = rubricService.findOne(rkey);

		// 過去回答を日付でグループ化。
		Map<java.util.Date, List<NrSubjectAnswerBkupTbl>> dmap = diagService // savedDate=>ans
				.findPastAnswers(userInfo.getTargetUserKey(), rkey).stream()
				.collect(Collectors.<NrSubjectAnswerBkupTbl, java.util
						.Date> groupingBy(ans -> ans.getId().getSaveDate()));
		Map<java.util.Date, Map<String, NrSubjectAnswerBkupTbl>> amap = buildAnswerMap(dmap);

		// レンズ情報。
		int[] lensIds = { CommonConst.LENSID_BASIC, CommonConst.LENSID_CAREER, CommonConst.LENSID_FULL };
		String[] lensNames = Arrays.stream(lensIds)
				.mapToObj(lensId -> DbUtil.getJosuName(JKBN_LENSNAME, String.valueOf(lensId), locale))
				.toArray(String[]::new);
		List<String>[] codeLists = new List[lensIds.length];
		for (int ix = 0; ix < lensIds.length; ix++) {
			codeLists[ix] = rubricService.codesThroughLens(rub, lensIds[ix]);
		}

		// 達成率を調査。
		// レンズごとに、日付の新しいもの順に集計。
		List<SkillPastAnswerDto>[] pastAnswers = Stream.generate(ArrayList<SkillPastAnswerDto>::new)
				.limit(lensIds.length).toArray(List[]::new);
		calcPastAnswerStats(amap, lensIds, codeLists, pastAnswers);

		model.addAttribute("rkey", rkey);
		model.addAttribute("lensNames", lensNames);
		model.addAttribute("lensIds", lensIds);
		model.addAttribute("pastAnswers", pastAnswers);

		logger.infoCode("I0002", VIEW_PAST_PAGE);
		return VIEW_PAST_PAGE;
	}

	// 回答のリストから
	// マップ(abilityCode => 回答)を生成。
	private Map<Date, Map<String, NrSubjectAnswerBkupTbl>> buildAnswerMap(
			Map<Date, List<NrSubjectAnswerBkupTbl>> dmap) {
		Map<Date, Map<String, NrSubjectAnswerBkupTbl>> amap = new HashMap<Date, Map<String, NrSubjectAnswerBkupTbl>>();
		dmap.entrySet().forEach(e -> {
			// amap.put(e.getKey(),
			// e.getValue().stream().collect(Collectors.groupingBy(v->v.getId().getSubjectCode(),
			// Collectors.mapping(a -> a, Collectors.)));
			amap.put(e.getKey(),
					e.getValue().stream().collect(Collectors.toMap(a -> a.getId().getSubjectCode(), a -> a)));

		});

		return amap;
	}

	// 達成率の調査(全レンズ、全日付)。
	private void calcPastAnswerStats(Map<Date, Map<String, NrSubjectAnswerBkupTbl>> amap, int[] lensIds,
			List<String>[] codeLists, List<SkillPastAnswerDto>[] pastAnswers) {
		IntStream.range(0, lensIds.length).forEach(ix -> {
			calcPastAnswerStats(amap, lensIds[ix], codeLists[ix], pastAnswers[ix]);
		});
	}

	// 達成率の調査(1レンズ、全日付)。
	private void calcPastAnswerStats(Map<Date, Map<String, NrSubjectAnswerBkupTbl>> amap, int lensId,
			List<String> codeList, List<SkillPastAnswerDto> ans) {
		int numItems = codeList.size();
		if (numItems <= 0) {
			return;
		}
		// 日付で逆ソートしてからイテレート。
		Comparator<Entry<Date, Map<String, NrSubjectAnswerBkupTbl>>> comp = Comparator.comparing(Entry::getKey);
		amap.entrySet().stream().sorted(comp.reversed()).forEach(e -> {
			SkillPastAnswerDto a = new SkillPastAnswerDto();
			a.setSavedDate(e.getKey());
			a.setDenom(numItems);
			a.setNume(countNumAchievements(e.getValue(), codeList));
			ans.add(a);
		});
	}

	private int countNumAchievements(Map<String, NrSubjectAnswerBkupTbl> ansmap, List<String> codeList) {
		return (int) codeList.stream().filter(code -> {
			return ansmap.containsKey(code) && diagService.isAchieved(ansmap.get(code));
		}).count();
	}

	// エビデンスダウンロード。
	@RequestMapping("/download/{ukey}")
	public void download(HttpServletRequest request, @PathVariable("ukey") String ukey, HttpServletResponse response,
			Model model, Locale locale) throws IOException {
		logger.infoCode("I0001", request.getRequestURI());

		// uploadKeyとtargetUserKeyで検索(他人のファイルをDLできないよう)。
		// 拡張子からContent-typeを推測(デフォはoctet-stream)。
		CmFileUploadTbl file = fileService.findOne(ukey, userInfo.getTargetUserKey());
		String mimeType = URLConnection.guessContentTypeFromName(file.getFileName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		File filepath = new File(file.getRealPath());
		response.setContentLength((int) filepath.length());
		response.setContentType(mimeType);
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName());

		// Resourceをreturnする方式だと、content-typeヘッダがtext/htmlになってしまった。
		// Springがオーバールールするのかな?
		// よって、直接responseへ書き込むことにする。
		FileCopyUtils.copy(new FileInputStream(filepath), response.getOutputStream());

		logger.infoCode("I0002");
	}

	private String downloadBasePath(HttpServletRequest request) {
		return request.getContextPath() + "/skill/diag/download/";
	}
}
