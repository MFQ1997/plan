package ict.plan.service;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Customer;
import ict.plan.entity.po.Music;

public interface MusicService {
    //新增
    public Music save(Music music);
    //根据主键Id进行删除
    public boolean deleteById(Integer id);
    //更新信息
    public boolean Update(Music music);

    //根据公司id获取该公司下的所有的音频信息，并且进行分页返回
    public PageInfo<Music> selectMusicByCompanyId(Integer companyId);
    //查询条数
    public int countNum(int companyId);
}
