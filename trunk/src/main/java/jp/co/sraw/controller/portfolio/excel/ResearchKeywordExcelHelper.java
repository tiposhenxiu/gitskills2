package jp.co.sraw.controller.portfolio.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.GyResearchKeywordForm;
import jp.co.sraw.util.PoiBook;

@Service
public class ResearchKeywordExcelHelper extends PortfolioExcelHelper<GyResearchKeywordForm> {

	private final String SHEET_NAME = "ACADEMIC";

	@Override
	public void buildExcelDocument(PoiBook workbook, List<GyResearchKeywordForm> list) {

	}

	@Override
	public Sheet getSheet(Workbook workbook) {
		return workbook.getSheet(SHEET_NAME);
	}

	@Override
	public GyResearchKeywordForm getForm(Row row) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}