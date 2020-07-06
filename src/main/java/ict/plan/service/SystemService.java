package ict.plan.service;

import ict.plan.entity.po.Qiniu;
import ict.plan.entity.po.Setting;

import java.util.List;

public interface SystemService{
    //获取系统的设置信息
    public Setting getSetting();

    /*
     * ____________七牛云配置____________-_-
     * */
    //这个是获取所有的七牛账号信息
    public List<Qiniu> qiniuList();
    //根据id获取七牛账号
    public Qiniu findQiniuById(Integer id);
    //添加七牛
    public boolean saveQiniu(Qiniu qiniu);
    //删除七牛
    public boolean deleteQiniuById(Integer id);
    //修改七牛账号的信息
    public boolean updateQiniu(Qiniu qiniu);
}
