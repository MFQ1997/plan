package ict.plan.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import ict.plan.core.annotation.SysLog;
import ict.plan.core.bean.RestResponse;
import ict.plan.core.util.PasswordUtil;
import ict.plan.entity.po.Company;
import ict.plan.entity.po.DbConnString;
import ict.plan.entity.shiro.Permission;
import ict.plan.entity.shiro.Role;
import ict.plan.entity.shiro.User;
import ict.plan.entity.vo.UserVO;
import ict.plan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import javax.xml.transform.Source;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
* @Name:UserController
* @Description:这个是管理用户的控制器
* @Author:xiaomai
* @Date：2019-11
* */

@RestController
@RequestMapping("/user")
public class UserController extends BaseRestController{

    @Autowired
    private UserService userService;

    /*
    * @Name:save()
    * @Description:新增一个用户，在添加的时候讲登录使用用到的字段存储在user表，将其他一些字段存储在user_info表
    * @Param：User（class）
    * @Date:2019
    * */
    @PostMapping
    @ResponseBody
    @SysLog(value = "添加用户操作")  //这里添加了AOP的自定义注解
    //@RequiresPermissions("user:save")
    public RestResponse save(@RequestBody User user) {
        //查看有没有同名的用户

        if (userService.hasName(user.getUserName())) {
            return fail("用户名已存在","01",null);
        }
        try {
            PasswordUtil passwordHelper = new PasswordUtil();
            //设置加密加盐之后的新密码给user
            passwordHelper.encryptPassword(user);
            String salt = ByteSource.Util.bytes(user.getUserName()).toString();
            //将新增用户的时候产生的盐值添加到数据库中
            user.setSalt(salt);
            //新增用户到数据库中
            boolean userAfter = userService.save(user);
            if (userAfter){
                return success("操作成功");
            }else{
                return fail("操作失败","01",null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return fail("操作失败","01",null);
        }
    }

    /*
    * @Description:这个是修改用户信息的方法
    * @Param:User(Class)
    * @Date:2019
    * */
    @ResponseBody
    @PutMapping
    @SysLog(value = "更新用户操作")  //这里添加了AOP的自定义注解
    //@RequiresPermissions("user:update")
    public RestResponse updateUser(@RequestBody User user){
        System.out.println("这里是执行用户更新操作的方法");
        //获取当前用户的名称
        //String userName = SecurityUtils.getSubject().getPrincipal().toString();
        //System.out.println("修改的用户的用户名称是:"+userName);
        //获取当前的用户的信息
        Integer userId = user.getId();
        User currentUser = userService.findOneUserById(userId);
        //进行操作
        currentUser.setId(userId);
        Timestamp updateTime = new Timestamp(new Date().getTime());
        currentUser.setUpdateTime(updateTime);
        User userComplete = userService.updateUserById(currentUser);
        if(userComplete==null){
            return fail("操作失败","01",null);
        }
        return success("操作成功");
    }

    /*
    * @Description:这个是用户分配角色的操作防范
    * @Date：2019
    * */
    @PostMapping("/allot")
    //@RequiresPermissions("user:allot")
    public RestResponse allotRoleToUser(@RequestBody UserVO user){
        System.out.println("_______"+user);
        boolean allot = userService.allot(user);
        return success("操作成功");
    }

    /*
    * @Description:这个是完善用户头像的信息,添加完成用户的信息之后就将用户的头像路径返回给前端
    * @Param:
    * @ReturnType:T(泛型)
    * */
    public RestResponse photoPath(){
        String photoPath = "";
        return success(photoPath);
    }

    /*
    * @Descirption:获取到所有用户
    * @Param:page 要显示第几页的内容
    * @Parma:rows 一页显示多少条
    * @ReturnType:T
    * */
    @SysLog(value = "查询所有用户操作")  //这里添加了AOP的自定义注解
    @GetMapping
    @ResponseBody
   // @RequiresPermissions("user:list")
    public RestResponse<PageInfo<User>> users(@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows) throws Exception{
        PageInfo<User> pageResult = userService.users(page,rows);
        //List<User> result = userService.users();
        return success(pageResult);
    }

    /*
    * @Description:这个是根据Id获取到用户的信息：应用在查看详情
    * @Param:id(int)
    * @ReturnType:T
    * @Date:2019
    * */
    @GetMapping("/id/{id}")
    @ResponseBody
    //@RequiresPermissions("user:id")
    public RestResponse<User> findUserById(@PathVariable("id") int id) throws Exception{
        User user = userService.findUserById(id);
        return success(user);
    }

    /*
    * @Description:根据名字进行模糊查询
    * @Param:name(String)
    * @Date:2019
    * */
    @GetMapping("/name/{name}")
    @ResponseBody
    //@RequiresPermissions("user:name")
    public RestResponse<PageInfo<User>> findUserByName(@PathVariable("name") String name,@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows){
        //List<User> users = userService.findUserByName(name);
        PageInfo<User> pageResult = userService.findUserByName(name,page,rows);
        return success(pageResult);
    }

    /*
    * @Name：getPermissionListFromRoleByUser()
    * @Description:这个是根据用户名来查询角色，然后根据角色来查询出对应的权限列表
    * @Param:userName(String)
    * @ReturnType:User(Class)
    * @Date:2019
    * */
    @ResponseBody
    @GetMapping("/getPerms/id/{userId}")
    public RestResponse<User> getPermissionListFromRoleByUser(@PathVariable("userId") Integer userId){
        User user = userService.findOneUserById(userId);
        return success(user);
    }

    /*
    * @Description:根据Id进行用户的删除
    * @Param:id(int)
    * @Date:2019
    * */
    @DeleteMapping("/{id}")
    @ResponseBody
    //@RequiresPermissions("user:del")
    public RestResponse userDel(@PathVariable("id") int id){
        if(userService.deleteById(id)){
            return success("操作成功");
        }
        return fail("操作失败","01",null);
    }



}
