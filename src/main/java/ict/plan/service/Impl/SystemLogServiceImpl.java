package ict.plan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Company;
import ict.plan.entity.po.SystemLog;
import ict.plan.mapper.SystemLogMapper;
import ict.plan.mapper.SystemMapper;
import ict.plan.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public boolean save(SystemLog systemLog) {
        int result = systemLogMapper.save(systemLog);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<SystemLog> findAllLogs(int page,int rows) {
        PageHelper.startPage(page,rows);
        List<SystemLog> logs = systemLogMapper.findAllLogs();
        //用PageInfo对结果进行包装
        PageInfo<SystemLog> pageData = new PageInfo<SystemLog>(logs);
        return pageData;
    }
}
