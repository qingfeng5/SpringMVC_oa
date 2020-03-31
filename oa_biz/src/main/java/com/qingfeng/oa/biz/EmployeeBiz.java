package com.qingfeng.oa.biz;

import com.qingfeng.oa.entity.Employee;

import java.util.List;

/**
 * 业务层接口
 * @author 清风
 * @date 2020/2/7 17:06
 */
public interface EmployeeBiz {
    void add(Employee employee);
    void edit(Employee employee);
    void remove(String sn);
    Employee get(String sn);
    List<Employee> getAll();
}
