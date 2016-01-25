package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyPaperForm;
import jp.co.sraw.util.PoiBook;

@Service
public class PaperExcelHelper extends PortfolioExcelHelper<GyPaperForm> {

	private final String SHEET_NAME = "PAPER";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyPaperForm> list) {

		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyPaperForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// タイトル
			workbook.changeValue(rowno, 1, form.getTitle());
			// 著者
			workbook.changeValue(rowno, 2, form.getAuthor());
			// 誌名
			workbook.changeValue(rowno, 3, form.getJournal());
			// 巻
			workbook.changeValue(rowno, 4, form.getVolume());
			// 号
			workbook.changeValue(rowno, 5, form.getNumber());
			// 開始ページ
			workbook.changeValue(rowno, 6, form.getStartingpage());
			// 終了ページ
			workbook.changeValue(rowno, 7, form.getEndingpage());
			// 出版年月
			workbook.changeValue(rowno, 8, form.getPublicationdate());
			// 査読の有無
			workbook.changeValue(rowno, 9, form.getReferee());
			// 記述言語
			workbook.changeValue(rowno, 10, form.getWlanguage());
			// 掲載種別
			workbook.changeValue(rowno, 11, form.getPapertypeid());
			// ID:DOI
			workbook.changeValue(rowno, 12, form.getDoi());
			// 公開範囲
			workbook.changeValue(rowno, 13, form.getPublicFlag());
		}

	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyPaperForm getForm(Row row) {
		GyPaperForm form = new GyPaperForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// タイトル
		form.setTitle(getCellValue(row, 1));
		// 著者
		form.setAuthor(getCellValue(row, 2));
		// 誌名
		form.setJournal(getCellValue(row, 3));
		// 巻
		form.setVolume(getCellValue(row, 4));
		// 号
		form.setNumber(getCellValue(row, 5));
		// 開始ページ
		form.setStartingpage(getCellValue(row, 6));
		// 終了ページ
		form.setEndingpage(getCellValue(row, 7));
		// 出版年月
		form.setPublicationdate(getCellValue(row, 8));
		// 査読の有無
		form.setReferee(getCellValue(row, 9));
		// 記述言語
		form.setWlanguage(getCellValue(row, 10));
		// 掲載種別
		form.setPapertypeid(getCellValue(row, 11));
		// ID:DOI
		form.setDoi(getCellValue(row, 12));
		// 公開範囲
		form.setPublicFlag(getCellValue(row, 13));

		return form;
	}

}