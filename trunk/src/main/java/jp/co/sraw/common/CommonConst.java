/*
* ファイル名：CommonConst.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.common;

import java.util.Locale;

/**
* <B>CommonConstクラス</B>
* <P>
* Constを提供する
*/
public interface CommonConst {

    /** デフォルトロケール */
    public static final Locale DEFAULT_LOCALE = Locale.JAPANESE;

    /** デフォルトエンコーディング */
    public static final String DEFAULT_ENDODEING = "UTF-8";

    /** 内部日付フォーマット */
    public static final String DEFAULT_YYYYMMDD = "yyyy/MM/dd";

    /** 暗号キー */
    public static final String SECRET_KEY = "abyeX#wD97";  // mkpasswdで生成

    /** 全組織コード */
    public static final String ALL_ORG_CD = "0000000";

	/** パス区切り文字 */
	public static final String PATH_CHAR = "/";

	/** シーケンス(数値)から文字Keyに変換するフォーマット */
	public static final String SEQUENCE_FORMAT = "0000000000";

	/** システム管理者ユーザーキー */
	public static final String USER_KEY_ADMIN = "0000000000";
	/** 内部処理ようの仮想ユーザーキー */
	public static final String USER_KEY_DUMMY = "9999999999";

	/** API向け  */
	public static String PATH_API = "/api";
	/** 管理画面(事務局)向け  */
	public static String PATH_MGMT = "/mgmt";
	/** 管理画面(マスタメンテナンス、スーパーユーザー)向け */
	public static String PATH_ADMIN = "/admin";

	/** URLダイレクトアクセス時のリダイレクト先=index */
	public static final String REDIRECT_INDEX = "redirect:";

	/** Action URL */
	public static final String ACTION_URL = "pageActionUrl";
	/** Action URL create */
	public static final String ACTION_URL_CREATE = "create";
	/** Action URL update */
	public static final String ACTION_URL_UPDATE = "update";
	/** Action URL delete */
	public static final String ACTION_URL_DELETE = "delete";

	/** form名 */
	public static final String FORM_NAME = "form";

	/** ページモード */
	public static final String PAGE_MODE = "pageMode";
	/** ページモード 新規追加 */
	public static final String PAGE_MODE_ADD = "pageModeAdd";
	/** ページモード コピー */
	public static final String PAGE_MODE_COPY = "pageModeCopy";
	/** ページモード 編集 */
	public static final String PAGE_MODE_EDIT = "pageModeEdit";
	/** ページモード 削除 */
	public static final String PAGE_MODE_DELETE = "pageModeDelete";

	/** 成功メッセージキー */
	public static final String PAGE_SUCCESS_MESSAGE = "page_success_message";
	/** 情報メッセージキー */
	public static final String PAGE_INFO_MESSAGE = "page_info_message";
	/** 情報メッセージキー */
	public static final String PAGE_WARNING_MESSAGE = "page_warning_message";
	/** エラーメッセージキー */
	public static final String PAGE_DANGER_MESSAGE = "page_danger_message";

	/** 有効／無効フラグ 定数区分:0023 */
	public static final String USE_FALG_ACTIVE ="1";  //有効
	public static final String USE_FALG_INACTIVE ="0";  //無効

	public static final int TEXT_MAX_LENGTH =100000;

	// Excelテンプレートなどの、jarリソース内での置き場。
	public static final String RESPATH_DOC_TEMPLATE = "/doc-templates/";

	// ルーブリック関連の定数。
	public static final int MIN_PHASERANK = 1; // フェーズの最小値。
	public static final int NUM_PHASES = 5; // フェーズの数。
	public static final int NUM_TARGETS = 3; // 目標値の数。
	public static final int NUM_LENSES = 2; // レンズの数。
	public static final int LENSID_BASIC = 1; // 「研究者基礎能力診断」のビット。JOSU_CODEとしても使う。
	public static final int LENSID_CAREER = 2; // 「キャリアパス診断」のビット。JOSU_CODEとしても使う。
	public static final int LENSID_FULL = 3; // フル診断(これは厳密にはレンズではないが)。JOSU_CODEとしても使う。


