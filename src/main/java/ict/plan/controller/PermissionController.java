package ict.plan.controller;

/*
* @Name:PermissionController
* @Description:这个是管理权限信息的控制器
* @Date:2019
* */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import ict.plan.core.bean.RestResponse;
import ict.plan.core.util.PasswordUtil;
import ict.plan.entity.shiro.Permission;
import ict.plan.entity.shiro.Role;
import ict.plan.entity.shiro.User;
import ict.plan.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* @Name:PermissionController
* @Description:这个是管理权限信息的控制器
* @Date:2019
* */

@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseRestController{

    @Autowired
    private PermissionService permissionService;

    /*
    * @Description:这个方法是添加权限资源
    * @Param:permission
    * */
    @PostMapping
    @ResponseBody
    //@RequiresPermissions("permission:add")
    public RestResponse save(@RequestBody Permission permission) {
        boolean permissionAfter = permissionService.save(permission);
        if(permissionAfter){
            return success("操作成功");
        }
        return fail("操作失败","01",null);

    }

    /*
    * @Description:这个是删除相应的权限的操作
    * @Param:idd(int)
    * */
    @DeleteMapping("/{id}")
    @ResponseBody
    @RequiresPermissions("permission:delete")
    public RestResponse delete(@PathVariable("id") int id){
        Boolean result = permissionService.deleteById(id);
        if(result){
            return success("操作成功");
        }
        return fail("操作失败","01",null);
    }

    /*
    * @Description:这个是负责修改权限信息的操作
    * @Param:Permission
    * */
    @PutMapping
    @ResponseBody
    @RequiresPermissions("permission:update")
    public RestResponse update(@RequestBody Permission permission){

        return success("操作成功");
    }

    /*
    * @Description:这个是查询出所有的权限信息，用来添加角色的时候选择分配权限
    * @Return:List
    * */
    @GetMapping
    @ResponseBody
    @RequiresPermissions("permission:list")
    public RestResponse<List<Permission>> permissions(){
        List<Permission> permissions = permissionService.Permissions();
        return success(permissions);
    }

    /*
    * @Description:根据角色获取相应的权限,提供给Shiro
    * @Param:Role(class)
    * */
    public RestResponse<List<Permission>> findPermissionByRole(List<Role> role){
        List<Permission> permissions  = permissionService.permissionsByRole(role);
        return success(permissions);
    }

    /*
    * @Description:这个是拿出用户模块的（五大操作模块）
    * */
    @GetMapping("/module")
    public RestResponse<List<Permission>> getUserModule(){
        List<Permission> userPermission = permissionService.getUserModule();
        return success(userPermission);
    }


    /*public static JSONArray treeRecursionDataList(List<Map<String,Object>> treeList, int parentId) {
        JSONArray childMenu = new JSONArray();
        for (Object object : treeList) {
            JSONObject jsonMenu =JSONObject.parseObject(JSON.toJSONString(object));
            int menuId = jsonMenu.getIntValue("id");
            int pid = jsonMenu.getIntValue("pid");
            if (parentId == pid) {
                JSONArray c_node = treeRecursionDataList(treeList, menuId);
                jsonMenu.put("childList", c_node);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }*/






}
