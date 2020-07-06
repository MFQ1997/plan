package ict.plan.mapper;

import ict.plan.entity.po.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    public List<Company> companies();

    public Company findCompanyById(int id);

    public Integer save(Company Company);

    public int deleteCompanyById(int id);

    public List<Company> findCompanyByName(String name);

    public int updateById(Company company);
}
