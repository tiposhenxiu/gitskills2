package jp.co.sraw.dto;

import java.io.Serializable;
import java.util.List;


public class MenuDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer menuId;
	private String menuName;
	private String menuNameEn;
	private String menuNameAbbr;
	private String menuNameAbbrEn;
	private Integer menuLevel;
	private String actionUrl;
	private Integer menuDispOrder;

	// 親
	private Integer oyaMenuId;
	private String oyaMenuName;
	private String oyaMenuNameEn;
	private String oyaMenuNameAbbr;
	private String oyaMenuNameAbbrEn;
	private Integer oyaMenuLevel;
	private Integer oyaMenuDispOrder;

	//サブメニュー一覧
	private List<MenuDto> subMenuList;


	public MenuDto() {
		super();
	}

	public MenuDto(Integer menuId, String menuName, String menuNameEn, String menuNameAbbr, String menuNameAbbrEn, Integer menuLevel, String actionUrl, Integer menuDispOrder,
			Integer oyaMenuId, String oyaMenuName, String oyaMenuNameEn, String oyaMenuNameAbbr, String oyaMenuNameAbbrEn, Integer oyaMenuLevel, Integer oyaMenuDispOrder){
		super();
		//自
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuNameEn = menuNameEn;
		this.menuNameAbbr = menuNameAbbr;
		this.menuNameAbbrEn = menuNameAbbrEn;
		this.menuLevel = menuLevel;
		this.actionUrl = actionUrl;
		this.menuDispOrder = menuDispOrder;

		//親
		this.oyaMenuId = oyaMenuId;
		this.oyaMenuName = oyaMenuName;
		this.oyaMenuNameEn = oyaMenuNameEn;
		this.oyaMenuNameAbbr = oyaMenuNameAbbr;
		this.oyaMenuNameAbbrEn = oyaMenuNameAbbrEn;
		this.oyaMenuLevel = oyaMenuLevel;
		this.oyaMenuDispOrder = oyaMenuDispOrder;

	}

	/**
	 * @return menuId
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId セットする menuId
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return actionUrl
	 */
	public String getActionUrl() {
		return actionUrl;
	}

	/**
	 * @param actionUrl セットする actionUrl
	 */
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	/**
	 * @return menuDispOrder
	 */
	public Integer getMenuDispOrder() {
		return menuDispOrder;
	}

	/**
	 * @param menuDispOrder セットする menuDispOrder
	 */
	public void setMenuDispOrder(Integer menuDispOrder) {
		this.menuDispOrder = menuDispOrder;
	}

	/**
	 * @return menuNameAbbrEn
	 */
	public String getMenuNameAbbrEn() {
		return menuNameAbbrEn;
	}

	/**
	 * @param menuNameAbbrEn セットする menuNameAbbrEn
	 */
	public void setMenuNameAbbrEn(String menuNameAbbrEn) {
		this.menuNameAbbrEn = menuNameAbbrEn;
	}

	/**
	 * @return menuNameAbbr
	 */
	public String getMenuNameAbbr() {
		return menuNameAbbr;
	}

	/**
	 * @param menuNameAbbr セットする menuNameAbbr
	 */
	public void setMenuNameAbbr(String menuNameAbbr) {
		this.menuNameAbbr = menuNameAbbr;
	}

	/**
	 * @return menuNameEn
	 */
	public String getMenuNameEn() {
		return menuNameEn;
	}

	/**
	 * @param menuNameEn セットする menuNameEn
	 */
	public void setMenuNameEn(String menuNameEn) {
		this.menuNameEn = menuNameEn;
	}

	/**
	 * @return menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName セットする menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return oyaMenuId
	 */
	public Integer getOyaMenuId() {
		return oyaMenuId;
	}

	/**
	 * @param oyaMenuId セットする oyaMenuId
	 */
	public void setOyaMenuId(Integer oyaMenuId) {
		this.oyaMenuId = oyaMenuId;
	}

	/**
	 * @return oyaMenuDispOrder
	 */
	public Integer getOyaMenuDispOrder() {
		return oyaMenuDispOrder;
	}

	/**
	 * @param oyaMenuDispOrder セットする oyaMenuDispOrder
	 */
	public void setOyaMenuDispOrder(Integer oyaMenuDispOrder) {
		this.oyaMenuDispOrder = oyaMenuDispOrder;
	}

	/**
	 * @return oyaMenuNameAbbrEn
	 */
	public String getOyaMenuNameAbbrEn() {
		return oyaMenuNameAbbrEn;
	}

	/**
	 * @param oyaMenuNameAbbrEn セットする oyaMenuNameAbbrEn
	 */
	public void setOyaMenuNameAbbrEn(String oyaMenuNameAbbrEn) {
		this.oyaMenuNameAbbrEn = oyaMenuNameAbbrEn;
	}

	/**
	 * @return oyaMenuNameAbbr
	 */
	public String getOyaMenuNameAbbr() {
		return oyaMenuNameAbbr;
	}

	/**
	 * @param oyaMenuNameAbbr セットする oyaMenuNameAbbr
	 */
	public void setOyaMenuNameAbbr(String oyaMenuNameAbbr) {
		this.oyaMenuNameAbbr = oyaMenuNameAbbr;
	}

	/**
	 * @return oyaMenuNameEn
	 */
	public String getOyaMenuNameEn() {
		return oyaMenuNameEn;
	}

	/**
	 * @param oyaMenuNameEn セットする oyaMenuNameEn
	 */
	public void setOyaMenuNameEn(String oyaMenuNameEn) {
		this.oyaMenuNameEn = oyaMenuNameEn;
	}

	/**
	 * @return oyaMenuName
	 */
	public String getOyaMenuName() {
		return oyaMenuName;
	}

	/**
	 * @param oyaMenuName セットする oyaMenuName
	 */
	public void setOyaMenuName(String oyaMenuName) {
		this.oyaMenuName = oyaMenuName;
	}

	/**
	 * @return subMenuListl
	 */
	public List<MenuDto> getSubMenuList() {
		return subMenuList;
	}

	/**
	 * @param subMenuList セットする subMenuList
	 */
	public void setSubMenuList(List<MenuDto> subMenuList) {
		this.subMenuList = subMenuList;
	}

	/**
	 * @return menuLevel
	 */
	public Integer getMenuLevel() {
		return menuLevel;
	}

	/**
	 * @param menuLevel セットする menuLevel
	 */
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	/**
	 * @return oyaMenuLevel
	 */
	public Integer getOyaMenuLevel() {
		return oyaMenuLevel;
	}

	/**
	 * @param oyaMenuLevel セットする oyaMenuLevel
	 */
	public void setOyaMenuLevel(Integer oyaMenuLevel) {
		this.oyaMenuLevel = oyaMenuLevel;
	}


}