	/** 操作ログDB出力 */
	/** operationFuncId 操作機能ID */
	public static final String OP_FUNC_EVENT ="operation.function.event"; //イベント情報
	public static final String OP_FUNC_SUPPORT_SUPPORT ="operation.function.support.support"; //支援制度情報
	public static final String OP_FUNC_SUPPORT_FACILITY ="operation.function.support.facility"; //機器、設備情報
	public static final String OP_FUNC_INTERNSHIP ="operation.function.internship"; //インターンシップ募集情報
	public static final String OP_FUNC_INTERNSHIP_APPLY ="operation.function.internship.apply"; //インターンシップ募集・応募
	public static final String OP_FUNC_INTERNSHIP_REFUSE ="operation.function.internship.refuse"; //インターンシップ募集・辞退
	public static final String OP_FUNC_INTERNSHIP_UPLOAD ="operation.function.internship.upload"; //インターンシップ募集・書類登録
	public static final String OP_FUNC_INTERNSHIP_DISPATCH ="operation.function.internship.dispatch"; //インターンシップ募集・派遣情報
	public static final String OP_FUNC_INTERNSHIP_RESULTS ="operation.function.internship.results"; //インターンシップ募集・合否結果
	public static final String OP_FUNC_MESSAGEBOX ="operation.function.messagebox"; //メッセージ
	public static final String OP_FUNC_USER_BASE ="operation.function.user.base"; //ユーザ基本情報
	public static final String OP_FUNC_USER_CAREER_PAPER ="operation.function.user.career.paper"; //業績情報・論文
	public static final String OP_FUNC_USER_CAREER_CONFERENCE ="operation.function.user.career.conference"; //業績情報・講演・口頭発表等
	public static final String OP_FUNC_USER_CAREER_BIBLIO ="operation.function.user.career.biblio"; //業績情報・書籍
	public static final String OP_FUNC_USER_CAREER_RESEARCH_KEYWORD ="operation.function.user.career.research.keyword"; //業績情報・研究キーワード
	public static final String OP_FUNC_USER_CAREER_RESEARCH_AREA ="operation.function.user.career.research.area"; //業績情報・研究分野
	public static final String OP_FUNC_USER_CAREER_SOCIETY ="operation.function.user.career.society"; //業績情報・所属学協会
	public static final String OP_FUNC_USER_CAREER_WORKS ="operation.function.user.career.works"; //業績情報・Works
	public static final String OP_FUNC_USER_CAREER_PATENT ="operation.function.user.career.patent"; //業績情報・特許
	public static final String OP_FUNC_USER_CAREER_ACADEMIC ="operation.function.user.career.academic"; //業績情報・学歴
	public static final String OP_FUNC_USER_CAREER_CARRER ="operation.function.user.career.carrer"; //業績情報・経歴
	public static final String OP_FUNC_USER_CAREER_PRIZE ="operation.function.user.career.prize"; //業績情報・受賞
	public static final String OP_FUNC_USER_CAREER_OTHERS ="operation.function.user.career.others"; //業績情報・その他
	public static final String OP_FUNC_USER_CAREER_DEGREE ="operation.function.user.career.degree"; //業績情報・学位
	public static final String OP_FUNC_USER_CAREER_COMPETITION ="operation.function.user.career.competition"; //業績情報・競争的資金等の研究課題
	public static final String OP_FUNC_USER_PRMOVIE ="operation.function.user.prmovie"; //ＰＲ動画
	public static final String OP_FUNC_USER_OTHER_DOKYUMENT ="operation.function.user.other.dokyument"; //その他成果物画
	public static final String OP_FUNC_CARRER ="operation.function.carrer"; //キャリア相談
	public static final String OP_FUNC_CARRER_SETTING ="operation.function.carrer.setting"; //キャリア相談・面談設定
	public static final String OP_FUNC_CARRER_RESULTS ="operation.function.carrer.results"; //キャリア相談・面談実績
	/** システム管理 */
	public static final String OP_FUNC_ADMIN_MSCODE = "operation.function.admin.mscode"; //operation.function.admin.mscode=定数コードマスタ

	/** operationActionId 操作ID */
	public static final String OP_ACTION_INSERT ="operation.action.insert"; //追加しました。
	public static final String OP_ACTION_UPDATE ="operation.action.update"; //変更しました。
	public static final String OP_ACTION_DELETE ="operation.action.delete"; //削除しました。
	public static final String OP_ACTION_UPLOAD ="operation.action.upload"; //アップロードしました。
	public static final String OP_ACTION_UPDATE_ALL ="operation.action.update.all"; //一括変更しました。
	public static final String OP_ACTION_INSERT_ALL ="operation.action.insert.all"; //一括追加しました。

	/** emailTitle ｅメール送信時のタイトル */
	public static final String EMAIL_TITLE_SUPPORT ="email.title.support"; //支援制度情報が登録されました。
	public static final String EMAIL_TITLE_EVENT ="email.title.event"; //イベント情報が登録されました。
	public static final String EMAIL_TITLE_INTERNSHIP ="email.title.internship"; //インターンシップ情報が登録されました。

}
