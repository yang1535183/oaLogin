package com.yunxi.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yunxi.dao.UserInfoMapper;
import com.yunxi.model.UserInfo;

/**
 * @author:Mr.Yang
 * @version date:2018年5月24日 下午2:53:28
 * @Description
 */
public class MyRealm extends AuthorizingRealm {
	
	@Resource(name="UserDao")
	public UserInfoMapper um;
	public UserInfoMapper getUm() {
		return um;
	}
	public void setUm(UserInfoMapper um) {
		this.um = um;
	}

	// 设置realm名称
	@Override
	public void setName(String name) {
		super.setName("myRealm");
	}

	//用于认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.从token取出用户身份信息
		String userCode=(String) token.getPrincipal();
		System.out.println("userCode"+userCode);
		
		//2.根据用户userCode查询数据库
		UserInfo user=um.getUserByUserName(userCode);
		System.out.println("user:"+user);
		String password;
		if (user!=null) {
			password=user.getPasswd();
		}else {
			//模拟从数据库查询到的密码
			password="123";
		}
		//查询不到返回null
		
		//查询到返回认证信息
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(userCode, password, this.getName());
		return authenticationInfo;
	}

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		
		//获取主身份信息
		String userCode =(String) principals.getPrimaryPrincipal();
		
		//根据身份信息获取权限信息
		//模拟从数据库获取到数据
		List<String> permissions=new ArrayList<String>();
		permissions.add("user:create");//用户的创建权限
		permissions.add("items:add");//商品的添加权限
		
		//将查询到的授权信息填充到对象中
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);
		return info;
	}

}
