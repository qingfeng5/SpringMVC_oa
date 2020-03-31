package com.qingfeng.oa.biz.impl;

import com.qingfeng.oa.biz.DepartmentBiz;
import com.qingfeng.oa.dao.DepartmentDao;
import com.qingfeng.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层处理
 * Created by 清风
 * 2019/12/24 17:27
 */
@Service("departmentBiz")
public class DepartmentBizImpl implements DepartmentBiz {

    //声明departmentdao
    @Autowired
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void remove(String sn) {
        departmentDao.delete(sn);
    }

    public Department get(String sn) {
        return departmentDao.select(sn);
    }

    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
