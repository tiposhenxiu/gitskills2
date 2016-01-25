package jp.co.sraw.oxm.rubric;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RubricTarget {
	private String position;
	private String target;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * positionからtarget phaseへのマップ。
	 *
	 */
	public static Map<String, Integer> getTargetMap(List<RubricTarget> targets) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		targets.forEach(t -> map.put(t.getPosition(), Integer.valueOf(t.getTarget())));
		return map;
	}

	@XmlAttribute(name = "position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@XmlValue
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
