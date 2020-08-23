package com.ei41.base.mapper;

import java.util.List;

import com.ei41.base.model.Resource;
import com.ei41.base.model.RoleDataRightsView;

public interface ResourceMapper {

	List<Resource> searchTotalResourceList();

	List<Resource> searchSubResourceList(Long parentreSourceId);

	void createResource(Resource resource);

	Resource searchResourceById(Long resourceId);

	void updateResource(Resource resource);

	void deleteResource(Long resourceId);

	List<RoleDataRightsView> searchRoleResourceList(Long roleId);

	List<RoleDataRightsView> searchRoleResourceLeaf(Long roleId);
	
	List<Resource> searchTotalMenuList();
}
