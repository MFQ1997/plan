package ict.plan.service;

/*
* @Description:这个是负责各个报表信息的服务接口
* @Date：2019
* */

import ict.plan.entity.po.CustomerConsumptionReport;
import ict.plan.entity.po.CustomerFlowReport;
import ict.plan.entity.po.FinancialStatisticalReport;

import java.util.List;

public interface ReportService {
    List<CustomerFlowReport> getCustomerFlowReport();
    List<CustomerConsumptionReport> getCustomerConsumptionReport();
    List<FinancialStatisticalReport> getFinancialStatisticalReport();
}
