/*
* ファイル名：LoginController.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.login;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sraw.common.CommonController;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.config.WebSecurityConfig;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.service.UserInfoServiceImpl;
import jp.co.sraw.util.StringUtil;

/**
* <B>LoginControllerクラス</B>
* <P>
* Controllerのメソッドを提供する
*/
@Controller
@RequestMapping("/")
public class LoginController extends CommonController {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(LoginController.class);

	@Autowired
	private UserInfoServiceImpl userInfoService;

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * DB認証画面遷移＆クッキー認証
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		logger.infoCode("I0001");

		// 有効な remember-me Cookie が存在する場合にはログイン画面を表示させず自動ログインさせる
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
				WebSecurityConfig.REMEMBERME_KEY, userInfoService);
		// クッキー取得
		rememberMeServices.setCookieName(WebSecurityConfig.COOKIE_NAME);
		//
		Authentication rememberMeAuth = rememberMeServices.autoLogin(request, response);
		if (rememberMeAuth != null) {
			SecurityContextHolder.getContext().setAuthentication(rememberMeAuth);

			logger.infoCode("I0002", "login"); // I0002=メソッド終了:{0}
			return "redirect:" + WebSecurityConfig.DEFAULT_SUCCESS_URL;
		}

		logger.infoCode("I0002", "login"); // I0002=メソッド終了:{0}
		return "login/login";
	}

	/**
	 * ログアウト
	 *
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		logger.infoCode("I0001");
		return "login/login";
	}


	/**
	 * SSO認証(暫定)
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/ssologin")
	public String urllogin(HttpServletRequest request) {
		logger.infoCode("I0001");

		if (StringUtil.isNotNull(systemSetting.getShibbolethLoginid())) {
			try {

				// ヘッダーパラメータ取得
				String username = request.getHeader(systemSetting.getShibbolethLoginid());

				// username パラメータで指定されたメールアドレスのユーザが user_info テーブルに存在するかチェックする
				UserInfo lendingUserInfo = userInfoService.loadUserByUsername(username);
				if (lendingUserInfo != null) {
					// UsernamePasswordAuthenticationToken を生成して SecurityContext にセットする
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(lendingUserInfo, null,
							lendingUserInfo.getAuthorities());

					// org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
					// setDetails メソッドを見て実装しています
					AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
					token.setDetails(authenticationDetailsSource.buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(token);

					logger.infoCode("I0002", "ssologin"); // I0002=メソッド終了:{0}
					return "redirect:" + WebSecurityConfig.DEFAULT_SUCCESS_URL;
				}
			} catch (UsernameNotFoundException e) {
				logger.errorCode("E0014", "ssologin"); // E0014=メソッド異常終了:{0}
			}
		}
		return "redirect:login";
	}

}
