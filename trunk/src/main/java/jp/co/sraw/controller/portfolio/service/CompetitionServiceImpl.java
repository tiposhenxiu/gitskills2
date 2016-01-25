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

import jp.co.sraw.controller.portfolio.form.GyCompetitionForm;
import jp.co.sraw.entity.GyCompetitionTbl;
import jp.co.sraw.repository.GyCompetitionTblRepository;

/**
 * <B>CompetitionServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class CompetitionServiceImpl extends PortfolioServiceImpl<GyCompetitionTbl, GyCompetitionForm, GyCompetitionTblRepository> {

	@Override
	public GyCompetitionForm getPortfolioForm(GyCompetitionTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyCompetitionForm dto = new GyCompetitionForm();
		dto = (GyCompetitionForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
