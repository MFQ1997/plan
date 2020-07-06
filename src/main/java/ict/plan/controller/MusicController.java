package ict.plan.controller;

/*
* @Description:这个是音频管理的控制器
* @Date:2019
* */

import ict.plan.core.bean.RestResponse;
import ict.plan.core.util.DBConnStrUtil;
import ict.plan.entity.po.Music;
import ict.plan.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
public class MusicController extends BaseRestController{

    @Autowired
    private MusicService musicService;

    @PostMapping
    public RestResponse save(@RequestBody Music music) throws Exception{
        //设置从库数据源，逻辑处理，这里可根据项目设置优化
        //参数1为从库1的dbID，需更改
        DBConnStrUtil.setDataSourceByCompanyID(1);
        Music save = musicService.save(music);
        if(save != null)
            return success("新增成功");
        return fail("操作失败","01",null);
    }

    @GetMapping("/num")
    public RestResponse countNum(int companyId){
        int resultNum = musicService.countNum(companyId);
        return success(resultNum);
    }
}

