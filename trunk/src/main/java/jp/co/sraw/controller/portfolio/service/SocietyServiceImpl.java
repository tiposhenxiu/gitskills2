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

import jp.co.sraw.controller.portfolio.form.GySocietyForm;
import jp.co.sraw.entity.GySocietyTbl;
import jp.co.sraw.repository.GySocietyTblRepository;

/**
 * <B>SocietyServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class SocietyServiceImpl
		extends PortfolioServiceImpl<GySocietyTbl, GySocietyForm, GySocietyTblRepository> {

	@Override
	public GySocietyForm getPortfolioForm(GySocietyTbl tbl) {
		if (tbl == null)
			return null;
		//
		GySocietyForm dto = new GySocietyForm();
		dto = (GySocietyForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
