/*
* ファイル名：PortfolioEngine.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.controller.portfolio.excel.PortfolioExcelHelper;
import jp.co.sraw.controller.portfolio.form.PortfolioForm;
import jp.co.sraw.controller.portfolio.service.PortfolioServiceImpl;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.PoiBook;

/**
 * <B>PortfolioEngineクラス</B>
 * <P>
 */
@Service
public class PortfolioEngine<C extends PortfolioController, F extends PortfolioForm, S extends PortfolioServiceImpl, H extends PortfolioExcelHelper> {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(PortfolioEngine.class);

	protected static final String FORM_NAME = "form";
	//
	private static final String XLS_TEMPLATE_PATH = CommonConst.RESPATH_DOC_TEMPLATE + "results.xls";
	//
	private static final String DEFAULT_XLS_NAME = "results.xls";
	// 公開フラグ区分(業績向け)
	protected static final String CODE_PUBLICCODE = "0024";
	// 言語(業績向け)
	protected static final String CODE_LANGUEGE = "0041";

	protected C controller;

	protected S serviceImpl;

	protected UserInfo userInfo = null;

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected Validator validator;

	@PostConstruct
	public void init() {
		logger.setMessageSource(messageSource);
	}

	public void start(C controller, S serviceImpl) {
		this.controller = controller;
		this.serviceImpl = serviceImpl;
		this.userInfo = controller.userInfo();
	}

	/**
	 * 定数コードList
	 *
	 * @param model
	 * @param listName
	 * @param locale
	 */
	protected void setListToModel(Model model, String listName, Locale locale) {
		List<MsCodeDto> list = DbUtil.getJosuList(listName, locale);
		model.addAttribute("list" + listName, list);
	}

	/**
	 * 定数コードMap
	 *
	 * @param model
	 * @param listName
	 * @param locale
	 */
	protected void setMapToModel(Model model, String mapName, Locale locale) {
		Map<String, MsCodeDto> map = DbUtil.getJosuMap(mapName, locale);
		model.addAttribute("Map" + mapName, map);
	}

	/**
	 *
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	public String list(C controller, S serviceImpl, F form, Model model, Locale locale) {

		start(controller, serviceImpl);
		logger.infoCode("I0001");

		this.setListToModel(model, CODE_PUBLICCODE, locale);
		this.setMapToModel(model, CODE_PUBLICCODE, locale);
		form.setPublicFlag("2");

		List<T> dataList = new ArrayList<>();
		dataList = serviceImpl.findAllDto(userInfo, form);
		model.addAttribute("list", dataList);

		model.addAttribute(CommonConst.FORM_NAME, form);
		logger.infoCode("I0002", controller.EDIT_PAGE); // I0002=メソッド終了:{0}
		return controller.LIST_PAGE;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	public String edit(C controller, S serviceImpl, F form, Model model, Locale locale) {

		start(controller, serviceImpl);

		logger.infoCode("I0001");

		this.setListToModel(model, CODE_PUBLICCODE, locale);

		this.setListToModel(model, CODE_LANGUEGE, locale);

		form = (F) serviceImpl.findOne(controller.userInfo(), form);

		if (form == null) {
			// DB更新が失敗した場合
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			logger.errorCode("E0014", form.getPageActionUrl()); //
			// E0014=メソッド異常終了:{0}

			return controller.REDIRECT_LIST;
		}

		List<MsCodeDto> publicFlagList = DbUtil.getJosuList(CODE_PUBLICCODE, locale);
		model.addAttribute("publicFlagList", publicFlagList);

		form.setPageMode(CommonConst.PAGE_MODE_EDIT);
		model.addAttribute(CommonConst.FORM_NAME, form);
		logger.infoCode("I0002", controller.EDIT_PAGE); // I0002=メソッド終了:{0}
		return controller.EDIT_PAGE;
	}

	/**
	 *
	 * @param supportKey
	 * @param form
	 * @param model
	 * @return
	 */
	public String copy(C controller, S serviceImpl, F form, Model model, Locale locale) {

		start(controller, serviceImpl);

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<MsCodeDto> publicFlagList = DbUtil.getJosuList(CODE_PUBLICCODE, locale);
		model.addAttribute("publicFlagList", publicFlagList);

		form = (F) serviceImpl.getOne(form.getGyosekiKey());

		if (form != null) {
			model.addAttribute(CommonConst.FORM_NAME, form);
			logger.infoCode("I0002", controller.EDIT_PAGE); //
			// I0002=メソッド終了:{0}
			return controller.EDIT_PAGE;
		}

		// DB更新が失敗した場合
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。

		logger.errorCode("E0014", form.getPageActionUrl()); //
		// E0014=メソッド異常終了:{0}

		model.addAttribute(CommonConst.FORM_NAME, form);
		return controller.REDIRECT_LIST;
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	public String create(C controller, S serviceImpl, F form, Model model, Locale locale) {
		start(controller, serviceImpl);
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<MsCodeDto> publicFlagList = DbUtil.getJosuList(CODE_PUBLICCODE, locale);
		model.addAttribute("publicFlagList", publicFlagList);

		this.setListToModel(model, CODE_PUBLICCODE, locale);

		this.setListToModel(model, CODE_LANGUEGE, locale);

		form.setPageMode(CommonConst.PAGE_MODE_ADD);
		model.addAttribute(CommonConst.FORM_NAME, form);

		logger.infoCode("I0002", controller.EDIT_PAGE); // I0002=メソッド終了:{0}
		return controller.EDIT_PAGE;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	public String update(C controller, S serviceImpl, F form, BindingResult bindingResult, Model model,
			RedirectAttributes attributes, Locale locale) {
		start(controller, serviceImpl);
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<MsCodeDto> publicFlagList = DbUtil.getJosuList(CODE_PUBLICCODE, locale);
		model.addAttribute("publicFlagList", publicFlagList);

		this.setListToModel(model, "0024", locale);

		///////////////////////////////////////////////////////////////////////////////////
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			// if (logger.isDebugEnabled()) {
			logger.debugCode("W1010", bindingResult.getFieldError()); //
			// W1010=Validationチェックエラーがありました。
			// }
			model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

			model.addAttribute(CommonConst.FORM_NAME, form);

			logger.infoCode("I0002", form.getPageActionUrl()); //
			// I0002=メソッド終了:{0}

			return controller.EDIT_PAGE;
		}

		if (serviceImpl.update(controller.userInfo(), form)) {
			// DB更新が成功した場合
			logger.infoCode("I1004", ""); // I1004=更新しました。{0}

			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.update.success"); // message.data.update.success=データを更新しました。

			logger.infoCode("I0002", form.getPageActionUrl()); //
			// I0002=メソッド終了:{0}
			return controller.REDIRECT_LIST;
		}

		logger.infoCode("I0002", controller.LIST_PAGE); // I0002=メソッド終了:{0}

		// DB更新が失敗した場合
		model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.regist"); // error.data.message.db.regist=登録が失敗しました。
		logger.errorCode("E0014", form.getPageActionUrl()); //
		// E0014=メソッド異常終了:{0}
		return controller.REDIRECT_LIST;
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String delete(C controller, S serviceImpl, F form, Model model, RedirectAttributes attributes,
			Locale locale) {
		start(controller, serviceImpl);
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		String key = "key=" + form.getGyosekiKey();

		if (serviceImpl.delete(controller.userInfo(), form)) {
			// DB更新が成功した場合
			logger.infoCode("I1003", key); // I1003=削除しました。{0}

			attributes.addFlashAttribute(CommonConst.PAGE_SUCCESS_MESSAGE, "message.data.delete.success"); // message.data.delete.success=データを削除しました。

			attributes.addFlashAttribute(CommonConst.FORM_NAME, form);
			logger.infoCode("I0002", form.getPageActionUrl()); //
			// I0002=メソッド終了:{0}
			return this.list(controller, serviceImpl, form, model, locale);
		} else {

			attributes.addFlashAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.db.remove"); // error.data.message.db.remove=削除が失敗しました。

			model.addAttribute(CommonConst.FORM_NAME, form);
			logger.errorCode("E0014", form.getPageActionUrl()); //
			// E0014=メソッド異常終了:{0}
			return this.list(controller, serviceImpl, form, model, locale);
		}
	}

	public String updateAll(C controller, S serviceImpl, F form, Model model, RedirectAttributes attributes,
			Locale locale) {
		start(controller, serviceImpl);
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}
		serviceImpl.updateAll(userInfo, form);
		logger.infoCode("I0002"); //
		return this.list(controller, serviceImpl, form, model, locale);
	}

	public Resource exportExcel(C controller, S serviceImpl, F form, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes attributes, Locale locale) {
		logger.infoCode("I0001", request.getRequestURI());

		ByteArrayOutputStream baos = prepareExcel(controller, userInfo, form);
		if (baos == null) { // 失敗?
			// 500エラーとする。
			logger.errorCode("E0014", "failed to prepare excel file");
			throw new RuntimeException();
		}

		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + DEFAULT_XLS_NAME);

		logger.infoCode("I0002");
		return new ByteArrayResource(baos.toByteArray());
	}

