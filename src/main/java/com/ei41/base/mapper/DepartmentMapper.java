package com.ei41.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ei41.base.model.Department;

public interface DepartmentMapper {

	List<Department> queryList(@Param("deptId") Long deptId, @Param("orgId") Long orgId);

	void save(Department depaetment);

	void batchDelete(Long[] ids);

	// void update(Department depart);

	List<Department> querysubList(@Param("parentDeptId") Long parentDeptId, @Param("orgId") Long orgId);

	Department findDepartById(Long deptId);

	void updateDepartment(Department depart);

	void deleteDepartment(Number departId);

	List<Department> findDepartmentLeaf(Long deptId);
}
