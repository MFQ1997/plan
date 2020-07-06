package ict.plan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Place;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlaceMapper extends BaseMapper<Place> {

    public Integer save(Place place);

    public Boolean deleteById(Integer id);

    public int update(Place place);

    //分页获取所有场地
    public List<Place> places();
    //根据id获取场地
    public Place findPlaceById(Integer id);

    //根据关键字进行模糊查询场地信息
    public List<Place> findPlaceByKey(String key);

    //查询条数
    public int countNum(int companyId);

}
