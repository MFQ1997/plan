package ict.plan.controller;

import com.github.pagehelper.PageInfo;
import ict.plan.core.bean.RestResponse;
import ict.plan.entity.po.CustomerConsumptionReport;
import ict.plan.entity.po.CustomerFlowReport;
import ict.plan.entity.po.FinancialStatisticalReport;
import ict.plan.mapper.ReportMapper;
import ict.plan.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 * @Name:ReportController
 * @Description:这个是管理所有报表的Controller
 * @Date:2019
 * */

@Controller
@RequestMapping("/report")
public class ReportController extends BaseRestController{

    @Autowired
    private ReportService reportService;


    //@Description:以下是运营报表管理


    /*
    * @Name:
    * @Description:这个是客户流量统计报表
    * */
    @GetMapping("/flow")
    @ResponseBody
    public RestResponse<List<CustomerFlowReport>> CustomerFlowStatisticalReport(){
        List<CustomerFlowReport> customerFlowReportList = reportService.getCustomerFlowReport();
        return success(customerFlowReportList);
    }

    /*
    * @Description:这个是客户消费统计报表
    * */
    @GetMapping("/consume")
    @ResponseBody
    public RestResponse<List<CustomerConsumptionReport>> CustomerConsumptionStatisticsReport(){
        List<CustomerConsumptionReport> customerConsumptionReportList = reportService.getCustomerConsumptionReport();
        return success(customerConsumptionReportList);
    }

    //@Description:以下是财务统计报表管理
    @GetMapping("/finance")
    @ResponseBody
    public RestResponse<List<FinancialStatisticalReport>> FinancialStatisticalReport(){
        List<FinancialStatisticalReport> financialStatisticalReportList = reportService.getFinancialStatisticalReport();
        return success(financialStatisticalReportList);
    }
}
