package ict.plan.service;



import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Project;

import java.util.List;

public interface ProjectService {
    public List<Project> projects(Integer comanyId);
    public PageInfo<Project> getProjectLikeName(String name, int page, int rows, int companyId);
    public Project findProjectById(int id);
    public List<Project> findProjectByName(String name);
    public Project save(Project project);
    public Boolean delete(int id);
    public Boolean Update(Project project);
}
