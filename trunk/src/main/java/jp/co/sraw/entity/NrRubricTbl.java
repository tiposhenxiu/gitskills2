package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import jp.co.sraw.common.CommonConst;


/**
 * The persistent class for the nr_rubric_tbl database table.
 *
 */
@Entity
@Table(name="nr_rubric_tbl")
@NamedQuery(name="NrRubricTbl.findAll", query="SELECT n FROM NrRubricTbl n")
public class NrRubricTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="NR_RUBRIC_TBL_RUBRICKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="NR_RUBRIC_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NR_RUBRIC_TBL_RUBRICKEY_GENERATOR")
	@Column(name="rubric_key")
	private String rubricKey;

	@Column(name="rubric_contents")
	@Type(type="jp.co.sraw.type.XmlType")
	private String rubricContents;

	@Column(name="rubric_memo")
	private String rubricMemo;

	@Column(name="rubric_name")
	private String rubricName;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to NrRubricRelUserTbl
	@OneToMany(mappedBy="nrRubricTbl")
	private List<NrRubricRelUserTbl> nrRubricRelUserTbls;

	//bi-directional many-to-one association to NrRubricLense
	@OneToMany(mappedBy="nrRubricTbl")
	private List<NrRubricLense> nrRubricLenses;

	public NrRubricTbl() {
	}

	public String getRubricKey() {
		return this.rubricKey;
	}

	public void setRubricKey(String rubricKey) {
		this.rubricKey = rubricKey;
	}

	public String getRubricContents() {
		return this.rubricContents;
	}

	public void setRubricContents(String rubricContents) {
		this.rubricContents = rubricContents;
	}

	public String getRubricMemo() {
		return this.rubricMemo;
	}

	public void setRubricMemo(String rubricMemo) {
		this.rubricMemo = rubricMemo;
	}

	public String getRubricName() {
		return this.rubricName;
	}

	public void setRubricName(String rubricName) {
		this.rubricName = rubricName;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUserKey() {
		return this.updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public List<NrRubricRelUserTbl> getNrRubricRelUserTbls() {
		return this.nrRubricRelUserTbls;
	}

	public void setNrRubricRelUserTbls(List<NrRubricRelUserTbl> nrRubricRelUserTbls) {
		this.nrRubricRelUserTbls = nrRubricRelUserTbls;
	}

	public NrRubricRelUserTbl addNrRubricRelUserTbl(NrRubricRelUserTbl nrRubricRelUserTbl) {
		getNrRubricRelUserTbls().add(nrRubricRelUserTbl);
		nrRubricRelUserTbl.setNrRubricTbl(this);

		return nrRubricRelUserTbl;
	}

	public NrRubricRelUserTbl removeNrRubricRelUserTbl(NrRubricRelUserTbl nrRubricRelUserTbl) {
		getNrRubricRelUserTbls().remove(nrRubricRelUserTbl);
		nrRubricRelUserTbl.setNrRubricTbl(null);

		return nrRubricRelUserTbl;
	}

	public List<NrRubricLense> getNrRubricLenses() {
		return this.nrRubricLenses;
	}

	public void setNrRubricLenses(List<NrRubricLense> nrRubricLenses) {
		this.nrRubricLenses = nrRubricLenses;
	}

	public NrRubricLense addNrRubricLens(NrRubricLense nrRubricLens) {
		getNrRubricLenses().add(nrRubricLens);
		nrRubricLens.setNrRubricTbl(this);

		return nrRubricLens;
	}

	public NrRubricLense removeNrRubricLens(NrRubricLense nrRubricLens) {
		getNrRubricLenses().remove(nrRubricLens);
		nrRubricLens.setNrRubricTbl(null);

		return nrRubricLens;
	}

}