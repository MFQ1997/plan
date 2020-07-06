package ict.plan.controller;

import com.sun.net.httpserver.Authenticator;
import ict.plan.core.annotation.SysLog;
import ict.plan.core.bean.RestResponse;
import ict.plan.core.exception.DefaultException;
import ict.plan.core.util.CaptchaUtil;
import ict.plan.core.util.RandomValidateCodeUtil;
import ict.plan.entity.shiro.User;
import ict.plan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/*
 * @Name:LoginController
 * @Description:负责处理登录相关的控制器
 * @Date：2019-11
 * */

@RestController
public class LoginController extends BaseRestController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /*
    * @Name:login
    * @Description:这个是登录的验证接口,当验证通过之后就将该用户对应的角色所拥有权限返回给前端进行管理
    * @param:User(Class类型)
    * @ReturnType：T
    * */
    @SysLog(value = "登录操作")  //这里添加了AOP的自定义注解
    @RequestMapping("/login")
    @ResponseBody
    public RestResponse<User> login(String userName,String password) {
        System.out.println("开始登陆");
        /*CaptchaUtil.Captcha captcha = CaptchaUtil.createCaptcha(50,30,4,1,40);
        System.out.println("这个验证码是："+captcha);*/
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        //模拟前端传递过来的用户登录信息
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            User userObject = userService.findOneUserByName(userName);
            System.out.println("这个用户的公司Id为："+userObject.getCompanyId());
            return success(userObject);
            //subject.checkRole("admin");
            //subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            //e.printStackTrace();
            return fail("账号或密码错误","01",null);
        } catch (AuthorizationException e) {
            //e.printStackTrace();
            return fail("没有权限","01",null);
        }
    }

    /*
    * @Description:这个是生成验证码的API接口
    * @ReturnValue:img
    * */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            throw new DefaultException("创建验证码失败");
        }
    }

    /**
     * @Description:忘记密码页面校验验证码
     */
    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST, headers = "Accept=application/json")
    public boolean checkVerify(@RequestBody Map<String, Object> requestMap, HttpSession session) {
        try{
            //从session中获取随机数
            String inputStr = requestMap.get("inputStr").toString();
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return false;
            }
            if (random.equals(inputStr)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    /*
    * @Name:logout
    * @Description:这个是退出登录的方法
    * */
    @ResponseBody
    @RequestMapping("/logout")
    public RestResponse logout(){
        //开始清除session的信息
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        //重定向页面
        return success("退出成功");
    }

    /*
    * @Name:changePwd()
    * @Description:这个是修改密码的方法
    * @Param:userId(int),password(String)
    * */
    @ResponseBody
    @PutMapping
    public RestResponse changePwd(int userId,String password){
        return success("操作成功");
    }

    /*
    * @Description:这个是未授权的跳转，返回未授权提示信息
    * @ReturnType:T(泛型)
    * */
    @ResponseBody
    @RequestMapping("/unAuth")
    public RestResponse unAuth(){
        return fail("对不起，你没有权限访问","01",null);
    }
}
