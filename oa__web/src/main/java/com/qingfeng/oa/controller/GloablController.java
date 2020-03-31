package com.qingfeng.oa.controller;

import com.qingfeng.oa.biz.GlobalBiz;
import com.qingfeng.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 * @author 清风
 * @date 2020/2/8 20:11
 * 第二步 登录用户，要对所有的url进行限制，进行登录拦截
 *       这里使用拦截器来处理LoginInterceptor
 */

@Controller("globalController")
public class GloablController {

    @Autowired
    private GlobalBiz globalBiz;

    /**登录界面**/
    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    /**登录**/
    @RequestMapping("login")
    //这里用户传来sn和password，多个参数就需要注解RequestParam，
    // RequestParam来标明两个参数都是用来提交数据缓存的
    //HttpSession开头声明就会直接把session注入进来
    public String login(HttpSession session,
                        @RequestParam String sn,
                        @RequestParam String password){
        //把业务层的登录的密码和账户交给她
        Employee employee = globalBiz.login(sn,password);
        if (employee == null) {
//            登录失败重新去登录
            return "redirect:to_login";
        }
        //登录成功后要保存当前用户数据，保存到session重
        session.setAttribute("employee",employee);
//        登录成功后跳转到个人信息界面
        return "redirect:self";
    }

    /**个人信息界面**/
    @RequestMapping("self")
    public String self(){
        return  "self";
    }


    /**退出**/
    @RequestMapping("quit")
    public String quit(HttpSession session){
        session.setAttribute("employee",null);
        return "redirect:to_login";
    }

    /**进行修改密码**/
    @RequestMapping("to_change_password")
    public String toChangePassword(){
        return "change_password";
    }

    /**修改密码**/
    @RequestMapping("change_password")
    //用户提交过来的用户密码，和两个新密码
    public String changePassword(HttpSession session, @RequestParam String old,
                                 @RequestParam String new1 ,@RequestParam String new2){
        Employee employee = (Employee)session.getAttribute("employee");
        //判断当前输入的密码是不是你输入的旧密码
        if(employee.getPassword().equals(old)){
            //判断两个新输入的密码是否相等
            if(new1.equals(new2)){
                //这样就可以去修改密码
                //把用户密码修改为新密码
                employee.setPassword(new1);
                globalBiz.changePassword(employee);
                //修改成功后重定向到个人信息界面
                return "redirect:self";
            }
        }
        //上述都不对，就是输入密码不对，两次输入密码不对，
//        重定向到输入密码界面
        return "redirect:to_change_password";
    }

}
