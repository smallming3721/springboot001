package com.hqyj.modules.test.service;

import java.util.List;


import com.hqyj.common.MyResult;
import com.hqyj.modules.test.pojo.Role;

public interface RoleService {

	List<Role> selRoleAll();
	
	Role selRoleByRoleId(String roleId);
	
	MyResult<Role> insRole(Role role);
	
	MyResult<Role> updRole(Role role);
	
	MyResult<Object> delRole(String roleId);
}
