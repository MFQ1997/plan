package ict.plan.controller;

/*
* @Description:这个是公司的客户管理
* */

import com.github.pagehelper.PageInfo;
import ict.plan.core.bean.RestResponse;
import ict.plan.core.util.DBConnStrUtil;
import ict.plan.entity.po.Customer;
import ict.plan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public RestResponse<PageInfo<Customer>> getAllCustomerByCompanyId(int companyId,@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        System.out.println("开始查询素所有的客户");
        PageInfo<Customer> customerList = customerService.selectCustomerByCompanyId(companyId,page,rows);
        return success(customerList);
    }

    @PostMapping
    public RestResponse save(@RequestBody Customer customer) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        Customer save = customerService.save(customer);
        if(save!=null)
            return success("新增成功");
        return fail("操作失败","01",null);
    }

    @PutMapping
    public RestResponse update(@RequestBody Customer customer){
        boolean result = customerService.Update(customer);
        if(result)
            return success("修改成功");
        return fail("操作失败","01",null);
    }
    @GetMapping("/key")
    public RestResponse<PageInfo<Customer>> getCustomerLikeKey(@RequestParam(value="name")String name,@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows, @RequestParam(value="companyId")int companyId) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        PageInfo<Customer> customerList = customerService.findCustomerLikeKey(name,page,rows,companyId);
        return success(customerList);
    }

}
