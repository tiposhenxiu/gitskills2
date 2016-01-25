package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyDegreeForm;
import jp.co.sraw.util.PoiBook;

@Service
public class DegreeExcelHelper extends PortfolioExcelHelper<GyDegreeForm> {

	private final String SHEET_NAME = "DEGREE";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyDegreeForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyDegreeForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 学位名
			workbook.changeValue(rowno, 1, form.getDegreename());
			// 学位・授与機関
			workbook.changeValue(rowno, 2, form.getOrganization());
			// 公開範囲
			workbook.changeValue(rowno, 3, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyDegreeForm getForm(Row row) {
		GyDegreeForm form = new GyDegreeForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 学位名
		form.setDegreename(getCellValue(row, 1));
		// 学位・授与機関
		form.setOrganization(getCellValue(row, 2));
		// 公開範囲
		form.setLanguage(getCellValue(row, 3));

		return form;
	}

}