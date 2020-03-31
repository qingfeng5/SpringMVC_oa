package com.qingfeng.oa.dto;

import com.qingfeng.oa.entity.ClaimVoucher;
import com.qingfeng.oa.entity.ClaimVoucherItem;

import java.util.List;

/**
 * 报销单的数据
 * @author 清风
 * @date 2020/2/14 20:07
 */
public class ClaimVoucherInfo {
    /**用下面的形式来声明用户提交过来的信息**/
    /**报销单的基本信息**/
    private ClaimVoucher claimVoucher;
    /**一系列的条目信息**/
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
