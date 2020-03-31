package com.qingfeng.oa.biz.impl;

import com.qingfeng.oa.biz.GlobalBiz;
import com.qingfeng.oa.dao.EmployeeDao;
import com.qingfeng.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 清风
 * @date 2020/2/8 19:57
 */

@Service("globalBiz")
public class GlobalBizImpl implements GlobalBiz {
    @Autowired
    private EmployeeDao employeeDao;

    /**登录**/
    public Employee login(String sn, String password) {
        //调用employeeDao查询方法，查询这个用户sn
        Employee employee = employeeDao.select(sn);
        //如果这个employee不为空就是有这个用户
        //并且用户提交过来的密码，和已有密码是一样的，代表登录成功
        if(employee!=null&&employee.getPassword().equals(password)){
            return  employee;
        }
        return null;
    }

    /**修改密码**/
    public void changePassword(Employee employee) {
        employeeDao.update(employee);
    }
}
