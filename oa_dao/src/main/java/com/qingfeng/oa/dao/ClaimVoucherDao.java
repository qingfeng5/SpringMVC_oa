package com.qingfeng.oa.dao;

import com.qingfeng.oa.entity.ClaimVoucher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报销单接口
 * @author 清风
 * @date 2020/2/9 19:47
 */
@Repository("claimVoucherDao")
public interface ClaimVoucherDao {
    void insert(ClaimVoucher claimVoucher);
    void update(ClaimVoucher claimVoucher);
    void delete(int id);
    ClaimVoucher select(int id);

     /** 查询某个创建者的报销单**/
    List<ClaimVoucher> selectByCreateSn(String csn);
    /** 查询某个人能处理的报销单**/
    List<ClaimVoucher> selectByNextDealSn(String ndsn);
}
