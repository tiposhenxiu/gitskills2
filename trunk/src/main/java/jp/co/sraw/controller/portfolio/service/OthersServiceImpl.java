/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.controller.portfolio.form.GyOthersForm;
import jp.co.sraw.entity.GyOthersTbl;
import jp.co.sraw.repository.GyOthersTblRepository;

/**
 * <B>UserServiceクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class OthersServiceImpl extends PortfolioServiceImpl<GyOthersTbl, GyOthersForm, GyOthersTblRepository> {

	@Override
	public GyOthersForm getPortfolioForm(GyOthersTbl gyOthersTbl) {
		//
		if (gyOthersTbl == null)
			return null;
		//
		GyOthersForm dto = new GyOthersForm();
		dto = (GyOthersForm) objectUtil.getObjectCopyValue(dto, gyOthersTbl);
		return dto;
	}

}
