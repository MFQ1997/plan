package ict.plan.service;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Member;
import ict.plan.entity.po.Quota;

import java.util.List;

public interface MemberService {

    public List<Member> getAllMember();
    public boolean save(Member member);
    public boolean deleteById(Integer id);
    public Member findMemberById(Integer id);
    public Member update(Member member);

}
