package jp.co.sraw.dto;

import java.io.Serializable;

import jp.co.sraw.entity.ViewTbl;

public abstract class ViewDto implements Serializable {

	public abstract ViewDto getViewDto();

	public abstract String getKbn(ViewTbl tbl);
}
