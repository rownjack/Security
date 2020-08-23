package com.ei41.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ei41.base.Service.DataRightsService;
import com.ei41.base.Service.UserService;
import com.ei41.base.model.DataRights;
import com.ei41.base.model.Department;
import com.ei41.base.model.User;
import com.ei41.base.model.UserDataRights;
import com.ei41.mybatis.search.condition.DataRightsConditionAdapter;

@Controller
@RequestMapping("dataRights")
public class DataRightsController {

	@Autowired
	DataRightsService dataRightsService;

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping(value = "/dataRightsDefine", method = RequestMethod.PUT, consumes = "application/json")
	public void DepartDataRights(@RequestBody List<Map<?, ?>> dataRightsList) {
		String rights = "";
		String rightsDescription = "";
		Long dataRightsId = (long) 0;
		// var id = ids.join(',');
		Long roleResourcesId = (long) 0;

		for (Map<?, ?> temp : dataRightsList) {
			rights = rights.concat(((Number) temp.get("rights")).toString()).concat(";");
			rightsDescription = rightsDescription.concat((String) temp.get("rightsDescription")).concat(";");
			dataRightsId = ((Number) temp.get("dataRightsId")).longValue();
			roleResourcesId = ((Number) temp.get("roleResourcesId")).longValue();
			// roleSerivce.updateRoleRightsLevel(rank,roleResourcesId);
		}

		// System.out.println(rightsDescription);

		if (dataRightsList.size() > 0) {

			if (dataRightsId != 0) {

				DataRights dataRights = dataRightsService.findDataRightsById(dataRightsId);
				dataRights.setRights(rights);
				dataRights.setRightsDescription(rightsDescription);
				dataRightsService.updateDataRights(dataRights);

			} else {
				DataRights dataRights = new DataRights();
				dataRights.setRights(rights);
				dataRights.setRightsDescription(rightsDescription);
				dataRightsService.createDataRights(dataRights, roleResourcesId);

			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public List<Department> searchDepartmentchosen(@RequestParam Long dataRightsId, @RequestParam String tree) {
		return dataRightsService.searchDepartmentLeaf(dataRightsId);
	}

	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> searchUserchosen(@RequestParam Long dataRightsId) {
		return dataRightsService.searchUserchosen(dataRightsId);
	}

	@ResponseBody
	@RequestMapping(value = "/definDataRightsCondition", method = RequestMethod.POST)
	public Object defineDataRightsCondition(@RequestBody Map<?, ?> dataCondition) {

//		CustomUserDetails customUserDetails = UserDetailsUtil.extractUserDetails(authentication);
		Long orgId =(long)1;
		User user = userService.searchUserByName("luoyao", orgId);

		String conditionString = (String) dataCondition.get("actionString");

		DataRightsConditionAdapter datacondition = (DataRightsConditionAdapter) dataCondition.get("action");

		List<UserDataRights> userDataRightsList = userService.searcUserDataRights(user.getUserId(), orgId, conditionString);

		return setTheDatarights(user, datacondition, userDataRightsList);
	}

	 Object setTheDatarights(User user, DataRightsConditionAdapter condition, List<UserDataRights> userDataRightsList) {

		List<Long> createrList = new ArrayList<Long>();
		List<Long> deptmentList = new ArrayList<Long>();
		List<Long> orgIdList = new ArrayList<Long>();

		for (UserDataRights userDataRights : userDataRightsList) {

			switch (userDataRights.getRank().intValue()) {
			case (1): {

				createrList.add(user.getUserId());
				condition.setCreatorList(createrList);
				System.out.println("in" + userDataRights.getRank());

				break;
			}
			case (2): {

				String userList = userDataRights.getRights();

				String[] userListArray = userList.split(";");

				for (int i = 0; i < userListArray.length; i++) {

					createrList.add(Long.parseLong(userListArray[i]));
					condition.setCreatorList(createrList);
				}

				System.out.println("in" + userDataRights.getRank());

				break;
			}
			case (3): {

				deptmentList.add(user.getDeptId());
				condition.setDepartIdList(deptmentList);

				System.out.println("in" + userDataRights.getRank());

				break;
			}
			case (4): {

				String deptList = userDataRights.getRights();

				String[] deptListArray = deptList.split(";");

				for (int i = 0; i < deptListArray.length; i++) {

					Long deptid = Long.parseLong(deptListArray[i]);

					deptmentList.add(deptid);
					condition.setDepartIdList(deptmentList);
				}

				System.out.println("in" + userDataRights.getRank());
				break;
			}
			case (5): {
				orgIdList.add(user.getOrgId());
				condition.setOrgIdList(orgIdList);

				System.out.println("in" + userDataRights.getRank());

				break;
			}
			case (6): {

				String orgList = userDataRights.getRights();

				String[] orgListArray = orgList.split(";");

				for (int i = 0; i < orgListArray.length; i++) {

					Long orgid = Long.parseLong(orgListArray[i]);

					orgIdList.add(orgid);
					condition.setOrgIdList(orgIdList);
				}

				System.out.println("in" + userDataRights.getRank());
				break;
			}
			default: {

				System.out.println("undefined Rank set person");
				break;
			}

			}

			// String[] rightsArray = rightsList.split(";");
		}

		if (createrList.isEmpty()) {
			createrList.add((long) 0);
			condition.setCreatorList(createrList);
		}

		return condition;

	}

}
