package com.ei41.base.model;

/**
 * 角色资源映射表
 */

public class RoleResource {
	private Long id;
	private Long roleId;
	private Integer rank;
	private Long dataRightsId;
	private Long resourceId;

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Long getDataRightsId() {
		return dataRightsId;
	}

	public void setDataRightsId(Long dataRightsId) {
		this.dataRightsId = dataRightsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
