package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyPatentForm;
import jp.co.sraw.util.PoiBook;

@Service
public class PatentExcelHelper extends PortfolioExcelHelper<GyPatentForm> {

	private final String SHEET_NAME = "PATENT";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyPatentForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyPatentForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 区分
			workbook.changeValue(rowno, 1, form.getKbn());
			// 番号
			workbook.changeValue(rowno, 2, form.getBango());
			// 日付
			workbook.changeValue(rowno, 3, form.getBusday());
			// 発明の名称
			workbook.changeValue(rowno, 4, form.getTitle());
			// 出願人
			workbook.changeValue(rowno, 5, form.getApplicationperson());
			// 発明者
			workbook.changeValue(rowno, 6, form.getAuthor());
			// 公開範囲
			workbook.changeValue(rowno, 7, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyPatentForm getForm(Row row) {
		GyPatentForm form = new GyPatentForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 区分
		form.setKbn(getCellValue(row, 1));
		// 番号
		form.setBango(getCellValue(row, 2));
		// 日付
		form.setBusday(getCellValue(row, 3));
		// 発明の名称
		form.setTitle(getCellValue(row, 4));
		// 出願人
		form.setApplicationperson(getCellValue(row, 5));
		// 発明者
		form.setAuthor(getCellValue(row, 6));
		// 公開範囲
		form.setLanguage(getCellValue(row, 7));

		return form;
	}

}