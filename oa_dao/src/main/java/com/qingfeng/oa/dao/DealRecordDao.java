package com.qingfeng.oa.dao;

import com.qingfeng.oa.entity.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理记录接口
 * @author 清风
 * @date 2020/2/9 19:53
 */
@Repository("dealRecordDao")
public interface DealRecordDao {
    //处理记录是不能删除的，也不能修改
    void insert(DealRecord dealRecord);

    /**针对某一个报销单查询它的处理的流程**/
    List<DealRecord> selectByClaimVoucher(int cvid);

}
