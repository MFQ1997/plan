package ict.plan.controller;

import ict.plan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/*
* @Author:xiaomai
* @Name:IndexController
* @Description:这个是后台首页的控制器，负责初始化一些系统的配置信息
* @Date:2019-11
*
* */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name","策划通");
        return "admin/index/index";
    }


}
