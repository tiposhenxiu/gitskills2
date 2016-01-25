package jp.co.sraw.controller.skill;

import java.util.List;

import jp.co.sraw.common.CommonForm;

/**
 * <b>養成科目履修画面のフォーム</b>
 *
 */
public class BuildForm extends CommonForm {
	/** 表示中 大項目 */
	private String selMainCtgry;
	/** 表示中 中項目 */
	private String selSubCtgry;
	/** 表示中 小項目 */
	private String selGrantCtgry;

	/** 参加状況：参加確定済 */
	private Boolean chkJoin;
	/** 参加状況：参加予定 */
	private Boolean chkReserve;
	/** 参加状況：未参加 */
	private Boolean chkNone;

	/** 選択した行番号 */
	private List<String> selRow;
	/** 一覧データ */
	private List<CurriculumForm> curriculumList;

	public Boolean getChkJoin() {
		return chkJoin;
	}
	public void setChkJoin(Boolean chkJoin) {
		this.chkJoin = chkJoin;
	}
	public Boolean getChkReserve() {
		return chkReserve;
	}
	public void setChkReserve(Boolean chkReserve) {
		this.chkReserve = chkReserve;
	}
	public Boolean getChkNone() {
		return chkNone;
	}
	public void setChkNone(Boolean chkNone) {
		this.chkNone = chkNone;
	}
	public List<String> getSelRow() {
		return selRow;
	}
	public void setSelRow(List<String> selRow) {
		this.selRow = selRow;
	}
	public String getSelMainCtgry() {
		return selMainCtgry;
	}
	public void setSelMainCtgry(String selMainCtgry) {
		this.selMainCtgry = selMainCtgry;
	}
	public String getSelSubCtgry() {
		return selSubCtgry;
	}
	public void setSelSubCtgry(String selSubCtgry) {
		this.selSubCtgry = selSubCtgry;
	}
	public String getSelGrantCtgry() {
		return selGrantCtgry;
	}
	public void setSelGrantCtgry(String selGrantCtgry) {
		this.selGrantCtgry = selGrantCtgry;
	}
	public List<CurriculumForm> getCurriculumList() {
		return curriculumList;
	}
	public void setCurriculumList(List<CurriculumForm> curriculumList) {
		this.curriculumList = curriculumList;
	}
}
