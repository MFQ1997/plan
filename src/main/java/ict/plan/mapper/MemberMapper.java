package ict.plan.mapper;

import com.github.pagehelper.PageInfo;
import ict.plan.entity.po.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public List<Member> getAllMember();
    public boolean save(Member member);
    public boolean deleteById(Integer id);
    public Member findMemberById(Integer id);
    public Member update(Member member);

}
