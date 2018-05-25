package com.yunxi.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
* @author:Mr.Yang
* @version date:2018年5月23日 上午9:56:05
* @Description
*/
public class CustomRealm extends AuthorizingRealm{

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
         
        //3.查询到返回认证信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userCode,password,this.getName());
         
        return info;
    }
 
    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
         
        //获取主身份信息
         String userCode = (String)principals.getPrimaryPrincipal();
          
         //根据身份信息获取权限信息
         //模拟从数据库获取到数据
         List<String> permissions = new ArrayList<String>();
         permissions.add("user:create"); //用户的创建权限
         permissions.add("items:add"); //商品的添加权限
          
         //将查询到授权信息填充到对象中
         SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
         info.addStringPermissions(permissions);
          
        return info;
    }
}
