package com.yunxi.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @author:Mr.Yang
 * @version date:2018年5月24日 下午2:27:39
 * @Description
 */
@SuppressWarnings("deprecation")
public class AuthenticationTest2 {
	// 用户登录和退出
	@Test
	public void testLogin() {
		// 创建SecurityManager工厂，通过init配置文件构造
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");

		// 创建SecurityManager
		org.apache.shiro.mgt.SecurityManager securityManager = factory.createInstance();

		// 将SecurityManager设置到当前运行环境
		SecurityUtils.setSecurityManager(securityManager);

		// 从SecurityUtils里创建Subject
		Subject subject = SecurityUtils.getSubject();

		// 认证提交前准备token
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

		try {
			// 执行认证提交
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();
		System.out.println("是否认证通过:" + isAuthenticated);

		// 退出操作
		subject.logout();

		isAuthenticated = subject.isAuthenticated();
		System.out.println("是否认证通过:" + isAuthenticated);
	}
	
	@Test
	public void testMyRealm() {
		//创建SecurityManager工厂,通过ini配置文件构造
		IniSecurityManagerFactory factory=new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		
		//创建SecurityManager
		org.apache.shiro.mgt.SecurityManager securityManager = factory.createInstance();
		
		//将SecurityManager设置到当前运行环境
		SecurityUtils.setSecurityManager(securityManager);
		
		//从SecurityUtils里创建Subject
		Subject subject=SecurityUtils.getSubject();
		
		//认证提交前准备token
		UsernamePasswordToken token=new UsernamePasswordToken("zhangsan", "123");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//是否认证通过
		boolean isAuthenticated =subject.isAuthenticated();
		System.out.println("是否认证通过："+isAuthenticated);
		
		//基于资源的授权（权限标识符）
		boolean permitted=subject.isPermitted("user:create");
		System.out.println("基于资源的而授权："+permitted);
		
		//退出操作
		subject.logout();
		
		isAuthenticated=subject.isAuthenticated();
		System.out.println("是否认证通过："+isAuthenticated);
	}
	
	/*
	 * 授权测试
	 */
	@Test
	public void testAuthorzation() {
		//创建SecurityManager工厂
		Factory<org.apache.shiro.mgt.SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-permission.ini");
		
		//创建SecurityManager
		org.apache.shiro.mgt.SecurityManager securityManager=factory.getInstance();
		
		//将SecutityManager设置到系统环境
		SecurityUtils.setSecurityManager(securityManager);
		
		//创建Subject
		Subject subject=SecurityUtils.getSubject();
		
		//创建token令牌
		UsernamePasswordToken token=new UsernamePasswordToken("zhangsan", "123");
		
		//执行认证
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("是否认证通过："+subject.isAuthenticated());
		
		//基于角色的授权（角色标识）
		boolean hasRole = subject.hasRole("role3");
		System.out.println("基于角色的授权："+hasRole);
		
		//基于资源的授权（权限标识符）
		boolean permitted=subject.isPermitted("user:create");
		System.out.println("基于资源的授权："+permitted);
	}
}
