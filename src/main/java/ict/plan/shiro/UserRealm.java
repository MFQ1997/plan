package ict.plan.shiro;


import ict.plan.entity.shiro.Permission;
import ict.plan.entity.shiro.Role;
import ict.plan.entity.shiro.User;
import ict.plan.service.PermissionService;
import ict.plan.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import sun.java2d.pipe.SpanIterator;

import java.util.List;


/*
* @Name:UserRealm
* @Description:配置自定义的Realm
* @Date:2019
* */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /*
    * @Description:执行授权逻辑
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取认证通过的账号信息
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名去数据库查询用户信息
        //User user = userService.findOneUserByName("lisi");
        User user = userService.selectOneUserByName(userName);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //使用getRoles()方法将该用户的所有的角色信息遍历出来
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Permission permissions : role.getPermissions()) {
                System.out.println("添加的权限标识是："+permissions.getPerms());

                simpleAuthorizationInfo.addStringPermission(permissions.getPerms());
            }
        }
        return simpleAuthorizationInfo;
    }


    /*
    * @Description:执行认证逻辑
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("开始执行认证逻辑");
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取账号和密码的信息
        String userName = authenticationToken.getPrincipal().toString();
        String password = new String((char[]) authenticationToken.getCredentials());

        User user = userService.findUserToLogin(userName);
        System.out.println(user);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword().toString(), ByteSource.Util.bytes(user.getUserName()), getName());
            // 当验证都通过后，把用户信息放在session里
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession", user);
            session.setAttribute("userSessionId", user.getId());
            //获取用户的公司id，用来获取相应的模块返回给前端
            session.setAttribute("companyId",user.getCompanyId());
            return simpleAuthenticationInfo;
        }
    }
}
