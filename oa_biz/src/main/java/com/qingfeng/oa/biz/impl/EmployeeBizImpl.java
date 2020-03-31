package com.qingfeng.oa.biz.impl;

import com.qingfeng.oa.biz.EmployeeBiz;
import com.qingfeng.oa.dao.EmployeeDao;
import com.qingfeng.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * @author 清风
 * @date 2020/2/7 17:07
 */

@Service("employeeBiz")
public class EmployeeBizImpl implements EmployeeBiz {
    @Autowired
    private EmployeeDao employeeDao;

    public void add(Employee employee) {
        //当我们使用添加员工的时候要处理员工的密码
        //这是一个基本业务规则，写表现层不一定知道业务密码怎么填
        employee.setPassword("000000");
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
