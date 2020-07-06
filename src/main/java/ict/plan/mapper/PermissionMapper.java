package ict.plan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ict.plan.entity.shiro.Permission;
import ict.plan.entity.shiro.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    //添加权限
    public Integer save(Permission permission);
    //获取所有的权限
    public List<Permission> permissions();
    public List<Permission> getUserModule();
    //根据用户获取对应的权限
    public List<Permission> permissionsByRole(Role role);
    //删除权限
    public int permissionDelById(Integer id);
    //修改权限
    public Permission permissionUpd(Integer id,Permission permission);
}
