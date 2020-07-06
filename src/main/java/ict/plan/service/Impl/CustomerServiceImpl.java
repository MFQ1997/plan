package ict.plan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Customer;
import ict.plan.mapper.CustomerMapper;
import ict.plan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer save(Customer customer) {
        int result = customerMapper.save(customer);
        if(result>0)
            return customer;
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public boolean Update(Customer customer) {
        boolean update = customerMapper.update(customer);
        if(update)
            return true;
        return false;
    }

    @Override
    public PageInfo<Customer> selectCustomerByCompanyId(Integer companyId,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Customer> customerList = customerMapper.selectCustomerByCompanyId(companyId);
        PageInfo<Customer> pageData = new PageInfo<>(customerList);
        return pageData;
    }

    @Override
    public PageInfo<Customer> findCustomerLikeKey(String name, int page, int rows,int companyId) {
        PageHelper.startPage(page,rows);
        List<Customer> customerList = customerMapper.findCustomerLikeKey(name,companyId);
        PageInfo<Customer> pageData = new PageInfo<>(customerList);
        return pageData;
    }
}
