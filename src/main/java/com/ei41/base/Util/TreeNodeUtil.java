package com.ei41.base.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ei41.base.model.Resource;
import com.ei41.base.pageModel.TreeAttributes;
import com.ei41.base.pageModel.TreeNode;

public class TreeNodeUtil {

	public static List<Map<String, Object>> bulidMenu(List<Resource> resources) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		for (Resource item : resources) {

			if (item.getParentResourceId() == null || item.getParentResourceId() == 0) {
				// ParentId = 0 表示顶级【文档项】
				map = new HashMap<String, Object>();
				map.put("menuid", item.getResourceId());
				map.put("menuname", item.getResourceName());
				map.put("icon", "icon-sys");
				map.put("url", item.getResourceUrl());
				getSonMenuTree(map, resources);

				result.add(map);
			}
		}
		return result;
	}

	private static Map<String, Object> getSonMenuTree(Map<String, Object> map, List<Resource> itemList) {
		List<Map<String, Object>> sonList = new ArrayList<Map<String, Object>>();

		for (Resource item : itemList) {
			if (item.getParentResourceId() != null) {
				if (map.get("menuid").toString().equals(item.getParentResourceId().toString())) {

					Map<String, Object> sonMap = new HashMap<String, Object>();
					sonMap.put("menuid", item.getResourceId());
					sonMap.put("menuname", item.getResourceName());
					sonMap.put("icon", "icon-sys");
					sonMap.put("url", item.getResourceUrl());
					sonList.add(sonMap);
					getSonMenuTree(sonMap, itemList);
				}
			}
		}

		if (sonList.size() > 0) {
			map.put("menus", sonList);
		}
		return map;
	}

	public static List<Map<String, Object>> bulidSimpleTree(List<TreeNode> treelist) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		for (TreeNode item : treelist) {

			if (item.getPid() == null || item.getPid() == 0) {
				// ParentId = 0 表示顶级【文档项】
				map = new HashMap<String, Object>();
				map.put("id", item.getId());
				map.put("text", item.getText());
				map.put("icon", "icon-sys");

				if (item.getAttributs() != null) {
					map.put("attributs", item.getAttributs());
				}
				getSonSimpleTree(map, treelist);

				result.add(map);
			}
		}
		return result;

	}

	private static Map<String, Object> getSonSimpleTree(Map<String, Object> map, List<TreeNode> treelist) {

		List<Map<String, Object>> sonList = new ArrayList<Map<String, Object>>();

		for (TreeNode item : treelist) {
			if (item.getPid() != null) {
				if (map.get("id").toString().equals(item.getPid().toString())) {

					Map<String, Object> sonMap = new HashMap<String, Object>();
					sonMap.put("id", item.getId());
					sonMap.put("text", item.getText());
					sonMap.put("icon", "icon-sys");
					if (item.getAttributs() != null) {
						map.put("attributs", item.getAttributs());
					}
					sonList.add(sonMap);
					getSonSimpleTree(sonMap, treelist);
				}
			}
		}

		if (sonList.size() > 0) {
			map.put("children", sonList);
		}
		return map;
	}

	public static List<Map<String, Object>> bulidMiniuiMenu(List<Resource> resources) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		for (Resource item : resources) {

			if (item.getParentResourceId() == null || item.getParentResourceId() == 0) {
				// ParentId = 0 表示顶级【文档项】
				map = new HashMap<String, Object>();

				map.put("id", item.getResourceId().toString());
				map.put("text", item.getResourceName());

				if (item.getImgUrl() != null && !item.getImgUrl().equals("")) {
					map.put("iconCls", "fa " + item.getImgUrl());
				} else {
					map.put("iconCls", "fa fa-briefcase");
				}

				map.put("url", item.getResourceUrl());

				getMiniuiSonMenuTree(map, resources);
				result.add(map);
			}
		}

		return result;
	}

	private static List<Map<String, Object>> getMiniuiSonMenuTree(Map<String, Object> map, List<Resource> itemList) {
		List<Map<String, Object>> sonList = new ArrayList<Map<String, Object>>();
		for (Resource item : itemList) {
			if (item.getParentResourceId() != null) {
				if (map.get("id").toString().equals(item.getParentResourceId().toString())) {

					Map<String, Object> sonMap = new HashMap<String, Object>();
					sonMap.put("id", item.getResourceId().toString());
					sonMap.put("text", item.getResourceName());
					if (item.getImgUrl() != null && !item.getImgUrl().equals("")) {
						sonMap.put("iconCls", "fa " + item.getImgUrl());

					} else {
						sonMap.put("iconCls", "fa fa-briefcase");
					}
					sonMap.put("url", item.getResourceUrl());
					sonList.add(sonMap);
					getMiniuiSonMenuTree(sonMap, itemList);

				}
			}
		}

		if (sonList.size() > 0) {
			map.put("children", sonList);
		}

		return sonList;
	}

}
