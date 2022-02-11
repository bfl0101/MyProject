package com.zhidao.kstb.service;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 跨省通办户口迁移业务层接口
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-8-31<p>
 */
@Service
@WebService(name="KSTBService",//暴露的服务名称
        targetNamespace = "http://service.kstb.zhidao.com")//命名空间
public interface IKSTBService {

    /**
     * 申请迁出信息接收服务
     *
     * @author <致道科技>YunTong
     * @param sqzcm 授权注册码
     * @param information 业务请求参数
     * @return 接口调用结果
     * <p>Created on 2021-8-2<p>
     */
    @WebMethod
    String ZAGL_SQQCJSFW(@WebParam(name = "sqzcm")String sqzcm, @WebParam(name = "information")String information);

    /**
     * 迁出反馈信息接收服务
     *
     * @author <致道科技>YunTong
     * @param sqzcm 授权注册码
     * @param information 业务请求参数
     * @return 接口调用结果
     * <p>Created on 2021-8-2<p>
     */
    @WebMethod
    String ZAGL_QCFKJSFW(@WebParam(name = "sqzcm")String sqzcm,@WebParam(name = "information") String information);

    /**
     * 申请出生入户接收服务
     *
     * @author <致道科技>Cui
     * @param sqzcm 授权注册码
     * @param information 业务请求参数
     *<p>Created on 2021-7-29<p>
     */
    @WebMethod
    String ZAGL_SQCSRHJSFW(@WebParam(name = "sqzcm") String sqzcm, @WebParam(name = "information") String information);

    /**
     * 出生入户反馈接收服务和不予落户反馈接收服务
     *
     * @author <致道科技>Cui
     * @param sqzcm 授权注册码
     * @param information 业务请求参数
     * <p>Created on 2021-7-29<p>
     */
    @WebMethod
    String ZAGL_CSRHFKJSFW(@WebParam(name = "sqzcm") String sqzcm, @WebParam(name = "information") String information);

    /**
     * 常住人口登记表流转接收服务
     *
     * @author <致道科技>Cui
     * @param sqzcm 授权注册码
     * @param information 业务请求参数
     * <p>Created on 2021-7-29<p>
     */
    @WebMethod
    String ZAGL_CZRKDJBLZJSFW(@WebParam(name = "sqzcm") String sqzcm, @WebParam(name = "information") String information);

    /**
     * “跨省通办”办理居民身份证业务协同接口-申请审核签发接收服务
     *
     * @param information 协同数据
     * @return xml          返回xml格式成功或失败数据
     * @author bfl
     * @version 1.0.0
     * @date 2021.06.09
     */
    @WebMethod
    String ZAGL_JMSFZSQSHQFJSFW(@WebParam String sqzcm, @WebParam String information);

    /**
     * “跨省通办”办理居民身份证业务协同接口-审核签发反馈接收服务
     *
     * @param information 协同数据
     * @return xml          返回xml格式成功或失败数据
     * @author bfl
     * @version 1.0.0
     * @date 2021.06.16
     */
    @WebMethod
    String ZAGL_JMSFZSHQFFKJSFW(@WebParam String sqzcm, @WebParam String information);

    /**
     * “跨省通办”办理居民身份证业务协同接口-证件制作反馈接收服务
     *
     * @param information 协同数据
     * @return xml          返回xml格式成功或失败数据
     * @author bfl
     * @version 1.0.0
     * @date 2021.06.18
     */
    @WebMethod
    String ZAGL_ZJZZFKJSFW(@WebParam String sqzcm, @WebParam String information);

    /**
     * 申请开具户籍证明接收服务
     * @author yhl
     * @param sqzcm 授权注册码
     * @param information 业务请求参数
     * @return 接口调用结果
     */
    @WebMethod
    String ZAGL_SQKJHJZMJSFW (@WebParam String sqzcm ,@WebParam String information);

    /**
     * 开具户籍证明反馈接收服务
     * @author yhl
     * @param sqzcm 授权注册码
     * @param information 业务请求参数
     * @return 接口调用结果
     */
    @WebMethod
    String ZAGL_KJHJZMFKJSFW (@WebParam String sqzcm,@WebParam String information);
}
