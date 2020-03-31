package com.qingfeng.oa.biz.impl;

import com.qingfeng.oa.biz.ClaimVoucherBiz;
import com.qingfeng.oa.dao.ClaimVoucherDao;
import com.qingfeng.oa.dao.ClaimVoucherItemDao;
import com.qingfeng.oa.dao.DealRecordDao;
import com.qingfeng.oa.dao.EmployeeDao;
import com.qingfeng.oa.entity.ClaimVoucher;
import com.qingfeng.oa.entity.ClaimVoucherItem;
import com.qingfeng.oa.entity.DealRecord;
import com.qingfeng.oa.entity.Employee;
import com.qingfeng.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 报销单的实现类
 * @author 清风
 * @date 2020/2/14 19:45
 */

@Service("cliamVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {
    @Autowired
    private ClaimVoucherDao claimVoucherDao;
    @Autowired
    private ClaimVoucherItemDao claimVoucherItemDao;
    @Autowired
    private DealRecordDao dealRecordDao;
    @Autowired
    private EmployeeDao employeeDao;

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        //创建时间
        claimVoucher.setCreateTime(new Date());
        //待处理人，设置为创建者
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        //创建状态，新创建"状态
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        //调用insert方法保存到数据库去
        claimVoucherDao.insert(claimVoucher);

        //声明一个item变量来接受这个集合里面迭代出来的元素
        for(ClaimVoucherItem item:items){
            //报销单编号
            item.setClaimVoucherId(claimVoucher.getId());
            //再将item保存一下
            claimVoucherItemDao.insert(item);
        }
    }

    /**获取报销单对象**/
    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    /**获取报销单对应的条目**/
    public List<ClaimVoucherItem> getItems(int cvid) {
        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    /**获取处理记录**/
    public List<DealRecord> getRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    /**获取个人报销单，调用的是持久化操作对象，创建者编号**/
    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    /**获取待处理报销单，调用的是持久化操作对象，待处理人编号**/
    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }

    /**修改报销单，跟保存报销单结构是一样的，报销单的基本信息，第二个报销单的条目集合**/
    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        //这是报销单信息
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        // 设置状态CLAIMVOUCHER_CREATED="新创建"状态
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        //调用保险单的update方法
        claimVoucherDao.update(claimVoucher);

        //下面是条目集合
        /**有两个条目集合，一个是现在传递过来的集合，还有一个以前保存数据库条目集合
        *把数据库里面的条目变成传递过来的集合
        *先进行判断，如果在数据库里面已有的条目集合里面拿出每一个条目来判断
        *如果那个条目不在指责给集合里，就不要了，就要删掉，然后再把这个条目插入到数据库里面去 **/
        //首先获取已有的数据库集合
        List<ClaimVoucherItem> olds = claimVoucherItemDao.selectByClaimVoucher(claimVoucher.getId());
        //迭代这个集合
        for(ClaimVoucherItem old:olds){
            //判断当前获取得到条目是否不存在在这个集合里面
            boolean isHave=false;
            //迭代现有的更新的集合
            for(ClaimVoucherItem item:items){
                //如果存在，就跳出循环
                if(item.getId()==old.getId()){
                    isHave=true;
                    break;
                }
            }
            //在判断是否还是false，就是迭代还没有找到，在新的集合里面都没有找到这个条目
            if(!isHave){
                //代表这个条目应该被删掉
                claimVoucherItemDao.delete(old.getId());
            }
        }
        //上面迭代结束后，拿出一个新的集合，
        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            //如果条目的id大于0，代表数据库里面已经有了这个条目了,同时防止空指针异常
            if(item.getId()!=null&&item.getId()>0){
                //代表要去更新里面的内容
                claimVoucherItemDao.update(item);
            }else{
                //否则就需要把这个对象，就是这个对象不存在数据里面，进行插入
                claimVoucherItemDao.insert(item);
            }
        }

    }

    /**提交操作**/
    public void submit(int id) {
        //拿到报销单
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        //拿到当前的创建人
        Employee employee = employeeDao.select(claimVoucher.getCreateSn());

        //1、报销单第一步更新
        //状态为已提交的状态
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        //待处理人就是跟我这个员工同部门的，他的职位是部门经理的，POST_FM="部门经理"
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(),Contant.POST_FM).get(0).getSn());
        //调用更新的方法
        claimVoucherDao.update(claimVoucher);

        //2、进行记录的保存
        //声明一个对象，进行一个属性的填充
        DealRecord dealRecord = new DealRecord();
        //进行的处理方式DEAL_SUBMIT="提交";
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        //当前处理人，谁来提交的当前的员工
        dealRecord.setDealSn(employee.getSn());
        //报销单编号
        dealRecord.setClaimVoucherId(id);
        //处理结果CLAIMVOUCHER_SUBMIT="已提交";
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDealTime(new Date());
        //备注
        dealRecord.setComment("无");
        //调用dealRecordDao保存记录
        dealRecordDao.insert(dealRecord);
    }

    /**审核报销单**/
    public void deal(DealRecord dealRecord) {
        //首先拿出报销单拿出啦
        ClaimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        //把审核人是谁传过来
        Employee employee = employeeDao.select(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());

        //审核通过的方式
        /**首先考虑不需要复审的  DEAL_PASS="通过";**/
        if(dealRecord.getDealWay().equals(Contant.DEAL_PASS)){
            //你的报销单的金额小于等于我们审核的金额就不要复审 ，并且当前审核的处理人职位是谁，
            if(claimVoucher.getTotalAmount()<=Contant.LIMIT_CHECK || employee.getPost().equals(Contant.POST_GM)){
                //改动状态，变成已审核
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                //获取财务，拿到第一个sn，拿到该员工的编号
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_CASHIER).get(0).getSn());
                //下面就是处理结果
                //处理完的报销单的状态
                dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
            }else{
                /**需要复审的**/
                //报销单的状态改为待复审
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
                //待处理人的只能是总经理
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_GM).get(0).getSn());
                //处理时间为待复审
                dealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            }

         /**下面是打回操作**/
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_BACK)){
            //报销单的状态改为已打回
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            //处理结果已打回
            dealRecord.setDealResult(Contant.CLAIMVOUCHER_BACK);

        /**如果是拒绝**/
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_REJECT)){
            //报销单的状态改为CLAIMVOUCHER_TERMINATED="已终止
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
            //他就没有待处理人了设置为null
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);

        /**最后是打款操作**/
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_PAID)){
            //报销单状态设置为已打款CLAIMVOUCHER_PAID
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
        }

        //审核结果，就是保险单的更新一下
        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }

}
