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

import jp.co.sraw.controller.portfolio.form.GyBiblioForm;
import jp.co.sraw.entity.GyBiblioTbl;
import jp.co.sraw.repository.GyBiblioTblRepository;

/**
 * <B>BiblioServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class BiblioServiceImpl extends PortfolioServiceImpl<GyBiblioTbl, GyBiblioForm, GyBiblioTblRepository> {

	@Override
	public GyBiblioForm getPortfolioForm(GyBiblioTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyBiblioForm dto = new GyBiblioForm();
		dto = (GyBiblioForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
