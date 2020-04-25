package com.hqyj.modules.test.service;

import java.util.List;

import com.hqyj.common.MyResult;
import com.hqyj.modules.test.pojo.Resource;

public interface ResourceService {

	List<Resource> selResourceAll();
	
	Resource selResourceByResourceId(String resourceId);
	
	MyResult<Resource> insResource(Resource resource);
	
	MyResult<Resource> updResource(Resource resource);
	
	MyResult<Object> delResource(String resourceId);
}
