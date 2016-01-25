package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyCareerForm;
import jp.co.sraw.util.PoiBook;

@Service
public class CareerExcelHelper extends PortfolioExcelHelper<GyCareerForm> {

	private final String SHEET_NAME = "CAREER";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyCareerForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyCareerForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 年月(From)
			workbook.changeValue(rowno, 1, form.getFromdate());
			// 年月(To)
			workbook.changeValue(rowno, 2, form.getTodate());
			// 所属
			workbook.changeValue(rowno, 3, form.getDivision());
			// 部署
			workbook.changeValue(rowno, 4, form.getSection());
			// 職位・身分
			workbook.changeValue(rowno, 5, form.getJob());
			// 公開範囲
			workbook.changeValue(rowno, 7, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyCareerForm getForm(Row row) {

		GyCareerForm form = new GyCareerForm();
		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 年月(From)
		form.setFromdate(getCellValue(row, 1));
		// 年月(To)
		form.setTodate(getCellValue(row, 2));
		// 所属
		form.setDivision(getCellValue(row, 3));
		// 部署
		form.setSection(getCellValue(row, 4));
		// 職位・身分
		form.setJob(getCellValue(row, 5));
		// 公開範囲
		form.setLanguage(getCellValue(row, 6));

		return form;
	}

}