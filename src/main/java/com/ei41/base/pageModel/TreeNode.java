package com.ei41.base.pageModel;

public class TreeNode {
	private Long id;
	private String text;
	private Long pid;
	private String icon;
	private Object attributs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Object getAttributs() {
		return attributs;
	}

	public void setAttributs(Object attributs) {
		this.attributs = attributs;
	}

}
