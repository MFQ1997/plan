package ict.plan.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Member;
import ict.plan.entity.po.Quota;
import ict.plan.mapper.MemberMapper;
import ict.plan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> getAllMember() {

        return memberMapper.getAllMember();
    }

    @Override
    public boolean save(Member member) {

        boolean save = memberMapper.save(member);
        if (save)
            return true;
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public Member findMemberById(Integer id) {
        return memberMapper.findMemberById(id);
    }

    @Override
    public Member update(Member member) {
        return null;
    }
}
