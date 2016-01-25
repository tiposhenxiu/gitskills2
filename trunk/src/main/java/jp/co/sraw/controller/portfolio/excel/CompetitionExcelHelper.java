package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyCompetitionForm;
import jp.co.sraw.util.PoiBook;

@Service
public class CompetitionExcelHelper extends PortfolioExcelHelper<GyCompetitionForm> {

	private final String SHEET_NAME = "COMPETITION";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyCompetitionForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyCompetitionForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// 提供機関
			workbook.changeValue(rowno, 1, form.getProvider());
			// 制度名
			workbook.changeValue(rowno, 2, form.getSystem());
			// タイトル
			workbook.changeValue(rowno, 3, form.getTitle());
			// 研究期間(From)
			workbook.changeValue(rowno, 4, form.getFromdate());
			// 研究期間(To)
			workbook.changeValue(rowno, 5, form.getTodate());
			// 連携研究者
			workbook.changeValue(rowno, 6, form.getMember());
			// 研究分野
			workbook.changeValue(rowno, 7, form.getField());
			// 研究種目
			workbook.changeValue(rowno, 8, form.getCategory());
			// 代表者
			workbook.changeValue(rowno, 9, form.getAuthor());
			// 配分額(総額)
			workbook.changeValue(rowno, 10, form.getAmounttotal().toString());
			// 公開範囲
			workbook.changeValue(rowno, 11, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyCompetitionForm getForm(Row row) {

		GyCompetitionForm form = new GyCompetitionForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// 提供機関
		form.setProvider(getCellValue(row, 1));
		// 制度名
		form.setSystem(getCellValue(row, 2));
		// タイトル
		form.setTitle(getCellValue(row, 3));
		// 研究期間(From)
		form.setFromdate(getCellValue(row, 4));
		// 研究期間(To)
		form.setTodate(getCellValue(row, 5));
		// 連携研究者
		form.setMember(getCellValue(row, 6));
		// 研究分野
		form.setField(getCellValue(row, 7));
		// 研究種目
		form.setCategory(getCellValue(row, 8));
		// 代表者
		form.setAuthor(getCellValue(row, 9));
		// 配分額(総額)
		form.setAmounttotal(this.getCellBigDecimal(row, 10));
		// 公開範囲
		form.setLanguage(getCellValue(row, 11));

		return form;
	}

}