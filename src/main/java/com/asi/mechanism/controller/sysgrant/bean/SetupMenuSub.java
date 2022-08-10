package com.asi.mechanism.controller.sysgrant.bean;

import java.util.List;

/**
 * 
 * @author biruh
 *
 */
public class SetupMenuSub {
	private String menuId;
	private String upperMenuId;
	private String menuName;
	private String menuFuncUrl;
	private String menuItemClass;
	private int sortNum;
	private String indexId;
	private List<SetupMenuSub> subMenuList;

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public List<SetupMenuSub> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<SetupMenuSub> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public String getMenuId() {
		return menuId;
	}

	public String getUpperMenuId() {
		return upperMenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getMenuFuncUrl() {
		return menuFuncUrl;
	}

	public String getMenuItemClass() {
		return menuItemClass;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setMenuFuncUrl(String menuFuncUrl) {
		this.menuFuncUrl = menuFuncUrl;
	}

	public void setMenuItemClass(String menuItemClass) {
		this.menuItemClass = menuItemClass;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

}