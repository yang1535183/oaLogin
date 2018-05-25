package com.yunxi.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @author:Mr.Yang
* @version date:2018年5月23日 上午10:21:07
*/
@Controller
public class LoginController {
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
    public String login01() {
        return "login";
    }
	
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String login02(String userName, String passwd, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passwd);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("userName", "用户名错误！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("passwd", "密码错误");
            return "login";
        }
        return "index";
    }
	
	@RequestMapping(value="shiroLogin.do",method=RequestMethod.POST)
	public String login03(HttpSession session,String userName,String passwd) {
		System.out.println(userName+passwd);
		shiroLogin(userName,passwd);
		return "index";
	}
	
	public void shiroLogin(String userName,String passwd) {
		//创建SecurityManager工厂
		Factory<org.apache.shiro.mgt.SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		
		//创建SecurityManager
		org.apache.shiro.mgt.SecurityManager securityManager=factory.getInstance();
		
		//将SecutityManager设置到系统环境
		SecurityUtils.setSecurityManager(securityManager);
		
		//创建Subject
		Subject subject=SecurityUtils.getSubject();
		
		//创建token令牌
		UsernamePasswordToken token=new UsernamePasswordToken(userName, passwd);
		
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
