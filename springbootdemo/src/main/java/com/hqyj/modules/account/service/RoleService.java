package com.hqyj.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.modules.account.pojo.Role;
import com.hqyj.modules.common.vo.Result;
import com.hqyj.modules.common.vo.SearchVo;

public interface RoleService {

	Result<Role> editRole(Role role);
	
	Result<Role> deleteRole(int roleId);
	
	PageInfo<Role> getRoles(SearchVo searchVo);
	
	List<Role> getRolesByUserId(int userId);
	
	List<Role> getRolesByResourceId(int resourceId);
	
	Role getRoleById(int roleId);
	
	List<Role> getRoles();
}
