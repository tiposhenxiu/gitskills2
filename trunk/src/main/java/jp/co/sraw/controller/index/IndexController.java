/*
* ファイル名：IndexController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.index;


import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sraw.common.CommonController;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;


/**
* <B>IndexControllerクラス</B>
* <P>
* Controllerのメソッドを提供する
*/
@Controller
@RequestMapping("")
public class IndexController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(IndexController.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 *
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping({"", "/"})
	public String index(Model model) {

		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		if (logger.isDebugEnabled() && userInfo != null) {
			logger.debug("LoginUserKey="+ userInfo.getLoginUserKey());
			logger.debug("TargetUserKey="+ userInfo.getTargetUserKey());
		}

		// dump
		modelDump(logger, model, "index");

		logger.infoCode("I0002", "index/index"); // I0002=メソッド終了:{0}
		return "index/index";
	}
}
