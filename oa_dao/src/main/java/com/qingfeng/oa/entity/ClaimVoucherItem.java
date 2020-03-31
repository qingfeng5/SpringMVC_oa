package com.qingfeng.oa.entity;

/**
 * 报销单条目实体类
 * Created by 清风
 * 2019/12/24 11:28
 */
public class ClaimVoucherItem {
    /**编号**/
    private Integer id;

    /**报销单编号**/
    private Integer claimVoucherId;

    /**费用类型**/
    private String item;

    /**金额**/
    private Double amount;

    /**说明**/
    private String comment;

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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
