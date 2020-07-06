package ict.plan.entity.po;

/*
* @Description：这个是客户的实体类
* */
public class Customer {

    private Integer id;                 //客户编码
    private String name;                //客户的名称
    private String firstContactName;    //主联系人名称
    private String firstContactPhone;   //主联系人电话
    private Integer companyId;          //公司的id

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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstContactName='" + firstContactName + '\'' +
                ", firstContactPhone='" + firstContactPhone + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
