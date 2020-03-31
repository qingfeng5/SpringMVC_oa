package com.qingfeng.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * ClaimVoucher报销单实体类
 * Created by 清风
 * 2019/12/24 11:27
 */
public class ClaimVoucher {
    /**编号，这是mysql自动生成的编号**/
    private Integer id;

    /**事由**/
    private String cause;

    /**创建者，这个编号用来关联employee对象
     * 这两个字段不能直接再表现层上面做呈现**/
    private String createSn;

    /**创建时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date createTime;

    /**待处理人，这个编号用来关联employee对象**/
    private String nextDealSn;

    /**金额**/
    private Double totalAmount;

    /**状态**/
    private String status;

    /**声明两个关联对象的属性，创建者和处理人**/
    private Employee creater;
    private Employee dealer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCreateSn() {
        return createSn;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNextDealSn() {
        return nextDealSn;
    }

    public void setNextDealSn(String nextDealSn) {
        this.nextDealSn = nextDealSn;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Employee getCreater() {
        return creater;
    }

    public void setCreater(Employee creater) {
        this.creater = creater;
    }

    public Employee getDealer() {
        return dealer;
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }

}
