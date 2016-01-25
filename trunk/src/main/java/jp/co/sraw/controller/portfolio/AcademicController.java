/*
* ファイル名：AcademicController.java
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
import jp.co.sraw.controller.portfolio.excel.AcademicExcelHelper;
import jp.co.sraw.controller.portfolio.excel.PortfolioExcelHelper;
import jp.co.sraw.controller.portfolio.form.GyAcademicForm;
import jp.co.sraw.controller.portfolio.service.AcademicServiceImpl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;

/**
 * <B>AcademicControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@SuppressWarnings({ "rawtypes", "hiding" })
@Controller
@RequestMapping("/portfolio/academic")
public class AcademicController extends PortfolioController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(AcademicController.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
		LIST_PAGE = "portfolio/academic/list";
		EDIT_PAGE = "portfolio/academic/edit";
		REDIRECT_LIST = "redirect:/portfolio/academic/";
		OP_FUNCID = CommonConst.OP_FUNC_USER_CAREER_ACADEMIC;
	}

	//
	@SuppressWarnings("unused")
	private static final String CODE_PUBLICCODE = "0024";
	protected static final String FORM_NAME = "form";

	@Autowired
	private AcademicServiceImpl serviceImpl;

	@Autowired
	private AcademicExcelHelper execlHelper;

	@Autowired
	private PortfolioEngine<AcademicController, GyAcademicForm, AcademicServiceImpl, AcademicExcelHelper> engine;

	/**
	 *
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String list(@ModelAttribute(FORM_NAME) GyAcademicForm form, Model model, Locale locale) {
		return engine.list(this, serviceImpl, form, model, locale);
	}

	/**
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute(FORM_NAME) GyAcademicForm form, Model model, Locale locale) {
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
	public String copy(@Validated @ModelAttribute(FORM_NAME) GyAcademicForm form, Model model, Locale locale) {
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
		GyAcademicForm form = new GyAcademicForm();
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
	public String update(@Validated @ModelAttribute(FORM_NAME) final GyAcademicForm form, BindingResult bindingResult,
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
	public String delete(@ModelAttribute(FORM_NAME) final GyAcademicForm form, Model model,
			RedirectAttributes attributes, Locale locale) {
		return engine.delete(this, serviceImpl, form, model, attributes, locale);
	}

	@RequestMapping(value = "/update/all", method = RequestMethod.POST)
	public String updateAll(@Validated @ModelAttribute(FORM_NAME) final GyAcademicForm form,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, Locale locale) {
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
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	@ResponseBody
	public Resource exportExcel(@ModelAttribute(FORM_NAME) final GyAcademicForm form, HttpServletRequest request,
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
	public String importExcel(@ModelAttribute(FORM_NAME) final GyAcademicForm form, MultipartHttpServletRequest request,
			BindingResult result, Model model, Locale locale) {
		return engine.importExcel(this, serviceImpl, form, request, model, result, locale);
	}

	@Override
	public PortfolioExcelHelper getExcelHelper() {
		return execlHelper;
	}
}
