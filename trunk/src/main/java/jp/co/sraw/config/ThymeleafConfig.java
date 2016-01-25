/*
* ファイル名：ThymeleafConfig.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.co.sraw.tags.dialect.MyCustomDialect;

/**
* <B>ThymeleafConfigクラス</B>
* <P>
* ThymeleafConfigを提供する
*/
@Configuration
public class ThymeleafConfig {
	@Bean
	public MyCustomDialect myCustomDialect() {
		return new MyCustomDialect();
	}

}
