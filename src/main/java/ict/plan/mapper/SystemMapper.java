package ict.plan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ict.plan.entity.po.Qiniu;
import ict.plan.entity.po.Setting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemMapper{
    //获取系统的设置信息
    public Setting getSetting();

    //获取七牛账号
    public List<Qiniu> qiniuList();
    //根据id获取七牛账号
    public Qiniu findQiniuById(Integer id);
    //添加七牛
    public Integer saveQiniu(Qiniu qiniu);
    //删除七牛
    public boolean deleteQiniuById(Integer id);
    //修改七牛
    public Integer updateQiniu(Qiniu qiniu);
}
