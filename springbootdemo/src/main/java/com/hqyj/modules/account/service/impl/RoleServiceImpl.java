package com.hqyj.modules.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.modules.account.mapper.RoleMapper;
import com.hqyj.modules.account.pojo.Role;
import com.hqyj.modules.account.service.RoleService;
import com.hqyj.modules.common.vo.Result;
import com.hqyj.modules.common.vo.SearchVo;
import com.hqyj.modules.common.vo.Result.ResultStatus;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Override
	@Transactional
	public Result<Role> editRole(Role role) {
		if (role == null || StringUtils.isBlank(role.getRoleName())) {
			return new Result<Role>(ResultStatus.FAILED.status, "Role info is null");
		}
		
		if (role.getRoleId() > 0) {
			roleMapper.updateRole(role);
		} else {
			roleMapper.addRole(role);
		}
		
		return new Result<Role>(ResultStatus.SUCCESS.status, "success", role);
	}

	@Override
	@Transactional
	public Result<Role> deleteRole(int roleId) {
		roleMapper.deleteRole(roleId);
		return new Result<Role>(ResultStatus.SUCCESS.status, "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<Role> getRoles(SearchVo searchVo) {
		searchVo.initSearchVo(searchVo);
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(roleMapper.getRolesBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public List<Role> getRolesByUserId(int userId) {
		return roleMapper.getRolesByUserId(userId);
	}

	@Override
	public List<Role> getRolesByResourceId(int resourceId) {
		return roleMapper.getRolesByResourceId(resourceId);
	}

	@Override
	public Role getRoleById(int roleId) {
		return roleMapper.getRoleById(roleId);
	}

	@Override
	public List<Role> getRoles() {
		return roleMapper.getRoles();
	}

}
