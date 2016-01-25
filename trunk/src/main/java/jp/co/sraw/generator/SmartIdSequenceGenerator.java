package jp.co.sraw.generator;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class SmartIdSequenceGenerator extends SequenceGenerator {

	private DecimalFormat format;
	private Type identifierType = new LongType(); // シーケンス格納先をLong型と思わせる。

	@Override
	public void configure(Type type, Properties params, Dialect dialect) throws MappingException {
		super.configure(identifierType, params, dialect);

		String formatPattern = params.getProperty("format");
		if (formatPattern != null) {
			format = new DecimalFormat(formatPattern);
		}
	}

	/**
	 * Formatに合わせて文字列で返す
	 *
	 */
	@Override
	public synchronized Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Serializable generated = super.generate(session, object);
		String v = null;
		// since the type of id is String hibernate returns Long.toString()
		// Value
		if (generated instanceof String) {
			generated = Long.parseLong((String) generated);
		}
		if (generated instanceof Number) {
			if (format != null) {
				v = format.format(generated);
			} else {
				v = String.valueOf(generated);
			}
		} else {
			v = (String) generated;
		}
		return v;
	}

}
