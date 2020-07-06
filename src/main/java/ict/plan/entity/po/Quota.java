package ict.plan.entity.po;

/*
* @Descriotion:这个是定义不同会员可以操作的数量额度,属性可以一直增加
* @Date:2019
* */

public class Quota {


    private int projectNum;         //项目管理的项目数量
    private int qiniuNum;           //七牛云的存储的数量
    private int placeNum;           //管理场地数量

    /*
    * @Description:生成getter 和 Setter方法
    * */

    public int getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    public int getQiniuNum() {
        return qiniuNum;
    }

    public void setQiniuNum(int qiniuNum) {
        this.qiniuNum = qiniuNum;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }
}
