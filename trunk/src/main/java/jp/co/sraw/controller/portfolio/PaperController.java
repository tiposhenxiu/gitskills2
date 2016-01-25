/*
* ファイル名：paperController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/24     新規作成
*/
package jp.co.sraw.controller.portfolio;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.controller.portfolio.excel.OthersExcelHelper;
import jp.co.sraw.controller.portfolio.excel.PaperExcelHelper;
import jp.co.sraw.controller.portfolio.excel.PortfolioExcelHelper;
import jp.co.sraw.controller.portfolio.form.GyPaperForm;
import jp.co.sraw.controller.portfolio.service.PaperServiceImpl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;

/**
 * <B>PaperControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@SuppressWarnings({ "rawtypes", "hiding" })
@Controller
@RequestMapping("/portfolio/paper")
public class PaperController extends PortfolioController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(PaperController.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
		LIST_PAGE = "portfolio/paper/list";
		EDIT_PAGE = "portfolio/paper/edit";
		REDIRECT_LIST = "redirect:/portfolio/paper/";
		OP_FUNCID = CommonConst.OP_FUNC_USER_CAREER_PAPER;
	}

	//
	@SuppressWarnings("unused")
	private static final String CODE_PUBLICCODE = "0024";
	protected static final String FORM_NAME = "form";

	@Autowired
	private PaperServiceImpl serviceImpl;

	@Autowired
	private PaperExcelHelper execlHelper;

	@Autowired
	private PortfolioEngine<PaperController, GyPaperForm, PaperServiceImpl, PaperExcelHelper> engine;

	/**
	 *
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String list(@ModelAttribute(FORM_NAME) GyPaperForm form, Model model, Locale locale) {
		return engine.list(this, serviceImpl, form, model, locale);
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute(FORM_NAME) GyPaperForm form, Model model, Locale locale) {
		this.setListToModel(model, "0024", locale);
		this.setListToModel(model, "0203", locale);
		this.setListToModel(model, "0204", locale);
		this.setListToModel(model, "0206", locale);
		return engine.edit(this, serviceImpl, form, model, locale);
	}

	/**
	 *
	 * @param supportKey
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	public String copy(@Validated @ModelAttribute(FORM_NAME) GyPaperForm form, Model model, Locale locale) {
		return engine.copy(this, serviceImpl, form, model, locale);
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Model model, Locale locale) {
		this.setListToModel(model, "0024", locale);
		this.setListToModel(model, "0203", locale);
		this.setListToModel(model, "0204", locale);
		this.setListToModel(model, "0206", locale);
		GyPaperForm form = new GyPaperForm();
		form.setPublicFlag("2");
		form.setLanguage("ja");
		return engine.create(this, serviceImpl, form, model, locale);
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute(FORM_NAME) final GyPaperForm form, BindingResult bindingResult,
			Model model, RedirectAttributes attributes, Locale locale) {
		this.setListToModel(model, "0024", locale);
		this.setListToModel(model, "0203", locale);
		this.setListToModel(model, "0204", locale);
		this.setListToModel(model, "0206", locale);
		return engine.update(this, serviceImpl, form, bindingResult, model, attributes, locale);
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(FORM_NAME) final GyPaperForm form, Model model, RedirectAttributes attributes,
			Locale locale) {
		return engine.delete(this, serviceImpl, form, model, attributes, locale);
	}

	/**
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/update/all", method = RequestMethod.POST)
	public String updateAll(@Validated @ModelAttribute(FORM_NAME) final GyPaperForm form, BindingResult bindingResult,
			Model model, RedirectAttributes attributes, Locale locale) {
		return engine.updateAll(this, serviceImpl, form, model, attributes, locale);
	}

	/**
	 *
	 * @param form
	 * @param request
	 * @param response
	 * @param model
	 * @param attributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST, produces = "application/vnd.ms-excel")
	@ResponseBody
	public Resource exportExcel(@ModelAttribute(FORM_NAME) final GyPaperForm form, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes attributes, Locale locale) {
		return engine.exportExcel(this, serviceImpl, form, request, response, model, attributes, locale);
	}

	/**
	 *
	 * @param form
	 * @param request
	 * @param response
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public String importExcel(@ModelAttribute(FORM_NAME) final GyPaperForm form, MultipartHttpServletRequest request,
			BindingResult result, Model model, Locale locale) {
		return engine.importExcel(this, serviceImpl, form, request, model, result, locale);
	}

	@Override
	public PortfolioExcelHelper getExcelHelper() {
		return execlHelper;
	}
}
