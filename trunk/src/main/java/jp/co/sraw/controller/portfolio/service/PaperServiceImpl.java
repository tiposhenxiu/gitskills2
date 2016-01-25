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

import jp.co.sraw.controller.portfolio.form.GyPaperForm;
import jp.co.sraw.entity.GyPaperTbl;
import jp.co.sraw.repository.GyPaperTblRepository;

/**
 * <B>UserServiceクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class PaperServiceImpl extends PortfolioServiceImpl<GyPaperTbl, GyPaperForm, GyPaperTblRepository> {

	@Override
	public GyPaperForm getPortfolioForm(GyPaperTbl gyPaperTbl) {
		//
		if (gyPaperTbl == null)
			return null;
		//
		GyPaperForm dto = new GyPaperForm();
		dto = (GyPaperForm) objectUtil.getObjectCopyValue(dto, gyPaperTbl);
		return dto;
	}

}
