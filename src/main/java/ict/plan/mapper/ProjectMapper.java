package ict.plan.mapper;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {
    public List<Project> projects(int companyId);

    public Project findProjectById(int id);

    public List<Project> getProjectLikeName(String name,int companyId);

    public List<Project> findProjectByName(String name);

    public Integer save(Project project);

    public int Update(Project project);
}
