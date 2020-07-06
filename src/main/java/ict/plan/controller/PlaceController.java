package ict.plan.controller;

import com.github.pagehelper.PageInfo;
import ict.plan.core.bean.RestResponse;
import ict.plan.core.util.DBConnStrUtil;
import ict.plan.entity.po.Place;
import ict.plan.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* @Name：PlaceController
* @Description:这是管理场地信息的控制器
* @Author:xiaomai
* @Date:2019-11
* */

@RestController
@RequestMapping("/place")
public class PlaceController extends BaseRestController{

    @Autowired
    private PlaceService placeService;

    /*
    * @Description:新增一个场地
    * @Param:place
    * @ReturnValue:place 新增成功的场地对象
    * */
    @PostMapping
    @ResponseBody
    public RestResponse<Place> save(@RequestBody Place place) throws Exception{
        DBConnStrUtil.setDataSourceByCompanyID(1);
        Place save = placeService.save(place);
        if(save!=null){
            return success(place);
        }
        return fail("操作失败","01",null);
    }

    /*
    * @Description:删除一个场地信息
    * @param：id 根据id进行删除
    * @ReturnType:message(String) 提示信息的字符串
    * */
    @DeleteMapping("/{id}")
    @ResponseBody
    public RestResponse delete(@PathVariable("id") int id) throws Exception{
        DBConnStrUtil.setDataSourceByCompanyID(1);
        System.out.println("操作的Id"+id);
        if (placeService.deleteById(id))
            return success("操作成功");
        return fail("操作失败","01",null);
    }

    /*
    * @Description:根据id进行修改一个场地信息
    * @Param:place 场地的对象
    * */
    @PutMapping
    @ResponseBody
    public Place update(@PathVariable("id") int id,@RequestBody Place place) throws Exception{
        DBConnStrUtil.setDataSourceByCompanyID(1);
        placeService.updatePlaceById(id,place);
        return place;
    }

    /*
    * @Description:获取所有的场地
    * @Param:page 要显示第几页的内容
    * @Parma:rows 一页显示多少条
    * @ReturnType:List
    * */
    @GetMapping
    @ResponseBody
    public RestResponse<PageInfo<Place>> places(@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows) throws Exception{
        DBConnStrUtil.setDataSourceByCompanyID(1);
        PageInfo<Place> places = placeService.places(page,rows);
        return success(places);
    }

    /*
    * @Description:根据Id字段来查看相应的场地信息（查看场地的详细信息）
    * @Param:id 场地唯一ID
    * @ReturnValue:place 根据id获取出来的数据对象
    * */
    @GetMapping("/id/{id}")
    @ResponseBody
    public RestResponse<Place> findPlaceById(@PathVariable("id") int id) throws Exception{
        DBConnStrUtil.setDataSourceByCompanyID(1);
        Place place =  placeService.findPlaceById(id);
        return success(place);
    }

    /*
    * @Description:这个是根据公司Id获取出场地的数量
    * @Param:公司的Id
    * */
    @GetMapping("/num")
    public RestResponse getPlaceNumByCompanyId(int companyId) throws Exception{
        DBConnStrUtil.setDataSourceByCompanyID(1);
        int resultNum = placeService.countNum(companyId);
        return success(resultNum);
    }


    /*
    * @Description:这个是根据名称进行模糊查询
    * @Param:key 模糊查询的关键字
    * @Param:page 要显示第几页的内容
    * @Parma:rows 一页显示多少条
    * */
    @GetMapping("/key/{key}")
    @ResponseBody
    public RestResponse<PageInfo<Place>> findPlaceByKey(@PathVariable("key") String key,@RequestParam(value="page",defaultValue="1")int page, @RequestParam(value="rows",defaultValue="10")int rows)throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        PageInfo<Place> places = placeService.findPlaceByKey(key,page,rows);
        return success(places);
    }

}
