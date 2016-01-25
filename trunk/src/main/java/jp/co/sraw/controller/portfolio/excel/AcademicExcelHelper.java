package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.common.CommonForm;
import jp.co.sraw.controller.portfolio.form.GyAcademicForm;
import jp.co.sraw.util.PoiBook;

@Service
public class AcademicExcelHelper extends PortfolioExcelHelper<GyAcademicForm> {

	private final String SHEET_NAME = "ACADEMIC";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyAcademicForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyAcademicForm form = list.get(i);
			form.setViewType(CommonForm.VIEW_TYPE_EXCEL);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 学校名
			workbook.changeValue(rowno, 1, form.getTitle());
			// 学部等
			workbook.changeValue(rowno, 2, form.getDepartmentname());
			// 学科等
			workbook.changeValue(rowno, 3, form.getSubjectname());
			// 国名
			workbook.changeValue(rowno, 4, form.getCountry());
			// 年月(From)
			workbook.changeValue(rowno, 5, form.getFromdate());
			// 年月(To)
			workbook.changeValue(rowno, 6, form.getTodate());
			// 公開範囲
			workbook.changeValue(rowno, 7, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyAcademicForm getForm(Row row) {
		GyAcademicForm form = new GyAcademicForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 学校名
		form.setTitle(getCellValue(row, 1));
		// 学部等
		form.setDepartmentname(getCellValue(row, 2));
		// 学科等
		form.setSubjectname(getCellValue(row, 3));
		// 国名
		form.setCountry(getCellValue(row, 4));
		// 年月(From)
		form.setFromdate(getCellValue(row, 5));
		// 年月(To)
		form.setTodate(getCellValue(row, 6));
		// 公開範囲
		form.setPublicFlag(getCellValue(row, 7));

		return form;
	}

}