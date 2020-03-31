package com.qingfeng.oa.biz;

import com.qingfeng.oa.entity.Department;

import java.util.List;

/**
 * 业务层包含biz接口和实现类
 * Created by 清风
 * 2019/12/24 16:48
 */
public interface DepartmentBiz {

    //添加
    void add(Department department);
    //修改
    void edit(Department department);
    void remove(String sn);
    //去部门信息，给一个部门信息
    Department get(String sn);
    //查看所有部门的信息
    List<Department> getAll();
}
