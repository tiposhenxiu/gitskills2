package jp.co.sraw.dto;

import java.io.Serializable;

public class CmScheduleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String start;

	private String end;

	private String title;

	private String backgroundColor;

	private String borderColor;

	private String url;

	private String dataKbn;

	private String scheduleTitle;

	private String scheduleTelno;

	private String scheduleMemo;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getScheduleMemo() {
		return this.scheduleMemo;
	}

	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}

	public String getScheduleTitle() {
		return this.scheduleTitle;
	}

	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}

	public String getScheduleTelno() {
		return this.scheduleTelno;
	}

	public void setScheduleTelno(String scheduleTelno) {
		this.scheduleTelno = scheduleTelno;
	}

	public String getDataKbn() {
		return this.dataKbn;
	}

	public void setDataKbn(String dataKbn) {
		this.dataKbn = dataKbn;
	}

	public String getBackgroundColor() {
		return this.backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return this.borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getEnd() {
		return this.end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}