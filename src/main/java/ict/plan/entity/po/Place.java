package ict.plan.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.List;

/*
* @Name:Place
* @Description:这个是场地信息对应的实体类
* @Date:2019
* */

public class Place {

    @TableId(type= IdType.AUTO)
    private Integer id;                 //自增长的id
    private String placeName;           //场地的名称
    private String location;            //场地的所在地
    private String firstContactName;    //第一联系人的名字
    private String firstContactPhone;   //第一联系人的联系号码
    private Integer companyId;          //公司Id
    private int status;                 //场地的状态
    private String remark;              //备注信息

    /*
    * @Description:这个是存储多个项目的属性
    * */
    private List<Project> projects;     //场地的项目列表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", placeName='" + placeName + '\'' +
                ", location='" + location + '\'' +
                ", firstContactName='" + firstContactName + '\'' +
                ", firstContactPhone='" + firstContactPhone + '\'' +
                ", companyId=" + companyId +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", projects=" + projects +
                '}';
    }
}
