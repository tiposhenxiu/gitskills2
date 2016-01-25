package jp.co.sraw.dto;

import java.io.Serializable;

public class MsCodeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;  //定数コード
	private String value; //名称
	private String commentProperty; //注釈(propertiesキー)
	private String useFlag; //有効／無効フラグ
	private boolean selected;//選択状態

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code セットする code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value セットする value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return commentProperty
	 */
	public String getCommentProperty() {
		return commentProperty;
	}
	/**
	 * @param commentProperty セットする commentProperty
	 */
	public void setCommentProperty(String commentProperty) {
		this.commentProperty = commentProperty;
	}
	/**
	 * @return useFlag
	 */
	public String getUseFlag() {
		return useFlag;
	}
	/**
	 * @param useFlag セットする useFlag
	 */
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	/**
	 * @return selected
	 */
	public boolean isSelected() {
		return selected;
	}
	/**
	 * @param selected セットする selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
