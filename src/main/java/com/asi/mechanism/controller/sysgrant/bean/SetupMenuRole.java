package com.asi.mechanism.controller.sysgrant.bean;

import java.util.List;

/**
 * 
 * @author biruh
 *
 */
public class SetupMenuRole {
	private String roleId;
	private List<String> menuIdList;

	public String getRoleId() {
		return roleId;
	}

	public List<String> getMenuIdList() {
		return menuIdList;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}

}
