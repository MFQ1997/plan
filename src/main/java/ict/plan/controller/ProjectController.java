package ict.plan.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.PageInfo;
import ict.plan.core.bean.RestResponse;
import ict.plan.core.util.DBConnStrUtil;
import ict.plan.entity.po.Project;
import ict.plan.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* @Name:ProjectController
* @Description:这个是管理项目信息的控制器
* @Date:2019
* */

@RestController
@RequestMapping("/project")
public class ProjectController extends BaseRestController {

    @Autowired
    private ProjectService projectService;


    /*
    * @Description:新增项目
    * @Param:project
    * */
    @PostMapping
    public RestResponse<Project> save(@RequestBody Project project) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        Project save = projectService.save(project);
        if (save!=null)
            return success(project);
        return fail("新增失败","01",null);
    }

    /*
    * @Description:删除一个项目
    * @Param:id(int)
    * */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public RestResponse delete(@PathVariable int id) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        Boolean result = projectService.delete(id);
        if(result){
            return success("操作成功");
        }
        return fail("操作失败","01",null);
    }

    /*
    * @Description:更新项目
    * */
    @PutMapping
    public RestResponse update(@RequestBody Project project) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        Boolean result = projectService.Update(project);
        if(result){
            return success("操作成功");
        }
        return fail("操作失败","01",null);
    }

    /*
    * @Description:获取所有的项目信息
    * */
    @GetMapping
    @ResponseBody
    public RestResponse<List<Project>> projects(int companyId) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        List<Project> projects = projectService.projects(companyId);
        return success(projects);
    }

    /*
    * @Description:根据id查看单条项目的详细信息
    * @Param:id(int)
    * */
    @GetMapping(value = "/id/{id}")
    @ResponseBody
    public RestResponse<Project> findProjectById(@PathVariable("id")int id) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        Project project = projectService.findProjectById(id);
        return success(project);
    }

    /*
    * @Description:根据名称进行模糊查询
    * @Param:name(String)
    * */
    @GetMapping(value = "/name/{name}")
    public RestResponse<List<Project>> findProjectByName(@PathVariable("name") String name) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        List<Project> projects = projectService.findProjectByName(name);
        return success(projects);
    }

    /*
    * @Description:这个是根据名称来进行模糊查询
    * @Date：2019
    * */
    @GetMapping("/key")
    public RestResponse<PageInfo<Project>> getProjectLikeName(@RequestParam(value="name")String name,@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows, @RequestParam(value="companyId")int companyId) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        PageInfo<Project> projectList = projectService.getProjectLikeName(name,page,rows,companyId);
        return success(projectList);
    }
}
