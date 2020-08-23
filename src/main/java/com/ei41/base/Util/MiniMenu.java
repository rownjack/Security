package com.ei41.base.Util;

public class MiniMenu {
	private Long id;
	private String text;
	private String iconCls;
	private String url;
    private MiniMenu children;
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public MiniMenu getChildren() {
		return children;
	}
	public void setChildren(MiniMenu children) {
		this.children = children;
	}

    
    
}
