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

import jp.co.sraw.controller.portfolio.form.GyResearchAreaForm;
import jp.co.sraw.entity.GyResearchAreaTbl;
import jp.co.sraw.repository.GyResearchAreaTblRepository;

/**
 * <B>ResearchAreaServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class ResearchAreaServiceImpl extends PortfolioServiceImpl<GyResearchAreaTbl, GyResearchAreaForm, GyResearchAreaTblRepository> {

	@Override
	public GyResearchAreaForm getPortfolioForm(GyResearchAreaTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyResearchAreaForm dto = new GyResearchAreaForm();
		dto = (GyResearchAreaForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
