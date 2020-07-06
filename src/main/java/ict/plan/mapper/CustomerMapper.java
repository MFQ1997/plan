package ict.plan.mapper;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    //新增消费者
    public Integer save(Customer customer);
    //根据主键Id进行删除
    public boolean deleteById(Integer id);
    //更新客户信息
    public boolean update(Customer customer);

    //根据公司id获取该公司下的所有的客户信息，并且进行分页返回
    public List<Customer> selectCustomerByCompanyId(Integer companyId);
    //模糊查询
    public List<Customer> findCustomerLikeKey(String name,int companyId);
}
