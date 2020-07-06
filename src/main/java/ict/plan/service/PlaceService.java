package ict.plan.service;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Place;

import java.util.List;

/*
* @Name:PlaceService
* @Description:这个是场地的服务接口
* @Date:2019
* */

public interface PlaceService {
    //添加场地信息
    public Place save(Place place);
    //查询所有的场地
    public PageInfo<Place> places(int page,int rows);
    //根据Id查询
    public Place findPlaceById(Integer id);
    //根据名称来进行查询
    public PageInfo<Place> findPlaceByKey(String placeName,int page,int rows);
    //删除用户
    public Boolean deleteById(Integer id);
    //修改用户
    public Place updatePlaceById(Integer id, Place place);
    //查询条数
    public int countNum(int companyId);
}
