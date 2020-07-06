package ict.plan.entity.po;

/*
* @Name:CustomerFlowReport
* @Description:这个是客户流量统计报表的实体类
* @Date:2019
* */

public class CustomerFlowReport {


    private String location;            //公司的地址：（--省--市--区）
    private String companyName;         //公司的名称
    private String count;               //所在地址的公司数量（茂南区有*家公司）

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CustomerFlowReport{" +
                "location='" + location + '\'' +
                ", companyName='" + companyName + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
