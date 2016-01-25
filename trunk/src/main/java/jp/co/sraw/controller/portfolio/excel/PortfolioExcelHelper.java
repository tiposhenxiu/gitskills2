package jp.co.sraw.controller.portfolio.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sraw.controller.portfolio.form.PortfolioForm;
import jp.co.sraw.dto.MsCodeDto;
import jp.co.sraw.entity.MsResearchAreaTbl;
import jp.co.sraw.service.MsResearchAreaServiceImpl;
import jp.co.sraw.util.DbUtil;
import jp.co.sraw.util.PoiBook;
import jp.co.sraw.util.StringUtil;

@Service
public abstract class PortfolioExcelHelper<F extends PortfolioForm> {

	public final static String VIEW_TYPE_FORM = "VIEW_TYPE_FORM";
	public final static String VIEW_TYPE_DB = "VIEW_TYPE_DB";
	public final static String VIEW_TYPE_EXCEL = "VIEW_TYPE_EXCEL";

	private String viewType = VIEW_TYPE_DB;

	public static final String DELIMITER = ":";

	private final String SHEET_NAME = "CONTENTS";

	@Autowired
	public MsResearchAreaServiceImpl serviceImpl;

	public void buildExcelDocument(PoiBook book, List<F> list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public List<F> getFormList(Workbook workbook) {

		Sheet sheet = getSheet(workbook);

		int startRow = 1;

		List<F> list = new ArrayList<>();

		for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				list.add(getForm(row));
			}
		}
		return list;
	}

	public abstract Sheet getSheet(Workbook workbook);

	public abstract F getForm(Row row);

	protected String getLanguage(String language) {
		if (this.viewType.equals(VIEW_TYPE_EXCEL)) {
			return language + DELIMITER + language;
		}
		return language;
	}

	protected String getSummary(String areaCode) {
		if (this.viewType.equals(VIEW_TYPE_EXCEL)) {
			return getSummaryExcelFormat(areaCode);
		}

		if (this.viewType.equals(VIEW_TYPE_FORM)) {
			return getSummaryFORMFormat(areaCode);
		}
		return areaCode;
	}

	public String getSummaryFORMFormat(String areaCode) {
		if (StringUtil.isNull(areaCode)) {
			return "";
		}
		MsResearchAreaTbl tbl = serviceImpl.findOne(areaCode);
		if (tbl != null) {
			return  tbl.getResearchAreaName();
		}
		return areaCode;
	}

	public String getSummaryExcelFormat(String areaCode) {
		if (StringUtil.isNull(areaCode)) {
			return "";
		}
		MsResearchAreaTbl tbl = serviceImpl.findOne(areaCode);
		if (tbl != null) {
			return tbl.getResearchAreaCode() + DELIMITER + tbl.getResearchAreaName();
		}
		return areaCode + DELIMITER + "";
	}

	public String getTitle(String title, String code) {
		if (StringUtil.isNull(code)) {
			return "";
		}
		Map<String, MsCodeDto> map = DbUtil.getJosuMap(code);
		MsCodeDto dto = map.get(title);
		if (dto != null) {
			return title + DELIMITER + dto.getValue();
		}
		return title + DELIMITER + "";
	}

	protected String getCellValue(Row row, int sortNo) {
		if (row.getCell(sortNo) == null)
			return null;
		int cellType = row.getCell(sortNo).getCellType();
		String result = "";
		switch (cellType) {
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
			result = String.valueOf(row.getCell(sortNo).getNumericCellValue());
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
			result = row.getCell(sortNo).getStringCellValue();
		default:
			break;
		}
		if (result.contains(DELIMITER))
			result = result.substring(0, result.indexOf(DELIMITER));
		return result;
	}

	protected int getCellIntValue(Row row, int sortNo) {
		if (row.getCell(sortNo) == null)
			return 0;
		int cellType = row.getCell(sortNo).getCellType();
		int result = 0;
		try {
			switch (cellType) {
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
				result = (int) row.getCell(sortNo).getNumericCellValue();
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
				result = Integer.parseInt(row.getCell(sortNo).getStringCellValue());
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	protected BigDecimal getCellBigDecimal(Row row, int sortNo) {
		if (row.getCell(sortNo) == null)
			return null;
		int cellType = row.getCell(sortNo).getCellType();
		BigDecimal result = new BigDecimal(0);
		try {
			switch (cellType) {
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
				result = new BigDecimal(row.getCell(sortNo).getNumericCellValue());
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
				result = new BigDecimal(row.getCell(sortNo).getStringCellValue());
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String[] codelist = new String[] { "0024", "0204", "0206", "0203", "0209", "0210", "0208", "0213", "0201",
			"2011", "0221", "0222", "0017" };

	public void buildSelectItemList(PoiBook workbook) {
		workbook.activeSheet = workbook.book.getSheet(SHEET_NAME);

		int colno = 0;
		List<MsCodeDto> langlist = DbUtil.getJosuList("0041");
		for (int i = 0; i < langlist.size(); i++) {
			MsCodeDto dto = langlist.get(i);
			workbook.changeValue(i, colno, dto.getValue() + DELIMITER + dto.getValue());
		}

		for (int i = 0; i < codelist.length; i++) {
			colno++;
			List<MsCodeDto> list = DbUtil.getJosuList(codelist[i]);
			for (int j = 0; j < list.size(); j++) {
				MsCodeDto dto = list.get(j);
				workbook.changeValue(j, colno, dto.getCode() + DELIMITER + dto.getValue());
			}
		}

		colno++;

		List<MsResearchAreaTbl> list1 = serviceImpl.findAllResearchArea();
		for (int i = 0; i < list1.size(); i++) {
			MsResearchAreaTbl dto = list1.get(i);
			workbook.changeValue(i, colno, dto.getResearchAreaCode() + DELIMITER + dto.getResearchAreaName());
		}

	}
}
