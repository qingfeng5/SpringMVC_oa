package com.qingfeng.oa.biz;

import com.qingfeng.oa.entity.ClaimVoucher;
import com.qingfeng.oa.entity.ClaimVoucherItem;
import com.qingfeng.oa.entity.DealRecord;

import java.util.List;

/**
 * 报销单业务层
 * @author 清风
 * @date 2020/2/14 19:39
 */
public interface ClaimVoucherBiz {
    /**保存报销单，第一个报销单的基本信息，第二个报销单的条目集合**/
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /**保存之后，要跳转详情，首先需要一个报销单对象**/
    ClaimVoucher get(int id);
    /**获取所有的报下单条目，传入的是报销单的编号**/
    List<ClaimVoucherItem> getItems(int cvid);
    /**审核记录**/
    List<DealRecord> getRecords(int cvid);


    /**获取个人报销单**/
    List<ClaimVoucher> getForSelf(String sn);
    /**获取待处理报销单**/
    List<ClaimVoucher> getForDeal(String sn);

    /**修改报销单跟保存报销单结构是一样的，报销单的基本信息，第二个报销单的条目集合**/
    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /**提交操作**/
    void submit(int id);

    /**审核操作**/
    void deal(DealRecord dealRecord);
}
