package ict.plan.service;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Customer;

import java.util.List;

public interface CustomerService {
    //新增消费者
    public Customer save(Customer customer);
    //根据主键Id进行删除
    public boolean deleteById(Integer id);
    //更新客户信息
    public boolean Update(Customer customer);

    //根据公司id获取该公司下的所有的客户信息，并且进行分页返回
    public PageInfo<Customer> selectCustomerByCompanyId(Integer companyId,int page,int rows);
    //模糊查询
    public PageInfo<Customer> findCustomerLikeKey(String name,int page,int rows,int companyId);
}
