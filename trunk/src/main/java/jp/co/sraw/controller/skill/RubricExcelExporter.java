package jp.co.sraw.controller.skill;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.oxm.rubric.Rubric;
import jp.co.sraw.oxm.rubric.RubricCategory;
import jp.co.sraw.oxm.rubric.RubricPhase;
import jp.co.sraw.oxm.rubric.RubricTarget;
import jp.co.sraw.util.PoiBook;

/**
 * ルーブリックをExcelへエクスポートする。
 *
 *
 */
public class RubricExcelExporter {

	// コンフィグ情報。
	// RXXXはrowで、CXXXはcol。
	private static final int ROFFSET = 0; // 全体のオフセット(VBAの定数とは値が違うよ)。
	private static final int COFFSET = 0; // 全体のオフセット(VBAの定数とは値が違うよ)。
	private static final int RNAME = ROFFSET + 1; // ルーブリック名。
	private static final int CNAME = COFFSET + 4;
	private static final int RSUMMARY = RNAME + 1; // ルーブリック概要
	private static final int CSUMMARY = CNAME;
	private static final int RITEM_TOP = ROFFSET + 6; // 始まり。
	private static final int CITEM_LEFT = COFFSET + 1;

	// COFF_XXXはCITEM_LEFTに対するオフセット。
	private static final int COFF_RDFNO = 0;
	private static final int COFF_DOMNAME = 1;
	private static final int COFF_SDOMNAME = 2;
	private static final int COFF_ITEMNAME = 4;
	private static final int COFF_PHASE = 6;
	private static final int COFF_TARGET = COFF_PHASE + CommonConst.NUM_PHASES;
	private static final int COFF_LENS = COFF_TARGET + CommonConst.NUM_TARGETS;

	private static final String DITTO_STR = "←"; // フェーズ欄の「同左」を意味する特殊値。
	private static final String LENS_MARK = "●"; // レンズ欄の「あり」を意味する特殊値。

	// エクスポート過程の状態を管理するフィールド。
	private Rubric rub;
	private PoiBook book;
	private int row;

	public RubricExcelExporter(Rubric rub, PoiBook book) {
		this.rub = rub;
		this.book = book;
		this.row = RITEM_TOP;
	}

	/**
	 * エクスポートする。
	 */
	public void export() {
		// 概要。
		book.changeValue(RNAME, CNAME, rub.getName());
		book.changeValue(RSUMMARY, CSUMMARY, rub.getSummary());
		// 各カテゴリ。
		rub.getCategoryList().forEach(this::exportCategory);

		this.row = RITEM_TOP; // 2回以上呼ぶことは無いと思うが、念のためリセット。
	}

	private void exportCategory(RubricCategory cat) {
		book.changeValue(row, CITEM_LEFT + COFF_DOMNAME, cat.getName());
		cat.getChildList().forEach(this::exportSubCategory);
	}

	private void exportSubCategory(RubricCategory subCat) {
		book.changeValue(row, CITEM_LEFT + COFF_SDOMNAME, subCat.getName());
		book.changeValue(row, CITEM_LEFT + COFF_SDOMNAME + 1, subCat.getSummary());
		subCat.getChildList().forEach(this::exportItem);
	}

	private void exportItem(RubricCategory item) {
		book.changeValue(row, CITEM_LEFT + COFF_RDFNO, item.getAbilityCode());
		book.changeValue(row, CITEM_LEFT + COFF_ITEMNAME, item.getName());
		book.changeValue(row, CITEM_LEFT + COFF_ITEMNAME + 1, item.getSummary());
		exportPhaseList(item.getPhaseList());
		exportTargetList(item.getTargetList());
		exportLens(item.getLens());

		row++;
	}

	private void exportPhaseList(List<RubricPhase> phases) {
		RubricPhase[] dic = RubricPhase.getPhaseMap(phases);
		Arrays.stream(RubricPhase.getPhaseRanks()).forEach(r -> {
			int ix = r - 1;
			book.changeValue(row, CITEM_LEFT + COFF_PHASE + ix, dic[ix] == null ? DITTO_STR : dic[ix].getTarget());
		});

	}

	private void exportTargetList(List<RubricTarget> targets) {
		IntStream.range(0, CommonConst.NUM_TARGETS).forEach(ix -> {
			book.changeValue(row, CITEM_LEFT + COFF_TARGET + ix, targets.get(ix).getTarget());
		});
	}

	private void exportLens(String lens) {
		int l = Integer.valueOf(lens);
		IntStream.range(0, CommonConst.NUM_LENSES).forEach(ix -> {
			if ((l & (1 << ix)) > 0) {
				book.changeValue(row, CITEM_LEFT + COFF_LENS + ix, LENS_MARK);
			}
		});
	}

}
