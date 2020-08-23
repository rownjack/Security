package com.ei41.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ei41.base.model.Role;
import com.ei41.base.model.UserRole;

public interface RoleMapper {

	List<Role> searchRoleList( @Param("keyword") String keyword,@Param("orgId") Long orgId);

	void createRole(Role role);

	void deleteRole(Long roleId);

	Role findRoleById(Long roleId);

	void updateRole(Role role);
	
	void addRoleMembers(UserRole userRole);

}
