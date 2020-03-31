package com.qingfeng.oa.dao;

import com.qingfeng.oa.entity.ClaimVoucherItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报销单条目
 * @author 清风
 * @date 2020/2/9 19:50
 */

@Repository("claimVoucherItemDao")
public interface ClaimVoucherItemDao {

    void insert(ClaimVoucherItem claimVoucherItem);
    void update(ClaimVoucherItem claimVoucherItem);
    void delete(int id);

    /**根据报销单编号来进行报销单查询**/
    List<ClaimVoucherItem> selectByClaimVoucher(int cvid);
}
