package ict.plan.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import ict.plan.core.util.TreeUtil;
import ict.plan.entity.po.Company;
import ict.plan.entity.shiro.Permission;
import ict.plan.entity.shiro.Role;
import ict.plan.entity.shiro.User;
import ict.plan.entity.vo.UserVO;
import ict.plan.mapper.UserMapper;
import ict.plan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /*
    * @Description:获取所有的User
    * @Param:page 要显示第几页的内容
    * @Parma:rows 一页显示多少条
    * @ReturnType:List<User>
    * */
    @Override
    public PageInfo<User> users(int page,int rows){
        //List<User> users = userMapper.users();
        PageHelper.startPage(page,rows);
        List<User> users = userMapper.findAllUserWithRole();
        String userJson = JSON.toJSONString(users);
        System.out.println("将信息转为json格式"+userJson);
        //用PageInfo对结果进行包装
        PageInfo<User> pageData = new PageInfo<User>(users);
        return pageData;
    }

    @Override
    public boolean hasName(String userName) {
        User user = userMapper.hasName(userName);
        if(user == null)
            return false;
        return true;
    }

    @Transactional
    @Override
    public boolean save(User user) {
        //设置时间
        Timestamp createTime = new Timestamp(new Date().getTime());
        user.setCreateTime(createTime);
        user.setUpdateTime(createTime);
        //添加数据到user表
        int resultFromUser = userMapper.save(user);
        //添加数据到user_info表
        int resultFromUserInfo = userMapper.complete(user);
        //设置默认的普通用户的角色
        int resultFromRole = userMapper.setDefaultRole(user.getId());
        if (resultFromUser>0 && resultFromUserInfo >0 && resultFromRole>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean allot(UserVO user) {
        int userId = user.getId();
        int [] roleIds = user.getIds();
        for (int i=0;i<roleIds.length;i++){
            int result = userMapper.allot(userId,roleIds[i]);
        }
        return true;
    }


    @Override
    public User findUserById(Integer id) {
        System.out.println(id);
        User user = userMapper.findUserById(id);
        return user;
    }

    @Override
    public User findOneUserByName(String userName) {
        User user =  userMapper.findOneUserByName(userName);
        List<Role> roleList = user.getRoles();
        //创建一个空白的List，用以后面树形排序后的形式的List
        ArrayList<Role> roleArrayList = new ArrayList();
        for (Role role:roleList) {
            List<Permission> permissionList = role.getPermissions();
            //将上一步找出的所有权限做递归排序父子节点，并且返回树的形式的结果集合
            Collection<Permission> permissions = TreeUtil.toTree(permissionList, "id", "pid", "children", Permission.class);
            //将collection转化为List格式
            List resultToTreelist;
            if (permissions instanceof List)
                resultToTreelist = (List)permissions;
            else
                resultToTreelist = new ArrayList(permissions);
            role.setPermissions(resultToTreelist);
            roleArrayList.add(role);
        }
        user.setRoles(roleArrayList);
        System.out.println("这个是登录用户树形排序后的权限信息");
        return user;
    }

    @Override
    public User selectOneUserByName(String userName) {
        return userMapper.selectOneUserByName(userName);
    }

    @Override
    public User complete(User user) {
        userMapper.complete(user);
        return user;
    }

    @Override
    public PageInfo<User> findUserByName(String userName,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<User> users = userMapper.findUserByName(userName);
        //用PageInfo对结果进行包装
        PageInfo<User> pageData = new PageInfo<User>(users);
        return pageData;
    }

    @Override
    public User findOneUserById(Integer userId) {
        User user = userMapper.findOneUserById(userId);
        return user;
    }

    @Override
    public User findUserToLogin(String userName) {
        return userMapper.findUserToLogin(userName);
    }


    @Override
    public Boolean deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Transactional
    @Override
    public User updateUserById( User user) {
        userMapper.updateUserById(user);
        userMapper.updateUser(user);
        return user;
    }

    @Override
    public String findPermissionById(Integer id) {
        return null;
    }

    public User findPermissionFromRoleByUserName(Integer userId){
        return userMapper.findPermissionFromRoleByUserName(userId);
    }

}
