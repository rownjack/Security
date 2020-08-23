package com.ei41.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ei41.base.Service.DepartmentService;
import com.ei41.base.Util.TreeNodeUtil;
import com.ei41.base.model.Department;
import com.ei41.base.model.Resource;
import com.ei41.base.pageModel.TreeAttributes;
import com.ei41.base.pageModel.TreeNode;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/deptList", method = RequestMethod.GET)
	@ResponseBody
	public List<Department> searchTotalDepartList(@RequestParam(required = false) Long parentDeptId) {

	Long orgId =(long)1;
		
		if (parentDeptId == null) {
			return departmentService.searchTotalDepartList(orgId);
		} else {
			return departmentService.searchSubDepartList(parentDeptId,orgId);

		}

	}

	@ResponseBody
	@RequestMapping(value = "/deleteDept", method = RequestMethod.DELETE, consumes = "application/json")
	public void delete(@RequestBody List<Number> departNumList) {

		for (Number departId : departNumList) {
			departmentService.deleteDepartment(departId.longValue());
		}

	}

	@ResponseBody
	@RequestMapping(value = "/createDept", method = RequestMethod.POST, consumes = "application/json")
	public void createDepartment(@RequestBody List<Map<?, ?>> departList) {
		for (Map<?, ?> temp : departList) {
			Department depart = new Department();

			depart.setDeptName((String) temp.get("deptName"));

			if (temp.get("parentDeptId") != null) {
				if (temp.get("parentDeptId") instanceof Number) {
					depart.setParentDeptId(((Number) temp.get("parentDeptId")).longValue());
				} else {
					depart.setParentDeptId(Long.parseLong((String) temp.get("parentDeptId")));
				}
			}

			depart.setDeptDN((String) temp.get("deptDN"));
			depart.setDeptCode((String) temp.get("deptCode"));
			depart.setSortField((String) temp.get("sortField"));
			departmentService.createDepartment(depart);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/editDept", method = RequestMethod.PUT, consumes = "application/json")
	public void edit(@RequestBody List<Map<?, ?>> departList) {
		for (Map<?, ?> temp : departList) {
			System.out.println("editDepartment");
			Department depart = departmentService.findDepartById(((Number) temp.get("deptId")).longValue());

			depart.setDeptName((String) temp.get("deptName"));
			depart.setDeptDN((String) temp.get("deptDN"));
			depart.setDeptCode((String) temp.get("deptCode"));
			depart.setSortField((String) temp.get("sortField"));

			departmentService.updateDepartment(depart);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deptTree", method = RequestMethod.GET)
	public List<Map<String, Object>> searchDepartTree(@RequestParam(required = false) Long parentDeptId) {

		System.out.println("searchRoleResourceTree");
		Long orgId =(long)1;
		
		
		List<Department> departments = departmentService.searchTotalDepartList(orgId);

		List<TreeNode> treelist = new ArrayList<TreeNode>();

		for (Department item : departments) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(item.getDeptId());
			treeNode.setPid(item.getParentDeptId());
			treeNode.setText(item.getDeptName());
			treelist.add(treeNode);
		}

		List<Map<String, Object>> treeList = TreeNodeUtil.bulidSimpleTree(treelist);

		return treeList;
	}

}
