package jp.co.sraw.oxm.rubric;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jp.co.sraw.common.CommonConst;

public class RubricCategory {
	private String abilityCode;
	private String name;
	private String summary;
	private String lens;
	private List<RubricCategory> childList;
	private List<RubricPhase> phaseList;
	private List<RubricTarget> targetList;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * POSTデータとRubricEditFormの構造の違いを調整する。
	 *
	 */
	public void normalize() {
		if (phaseList == null) {
			return;
		}
		// itemのphaseListの要素数は可変だが、
		// POSTデータとしては、常に固定数が返ってくる。
		// それを可変に戻す。
		Iterator<RubricPhase> ite = phaseList.iterator();
		while (ite.hasNext()) {
			RubricPhase p = ite.next();
			if (p.isDitto()) { // チェックボックスON?
				ite.remove();
			}
		}
	}

	/**
	 * phaseList(要素数可変)を元に、rankからphaseへのマップを作る。 マップといっても、実は配列だが…。
	 *
	 */
	public RubricPhase[] getPhaseMap() {
		return RubricPhase.getPhaseMap(phaseList);
	}

	/**
	 * positionからtarget phaseへのマップ。
	 *
	 */
	public Map<String, Integer> getTargetMap() {
		return RubricTarget.getTargetMap(targetList);
	}

	/**
	 * レンズ設定が、特定のレンズ機能とマッチするかテスト。
	 *
	 * @param lens
	 *            RubricCategoryのlensフィールド
	 * @param lensBit
	 *            CommonConstant.LENSID_XXX
	 * @return
	 */
	public static boolean testLensFor(String lens, int lensBit) {
		if ( lens == null ) {
			return false;
		}
		int l = Integer.valueOf(lens);
		return (l & lensBit) == lensBit;
	}

	public boolean forBasicLens() {
		return RubricCategory.testLensFor(lens, CommonConst.LENSID_BASIC);
	}

	public boolean forCareerLens() {
		return RubricCategory.testLensFor(lens, CommonConst.LENSID_CAREER);
	}

	/**
	 * 該当するレンズの名前一覧を返す。
	 *
	 * @return
	 */
	public List<String> getLensNames() {
		List<String> lis = new ArrayList<String>();
		if (forBasicLens()) {
			lis.add("skill.lens.basic");
		}
		if (forCareerLens()) {
			lis.add("skill.lens.career");
		}
		if (lis.isEmpty()) {
			lis.add("skill.lens.none");
		}

		return lis;
	}

	@XmlElement(name = "abilityCode")
	public String getAbilityCode() {
		return abilityCode;
	}

	public void setAbilityCode(String abilityCode) {
		this.abilityCode = abilityCode;
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

	@XmlElement(name = "lens")
	public String getLens() {
		return lens;
	}

	public void setLens(String lens) {
		this.lens = lens;
	}

	@XmlElementWrapper(name = "childList")
	@XmlElement(name = "category")
	public List<RubricCategory> getChildList() {
		return childList;
	}

	public void setChildList(List<RubricCategory> childList) {
		this.childList = childList;
	}

	@XmlElementWrapper(name = "phaseList")
	@XmlElement(name = "phase")
	public List<RubricPhase> getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(List<RubricPhase> phaseList) {
		this.phaseList = phaseList;
	}

	@XmlElementWrapper(name = "targetList")
	@XmlElement(name = "target")
	public List<RubricTarget> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<RubricTarget> targetList) {
		this.targetList = targetList;
	}

}
