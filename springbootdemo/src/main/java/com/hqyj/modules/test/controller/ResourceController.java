package com.hqyj.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.common.MyResult;
import com.hqyj.modules.test.pojo.Resource;
import com.hqyj.modules.test.service.ResourceService;

@RestController
@RequestMapping("/api")
public class ResourceController {

	@Autowired
	private ResourceService resorceServiceImpl;
	
	/**
	 * 127.0.0.1/api/resources
	 */
	@RequestMapping("/resources")
	public List<Resource> getResourceAll(){
		return resorceServiceImpl.selResourceAll();
	}
	
	/**
	 * 127.0.0.1/api/resource?resourceId=1
	 */
	@RequestMapping("/resource")
	public Resource getResourceByResourceId(String resourceId){
		return resorceServiceImpl.selResourceByResourceId(resourceId);
	}
	
	/**
	 * 127.0.0.1/api/resource
	 * {"resourceUri":"/image/p2","resourceName":"图2","permission":"不会写"}
	 */
	@PostMapping(value="/resource",consumes="application/json")
	public MyResult<Resource> insResource(@RequestBody Resource resource){
		return resorceServiceImpl.insResource(resource);
	}
	
	/**
	 * 127.0.0.1/api/resource
	 * {"resourceId":"2","resourceUri":"/image/p22","resourceName":"图22","permission":"不会写2"}
	 */
	@PutMapping(value="/resource",consumes="application/json")
	public MyResult<Resource> updResource(@RequestBody Resource resource){
		return resorceServiceImpl.updResource(resource);
	}
	
	/**
	 * 127.0.0.1/api/resource/2
	 */
	@DeleteMapping("/resource/{resourceId}")
	public MyResult<Object> updResource(@PathVariable String resourceId){
		return resorceServiceImpl.delResource(resourceId);
	}
}
