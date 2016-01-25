package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GySocietyForm;
import jp.co.sraw.util.PoiBook;

@Service
public class SocietyExcelHelper extends PortfolioExcelHelper<GySocietyForm> {

	private final String SHEET_NAME = "SOCIETY";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GySocietyForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GySocietyForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 所属学協会名
			workbook.changeValue(rowno, 1, form.getTitle());
			// 公開範囲
			workbook.changeValue(rowno, 2, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GySocietyForm getForm(Row row) {
		GySocietyForm form = new GySocietyForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 所属学協会名
		form.setTitle(getCellValue(row, 1));
		// 公開範囲
		form.setLanguage(getCellValue(row, 2));

		return form;
	}

}