package com.ei41.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ei41.base.Service.RoleService;
import com.ei41.base.Util.TreeNodeUtil;
import com.ei41.base.model.Resource;
import com.ei41.base.model.Role;
import com.ei41.base.model.RoleDataRightsView;
import com.ei41.base.model.User;
import com.ei41.base.pageModel.TreeAttributes;
import com.ei41.base.pageModel.TreeNode;

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	RoleService roleSerivce;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Role> searchRoleList(@RequestParam(required = false) String keyword) {
		System.out.println("SearchRoleList");
	   Long orgId = (long) 1;
		
		return roleSerivce.searchRoleList(keyword,orgId);
	}

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public void create(@RequestBody List<Map<?, ?>> roleList) {

		
		//orgId
		Long orgId = (long) 1;
		for (Map<?, ?> temp : roleList) {
			Role role = new Role();
			role.setRoleName((String) temp.get("roleName"));
			if (temp.get("allUser") instanceof String) {
				role.setAllUser(Boolean.parseBoolean((String) temp.get("allUser")));
			} else {
				role.setAllUser((Boolean) temp.get("allUser"));

			}
			role.setOrgId(orgId);
			roleSerivce.createRole(role);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "application/json")
	public void delete(@RequestBody List<Number> roleNumList) {

		for (Number roleNum : roleNumList) {
			roleSerivce.deleteRole(roleNum.longValue());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = "application/json")
	public void edit(@RequestBody List<Map<?, ?>> roleList) {

		for (Map<?, ?> temp : roleList) {
			Role role = roleSerivce.findRoleById(((Number) temp.get("roleId")).longValue());

			role.setRoleName((String) temp.get("roleName"));

			if (temp.get("orgId") instanceof Number) {
				role.setOrgId(((Number) temp.get("orgId")).longValue());

			} else {
				role.setOrgId(Long.parseLong((String) temp.get("orgId")));

			}

			if (temp.get("allUser") instanceof String) {
				role.setAllUser(Boolean.parseBoolean((String) temp.get("allUser")));
			} else {
				role.setAllUser((Boolean) temp.get("allUser"));

			}

			if (role.isAllUser()) {

				roleSerivce.deleteRoleDependcy(role.getRoleId());
			}

			roleSerivce.updateRole(role);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/member/{roleId}", method = RequestMethod.POST, consumes = "application/json")
	public void addRoleMembers(@PathVariable Long roleId, @RequestBody List<Number> userNumList) {

		List<Long> temp = new ArrayList<Long>();

		for (Number number : userNumList) {
			temp.add(number.longValue());
			// addRoleMembers
		}
		roleSerivce.addRoleMembers(roleId, temp);
	}

	@ResponseBody
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public List<User> searchRoleMemberList(@RequestParam(required = false) Long roleId) {

		return roleSerivce.searchRoleMembers(roleId);
	}

	@ResponseBody
	@RequestMapping(value = "/member/{roleId}", method = RequestMethod.DELETE, consumes = "application/json")
	public void removeRoleMembers(@PathVariable Long roleId, @RequestBody List<Number> userNumList) {

		List<Long> temp = new ArrayList<Long>();
		for (Number number : userNumList) {
			temp.add(number.longValue());
		}
		roleSerivce.removeRoleMembers(roleId, temp);
	}

	@ResponseBody
	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	public List<RoleDataRightsView> searchRoleResourceList(@RequestParam Long roleId, @RequestParam String tree) {

		if (tree.equals("true")) {
			// return roleSerivce.searchRoleResourceListTree(roleId);
			return roleSerivce.searchRoleResourceList(roleId);
		} else {
			return roleSerivce.searchRoleResourceLeaf(roleId);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/resourceTree", method = RequestMethod.GET)
	public List<Map<String, Object>> searchRoleResourceTree(@RequestParam(required = false) Long roleId) {

		List<RoleDataRightsView> resources = roleSerivce.searchRoleResourceList(roleId);

		List<TreeNode> treelist = new ArrayList<TreeNode>();

		for (RoleDataRightsView item : resources) {
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
	@RequestMapping(value = "/resource/{roleId}", method = RequestMethod.PUT)
	public void updateRoleResources(@PathVariable Long roleId, @RequestBody List<Number> actionNumList) {

		List<Long> temp = new ArrayList<Long>();
		for (Number number : actionNumList) {
			temp.add(number.longValue());
		}
		roleSerivce.updateRoleResources(roleId, temp);
	}

	@ResponseBody
	@RequestMapping(value = "/roleRightsLevel", method = RequestMethod.PUT)
	public void updateRoleRightsLevel(@RequestBody List<Map<?, ?>> roleRightsList) {

		for (Map<?, ?> temp : roleRightsList) {
			Integer rank = Integer.parseInt((String) temp.get("rank"));
			Long roleResourcesId = ((Number) temp.get("roleResourcesId")).longValue();
			roleSerivce.updateRoleRightsLevel(rank, roleResourcesId);
		}

	}

}
