package com.hqyj.modules.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.common.Result;
import com.hqyj.common.Result.ResultStatus;
import com.hqyj.common.SearchVo;
import com.hqyj.modules.account.mapper.ResourceMapper;
import com.hqyj.modules.account.mapper.RoleResourceMapper;
import com.hqyj.modules.account.pojo.Resource;
import com.hqyj.modules.account.pojo.Role;
import com.hqyj.modules.account.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	@Transactional
	public Result<Resource> editResource(Resource resource) {
		if (resource == null) {
			return new Result<Resource>(500, "resource info is null");
		}

		// 添加 resource
		if (resource.getResourceId() > 0) {
			resourceMapper.updateResource(resource);
		} else {
			resourceMapper.addResource(resource);
		}

		// 添加 roleResource
		roleResourceMapper.deletRoleResourceByResourceId(resource.getResourceId());
		if (resource.getRoles() != null && !resource.getRoles().isEmpty()) {
			for (Role role : resource.getRoles()) {
				roleResourceMapper.addRoleResource(role.getRoleId(), resource.getResourceId());
			}
		}

		return new Result<Resource>(200, "success", resource);
	}

	@Override
	@Transactional
	public Result<Resource> deleteResource(int resourceId) {
		roleResourceMapper.deletRoleResourceByResourceId(resourceId);
		resourceMapper.deleteResource(resourceId);
		return new Result<Resource>(ResultStatus.SUCCESS.status, "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<Resource> getResources(SearchVo searchVo) {
		searchVo.initSearchVo(searchVo);
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(resourceMapper.getResourcesBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public List<Resource> getResourcesByRoleId(int roleId) {
		return resourceMapper.getResourcesByRoleId(roleId);
	}

	@Override
	public Resource getResourceById(int resourceId) {
		return resourceMapper.getResourceById(resourceId);
	}

}
