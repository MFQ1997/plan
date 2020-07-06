package ict.plan.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.shiro.Role;
import ict.plan.entity.shiro.User;
import ict.plan.entity.vo.RoleVO;
import ict.plan.mapper.RoleMapper;
import ict.plan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public boolean save(RoleVO role) {
        int [] ids = role.getIds();
        int result = roleMapper.save(role);
        if(result>0){
            for (int i=0;i<ids.length;i++){
                int resultItem = roleMapper.allot(role.getId(),ids[i]);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Role> findRoleList(String roleName) {
        return roleMapper.findRoleByName(roleName);
    }

    @Override
    public Boolean delete(Integer id) {
        return roleMapper.delete(id);
    }

    @Override
    public Role update(Role role) {
        int result = roleMapper.update(role);
        return role;
    }

    @Override
    public PageInfo<Role> roles(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Role> roleList = roleMapper.roles();
        PageInfo<Role> pageData = new PageInfo<Role>(roleList);
        return pageData;

    }

    @Override
    public List<User> getUserListByRoleId(Integer id) {
        return null;
    }

    @Override
    public Role role(Integer id) {
        return roleMapper.role(id);
    }
}
