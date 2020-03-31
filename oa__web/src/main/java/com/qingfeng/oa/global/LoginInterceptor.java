package com.qingfeng.oa.global;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录的拦截器接口
 * qingfeng
 * 作用就是拦截，只有登录才能访问
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 只有登录才能访问那就是再处理前进行拦截
     * **/
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        //对登录界面的控制器进行放权，打开登录界面，登录操作可以访问
        //这些路径设计去登录的控制器，处理登陆的控制器
        //登录上面都有login这个，首先过滤这个东西
        //getRequestURI()是获取当前请求的路径
        String url = httpServletRequest.getRequestURI();
        //拿到这个关键字后，查询这个路径里面有没有关键字为login
        //大于等0，代表有这个login
        if(url.toLowerCase().indexOf("login")>=0){
            //有可能login有大小写，先把url转换为小写，用toLowerCase
            //直接放行
            return true;
        }
        //剩下的路径登录以后才能访问
        //通过session来判断有没有登录
        HttpSession session = httpServletRequest.getSession();
        //这个已经登录过了，不为空
        if(session.getAttribute("employee")!=null){
            return true;
        }
        //既不是相关login，也不是其他路径，这个就是拦截，回到重新登录去
        httpServletResponse.sendRedirect("/to_login");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
