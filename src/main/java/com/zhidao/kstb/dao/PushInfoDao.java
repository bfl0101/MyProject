package com.zhidao.kstb.dao;

import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX_FS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 推送信息持久层数据访问对象(接口)
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-16<p>
 */
@Mapper
@Repository
public interface PushInfoDao {

    /**
     * 添加申请迁出推送信息
     *
     * @author <致道科技>YunTong
     * @param kstb_sqqcxx_fs 跨省通办申请迁出推送信息
     * @return 受影响的行数
     * <p>Created on 2021-9-16<p>
     */
    Integer addMoveOutInformation (KSTB_SQQCXX_FS kstb_sqqcxx_fs);

    /**
     * 添加迁出反馈推送信息
     *
     * @author <致道科技>YunTong
     * @param kstb_qcfkxx_fs 跨省通办_迁出反馈推送信息
     * @return 受影响的行数
     * <p>Created on 2021-9-24<p>
     */
    Integer addFeedBackInfo(KSTB_QCFKXX_FS kstb_qcfkxx_fs);

    /**
     * 添加不予迁出反馈推送信息
     *
     * @author <致道科技>YunTong
     * @param kstb_byqcfkxx_fs 跨省通办_不予迁出反馈推送信息
     * @return 受影响的行数
     * <p>Created on 2021-9-24<p>
     */
    Integer addUnFeedBackInfo(KSTB_BYQCFKXX_FS kstb_byqcfkxx_fs);

    /**
     * 根据申请号更新申请迁入审批信息
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 协同业务编号
     * @param sqh 申请号
     * @return 受影响的行数
     * <p>Created on 2021-9-17<p>
     */
    Integer changeApprovalInformation(@Param("ywxtbh") String ywxtbh,@Param("sqh") String sqh);

    /**
     * 判断该记录是否存在
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 业务协同编号
     * @param qyrGmsfhm 迁移人公民身份号码
     * @return 存在该记录的行数
     * <p>Created on 2021-9-17<p>
     */
    Integer existOfRecord(String ywxtbh , String qyrGmsfhm);

    /**
     * 判断该迁出反馈记录是否存在
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 协同业务编号
     * @param qyrGmsfhm 迁移人公民身份号码
     * @return 存在该记录的行数
     * <p>Created on 2021-9-24<p>
     */
    Integer existOfFeedBack(String ywxtbh,String qyrGmsfhm);

    /**
     * 判断该不予迁出反馈记录是否存在
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 业务协同编号
     * @param sqrGmsfhm 申请人公民身份号码
     * @return 受影响的行数
     * <p>Created on 2021-9-24<p>
     * <p>Updated on 2021-9-26<p>
     */
    Integer existOfUnFeedBack(String ywxtbh,String sqrGmsfhm);

    /**
     * 更新申请迁出不予迁出标识为'0'
     *
     * @author <致道科技>YunTong
     * @param qcfkxlh 迁出反馈序列号
     * @param ywxtbh 业务协同编号
     * @return 受影响的行数
     * <p>Created on 2021-9-24<p>
     */
    Integer changeMoveOutStatus(@Param("qcfkxlh") String qcfkxlh,@Param("ywxtbh") String ywxtbh);

    /**
     * 更新申请迁出不予迁出标识为'1'
     *
     * @author <致道科技>YunTong
     * @param qcfkxlh 迁出反馈序列号
     * @param ywxtbh 业务协同编号
     * @return @return 受影响的行数
     * <p>Created on 2021-9-24<p>
     */
    Integer changeUnMoveOutStatus(@Param("qcfkxlh") String qcfkxlh,@Param("ywxtbh") String ywxtbh);

    /**
     * 更新常住人口基本信息为'注销'状态
     *
     * @author <致道科技>YunTong
     * @param gmsfhm 公民身份号码
     * @return 受影响的行数
     * <p>Created on 2021-9-28<p>
     */
    Integer changeStatusIntoDestroy(@Param(value = "gmsfhm") String gmsfhm);

    /**
     * 判断申请迁出记录是否存在
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 业务协同编号
     * @return 存在记录的行数
     * <p>Created on 2021-9-24<p>
     */
    Integer existOfMoveOut(String ywxtbh);

    /**
     * 从申请迁出信息当中获取对应的业务协同编号
     *
     * @author <致道科技>YunTong
     * @param nowYwxtbh 当前业务协同编号
     * @return 匹配的申请迁出信息中的业务协同编号
     * <p>Created on 2021-9-24<p>
     */
    String getYwxtbhFromSqqcInfo(String nowYwxtbh);

    /**
     * 获取符合条件ZT='0'常住人口基本信息
     *
     * @author <致道科技>YunTong
     * @param gmsfhm 公民身份号码
     * @return 匹配的状态被注销的常驻人口
     * <p>Created on 2021-9-26<p>
     */
    Integer getPopulationInfo(String gmsfhm);
}
