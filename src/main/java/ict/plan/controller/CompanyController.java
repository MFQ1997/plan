package ict.plan.controller;

import com.github.pagehelper.PageInfo;
import ict.plan.core.bean.RestResponse;
import ict.plan.entity.po.Company;
import ict.plan.entity.shiro.User;
import ict.plan.service.CompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
* @Name:CompanyController
* @Description:这个是管理公司(客户)相关信息的Controller控制器
* @Date:2019
* */

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseRestController{

    @Autowired
    private CompanyService companyService;

    /*
    * @Description:这个是添加公司的方法
    * @Date：2019
    * */
    @ResponseBody
    @PostMapping
    public RestResponse save(@RequestBody Company company) throws Exception{
        boolean companyAfter = companyService.save(company);
        if(companyAfter)
            return success("操作成功");
        return fail("操作失败","01",null);
    }


    /*
    * @Name:companyList
    * @Description:这个是返回全部的公司的列表
    * @Param:page 要显示第几页的内容
    * @Parma:rows 一页显示多少条
    * @Date:2019
    * */
    @GetMapping
    @ResponseBody
    public RestResponse<PageInfo<Company>> companyList(@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows){
        PageInfo<Company> pageResult = companyService.companies(page,rows);
        return success(pageResult);
    }

    /*
    * @Name:
    * @Description:这个是返回没有进行分页的全部的公司列表
    * */
    @ResponseBody
    @GetMapping("/list")
    public RestResponse<List<Company>> companyListWithoutPage(){
        List<Company> companyList = companyService.companyList();
        return success(companyList);
    }

    /*
    * @Description:这个是根据id进行公司的查询
    * @Param:id(int)
    * @ReturnType:Company
    * */
    @ResponseBody
    @GetMapping("/id/{id}")
    public RestResponse<Company> getComanyById(@PathVariable("id") int id){
        Company company = companyService.findCompanyById(id);
        return success(company);
    }

    /*
     * @Description:这个是根据name进行公司的模糊查询
     * @Param:key 搜索的关键字
     * @Param:page 要显示第几页的内容
     * @Parma:rows 一页显示多少条
     * @ReturnType:List<Company>
     * */
    @ResponseBody
    @GetMapping("/key/{key}")
    public RestResponse<PageInfo<Company>> getComanyById(@PathVariable("key") String key,@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows){
        PageInfo<Company> companyList = companyService.findCompanyByName(key,page,rows);
        return success(companyList);
    }

    /*
    * @Description:这个是将公司的数据进行伪删除
    * @param:id(int)
    * */
    @DeleteMapping("/{id}")
    @ResponseBody
    public RestResponse companyDel(@PathVariable("id") int id){
        //将数据库的数据进行删除
        int result = companyService.deleteCompanyById(id);
        if (result>0){
            return success("操作成功");
        }
        return fail("操作失败","01",null);

    }

    /*
    * @Name:CompanyUpd
    * @Description:这个是修改公司的相关的信息
    * @param:id(int) , Company(Class)
    * */
    @PutMapping
    @ResponseBody
    public RestResponse CompanyUpd(@RequestBody Company company){
        Company companyAfter = companyService.updateById(company);
        //开始进行数据的更新操作
        if (companyAfter != null){
            return success("修改成功");
        }
        return fail("操作失败","01",null);
    }
}
