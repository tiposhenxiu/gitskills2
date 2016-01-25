package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyPrizeForm;
import jp.co.sraw.util.PoiBook;

@Service
public class PrizeExcelHelper extends PortfolioExcelHelper<GyPrizeForm> {

	private final String SHEET_NAME = "PRIZE";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyPrizeForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyPrizeForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 受賞年月
			workbook.changeValue(rowno, 1, form.getPublicationdate());
			// 授与機関
			workbook.changeValue(rowno, 2, form.getAssociation());
			// 賞名
			workbook.changeValue(rowno, 3, form.getSubtitle());
			// タイトル
			workbook.changeValue(rowno, 4, form.getTitle());
			// 受賞者(グループ)
			workbook.changeValue(rowno, 5, form.getPartner());
			// 受賞区分コード
			workbook.changeValue(rowno, 6, form.getPrizetype());
			// 受賞国
			workbook.changeValue(rowno, 7, form.getPrizetype());
			// 公開範囲
			workbook.changeValue(rowno, 8, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyPrizeForm getForm(Row row) {
		GyPrizeForm form = new GyPrizeForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 受賞年月
		form.setPublicationdate(getCellValue(row, 1));
		// 授与機関
		form.setAssociation(getCellValue(row, 2));
		// 賞名
		form.setTitle(getCellValue(row, 3));
		// タイトル
		form.setSubtitle(getCellValue(row, 4));
		// 受賞者(グループ)
		form.setPartner(getCellValue(row, 5));
		// 受賞区分コード
		form.setPrizetype(getCellValue(row, 6));
		// 受賞国
		form.setCountry(getCellValue(row, 7));
		// 公開範囲
		form.setLanguage(getCellValue(row, 8));

		return form;
	}

}