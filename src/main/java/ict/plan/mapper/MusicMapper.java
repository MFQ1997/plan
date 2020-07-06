package ict.plan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Music;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusicMapper extends BaseMapper<Music> {
    //新增
    public Integer save(Music music);
    //根据主键Id进行删除
    public boolean deleteById(Integer id);
    //更新信息
    public boolean Update(Music music);
    //根据公司id获取该公司下的所有的音频信息，并且进行分页返回
    public PageInfo<Music> selectMusicByCompanyId(Integer companyId);
    //查询条数
    public int countNum(int companyId);
}
