package com.ei41.base.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei41.base.mapper.DataRightsMapper;
import com.ei41.base.mapper.ResourceMapper;
import com.ei41.base.mapper.RoleMapper;
import com.ei41.base.mapper.RoleResourceMapper;
import com.ei41.base.mapper.UserMapper;
import com.ei41.base.mapper.UserRoleMapper;
import com.ei41.base.model.Resource;
import com.ei41.base.model.Role;
import com.ei41.base.model.RoleDataRightsView;
import com.ei41.base.model.RoleResource;
import com.ei41.base.model.User;
import com.ei41.base.model.UserRole;

@Service
public class RoleService {
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	ResourceMapper resourceMapper;
	@Autowired
	RoleResourceMapper roleResourceMapper;
	@Autowired
	DataRightsMapper dataRightsMapper;

	public List<Role> searchRoleList(String keyword,Long orgId) {
		return roleMapper.searchRoleList(keyword,orgId);
	}

	public void createRole(Role role) {

		roleMapper.createRole(role);
	}

	public void deleteRole(Long roleId) {
		roleMapper.deleteRole(roleId);
		userRoleMapper.deleteRoleDependcy(roleId);
		roleResourceMapper.deleteRoleDependcy(roleId);
	}

	public void deleteRoleDependcy(Long roleId) {
		userRoleMapper.deleteRoleDependcy(roleId);
	}

	public Role findRoleById(Long roleId) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleById(roleId);
	}

	public void updateRole(Role role) {
		roleMapper.updateRole(role);

	}

	public void addRoleMembers(Long roleId, List<Long> userIdList) {

		for (Long userId : userIdList) {

			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);

			List<UserRole> userRoleList = userRoleMapper.isRoleMemberExist(userRole);

			if (userRoleList.isEmpty()) {
				userRoleMapper.addRoleMembers(userRole);
			}
		}

	}

	public List<User> searchRoleMembers(Long roleId) {

		return userMapper.searchRoleMembers(roleId);
	}

	public void removeRoleMembers(Long roleId, List<Long> userIdList) {

		for (Long userId : userIdList) {

			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);

			userRoleMapper.removeRoleMembers(userRole);

		}

	}

	// public List<Resource> searchRoleResourceListTree(Long roleId) {
	// // TODO Auto-generated method stub
	//
	// List<Resource> resources = searchRoleResourceList(roleId);
	//
	// Set<Resource> resourceSet = new HashSet<Resource>();
	//
	// for (Resource resource : resources) {
	//
	// System.out.println(resource.getResourceName());
	// while (resource != null) {
	// resourceSet.add(resource);
	// resource = resourceMapper.searchResourceById(resource.getParentResourceId());
	// }
	// }
	//
	// Resource[] resourceArray = resourceSet.toArray(new Resource[0]);
	// Arrays.sort(resourceArray, new Comparator<Resource>() {
	// @Override
	// public int compare(Resource o1, Resource o2) {
	// return (int) (o1.getResourceId() - o2.getResourceId());
	// }
	// });
	// return Arrays.asList(resourceArray);
	//
	// // return resourceMapper.searchRoleResourceList(roleId);
	// }

	public List<RoleDataRightsView> searchRoleResourceList(Long roleId) {

		return resourceMapper.searchRoleResourceList(roleId);
	}

	public void updateRoleResources(Long roleId, List<Long> temp) {

		// roleResourceMapper.deleteRoleDependcy(roleId);

		List<RoleResource> roleResourceList = roleResourceMapper.searchRoleResource(roleId);

		List<Long> deleteList = new ArrayList<Long>();

		boolean isexist;

		// 将界面获取的数据和数据库中做对比界面中没有勾选但数据库中有的删除，数据库中有并且有勾选更新，数据库中没有有勾选的添加。

		for (RoleResource roleResource : roleResourceList) {

			isexist = false;

			for (Long resourceId : temp) {

				if (resourceId == roleResource.getResourceId()) {
					isexist = true;
				}
			}

			if (!isexist) {
				deleteList.add(roleResource.getId());
				dataRightsMapper.delteDataRights(roleResource.getDataRightsId());
			}

		}

		// 先删除
		for (Long id : deleteList) {

			roleResourceMapper.deletRoleResourceById(id);

		}

		for (Long resourceId : temp) {

			RoleResource roleResource = new RoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(resourceId);
			roleResource.setDataRightsId((long) 0);
			roleResource.setRank(1);

			RoleResource roleResourcedb = roleResourceMapper.isResourceExist(roleId, resourceId);

			if (roleResourcedb != null) {
				roleResourceMapper.updateRoleResource(roleResourcedb);
			} else {
				roleResourceMapper.addRoleResource(roleResource);
			}

		}

	}

	public void updateRoleRightsLevel(Integer rank, Long roleResourcesId) {

		RoleResource roleResource = roleResourceMapper.findRoleResourceById(roleResourcesId);
		if (roleResource != null) {
			roleResource.setRank(rank);
			dataRightsMapper.delteDataRights(roleResource.getDataRightsId());
			roleResource.setDataRightsId((long) 0);
			roleResourceMapper.updateRoleResource(roleResource);

		}

	}

	public List<RoleDataRightsView> searchRoleResourceLeaf(Long roleId) {
		// TODO Auto-generated method stub
		return resourceMapper.searchRoleResourceLeaf(roleId);
	}

}
