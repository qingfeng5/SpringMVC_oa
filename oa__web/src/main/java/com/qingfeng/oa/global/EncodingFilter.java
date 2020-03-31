package com.qingfeng.oa.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 清风
 * 2019/12/3 9:27
 */
public class EncodingFilter implements Filter {
    //指定编码
    private String encoding = "utf-8";
    /**初始化方法*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //读取初始化参数，配置的参数中间以encoding为键指定编码，请求响应的时候按这个编码来处理，没有就指定默认
        if (filterConfig.getInitParameter("encoding")!= null){
            //不为空，就把这个值设定为指定的值
            encoding = filterConfig.getInitParameter("encoding");
        }
    }
    /**2、下面是拦截过滤**/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //接受的时候由utf-8接受，相应也是utf-8向用户相应
        //不过先把ServletRequest，ServletResponse强转化为httpServletResponse等
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //设置字符集
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        //设置好，调用拦截让他继续执行FilterChain
        filterChain.doFilter(request,response);
    }
    /**销毁**/
    @Override
    public void destroy() {
    }
}
