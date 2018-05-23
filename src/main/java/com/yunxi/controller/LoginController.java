package com.yunxi.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
}
