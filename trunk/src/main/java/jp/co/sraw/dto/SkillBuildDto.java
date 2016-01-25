/*
* ファイル名：SkillBuildDto.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2016/01/18   tsutsumi    新規作成
*/
package jp.co.sraw.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <B>能力養成科目のDtoクラス</B>
 * <P>
 */
public class SkillBuildDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 養成能力コード */
	private String subjectCode;

	/** 能力養成科目キー */
	private String lessonKey;

	/** 能力養成科目名称 */
	private String lessonName;

	/** 組織名称 */
	private String partyName;

	/** 受講対象者 */
	private String lessonTarget;

	/** 開講期 */
	private String lessonDate;

	/** 曜日時限 */
	private String lessonPeriod;

	/** 授業形態 */
	private String lessonKbn;

	/** 単位 */
	private String unit;

	/** 履修状況 */
	private String courseStatus;

	/** 更新日時 */
	private Timestamp updDate;

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getLessonKey() {
		return lessonKey;
	}

	public void setLessonKey(String lessonKey) {
		this.lessonKey = lessonKey;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getLessonTarget() {
		return lessonTarget;
	}

	public void setLessonTarget(String lessonTarget) {
		this.lessonTarget = lessonTarget;
	}

	public String getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(String lessonDate) {
		this.lessonDate = lessonDate;
	}

	public String getLessonPeriod() {
		return lessonPeriod;
	}

	public void setLessonPeriod(String lessonPeriod) {
		this.lessonPeriod = lessonPeriod;
	}

	public String getLessonKbn() {
		return lessonKbn;
	}

	public void setLessonKbn(String lessonKbn) {
		this.lessonKbn = lessonKbn;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
}
