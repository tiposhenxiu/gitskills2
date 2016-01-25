/*
* ファイル名：CommonForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
* <B>CommonFormクラス</B>
* <P>
* Formのメソッドを提供する
*/
public class CommonForm implements Serializable {

	public final static String VIEW_TYPE_FORM = "VIEW_TYPE_FORM";
	public final static String VIEW_TYPE_DB = "VIEW_TYPE_DB";
	public final static String VIEW_TYPE_EXCEL = "VIEW_TYPE_EXCEL";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String pageActionUrl;

	private String pageMode;

	@Override
	public String toString() {
		//return new ToStringCreator(this).toString();
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * @return pageActionUrl
	 */
	public String getPageActionUrl() {
		return pageActionUrl;
	}

	/**
	 * @param pageActionUrl セットする pageActionUrl
	 */
	public void setPageActionUrl(String pageActionUrl) {
		this.pageActionUrl = pageActionUrl;
	}

	/**
	 * @return pageMode
	 */
	public String getPageMode() {
		return pageMode;
	}

	/**
	 * @param pageMode セットする pageMode
	 */
	public void setPageMode(String pageMode) {
		this.pageMode = pageMode;
	}



}
