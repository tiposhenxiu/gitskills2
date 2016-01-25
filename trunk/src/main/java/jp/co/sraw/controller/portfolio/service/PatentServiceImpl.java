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

import jp.co.sraw.controller.portfolio.form.GyPatentForm;
import jp.co.sraw.entity.GyPatentTbl;
import jp.co.sraw.repository.GyPatentTblRepository;
import jp.co.sraw.util.StringUtil;

/**
 * <B>PatentServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class PatentServiceImpl extends PortfolioServiceImpl<GyPatentTbl, GyPatentForm, GyPatentTblRepository> {

	@Override
	public GyPatentForm getPortfolioForm(GyPatentTbl tbl) {
		if (tbl == null)
			return null;
		//
		GyPatentForm dto = new GyPatentForm();
		dto = (GyPatentForm) objectUtil.getObjectCopyValue(dto, tbl);
		// 「出願番号」が設定されている場合
		if (StringUtil.isNotNull(dto.getApplicationid())) {
			dto.setKbn("01");
			// 「出願番号」が設定されている場合、「出願番号」を表示。
			dto.setBango(dto.getApplicationid());
			dto.setBusday(dto.getApplicationdate());
		}
		// 「公開番号」が設定されている場合
		if (StringUtil.isNotNull(dto.getPublicid())) {
			dto.setKbn("02");
			// 「公開番号」が設定されている場合、「公開番号」を表示。
			dto.setBango(dto.getPublicid());
			dto.setBusday(dto.getPublicdate());
		}
		// 「公表番号」が設定されている場合
		if (StringUtil.isNotNull(dto.getTranslationid())) {
			dto.setKbn("03");
			// 「公表番号」が設定されている場合、「公表番号」を表示。
			dto.setBango(dto.getTranslationid());
			dto.setBusday(dto.getTranslationdate());
		}
		// 「特許番号」が設定されている場合
		if (StringUtil.isNotNull(dto.getPatentid())) {
			dto.setKbn("04");
			// 「特許番号」が設定されている場合、「特許番号」を表示。
			dto.setBango(dto.getPatentid());
			dto.setBusday(dto.getPatentdate());
		}
		return dto;
	}

	@Override
	public GyPatentTbl getPortfolioTbl(GyPatentForm f, GyPatentTbl t) {
		t = super.getPortfolioTbl(f, t);
		// 区分で選択された値が、１の時
		if (f.getKbn().equals("01")) {
			// 区分で選択された値が、１の時、番号を設定する。以外の場合、NULL値。
			t.setApplicationid(f.getBango());
			// 区分で選択された値が、１の時、日付を設定する。以外の場合、NULL値。
			t.setApplicationdate(f.getBusday());
		}
		// 区分で選択された値が、02の時
		if (f.getKbn().equals("02")) {
			// 区分で選択された値が、１の時、番号を設定する。以外の場合、NULL値。
			t.setPublicid(f.getBango());
			// 区分で選択された値が、１の時、日付を設定する。以外の場合、NULL値。
			t.setPublicdate(f.getBusday());
		}
		// 区分で選択された値が 03の時
		if (f.getKbn().equals("03")) {
			// 区分で選択された値が、１の時、番号を設定する。以外の場合、NULL値。
			t.setTranslationid(f.getBango());
			// 区分で選択された値が、１の時、日付を設定する。以外の場合、NULL値。
			t.setTranslationdate(f.getBusday());
		}
		// 区分で選択された値が、4の時
		if (f.getKbn().equals("04")) {
			// 区分で選択された値が、4の時、番号を設定する。以外の場合、NULL値。
			t.setPatentid(f.getBango());
			// 区分で選択された値が、4の時、日付を設定する。以外の場合、NULL値。
			t.setPatentdate(f.getBusday());
		}
		return t;
	}

}
