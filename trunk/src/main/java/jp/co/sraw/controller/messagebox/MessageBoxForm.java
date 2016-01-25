/*
* ファイル名：HomeForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.messagebox;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.common.CommonForm;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>HomeFormクラス</B>
 * <P>
 * Formのメソッドを提供する
 */
public class MessageBoxForm extends CommonForm {

	private String messageKey;

	private String userKey;

	@NotNull
	@CharLength(min = 10, max = 10)
	private String sendDate;

	private String messageTitle;

	private String messageContents;

	private String refMessageKey;

	private String makeUserKey;

	@NotNull
	@CharLength(min = 10, max = 10)
	private String updDate;

	private String updUserKey;

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getMakeUserKey() {
		return makeUserKey;
	}

	public void setMakeUserKey(String makeUserKey) {
		this.makeUserKey = makeUserKey;
	}

	public String getUpdUserKey() {
		return updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	// private UsUserTbl usUserTbl;
	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	public Timestamp getUpdDateAsTimestamp() {
		return DateUtil.getTimestamp(this.updDate);
	}

	public String getSendDate() {
		return sendDate;
	}

	public Timestamp getSendDateAsTimestamp() {
		return DateUtil.getTimestamp(this.sendDate, "yyyy-MM-dd");
	}

	public void setSendDate(String sendDate) {
		this.sendDate = StringUtil.htmlFilter(sendDate);
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContents() {
		return messageContents;
	}

	public void setMessageContents(String messageContents) {
		this.messageContents = messageContents;
	}

	public String getRefMessageKey() {
		return refMessageKey;
	}

	public void setRefMessageKey(String refMessageKey) {
		this.refMessageKey = refMessageKey;
	}
}
