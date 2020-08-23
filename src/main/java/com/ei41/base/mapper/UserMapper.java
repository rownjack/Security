package com.ei41.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ei41.base.model.User;

public interface UserMapper {
	List<User> queryUserList();

	User findUserById(Long userId);

	void updateUser(User user);

	// List<User> queryUserListByDept(Long deptId,String);

	void createUser(User user);

	void deleteUser(Long userId);

	List<User> searchUserList(@Param("deptId") Long deptId, @Param("keyword") String keyword,@Param("orgId") Long orgId);

	List<User> searchRoleMembers(Long roleId);

	User findUserByName(@Param("userName")String userName,@Param("orgId") Long orgId);

}
