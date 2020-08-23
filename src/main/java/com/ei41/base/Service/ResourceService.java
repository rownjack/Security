package com.ei41.base.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ei41.base.mapper.ResourceMapper;
import com.ei41.base.mapper.RoleResourceMapper;
import com.ei41.base.model.Resource;

@Service
public class ResourceService {

	@Autowired
	ResourceMapper resourceMapper;
	@Autowired
	RoleResourceMapper roleResourceMapper;

	public List<Resource> searchTotalResourceList() {
		return resourceMapper.searchTotalResourceList();
	}

	public List<Resource> searchSubResourceList(Long parentreSourceId) {
		return resourceMapper.searchSubResourceList(parentreSourceId);
	}

	public void createResource(Resource resource) {
		resourceMapper.createResource(resource);
	}

	public Resource searchResourceById(Long resourceId) {
		return resourceMapper.searchResourceById(resourceId);
	}

	public void updateResource(Resource resource) {
		resourceMapper.updateResource(resource);

	}

	public void deleteResource(Long resourceId) {
		resourceMapper.deleteResource(resourceId);
		roleResourceMapper.deleteResourceDependcy(resourceId);

	}

	public List<Resource> searchTotalMenuList() {
		// TODO Auto-generated method stub
		return resourceMapper.searchTotalMenuList();
	}

}
