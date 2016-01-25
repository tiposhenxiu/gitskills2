/*
* ファイル名：AcademicServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.controller.portfolio.form.GyAcademicForm;
import jp.co.sraw.entity.GyAcademicTbl;
import jp.co.sraw.repository.GyAcademicTblRepository;

/**
 * <B>AcademicServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class AcademicServiceImpl extends PortfolioServiceImpl<GyAcademicTbl, GyAcademicForm, GyAcademicTblRepository> {

	@Override
	public GyAcademicForm getPortfolioForm(GyAcademicTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyAcademicForm dto = new GyAcademicForm();
		dto = (GyAcademicForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
