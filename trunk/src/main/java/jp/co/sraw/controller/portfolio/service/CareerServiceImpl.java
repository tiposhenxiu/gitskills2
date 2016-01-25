/*
* ファイル名：CareerServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.controller.portfolio.form.GyCareerForm;
import jp.co.sraw.entity.GyCareerTbl;
import jp.co.sraw.repository.GyCareerTblRepository;

/**
 * <B>CareerServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class CareerServiceImpl extends PortfolioServiceImpl<GyCareerTbl, GyCareerForm, GyCareerTblRepository> {

	@Override
	public GyCareerForm getPortfolioForm(GyCareerTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyCareerForm dto = new GyCareerForm();
		dto = (GyCareerForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
