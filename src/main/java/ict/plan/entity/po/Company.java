package ict.plan.entity.po;

/*
* @Name:Company
* @Description:这个是公司的实体类,是平台层面上的客户
* @Date:2019
* */

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Company {
    private Integer id;                             //自增长的主键id
    private String name;                            //公司的名称
    private String firstContactName;                //公司主要的联系人的名字
    private String firstContactPhone;               //主要联系人的联系号码
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;                        //创建的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;                        //更新的时间
    private Double balance;                         //余额
    private int chargeLevel;                        //收费等级
    private Double owe;                             //欠费数量
    private int dbid;                               //动态数据源的标识
    private int status;                             //公司的状态
    private String location;                        //公司的所在地址
    private String remark;                          //备注信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstContactName() {
        return firstContactName;
    }

    public void setFirstContactName(String firstContactName) {
        this.firstContactName = firstContactName;
    }

    public String getFirstContactPhone() {
        return firstContactPhone;
    }

    public void setFirstContactPhone(String firstContactPhone) {
        this.firstContactPhone = firstContactPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getChargeLevel() {
        return chargeLevel;
    }

    public void setChargeLevel(int chargeLevel) {
        this.chargeLevel = chargeLevel;
    }

    public Double getOwe() {
        return owe;
    }

    public void setOwe(Double owe) {
        this.owe = owe;
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstContactName='" + firstContactName + '\'' +
                ", firstContactPhone='" + firstContactPhone + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", balance=" + balance +
                ", chargeLevel=" + chargeLevel +
                ", location=" + location +
                ", owe=" + owe +
                ", dbid=" + dbid +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
