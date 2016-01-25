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

import jp.co.sraw.controller.portfolio.form.GyDegreeForm;
import jp.co.sraw.entity.GyDegreeTbl;
import jp.co.sraw.repository.GyDegreeTblRepository;
import jp.co.sraw.controller.portfolio.service.PortfolioServiceImpl;

/**
 * <B>DegreeServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class DegreeServiceImpl extends PortfolioServiceImpl<GyDegreeTbl, GyDegreeForm, GyDegreeTblRepository> {

	@Override
	public GyDegreeForm getPortfolioForm(GyDegreeTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyDegreeForm dto = new GyDegreeForm();
		dto = (GyDegreeForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
