package com.ei41.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ei41.base.model.RoleResource;

public interface RoleResourceMapper {

	void deleteRoleDependcy(Long roleId);

	void addRoleResource(RoleResource roleResource);

	void deleteResourceDependcy(Long resourceId);
   
	RoleResource isResourceExist(@Param("roleId") Long roleId, @Param("resourceId") Long resourceId);

	void updateRoleResource(RoleResource roleResourcedb);

	 List<RoleResource> searchRoleResource(Long roleId);

	void deletRoleResourceById(Long id);
	
	RoleResource findRoleResourceById(Long id);
	
}
