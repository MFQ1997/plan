package ict.plan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Place;
import ict.plan.entity.shiro.User;
import ict.plan.mapper.PlaceMapper;
import ict.plan.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;


    /*
    * @Description:这个是添加场地的服务
    * @Param:place 场地的实体类
    * @ReturnValue:place 新增的数据对象
    * */
    @Override
    public Place save(Place place) {
        Integer save = placeMapper.save(place);
        if(save != null)
            return place;
        return null;
    }

    /*
    * @Description:这是分页查询出所有的场地的服务
    * @Param：page 显示哪一页的内容
    * @Param：rows 显示多少条数据
    * @ReturnValue:pageData 分页之后的数据
    * */
    @Override
    public PageInfo<Place> places(int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Place> places = placeMapper.places();
        //用PageInfo对结果进行包装
        PageInfo<Place> pageData = new PageInfo<Place>(places);
        return pageData;
    }

    @Override
    public Place findPlaceById(Integer id) {
        return placeMapper.findPlaceById(id);
    }

    /*
    * @Description:根据关键字进行模糊查询
    * @Param:key 查询的关键字
    * @Param:page 查询第几页的内容
    * @Param:rows 一页显示多少条数据
    * @ReturnValue:pageData 分页之后的数据列表
    * */
    @Override
    public PageInfo<Place> findPlaceByKey(String key,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Place> places = placeMapper.findPlaceByKey(key);
        PageInfo<Place> pageData = new PageInfo<Place>(places);
        return pageData;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return placeMapper.deleteById(id);
    }

    @Override
    public Place updatePlaceById(Integer id, Place place) {
        return null;
    }

    @Override
    public int countNum(int companyId) {
        return placeMapper.countNum(companyId);
    }
}
