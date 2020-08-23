package com.ei41.base.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei41.base.mapper.DepartmentMapper;
import com.ei41.base.model.Department;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	public List<Department> searchTotalDepartList(Long orgId) {

		List<Department> list = departmentMapper.queryList(orgId,orgId);
		
		return list;

	}

	public List<Department> searchSubDepartList(Long parentId,Long orgId) {
		List<Department> list = departmentMapper.querysubList(parentId,orgId);
		return list;
	}

	public Department findDepartById(Long deptId) {
		return departmentMapper.findDepartById(deptId);
	}

	public void updateDepartment(Department depart) {
		departmentMapper.updateDepartment(depart);
	}

	public void deleteDepartment(Number departId) {
		departmentMapper.deleteDepartment(departId);

	}

	public void createDepartment(Department depart) {
		departmentMapper.save(depart);

	}

}
