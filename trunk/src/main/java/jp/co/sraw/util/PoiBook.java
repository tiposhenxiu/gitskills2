package jp.co.sraw.util;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * apache-poiのHSSWorkbookのラッパクラス。
 * <h4>使用例:</h4>
 *
 * <pre>
 * try (PoiBook book = PoiBook.fromResource("hoge.xls")) {
 * 	book.setValue(0, 0, "hoge");
 * 	book.createSheet("fuga
 * 	book.setValue(1, 1, "fuga");
 * 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
 * 	book.write(baos);
 * 	return baos;
 * } catch (IOException e) {
 * } catch (Exception e) {
 * }
 * </pre>
 */
public class PoiBook implements AutoCloseable {

	public HSSFWorkbook book;
	public HSSFSheet activeSheet;

	/**
	 * jar内リソースにあるExcelテンプレートを読み込んでPoiBookインスタンスを生成。
	 *
	 * @param path
	 *            リソースパス
	 * @return
	 * @throws IOException
	 */
	public static PoiBook fromResource(String path) throws IOException {
		PoiBook pb = new PoiBook();
		pb.book = new HSSFWorkbook(new POIFSFileSystem(PoiBook.class.getResourceAsStream(path)));
		pb.activeSheet = pb.book.getSheetAt(0);
		return pb;
	}

	private PoiBook() {
	}

	/**
	 * アクティブシートを切り替える。
	 *
	 * @param index
	 */
	public void selectSheetAt(int index) {
		activeSheet = book.getSheetAt(index);
	}

	/**
	 * 新しいシートを作り、アクティブにする。
	 *
	 * @param name
	 *            シート名
	 *
	 */
	public void createSheet(String name) {
		activeSheet = book.createSheet(name);
	}

	/**
	 * 新しいセルに、値をセットする。書式とか罫線はクリアされる(のかな?)。
	 *
	 * @param row
	 *            行番号(0オリジン)
	 * @param col
	 *            列番号(0オリジン)
	 * @param value
	 */
	public void setValue(int row, int col, String value) {
		HSSFRow r = activeSheet.createRow(row);
		HSSFCell cell = r.createCell(col);
		cell.setCellValue(value);
	}

	/**
	 * 既存セルの値を変更する。
	 *
	 * @param row
	 *            行番号(0オリジン)
	 * @param col
	 *            列番号(0オリジン)
	 * @param value
	 */
	public void changeValue(int row, int col, String value) {
		HSSFCell cell = getCellSafe(row, col);
		cell.setCellValue(value);
	}

	/**
	 * OutputStreamへ書き出す。
	 *
	 * @param os
	 * @throws IOException
	 */
	public void write(OutputStream os) throws IOException {
		book.write(os);
	}

	/**
	 * 行が存在するならそれを返す。存在しないなら生成して返す。
	 *
	 * @param row
	 *            行番号(0オリジン)
	 * @return
	 */
	private HSSFRow getRowSafe(int row) {
		HSSFRow r = activeSheet.getRow(row);
		if (r == null) {
			r = activeSheet.createRow(row);
		}
		return r;
	}

	/**
	 * セルが存在するならそれを返す。存在しないなら生成して返す。
	 *
	 * @param row
	 *            行番号(0オリジン)
	 * @param col
	 *            列番号(0オリジン)
	 * @return
	 */
	private HSSFCell getCellSafe(int row, int col) {
		HSSFRow r = getRowSafe(row);
		HSSFCell cell = r.getCell(col);
		if (cell == null) {
			cell = r.createCell(col);
		}
		return cell;
	}

	/**
	 * クローズ。
	 */
	@Override
	public void close() throws Exception {
		if (book != null) {
			book.close();
		}
	}
}
