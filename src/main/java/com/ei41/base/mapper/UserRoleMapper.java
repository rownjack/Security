package com.ei41.base.mapper;

import java.util.List;

import com.ei41.base.model.UserRole;

public interface UserRoleMapper {

	void addRoleMembers(UserRole userRole);

	void removeRoleMembers(UserRole userRole);

	void deleteUserDependcy(Long userId);

	void deleteRoleDependcy(Long roleId);

	List<UserRole> isRoleMemberExist(UserRole userRole);

}
