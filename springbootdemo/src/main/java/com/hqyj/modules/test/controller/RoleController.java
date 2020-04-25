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
import com.hqyj.modules.test.pojo.Role;
import com.hqyj.modules.test.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleService roleServiceImpl;
	
	/**
	 * 127.0.0.1/api/roles
	 */
	@RequestMapping("/roles")
	public List<Role> getRoleAll(){
		return roleServiceImpl.selRoleAll();
	}
	
	/**
	 * 127.0.0.1/api/role?roleId=1
	 */
	@RequestMapping("/role")
	public Role getRoleByRoleId(String roleId){
		return roleServiceImpl.selRoleByRoleId(roleId);
	}
	
	/**
	 * 127.0.0.1/api/role
	 * {"roleName":"管理员2"}
	 */
	@PostMapping(value="/role",consumes="application/json")
	public MyResult<Role> insRole(@RequestBody Role role){
		return roleServiceImpl.insRole(role);
	}
	
	/**
	 * 127.0.0.1/api/role
	 * {"roleId":"2","roleName":"管理员222"}
	 */
	@PutMapping(value="/role",consumes="application/json")
	public MyResult<Role> updRole(@RequestBody Role role){
		return roleServiceImpl.updRole(role);
	}
	
	/**
	 * 127.0.0.1/api/role/2
	 */
	@DeleteMapping("role/{roleId}")
	public MyResult<Object> delRole(@PathVariable String roleId){
		return roleServiceImpl.delRole(roleId);
	}
	
}
