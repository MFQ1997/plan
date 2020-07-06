package ict.plan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Project;
import ict.plan.mapper.ProjectMapper;
import ict.plan.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> projects(Integer comanyId) {
        List<Project> projectList = projectMapper.projects(comanyId);
        return projectList;
    }

    @Override
    public PageInfo<Project> getProjectLikeName(String name, int page, int rows, int companyId) {
        PageHelper.startPage(page,rows);
        List<Project> projectList = projectMapper.getProjectLikeName(name,companyId);
        PageInfo<Project> pageData = new PageInfo<>(projectList);
        return pageData;
    }

    @Override
    public Project findProjectById(int id) {
        Project project = projectMapper.findProjectById(id);
        return project;
    }

    @Override
    public List<Project> findProjectByName(String name) {
        List<Project> projects = projectMapper.findProjectByName(name);
        return projects;
    }

    @Override
    public Project save(Project project) {
        int result = projectMapper.save(project);
        if (result>0)
            return project;
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public Boolean Update( Project project) {
        int update = projectMapper.Update(project);
        if(update>0)
            return true;
        return false;
    }
}
