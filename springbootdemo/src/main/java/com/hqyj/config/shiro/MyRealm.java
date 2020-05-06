package com.hqyj.config.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hqyj.modules.account.pojo.Resource;
import com.hqyj.modules.account.pojo.Role;
import com.hqyj.modules.account.pojo.User;
import com.hqyj.modules.account.service.ResourceService;
import com.hqyj.modules.account.service.RoleService;
import com.hqyj.modules.account.service.UserService;


@Component
public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	// 资源授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		String userName = (String) principals.getPrimaryPrincipal();
		User user = userService.getUserByUserName(userName);
		if (user == null) {
			return null;
		}
		
		List<Role> roles = roleService.getRolesByUserId(user.getUserId());
		for (Role role : roles) {
			authorizationInfo.addRole(role.getRoleName());
			List<Resource> resources = resourceService.getResourcesByRoleId(role.getRoleId());
			for (Resource resource : resources) {
				authorizationInfo.addStringPermission(resource.getPermission());
			}
		}
		
		return authorizationInfo;
	}

	// 身份验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		User user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new UnknownAccountException("This user name do not exist.");
		}
		
		// 身份验证器，包装用户名和密码
		return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
	}

	
}
