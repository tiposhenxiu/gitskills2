/*
* ファイル名：SupportController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.controller.portfolio.form.PortfolioProfileForm;
import jp.co.sraw.controller.portfolio.service.AcademicServiceImpl;
import jp.co.sraw.controller.portfolio.service.BiblioServiceImpl;
import jp.co.sraw.controller.portfolio.service.CareerServiceImpl;
import jp.co.sraw.controller.portfolio.service.CompetitionServiceImpl;
import jp.co.sraw.controller.portfolio.service.ConferenceServiceImpl;
import jp.co.sraw.controller.portfolio.service.DegreeServiceImpl;
import jp.co.sraw.controller.portfolio.service.OthersServiceImpl;
import jp.co.sraw.controller.portfolio.service.PaperServiceImpl;
import jp.co.sraw.controller.portfolio.service.PatentServiceImpl;
import jp.co.sraw.controller.portfolio.service.PrizeServiceImpl;
import jp.co.sraw.controller.portfolio.service.ResearchAreaServiceImpl;
import jp.co.sraw.controller.portfolio.service.ResearchKeywordServiceImpl;
import jp.co.sraw.controller.portfolio.service.SocietyServiceImpl;
import jp.co.sraw.controller.portfolio.service.WorksServiceImpl;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.MsPartyTbl;
import jp.co.sraw.entity.UsCompetitionTbl;
import jp.co.sraw.entity.UsResultUploadTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.MsPartyServiceImpl;
import jp.co.sraw.service.PortfolioProfileServiceImpl;
import jp.co.sraw.service.ResultUploadImpl;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>SupportControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/portfolio/profile")
public class PortfolioProfileController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(PortfolioProfileController.class);

	@Autowired
	private PortfolioProfileServiceImpl portfolioProfileServiceImpl;

	@Autowired
	private PaperServiceImpl paperServiceImpl;

	@Autowired
	private ConferenceServiceImpl conferenceServiceImpl;

	@Autowired
	private BiblioServiceImpl biblioServiceImpl;

	@Autowired
	private ResearchKeywordServiceImpl researchKeywordServiceImpl;

	@Autowired
	private ResearchAreaServiceImpl researchAreaServiceImpl;

	@Autowired
	private SocietyServiceImpl societyServiceImpl;

	@Autowired
	private WorksServiceImpl worksServiceImpl;

	@Autowired
	private PatentServiceImpl patentServiceImpl;

	@Autowired
	private AcademicServiceImpl academicServiceImpl;

	@Autowired
	private CareerServiceImpl careerServiceImpl;

	@Autowired
	private PrizeServiceImpl prizeServiceImpl;

	@Autowired
	private OthersServiceImpl othersServiceImpl;

	@Autowired
	private DegreeServiceImpl degreeServiceImpl;

	@Autowired
	private CompetitionServiceImpl competitionServiceImpl;

	@Autowired
	private ResultUploadImpl resultUploadImpl;

	@Autowired
	private MsPartyServiceImpl msPartyServiceImpl;

	private static final String LIST_SHOWDOCTOR_PAGE = "portfolio/profile/showDoctor";

	private static final String LIST_SHOWTEACHER_PAGE = "portfolio/profile/showTeacher";

	private static final String LIST_SHOWCONSUL_PAGE = "portfolio/profile/showConsul";

	private static final String LIST_SHOWMGMT_PAGE = "portfolio/profile/showMgmt";

	private static final String LIST_SHOWPARTY_PAGE = "portfolio/profile/showParty";

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
	@RequestMapping({ "", "/", "/showDoctor" })
	public String list(@ModelAttribute(CommonConst.FORM_NAME) PortfolioProfileForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		this.setListToModel(model, "0042", locale);

		String userKey = null;
		form.setUserKey("0000000017");
		userKey = form.getUserKey();
		if (userInfo.getLoginUserKey().equals(userKey)) {
			form.setButtonFlag("1");
		} else {
			form.setButtonFlag("0");
		}
		;

		// //公開フラグ
		// String[] publicFlag = new String[3];
		String[] publicFlag = null;
		if (StringUtil.isNull(form.getPublicFlag())) {
			form.setPublicFlag("2");
		}

		// 全公開
		if (form.getPublicFlag().equals("2")) {
			publicFlag = new String[] { "2" };
		}
		// 内部公開
		if (form.getPublicFlag().equals("1")) {
			publicFlag = new String[] { "2", "1" };
		}

		// 全て表示
		if (form.getPublicFlag().equals("0")) {
			publicFlag = new String[] { "2", "1", "0" };
		}

		List<MsPartyTbl> listParty = msPartyServiceImpl.findAllByPartyKbn(publicFlag);
		model.addAttribute("listParty", listParty);

		// ユーザ情報テーブル
		List<UsUserTbl> usUserTblList = new ArrayList<>();
		usUserTblList = portfolioProfileServiceImpl.findAllUser();

		UsUserTbl usUserTbl = new UsUserTbl();
		usUserTbl = portfolioProfileServiceImpl.findOneUser(userKey);

		// 業績情報・論文
		model.addAttribute("gyPaperTblList", paperServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・講演・口頭発表等
		model.addAttribute("gyConferenceTblList", conferenceServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・書籍
		model.addAttribute("gyBiblioTblList", biblioServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・研究キーワード
		model.addAttribute("gyResearchKeywordTblList",
				researchKeywordServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・研究分野
		model.addAttribute("gyResearchAreaTblList", researchAreaServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・所属学協会
		model.addAttribute("gySocietyTblList", societyServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・Works
		model.addAttribute("gyWorksTblList", worksServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・特許
		model.addAttribute("gyPatentTblList", patentServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・学歴
		model.addAttribute("gyAcademicTblList", academicServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・経歴
		model.addAttribute("gyCareerTblList", careerServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・受賞
		model.addAttribute("gyPrizeTblList", prizeServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・その他
		model.addAttribute("gyOthersTblList", othersServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・学位
		model.addAttribute("gyDegreeTblList", degreeServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・競争的資金等の研究課題
		model.addAttribute("GyCompetitionTblList", competitionServiceImpl.findAllProfileView(userInfo, publicFlag));

		 //コンペティションファイルVIEW
		 List<UsCompetitionTbl> usCompetitionTblList = new ArrayList<>();
		 usCompetitionTblList = portfolioProfileServiceImpl.findAllUsCompetition();
		// 成果物ファイル
		List<UsResultUploadTbl> usResultUploadTblList = new ArrayList<>();
		usResultUploadTblList = portfolioProfileServiceImpl.findAllUsResultUpload();

		model.addAttribute("usUserTblList", usUserTblList);
		model.addAttribute("usUserTbl", usUserTbl);

		model.addAttribute("usCompetitionTblList", usCompetitionTblList);
		model.addAttribute("usResultUploadTblList", usResultUploadTblList);

		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_SHOWDOCTOR_PAGE;
	}

	/**
	 * 一覧
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/showTeacher" })
	public String list2(@ModelAttribute(CommonConst.FORM_NAME) PortfolioProfileForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		String userKey = null;
		form.setUserKey("0000000017");
		userKey = form.getUserKey();
		if (userInfo.getLoginUserKey().equals(userKey)) {
			form.setButtonFlag("1");
		} else {
			form.setButtonFlag("0");
		}
		;

		// //公開フラグ
		String[] publicFlag = new String[3];
		// 全公開
		publicFlag[0] = "2";
		// 内部公開
		publicFlag[1] = "1";
		// 全て表示
		publicFlag[2] = "0";
		List<MsPartyTbl> listParty = msPartyServiceImpl.findAllByPartyKbn(publicFlag);
		model.addAttribute("listParty", listParty);

		this.setListToModel(model, "0000", locale);
		// 公開設定
		this.setListToModel(model, "0024", locale);
		// ＰＲ動画登録区分
		this.setListToModel(model, "0034", locale);
		// 査読の有無
		this.setListToModel(model, "0204", locale);
		// 記述言語
		this.setListToModel(model, "0206", locale);
		// 掲載種別
		this.setListToModel(model, "0203", locale);
		// 会議区分
		this.setListToModel(model, "0209", locale);
		// 会議種別
		this.setListToModel(model, "0210", locale);
		// 招待の有無
		this.setListToModel(model, "0208", locale);
		// 作品分類
		this.setListToModel(model, "0213", locale);
		// 受賞区分
		this.setListToModel(model, "0201", locale);
		// 担当区分
		this.setListToModel(model, "0211", locale);
		// 学年／職位
		this.setListToModel(model, "0010", locale);
		// 性別
		this.setListToModel(model, "0025", locale);

		// ユーザ情報テーブル
		List<UsUserTbl> usUserTblList = new ArrayList<>();
		usUserTblList = portfolioProfileServiceImpl.findAllUser();

		UsUserTbl usUserTbl = new UsUserTbl();
		usUserTbl = portfolioProfileServiceImpl.findOneUser(userKey);

		// 業績情報・論文
		model.addAttribute("gyPaperTblList", paperServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・講演・口頭発表等
		model.addAttribute("gyConferenceTblList", conferenceServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・書籍
		model.addAttribute("gyBiblioTblList", biblioServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・研究キーワード
		model.addAttribute("gyResearchKeywordTblList",
				researchKeywordServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・研究分野
		model.addAttribute("gyResearchAreaTblList", researchAreaServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・所属学協会
		model.addAttribute("gySocietyTblList", societyServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・Works
		model.addAttribute("gyWorksTblList", worksServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・特許
		model.addAttribute("gyPatentTblList", patentServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・学歴
		model.addAttribute("gyAcademicTblList", academicServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・経歴
		model.addAttribute("gyCareerTblList", careerServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・受賞
		model.addAttribute("gyPrizeTblList", prizeServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・その他
		model.addAttribute("gyOthersTblList", othersServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・学位
		model.addAttribute("gyDegreeTblList", degreeServiceImpl.findAllProfileView(userInfo, publicFlag));
		// 業績情報・競争的資金等の研究課題
		model.addAttribute("GyCompetitionTblList", competitionServiceImpl.findAllProfileView(userInfo, publicFlag));

		// //コンペティションファイルVIEW
		// List<> userTblList = new ArrayList<>();
		// userTblList = portfolioProfileServiceImpl.findAll(userInfo, form);
		// 成果物ファイル
		List<UsResultUploadTbl> usResultUploadTblList = new ArrayList<>();
		usResultUploadTblList = portfolioProfileServiceImpl.findAllUsResultUpload();

		model.addAttribute("usUserTblList", usUserTblList);
		model.addAttribute("usUserTbl", usUserTbl);

		// model.addAttribute("", );
		model.addAttribute("usResultUploadTblList", usResultUploadTblList);

		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_SHOWTEACHER_PAGE;
	}

	/**
	 * 一覧
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/showConsul" })
	public String list3(@ModelAttribute(CommonConst.FORM_NAME) PortfolioProfileForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		String userKey = null;
		form.setUserKey("0000000017");
		userKey = form.getUserKey();
		if (userInfo.getLoginUserKey().equals(userKey)) {
			form.setButtonFlag("1");
		} else {
			form.setButtonFlag("0");
		}
		;

		// //公開フラグ
		String[] publicFlag = new String[3];
		// 全公開
		publicFlag[0] = "2";
		// 内部公開
		publicFlag[1] = "1";
		// 全て表示
		publicFlag[2] = "0";
		List<MsPartyTbl> listParty = msPartyServiceImpl.findAllByPartyKbn(publicFlag);
		model.addAttribute("listParty", listParty);

		// 公開設定
		this.setListToModel(model, "0024", locale);

		// ユーザ情報テーブル
		List<UsUserTbl> usUserTblList = new ArrayList<>();
		usUserTblList = portfolioProfileServiceImpl.findAllUser();

		UsUserTbl usUserTbl = new UsUserTbl();
		usUserTbl = portfolioProfileServiceImpl.findOneUser(userKey);

		model.addAttribute("usUserTblList", usUserTblList);

		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_SHOWCONSUL_PAGE;
	}

	/**
	 * 一覧
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/showMgmt" })
	public String list4(@ModelAttribute(CommonConst.FORM_NAME) PortfolioProfileForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		String userKey = null;
		form.setUserKey("0000000017");
		userKey = form.getUserKey();
		if (userInfo.getLoginUserKey().equals(userKey)) {
			form.setButtonFlag("1");
		} else {
			form.setButtonFlag("0");
		}
		;

		// //公開フラグ
		String[] publicFlag = new String[3];
		// 全公開
		publicFlag[0] = "2";
		// 内部公開
		publicFlag[1] = "1";
		// 全て表示
		publicFlag[2] = "0";
		List<MsPartyTbl> listParty = msPartyServiceImpl.findAllByPartyKbn(publicFlag);
		model.addAttribute("listParty", listParty);

		// 公開設定
		this.setListToModel(model, "0024", locale);

		// ユーザ情報テーブル
		List<UsUserTbl> usUserTblList = new ArrayList<>();
		usUserTblList = portfolioProfileServiceImpl.findAllUser();

		UsUserTbl usUserTbl = new UsUserTbl();
		usUserTbl = portfolioProfileServiceImpl.findOneUser(userKey);

		model.addAttribute("usUserTblList", usUserTblList);

		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_SHOWMGMT_PAGE;
	}

	/**
	 * 一覧
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/showParty" })
	public String list5(@ModelAttribute(CommonConst.FORM_NAME) PortfolioProfileForm form, Model model, Locale locale) {

		logger.infoCode("I0001", "list"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		String userKey = null;
		form.setUserKey("0000000017");
		userKey = form.getUserKey();
		if (userInfo.getLoginUserKey().equals(userKey)) {
			form.setButtonFlag("1");
		} else {
			form.setButtonFlag("0");
		}
		;

		// //公開フラグ
		String[] publicFlag = new String[3];
		// 全公開
		publicFlag[0] = "2";
		// 内部公開
		publicFlag[1] = "1";
		// 全て表示
		publicFlag[2] = "0";
		List<MsPartyTbl> listParty = msPartyServiceImpl.findAllByPartyKbn(publicFlag);
		model.addAttribute("listParty", listParty);

		// 公開設定
		this.setListToModel(model, "0024", locale);
		// ＰＲ動画登録区分
		this.setListToModel(model, "0034", locale);

		// ユーザ情報テーブル
		List<UsUserTbl> usUserTblList = new ArrayList<>();
		usUserTblList = portfolioProfileServiceImpl.findAllUser();

		UsUserTbl usUserTbl = new UsUserTbl();
		usUserTbl = portfolioProfileServiceImpl.findOneUser(userKey);
		// 業績情報・論文
		model.addAttribute("gyPaperTblList", paperServiceImpl.findAllProfileView(userInfo, publicFlag));

		model.addAttribute("usUserTblList", usUserTblList);
		model.addAttribute(CommonConst.FORM_NAME, form);

		// dump
		modelDump(logger, model, "list");

		logger.infoCode("I0002", "list"); // I0002=メソッド終了:{0}

		return LIST_SHOWPARTY_PAGE;
	}

	protected void setListToModel(Model model, String listName, Locale locale) {
		List<MsCodeDto> list = DbUtil.getJosuList(listName, locale);
		model.addAttribute("list" + listName, list);
	}

}
