/*
* ファイル名：RubricMgmtController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/05   etoh        新規作成
*/
package jp.co.sraw.controller.skill;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonController;
import jp.co.sraw.entity.NrRubricTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.oxm.rubric.Rubric;
import jp.co.sraw.oxm.rubric.RubricPhase;
import jp.co.sraw.service.RubricServiceImpl;
import jp.co.sraw.util.PoiBook;

/**
 * <B>能力診断管理機能用コントローラ</B>
 * <P>
 * ルーブリックのCRUD。
 */
@Controller
@RequestMapping(CommonConst.PATH_MGMT + "/skill/diag")
public class RubricMgmtController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(RubricMgmtController.class);

	// 遷移先。
	private static final String REDIRECT_LIST = "redirect:list";
	private static final String LIST_PAGE = "skill/diag/mgmt/list";
	private static final String EDIT_PAGE = "skill/diag/mgmt/edit";

	// export.
	private static final String XLS_TEMPLATE_PATH = CommonConst.RESPATH_DOC_TEMPLATE + "rubric.xls";
	private static final String DEFAULT_XLS_NAME = "rubric.xls";

	@Autowired
	private RubricServiceImpl rubricService;

	@Override
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	// ルーブリック一覧表示。
	@RequestMapping({ "", "/", "/list" })
	public String index(HttpServletRequest request, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		List<NrRubricTbl> list = rubricService.findAll();
		model.addAttribute("rubrics", list);

		logger.infoCode("I0002", LIST_PAGE);
		return LIST_PAGE;
	}

	// 編集画面表示。
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, @ModelAttribute(CommonConst.FORM_NAME) RubricSelForm selForm,
			Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		RubricEditForm editForm = new RubricEditForm(selForm);
		if (selForm.getPageMode().equals(CommonConst.PAGE_MODE_EDIT)) {
			rubricService.populateFormForEdit(editForm, selForm.getKeyToEdit());
		}

		prepareModelForEdit(model, editForm);

		logger.infoCode("I0002", EDIT_PAGE);
		return EDIT_PAGE;
	}

	/**
	 * 編集画面用の情報をModelへセット。
	 *
	 * @param model
	 * @param form
	 */
	private void prepareModelForEdit(Model model, RubricEditForm form) {
		model.addAttribute(CommonConst.FORM_NAME, form);

		// phaseRanksは、[1, 2, 3, 4, 5]みたいな固定配列。
		// phaseListの要素数が可変なので、マスター的に使う。
		model.addAttribute("phaseRanks", RubricPhase.getPhaseRanks());
	}

	// 保存して一覧へ遷移。
	@RequestMapping(value = { "/create", "/update" }, method = RequestMethod.POST)
	public String save(HttpServletRequest request,
			@Validated @ModelAttribute(CommonConst.FORM_NAME) RubricEditForm form, BindingResult bindingResult,
			Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		form.normalize(); // POSTデータとRubricEditFormの構造が少し違うので調整。

		if (bindingResult.hasErrors()) { // バリデーションエラーありか?
			if (logger.isDebugEnabled()) {
				logger.debug(form.toString());
			}

			prepareModelForEdit(model, form);

			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data");

			logger.infoCode("I0002", EDIT_PAGE);
			return EDIT_PAGE;
		}

		// DB登録。
		if (!rubricService.update(form, userInfo)) {
			logger.errorCode("E1007");
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist");

			prepareModelForEdit(model, form);

			logger.errorCode("E0014", EDIT_PAGE);
			return EDIT_PAGE;
		}

		logger.infoCode("I0002", REDIRECT_LIST);
		return REDIRECT_LIST;
	}

	// 削除。
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, @ModelAttribute(CommonConst.FORM_NAME) RubricSelForm form,
			Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		String[] rkeys = form.getKeyToDelete();
		if (rkeys != null) {
			Arrays.stream(rkeys).forEach(rubricService::delete);
		}

		logger.infoCode("I0002", REDIRECT_LIST);
		return REDIRECT_LIST;
	}

	// アップロードして、編集画面を表示。
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, RubricUploadForm uploadForm, Model model, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		MultipartFile doc = uploadForm.getDoc();
		RubricEditForm editForm = new RubricEditForm(uploadForm);

		// アップロードファイルがあれば、XMLをパースしてRubricEditFormへセットし、編集画面へ遷移。
		// パースに失敗したら、DBから読み直して編集画面へ遷移。
		// アップロードファイルが無ければ、DBから読み直して編集画面へ遷移。
		if (doc.getOriginalFilename().length() > 0) {
			logger.info(doc.getOriginalFilename() + ", " + doc.getSize() + "bytes");
			try {
				String xmlStr = new String(doc.getBytes(), StandardCharsets.UTF_8);
				rubricService.populateFormForEdit(editForm, uploadForm.getKey(), uploadForm.getUpdDate(), xmlStr);
			} catch (IOException e) {
				model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.system.message.contact");

				rubricService.populateFormForEdit(editForm, uploadForm.getKey());
				prepareModelForEdit(model, editForm);

				logger.errorCode("E0014", EDIT_PAGE);
				return EDIT_PAGE;
			}
		} else {
			rubricService.populateFormForEdit(editForm, uploadForm.getKey());
		}

		prepareModelForEdit(model, editForm);

		logger.infoCode("I0002", EDIT_PAGE);
		return EDIT_PAGE;
	}

	// ダウンロード。
	// Viewは更新しないので、編集途中の情報は失われない。
	@RequestMapping(value = "/download/{key}", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
	@ResponseBody
	public Resource download(HttpServletRequest request, @PathVariable("key") String key, HttpServletResponse response,
			Model model, Locale locale) throws IOException {
		logger.infoCode("I0001", request.getRequestURI());

		ByteArrayOutputStream baos = prepareExcel(key);
		if (baos == null) { // 失敗?
			// 500エラーとする。
			logger.errorCode("E0014", "failed to prepare excel file");
			throw new RuntimeException();
		}

		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + DEFAULT_XLS_NAME);

		logger.infoCode("I0002");
		return new ByteArrayResource(baos.toByteArray());
	}

	/**
	 * ルーブリックをExcelへエクスポートして、OutputStreamとして返す。
	 *
	 * @param rkey
	 *            ルーブリックキー
	 * @return
	 */
	private ByteArrayOutputStream prepareExcel(String rkey) {
		Rubric rub = rubricService.findOne(rkey);

		try (PoiBook book = PoiBook.fromResource(XLS_TEMPLATE_PATH)) {
			RubricExcelExporter ree = new RubricExcelExporter(rub, book);
			ree.export();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			book.write(baos);
			return baos;
		} catch (IOException e) {
			logger.error("IOException while exporting a rublic: " + rkey, e);
			return null;
		} catch (Exception e) {
			logger.error("unexpected error while exporting a rublic: " + rkey, e);
			return null;
		}
	}
}
