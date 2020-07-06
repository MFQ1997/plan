package ict.plan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.shiro.Role;
import ict.plan.entity.shiro.User;
import ict.plan.entity.vo.RoleVO;

import java.util.List;

public interface RoleService extends IService<Role> {
    //添加角色
    public boolean save(RoleVO role);
    //角色模糊查询
    public List<Role> findRoleList(String roleName);

    //删除角色
    public Boolean delete(Integer id);
    //修改角色
    public Role update(Role role);
    //获取所有的角色
    public PageInfo<Role> roles(int page,int rows);
    //根据角色的id获取对应的用户列表
    public List<User> getUserListByRoleId(Integer id);
    //根据id获取单一用户
    public Role role(Integer id);
}
