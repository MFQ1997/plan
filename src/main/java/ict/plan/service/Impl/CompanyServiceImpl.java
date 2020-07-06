package ict.plan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Company;
import ict.plan.mapper.CompanyMapper;
import ict.plan.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public boolean save(Company customer) {
        //设置时间
        customer.setCreateTime(new Date());
        customer.setUpdateTime(new Date());

        int result = companyMapper.save(customer);
        if(result>0)
            return true;
        return false;
    }

    @Override
    public int deleteCompanyById(int id) {
        return companyMapper.deleteCompanyById(id);
    }

    /*
    * @Description：修改公司的信息
    * */
    @Override
    public Company updateById(Company company) {
        //重新设置更新时间为最新的时间
        company.setUpdateTime(new Date());
        //将createTime属性的值设置为null，避免将数据库中的create_time字段的数据重新赋值
        company.setCreateTime(null);
        int result = companyMapper.updateById(company);
        if(result>0)
            return company;
        return null;
    }

    @Override
    public PageInfo<Company> companies(int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Company> companies = companyMapper.companies();
        //用PageInfo对结果进行包装
        PageInfo<Company> pageData = new PageInfo<Company>(companies);
        return pageData;
    }

    @Override
    public List<Company> companyList() {
        List<Company> companies = companyMapper.companies();
        return companies;
    }


    @Override
    public Company findCompanyById(int id) {
        Company customer = companyMapper.findCompanyById(id);
        return customer;
    }

    @Override
    public PageInfo<Company> findCompanyByName(String key,int page,int rows) {

        PageHelper.startPage(page,rows);
        List<Company> companies = companyMapper.findCompanyByName(key);
        //用PageInfo对结果进行包装
        PageInfo<Company> pageData = new PageInfo<Company>(companies);
        return pageData;
    }
}
