package jp.co.sraw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@ImportResource("classpath:applicationContext.xml")
@SpringBootApplication
public class EportfolioApplication extends WebMvcAutoConfigurationAdapter {

    public static void main(String[] args) {
        SpringApplication.run(EportfolioApplication.class, args);
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        // サーバーに保存
        SessionLocaleResolver r = new SessionLocaleResolver();
        //r.setDefaultLocale(Locale.JAPAN);
        return r;
    }

//  @Bean
//  public LocaleResolver localeResolver() {
//      // クライアントに保存
//  	CookieLocaleResolver c = new CookieLocaleResolver();
//  	//c.setCookieMaxAge(60 * 60 * 24 * 30);
//  	return c;
//  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
      LocaleChangeInterceptor i = new LocaleChangeInterceptor();
      i.setParamName("lang");
      return i;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(localeChangeInterceptor());
  }
}
