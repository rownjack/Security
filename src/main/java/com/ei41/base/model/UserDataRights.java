package com.ei41.base.model;

public class UserDataRights {
	private Long resourceId;
	private String resourceName;
	private String resourceUrl;
	private Long parentResourceId;
	private Integer resourceType;
	private Integer sequence;
	private Long userId;
	private String userName;
	private Long orgId;
	private String rights;
	private String rightsDescription;
	private Integer rank;

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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