	public String importExcel(C controller, S serviceImpl, F form, MultipartHttpServletRequest request, Model model,
			BindingResult result, Locale locale) {

		Iterator<String> itrator = request.getFileNames();
		MultipartFile mlf = request.getFile(itrator.next());
		String fileName = mlf.getOriginalFilename();

		ByteArrayInputStream bis = null;
		Workbook workbook = null;
		try {
			bis = new ByteArrayInputStream(mlf.getBytes());
			if (bis != null) {

				workbook = new HSSFWorkbook(bis);

				H helper = (H) controller.getExcelHelper();

				List<F> list = helper.getFormList(workbook);

				for (F f : list) {

					validator.validate(f, result);

					if (result.hasErrors()) {
						if (logger.isDebugEnabled()) {
							logger.debugCode("W1010", result.getFieldError()); //
							// W1010=Validationチェックエラーがありました。
						}
						model.addAttribute(CommonConst.PAGE_DANGER_MESSAGE, "error.data.message.valid.data"); // error.data.message.valid.data=入力項目に誤りがあります。

						model.addAttribute(CommonConst.FORM_NAME, form);

						logger.infoCode("I0002", form.getPageActionUrl()); //
						// I0002=メソッド終了:{0}
						return this.list(controller, serviceImpl, form, model, locale);
					}
				}

				serviceImpl.importData(userInfo, list);

				logger.infoCode("I0002");
				return controller.LIST_PAGE;
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return controller.LIST_PAGE;
	}

	private ByteArrayOutputStream prepareExcel(C controller, UserInfo userInfo, F form) {

		List<F> list = serviceImpl.findAllDto(userInfo, form);

		try (PoiBook book = PoiBook.fromResource(XLS_TEMPLATE_PATH)) {
			H helper = (H) controller.getExcelHelper();
			helper.buildSelectItemList(book);
			helper.buildExcelDocument(book, list);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			book.write(baos);
			return baos;
		} catch (IOException e) {
			logger.error("IOException exporting a execlfile ");
			return null;
		} catch (Exception e) {
			logger.error("unexpected error while exporting a excelfile ");
			return null;
		}
	}

}
