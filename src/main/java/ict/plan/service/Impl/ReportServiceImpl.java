package ict.plan.service.Impl;

import ict.plan.entity.po.CustomerConsumptionReport;
import ict.plan.entity.po.CustomerFlowReport;
import ict.plan.entity.po.FinancialStatisticalReport;
import ict.plan.mapper.ReportMapper;
import ict.plan.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* @Description:这个是报表统计服务
* @Date:2019
* */

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    /*
    * @Description:这个是获取客户流量数据统计
    * @ReturnType:List
    * */
    public List<CustomerFlowReport> getCustomerFlowReport(){
        return reportMapper.getCustomerFlowReport();
    }

    @Override
    public List<CustomerConsumptionReport> getCustomerConsumptionReport() {
        return reportMapper.getCustomerConsumptionReport();
    }

    @Override
    public List<FinancialStatisticalReport> getFinancialStatisticalReport() {
        return reportMapper.getFinancialStatisticalReport();
    }
}
