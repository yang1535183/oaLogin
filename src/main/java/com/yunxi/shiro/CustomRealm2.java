package com.yunxi.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author:Mr.Yang
 * @version date:2018年5月23日 下午5:34:03
 * @Description
 */
public class CustomRealm2 extends AuthorizingRealm {

	//设置Realm名称
    @Override
    public void setName(String name){
        super.setName("customRealm");
    }
 
    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
         
        //1.从token取出用户身份信息
        String userCode = (String)token.getPrincipal();
         
        //2.根据用户userCode查询数据库
        //模拟从数据库查询到的密码
        String password = "123";
         
        //查询不到返回null
         
        //查询到返回认证信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userCode,password,this.getName());
         
        return authenticationInfo;
    }
 
    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
         
        return null;
    }
}
