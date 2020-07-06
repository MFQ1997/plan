package ict.plan.controller;

import com.github.pagehelper.PageInfo;
import ict.plan.core.bean.RestResponse;
import ict.plan.entity.po.*;
import ict.plan.service.PermissionService;
import ict.plan.service.SystemLogService;
import ict.plan.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* @Name:SystemController
* @Description:这个是管理系统设置的Controller（例如设置系统的名称、域名信息、备案号等等,此外还有七牛云账号，操作日志等等的模块管理）
* @Date:2019
* */

@RestController
@RequestMapping("/system")
public class SystemController extends BaseRestController{

    @Autowired
    private SystemService systemService;

    @Autowired
    private SystemLogService systemLogService;


    /*
     * @Descirption:获取到所有数据库连接字符串
     * */
    @ResponseBody
    @GetMapping("/setting")
    public RestResponse<Setting> getSettings() throws Exception{
        //获取网站的设置信息
        Setting setting = systemService.getSetting();
        return success(setting);
    }

    // ____________系统操作日志____________-_-
    /*
    * @Description:这个是获取所有系统操作日志的方法
    * @Date:2019
    * */
    @GetMapping("/logs")
    @ResponseBody
    public RestResponse<PageInfo<SystemLog>> systemLogList(@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows){
        PageInfo<SystemLog> pageResult = systemLogService.findAllLogs(page,rows);
        return success(pageResult);
    }








    //____________七牛云配置____________-_-
    /*
    * @Name:qiniuList
    * @Description:这个是获取到所有的七牛账号
    * */
    @ResponseBody
    @GetMapping("/qiniu")
    public RestResponse<List<Qiniu>> qiniuList(){
        List<Qiniu> qiniuList = systemService.qiniuList();
        return success(qiniuList);
    }

    /*
    * @Description:这个是根据id获取的七牛账号
    * @ReturnType:Qiniu
    * @Param:id(int)
    * @Date：2019
    * */
    @GetMapping("/qiniu/{id}")
    @ResponseBody
    public RestResponse<Qiniu> findQiniuById(@PathVariable("id") int id){
        Qiniu qiniu = systemService.findQiniuById(id);
        return success(qiniu);
    }

    /*
    * @Name:saveQiniu()
    * @Description:这个是保存七牛账号的操作
    * @ReturnType:message(String)
    * @Date：2019
    * */
    @ResponseBody
    @PostMapping("/qiniu")
    public RestResponse saveQiniu(@RequestBody Qiniu qiniu){
        boolean qiniuAfter = systemService.saveQiniu(qiniu);
        if (qiniuAfter)
            return success("操作成功");
        return fail("操作失败","01",null);
    }

    /*
    * @Name:userDel()
    * @Description:这个是删除七牛账号的操作
    * @Date:2019
    * @ReturnType:message(String)
    * */
    @DeleteMapping("/qiniu/{id}")
    @ResponseBody
    public RestResponse userDel(@PathVariable("id") int id){
        if(systemService.deleteQiniuById(id)){
            return success("操作成功");
        }
        return fail("操作失败","01",null);
    }

    /*
    * @Description:这个是修改七牛账号信息的操作
    * @Param:qiniu(Qiniu.class)
    * @Date:2019
    * */
    @PutMapping("/qiniu")
    @ResponseBody
    public RestResponse upateQiniu(@RequestBody Qiniu qiniu){
        boolean qiniuAfter = systemService.updateQiniu(qiniu);
        if(qiniuAfter){
            return success("操作成功");
        }
        return fail("操作失败","01",null);
    }


}
