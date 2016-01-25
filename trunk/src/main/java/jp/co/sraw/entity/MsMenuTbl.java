package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the ms_menu_tbl database table.
 *
 */
@Entity
@Table(name="ms_menu_tbl")
@NamedQuery(name="MsMenuTbl.findAll", query="SELECT m FROM MsMenuTbl m")
public class MsMenuTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="menu_id")
	private Integer menuId;

	@Column(name="action_url")
	private String actionUrl;

	@Column(name="menu_disp_order")
	private Integer menuDispOrder;

	@Column(name="menu_level")
	private Integer menuLevel;

	@Column(name="menu_name")
	private String menuName;

	@Column(name="menu_name_abbr")
	private String menuNameAbbr;

	@Column(name="menu_name_abbr_en")
	private String menuNameAbbrEn;

	@Column(name="menu_name_en")
	private String menuNameEn;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to MsMenuTbl
	@ManyToOne
	@JoinColumn(name="oya_menu_id")
	private MsMenuTbl msMenuTbl;

	//bi-directional many-to-one association to MsMenuTbl
	@OneToMany(mappedBy="msMenuTbl")
	private List<MsMenuTbl> msMenuTbls;

	public MsMenuTbl() {
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public Integer getMenuDispOrder() {
		return this.menuDispOrder;
	}

	public void setMenuDispOrder(Integer menuDispOrder) {
		this.menuDispOrder = menuDispOrder;
	}

	public Integer getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuNameAbbr() {
		return this.menuNameAbbr;
	}

	public void setMenuNameAbbr(String menuNameAbbr) {
		this.menuNameAbbr = menuNameAbbr;
	}

	public String getMenuNameAbbrEn() {
		return this.menuNameAbbrEn;
	}

	public void setMenuNameAbbrEn(String menuNameAbbrEn) {
		this.menuNameAbbrEn = menuNameAbbrEn;
	}

	public String getMenuNameEn() {
		return this.menuNameEn;
	}

	public void setMenuNameEn(String menuNameEn) {
		this.menuNameEn = menuNameEn;
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

	public MsMenuTbl getMsMenuTbl() {
		return this.msMenuTbl;
	}

	public void setMsMenuTbl(MsMenuTbl msMenuTbl) {
		this.msMenuTbl = msMenuTbl;
	}

	public List<MsMenuTbl> getMsMenuTbls() {
		return this.msMenuTbls;
	}

	public void setMsMenuTbls(List<MsMenuTbl> msMenuTbls) {
		this.msMenuTbls = msMenuTbls;
	}

	public MsMenuTbl addMsMenuTbl(MsMenuTbl msMenuTbl) {
		getMsMenuTbls().add(msMenuTbl);
		msMenuTbl.setMsMenuTbl(this);

		return msMenuTbl;
	}

	public MsMenuTbl removeMsMenuTbl(MsMenuTbl msMenuTbl) {
		getMsMenuTbls().remove(msMenuTbl);
		msMenuTbl.setMsMenuTbl(null);

		return msMenuTbl;
	}

}