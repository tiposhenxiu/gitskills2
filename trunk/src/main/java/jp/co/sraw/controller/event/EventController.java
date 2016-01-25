/*
* ファイル名：IndexController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sraw.common.CommonController;
import jp.co.sraw.dto.EvEventViewDto;
import jp.co.sraw.dto.EventDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.EventServiceImpl;

/**
 * <B>EventControllerクラス</B>
 * <P>
 * Controllerのメソッドを提供する
 */
@Controller
@RequestMapping("/event")
public class EventController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(EventController.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	@Autowired
	private EventServiceImpl eventServiceImpl;

	private static final String LIST_PAGE = "event/list";

	private static final String FORM_NAME = "form";

	List<EventDto> slist = new ArrayList<>();

	Boolean publicFlag = false;
	Boolean partyCodeFlag = false;
	Boolean roleFlag = false;

	/**
	 *
	 * @param role
	 * @return
	 */
	private boolean checkAuthority(String role) {
		Collection<GrantedAuthority> authorityList = userInfo.getAuthorities();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
		// return authorityList.contains(authority);
		return true;
	}

	/**
	 *
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/", "/list" })
	public String list(@ModelAttribute(FORM_NAME) final EventForm form, Model model, Locale locale) {

		logger.infoCode("I0001");

		List<EvEventViewDto> eventPresentList = new ArrayList<>();

		// if (userInfo.isUser1() || userInfo.isUser2() || userInfo.isUser3() ||
		// userInfo.isUser4()) {
		// publicFlag = true;
		// eventPresentList = eventServiceImpl.findAllEvent(userInfo,
		// publicFlag, partyCodeFlag, roleFlag, false);
		// }
		//
		// if (userInfo.isMgmt1()) {
		// partyCodeFlag = true;
		// eventPresentList = eventServiceImpl.findAllEvent(userInfo,
		// publicFlag, partyCodeFlag, roleFlag, false);
		// }
		//
		// if (userInfo.isMgmt2() || userInfo.isMgmt3() || userInfo.isMgmt4()) {
		// publicFlag = true;
		// roleFlag = true;
		// eventPresentList = eventServiceImpl.findAllEvent(userInfo,
		// publicFlag, partyCodeFlag, roleFlag, false);
		// }

		// if (userInfo.isAdmin()) {
		// eventPresentList = eventServiceImpl.findAllEvent(userInfo,
		// publicFlag, partyCodeFlag, roleFlag, false);
		eventPresentList = eventServiceImpl.findAllEventViewDto(userInfo, locale);
		// }

		model.addAttribute("eventPresentList", eventPresentList);

		if (logger.isDebugEnabled()) {
			logger.debug("LoginUserKey=" + userInfo.getLoginUserKey());
			logger.debug("TargetUserKey=" + userInfo.getTargetUserKey());
		}

		// dump
		modelDump(logger, model, "index");

		return LIST_PAGE;
	}
}
