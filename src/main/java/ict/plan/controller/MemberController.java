package ict.plan.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import ict.plan.core.bean.RestResponse;
import ict.plan.core.util.DBConnStrUtil;
import ict.plan.entity.po.Company;
import ict.plan.entity.po.Member;
import ict.plan.entity.po.Quota;
import ict.plan.entity.vo.MemberVO;
import ict.plan.service.CompanyService;
import ict.plan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController extends BaseRestController{

    @Autowired
    private MemberService memberService;

    @Autowired
    private CompanyService companyService;

    /*
    * @Description:这个是新增会员等级
    * @Date：2019
    * */
    @PostMapping
    public RestResponse save(@RequestBody MemberVO member){
        //将可操作性模块或者定额度信息存储为json信息
        Quota quota = member.getQuotaObject();
        String quotaJson = JSON.toJSONString(quota);
        member.setQuota(quotaJson);

        //操作模块id
        int [] permissionIds = member.getIds();
        String permissionStr =  Arrays.toString(permissionIds);
        member.setModule(permissionStr);

        boolean save = memberService.save(member);
        if (save)
            return success("新增成功");
        return fail("操作失败","01",null);
    }

    /*
    * @Description:这个是获取所有的会员等级信息
    * @Data:2019
    * */
    @GetMapping
    public RestResponse<List<Member>> getAllMember() {
        List<Member> memberList = memberService.getAllMember();
        for (Member member: memberList) {
            System.out.println(member.getQuota());

        }
        return success(memberList);
    }

    /*
    * @Description:这个是获取用户所在的公司的收费等级,将返回结果中的操作模块的ID和全部权限的Id进行交集，则可以得出当前用户可以操作的模块
    * @ReturnValue:用户可以操作的模块（权限）
    * */
    @GetMapping("/level")
    public RestResponse<Member> getMemberByCompanyId(@RequestParam(value="id")int companyId) {

        //根据公司的Id查出公司
        Company company = companyService.findCompanyById(companyId);
        //拿出公司的收费等级
        int chargeLevel = company.getChargeLevel();
        //拿出公司的收费等级之后到会员模块里面查询可操作模块
        Member member = memberService.findMemberById(chargeLevel);
        return success(member);
    }

    /*
    * @Description:这个是用户切换等级之后的消费记录信息
    * @Date:2019
    * */
    public RestResponse changeLevel(int companyId,int memberId){

        return success("切换消费等级成功");
    }


}
