package com.qingfeng.oa.biz;

import com.qingfeng.oa.entity.Employee;

/**
 * 业务层
 * 登录与个人中心
 * @author 清风
 * @date 2020/2/8 19:40
 */
public interface GlobalBiz {
    //登录，告诉用户名和密码
    Employee login(String sn, String password);

    //退出一般是session操作，session是表现层的结构，
    // 通常不会再业务层，同样个人信息也是一样

    //最后一个，修改密码，
    //这里直接声明对象，表现层用起来也方便
    void changePassword(Employee employee);
}
