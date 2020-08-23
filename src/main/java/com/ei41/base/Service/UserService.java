package com.ei41.base.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei41.base.mapper.UserMapper;
import com.ei41.base.mapper.UserResourceViewMapper;
import com.ei41.base.mapper.UserRoleMapper;
import com.ei41.base.model.AjaxResult;
import com.ei41.base.model.Resource;
import com.ei41.base.model.User;
import com.ei41.base.model.UserDataRights;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	UserResourceViewMapper userResourceViewMapper;

	public AjaxResult searchTotalTuserList() {

		List<User> list = userMapper.queryUserList();

		return new AjaxResult(list);
	}

	public List<User> searchUserList(Long deptId, String keyword,Long orgId) {
		// TODO Auto-generated method stub
		return userMapper.searchUserList(deptId, keyword,orgId);
	}

	public void createUser(User user) {
		userMapper.createUser(user);
	}

	public void deleteUser(Long userId) {
		userMapper.deleteUser(userId);
		userRoleMapper.deleteUserDependcy(userId);
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);

	}

	public User searchUserById(Long userId) {
		return userMapper.findUserById(userId);
	}

	public List<Resource> searchUserAccessRightList(Long userId, Long orgId) {
		return userResourceViewMapper.searchUserAccessRightList(userId,orgId);
	}

	public List<Resource> searchUserMenuList(Long userId,Long orgId) {
		User user =userMapper.findUserById(userId);
	
		return userResourceViewMapper.searchUserMenuList(userId,orgId);
	}

	public User searchUserByName(String userName,Long orgId) {
		// TODO Auto-generated method stub
		return userMapper.findUserByName(userName,orgId);
	}

	public List<UserDataRights> searcUserDataRights(Long userId, Long orgId, String resourceUrl) {
		// TODO Auto-generated method stub
		return userResourceViewMapper.searchDataRightsList(userId, orgId, resourceUrl);
	}

}
