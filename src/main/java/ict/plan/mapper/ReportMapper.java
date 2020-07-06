package ict.plan.mapper;

import ict.plan.entity.po.CustomerConsumptionReport;
import ict.plan.entity.po.CustomerFlowReport;
import ict.plan.entity.po.FinancialStatisticalReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    //获取客户流量信息统计表
    public List<CustomerFlowReport> getCustomerFlowReport();
    List<CustomerConsumptionReport> getCustomerConsumptionReport();
    List<FinancialStatisticalReport> getFinancialStatisticalReport();
}
