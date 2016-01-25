package jp.co.sraw.controller.account;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import jp.co.sraw.common.CommonForm;

public class AccountForm extends CommonForm {

	//グループの定義
	public interface Step1 {} // アカウント登録(メールアドレスのみ)
	public interface Step2 {} // アカウント本登録(学認:パスワード無し)
	public interface Step3 {} // アカウント本登録(DB認証:パスワード有り)

	//入力
	@NotBlank(groups = {Step1.class, Step2.class, Step3.class})
	@Email(groups = {Step1.class, Step2.class, Step3.class})
	private String mailAddress;

	/**
	 * @return mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress セットする mailAddress
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}


}
