package ict.plan.entity.po;

/*
* @Description:这个是会员的实体类
* @Date:2019
* */

import java.util.Date;
import java.util.List;

public class Member {

    private Integer id;
    private String memberName;              //免费，优享，尊享，尊享VIP
    private double price;                   //不同会员等级的价格
    private String quota;                   //操作的定额

    /*
    * @Description:可以操作的模块集合
    * */
    //private List<Permission> permissionList;
    private String module;                  //等级可以操作的模块,存储权限ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
