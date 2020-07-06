package ict.plan.service;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.SystemLog;

import java.util.List;

public interface SystemLogService {
    public boolean save(SystemLog systemLog);
    public PageInfo<SystemLog> findAllLogs(int page, int rows);
}
