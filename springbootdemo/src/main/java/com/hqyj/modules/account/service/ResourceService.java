package com.hqyj.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.common.Result;
import com.hqyj.common.SearchVo;
import com.hqyj.modules.account.pojo.Resource;

public interface ResourceService {


	Result<Resource> editResource(Resource resource);
	
	Result<Resource> deleteResource(int resourceId);
	
	PageInfo<Resource> getResources(SearchVo searchVo);
	
	List<Resource> getResourcesByRoleId(int roleId);
	
	Resource getResourceById(int resourceId);
}
