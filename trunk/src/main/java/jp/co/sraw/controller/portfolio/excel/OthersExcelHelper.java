package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyOthersForm;
import jp.co.sraw.util.PoiBook;

@Service
public class OthersExcelHelper extends PortfolioExcelHelper<GyOthersForm> {

	private final String SHEET_NAME = "OTHERS";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyOthersForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyOthersForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 実施年月
			workbook.changeValue(rowno, 1, form.getPublicationdate());
			// 内容
			workbook.changeValue(rowno, 2, form.getSummary());
			// 公開範囲
			workbook.changeValue(rowno, 3, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyOthersForm getForm(Row row) {
		GyOthersForm form = new GyOthersForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 実施年月
		form.setPublicationdate(getCellValue(row, 1));
		// 内容
		form.setSummary(getCellValue(row, 2));
		// 公開範囲
		form.setLanguage(getCellValue(row, 3));

		return form;
	}

}