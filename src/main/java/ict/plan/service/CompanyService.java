package ict.plan.service;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Company;

import java.util.List;

public interface CompanyService {

    public boolean save(Company Company);

    public int deleteCompanyById(int id);

    public Company updateById(Company company);

    public PageInfo<Company> companies(int page, int rows);
    public List<Company> companyList();
    public PageInfo<Company> findCompanyByName(String key,int page,int rows);

    public Company findCompanyById(int id);



}
