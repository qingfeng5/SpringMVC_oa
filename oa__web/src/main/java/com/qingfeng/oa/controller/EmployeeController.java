package com.qingfeng.oa.controller;

import com.qingfeng.oa.biz.DepartmentBiz;
import com.qingfeng.oa.biz.EmployeeBiz;
import com.qingfeng.oa.entity.Employee;
import com.qingfeng.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author 清风
 * @date 2020/2/8 15:53
 */

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private DepartmentBiz departmentBiz;
    @Autowired
    private EmployeeBiz employeeBiz;

    /**员工信息**/
    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        map.put("list",employeeBiz.getAll());
        return "employee_list";
    }

    /**打开添加页面**/
    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("employee",new Employee());
        //添加员工需要为员工设置部门，需要把所有部门传递到添加页面去
        map.put("dlist",departmentBiz.getAll());
        //员工选择职位，职位也要放置大到添加页面去
        //位置在常量类里面找Contant，getPosts是一个静态方法static
        map.put("plist", Contant.getPosts());
        return "employee_add";
    }

    /**添加方法**/
    @RequestMapping("/add")
    public String add(Employee employee){
        employeeBiz.add(employee);
        return "redirect:list";
    }

    /**打开修改界面**/
    @RequestMapping(value = "/to_update",params = "sn")
    public String toUpdate(String sn,Map<String,Object> map){
        map.put("employee",employeeBiz.get(sn));
        //修改界面也需要传递部门，职位信息
        map.put("dlist",departmentBiz.getAll());
        map.put("plist", Contant.getPosts());
        return "employee_update";
    }

    /**更新方法**/
    @RequestMapping("/update")
    public String update(Employee employee){
        employeeBiz.edit(employee);
        return "redirect:list";
    }

    /**删除方法**/
    @RequestMapping(value = "/remove",params = "sn")
    public String remove(String sn){
        employeeBiz.remove(sn);
        return "redirect:list";
    }

}
