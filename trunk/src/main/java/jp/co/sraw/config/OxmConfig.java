package jp.co.sraw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class OxmConfig {
	@Bean(name="xmlMarshaller")
	public Marshaller getXmlMarshaller() {
		return getJaxb2Marshaller();
	}

	@Bean(name="xmlUnmarshaller")
	public Unmarshaller getXmlUnmarshaller() {
		return getJaxb2Marshaller();
	}

	private Jaxb2Marshaller getJaxb2Marshaller() {
		Jaxb2Marshaller m = new Jaxb2Marshaller();
		m.setPackagesToScan("jp.co.sraw.oxm");
		return m;
	}
}
