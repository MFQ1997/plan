package ict.plan.entity.po;

/*
* @Description:这个财务统计报表实体类
* @Date:2019
* */

import java.util.Date;

public class FinancialStatisticalReport {

    private String location;            //统计的地区
    private double paymentAccount;      //支出的统计
    private double incomeAccont;        //收入的统计
    private Date startTime;             //报表开始时间
    private Date endTime;               //报表结束时间

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(double paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public double getIncomeAccont() {
        return incomeAccont;
    }

    public void setIncomeAccont(double incomeAccont) {
        this.incomeAccont = incomeAccont;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "FinancialStatisticalReport{" +
                "location='" + location + '\'' +
                ", paymentAccount=" + paymentAccount +
                ", incomeAccont=" + incomeAccont +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
