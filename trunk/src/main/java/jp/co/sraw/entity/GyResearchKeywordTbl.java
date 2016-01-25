package jp.co.sraw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the gy_research_keyword_tbl database table.
 *
 */
@Entity
@Table(name = "gy_research_keyword_tbl")
@NamedQuery(name = "GyResearchKeywordTbl.findAll", query = "SELECT g FROM GyResearchKeywordTbl g")
public class GyResearchKeywordTbl extends GyCommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "research_keyword_language")
	private String researchKeywordLanguage;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GyResearchKeywordTbl() {
	}

	public String getResearchKeywordLanguage() {
		return this.researchKeywordLanguage;
	}

	public void setResearchKeywordLanguage(String researchKeywordLanguage) {
		this.researchKeywordLanguage = researchKeywordLanguage;
	}

}