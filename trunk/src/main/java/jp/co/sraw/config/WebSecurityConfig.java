/*
* ファイル名：WebSecurityConfig.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.security.Role;
import jp.co.sraw.service.UserInfoServiceImpl;

/*
 * Ordered.HIGHEST_PRECEDENCE 	-2147483648
 * SecurityProperties.IGNORED_ORDER 	-2147483648
 * SecurityProperties.DEFAULT_FILTER_ORDER 	0
 * ManagementServerProperties.ACCESS_OVERRIDE_ORDER 	2147483636
 * ManagementServerProperties.BASIC_AUTH_ORDER 	2147483637
 * SecurityProperties.ACCESS_OVERRIDE_ORDER 	2147483640
 * SecurityProperties.BASIC_AUTH_ORDER 	2147483642
 * Ordered.LOWEST_PRECEDENCE 	2147483647
 */

/**
* <B>WebSecurityConfigクラス</B>
* <P>
* WebSecurityConfigを提供する
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String DEFAULT_SUCCESS_URL = "/home";

	public static final String COOKIE_NAME = "remember-me";

	public static final String REMEMBERME_KEY = "remember-me-webapp-lending";

	public static final int TOKEN_VALIDITY_SECONDS = 1209600;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserInfoServiceImpl userInfoService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .headers()
//                .xssProtection()
//                .and()
//                .frameOptions()
//                .and()
//                .contentTypeOptions()
//                .and()
//                .cacheControl();
        http
            // 無効
            //.csrf().disable()
            //.headers().cacheControl().disable()
            //.and()
            //有効
            //
            //DB認証
            .authorizeRequests()
                // DB認証対象外のパスを設定する
                .antMatchers("/", "/login", "/ssologin", "/logout", "/css/**", "/js/**", "/fonts/**", "/images/**", "**/favicon.ico", "/account/**", "/service/batch/**", "/bootstrap/**", "/dist/**", "/gfx/**", "/img/**", "/jquery/**", "/plugins/**")
                // /account はアカウント登録用
                // /ssologin は学認(Shibboleth)用
                // /service/batch はバッチ起動用 アクセス許可判定はヘッダーパラメータで判断
                // 上記パスへのアクセスを許可する
                .permitAll()
                // MGMT権限指定
                .antMatchers("/mgmt/**").hasAnyRole(Role.ROLE_MGMT1.getShortRole(), Role.ROLE_MGMT2.getShortRole(), Role.ROLE_MGMT3.getShortRole(), Role.ROLE_ADMIN.getShortRole())
                // ADMIN権限指定
                .antMatchers("/management/**", "/admin/**").hasRole(Role.ROLE_ADMIN.getShortRole())
                // その他のリクエストは認証がに必要
                .anyRequest().authenticated()
                .and()
            .formLogin()
                // ログインフォームのパス
                .loginPage("/login")
                // ログイン処理のパス
                .loginProcessingUrl("/login")
                // ログイン成功時の遷移先
                .defaultSuccessUrl(DEFAULT_SUCCESS_URL)
                // ログイン失敗時の遷移先
                .failureUrl("/login?error")
                // ログインフォームで使用するユーザー名のinput name
                .usernameParameter("username")
                // ログインフォームで使用するパスワードのinput name
                .passwordParameter("password")
                .permitAll()
                .and()
            .rememberMe()
                .key(REMEMBERME_KEY)
                //.tokenValiditySeconds(2592000) // 1ヶ月（秒）= 60 * 60 * 24 * 30
                //.tokenValiditySeconds(1209600) // デフォルト14日= 60 * 60 * 24 * 14
                .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
                .and()
            .logout()
                // ログアウトがパス(GET)の場合設定する（CSRF対応）
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // ログアウトがPOSTの場合設定する
                //.logoutUrl("/logout")
                // ログアウト後の遷移先
                .logoutSuccessUrl("/login?logout")
                // セッションを破棄する
                .invalidateHttpSession(true)
                // ログアウト時に削除するクッキー名
                //.deleteCookies("JSESSIONID", COOKIE_NAME)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 独自認証クラスを設定する
		auth.userDetailsService(userInfoService).passwordEncoder(passwordEncoder);
	}

	// パスワードの暗号化方式
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder(CommonConst.SECRET_KEY);
	}

//	@Bean
//	public MessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasenames("i18n/messages", "i18n/ValidationMessages", "i18n/event");
//		messageSource.setCacheSeconds(-1);
//		messageSource.setDefaultEncoding("UTF-8");
//		return messageSource;
//	}
}
