package jp.co.sraw.oxm.rubric;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ルーブリックXMLのルート要素。
 *
 */
@XmlRootElement(name = "data")
public class Rubric {
	private String name;
	private String summary;
	private List<RubricCategory> categoryList;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	public Map<String, RubricCategory> buildMap() {
		Map<String, RubricCategory> map = new HashMap<String, RubricCategory>();
		categoryList.forEach(cat -> {
			map.put(cat.getAbilityCode(), cat);
			cat.getChildList().forEach(subc -> {
				map.put(subc.getAbilityCode(), subc);
				subc.getChildList().forEach(item -> map.put(item.getAbilityCode(), item));
			});
		});
		return map;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "summary")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@XmlElementWrapper(name = "categoryList")
	@XmlElement(name = "category")
	public List<RubricCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<RubricCategory> categoryList) {
		this.categoryList = categoryList;
	}
}
