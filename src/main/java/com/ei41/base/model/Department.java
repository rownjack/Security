package com.ei41.base.model;

public class Department {
	private Long deptId;
	private String deptDN;
	private String deptCode;
	private String deptName;
	private Long parentDeptId;
	private String sortField;

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptDN() {
		return deptDN;
	}

	public void setDeptDN(String deptDN) {
		this.deptDN = deptDN;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(Long parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

}
