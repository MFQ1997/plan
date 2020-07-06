package ict.plan.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/*
 * @Name:Project
 * @Description:这个是项目信息对应的实体类
 * @Date:2019
 * */

public class Project {

    @TableId(type= IdType.AUTO)
    private Integer id;         //自增长的主键id
    private String name;        //项目的名称
    private String ownerPhone;  //拥有者的电话
    private String ownerName;   //拥有者
    private double payCosts;    //支付的金额
    private double spendCosts;  //花费的金额
    private int placeId;        //场地的id
    private int companyId;      //这个是公司的Id
    private String handler;         //操作者
    private String remark;      //备注

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

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getPayCosts() {
        return payCosts;
    }

    public void setPayCosts(double payCosts) {
        this.payCosts = payCosts;
    }

    public double getSpendCosts() {
        return spendCosts;
    }

    public void setSpendCosts(double spendCosts) {
        this.spendCosts = spendCosts;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", payCosts=" + payCosts +
                ", spendCosts=" + spendCosts +
                ", placeId=" + placeId +
                ", companyId=" + companyId +
                ", handler='" + handler + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
