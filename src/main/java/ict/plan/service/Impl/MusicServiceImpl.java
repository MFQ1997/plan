package ict.plan.service.Impl;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Music;
import ict.plan.mapper.MusicMapper;
import ict.plan.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;


    @Override
    public Music save(Music music) {
        music.setUploadTime(new Date());
        music.setUpdateTime(new Date());
        int result = musicMapper.save(music);
        if (result>0)
            return music;
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public boolean Update(Music music) {
        return false;
    }

    @Override
    public PageInfo<Music> selectMusicByCompanyId(Integer companyId) {
        return null;
    }

    @Override
    public int countNum(int companyId) {
        return musicMapper.countNum(companyId);
    }
}
