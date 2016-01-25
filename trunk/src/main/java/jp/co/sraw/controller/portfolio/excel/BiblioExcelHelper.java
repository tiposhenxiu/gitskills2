package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.common.CommonForm;
import jp.co.sraw.controller.portfolio.form.GyBiblioForm;
import jp.co.sraw.util.PoiBook;

@Service
public class BiblioExcelHelper extends PortfolioExcelHelper<GyBiblioForm> {

	private final String SHEET_NAME = "BIBLIO";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyBiblioForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyBiblioForm form = list.get(i);
			form.setViewType(CommonForm.VIEW_TYPE_EXCEL);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// タイトル
			workbook.changeValue(rowno, 1, form.getTitle());
			// 著者
			workbook.changeValue(rowno, 2, form.getAuthor());
			// 出版社
			workbook.changeValue(rowno, 3, form.getPublisher());
			// 出版年月
			workbook.changeValue(rowno, 4, form.getPublicationdate());
			// 総ページ数
			workbook.changeValue(rowno, 5, form.getTotalpagenumber().toString());
			// 担当ページ
			workbook.changeValue(rowno, 6, form.getReppagenumber().toString());
			// ID:ISBN
			workbook.changeValue(rowno, 7, form.getIsbn());
			// 担当区分
			workbook.changeValue(rowno, 8, form.getAuthortypeid());
			// 記述言語
			workbook.changeValue(rowno, 9, form.getWlanguage());
			// 公開範囲
			workbook.changeValue(rowno, 10, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyBiblioForm getForm(Row row) {

		GyBiblioForm form = new GyBiblioForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// タイトル
		form.setTitle(getCellValue(row, 1));
		// 著者
		form.setAuthor(getCellValue(row, 2));
		// 出版社
		form.setPublisher(getCellValue(row, 3));
		// 出版年月
		form.setPublicationdate(getCellValue(row, 4));
		// 総ページ数
		form.setTotalpagenumber(getCellBigDecimal(row, 5));
		// 担当ページ
		form.setReppagenumber(getCellBigDecimal(row, 6));
		// ID:ISBN
		form.setIsbn(getCellValue(row, 7));
		// 担当区分
		form.setAuthortypeid(getCellValue(row, 8));
		// 記述言語
		form.setWlanguage(getCellValue(row, 9));
		// 公開範囲
		form.setPublicFlag(getCellValue(row, 10));

		return form;
	}

}