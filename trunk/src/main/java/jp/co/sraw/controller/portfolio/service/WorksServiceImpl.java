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

import jp.co.sraw.controller.portfolio.form.GyWorksForm;
import jp.co.sraw.entity.GyWorksTbl;
import jp.co.sraw.repository.GyWorksTblRepository;

/**
 * <B>WorksServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class WorksServiceImpl
		extends PortfolioServiceImpl<GyWorksTbl, GyWorksForm, GyWorksTblRepository> {

	@Override
	public GyWorksForm getPortfolioForm(GyWorksTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyWorksForm dto = new GyWorksForm();
		dto = (GyWorksForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
