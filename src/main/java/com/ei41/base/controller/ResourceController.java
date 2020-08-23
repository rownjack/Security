package com.ei41.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ei41.base.Service.ResourceService;
import com.ei41.base.Util.TreeNodeUtil;
import com.ei41.base.model.Resource;
import com.ei41.base.pageModel.TreeAttributes;
import com.ei41.base.pageModel.TreeNode;

@Controller
@RequestMapping("resource")
public class ResourceController {
	@Autowired
	ResourceService resourceService;

	@RequestMapping(value = "/resourceList", method = RequestMethod.GET)
	@ResponseBody
	public List<Resource> searchResourceList(@RequestParam(required = false) Long parentResourceId) {

		// System.out.println("searchResourceList");
		if (parentResourceId == null) {
			return resourceService.searchTotalResourceList();
		} else {
			return resourceService.searchSubResourceList(parentResourceId);

		}

	}

	@RequestMapping(value = "/searchMenuList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchMenuList() {

		// List<Resource> resource = resourceService.searchTotalResourceList();
		List<Resource> resource = resourceService.searchTotalMenuList();

		Map<String, Object> returnMenu = new HashMap<String, Object>();

		List<Map<String, Object>> menusList = TreeNodeUtil.bulidMenu(resource);

		returnMenu.put("menus", menusList);

		return returnMenu;
	}

	
	

	@RequestMapping(value = "/searchMiniMenuList", method = RequestMethod.GET)
	@ResponseBody
	public  List<Map<String, Object>> searchminiMenuList() {

		// List<Resource> resource = resourceService.searchTotalResourceList();
		List<Resource> resource = resourceService.searchTotalMenuList();

		Map<String, Object> returnMenu = new HashMap<String, Object>();

		List<Map<String, Object>> menusList = TreeNodeUtil.bulidMiniuiMenu(resource);

		returnMenu.put("menus", menusList);

		return menusList;
	}
	
	
	
	@RequestMapping(value = "/searchTreeList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> searchTreeList() {

		// System.out.println("searchTreeList");

		List<Resource> resources = resourceService.searchTotalResourceList();

		List<TreeNode> treelist = new ArrayList<TreeNode>();

		for (Resource item : resources) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(item.getResourceId());
			treeNode.setPid(item.getParentResourceId());
			treeNode.setText(item.getResourceName());
			TreeAttributes treeAttributes = new TreeAttributes();
			treeAttributes.setResourceType(item.getResourceType());
			treeAttributes.setResourceUrl(item.getResourceUrl());
			treeNode.setAttributs(treeAttributes);
			treelist.add(treeNode);
		}

		List<Map<String, Object>> treeList = TreeNodeUtil.bulidSimpleTree(treelist);

		return treeList;

	}

	@ResponseBody
	@RequestMapping(value = "/createResource", method = RequestMethod.POST, consumes = "application/json")
	public void createResource(@RequestBody List<Map<?, ?>> resourceList) {

		// System.out.println("createResource");

		for (Map<?, ?> temp : resourceList) {

			// Action action = new Action();

			Resource resource = new Resource();

			resource.setResourceName((String) temp.get("resourceName"));

			resource.setResourceUrl((String) temp.get("resourceUrl"));
			
			resource.setImgUrl((String) temp.get("imgUrl"));

			if (temp.get("parentResourceId") != null && !temp.get("parentResourceId").equals("")) {
				if (temp.get("parentResourceId") instanceof Number) {

					resource.setParentResourceId(((Number) temp.get("parentResourceId")).longValue());
				} else {

					resource.setParentResourceId(Long.parseLong((String) temp.get("parentResourceId")));
				}
			}

			if (temp.get("sequence") != null) {
				if (temp.get("sequence") instanceof Number) {
					resource.setSequence(((Number) temp.get("sequence")).intValue());
				} else {
					resource.setSequence(Integer.parseInt((String) temp.get("sequence")));

				}
			}

			if (temp.get("resourceType") != null) {
				if (temp.get("resourceType") instanceof Number) {
					resource.setResourceType(((Number) temp.get("resourceType")).intValue());
				} else {
					resource.setResourceType(Integer.parseInt((String) temp.get("resourceType")));

				}
			}

			resourceService.createResource(resource);
		}

	}

	@ResponseBody
	@RequestMapping(value = "/updateResource", method = RequestMethod.PUT, consumes = "application/json")
	public void updateResource(@RequestBody List<Map<?, ?>> resourceList) {

		// System.out.println("updateResource");

		for (Map<?, ?> temp : resourceList) {

			Resource resource = resourceService.searchResourceById(((Number) temp.get("resourceId")).longValue());

			resource.setResourceName((String) temp.get("resourceName"));

			resource.setResourceUrl((String) temp.get("resourceUrl"));
			
			resource.setImgUrl((String) temp.get("imgUrl"));

			if (temp.get("parentResourceId") != null && !temp.get("parentResourceId").equals("")) {
				if (temp.get("parentResourceId") instanceof Number) {
					resource.setParentResourceId(((Number) temp.get("parentResourceId")).longValue());
				} else {
					resource.setParentResourceId(Long.parseLong((String) temp.get("parentResourceId")));
				}
			}

			if (temp.get("sequence") != null) {
				if (temp.get("sequence") instanceof Number) {
					resource.setSequence(((Number) temp.get("sequence")).intValue());
				} else {
					resource.setSequence(Integer.parseInt((String) temp.get("sequence")));

				}
			}

			if (temp.get("resourceType") != null) {
				if (temp.get("resourceType") instanceof Number) {
					resource.setResourceType(((Number) temp.get("resourceType")).intValue());
				} else {
					resource.setResourceType(Integer.parseInt((String) temp.get("resourceType")));

				}
			}

			resourceService.updateResource(resource);
		}

	}

	@ResponseBody
	@RequestMapping(value = "/deleteResource", method = RequestMethod.DELETE, consumes = "application/json")
	public void delete(@RequestBody List<Number> resourceIdList) {
		// System.out.println("deleteres");
		for (Number resourceId : resourceIdList) {
			resourceService.deleteResource(resourceId.longValue());
		}
	}

}
