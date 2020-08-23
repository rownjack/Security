package com.ei41.base.pageModel;

import java.util.List;

import com.ei41.base.model.Resource;
import com.ei41.base.model.User;

public class UserInfo {
	private Long userId;
	private String userName;
	private String password;
	private String realName;
	private String email;
	private String telNumber;
	private Long deptId;
	private Long divisionId;
	private boolean deptAdmin;
	private boolean systemAdmin;
	private List<Resource> menuList;
	private List<Resource> accessRightList;

	UserInfo() {
	}

	UserInfo(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.realName = user.getRealName();
		this.email = user.getEmail();
		this.telNumber = user.getTelNumber();
		this.deptId = user.getDeptId();
		this.divisionId = user.getDivisionId();
		this.deptAdmin = user.isDeptAdmin();
		this.systemAdmin = user.isSystemAdmin();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

	public boolean isDeptAdmin() {
		return deptAdmin;
	}

	public void setDeptAdmin(boolean deptAdmin) {
		this.deptAdmin = deptAdmin;
	}

	public boolean isSystemAdmin() {
		return systemAdmin;
	}

	public void setSystemAdmin(boolean systemAdmin) {
		this.systemAdmin = systemAdmin;
	}

	public List<Resource> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Resource> menuList) {
		this.menuList = menuList;
	}


	public List<Resource> getAccessRightList() {
		return accessRightList;
	}

	public void setAccessRightList(List<Resource> accessRightList) {
		this.accessRightList = accessRightList;
	}
}
