package com.qingfeng.oa.controller;

import com.qingfeng.oa.biz.DepartmentBiz;
import com.qingfeng.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentBiz departmentBiz;
    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        //你可以什么都不做但是你想使用springmvc的home标签
        //那就必须给传递一个对象
        map.put("list",departmentBiz.getAll());
        return "department_list";
//        创建department_list.jsp
    }

    /**
     *添加
     */
    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("department",new Department());
        return "department_add";
    }

    @RequestMapping("/add")
    public String add(Department department){
        //当用户在提交页面条款数据之后，交给控制器
        //springmvc控制器自动把这个信息封装到对象里
        //到对象里后，直接调用业务层的add方法
        departmentBiz.add(department);
        //添加完成后，返回list列表界面，直接返回add是没有值的
        //重定向到list这个控制器
        return "redirect:list";
    }

    /**
     * 去修改
     * **/
    @RequestMapping(value = "/to_update",params = "sn")
    //要知道修改哪个，参数sn定义下，必须传递这个参数
    public String toUpdate(String sn,Map<String,Object> map){
        map.put("department",departmentBiz.get(sn));
        return "department_update";
    }
    @RequestMapping("/update")
    public String update(Department department){
        departmentBiz.edit(department);
        return "redirect:list";
    }
    @RequestMapping(value = "/remove",params = "sn")
    public String remove(String sn){
        departmentBiz.remove(sn);
        return "redirect:list";
    }
}
