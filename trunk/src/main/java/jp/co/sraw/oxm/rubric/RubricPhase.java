package jp.co.sraw.oxm.rubric;

import java.util.List;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jp.co.sraw.common.CommonConst;

public class RubricPhase {
	private String rank;
	private String target;
	private boolean ditto; // これはXML要素ではなく、フォーム用(「まとめる」チェックボックスに対応)。

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * rank一覧の固定長配列。
	 */
	public static int[] getPhaseRanks() {
		return IntStream.range(CommonConst.MIN_PHASERANK, CommonConst.MIN_PHASERANK + CommonConst.NUM_PHASES).toArray();
	}

	/**
	 * phaseList(要素数可変)を元に、rankからphaseへのマップを作る。 マップといっても、実は配列だが…。
	 *
	 */
	public static RubricPhase[] getPhaseMap(List<RubricPhase> phases) {
		RubricPhase[] map = new RubricPhase[CommonConst.NUM_PHASES];
		phases.forEach(p -> map[p.getRankNo() - 1] = p);
		return map;
	}

	public int getRankNo() {
		return Integer.valueOf(rank);
	}

	@XmlAttribute(name = "rank")
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@XmlValue
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@XmlTransient
	public boolean isDitto() {
		return ditto;
	}

	public void setDitto(boolean ditto) {
		this.ditto = ditto;
	}

}
