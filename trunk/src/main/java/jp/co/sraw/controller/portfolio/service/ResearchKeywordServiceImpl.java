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

import jp.co.sraw.controller.portfolio.form.GyResearchKeywordForm;
import jp.co.sraw.entity.GyResearchKeywordTbl;
import jp.co.sraw.repository.GyResearchKeywordTblRepository;

/**
 * <B>ResearchKeywordServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class ResearchKeywordServiceImpl
		extends PortfolioServiceImpl<GyResearchKeywordTbl, GyResearchKeywordForm, GyResearchKeywordTblRepository> {

	@Override
	public GyResearchKeywordForm getPortfolioForm(GyResearchKeywordTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyResearchKeywordForm dto = new GyResearchKeywordForm();
		dto = (GyResearchKeywordForm) objectUtil.getObjectCopyValue(dto, tbl);
		return dto;
	}

}
