package com.qingfeng.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 处理记录实体类
 * Created by 清风
 * 2019/12/24 11:28
 */
public class DealRecord {
    /**编号**/
    private Integer id;

    /**报销单编号**/
    private Integer claimVoucherId;

    /**处理人编号  表现层处理的时候不能用编号**/
    private String dealSn;

    /**处理时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date dealTime;

    /**处理方式**/
    private String dealWay;

    /**处理结果**/
    private String dealResult;

    /**处理的意见或者备注**/
    private String comment;

    /**表现层处理的时候不能用编号
     * 声明一个Employee类型的dealer来进行数据传递**/
    private Employee dealer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaimVoucherId() {
        return claimVoucherId;
    }

    public void setClaimVoucherId(Integer claimVoucherId) {
        this.claimVoucherId = claimVoucherId;
    }

    public String getDealSn() {
        return dealSn;
    }

    public void setDealSn(String dealSn) {
        this.dealSn = dealSn;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public Employee getDealer() {
        return dealer;
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }
}
