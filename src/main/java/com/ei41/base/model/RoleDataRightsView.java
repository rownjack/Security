package com.ei41.base.model;

public class RoleDataRightsView {
	private Long resourceId;
	private String resourceName;
	private String resourceUrl;
	private Long parentResourceId;
	private Integer resourceType;
	private Integer sequence;
	private Integer rank;
	private Long roleResourcesId;
	private Long dataRightsId;
	private String rights;
	private String rightsDescription;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public Long getParentResourceId() {
		return parentResourceId;
	}

	public void setParentResourceId(Long parentResourceId) {
		this.parentResourceId = parentResourceId;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Long getRoleResourcesId() {
		return roleResourcesId;
	}

	public void setRoleResourcesId(Long roleResourcesId) {
		this.roleResourcesId = roleResourcesId;
	}



	public Long getDataRightsId() {
		return dataRightsId;
	}

	public void setDataRightsId(Long dataRightsId) {
		this.dataRightsId = dataRightsId;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getRightsDescription() {
		return rightsDescription;
	}

	public void setRightsDescription(String rightsDescription) {
		this.rightsDescription = rightsDescription;
	}

}
