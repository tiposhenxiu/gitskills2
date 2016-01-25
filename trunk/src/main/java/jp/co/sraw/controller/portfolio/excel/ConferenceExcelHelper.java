package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyConferenceForm;
import jp.co.sraw.util.PoiBook;

@Service
public class ConferenceExcelHelper extends PortfolioExcelHelper<GyConferenceForm> {

	private final String SHEET_NAME = "CONFERENCE";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyConferenceForm> list) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		for (int i = 0; i < list.size(); i++) {
			GyConferenceForm form = list.get(i);
			int rowno = i + 1;
			// 言語区分
			workbook.changeValue(rowno, 0, getLanguage(form.getLanguage()));
			// タイトル
			workbook.changeValue(rowno, 1, form.getTitle());
			// 講演者
			workbook.changeValue(rowno, 2, form.getAuthor());
			// 会議名
			workbook.changeValue(rowno, 3, form.getJournal());
			// 開催年月日
			workbook.changeValue(rowno, 4, form.getPublicationdate());
			// 招待の有無
			workbook.changeValue(rowno, 5, form.getInvited());
			// 記述言語
			workbook.changeValue(rowno, 6, form.getWlanguage());
			// 会議区分
			workbook.changeValue(rowno, 7,form.getConferenceclass());
			// 会議種別
			workbook.changeValue(rowno, 8, form.getConferencetype());
			// 主催者
			workbook.changeValue(rowno, 9, form.getPromoter());
			// 開催地
			workbook.changeValue(rowno, 10, form.getVenue());
			// 公開範囲
			workbook.changeValue(rowno, 11, form.getPublicFlag());
		}
	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyConferenceForm getForm(Row row) {
		GyConferenceForm form = new GyConferenceForm();

		// 言語区分
		form.setLanguage(getCellValue(row, 0));
		// タイトル
		form.setTitle(getCellValue(row, 1));
		// 講演者
		form.setAuthor(getCellValue(row, 2));
		// 会議名
		form.setJournal(getCellValue(row, 3));
		// 開催年月日
		form.setPublicationdate(getCellValue(row, 4));
		// 招待の有無
		form.setInvited(getCellValue(row, 5));
		// 記述言語
		form.setWlanguage(getCellValue(row, 6));
		// 会議区分
		form.setConferenceclass(getCellValue(row, 7));
		// 会議種別
		form.setConferencetype(getCellValue(row, 8));
		// 主催者
		form.setPromoter(getCellValue(row, 9));
		// 開催地
		form.setVenue(getCellValue(row, 10));
		// 公開範囲
		form.setLanguage(getCellValue(row, 11));

		return form;
	}

}