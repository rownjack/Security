package com.ei41.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ei41.base.model.Resource;
import com.ei41.base.model.UserDataRights;

public interface UserResourceViewMapper {

	List<Resource> searchUserAccessRightList(@Param("userId") Long userId, @Param("orgId") Long orgId);

	List<Resource> searchUserMenuList(@Param("userId") Long userId, @Param("orgId") Long orgId);

	List<UserDataRights> searchDataRightsList(@Param("userId") Long userId, @Param("orgId") Long orgId, @Param("resourceUrl") String resourceUrl);

}
