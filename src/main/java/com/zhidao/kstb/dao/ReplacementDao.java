package com.zhidao.kstb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 补发信息数据访问对象(接口)
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-18<p>
 */
@Mapper
public interface ReplacementDao {

    /**
     * 判断标记为'10'的记录是否存在
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 业务协同编号
     * @param qyrGmsfhm 迁移人公民身份号码
     * @return 存在该记录的行数
     * <p>Created on 2021-9-18<p>
     */
    Integer existOfSuccess(String ywxtbh, String qyrGmsfhm);

    /**
     * 判断标记不为'10'的记录是否存在于迁出反馈推送信息中
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 协同业务编号
     * @return 存在该记录的行数
     * <p>Created on 2021-9-26<p>
     */
    Integer existOfUnSuccessFromQCFKInfo(String ywxtbh);

    /**
     * 判断标记为'10'的记录是否存在于不予迁出反馈推送信息中
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 业务协同编号
     * @return 存在该记录的行数
     * <p>Created on 2021-9-26<p>
     */
    Integer existOfUnSuccessFromBYQCFKInfo(String ywxtbh);

    /**
     * 更新申请迁出推送记录的状态
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 业务协同编号
     * @param qyrGmsfhm 迁移人公民身份号码
     * @return 受影响的行数
     * <p>Created on 2021-9-18<p>
     */
    Integer changeSQQCInfoSuccess(@Param(value = "ywxtbh") String ywxtbh,@Param("qyrGmsfhm") String qyrGmsfhm);

    /**
     * 更新迁出反馈推送记录的状态
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 协同业务编号
     * @return 受影响的行数
     * <p>Created on 2021-9-26<p>
     */
    Integer changeQCFKInfoSuccess(@Param(value = "ywxtbh") String ywxtbh);

    /**
     * 更新不予迁出反馈推送记录的状态
     *
     * @author <致道科技>YunTong
     * @param ywxtbh 业务协同编号
     * @return 受影响的行数
     * <p>Created on 2021-9-26<p>
     */
    Integer changeBYQCFKInfoSuccess(@Param(value = "ywxtbh") String ywxtbh);

}
