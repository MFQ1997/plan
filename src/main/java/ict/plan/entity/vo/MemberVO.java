package ict.plan.entity.vo;

import ict.plan.entity.po.Member;
import ict.plan.entity.po.Quota;

import java.util.Arrays;

/*
* @Description:这个是前端传递过来的会员信息实体类
* */

public class MemberVO extends Member {
    private int [] ids;         //存储传递过来的模块ID
    private Quota quotaObject;        //前端传递过来的数量额度信息


    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public Quota getQuotaObject() {
        return quotaObject;
    }

    public void setQuotaObject(Quota quotaObject) {
        this.quotaObject = quotaObject;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "ids=" + Arrays.toString(ids) +
                ", quotaObject=" + quotaObject +
                '}';
    }
}
