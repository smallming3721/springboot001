package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.common.MyResult;
import com.hqyj.common.MyResult.ResultEnum;
import com.hqyj.modules.test.mapper.RoleMapper;
import com.hqyj.modules.test.pojo.Role;
import com.hqyj.modules.test.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> selRoleAll() {
		return roleMapper.selRoleAll();
	}

	@Override
	public Role selRoleByRoleId(String roleId) {
		return roleMapper.selRoleByRoleId(roleId);
	}

	@Override
	public MyResult<Role> insRole(Role role) {
		MyResult<Role> result=null;
		try{
			roleMapper.insRole(role);
			result=new MyResult<Role>(ResultEnum.SUCCESS.status,"insert success",role);
		}catch(Exception e){
			result=new MyResult<Role>(ResultEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MyResult<Role> updRole(Role role) {
		MyResult<Role> result=null;
		try{
			roleMapper.updRole(role);
			result=new MyResult<Role>(ResultEnum.SUCCESS.status,"update success",role);
		}catch(Exception e){
			result=new MyResult<Role>(ResultEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MyResult<Object> delRole(String roleId) {
		MyResult<Object> result=null;
		try{
			roleMapper.delRole(roleId);
			result=new MyResult<Object>(ResultEnum.SUCCESS.status,"delete success");
		}catch(Exception e){
			result=new MyResult<Object>(ResultEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
