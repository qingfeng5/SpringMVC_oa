package com.qingfeng.oa.dao;

import com.qingfeng.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 清风
 * @date 2020/2/7 16:46
 */

@Repository("employeeDao")
public interface EmployeeDao {
    void insert(Employee employee);
    void update(Employee employee);
    void delete(String sn);
    Employee select(String sn);
    List<Employee> selectAll();

    /**根据部门编号或职位，获取多个员工**/
    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn, @Param("post") String post);

}
