package com.ei41.base.model;

public class Role {
	private Long roleId;
	private String roleName;
	private Long orgId;
	private boolean allUser;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isAllUser() {
		return allUser;
	}

	public void setAllUser(boolean allUser) {
		this.allUser = allUser;
	}







}
