package com.qingfeng.oa.dao;

import com.qingfeng.oa.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口
 * Created by 清风
 * 2019/12/24 11:31
 */
//departmentdao会作为持久化操作对象放置在springioc容器，这里注解下
@Repository("departmentDao")
public interface DepartmentDao {
    /**增删改查**/
    void insert(Department department);
    void update(Department department);
    void delete(String sn);
    Department select(String sn);
    List<Department> selectAll();
}
