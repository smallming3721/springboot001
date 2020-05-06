package com.hqyj.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.common.Result;
import com.hqyj.common.SearchVo;
import com.hqyj.modules.account.pojo.Role;

public interface RoleService {

	Result<Role> editRole(Role role);
	
	Result<Role> deleteRole(int roleId);
	
	PageInfo<Role> getRoles(SearchVo searchVo);
	
	List<Role> getRolesByUserId(int userId);
	
	List<Role> getRolesByResourceId(int resourceId);
	
	Role getRoleById(int roleId);
	
	List<Role> getRoles();
}
