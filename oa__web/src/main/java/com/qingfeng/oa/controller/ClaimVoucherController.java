package com.qingfeng.oa.controller;

import com.qingfeng.oa.biz.ClaimVoucherBiz;
import com.qingfeng.oa.dto.ClaimVoucherInfo;
import com.qingfeng.oa.entity.DealRecord;
import com.qingfeng.oa.entity.Employee;
import com.qingfeng.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 报销单控制器
 * @author 清风
 * @date 2020/2/14 20:17
 */
@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {
    @Autowired
    private ClaimVoucherBiz claimVoucherBiz;

    /**打开保险单这个页面**/
    @RequestMapping("/to_add")
    //小需要所有的类型，要传递用Map
    public String toAdd(Map<String,Object> map){
        map.put("items", Contant.getItems());
        map.put("info",new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    /**保险单界面**/
    @RequestMapping("/add")
    public String add(HttpSession session, ClaimVoucherInfo info){
        //保存处理之前，创建者编号可以从sessi获取，前面要加httpsession
        Employee employee = (Employee)session.getAttribute("employee");
        //把保险单的编号，设置为用户的编号
        info.getClaimVoucher().setCreateSn(employee.getSn());
        //业务处理对象的save方法，拿到保险单，保险单条目
        claimVoucherBiz.save(info.getClaimVoucher(),info.getItems());
//        return "redirect:deal?id="+info.getClaimVoucher().getId();
        return "redirect:deal";
    }

    /**保险单详细**/
    @RequestMapping("/detail")
    public String detail(int id, Map<String,Object> map){
        map.put("claimVoucher",claimVoucherBiz.get(id));
        map.put("items",claimVoucherBiz.getItems(id));
        map.put("records",claimVoucherBiz.getRecords(id));
        //保存完后跳转到详情页面
        return "claim_voucher_detail";
    }

    /**个人报销单**/
    @RequestMapping("/self")
    public String self(HttpSession session, Map<String,Object> map){
        //获取当前登录用户
        Employee employee = (Employee)session.getAttribute("employee");
        //根据当前用户的编号来获取当前用户创建的保险单
        map.put("list",claimVoucherBiz.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }

    /**待处理**/
    @RequestMapping("/deal")
    public String deal(HttpSession session, Map<String,Object> map){
        Employee employee = (Employee)session.getAttribute("employee");
        map.put("list",claimVoucherBiz.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }

    /**打开修改的界面**/
    @RequestMapping("/to_update")
    //修改是必须知道是那个报销单，必须已id为界传递报销单编号
    public String toUpdate(int id,Map<String,Object> map){
        //费用类型
        map.put("items", Contant.getItems());
        //传递对象
        ClaimVoucherInfo info =new ClaimVoucherInfo();
        //报销单基本信息属性
        info.setClaimVoucher(claimVoucherBiz.get(id));
        //报销单的条目属性
        info.setItems(claimVoucherBiz.getItems(id));
        //传递到页面上去
        map.put("info",info);
        return "claim_voucher_update";
    }

    /**修改界面**/
    @RequestMapping("/update")
    public String update(HttpSession session, ClaimVoucherInfo info){
        //从session把当前用户拿出来
        Employee employee = (Employee)session.getAttribute("employee");
        //把当前用户的属性设置进去
        info.getClaimVoucher().setCreateSn(employee.getSn());
        //调用业务处理的报销单方法，更新属性
        claimVoucherBiz.update(info.getClaimVoucher(),info.getItems());
        //跳转到待处理页面去
        return "redirect:deal";
    }

    /**提交**/
    @RequestMapping("/submit")
    public String submit(int id){
        claimVoucherBiz.submit(id);
        //返回待处理页面
        return "redirect:deal";
    }

    /**去打开审核的界面**/
    @RequestMapping("/to_check")
    public String toCheck(int id,Map<String,Object> map){
        map.put("claimVoucher",claimVoucherBiz.get(id));
        map.put("items",claimVoucherBiz.getItems(id));
        map.put("records",claimVoucherBiz.getRecords(id));
        //设置当前的报销单编号
        DealRecord dealRecord =new DealRecord();
        dealRecord.setClaimVoucherId(id);
        map.put("record",dealRecord);
        return "claim_voucher_check";
    }

    /**审核界面**/
    @RequestMapping("/check")
    public String check(HttpSession session, DealRecord dealRecord){
        Employee employee = (Employee)session.getAttribute("employee");
        dealRecord.setDealSn(employee.getSn());
        claimVoucherBiz.deal(dealRecord);
        return "redirect:deal";
    }
}
