package com.ei41.base.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei41.base.mapper.DataRightsMapper;
import com.ei41.base.mapper.DepartmentMapper;
import com.ei41.base.mapper.RoleResourceMapper;
import com.ei41.base.mapper.UserMapper;
import com.ei41.base.model.DataRights;
import com.ei41.base.model.Department;
import com.ei41.base.model.RoleResource;
import com.ei41.base.model.User;

@Service
public class DataRightsService {

	@Autowired
	DataRightsMapper dataRightsMapper;

	@Autowired
	RoleResourceMapper roleResourceMapper;

	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	UserMapper userMapper;

	public DataRights findDataRightsById(Long dataRightsId) {
		// TODO Auto-generated method stub
		return dataRightsMapper.findDataRightsById(dataRightsId);
	}

	public void updateDataRights(DataRights dataRights) {
		dataRightsMapper.updateDataRights(dataRights);

	}

	public void createDataRights(DataRights dataRights, Long roleResourcesId) {
		// TODO Auto-generated method stub
		RoleResource roleResource = roleResourceMapper.findRoleResourceById(roleResourcesId);

		dataRightsMapper.createDataRights(dataRights);

		// System.out.println("newId="+ dataRights.getId());
		roleResource.setDataRightsId(dataRights.getId());

		roleResourceMapper.updateRoleResource(roleResource);
	}

	public List<Department> searchDepartmentLeaf(Long dataRightsId) {
		// TODO Auto-generated method stub
		DataRights dataRights = dataRightsMapper.findDataRightsById(dataRightsId);

		// System.out.println(rightsList);

		List<Department> departmentList = new ArrayList<Department>();

		if (dataRights != null) {
			String rightsList = dataRights.getRights();

			String[] rightsArray = rightsList.split(";");

			for (int i = 0; i < rightsArray.length; i++) {

				Long deptId = Long.parseLong(rightsArray[i]);

				if (departmentMapper.findDepartmentLeaf(deptId).size() == 0) {
					Department department = departmentMapper.findDepartById(deptId);
					departmentList.add(department);
				}

			}
		}
		return departmentList;
	}

	public List<User> searchUserchosen(Long dataRightsId) {
		// TODO Auto-generated method stub
		DataRights dataRights = dataRightsMapper.findDataRightsById(dataRightsId);

		// System.out.println(rightsList);

		List<User> userList = new ArrayList<User>();

		if (dataRights != null) {
			String rightsList = dataRights.getRights();

			String[] rightsArray = rightsList.split(";");

			for (int i = 0; i < rightsArray.length; i++) {

				Long userId = Long.parseLong(rightsArray[i]);
				User user = userMapper.findUserById(userId);
				userList.add(user);
			}
		}

		return userList;
	}

}
