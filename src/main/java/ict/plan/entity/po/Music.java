package ict.plan.entity.po;

/*
* @Description:这个是音频管理
* */

import java.util.Date;

public class Music {

    private int id;                 //数据库自增主键Id
    private int companyId;          //表示哪一家公司上传的文件
    private Date uploadTime;        //上传的时间
    private Date updateTime;        //更新的时间，包括删除的时间
    private String url;             //存储的url地址
    private String remark;          //备注信息

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
