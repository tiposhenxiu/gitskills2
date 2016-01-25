package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyResearchAreaForm;
import jp.co.sraw.util.PoiBook;

@Service
public class ResearchAreaExcelHelper extends PortfolioExcelHelper<GyResearchAreaForm> {

	private final String SHEET_NAME = "RESEARCH_AREA";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyResearchAreaForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyResearchAreaForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 大分類名称
			workbook.changeValue(rowno, 1, form.getFieldid());
			// 中分類名称
			workbook.changeValue(rowno, 2,form.getSubjectid());
			// 細目
			workbook.changeValue(rowno, 3, getSummary(form.getSummaryid()));
			// 公開範囲
			workbook.changeValue(rowno, 4, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyResearchAreaForm getForm(Row row) {
		GyResearchAreaForm form = new GyResearchAreaForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 大分類名称
		form.setFieldname(getCellValue(row, 1));
		// 中分類名称
		form.setSubjectname(getCellValue(row, 2));
		// 細目
		form.setSummary(getCellValue(row, 3));
		// 公開範囲
		form.setLanguage(getCellValue(row, 4));

		return form;
	}

}