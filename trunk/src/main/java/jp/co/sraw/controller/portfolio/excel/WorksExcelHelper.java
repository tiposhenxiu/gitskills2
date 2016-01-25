package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyWorksForm;
import jp.co.sraw.util.PoiBook;

@Service
public class WorksExcelHelper extends PortfolioExcelHelper<GyWorksForm> {

	private final String SHEET_NAME = "WORKS";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyWorksForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyWorksForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 作品名
			workbook.changeValue(rowno, 1, form.getTitle());
			// 発表者
			workbook.changeValue(rowno, 2, form.getAuthor());
			// 作品分類
			workbook.changeValue(rowno, 3, form.getWorktype());
			// 発表年月(From)
			workbook.changeValue(rowno, 4, form.getFromdate());
			// 発表年月(To)
			workbook.changeValue(rowno, 5, form.getTodate());
			// 発表場所・発表地等
			workbook.changeValue(rowno, 6, form.getVenue());
			// 公開範囲
			workbook.changeValue(rowno, 7, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyWorksForm getForm(Row row) {
		GyWorksForm form = new GyWorksForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 作品名
		form.setTitle(getCellValue(row, 1));
		// 発表者
		form.setAuthor(getCellValue(row, 2));
		// 作品分類
		form.setWorktype(getCellValue(row, 3));
		// 発表年月(From)
		form.setFromdate(getCellValue(row, 4));
		// 発表年月(To)
		form.setTodate(getCellValue(row, 5));
		// 発表場所・発表地等
		form.setVenue(getCellValue(row, 6));
		// 公開範囲
		form.setLanguage(getCellValue(row, 7));

		return form;
	}

}