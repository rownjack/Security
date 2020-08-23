package com.ei41.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ei41.base.Service.ResourceService;
import com.ei41.base.Service.UserService;
import com.ei41.base.Util.TreeNodeUtil;
import com.ei41.base.model.Resource;
import com.ei41.base.model.User;
import com.ei41.base.model.UserDataRights;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;

	//
	// @RequestMapping(value="/userList",method = RequestMethod.GET)
	// @ResponseBody
	// public AjaxResult searchTotalTuserList() {
	// return userService.searchTotalTuserList();
	// }
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	@ResponseBody
	public List<User> SearcUserList(@RequestParam(required = false) Long deptId, @RequestParam(required = false) String keyword) {
		//

		
		 Long orgId = (long) 1;
		return userService.searchUserList(deptId, keyword, orgId);
	}

	@RequestMapping(value = "/accessRightList", method = RequestMethod.GET)
	@ResponseBody
	public List<Resource> searchUserAccessRightList(@RequestParam(required = false) Long userId) {

		Long orgId = (long) 1;
	//	CustomUserDetails customUserDetails = UserDetailsUtil.extractUserDetails(authentication);
		return userService.searchUserAccessRightList(userId,orgId);
	}

	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchUserMenuList(@RequestParam(required = false) String userName) {

		// org

		Long orgId = (long) 1;
	// CustomUserDetails customUserDetails = UserDetailsUtil.extractUserDetails(authentication);
		User user = userService.searchUserByName(userName, orgId);

		List<Resource> resourcelist = new ArrayList<Resource>();

		if (user.isSystemAdmin()) {

			resourcelist = resourceService.searchTotalMenuList();

		} else {
			resourcelist = userService.searchUserMenuList(user.getUserId(),orgId);

		}

		Map<String, Object> returnMenu = new HashMap<String, Object>();

		List<Map<String, Object>> menusList = TreeNodeUtil.bulidMenu(resourcelist);

		returnMenu.put("menus", menusList);

		return returnMenu;

	}
	
	
	@RequestMapping(value = "/minimenuList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> searchUserMenuList1(@RequestParam(required = false) String userName) {

	//	System.out.println("userName"+userName);
		Long orgId = (long) 1;
		//CustomUserDetails customUserDetails = UserDetailsUtil.extractUserDetails(authentication);
		User user = userService.searchUserByName(userName, orgId);

		List<Resource> resourcelist = new ArrayList<Resource>();

		if (user.isSystemAdmin()) {

			resourcelist = resourceService.searchTotalMenuList();

		} else {
			resourcelist = userService.searchUserMenuList(user.getUserId(), orgId);

		}

	//	Map<String, Object> returnMenu = new HashMap<String, Object>();
//		List<Map<String, Object>> menusList = TreeNodeUtil.bulidMenu(resourcelist);
		List<Map<String, Object>> menusList = TreeNodeUtil.bulidMiniuiMenu(resourcelist);
	
		return menusList;

	}
	
	

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	@ResponseBody
	public User SearcUserInfoById(@RequestParam(required = false) Long userId) {

		return userService.searchUserById(userId);
	}

	@RequestMapping(value = "/userInfobyname", method = RequestMethod.GET)
	@ResponseBody
	public User SearcUserInfoByName(@RequestParam(required = false) String userName) {
	//	CustomUserDetails customUserDetails = UserDetailsUtil.extractUserDetails(authentication);

		Long orgId = (long) 1;
               System.out.println(orgId);
		return userService.searchUserByName(userName, orgId);

	}

	@RequestMapping(value = "/userDataRights", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDataRights> searcUserDataRights(@RequestParam(required = false) Long userId, @RequestParam(required = false) String resourceUrl) {

	Long orgId = (long) 1;
	//	CustomUserDetails customUserDetails = UserDetailsUtil.extractUserDetails(authentication);
		return userService.searcUserDataRights(userId,orgId, resourceUrl);

	}

	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST, consumes =
	// "application/json")
	// public void createContractBill(@RequestBody User user,
	// HttpServletRequest request) {
	//
	// userService.createUser(user);
	//
	// }

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public void create(@RequestBody List<Map<?, ?>> userList) {
		System.out.println("create");
		for (Map<?, ?> temp : userList) {
			User user = new User();

			user.setUserName((String) temp.get("userName"));
			user.setRealName((String) temp.get("realName"));
			user.setEmail((String) temp.get("email"));

			// if (temp.get("divisionId") instanceof Number) {
			// user.setDivisionId(((Number) temp.get("divisionId")).longValue());
			// } else {
			// user.setDivisionId(Long.parseLong((String) temp.get("divisionId")));
			// // user.setDivisionId(((Number) temp.get("divisionId")).longValue());
			//
			// }

			if (temp.get("divisionId") != null) {
				if (temp.get("divisionId") instanceof Number) {
					user.setDivisionId(((Number) temp.get("divisionId")).longValue());
				} else {
					user.setDivisionId(Long.parseLong((String) temp.get("divisionId")));
				}
			} else {
				user.setDivisionId(null);
			}

			if (temp.get("deptId") instanceof Number) {
				user.setDeptId(((Number) temp.get("deptId")).longValue());

			} else {
				user.setDeptId(Long.parseLong((String) temp.get("deptId")));

			}

			if (temp.get("systemAdmin") instanceof Number) {
				user.setSystemAdmin(((Number) temp.get("systemAdmin")).intValue() == 1);
			} else {
				user.setSystemAdmin(((Boolean) temp.get("systemAdmin")));

				// if (temp.get("systemAdmin").equals("1")) {
				// user.setSystemAdmin(true);
				// } else {
				//
				// user.setSystemAdmin(false);
				// }

			}

			if (temp.get("deptAdmin") instanceof Number) {
				user.setDeptAdmin(((Number) temp.get("deptAdmin")).intValue() == 1);
			} else {
				user.setSystemAdmin(((Boolean) temp.get("deptAdmin")));
				//
				// if (temp.get("deptAdmin").equals("1")) {
				// user.setDeptAdmin(true);
				// } else {
				//
				// user.setDeptAdmin(false);
				// }

			}

			if (temp.get("orgId") instanceof Number) {

				user.setOrgId(((Number) temp.get("orgId")).longValue());

			} else {

				user.setOrgId(Long.parseLong((String) temp.get("orgId")));

			}

			userService.createUser(user);

		}
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = "application/json")
	public void update(@RequestBody List<Map<?, ?>> userList) {
		System.out.println("update");

		for (Map<?, ?> temp : userList) {

			User user = userService.searchUserById(((Number) temp.get("userId")).longValue());

			// if (temp.get("divisionId") instanceof Number) {
			//
			// user.setDivisionId(((Number) temp.get("divisionId")).longValue());
			//
			// } else {
			// user.setDivisionId(Long.parseLong((String) temp.get("divisionId")));
			// // user.setDivisionId(((Number) temp.get("divisionId")).longValue());
			// }

			if (temp.get("divisionId") != null) {
				if (temp.get("divisionId") instanceof Number) {
					user.setDivisionId(((Number) temp.get("divisionId")).longValue());
				} else {
					user.setDivisionId(Long.parseLong((String) temp.get("divisionId")));
				}
			} else {
				user.setDivisionId(null);
			}

			user.setUserName((String) temp.get("userName"));
			user.setRealName((String) temp.get("realName"));
			user.setEmail((String) temp.get("email"));

			if (temp.get("deptId") instanceof Number) {

				user.setDeptId(((Number) temp.get("deptId")).longValue());

			} else {

				user.setDeptId(Long.parseLong((String) temp.get("deptId")));

			}

			if (temp.get("systemAdmin") instanceof Number) {
				user.setSystemAdmin(((Number) temp.get("systemAdmin")).intValue() == 1);
			} else {

				user.setSystemAdmin(((Boolean) temp.get("systemAdmin")));

				// if (temp.get("systemAdmin").equals("1")) {
				// user.setSystemAdmin(true);
				// } else {
				//
				// user.setSystemAdmin(false);
				// }
			}

			if (temp.get("deptAdmin") instanceof Number) {

				user.setDeptAdmin(((Number) temp.get("deptAdmin")).intValue() == 1);

			} else {

				user.setDeptAdmin(((Boolean) temp.get("deptAdmin")));

				// if (temp.get("deptAdmin").equals("1")) {
				// user.setDeptAdmin(true);
				// } else {
				//
				// user.setDeptAdmin(false);
				// }

			}

			if (temp.get("orgId") instanceof Number) {

				user.setOrgId(((Number) temp.get("orgId")).longValue());

			} else {

				user.setOrgId(Long.parseLong((String) temp.get("orgId")));

			}

			userService.updateUser(user);
		}

	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "application/json")
	public void deleteUser(@RequestBody List<Number> userList) {

		for (Number userId : userList) {
			userService.deleteUser(userId.longValue());
		}
	}

}
