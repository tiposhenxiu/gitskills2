package jp.co.sraw.dto;

import java.io.Serializable;
import java.util.Date;

public class SkillPastAnswerDto implements Serializable {

	private Date savedDate;
	private int nume;
	private int denom;

	public Date getSavedDate() {
		return savedDate;
	}

	public void setSavedDate(Date savedDate) {
		this.savedDate = savedDate;
	}

	public int getRatio() {
		if (denom <= 0) {
			return 0;
		}
		return nume * 100 / denom;
	}

	public int getNume() {
		return nume;
	}

	public void setNume(int nume) {
		this.nume = nume;
	}

	public int getDenom() {
		return denom;
	}

	public void setDenom(int denom) {
		this.denom = denom;
	}

}
