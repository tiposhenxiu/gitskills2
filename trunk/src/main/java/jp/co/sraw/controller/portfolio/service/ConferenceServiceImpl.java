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

import jp.co.sraw.controller.portfolio.form.GyConferenceForm;
import jp.co.sraw.entity.GyConferenceTbl;
import jp.co.sraw.repository.GyConferenceTblRepository;

/**
 * <B>ConferenceServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class ConferenceServiceImpl extends PortfolioServiceImpl<GyConferenceTbl, GyConferenceForm, GyConferenceTblRepository> {

	@Override
	public GyConferenceForm getPortfolioForm(GyConferenceTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyConferenceForm dto = new GyConferenceForm();
		dto = (GyConferenceForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
