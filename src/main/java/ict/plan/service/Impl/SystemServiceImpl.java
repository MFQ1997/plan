package ict.plan.service.Impl;


import ict.plan.entity.po.Qiniu;
import ict.plan.entity.po.Setting;
import ict.plan.mapper.SystemMapper;
import ict.plan.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemMapper systemMapper;

    @Override
    public Setting getSetting() {
        return systemMapper.getSetting();
    }

    @Override
    public List<Qiniu> qiniuList() {
        return systemMapper.qiniuList();
    }

    @Override
    public Qiniu findQiniuById(Integer id) {
        return systemMapper.findQiniuById(id);
    }

    @Override
    public boolean saveQiniu(Qiniu qiniu) {
        Integer qiniuAfter = systemMapper.saveQiniu(qiniu);
        if (qiniuAfter>0)
            return true;
        return false;
    }

    @Override
    public boolean deleteQiniuById(Integer id) {
        return systemMapper.deleteQiniuById(id);
    }

    @Override
    public boolean updateQiniu(Qiniu qiniu) {
        int result = systemMapper.updateQiniu(qiniu);
        if(result >0){
            return true;
        }
        return false;
    }
}
