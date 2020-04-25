package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.common.MyResult;
import com.hqyj.common.MyResult.MyEnum;
import com.hqyj.modules.test.mapper.ResourceMapper;
import com.hqyj.modules.test.mapper.ResourceMapper;
import com.hqyj.modules.test.pojo.Resource;
import com.hqyj.modules.test.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	public List<Resource> selResourceAll() {
		return resourceMapper.selResourceAll();
	}

	@Override
	public Resource selResourceByResourceId(String resourceId) {
		return resourceMapper.selResourceByResourceId(resourceId);
	}

	@Override
	public MyResult<Resource> insResource(Resource resource) {
		MyResult<Resource> result=null;
		try{
			resourceMapper.insResource(resource);
			result=new MyResult<Resource>(MyEnum.SUCCESS.status,"insert success",resource);
		}catch(Exception e){
			result=new MyResult<Resource>(MyEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MyResult<Resource> updResource(Resource resource) {
		MyResult<Resource> result=null;
		try{
			resourceMapper.updResource(resource);
			result=new MyResult<Resource>(MyEnum.SUCCESS.status,"update success",resource);
		}catch(Exception e){
			result=new MyResult<Resource>(MyEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MyResult<Object> delResource(String resourceId) {
		MyResult<Object> result=null;
		try{
			resourceMapper.delResource(resourceId);
			result=new MyResult<Object>(MyEnum.SUCCESS.status,"delete success");
		}catch(Exception e){
			result=new MyResult<Object>(MyEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
