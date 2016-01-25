/*
* ファイル名：PortfolioForm.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.form;

import java.sql.Timestamp;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.maru.m4hv.extensions.constraints.CharLength;

import jp.co.sraw.common.CommonForm;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.StringUtil;

/*;
 * Formのメソッドを提供する
 */
public class PortfolioForm extends CommonForm {

	public static final String DELIMITER = ":";

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

	private String viewType = VIEW_TYPE_DB;

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public PortfolioForm() {

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
		return getContent(this.publicFlag, "0204");
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
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

	protected String getContent(String value, String code) {
		if (this.viewType.equals(VIEW_TYPE_FORM)) {
			return getFormFormat(value, code);
		}
		if (this.viewType.equals(VIEW_TYPE_EXCEL)) {
			return getExcelFormat(value, code);
		}
		return value;
	}

	public String getExcelFormat(String title, String code) {
		if (StringUtil.isNull(code)) {
			return "";
		}
		Map<String, MsCodeDto> map = DbUtil.getJosuMap(code);
		MsCodeDto dto = map.get(title);
		if (dto != null) {
			return title + DELIMITER + dto.getValue();
		}
		return title + DELIMITER + "";
	}

	public String getFormFormat(String title, String code) {
		if (StringUtil.isNull(code)) {
			return "";
		}
		Map<String, MsCodeDto> map = DbUtil.getJosuMap(code);
		MsCodeDto dto = map.get(title);
		if (dto != null) {
			return title;
		}
		return title;
	}
}
