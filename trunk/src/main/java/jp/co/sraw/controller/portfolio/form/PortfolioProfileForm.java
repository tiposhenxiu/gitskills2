/*
* ファイル名：PortfolioForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.common.CommonForm;
import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.DbUtil;

/*;
 * Formのメソッドを提供する
 */
public class PortfolioProfileForm extends CommonForm {

	private String buttonFlag;

	private String gyosekiKey;

	private String userKey;

	@NotNull
	@CharLength(max = 16)
	private String protfolioLanguage;

	@NotNull
	@CharLength(max = 1)
	private String publicFlag;

	private String updDate;

	private String updUserKey;

	private UsUserTbl usUserTbl;

	public PortfolioProfileForm() {

	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	public Timestamp getUpdDateAsTimestamp() {
		return DateUtil.getTimestamp(this.updDate);
	}

	public UsUserTbl getUsUserTbl() {
		return this.usUserTbl;
	}

	public void setUsUserTbl(UsUserTbl usUserTbl) {
		this.usUserTbl = usUserTbl;
	}

	public String getGyosekiKey() {
		return this.gyosekiKey;
	}

	public void setGyosekiKey(String gyosekiKey) {
		this.gyosekiKey = gyosekiKey;
	}

	public String getLanguage() {
		return this.protfolioLanguage;
	}

	public void setLanguage(String protfolioLanguage) {
		this.protfolioLanguage = protfolioLanguage;
	}

	public String getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getPublicFlagTitle() {
		return DbUtil.getJosuName("0024", this.publicFlag);
	}

	public String getUpdUserKey() {
		return this.updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public String getUserKey() {
		return this.userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public GyCommonTbl getNewTbl() {
		return null;
	}

	public String getButtonFlag() {
		return buttonFlag;
	}

	public void setButtonFlag(String buttonFlag) {
		this.buttonFlag = buttonFlag;
	}
}
