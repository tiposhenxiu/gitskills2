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

import jp.co.sraw.controller.portfolio.form.GyPrizeForm;
import jp.co.sraw.entity.GyPrizeTbl;
import jp.co.sraw.repository.GyPrizeTblRepository;

/**
 * <B>PrizeServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class PrizeServiceImpl extends PortfolioServiceImpl<GyPrizeTbl, GyPrizeForm, GyPrizeTblRepository> {

	@Override
	public GyPrizeForm getPortfolioForm(GyPrizeTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyPrizeForm dto = new GyPrizeForm();
		dto = (GyPrizeForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
