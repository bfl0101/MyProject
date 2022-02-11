package com.zhidao.kstb.service.impl;

import com.zhidao.kstb.dao.*;
import com.zhidao.kstb.hjlzm.model.come.HjzmPackeage;
import com.zhidao.kstb.hjlzm.model.go.no.ErrData;
import com.zhidao.kstb.hjlzm.model.go.no.ErrPackage;
import com.zhidao.kstb.hjlzm.model.go.yes.YesPackage;
import com.zhidao.kstb.hjlzm.service.CaoZuo;
import com.zhidao.kstb.log.dao.Log_jkfwDao;
import com.zhidao.kstb.log.model.Log_jkfwWithBLOBs;
import com.zhidao.kstb.model.ele.PACKAGE;
import com.zhidao.kstb.model.ele.RECORD;
import com.zhidao.kstb.model.elec.PACKAGEC;
import com.zhidao.kstb.model.elec.RECORDC;
import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX;
import com.zhidao.kstb.model.tab.KSTB_CLXX;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX;
import com.zhidao.kstb.model.tabc.KSTB_CLXXC;
import com.zhidao.kstb.model.tabc.KSTB_CS_BYLHXTXX;
import com.zhidao.kstb.model.tabc.KSTB_CS_JMHKBGRXX;
import com.zhidao.kstb.model.tabc.KSTB_CS_SQCSRHXTXX;
import com.zhidao.kstb.service.IKSTBService;
import com.zhidao.kstb.service.ex.UpdateInformationException;
import com.zhidao.kstb.shqffkjs.dao.*;
import com.zhidao.kstb.shqffkjs.model.Kstb_sfz_sqshqfxx_fsWithBLOBs;
import com.zhidao.kstb.shqffkjs.model.Rk_zpxx;
import com.zhidao.kstb.shqffkjs.model.Sfz_glxx;
import com.zhidao.kstb.shqffkjs.model.Zwqd_zwsjxxWithBLOBs;
import com.zhidao.kstb.shqffkjs.model.req.ShqfPackage;
import com.zhidao.kstb.shqffkjs.model.req.ShqfPackageHead;
import com.zhidao.kstb.shqffkjs.model.req.ShqfRecord;
import com.zhidao.kstb.shqffkjs.model.resp.err.ShqfDataErr;
import com.zhidao.kstb.shqffkjs.model.resp.err.ShqfPackageErr;
import com.zhidao.kstb.shqffkjs.model.resp.err.ShqfRecordErr;
import com.zhidao.kstb.shqffkjs.model.resp.suc.ShqfPackageSuc;
import com.zhidao.kstb.sqshqfjs.dao.Kstb_clxxDao;
import com.zhidao.kstb.sqshqfjs.dao.Kstb_sfz_rxbdjgxxDao;
import com.zhidao.kstb.sqshqfjs.dao.Kstb_sfz_sqshqfxxDao;
import com.zhidao.kstb.sqshqfjs.model.req.SqshPackage;
import com.zhidao.kstb.sqshqfjs.model.req.SqshPackageHead;
import com.zhidao.kstb.sqshqfjs.model.req.SqshRecord;
import com.zhidao.kstb.sqshqfjs.model.resp.err.SqshPackageErr;
import com.zhidao.kstb.sqshqfjs.model.resp.err.SqshRecordErr;
import com.zhidao.kstb.sqshqfjs.model.resp.suc.SqshPackageSuc;


import com.zhidao.kstb.utils.AgeUtil;
import com.zhidao.kstb.utils.JAXBUtils;
import com.zhidao.kstb.utils.XMLUtil;
import com.zhidao.kstb.zjzzfkjs.dao.Kstb_sfz_zlhkxxDao;
import com.zhidao.kstb.zjzzfkjs.dao.Kstb_sfz_zzhkxxDao;
import com.zhidao.kstb.zjzzfkjs.model.req.ZjzzPackage;
import com.zhidao.kstb.zjzzfkjs.model.req.ZjzzPackageHead;
import com.zhidao.kstb.zjzzfkjs.model.req.ZjzzRecord;
import com.zhidao.kstb.zjzzfkjs.model.resp.err.ZjzzPackageErr;
import com.zhidao.kstb.zjzzfkjs.model.resp.err.ZjzzRecordErr;
import com.zhidao.kstb.zjzzfkjs.model.resp.suc.ZjzzPackageSuc;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 跨省通办业务层接口实现
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-8-31<p>
 * <p>UPdated on 2021-10-20<p>
 */
@Service
@WebService(serviceName = "KSTBService", // 与接口中指定的name一致
        targetNamespace = "http://service.kstb.zhidao.com", // 与接口中的命名空间一致，一般是包名倒序
        endpointInterface = "com.zhidao.kstb.service.IKSTBService") // 接口地址
public class IKSTBServiceImpl implements IKSTBService {
    @Autowired
    private HouseholdMigrationDao householdMigrationDao;
    @Autowired
    private PushInfoDao pushInfoDao;
    @Autowired
    private KSTB_CS_SQCSRHXTXXDao sqcsrhxtxxDao;
    @Autowired
    private KSTB_CLXXDao clxxDao;
    @Autowired
    private KSTB_CS_JMHKBGRXXDao jmhkdaao;
    @Autowired
    private KSTB_CS_BYLHXTXXDao byludao;
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;
    @Autowired
    private Kstb_clxxDao clxx1_Dao;
    @Autowired
    private Kstb_sfz_rxbdjgxxDao rxbdDao;
    @Autowired
    private Kstb_sfz_sqshqfxxDao sqshDao;
    @Autowired
    private Kstb_sfz_shqffkxxDao shqfDao;
    @Autowired
    private Kstb_sfz_zlhkxxDao zlhkDao;
    @Autowired
    private Kstb_sfz_zzhkxxDao zzhkDao;
    @Autowired
    private Log_jkfwDao logDao;
    @Autowired
    private CaoZuo caoZuo;

    @Resource
    private Rk_zpxxDao rk_zpxxDao;

    @Resource
    private Sfz_glxxDao sfz_glxxDao;

    @Resource
    private Zwqd_zwsjxxDao zwqd_zwsjxxDao;

    @Resource
    private Kstb_sfz_sqshqfxx_fsDao kstb_sfz_sqshqfxx_fsdao;

    private final static Logger logger = LoggerFactory.getLogger(IKSTBServiceImpl.class);

    @Override
    public String ZAGL_SQQCJSFW(String sqzcm, String information) {
        logger.info("======开始调用跨省通办_申请迁出接收服务=====");
        logger.info("授权注册码：" + sqzcm);
        logger.info("information：" + information);
        System.out.println(information);
		if(sqzcm==null) {
			PACKAGE returnXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
			Document document = loadXml("./kstb_configuration/ex_sqzcm.xml");
			Element rootElement = document.getRootElement();
			// 过滤出PACKAGEHEAD下的子节点
			for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
				Element headElement = iterator.next();
				Element bbh = headElement.element("BBH");
				Element fsd = headElement.element("FSDW_SJGSDWDM");
				Element fsm = headElement.element("FSDW_SJGSDWMC");
				Element jsd = headElement.element("JSDW_SJGSDWDM");
				Element jsm = headElement.element("JSDW_SJGSDWMC");
				Element ywfl = headElement.element("ZAGLYWFLDM");
				Element ywbh = headElement.element("YWXTBH");
				Element qyfw = headElement.element("YWXTQYFW");
				Element fssj = headElement.element("FSSJ");
				bbh.setText(returnXml.getPacskageHead().getBbh());
				fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
				fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
				jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
				jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
				ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
				ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
				qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
				fssj.setText(returnXml.getPacskageHead().getFssj());
			}
			// 过滤出DATA节点下的子节点
			for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
				Element dataElement = iterator.next();
				Element code = dataElement.element("CODE");
				Element msg = dataElement.element("MSG");
				code.setText("-50");
				msg.setText("许可证校验错误");
			}
			return document.asXML();
		}
        PACKAGE requestXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
        KSTB_SQQCXX kstbSqqcxx = new KSTB_SQQCXX();
        KSTB_CLXX kstbClxx = new KSTB_CLXX();
        //begin
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        // 过滤出KSTB_SQQCXX相关数据
        for (RECORD record : requestXml.getRecordList()) {
            //System.err.println("RECORD:" + record);
            if (record.getSid().equals("011004")) {
                kstbSqqcxx.setYwxtbh(record.getywxtbh());
                kstbSqqcxx.setYwlsh(record.getYwlsh());
                kstbSqqcxx.setSqrGmsfhm(record.getSqrGmsfhm());
                kstbSqqcxx.setSqrXm(record.getSqrXm());
                kstbSqqcxx.setSqrLxdh(record.getSqrLxdh());
                kstbSqqcxx.setSqrZzSsxqdm(record.getSqrZzSsxqdm());
                kstbSqqcxx.setSqrZzQhnxxdz(record.getSqrZzQhnxxdz());
                kstbSqqcxx.setQyrGmsfhm(record.getQyrGmsfhm());
                kstbSqqcxx.setQyrXm(record.getQyrXm());
                kstbSqqcxx.setQyrXbDm(record.getQyrXbDm());
                kstbSqqcxx.setQyrMzDm(record.getQyrMzDm());
                kstbSqqcxx.setQyrCsrq(record.getQyrCsrq());
                kstbSqqcxx.setQyrYsqrgxJtgxdm(record.getQyrYsqrgxJtgxdm());
                kstbSqqcxx.setQyldyydm(record.getQyldyydm());
                kstbSqqcxx.setQcdSsxqdm(record.getQcdSsxqdm());
                kstbSqqcxx.setQcdQhnxxdz(record.getQcdQhnxxdz());
                kstbSqqcxx.setQcdHkdjjgGajgdm(record.getQcdHkdjjgGajgdm());
                kstbSqqcxx.setQcdHkdjjgGajgmc(record.getQcdHkdjjgGajgmc());
                kstbSqqcxx.setQrdSsxqdm(record.getQrdSsxqdm());
                kstbSqqcxx.setQrdQhnxxdz(record.getQrdQhnxxdz());
                kstbSqqcxx.setQrdHkdjjgGajgdm(record.getQrdHkdjjgGajgdm());
                kstbSqqcxx.setQrdHkdjjgGajgmc(record.getQrdHkdjjgGajgmc());
                kstbSqqcxx.setZqzbh(record.getZqzbh());
                kstbSqqcxx.setQfjgGajgjgdm(record.getQfjgGajgjgdm());
                kstbSqqcxx.setQfjgGajgmc(record.getQfjgGajgmc());
                kstbSqqcxx.setCbrXm(record.getCbrXm());
                kstbSqqcxx.setQfRq(record.getQfRq());
                kstbSqqcxx.setBz(record.getBz());
                kstbSqqcxx.setYxjzrq(record.getYxjzrq());
                kstbSqqcxx.setXtywQyfwdm(record.getXtywQyfwdm());
                kstbSqqcxx.setSldwGajgjgdm(record.getSldwGajgjgdm());
                kstbSqqcxx.setSldwGajgmc(record.getSldwGajgmc());
                kstbSqqcxx.setSldwLxdh(record.getSldwLxdh());
                kstbSqqcxx.setSlrGmsfhm(record.getSlrGmsfhm());
                kstbSqqcxx.setSlrXm(record.getSlrXm());
                kstbSqqcxx.setSlrLxdh(record.getSlrLxdh());
                kstbSqqcxx.setSlsj(record.getSlsj());
                kstbSqqcxx.setSjgsdwdm(record.getSjgsdwdm());
                kstbSqqcxx.setSjgsdwmc(record.getSjgsdwmc());
                kstbSqqcxx.setFsdwSjgsdwdm(record.getFsdwSjgsdwdm());
                kstbSqqcxx.setFsdwSjgsdwmc(record.getFsdwSjgsdwmc());
                kstbSqqcxx.setJsdwSjgsdwdm(record.getJsdwSjgsdwdm());
                kstbSqqcxx.setJsdwSjgsdwmc(record.getJsdwSjgsdwmc());
                logger.info("申请迁出信息：" + kstbSqqcxx);

                try {
                    boolean result = householdMigrationDao.InsSQQCXX(kstbSqqcxx);
                    logger.info("申请迁出信息添加成功！" + result);
                } catch (Exception e) {
                    dataSourceTransactionManager.rollback(transactionStatus);//rollback
                    logger.error("接口异常描述：" + e.getMessage());
                    PACKAGE responseXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                    Document document = loadXml("./kstb_configuration/ex_handler.xml");
                    Element rootElement = document.getRootElement();
                    // 过滤出PACKAGEHEAD下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                        Element headElement = iterator.next();
                        Element bbh = headElement.element("BBH");
                        Element fsd = headElement.element("FSDW_SJGSDWDM");
                        Element fsm = headElement.element("FSDW_SJGSDWMC");
                        Element jsd = headElement.element("JSDW_SJGSDWDM");
                        Element jsm = headElement.element("JSDW_SJGSDWMC");
                        Element ywfl = headElement.element("ZAGLYWFLDM");
                        Element ywbh = headElement.element("YWXTBH");
                        Element qyfw = headElement.element("YWXTQYFW");
                        Element fssj = headElement.element("FSSJ");
                        bbh.setText(requestXml.getPacskageHead().getBbh());
                        fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                        fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                        jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                        jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                        ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                        ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                        qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                        fssj.setText(requestXml.getPacskageHead().getFssj());
                    }
                    // 过滤出DATA节点下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                        Element dataElement = iterator.next();
                        Element record1 = dataElement.addElement("RECORD");
                        //开始进行数据校验
                        for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                            if (requestXml.getRecordList().get(i).getywxtbh().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-901");
                                record1.addAttribute("msg", "(扩展异常描述)业务协同编号不存在");
                            }
                            if (requestXml.getRecordList().get(i).getZqzbh().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-902");
                                record1.addAttribute("msg", "(扩展异常描述)无法识别准迁证编号");
                            }
                            if (requestXml.getRecordList().get(i).getSqrGmsfhm().isEmpty() || requestXml.getRecordList().get(i).getSqrXm().isEmpty() || requestXml.getRecordList().get(i).getSqrLxdh().isEmpty() || requestXml.getRecordList().get(i).getSqrZzSsxqdm().isEmpty() || requestXml.getRecordList().get(i).getSqrZzSsxqdm().isEmpty() || requestXml.getRecordList().get(i).getSqrZzQhnxxdz().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-903");
                                record1.addAttribute("msg", "(扩展异常描述)缺少申请人相关信息");
                            }
                            if (requestXml.getRecordList().get(i).getQrdSsxqdm().isEmpty() || requestXml.getRecordList().get(i).getQrdQhnxxdz().isEmpty() || requestXml.getRecordList().get(i).getQrdHkdjjgGajgdm().isEmpty() || requestXml.getRecordList().get(i).getQrdHkdjjgGajgmc().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-905");
                                record1.addAttribute("msg", "(扩展异常描述)缺少迁入地相关信息");
                            }
                            if (requestXml.getRecordList().get(i).getQyrGmsfhm().isEmpty() || requestXml.getRecordList().get(i).getQyrXm().isEmpty() || requestXml.getRecordList().get(i).getQyrXbDm().isEmpty() || requestXml.getRecordList().get(i).getQyrMzDm().isEmpty() || requestXml.getRecordList().get(i).getQyrCsrq().isEmpty() || requestXml.getRecordList().get(i).getQyrYsqrgxJtgxdm().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-906");
                                record1.addAttribute("msg", "(扩展异常描述)缺少迁移人相关信息");
                            }
                            if (requestXml.getRecordList().get(i).getQyldyydm().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-907");
                                record1.addAttribute("msg", "(扩展异常描述)迁移流动原因不明确");
                            }
                            if (requestXml.getRecordList().get(i).getQfjgGajgjgdm().isEmpty() || requestXml.getRecordList().get(i).getQfjgGajgmc().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-908");
                                record1.addAttribute("msg", "(扩展异常描述)缺少签发机关相关信息");
                            }
                            if (requestXml.getRecordList().get(i).getSldwGajgjgdm().isEmpty() || requestXml.getRecordList().get(i).getSldwGajgmc().isEmpty() || requestXml.getRecordList().get(i).getSldwLxdh().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-909");
                                record1.addAttribute("msg", "(扩展异常描述)缺少受理单位相关信息");
                            }
                            if (requestXml.getRecordList().get(i).getSlrGmsfhm().isEmpty() || requestXml.getRecordList().get(i).getSlrXm().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-910");
                                record1.addAttribute("msg", "(扩展异常描述)缺少受理人相关信息");
                            }
                            if (requestXml.getRecordList().get(i).getSlsj().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-911");
                                record1.addAttribute("msg", "(扩展异常描述)缺少受理时间");
                            }
                            if (requestXml.getRecordList().get(i).getSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getSjgsdwmc().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-912");
                                record1.addAttribute("msg", "(扩展异常描述)缺少数据归属单位代码或名称");
                            }
                            if (requestXml.getRecordList().get(i).getFsdwSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getFsdwSjgsdwmc().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-42");
                                record1.addAttribute("msg", "发送单位不存在");
                            }
                            if (requestXml.getRecordList().get(i).getJsdwSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getJsdwSjgsdwmc().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-43");
                                record1.addAttribute("msg", "接收单位不存在");
                            }
                            if(requestXml.getRecordList().get(i).getQcdSsxqdm().isEmpty() || requestXml.getRecordList().get(i).getQcdQhnxxdz().isEmpty()){
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-926");
                                record1.addAttribute("msg", "(扩展异常描述)缺少迁出地相关信息");
                            }

                        }
                        return document.asXML();
                    }
                }
            }

        }
        // 过滤出KSTB_CLXX相关的数据
        for (RECORD record2 : requestXml.getRecordList()) {
            if (record2.getSid().equals("011000")) {
                kstbClxx.setYwxtbh(record2.getywxtbh());
                kstbClxx.setId(UUID.randomUUID().toString().toUpperCase());
                kstbClxx.setClnr(record2.getClnr());
                kstbClxx.setClgs(record2.getClgs());
                kstbClxx.setClmc(record2.getClmc());
                kstbClxx.setCllbdm(record2.getCllbdm());
                kstbClxx.setSxh(record2.getSxh());
                logger.info("材料信息：" + kstbClxx);
                try {
                    boolean result = householdMigrationDao.InsCLXX(kstbClxx);
                    logger.info("材料信息添加成功！" + result);
                } catch (Exception e) {
                    logger.error("接口异常描述：" + e.getMessage());
                    PACKAGE responseXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                    Document document = loadXml("./kstb_configuration/ex_handler.xml");
                    Element rootElement = document.getRootElement();
                    // 过滤出PACKAGEHEAD下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                        Element headElement = iterator.next();
                        Element bbh = headElement.element("BBH");
                        Element fsd = headElement.element("FSDW_SJGSDWDM");
                        Element fsm = headElement.element("FSDW_SJGSDWMC");
                        Element jsd = headElement.element("JSDW_SJGSDWDM");
                        Element jsm = headElement.element("JSDW_SJGSDWMC");
                        Element ywfl = headElement.element("ZAGLYWFLDM");
                        Element ywbh = headElement.element("YWXTBH");
                        Element qyfw = headElement.element("YWXTQYFW");
                        Element fssj = headElement.element("FSSJ");
                        bbh.setText(requestXml.getPacskageHead().getBbh());
                        fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                        fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                        jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                        jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                        ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                        ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                        qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                        fssj.setText(requestXml.getPacskageHead().getFssj());
                    }
                    // 过滤出DATA节点下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                        Element dataElement = iterator.next();
                        Element record0 = dataElement.addElement("RECORD");
                        for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                            if (requestXml.getRecordList().get(i).getywxtbh().isEmpty() || requestXml.getRecordList().get(i).getSxh().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-41");
                                record0.addAttribute("msg", "协同数据部分发生异常");
                            }
                            if (requestXml.getRecordList().get(i).getClmc().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-913");
                                record0.addAttribute("msg", "(扩展异常描述)缺少材料名称");
                            }
                            if (requestXml.getRecordList().get(i).getClgs().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-914");
                                record0.addAttribute("msg", "(扩展异常描述)不能识别的材料格式类型");
                            }
                            if (requestXml.getRecordList().get(i).getClnr().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-915");
                                record0.addAttribute("msg", "(扩展异常描述)缺少材料内容");
                            }
                        }
                        return document.asXML();
                    }
                }
            }

        }
        logger.info("======跨省通办_申请迁出接收服务调用结束=====");
        dataSourceTransactionManager.commit(transactionStatus);//commit
        return response_sqqc_Handler(information);
    }

    @Override
    public String ZAGL_QCFKJSFW(String sqzcm, String information) {
        logger.info("======开始调用跨省通办_迁出反馈接收服务=====");
        logger.info("授权注册码：" + sqzcm);
        logger.info("information：" + information);
		if(sqzcm==null){
			PACKAGE returnXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
			Document document = loadXml("./kstb_configuration/ex_sqzcm.xml");
			Element rootElement = document.getRootElement();
			// 过滤出PACKAGEHEAD下的子节点
			for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
				Element headElement = iterator.next();
				Element bbh = headElement.element("BBH");
				Element fsd = headElement.element("FSDW_SJGSDWDM");
				Element fsm = headElement.element("FSDW_SJGSDWMC");
				Element jsd = headElement.element("JSDW_SJGSDWDM");
				Element jsm = headElement.element("JSDW_SJGSDWMC");
				Element ywfl = headElement.element("ZAGLYWFLDM");
				Element ywbh = headElement.element("YWXTBH");
				Element qyfw = headElement.element("YWXTQYFW");
				Element fssj = headElement.element("FSSJ");
				bbh.setText(returnXml.getPacskageHead().getBbh());
				fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
				fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
				jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
				jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
				ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
				ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
				qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
				fssj.setText(returnXml.getPacskageHead().getFssj());
			}
			// 过滤出DATA节点下的子节点
			for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
				Element dataElement = iterator.next();
				Element code = dataElement.element("CODE");
				Element msg = dataElement.element("MSG");
				code.setText("-50");
				msg.setText("许可证校验错误");
			}
			return document.asXML();
		}
        PACKAGE requestXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
        KSTB_QCFKXX kstbQcfkxx = new KSTB_QCFKXX();
        KSTB_CLXX kstbClxx = new KSTB_CLXX();
        KSTB_BYQCFKXX kstbByqcfkxx = new KSTB_BYQCFKXX();
        //begin
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        // 过滤出迁出反馈信息
        for (RECORD record : requestXml.getRecordList()) {
            //System.err.println("RECORD:" + record);
            if (record.getSid().equals("011006")) {
                kstbQcfkxx.setYwxtbh(record.getywxtbh());
                kstbQcfkxx.setQyrGmsfhm(record.getQyrGmsfhm());
                kstbQcfkxx.setQyrXm(record.getQyrXm());
                kstbQcfkxx.setQyrXbdm(record.getQyrXbDm());
                kstbQcfkxx.setQyrMzDm(record.getQyrMzDm());
                kstbQcfkxx.setQyrCsrq(record.getQyrCsrq());
                kstbQcfkxx.setQyrCym(record.getQyrCym());
                kstbQcfkxx.setQyrYczrgxJtgxdm(record.getQyrYczrgxJtgxdm());
                kstbQcfkxx.setQyrXldm(record.getQyrXldm());
                kstbQcfkxx.setQyrHyzkdm(record.getQyrHyzkdm());
                kstbQcfkxx.setQyrZy(record.getQyrZy());
                kstbQcfkxx.setQyrCsdGjhdqdm(record.getQyrCsdGjhdqdm());
                kstbQcfkxx.setQyrCsdSsxqdm(record.getQyrCsdSsxqdm());
                kstbQcfkxx.setQyrCsdQhnxxdz(record.getQyrCsdQhnxxdz());
                kstbQcfkxx.setQyrJgGjhdqdm(record.getQyrJgGjhdqdm());
                kstbQcfkxx.setQyrJgSsxqdm(record.getQyrJgSsxqdm());
                kstbQcfkxx.setQyrJgQhnxxdz(record.getQyrJgQhnxxdz());
                kstbQcfkxx.setQyldyydm(record.getQyldyydm());
                kstbQcfkxx.setQyzBh(record.getQyzBh());
                kstbQcfkxx.setCzrGmsfhm(record.getCzrGmsfhm());
                kstbQcfkxx.setCzrXm(record.getCzrXm());
                kstbQcfkxx.setYzzSsxqdm(record.getYzzSsxqdm());
                kstbQcfkxx.setYzzQhnxxdz(record.getYzzQhnxxdz());
                kstbQcfkxx.setYzzCxfldm(record.getYzzCxfldm());
                kstbQcfkxx.setQwdSsxqdm(record.getQwdSsxqdm());
                kstbQcfkxx.setQwdQhnxxdz(record.getQwdQhnxxdz());
                kstbQcfkxx.setQfjgGajgjgdm(record.getQfjgGajgjgdm());
                kstbQcfkxx.setQfjgGajgmc(record.getQfjgGajgmc());
                kstbQcfkxx.setQfRq(record.getQfRq());
                kstbQcfkxx.setYxqjzrq(record.getYxjzrq());
                kstbQcfkxx.setCbrXm(record.getCbrXm());
                kstbQcfkxx.setBz(record.getBz());
                kstbQcfkxx.setZqbh(record.getZqbh());
                kstbQcfkxx.setXtywQyfwdm(record.getXtywQyfwdm());
                kstbQcfkxx.setSldwGajgjgdm(record.getSldwGajgjgdm());
                kstbQcfkxx.setSldwGajgmc(record.getSldwGajgmc());
                kstbQcfkxx.setSldwLxdh(record.getSldwLxdh());
                kstbQcfkxx.setSlrGmsfhm(record.getSlrGmsfhm());
                kstbQcfkxx.setSlrXm(record.getSlrXm());
                kstbQcfkxx.setSlrLxdh(record.getSlrLxdh());
                kstbQcfkxx.setSlsj(record.getSlsj());
                kstbQcfkxx.setSjgsdwdm(record.getSjgsdwdm());
                kstbQcfkxx.setSjgsdwmc(record.getSjgsdwmc());
                kstbQcfkxx.setFsdwSjgsdwdm(record.getFsdwSjgsdwdm());
                kstbQcfkxx.setFsdwSjgsdwmc(record.getFsdwSjgsdwmc());
                kstbQcfkxx.setJsdwSjgsdwdm(record.getJsdwSjgsdwdm());
                kstbQcfkxx.setJsdwSjgsdwmc(record.getJsdwSjgsdwmc());
                logger.info("迁出反馈信息:" + kstbQcfkxx);
                try {
                    //查询该迁出记录是否存在
                    Integer rows = pushInfoDao.existOfMoveOut(kstbQcfkxx.getYwxtbh());
                    //不存在
                    if (rows == 0) {
                        PACKAGE returnXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                        Document document = loadXml("./kstb_configuration/ex_handler.xml");
                        Element rootElement = document.getRootElement();
                        // 过滤出PACKAGEHEAD下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                            Element headElement = iterator.next();
                            Element bbh = headElement.element("BBH");
                            Element fsd = headElement.element("FSDW_SJGSDWDM");
                            Element fsm = headElement.element("FSDW_SJGSDWMC");
                            Element jsd = headElement.element("JSDW_SJGSDWDM");
                            Element jsm = headElement.element("JSDW_SJGSDWMC");
                            Element ywfl = headElement.element("ZAGLYWFLDM");
                            Element ywbh = headElement.element("YWXTBH");
                            Element qyfw = headElement.element("YWXTQYFW");
                            Element fssj = headElement.element("FSSJ");
                            bbh.setText(returnXml.getPacskageHead().getBbh());
                            fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                            fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                            jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                            jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                            ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                            ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                            qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                            fssj.setText(returnXml.getPacskageHead().getFssj());
                        }
                        // 过滤出DATA节点下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                            Element dataElement = iterator.next();
                            Element record1 = dataElement.addElement("RECORD");
                            for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                                record1.addAttribute("no", requestXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", requestXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-900");
                                record1.addAttribute("msg", "(扩展异常描述)协同业务信息不存在");
                            }
                        }
                        logger.error("协同业务信息不存在！");
                        return document.asXML();
                    } else {
                        boolean result = householdMigrationDao.InsQCFKXX(kstbQcfkxx);
                        logger.info("迁出反馈信息添加成功！" + result);
                        //更新申请迁出记录状态
                        Integer row = pushInfoDao.changeMoveOutStatus(kstbQcfkxx.getYwxtbh(), kstbQcfkxx.getYwxtbh());
                        if (row == 0) {
                            PACKAGE returnXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                            Document document = loadXml("./kstb_configuration/ex_handler.xml");
                            Element rootElement = document.getRootElement();
                            // 过滤出PACKAGEHEAD下的子节点
                            for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                                Element headElement = iterator.next();
                                Element bbh = headElement.element("BBH");
                                Element fsd = headElement.element("FSDW_SJGSDWDM");
                                Element fsm = headElement.element("FSDW_SJGSDWMC");
                                Element jsd = headElement.element("JSDW_SJGSDWDM");
                                Element jsm = headElement.element("JSDW_SJGSDWMC");
                                Element ywfl = headElement.element("ZAGLYWFLDM");
                                Element ywbh = headElement.element("YWXTBH");
                                Element qyfw = headElement.element("YWXTQYFW");
                                Element fssj = headElement.element("FSSJ");
                                bbh.setText(returnXml.getPacskageHead().getBbh());
                                fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                                fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                                jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                                jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                                ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                                ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                                qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                                fssj.setText(returnXml.getPacskageHead().getFssj());
                            }
                            // 过滤出DATA节点下的子节点
                            for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                                Element dataElement = iterator.next();
                                Element record1 = dataElement.addElement("RECORD");
                                for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                                    record1.addAttribute("no", requestXml.getRecordList().get(i).getNo());
                                    record1.addAttribute("sid", requestXml.getRecordList().get(i).getSid());
                                    record1.addAttribute("code", "-12");
                                    record1.addAttribute("msg", "数据访问服务调用成功，但发生数据服务处理异常");
                                }
                            }
                            return document.asXML();
                        }
                    }
                } catch (Exception e) {
                    dataSourceTransactionManager.rollback(transactionStatus);//rollback
                    logger.error("接口异常描述：" + e.getMessage());
                    PACKAGE responseXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                    Document document = loadXml("./kstb_configuration/ex_handler.xml");
                    Element rootElement = document.getRootElement();
                    // 过滤出PACKAGEHEAD下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                        Element headElement = iterator.next();
                        Element bbh = headElement.element("BBH");
                        Element fsd = headElement.element("FSDW_SJGSDWDM");
                        Element fsm = headElement.element("FSDW_SJGSDWMC");
                        Element jsd = headElement.element("JSDW_SJGSDWDM");
                        Element jsm = headElement.element("JSDW_SJGSDWMC");
                        Element ywfl = headElement.element("ZAGLYWFLDM");
                        Element ywbh = headElement.element("YWXTBH");
                        Element qyfw = headElement.element("YWXTQYFW");
                        Element fssj = headElement.element("FSSJ");
                        bbh.setText(requestXml.getPacskageHead().getBbh());
                        fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                        fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                        jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                        jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                        ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                        ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                        qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                        fssj.setText(requestXml.getPacskageHead().getFssj());
                    }
                    // 过滤出DATA节点下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                        Element dataElement = iterator.next();
                        Element record1 = dataElement.addElement("RECORD");
                        for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                            if (requestXml.getRecordList().get(i).getywxtbh().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-901");
                                record1.addAttribute("msg", "(扩展异常描述)业务协同编号不存在");
                            }
                            if (requestXml.getRecordList().get(i).getQyldyydm().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-907");
                                record1.addAttribute("msg", "(扩展异常描述)迁移流动原因不明确");
                            }
                            if (requestXml.getRecordList().get(i).getYzzSsxqdm().isEmpty() || requestXml.getRecordList().get(i).getYzzQhnxxdz().isEmpty() || requestXml.getRecordList().get(i).getYzzCxfldm().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-916");
                                record1.addAttribute("msg", "(扩展异常描述)缺少原住址相关业务信息");
                            }
                            if (requestXml.getRecordList().get(i).getQwdSsxqdm().isEmpty() || requestXml.getRecordList().get(i).getQwdQhnxxdz().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-917");
                                record1.addAttribute("msg", "(扩展异常描述)缺少去往地相关业务信息");
                            }
                            if (requestXml.getRecordList().get(i).getYwlsh().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-918");
                                record1.addAttribute("msg", "(扩展异常描述)无法识别业务流水号");
                            }
                            if (requestXml.getRecordList().get(i).getQyrGmsfhm().isEmpty() || requestXml.getRecordList().get(i).getQyrXm().isEmpty() || requestXml.getRecordList().get(i).getQyrXbDm().isEmpty() || requestXml.getRecordList().get(i).getQyrMzDm().isEmpty() || requestXml.getRecordList().get(i).getQyrCsrq().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-919");
                                record1.addAttribute("msg", "(扩展异常描述)缺少迁移人相关业务信息");
                            }
                            if (requestXml.getRecordList().get(i).getCzrGmsfhm().isEmpty() || requestXml.getRecordList().get(i).getCzrXm().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-920");
                                record1.addAttribute("msg", "(扩展异常描述)缺少持证人相关业务信息");
                            }
                            if (requestXml.getRecordList().get(i).getSldwGajgjgdm().isEmpty() || requestXml.getRecordList().get(i).getSldwGajgmc().isEmpty() || requestXml.getRecordList().get(i).getSldwLxdh().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-909");
                                record1.addAttribute("msg", "(扩展异常描述)缺少受理单位相关信息");
                            }
                            if (requestXml.getRecordList().get(i).getSlrGmsfhm().isEmpty() || requestXml.getRecordList().get(i).getSlrXm().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-910");
                                record1.addAttribute("msg", "(扩展异常描述)缺少受理人相关业务信息");
                            }
                            if (requestXml.getRecordList().get(i).getSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getSjgsdwmc().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-912");
                                record1.addAttribute("msg", "(扩展异常描述)数据归属单位不明确");
                            }
                            if (requestXml.getRecordList().get(i).getFsdwSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getFsdwSjgsdwmc().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-42");
                                record1.addAttribute("msg", "发送单位不存在");
                            }
                            if (requestXml.getRecordList().get(i).getJsdwSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getJsdwSjgsdwmc().isEmpty()) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-43");
                                record1.addAttribute("msg", "接收单位不存在");
                            }
                        }
                        return document.asXML();
                    }
                }
            }
        }
        // 过滤出KSTB_CLXX相关的数据
        for (RECORD record : requestXml.getRecordList()) {
            if (record.getSid().equals("011000")) {
                kstbClxx.setYwxtbh(record.getywxtbh());
                kstbClxx.setId(UUID.randomUUID().toString());
                kstbClxx.setClnr(record.getClnr());
                kstbClxx.setClgs(record.getClgs());
                kstbClxx.setClmc(record.getClmc());
                kstbClxx.setCllbdm(record.getCllbdm());
                kstbClxx.setSxh(record.getSxh());
                logger.info("材料信息:" + kstbClxx);
                try {
                    boolean result = householdMigrationDao.InsCLXX(kstbClxx);
                    logger.info("材料信息添加成功！" + result);
                } catch (Exception e) {
                    logger.error("接口异常描述：" + e.getMessage());
                    PACKAGE responseXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                    Document document = loadXml("./kstb_configuration/ex_handler.xml");
                    Element rootElement = document.getRootElement();
                    // 过滤出PACKAGEHEAD下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                        Element headElement = iterator.next();
                        Element bbh = headElement.element("BBH");
                        Element fsd = headElement.element("FSDW_SJGSDWDM");
                        Element fsm = headElement.element("FSDW_SJGSDWMC");
                        Element jsd = headElement.element("JSDW_SJGSDWDM");
                        Element jsm = headElement.element("JSDW_SJGSDWMC");
                        Element ywfl = headElement.element("ZAGLYWFLDM");
                        Element ywbh = headElement.element("YWXTBH");
                        Element qyfw = headElement.element("YWXTQYFW");
                        Element fssj = headElement.element("FSSJ");
                        bbh.setText(requestXml.getPacskageHead().getBbh());
                        fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                        fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                        jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                        jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                        ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                        ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                        qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                        fssj.setText(requestXml.getPacskageHead().getFssj());
                    }
                    // 过滤出DATA节点下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                        Element dataElement = iterator.next();
                        Element record0 = dataElement.addElement("RECORD");
                        for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                            if (requestXml.getRecordList().get(i).getywxtbh().isEmpty() || requestXml.getRecordList().get(i).getSxh().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-41");
                                record0.addAttribute("msg", "协同数据部分发生异常");
                            }
                            if (requestXml.getRecordList().get(i).getClmc().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-913");
                                record0.addAttribute("msg", "(扩展异常描述)缺少材料名称");
                            }
                            if (requestXml.getRecordList().get(i).getClgs().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-914");
                                record0.addAttribute("msg", "(扩展异常描述)不能识别的材料格式类型");
                            }
                            if (requestXml.getRecordList().get(i).getClnr().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-915");
                                record0.addAttribute("msg", "(扩展异常描述)缺少材料内容");
                            }
                        }
                        return document.asXML();
                    }
                }
            }
        }
        // 过滤出不予迁出反馈信息
        for (RECORD record : requestXml.getRecordList()) {
            //System.err.println("RECORD:" + record);
            if (record.getSid().equals("011005")) {
                kstbByqcfkxx.setYwxtbh(record.getywxtbh());
                kstbByqcfkxx.setYwlsh(record.getYwlsh());
                kstbByqcfkxx.setSqrGmsfhm(record.getSqrGmsfhm());
                kstbByqcfkxx.setSqrXm(record.getSqrXm());
                kstbByqcfkxx.setByqcJyqk(record.getByqcJyqk());
                kstbByqcfkxx.setXtywQyfwdm(record.getXtywQyfwdm());
                kstbByqcfkxx.setBldwGajgjgdm(record.getBldwGajgjgdm());
                kstbByqcfkxx.setBldwGajamc(record.getBldwGajamc());
                kstbByqcfkxx.setBldwLxdh(record.getBldwLxdh());
                kstbByqcfkxx.setBlrGmsfhm(record.getBlrGmsfhm());
                kstbByqcfkxx.setBlrXm(record.getBlrXm());
                kstbByqcfkxx.setBlrLxdh(record.getBlrLxdh());
                kstbByqcfkxx.setBlsj(record.getBlsj());
                kstbByqcfkxx.setSjgsdwdm(record.getSjgsdwdm());
                kstbByqcfkxx.setSjgsdwmc(record.getSjgsdwmc());
                kstbByqcfkxx.setFsdwSjgsdwdm(record.getFsdwSjgsdwdm());
                kstbByqcfkxx.setFsdwSjgsdwmc(record.getFsdwSjgsdwmc());
                kstbByqcfkxx.setJsdwSjgsdwdm(record.getJsdwSjgsdwdm());
                kstbByqcfkxx.setJsdwSjgsdwmc(record.getJsdwSjgsdwmc());
                logger.info("不予迁出反馈信息:" + kstbByqcfkxx);
                try {
                    Integer row = pushInfoDao.existOfMoveOut(kstbByqcfkxx.getYwxtbh());
                    if (row == 0) {
                        PACKAGE returnXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                        Document document = loadXml("./kstb_configuration/ex_handler.xml");
                        Element rootElement = document.getRootElement();
                        // 过滤出PACKAGEHEAD下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                            Element headElement = iterator.next();
                            Element bbh = headElement.element("BBH");
                            Element fsd = headElement.element("FSDW_SJGSDWDM");
                            Element fsm = headElement.element("FSDW_SJGSDWMC");
                            Element jsd = headElement.element("JSDW_SJGSDWDM");
                            Element jsm = headElement.element("JSDW_SJGSDWMC");
                            Element ywfl = headElement.element("ZAGLYWFLDM");
                            Element ywbh = headElement.element("YWXTBH");
                            Element qyfw = headElement.element("YWXTQYFW");
                            Element fssj = headElement.element("FSSJ");
                            bbh.setText(returnXml.getPacskageHead().getBbh());
                            fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                            fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                            jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                            jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                            ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                            ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                            qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                            fssj.setText(returnXml.getPacskageHead().getFssj());
                        }
                        // 过滤出DATA节点下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                            Element dataElement = iterator.next();
                            Element record1 = dataElement.addElement("RECORD");
                            for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                                record1.addAttribute("no", requestXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", requestXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-900");
                                record1.addAttribute("msg", "(扩展异常描述)协同业务信息不存在");
                            }
                        }
                        logger.info("协同业务信息不存在！");
                        return document.asXML();
                    }

                    boolean result = householdMigrationDao.InsBYQCFKXX(kstbByqcfkxx);
                    Integer r = pushInfoDao.changeUnMoveOutStatus(kstbByqcfkxx.getYwxtbh(), kstbByqcfkxx.getYwxtbh());
                    logger.info("申请人：'" + kstbByqcfkxx.getSqrXm() + "'状态更新成功！");
                    if (r == 0) {
                        PACKAGE returnXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                        Document document = loadXml("./kstb_configuration/ex_handler.xml");
                        Element rootElement = document.getRootElement();
                        // 过滤出PACKAGEHEAD下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                            Element headElement = iterator.next();
                            Element bbh = headElement.element("BBH");
                            Element fsd = headElement.element("FSDW_SJGSDWDM");
                            Element fsm = headElement.element("FSDW_SJGSDWMC");
                            Element jsd = headElement.element("JSDW_SJGSDWDM");
                            Element jsm = headElement.element("JSDW_SJGSDWMC");
                            Element ywfl = headElement.element("ZAGLYWFLDM");
                            Element ywbh = headElement.element("YWXTBH");
                            Element qyfw = headElement.element("YWXTQYFW");
                            Element fssj = headElement.element("FSSJ");
                            bbh.setText(returnXml.getPacskageHead().getBbh());
                            fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                            fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                            jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                            jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                            ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                            ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                            qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                            fssj.setText(returnXml.getPacskageHead().getFssj());
                        }
                        // 过滤出DATA节点下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                            Element dataElement = iterator.next();
                            Element record1 = dataElement.addElement("RECORD");
                            for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                                record1.addAttribute("no", requestXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", requestXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-12");
                                record1.addAttribute("msg", "数据访问服务调用成功，但发生数据服务处理异常");
                            }
                        }
                        return document.asXML();
                    }
                    logger.info("不予迁出反馈信息添加成功！" + result);
                } catch (Exception e) {
                    logger.error("接口异常描述：" + e.getMessage());
                    dataSourceTransactionManager.rollback(transactionStatus);//rollback
                    PACKAGE responseXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                    Document document = loadXml("./kstb_configuration/ex_handler.xml");
                    Element rootElement = document.getRootElement();
                    // 过滤出PACKAGEHEAD下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                        Element headElement = iterator.next();
                        Element bbh = headElement.element("BBH");
                        Element fsd = headElement.element("FSDW_SJGSDWDM");
                        Element fsm = headElement.element("FSDW_SJGSDWMC");
                        Element jsd = headElement.element("JSDW_SJGSDWDM");
                        Element jsm = headElement.element("JSDW_SJGSDWMC");
                        Element ywfl = headElement.element("ZAGLYWFLDM");
                        Element ywbh = headElement.element("YWXTBH");
                        Element qyfw = headElement.element("YWXTQYFW");
                        Element fssj = headElement.element("FSSJ");
                        bbh.setText(requestXml.getPacskageHead().getBbh());
                        fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                        fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                        jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                        jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                        ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                        ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                        qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                        fssj.setText(requestXml.getPacskageHead().getFssj());
                    }
                    // 过滤出DATA节点下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                        Element dataElement = iterator.next();
                        Element record0 = dataElement.addElement("RECORD");
                        for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                            if (requestXml.getRecordList().get(i).getywxtbh().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-901");
                                record0.addAttribute("msg", "(扩展异常描述)业务协同编号不存在");
                            }
                            if (requestXml.getRecordList().get(i).getSqrGmsfhm().isEmpty() || requestXml.getRecordList().get(i).getSqrXm().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-903");
                                record0.addAttribute("msg", "(扩展异常描述)缺少申请人相关信息");
                            }
                            if (requestXml.getRecordList().get(i).getByqcJyqk().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-921");
                                record0.addAttribute("msg", "(扩展异常描述)不予迁出原因不明确");
                            }
                            if (requestXml.getRecordList().get(i).getBldwGajgjgdm().isEmpty() || requestXml.getRecordList().get(i).getBldwGajamc().isEmpty() || requestXml.getRecordList().get(i).getBldwLxdh().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-923");
                                record0.addAttribute("msg", "(扩展异常描述)缺少办理单位相关业务信息");
                            }
                            if (requestXml.getRecordList().get(i).getBlrGmsfhm().isEmpty() || requestXml.getRecordList().get(i).getBlrXm().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-924");
                                record0.addAttribute("msg", "(扩展异常描述)缺少办理人相关业务信息");
                            }
                            if (requestXml.getRecordList().get(i).getBlsj().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-922");
                                record0.addAttribute("msg", "(扩展异常描述)办理时间不明确");
                            }
                            if (requestXml.getRecordList().get(i).getSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getSjgsdwmc().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-912");
                                record0.addAttribute("msg", "(扩展异常描述)数据归属单位不明确");
                            }
                            if (requestXml.getRecordList().get(i).getFsdwSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getFsdwSjgsdwmc().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-42");
                                record0.addAttribute("msg", "发送单位不存在");
                            }
                            if (requestXml.getRecordList().get(i).getJsdwSjgsdwdm().isEmpty() || requestXml.getRecordList().get(i).getJsdwSjgsdwmc().isEmpty()) {
                                record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record0.addAttribute("code", "-43");
                                record0.addAttribute("msg", "接收单位不存在");
                            }
                        }
                        return document.asXML();
                    }
                }
                dataSourceTransactionManager.commit(transactionStatus);//commit
                logger.info("======跨省通办_迁出反馈服务调用结束=====");
                return response_byqc_Handler(information);
            }
        }
        dataSourceTransactionManager.commit(transactionStatus);//commit
        logger.info("======跨省通办_迁出反馈服务调用结束=====");
        return response_qcfk_Handler(information);
    }

    @Override
    public String ZAGL_SQCSRHJSFW(String sqzcm, String information) {
        logger.info("=====正在调用申请出生入户接收服务=====");
        logger.info("授权注册码："+sqzcm);
		logger.info("WebService请求参数："+information);
		/*校验(授权注册码)许可证*/
		 if(sqzcm==null){
            PACKAGEC returnXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
            Document document = loadXml("./kstb_configuration/ex_cs_sqzcm.xml");
            Element rootElement = document.getRootElement();
            // 过滤出PACKAGEHEAD下的子节点
            for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                Element headElement = iterator.next();
                Element bbh = headElement.element("BBH");
                Element fsd = headElement.element("FSDW_SJGSDWDM");
                Element fsm = headElement.element("FSDW_SJGSDWMC");
                Element jsd = headElement.element("JSDW_SJGSDWDM");
                Element jsm = headElement.element("JSDW_SJGSDWMC");
                Element ywfl = headElement.element("HZGLYWXTFLYDM");
                Element ywbh = headElement.element("YWXTBH");
                Element qyfw = headElement.element("XTYWQYFW");
                Element fssj = headElement.element("FSSJ");
                bbh.setText(returnXml.getPacskageHead().getBbh());
                fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                fssj.setText(returnXml.getPacskageHead().getFssj());
            }
            // 过滤出DATA节点下的子节点
            for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                Element dataElement = iterator.next();
                Element code = dataElement.element("CODE");
                Element msg = dataElement.element("MSG");
                code.setText("-50");
                msg.setText("许可证校验错误");
            }
            return document.asXML();
        }
        PACKAGEC requestXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
        KSTB_CS_SQCSRHXTXX sqcs= new KSTB_CS_SQCSRHXTXX();
        KSTB_CLXXC clxx=new KSTB_CLXXC();
        //begin
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        // 过滤出KSTB_CS_SQCSRHXTXX相关数据;
        for (RECORDC record : requestXml.getRecordList()) {
            if (record.getSid().equals("011001")) {
                sqcs.setYwxtbh(record.getYwxtbh());
                sqcs.setXm(record.getXm());
                sqcs.setX(record.getX());
                sqcs.setM(record.getM());
                sqcs.setYhzgxdm(record.getYhzgxdm());
                sqcs.setHzXm(record.getHzXm());
                sqcs.setHzGmsfhm(record.getHzGmsfhm());
                sqcs.setXbdm(record.getXbdm());
                sqcs.setMzdm(record.getMzdm());
                sqcs.setCsrq(record.getCsrq());
                sqcs.setCssj(record.getCssj());
                sqcs.setCsdGjhdqdm(record.getCsdGjhdqdm());
                sqcs.setCsdSsxqdm(record.getCsdSsxqdm());
                sqcs.setCsdSsxqmc(record.getCsdSsxqmc());
                sqcs.setCsdQhnxxdz(record.getCsdQhnxxdz());
                sqcs.setJgGjhdqdm(record.getJgGjhdqdm());
                sqcs.setJgSsxqdm(record.getJgSsxqdm());
                sqcs.setJgSsxqmc(record.getJgSsxqmc());
                sqcs.setJgQhnxxdz(record.getJgQhnxxdz());
                sqcs.setXxdm(record.getXxdm());
                sqcs.setJhryGmsfhm(record.getJhryGmsfhm());
                sqcs.setJhryXm(record.getJhryXm());
                sqcs.setJhryCyzjdm(record.getJhryCyzjdm());
                sqcs.setJhryZjhm(record.getJhryZjhm());
                sqcs.setJhryWwx(record.getJhryWwx());
                sqcs.setJhryWwm(record.getJhryWwm());
                sqcs.setJhryJhgxdm(record.getJhryJhgxdm());
                sqcs.setJhryLxdh(record.getJhryLxdh());
                sqcs.setJhreGmsfhm(record.getJhreGmsfhm());
                sqcs.setJhreXm(record.getJhreXm());
                sqcs.setJhreCyzjdm(record.getJhreCyzjdm());
                sqcs.setJhreZjhm(record.getJhreZjhm());
                sqcs.setJhreWwx(record.getJhreWwx());
                sqcs.setJhreWwm(record.getJhreWwm());
                sqcs.setJhreJhgxdm(record.getJhreJhgxdm());
                sqcs.setJhreLxdh(record.getJhreLxdh());
                sqcs.setFqGmsfhm(record.getFqGmsfhm());
                sqcs.setFqXm(record.getFqXm());
                sqcs.setFqCyzjdm(record.getFqCyzjdm());
                sqcs.setFqZjhm(record.getFqZjhm());
                sqcs.setMqGmsfhm(record.getMqGmsfhm());
                sqcs.setMqXm(record.getMqXm());
                sqcs.setMqCyzjdm(record.getMqCyzjdm());
                sqcs.setMqZjhm(record.getMqZjhm());
                sqcs.setCsdjlbdm(record.getCsdjlbdm());
                sqcs.setCszmbh(record.getCszmbh());
                sqcs.setSbrGmsfhm(record.getSbrGmsfhm());
                sqcs.setSbrXm(record.getSbrXm());
                sqcs.setSbrLxdh(record.getSbrLxdh());
                sqcs.setSbrYcsrgxJtgxdm(record.getSbrYcsrgxJtgxdm());
                sqcs.setXzzSsxqdm(record.getXzzSsxqdm());
                sqcs.setXzzQhnxxdz(record.getXzzQhnxxdz());
                sqcs.setSldwGajgjgdm(record.getSldwGajgjgdm());
                sqcs.setSldwGajgmc(record.getSldwGajgmc());
                sqcs.setSldwLxdh(record.getSldwLxdh());
                sqcs.setSlrGmsfhm(record.getSlrGmsfhm());
                sqcs.setSlrXm(record.getSlrXm());
                sqcs.setSlrLxdh(record.getSlrLxdh());
                sqcs.setSlsj(record.getSlsj());
                sqcs.setXtywQyfwdm(record.getXtywQyfwdm());
                sqcs.setFsdwSjgsdwdm(record.getFsdwSjgsdwdm());
                sqcs.setFsdwSjgsdwmc(record.getFsdwSjgsdwmc());
                sqcs.setJsdwSjgsdwdm(record.getJsdwSjgsdwdm());
                sqcs.setJsdwSjgsdwmc(record.getJsdwSjgsdwmc());
                logger.info("申请出生入户信息：" + sqcs);
                try {
                    sqcsrhxtxxDao.insert(sqcs);
                    logger.info("申请出生入户信息添加成功！");
                } catch (Exception e) {
                    dataSourceTransactionManager.rollback(transactionStatus);//rollback
                    logger.error("接口异常描述：" + e.getMessage());
                    PACKAGEC responseXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
                    Document document = loadXml("./kstb_configuration/ex_cs_handler.xml");
                    Element rootElement = document.getRootElement();
                    // 过滤出PACKAGEHEAD下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                        Element headElement = iterator.next();
                        Element bbh = headElement.element("BBH");
                        Element fsd = headElement.element("FSDW_SJGSDWDM");
                        Element fsm = headElement.element("FSDW_SJGSDWMC");
                        Element jsd = headElement.element("JSDW_SJGSDWDM");
                        Element jsm = headElement.element("JSDW_SJGSDWMC");
                        Element ywfl = headElement.element("HZGLYWXTFLYDM");
                        Element ywbh = headElement.element("YWXTBH");
                        Element qyfw = headElement.element("XTYWQYFW");
                        Element fssj = headElement.element("FSSJ");
                        bbh.setText(requestXml.getPacskageHead().getBbh());
                        fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                        fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                        jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                        jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                        ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                        ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                        qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                        fssj.setText(requestXml.getPacskageHead().getFssj());
                    }
                    // 过滤出DATA节点下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                        Element dataElement = iterator.next();
                        Element record1 = dataElement.addElement("RECORD");
                        for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                            if (!requestXml.getRecordList().get(i).equals(information)) {
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-41");
                                record1.addAttribute("msg", "协同数据部分发生异常");
                            }else{
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-31");
                                record1.addAttribute("msg", "XML或JSON不符合规范");
                            }
                        }
                        return document.asXML();
                    }
                }
            }
        }
        // 过滤出KSTB_CLXX相关的数据
        for (RECORDC record2 : requestXml.getRecordList()) {
                    if (record2.getSid().equals("011100")) {
                        clxx.setYwxtbh(record2.getYwxtbh());
                        clxx.setSxh(record2.getSxh());
                        clxx.setCllbdm(record2.getCllbdm());
                        clxx.setClmc(record2.getClmc());
                        clxx.setClgs(record2.getClgs());
                        clxx.setClnr(record2.getClnr());
                        try {
                            logger.info("材料信息：" + clxx);
                            clxxDao.insert(clxx);
                            logger.info("材料信息添加成功！");
                        } catch (Exception e) {
                            logger.error("接口异常描述：" + e.getMessage());
                            PACKAGEC responseXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
                            Document document = loadXml("./kstb_configuration/ex_cs_handler.xml");
                            Element rootElement = document.getRootElement();
                            // 过滤出PACKAGEHEAD下的子节点
                            for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                                Element headElement = iterator.next();
                                Element bbh = headElement.element("BBH");
                                Element fsd = headElement.element("FSDW_SJGSDWDM");
                                Element fsm = headElement.element("FSDW_SJGSDWMC");
                                Element jsd = headElement.element("JSDW_SJGSDWDM");
                                Element jsm = headElement.element("JSDW_SJGSDWMC");
                                Element ywfl = headElement.element("HZGLYWXTFLYDM");
                                Element ywbh = headElement.element("YWXTBH");
                                Element qyfw = headElement.element("XTYWQYFW");
                                Element fssj = headElement.element("FSSJ");
                                bbh.setText(requestXml.getPacskageHead().getBbh());
                                fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                                fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                                jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                                jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                                ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                                ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                                qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                                fssj.setText(requestXml.getPacskageHead().getFssj());
                            }
                            // 过滤出DATA节点下的子节点
                            for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                                Element dataElement = iterator.next();
                                Element record0 = dataElement.addElement("RECORD");
                                for(int i = 0 ; i < requestXml.getRecordList().size() ;i++){
                                    if (!requestXml.getRecordList().get(i).equals(information)) {
                                        record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                        record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                        record0.addAttribute("code", "-41");
                                        record0.addAttribute("msg", "协同数据部分发生异常");
                                    }else{
                                        record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                        record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                        record0.addAttribute("code", "-31");
                                        record0.addAttribute("msg", "XML或JSON不符合规范");
                                    }
                                }
                                return document.asXML();
                            }
                        }
                }
            }
        logger.info("=====出生入户反馈接收服务调用完毕=====");
        dataSourceTransactionManager.commit(transactionStatus);//commit
        return success(information);
    }

    @Override
    public String ZAGL_CSRHFKJSFW(String sqzcm, String information) {
        logger.info("=====正在调用出生入户反馈接收服务=====");
        logger.info("授权注册码："+sqzcm);
		logger.info("WebService请求参数："+information);
		/*校验(授权注册码)许可证*/
		 if(sqzcm==null){
                PACKAGEC returnXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
                Document document = loadXml("./kstb_configuration/ex_cs_sqzcm.xml");
                Element rootElement = document.getRootElement();
                // 过滤出PACKAGEHEAD下的子节点
                for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                    Element headElement = iterator.next();
                    Element bbh = headElement.element("BBH");
                    Element fsd = headElement.element("FSDW_SJGSDWDM");
                    Element fsm = headElement.element("FSDW_SJGSDWMC");
                    Element jsd = headElement.element("JSDW_SJGSDWDM");
                    Element jsm = headElement.element("JSDW_SJGSDWMC");
                    Element ywfl = headElement.element("HZGLYWXTFLYDM");
                    Element ywbh = headElement.element("YWXTBH");
                    Element qyfw = headElement.element("XTYWQYFW");
                    Element fssj = headElement.element("FSSJ");
                    bbh.setText(returnXml.getPacskageHead().getBbh());
                    fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                    fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                    jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                    jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                    ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                    ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                    qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                    fssj.setText(returnXml.getPacskageHead().getFssj());
                }
                // 过滤出DATA节点下的子节点
                for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                    Element dataElement = iterator.next();
                    Element code = dataElement.element("CODE");
                    Element msg = dataElement.element("MSG");
                    code.setText("-50");
                    msg.setText("许可证校验错误");
                }
                return document.asXML();
            }
        PACKAGEC requestXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
        KSTB_CS_JMHKBGRXX jmhk=new KSTB_CS_JMHKBGRXX();
        KSTB_CLXXC clxx=new KSTB_CLXXC();
        KSTB_CS_BYLHXTXX bylu = new KSTB_CS_BYLHXTXX();
        //begin
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            //过滤出KSTB_CS_JMHKBGRXX相关的数据
            for (RECORDC record : requestXml.getRecordList()) {
                if (record.getSid().equals("011003")) {
                    jmhk.setYwxtbh(record.getYwxtbh());
                    jmhk.setYhzgxdm(record.getYhzgxdm());
                    jmhk.setGmsfhm(record.getGmsfhm());
                    jmhk.setXm(record.getXm());
                    jmhk.setCym(record.getCym());
                    jmhk.setXbdm(record.getXbdm());
                    jmhk.setMz(record.getMz());
                    jmhk.setCsrq(record.getCsrq());
                    jmhk.setCsdSsxqdm(record.getCsdSsxqdm());
                    jmhk.setCsdSsxqmc(record.getCsdSsxqmc());
                    jmhk.setJgSsxqdm(record.getJgSsxqdm());
                    jmhk.setJgSsxqmc(record.getJgSsxqmc());
                    jmhk.setBsxqtzz(record.getBsxqtzz());
                    jmhk.setZjxy(record.getZjxy());
                    jmhk.setSg(record.getSg());
                    jmhk.setXxdm(record.getXxdm());
                    jmhk.setXl(record.getXl());
                    jmhk.setHyzk(record.getHyzk());
                    jmhk.setByzk(record.getByzk());
                    jmhk.setFwcs(record.getFwcs());
                    jmhk.setZy(record.getZy());
                    jmhk.setHsyhdqlbsx(record.getHsyhdqlbsx());
                    jmhk.setHsyhdqlbz(record.getHsyhdqlbz());
                    jmhk.setBldwGajgjgdm(record.getBldwGajgjgdm());
                    jmhk.setBldwGajgmc(record.getBldwGajgmc());
                    jmhk.setBldwLxdh(record.getBldwLxdh());
                    jmhk.setBlrGmsfhm(record.getBlrGmsfhm());
                    jmhk.setBlrXm(record.getBlrXm());
                    jmhk.setBlrLxdh(record.getBlrLxdh());
                    jmhk.setBlsj(record.getBlsj());
                    jmhk.setXtywQyfwdm(record.getXtywQyfwdm());
                    jmhk.setFsdwSjgsdwdm(record.getFsdwSjgsdwdm());
                    jmhk.setFsdwSjgsdwmc(record.getFsdwSjgsdwmc());
                    jmhk.setJsdwSjgsdwdm(record.getJsdwSjgsdwdm());
                    jmhk.setJsdwSjgsdwmc(record.getJsdwSjgsdwmc());
                    try {
                        logger.info("KSTB_CS_JMHKBGRXX:"+jmhk);
                        jmhkdaao.insert(jmhk);
                        logger.info("KSTB_CS_JMHKBGRXX Insert Okay!");
                    }catch (Exception e) {
                        dataSourceTransactionManager.rollback(transactionStatus);//rollback
                        logger.error("接口异常描述：" + e.getMessage());
                        PACKAGEC responseXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
                        Document document = loadXml("./kstb_configuration/ex_cs_handler.xml");
                        Element rootElement = document.getRootElement();
                        // 过滤出PACKAGEHEAD下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                            Element headElement = iterator.next();
                            Element bbh = headElement.element("BBH");
                            Element fsd = headElement.element("FSDW_SJGSDWDM");
                            Element fsm = headElement.element("FSDW_SJGSDWMC");
                            Element jsd = headElement.element("JSDW_SJGSDWDM");
                            Element jsm = headElement.element("JSDW_SJGSDWMC");
                            Element ywfl = headElement.element("HZGLYWXTFLYDM");
                            Element ywbh = headElement.element("YWXTBH");
                            Element qyfw = headElement.element("XTYWQYFW");
                            Element fssj = headElement.element("FSSJ");
                            bbh.setText(responseXml.getPacskageHead().getBbh());
                            fsd.setText(responseXml.getPacskageHead().getFsdwSjgsdwdm());
                            fsm.setText(responseXml.getPacskageHead().getFsdwSjgsdwmc());
                            jsd.setText(responseXml.getPacskageHead().getJsdwSjgsdwdm());
                            jsm.setText(responseXml.getPacskageHead().getJsdwSjgsdwmc());
                            ywfl.setText(responseXml.getPacskageHead().getZaglywfldm());
                            ywbh.setText(responseXml.getPacskageHead().getYwxtbh());
                            qyfw.setText(responseXml.getPacskageHead().getYwxtqyfw());
                            fssj.setText(responseXml.getPacskageHead().getFssj());
                        }
                        // 过滤出DATA节点下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                            Element dataElement = iterator.next();
                            Element record1 = dataElement.addElement("RECORD");
                            for (int i = 0 ; i < requestXml.getRecordList().size(); i++){
                                if(!requestXml.getRecordList().get(i).equals(information)){
                                    record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                    record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                    record1.addAttribute("code", "-41");
                                    record1.addAttribute("msg", "协同数据部分发生异常");
                                }else {
                                    record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                    record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                    record1.addAttribute("code", "-31");
                                    record1.addAttribute("msg", "XML或JSON不符合规范格式");
                                }
                            }
                                return document.asXML();
                            }
                        }
                }//过滤出KSTB_CS_BYLHXTXX相关的数据
                if (record.getSid().equals("011002")){
                    bylu.setYwxtbh(record.getYwxtbh());
                    bylu.setXm(record.getXm());
                    bylu.setSbrGmsfhm(record.getSbrGmsfhm());
                    bylu.setSbrXm(record.getSbrXm());
                    bylu.setKstbshjgdm(record.getKstbshjgdm());
                    bylu.setBylhJyqk(record.getBylhJyqk());
                    bylu.setBldwGajgjgdm(record.getBldwGajgjgdm());
                    bylu.setBldwGajgmc(record.getBldwGajgmc());
                    bylu.setBldwLxdh(record.getBldwLxdh());
                    bylu.setBlrGmsfhm(record.getBlrGmsfhm());
                    bylu.setBlrXm(record.getBlrXm());
                    bylu.setBlrLxdh(record.getBlrLxdh());
                    bylu.setBlsj(record.getBlsj());
                    bylu.setXtywQyfwdm(record.getXtywQyfwdm());
                    bylu.setFsdwSjgsdwdm(record.getFsdwSjgsdwdm());
                    bylu.setFsdwSjgsdwmc(record.getFsdwSjgsdwmc());
                    bylu.setJsdwSjgsdwdm(record.getJsdwSjgsdwdm());
                    bylu.setJsdwSjgsdwmc(record.getJsdwSjgsdwmc());
                    try {
                        logger.info("不予落户反馈信息："+bylu);
                        byludao.insert(bylu);
                        logger.info("不予落户反馈信息添加成功！");
                    }catch (Exception e){
                        dataSourceTransactionManager.rollback(transactionStatus);//rollback
                        logger.error("接口异常描述："+e.getMessage());
                        PACKAGEC responseXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
                        Document document = loadXml("./kstb_configuration/ex_cs_handler.xml");
                        Element rootElement = document.getRootElement();
                        // 过滤出PACKAGEHEAD下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                            Element headElement = iterator.next();
                            Element bbh = headElement.element("BBH");
                            Element fsd = headElement.element("FSDW_SJGSDWDM");
                            Element fsm = headElement.element("FSDW_SJGSDWMC");
                            Element jsd = headElement.element("JSDW_SJGSDWDM");
                            Element jsm = headElement.element("JSDW_SJGSDWMC");
                            Element ywfl = headElement.element("HZGLYWXTFLYDM");
                            Element ywbh = headElement.element("YWXTBH");
                            Element qyfw = headElement.element("XTYWQYFW");
                            Element fssj = headElement.element("FSSJ");
                            bbh.setText(requestXml.getPacskageHead().getBbh());
                            fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                            fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                            jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                            jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                            ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                            ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                            qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                            fssj.setText(requestXml.getPacskageHead().getFssj());
                        }
                        // 过滤出DATA节点下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                            Element dataElement = iterator.next();
                            Element record1 = dataElement.addElement("RECORD");
                            for (int i = 0; i < requestXml.getRecordList().size(); i++) {
                                if(!requestXml.getRecordList().get(i).equals(information)){
                                    record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                    record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                    record1.addAttribute("code", "-41");
                                    record1.addAttribute("msg", "协同数据部分发生异常");
                                }else {
                                    record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                    record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                    record1.addAttribute("code", "-31");
                                    record1.addAttribute("msg", "XML或JSON不符合规范格式");
                                }
                            }
                            return document.asXML();
                        }
                    }
                }
            }
            //过滤出KSTB_CLXX相关的数据
            for (RECORDC RECORDC : requestXml.getRecordList()) {
                if (RECORDC.getSid().equals("011000")) {
                    clxx.setYwxtbh(RECORDC.getYwxtbh());
                    clxx.setSxh(RECORDC.getSxh());
                    clxx.setCllbdm(RECORDC.getCllbdm());
                    clxx.setClmc(RECORDC.getClmc());
                    clxx.setClgs(RECORDC.getClgs());
                    clxx.setClnr(RECORDC.getClnr());
                    try {
                        logger.info("材料信息：" + clxx);
                        clxxDao.insert(clxx);
                        logger.info("材料信息添加成功！");
                    } catch (Exception e) {
                        dataSourceTransactionManager.rollback(transactionStatus);//rollback
                        logger.error("接口异常描述：" + e.getMessage());
                        PACKAGEC responseXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
                        Document document = loadXml("./kstb_configuration/ex_cs_handler.xml");
                        Element rootElement = document.getRootElement();
                        // 过滤出PACKAGEHEAD下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                            Element headElement = iterator.next();
                            Element bbh = headElement.element("BBH");
                            Element fsd = headElement.element("FSDW_SJGSDWDM");
                            Element fsm = headElement.element("FSDW_SJGSDWMC");
                            Element jsd = headElement.element("JSDW_SJGSDWDM");
                            Element jsm = headElement.element("JSDW_SJGSDWMC");
                            Element ywfl = headElement.element("HZGLYWXTFLYDM");
                            Element ywbh = headElement.element("YWXTBH");
                            Element qyfw = headElement.element("XTYWQYFW");
                            Element fssj = headElement.element("FSSJ");
                            bbh.setText(requestXml.getPacskageHead().getBbh());
                            fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                            fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                            jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                            jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                            ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                            ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                            qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                            fssj.setText(requestXml.getPacskageHead().getFssj());
                        }
                        // 过滤出DATA节点下的子节点
                        for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                            Element dataElement = iterator.next();
                            Element record0 = dataElement.addElement("RECORD");
                            for(int i = 0 ; i < requestXml.getRecordList().size() ; i++){
                                if(!requestXml.getRecordList().get(i).equals(information)){
                                    record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                    record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                    record0.addAttribute("code", "-41");
                                    record0.addAttribute("msg", "协同数据部分发生异常");
                                }else{
                                    record0.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                    record0.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                    record0.addAttribute("code", "-31");
                                    record0.addAttribute("msg", "XML或JSON不符合规范格式");
                                }
                            }
                            return document.asXML();
                        }
                    }
                }
          }
            dataSourceTransactionManager.commit(transactionStatus);//commit
        logger.info("=====出生入户反馈接收服务调用完毕=====");
        return success(information);
    }

    @Override
    public String ZAGL_CZRKDJBLZJSFW(String sqzcm, String information) {
        logger.info("=====正在调用常驻人口登记表流转接收服务=====");
        logger.info("授权注册码：" + sqzcm);
        logger.info("WebService请求参数：" + information);
		if(sqzcm==null){
                PACKAGEC returnXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
                Document document = loadXml("./kstb_configuration/ex_cs_sqzcm.xml");
                Element rootElement = document.getRootElement();
                // 过滤出PACKAGEHEAD下的子节点
                for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                    Element headElement = iterator.next();
                    Element bbh = headElement.element("BBH");
                    Element fsd = headElement.element("FSDW_SJGSDWDM");
                    Element fsm = headElement.element("FSDW_SJGSDWMC");
                    Element jsd = headElement.element("JSDW_SJGSDWDM");
                    Element jsm = headElement.element("JSDW_SJGSDWMC");
                    Element ywfl = headElement.element("HZGLYWXTFLYDM");
                    Element ywbh = headElement.element("YWXTBH");
                    Element qyfw = headElement.element("XTYWQYFW");
                    Element fssj = headElement.element("FSSJ");
                    bbh.setText(returnXml.getPacskageHead().getBbh());
                    fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                    fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                    jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                    jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                    ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                    ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                    qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                    fssj.setText(returnXml.getPacskageHead().getFssj());
                }
                // 过滤出DATA节点下的子节点
                for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                    Element dataElement = iterator.next();
                    Element code = dataElement.element("CODE");
                    Element msg = dataElement.element("MSG");
                    code.setText("-50");
                    msg.setText("许可证校验错误");
                }
                return document.asXML();
            }
        PACKAGEC requestXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
        KSTB_CLXXC clxx = new KSTB_CLXXC();
        //begin
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        //过滤出KSTB_CLXX相关的数据
        for (RECORDC RECORDC : requestXml.getRecordList()) {
            if (RECORDC.getSid().equals("011000")) {
                clxx.setYwxtbh(RECORDC.getYwxtbh());
                clxx.setSxh(RECORDC.getSxh());
                clxx.setCllbdm(RECORDC.getCllbdm());
                clxx.setClmc(RECORDC.getClmc());
                clxx.setClgs(RECORDC.getClgs());
                clxx.setClnr(RECORDC.getClnr());
                try {
                    logger.info("材料信息：" + clxx);
                    clxxDao.insert(clxx);
                    logger.info("材料信息添加成功！");
                } catch (Exception e) {
                    dataSourceTransactionManager.rollback(transactionStatus);//rollback
                    logger.error("接口异常描述：" + e.getMessage());
                    PACKAGEC responseXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class, information);
                    Document document = loadXml("./kstb_configuration/ex_cs_handler.xml");
                    Element rootElement = document.getRootElement();
                    // 过滤出PACKAGEHEAD下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                        Element headElement = iterator.next();
                        Element bbh = headElement.element("BBH");
                        Element fsd = headElement.element("FSDW_SJGSDWDM");
                        Element fsm = headElement.element("FSDW_SJGSDWMC");
                        Element jsd = headElement.element("JSDW_SJGSDWDM");
                        Element jsm = headElement.element("JSDW_SJGSDWMC");
                        Element ywfl = headElement.element("HZGLYWXTFLYDM");
                        Element ywbh = headElement.element("YWXTBH");
                        Element qyfw = headElement.element("XTYWQYFW");
                        Element fssj = headElement.element("FSSJ");
                        bbh.setText(requestXml.getPacskageHead().getBbh());
                        fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
                        fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
                        jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
                        jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
                        ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
                        ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
                        qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
                        fssj.setText(requestXml.getPacskageHead().getFssj());
                    }
                    // 过滤出DATA节点下的子节点
                    for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                        Element dataElement = iterator.next();
                        Element record1 = dataElement.addElement("RECORD");
                        for(int i = 0 ; i < requestXml.getRecordList().size(); i++ ){
                            if(!requestXml.getRecordList().get(i).equals(information)){
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-41");
                                record1.addAttribute("msg", "协同数据部分发生异常");
                            } else{
                                record1.addAttribute("no", responseXml.getRecordList().get(i).getNo());
                                record1.addAttribute("sid", responseXml.getRecordList().get(i).getSid());
                                record1.addAttribute("code", "-31");
                                record1.addAttribute("msg", "XML或JSON不符合规范格式");
                            }
                        }
                        return document.asXML();
                    }
                }
            }
        }
        dataSourceTransactionManager.commit(transactionStatus);//commit
        logger.info("=====常驻人口登记表流转接收服务调用结束=====");
        return success(information);
    }

    @Override
    public String ZAGL_JMSFZSQSHQFJSFW(String sqzcm, String information) {
        logger.info(information);
        logger.info("ZAGL_JMSFZSQSHQFJSFW接口被调用！");
        if (sqzcm.isEmpty()) return "<MSG>授权注册码为空</MSG>";
        logger.info("授权注册码为:" + sqzcm);
        String uid = UUID.randomUUID().toString().toLowerCase();
        Log_jkfwWithBLOBs logIn = new Log_jkfwWithBLOBs();
        try {
            String dd = logDao.selectDate();
            logIn.setJSSJ(dd);
        } catch (Exception e) {
            logIn.setJSSJ("");
        }
        logIn.setXLH(uid);
        int a = 0;
        XMLUtil xmlUtil = new XMLUtil();
        SqshPackage pkg = new SqshPackage();
        SqshPackage pk = new SqshPackage();
        SqshPackageHead pkh = new SqshPackageHead();
        String xml = "";
        SqshRecord sqsh = null;
        SqshRecord rxbd = null;
        SqshRecord dataSuc = new SqshRecord();
        SqshRecord clxx_xp = new SqshRecord();
        List<SqshRecord> clxxs = new ArrayList<>();
        //开启事物
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            pk = (SqshPackage) xmlUtil.convertXmlStrToObject(SqshPackage.class, information);
            a++;//a=1
            pkh = pk.getSqshPackageHead();

            for (SqshRecord r : pk.getData()) {
                if ("011101".equals(r.getSid())) {
                    dataSuc = r;
                    sqsh = r;
                    sqsh.setID(uid);
                } else if ("011102".equals(r.getSid())) {
                    rxbd = r;
                    rxbd.setID(uid);
                } else if ("010100".equals(r.getSid())) {
                    String uuid = UUID.randomUUID().toString().toLowerCase();
                    r.setID(uuid);
                    clxxs.add(r);
                } else {
                    pkh.setCODE("-71");
                    pkh.setMSG("信息分类代码错误");
                    pkg.setSqshPackageHead(pkh);
                    LogOut(information, logIn, "ZAGL_JMSFZSHQFFKJSFW");
                    return xmlUtil.convertObjToXml(pkg);
                }
            }
            if (sqsh != null) {
                sqshDao.insertSelective(sqsh);
                a++;
                if (rxbd != null) {
                    rxbdDao.insertSelective(rxbd);
                    a++;
                }
                if (clxxs.size() > 0) {
                    for (SqshRecord clxx : clxxs){
                        String uuid_cl = UUID.randomUUID().toString().toLowerCase();
                        clxx.setID(uuid_cl);
//                        System.out.println("uiid_cl:" + clxx.getID());
                        byte[] CLNR_BLOB = XMLUtil.base64ToByte(clxx.getCLNR());
                        clxx.setCLNR_BLOB(CLNR_BLOB);
                        clxx1_Dao.insertSelective(clxx);
                    }
                    String uiid_blob = UUID.randomUUID().toString().toLowerCase();
//                    System.out.println(uiid_blob);
                    clxx_xp.setID(uiid_blob);
                    clxx_xp.setYWXTBH(sqsh.getYWXTBH());
                    clxx_xp.setSXH("0");
                    clxx_xp.setCLLBDM("000");
                    clxx_xp.setCLMC("身份证人像照片");
                    clxx_xp.setCLGS("JPG");
                    clxx_xp.setCLNR(sqsh.getSDXP());
                    byte[] XP = XMLUtil.base64ToByte(sqsh.getSDXP());
                    clxx_xp.setCLNR_BLOB(XP);
                    clxx1_Dao.insert(clxx_xp);
//                    clxxDao.insertClxxs(clxxs);
                }
            } else {
                pkh.setCODE("-41");
                pkh.setMSG("协同数据部分发生异常");
                pkg.setSqshPackageHead(pkh);
                LogOut(information, logIn, "ZAGL_JMSFZSHQFFKJSFW");
                return xmlUtil.convertObjToXml(pkg);
            }
            dataSourceTransactionManager.commit(transactionStatus);     //手动提交
            pkh.setCODE("10");
            pkh.setMSG("接口调用成功");
            xml = sqshSuc(pkh, dataSuc);
            sqshLogOut(pk, logIn, information);
//            System.out.println(xml);
            return xml;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();

            dataSourceTransactionManager.rollback(transactionStatus);//事物回滚

            switch (a) {
                case 0:
                    pkh.setCODE("-31");
                    pkh.setMSG("XML不符合规范格式");
                    pkg.setSqshPackageHead(pkh);
                    xml = xmlUtil.convertObjToXml(pkg);
                    LogOut(information, logIn, "ZAGL_JMSFZSQSHQFJSFW");
                    break;
                case 1:
                    sqshLogOut(pk, logIn, information);
                    assert sqsh != null;
                    xml = sqshErr(pkh, sqsh);
                    break;
                case 2:
                    sqshLogOut(pk, logIn, information);
                    assert rxbd != null;
                    xml = sqshErr(pkh, rxbd);
                    break;
                case 3:
                    sqshLogOut(pk, logIn, information);
                    xml = sqshErr(pkh, clxxs);
            }

            return xml;
        }
    }

    @Override
    public String ZAGL_JMSFZSHQFFKJSFW(String sqzcm, String information) {
        logger.info(information);
        logger.info("ZAGL_JMSFZSHQFFKJSFW接口被调用！");

        if (sqzcm.isEmpty()) return "<MSG>授权注册码为空</MSG>";
        logger.info("授权注册码为:" + sqzcm);
        String uid = UUID.randomUUID().toString().toLowerCase();
        Log_jkfwWithBLOBs logIn = new Log_jkfwWithBLOBs();

        try {
            String dd = logDao.selectDate();
            logIn.setJSSJ(dd);
        } catch (Exception e) {
            logIn.setJSSJ("");
        }
        logIn.setXLH(uid);
        XMLUtil xmlUtil = new XMLUtil();
        ShqfPackage pk = new ShqfPackage();
        ShqfPackage pkg = new ShqfPackage();
        ShqfPackageHead pkh = new ShqfPackageHead();
        String xml = "";
        int a = 0;
        ShqfRecord shqf;
        ShqfRecord dataSuc;
        //开启事务
        TransactionStatus transactionStatus_fk = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            pk = (ShqfPackage) xmlUtil.convertXmlStrToObject(ShqfPackage.class, information);
            a++;//1
            pkh = pk.getShqfPackageHead();
            shqf = pk.getShqfDataReq().getShqfRecord();

//            新增需求
//            int num = shqfDao.selectYWXTBH(pkh.getYWXTBH());
//            if (num > 0) {
//                shqfDao.updateYWXTBH(shqf.getJMSFZSHJGDM(), shqf.getYWXTBH(), shqf.getSHJG_JYQK());
//            } else {
//                pkh.setCODE("-70");
//                pkh.setMSG("无对应申请业务协同编号，不予接收");
//                pkg.setShqfPackageHead(pkh);
//                LogOut(information, logIn, "ZAGL_JMSFZSHQFFKJSFW");
//                return xmlUtil.convertObjToXml(pkg);
//            }

            if ("011103".equals(shqf.getSid())) {

                shqf.setID(uid);
                shqfDao.insertSelective(shqf);
                a++;//2 sfz_glxxDao.insertSelective(sfz_glxx);
//                System.out.println(shqf.getJMSFZSLH());

                //通过业务协同编号查询申请审核签发表信息 提取数据插入其他表
                Kstb_sfz_sqshqfxx_fsWithBLOBs kssfzsqsh = kstb_sfz_sqshqfxx_fsdao.selsqshqf(shqf.getYWXTBH());
                String sldwdqm = kssfzsqsh.getSLDW_GAJGJGDM().substring(4) + "00";
                if(!sldwdqm.substring(2).equals("36")){
                    sldwdqm = "360000";
                }
                String rkxlh = sfz_glxxDao.cxckxlh(shqf.getGMSFHM());
                String ryid = sfz_glxxDao.cxckrkid(shqf.getGMSFHM());
                String zwxlh = sfz_glxxDao.selectSequence("seq_rk_zwxx_xlh.nextval");
                String xpxlh = sfz_glxxDao.selectSequence("seq_rk_zpb_xlh.nextval");
                String slssxq = sfz_glxxDao.cxslssxq(kssfzsqsh.getSLDW_GAJGJGDM());
                String sldqm = sfz_glxxDao.cxsldqm(kssfzsqsh.getSLDW_GAJGJGDM());
                String slpcsdm = sfz_glxxDao.cxslpcs(kssfzsqsh.getSLDW_GAJGJGDM());
                String slfxj = sfz_glxxDao.cxslfxj(kssfzsqsh.getSLDW_GAJGJGDM());
                String sldwlx = sfz_glxxDao.cxsldwlx(kssfzsqsh.getSLDW_GAJGJGDM());//1

                System.out.println("-----------开始操作身份证表-----------");

                Sfz_glxx sfz_glxx = new Sfz_glxx();

                sfz_glxx.setXLH(sfz_glxxDao.selectSequence("seq_sfz_glxx_xlh.nextval"));//系列号
                sfz_glxx.setZT("5");//状态
                sfz_glxx.setBDSJ(kstb_sfz_sqshqfxx_fsdao.datetostring());//变动时间
                sfz_glxx.setYWLX("66");//业务类型

                sfz_glxx.setDQM(sldwdqm);//地区码
                sfz_glxx.setRKXLH(rkxlh);//人口序列号
                sfz_glxx.setSLH(shqf.getJMSFZSLH());//受理号
                sfz_glxx.setRYID(ryid);//人员ID
                sfz_glxx.setGMSFHM(shqf.getGMSFHM());//公民身份号码
                sfz_glxx.setXM(shqf.getXM());//姓名
                sfz_glxx.setXB(kssfzsqsh.getXBDM());//性别
                sfz_glxx.setMZ(kssfzsqsh.getMZDM());//民族
                DateFormat csrqdf = new SimpleDateFormat("yyyyMMdd");
                String csrq = csrqdf.format(kssfzsqsh.getCSRQ());
                sfz_glxx.setCSRQ(csrq);//出生日期

                sfz_glxx.setYXQXQSRQ(shqf.getQFRQ());//有效期限起始日期
                AgeUtil age = new AgeUtil();
                String yxjzrq = "";
                System.out.println("csrq" + kssfzsqsh.getCSRQ() + "///qfrq:" + kssfzsqsh.getCSRQ() + "//age:" + age.getAge(kssfzsqsh.getCSRQ()));
                if (age.getAge(kssfzsqsh.getCSRQ()) < 16)
                    yxjzrq = age.getDate(shqf.getQFRQ(), 1);
                else if (age.getAge(kssfzsqsh.getCSRQ()) >= 16 & age.getAge(kssfzsqsh.getCSRQ()) <= 25)
                    yxjzrq = age.getDate(shqf.getQFRQ(), 2);
                else if (age.getAge(kssfzsqsh.getCSRQ()) > 25 & age.getAge(kssfzsqsh.getCSRQ()) <= 45)
                    yxjzrq = age.getDate(shqf.getQFRQ(), 3);
                else
                    yxjzrq = age.getDate(shqf.getQFRQ(), 4);
                sfz_glxx.setYXQXJZRQ(yxjzrq);//有效期限截止日期
                sfz_glxx.setSDZPXLH(xpxlh);//视读照片序列号
                sfz_glxx.setDZBM("");//地址编码
                sfz_glxx.setSSXQ(kssfzsqsh.getHJDZ_SSXQDM());//省市县区
                sfz_glxx.setPCSJGDM(kssfzsqsh.getSLDW_GAJGJGDM());//派出所机构代码
                sfz_glxx.setPCSDM(kssfzsqsh.getSLDW_GAJGJGDM().substring(9));//派出所代码
                sfz_glxx.setPCSJGMC("");//派出所机构名称
                sfz_glxx.setXZJDDM("");//乡镇街道代码
                sfz_glxx.setXZJDMC("");//乡镇街道名称
                sfz_glxx.setSQJCWHDM("");//社区局村委会代码
                sfz_glxx.setSFZZZ(shqf.getDZMC() == null ? kssfzsqsh.getDZMC() : shqf.getDZMC());//身份证住址
                sfz_glxx.setSLYY(kssfzsqsh.getJMSFZSLYYDM());//受理原因
                sfz_glxx.setZZLX(kssfzsqsh.getJMSFZZZLXDM());//制证类型
                sfz_glxx.setLZFS(kssfzsqsh.getJMSFZLZFSDM());//领证方式
                sfz_glxx.setLSSLBS(kssfzsqsh.getLSSFZSLBS() == null ? "0" : kssfzsqsh.getLSSFZSLBS());//临时受理标识
                sfz_glxx.setSLJGDM(kssfzsqsh.getSLDW_GAJGJGDM());//受理机构代码
                sfz_glxx.setSLJGMC(kssfzsqsh.getSLDW_GAJGMC());//受理机构名称
                sfz_glxx.setSLRXM(kssfzsqsh.getSLR_XM());//受理人姓名
                DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
                String slsj = df.format(kssfzsqsh.getSLSJ());
                sfz_glxx.setSLSJ(slsj);//受理时间
                sfz_glxx.setSJRXM("");//收件人姓名
                sfz_glxx.setSJRLXDH("");//收件人联系电话
                sfz_glxx.setSJRYB("");//收件人yb
                sfz_glxx.setSJRTXDZ("");//收件人通讯地址
                sfz_glxx.setSHDWJGDM(shqf.getSHDW_GAJGJGDM());//审核单位代码
                sfz_glxx.setSHDWJGMC(shqf.getSHDW_GAJGJGMC());//审核单位名称
                sfz_glxx.setSHRXM(shqf.getSHR_XM());//审核人姓名
                sfz_glxx.setSHRQ(shqf.getSHRQ());//审核日期
                sfz_glxx.setQFJGJGDM(shqf.getQFJG_GAJGDM());//签发机关代码
                sfz_glxx.setQFJGJGMC(shqf.getQFJG_GAJGMC());//签发机关名称
                sfz_glxx.setQFRXM(shqf.getQFR_XM());//签发人姓名
                sfz_glxx.setQFRQ(shqf.getQFRQ());//签发日期
                sfz_glxx.setZZDWFSRQ("");//制证单位发送日期
                sfz_glxx.setJDXPXLH("");//机读相片序列号
                sfz_glxx.setSFZZZ1("");//
                sfz_glxx.setSFZZZ2("");//
                sfz_glxx.setSFZZZ3("");//
                sfz_glxx.setSFZZZ4("");//
                sfz_glxx.setZJDDRQ("");//
                sfz_glxx.setZJFFDWDM("");//
                sfz_glxx.setZJFFDWMC("");//
                sfz_glxx.setZJFFRXM("");//
                sfz_glxx.setZJFFRQ("");//
                sfz_glxx.setJSDWDM("");//
                sfz_glxx.setJSDWMC("");//
                sfz_glxx.setJSRXM("");//
                sfz_glxx.setFHJSDWDM("");//
                sfz_glxx.setFHJSDWMC("");//
                sfz_glxx.setFHJSRXM("");//
                sfz_glxx.setFHJSRQ("");//
                sfz_glxx.setLQRQ("");//
                sfz_glxx.setLQRHM("");//
                sfz_glxx.setLQRXM("");//
                sfz_glxx.setFFDWDM("");//
                sfz_glxx.setFFDWMC("");//
                sfz_glxx.setFFRXM("");//
                sfz_glxx.setFFRQ("");//
                sfz_glxx.setZJSHRQ("");//
                sfz_glxx.setZJSHJGDM("");//
                sfz_glxx.setZJSHJGMC("");//
                sfz_glxx.setZJSHSLRXM("");//
                sfz_glxx.setZJSHSLSJ("");//
                sfz_glxx.setXHJGDM("");//
                sfz_glxx.setXHJGMC("");//
                sfz_glxx.setXHRXM("");//
                sfz_glxx.setXHRQ("");//
                sfz_glxx.setYSDD("");//
                sfz_glxx.setYSRQ("");//
                sfz_glxx.setGSRHM("");//
                sfz_glxx.setGSRXM("");//
                sfz_glxx.setGSSLJGDM("");//
                sfz_glxx.setGSSLJGMC("");//
                sfz_glxx.setGSSLRXM("");//
                sfz_glxx.setGSSLSJ("");//
                sfz_glxx.setCWLB("");//错误类别
                sfz_glxx.setCWMS("");//错误描述
                sfz_glxx.setJYDM("");//
                sfz_glxx.setJYDW("");//
                sfz_glxx.setJYRXM("");//
                sfz_glxx.setJYRQ("");//
                sfz_glxx.setCLDM("");//处理代码
                sfz_glxx.setCLDW("");//
                sfz_glxx.setCLRXM("");//
                sfz_glxx.setCLRQ("");//
                sfz_glxx.setCLJG("");//
                sfz_glxx.setPCSHCSJ("");//派出所核查时间
                sfz_glxx.setPCSHCR("");//派出所核查人
                sfz_glxx.setPCSHCQK("");//派出所核查情况
                sfz_glxx.setQXJHCSJ("");//
                sfz_glxx.setQXJHCR("");//
                sfz_glxx.setQXJHCQK("");//
                sfz_glxx.setDSJHCSJ("");//
                sfz_glxx.setDSJHCR("");//
                sfz_glxx.setDSJHCQK("");//
                sfz_glxx.setSJHCSJ("");//
                sfz_glxx.setSJHCR("");//
                sfz_glxx.setSJHCQK("");//
                sfz_glxx.setXPCJSJ("");//相片采集时间
                sfz_glxx.setXJGAJGJGDM("");//
                sfz_glxx.setXJGAJGJGMC("");//
                sfz_glxx.setCXCSBS("");//
                sfz_glxx.setPCSKZXXXLH("");//
                sfz_glxx.setQXKZXXXLH("");//
                sfz_glxx.setDSKZXXXLH("");//
                sfz_glxx.setSKZXXXLH("");//
                sfz_glxx.setDSSJBXLH("");//
                sfz_glxx.setSSJBXLH("");//
                sfz_glxx.setZZFYDM("");//
                sfz_glxx.setZZFY(0);//
                sfz_glxx.setYDFY("");//
                sfz_glxx.setZT6("");//
                sfz_glxx.setSLDWLX(sldwlx);//
                sfz_glxx.setJMZDM("");//
                sfz_glxx.setJMZMC("");//
                sfz_glxx.setSJBH("");//
                sfz_glxx.setWSSLH("");//
                sfz_glxx.setSFBZ("1");//
                sfz_glxx.setWCSJ("");//
                sfz_glxx.setFHJSBZ("");//
                sfz_glxx.setSFZZZJG("");//
                sfz_glxx.setJS("");//
                sfz_glxx.setFXJDM("360000");//
                sfz_glxx.setSSMZXM("");//
                sfz_glxx.setSSMZXB("");//
                sfz_glxx.setSSMZMZ("");//
                sfz_glxx.setSSMZZZ1("");//
                sfz_glxx.setSSMZZZ2("");//
                sfz_glxx.setSSMZZZ3("");//
                sfz_glxx.setSSMZQFJG("");//
                sfz_glxx.setSSMZLRBS("");//
                sfz_glxx.setSSXQ1("");//
                sfz_glxx.setCWCXBLBZ("");//
                sfz_glxx.setZZXQZRCT("");//
                sfz_glxx.setXMPY("");//
                sfz_glxx.setLXDH(kssfzsqsh.getLXDH());//
                sfz_glxx.setHKSJ("");//
                sfz_glxx.setZWXLH(zwxlh);//
                sfz_glxx.setYDBLBZ("6");//
                sfz_glxx.setYDTM("");//
                sfz_glxx.setSPDWJGDM("");//
                sfz_glxx.setSPDWMC("");//
                sfz_glxx.setSPRXM("");//
                sfz_glxx.setSPSJ("");//
                sfz_glxx.setSPJG("1");//
//                    sfz_glxx.setZXQSSJ();//
//                    sfz_glxx.setZXQSBZ();//
//                    sfz_glxx.setZXCKQRSJ();//
//                    sfz_glxx.setZXCKQRZT();//
                sfz_glxx.setZWHYBZ("1");//指纹核验标志
                sfz_glxx.setDSSCSJ("");//
                sfz_glxx.setZWHYSJ("");//
                sfz_glxx.setFXJMC("");//
//                    sfz_glxx.setSJSLBZ("");//
                sfz_glxx.setPOSTID("");//
                sfz_glxx.setBZ("");//
                sfz_glxx.setSLSSXQ(slssxq);//受理省市县区
                sfz_glxx.setSLPCSDM(slpcsdm);//受理派出所代码
                sfz_glxx.setSLDQM(sldqm);//
                sfz_glxx.setMZBZ("");//
                sfz_glxx.setSJJZDZ("");//
                sfz_glxx.setSLFXJDM(slfxj);//
                sfz_glxx.setSJRSSXQ("");//
                sfz_glxx.setGXSJ(new Date());//更新时间
                sfz_glxx.setYWFL("");//
                System.out.println(csrq + "//csrq:" + kssfzsqsh.getCSRQ());
                sfz_glxxDao.insertSelective(sfz_glxx);
                a++;//3 rk_zpxxDao.insertSelective(rk_zpxx);
                System.out.println("-----------开始操作照片表-----------");
                Rk_zpxx rk_zpxx = new Rk_zpxx();

                rk_zpxx.setXLH(xpxlh);//序列号
                rk_zpxx.setZT(Short.valueOf("0"));
                rk_zpxx.setXPLX("1");
                rk_zpxx.setRKLB("2");
                rk_zpxx.setSSSSX(kssfzsqsh.getHJDZ_SSXQDM());
                rk_zpxx.setSLSJ(new Date());//受理时间


                rk_zpxx.setDQM(sldwdqm);//地区码
                rk_zpxx.setGMSFHM(shqf.getGMSFHM());//公民身份号码
                rk_zpxx.setXM(shqf.getXM());//公民身份号码
                //sfz_glxxDao.cxsqxp(shqf.getYWXTBH(), "000");
                //rk_zpxx.setXP(sfz_glxxDao.cxsqxp(shqf.getYWXTBH(), "000"));//相片
                rk_zpxx.setSJGSDWDM(shqf.getJSDW_SJGSDWDM());//数据归属单位代码
                rk_zpxx.setGXSJ(new Date());//更新时间
                rk_zpxx.setPCSDM("");//派出所代码
                rk_zpxx.setRKXLH("");//人口序列号
                //rkzp.setDsxlh();//地市序列号
                byte[] xp = xmlUtil.base64ToByte(kssfzsqsh.getSDXP());
                rk_zpxx.setXP(xp);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//注意月份是MM
                rk_zpxx.setCJSJ(simpleDateFormat.format(new Date()));//采集时间
                rk_zpxxDao.insertSelective(rk_zpxx);
                a++;//4 zwqd_zwsjxxDao.insertSelective(zwsjxxWithBLOBs);
                System.out.println("-----------开始操作指纹表-----------");
                Zwqd_zwsjxxWithBLOBs zwsjxxWithBLOBs = new Zwqd_zwsjxxWithBLOBs();

                zwsjxxWithBLOBs.setXLH(zwxlh);//序列号
                zwsjxxWithBLOBs.setDQM(sldwdqm);//地区码
                zwsjxxWithBLOBs.setSSXQ(kssfzsqsh.getHJDZ_SSXQDM());//省市县码
                zwsjxxWithBLOBs.setJMSFZSLH(shqf.getJMSFZSLH());//居民身份证受理号
                zwsjxxWithBLOBs.setGMSFHM(shqf.getGMSFHM());//公民身份号码
                zwsjxxWithBLOBs.setXM(shqf.getXM());//姓名
                zwsjxxWithBLOBs.setZWCJJG(kssfzsqsh.getZWCJJGDM());//指纹采集结果
                zwsjxxWithBLOBs.setZWY_ZCJG(kssfzsqsh.getZWY_ZWZCJGDM());//指纹一_注册结果
                zwsjxxWithBLOBs.setZWY_ZW(kssfzsqsh.getZWY_ZWDM());//指纹一_指位
                zwsjxxWithBLOBs.setZWY_TXSJ(kssfzsqsh.getZWY_ZWTXSJ());//指纹一_图像数据
                zwsjxxWithBLOBs.setZWY_TXZLZ(String.valueOf(kssfzsqsh.getZWY_ZWTXZLZ()));//指纹一_图像质量值
                zwsjxxWithBLOBs.setZWY_TZSJ(kssfzsqsh.getZWY_ZWTZSJ());//指纹一_特征数据
                zwsjxxWithBLOBs.setZWE_ZCJG(kssfzsqsh.getZWE_ZWZCJGDM());//指纹二_注册结果
                zwsjxxWithBLOBs.setZWE_ZW(kssfzsqsh.getZWE_ZWDM());//指纹二_指位
                zwsjxxWithBLOBs.setZWE_TXSJ(kssfzsqsh.getZWE_ZWTXSJ());//指纹二_图像数据
                zwsjxxWithBLOBs.setZWE_TXZLZ(String.valueOf(kssfzsqsh.getZWE_ZWTXZLZ()));//指纹二_图像质量值
                zwsjxxWithBLOBs.setZWE_TZSJ(kssfzsqsh.getZWE_ZWTZSJ());//指纹二_特征数据
                zwsjxxWithBLOBs.setZWCJQID(kssfzsqsh.getZWCJQBSH());//指纹采集器ID
                zwsjxxWithBLOBs.setCJDW_GAJGJGDM(kssfzsqsh.getSLDW_GAJGJGDM());//采集单位_公安机关机构代码
                zwsjxxWithBLOBs.setCJDW_GAJGMC(kssfzsqsh.getSLDW_GAJGMC());//采集单位_公安机关名称
                zwsjxxWithBLOBs.setCJR_XM(kssfzsqsh.getSLR_XM());//采集人_姓名
                zwsjxxWithBLOBs.setCJSJ(simpleDateFormat.format(kssfzsqsh.getSLSJ()));//采集时间
                zwsjxxWithBLOBs.setSJGSDWDM(kssfzsqsh.getFSDW_SJGSDWDM());//数据归属单位代码
                zwsjxxWithBLOBs.setSJGSDWMC(kssfzsqsh.getFSDW_SJGSDWMC());//数据归属单位名称
                zwsjxxWithBLOBs.setSJSCBS("0");//数据上传标识（0-未上传，1-已上传）
                zwsjxxWithBLOBs.setZT("0");//删除标识 （0正常1删除）
                zwsjxxWithBLOBs.setXTZCH(kssfzsqsh.getZWQDXTZCH());//系统注册号
                zwsjxxWithBLOBs.setZWY_TXSJ_WS(kssfzsqsh.getZWY_ZWTXSJ());//指纹一_图像数据_无损
                zwsjxxWithBLOBs.setZWE_TXSJ_WS(kssfzsqsh.getZWE_ZWTXSJ());//指纹二_图像数据_无损
                zwsjxxWithBLOBs.setGXSJ(kstb_sfz_sqshqfxx_fsdao.datetostring());//更新时间

                zwqd_zwsjxxDao.insertSelective(zwsjxxWithBLOBs);
                System.out.println("-----------操作指纹表结束-----------");
                dataSuc = pk.getShqfDataReq().getShqfRecord();
                shqfLogOut(pk, logIn, information);
                dataSourceTransactionManager.commit(transactionStatus_fk);     //手动提交
                pkh.setCODE("10");
                pkh.setMSG("接口调用成功");
                xml = shqfSuc(pkh, dataSuc);
                return xml;
            } else {
                pkh.setCODE("-71");
                pkh.setMSG("信息分类代码错误");
                pkg.setShqfPackageHead(pkh);
                LogOut(information, logIn, "ZAGL_JMSFZSHQFFKJSFW");
                return xmlUtil.convertObjToXml(pkg);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus_fk);//事物回滚

            switch (a) {
                case 0:
                    pkh.setCODE("-31");
                    pkh.setMSG("XML不符合规范格式");
                    pkg.setShqfPackageHead(pkh);
                    xml = xmlUtil.convertObjToXml(pkg);
                    LogOut(information, logIn, "ZAGL_JMSFZSHQFFKJSFW");
                    break;
                case 1:
                    shqfLogOut(pk, logIn, information);
                    ShqfRecordErr sre = new ShqfRecordErr();
                    ShqfDataErr sde = new ShqfDataErr();
                    ShqfPackageErr spe = new ShqfPackageErr();
                    sre.setSid(pk.getShqfDataReq().getShqfRecord().getSid());
                    sre.setNo("1");
                    sre.setCode("-72");
                    sre.setMsg("数据项非法");
                    sde.setRecord(sre);
                    spe.setShqfPackageHead(pkh);
                    spe.setShqfDataErr(sde);
                    xml = xmlUtil.convertObjToXml(spe);
                    break;
                case 2:
                    shqfLogOut(pk, logIn, information);
                    ShqfRecordErr sre1 = new ShqfRecordErr();
                    ShqfDataErr sde1 = new ShqfDataErr();
                    ShqfPackageErr spe1 = new ShqfPackageErr();
                    sre1.setSid(pk.getShqfDataReq().getShqfRecord().getSid());
                    sre1.setNo("1");
                    sre1.setCode("-55");
                    sre1.setMsg("sfz_glxx出错");
                    sde1.setRecord(sre1);
                    spe1.setShqfPackageHead(pkh);
                    spe1.setShqfDataErr(sde1);
                    xml = xmlUtil.convertObjToXml(spe1);
                    break;
                case 3:
                    shqfLogOut(pk, logIn, information);
                    ShqfRecordErr sre2 = new ShqfRecordErr();
                    ShqfDataErr sde2 = new ShqfDataErr();
                    ShqfPackageErr spe2 = new ShqfPackageErr();
                    sre2.setSid(pk.getShqfDataReq().getShqfRecord().getSid());
                    sre2.setNo("1");
                    sre2.setCode("-56");
                    sre2.setMsg("rk_zpxx出错");
                    sde2.setRecord(sre2);
                    spe2.setShqfPackageHead(pkh);
                    spe2.setShqfDataErr(sde2);
                    xml = xmlUtil.convertObjToXml(spe2);
                    break;
                case 4:
                    shqfLogOut(pk, logIn, information);
                    ShqfRecordErr sre3 = new ShqfRecordErr();
                    ShqfDataErr sde3 = new ShqfDataErr();
                    ShqfPackageErr spe3 = new ShqfPackageErr();
                    sre3.setSid(pk.getShqfDataReq().getShqfRecord().getSid());
                    sre3.setNo("1");
                    sre3.setCode("-57");
                    sre3.setMsg("zwqd_zwsjxx出错");
                    sde3.setRecord(sre3);
                    spe3.setShqfPackageHead(pkh);
                    spe3.setShqfDataErr(sde3);
                    xml = xmlUtil.convertObjToXml(spe3);
                    break;

            }

            return xml;
        }
    }

    @Override
    public String ZAGL_ZJZZFKJSFW(String sqzcm, String information) {
        logger.info(information);
        logger.info("ZAGL_ZJZZFKJSFW接口被调用！");

        if (sqzcm.isEmpty()) return "<MSG>授权注册码为空</MSG>";
        logger.info("授权注册码为:" + sqzcm);
        String uid = UUID.randomUUID().toString().toLowerCase();
        Log_jkfwWithBLOBs logIn = new Log_jkfwWithBLOBs();
        try {
            String dd = logDao.selectDate();
            logIn.setJSSJ(dd);
        } catch (Exception e) {
            logIn.setJSSJ("");
        }
        logIn.setXLH(uid);
        XMLUtil xmlUtil = new XMLUtil();
        ZjzzPackage pk = new ZjzzPackage();
        ZjzzPackage pkg = new ZjzzPackage();
        ZjzzPackageHead pkh = new ZjzzPackageHead();
        String xml = "";
        int a = 0;
        ZjzzRecord zjzz = new ZjzzRecord();
        try {
            pk = (ZjzzPackage) xmlUtil.convertXmlStrToObject(ZjzzPackage.class, information);
            a++;
            pkh = pk.getZjzzPackageHead();
            for (ZjzzRecord z : pk.getData()) {
                if (z.getSid().equals("011104")) {//质量回馈信息
                    //                    新增需求
//                    int num = zlhkDao.selectYWXTBH(pkh.getYWXTBH());
//                    if (num>0){
//                        zlhkDao.updateZLHK_YWXTBH(pkh.getYWXTBH());
//                    }else {
//                        pkh.setCODE("-70");
//                        pkh.setMSG("无对应申请业务协同编号，不予接收");
//                        pkg.setZjzzPackageHead(pkh);
//                        LogOut(information, logIn, "ZAGL_ZJZZFKJSFW");
//                        return xmlUtil.convertObjToXml(pkg);
//                    }
                    zjzz = z;
                    zjzz.setID(uid);
                    zlhkDao.insertSelective(zjzz);
                    pkh.setCODE("10");
                    pkh.setMSG("接口调用成功");
                    xml = zjzzSuc(pkh, z);
                } else if (z.getSid().equals("011105")) {//制证回馈信息
                    //                    新增需求
//                    int num = zlhkDao.selectYWXTBH(pkh.getYWXTBH());
//                    if (num > 0) {
//                        zlhkDao.updateZZHK_YWXTBH(pkh.getYWXTBH());
//                    } else {
//                        pkh.setCODE("-70");
//                        pkh.setMSG("无对应申请业务协同编号，不予接收");
//                        pkg.setZjzzPackageHead(pkh);
//                        LogOut(information, logIn, "ZAGL_ZJZZFKJSFW");
//                        return xmlUtil.convertObjToXml(pkg);
//                    }
                    zjzz = z;
                    zjzz.setID(uid);
                    zzhkDao.insertSelective(zjzz);
                    pkh.setCODE("10");
                    pkh.setMSG("接口调用成功");
                    xml = zjzzSuc(pkh, z);
                } else {
                    pkh.setCODE("-71");
                    pkh.setMSG("信息分类代码错误");
                    pkg.setZjzzPackageHead(pkh);
                    xml = xmlUtil.convertObjToXml(pkg);
                    LogOut(information, logIn, "ZAGL_ZJZZFKJSFW");
                }
            }
            a++;
            zjzzLogOut(pk, logIn, information);
            return xml;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            switch (a) {
                case 0:
                    pkh.setCODE("-31");
                    pkh.setMSG("XML不符合规范格式");
                    pkg.setZjzzPackageHead(pkh);
                    xml = xmlUtil.convertObjToXml(pkg);
                    LogOut(information, logIn, "ZAGL_ZJZZFKJSFW");
                    break;
                case 1:
                    zjzzLogOut(pk, logIn, information);
                    ZjzzRecordErr zre = new ZjzzRecordErr();
                    ZjzzPackageErr zpe = new ZjzzPackageErr();
                    List<ZjzzRecordErr> list = new ArrayList<>();
                    zre.setSid(zjzz.getSid());
                    zre.setNo("1");
                    zre.setCode("-72");
                    zre.setMsg("数据项非法");
                    list.add(zre);
                    zpe.setZjzzPackageHead(pkh);
                    zpe.setData(list);
                    xml = xmlUtil.convertObjToXml(zpe);
                    zjzzLogOut(pk, logIn, information);
                    break;
            }
            return xml;
        }
    }

    @Override
    public String ZAGL_SQKJHJZMJSFW(String sqzcm, String information) {
        logger.info("授权注册码："+sqzcm);
        logger.info("WebService请求参数："+information);
            if(sqzcm==null){
                PACKAGE returnXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                Document document = loadXml("./kstb_configuration/ex_sqzcm.xml");
                Element rootElement = document.getRootElement();
                // 过滤出PACKAGEHEAD下的子节点
                for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                    Element headElement = iterator.next();
                    Element bbh = headElement.element("BBH");
                    Element fsd = headElement.element("FSDW_SJGSDWDM");
                    Element fsm = headElement.element("FSDW_SJGSDWMC");
                    Element jsd = headElement.element("JSDW_SJGSDWDM");
                    Element jsm = headElement.element("JSDW_SJGSDWMC");
                    Element ywfl = headElement.element("ZAGLYWFLDM");
                    Element ywbh = headElement.element("YWXTBH");
                    Element qyfw = headElement.element("YWXTQYFW");
                    Element fssj = headElement.element("FSSJ");
                    bbh.setText(returnXml.getPacskageHead().getBbh());
                    fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                    fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                    jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                    jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                    ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                    ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                    qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                    fssj.setText(returnXml.getPacskageHead().getFssj());
                }
                // 过滤出DATA节点下的子节点
                for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                    Element dataElement = iterator.next();
                    Element code = dataElement.element("CODE");
                    Element msg = dataElement.element("MSG");
                    code.setText("-50");
                    msg.setText("许可证校验错误");
                }
                return document.asXML();
            }
        HjzmPackeage hjzmPackeage=null;
        try {
            hjzmPackeage= (HjzmPackeage) JAXBUtils.xmlStrToObject(HjzmPackeage.class,information);
            if(hjzmPackeage==null){
                return "<PACKAGE>\n" +
                        "    <PACKAGEHEAD>\n" +
                        "        <BBH>...</BBH>\n" +
                        "        <FSDW_SJGSDWDM>...</FSDW_SJGSDWDM>\n" +
                        "        <FSDW_SJGSDWMC>...</FSDW_SJGSDWMC>\n" +
                        "        <JSDW_SJGSDWDM>...</JSDW_SJGSDWDM>\n" +
                        "        <JSDW_SJGSDWMC>...</JSDW_SJGSDWMC>\n" +
                        "        <HZGLYWXTFLYDM>...</HZGLYWXTFLYDM>\n" +
                        "        <ywxtbh>...</ywxtbh> \n" +
                        "        <XTYWQYFW>...</XTYWQYFW>\n" +
                        "        <FSSJ>...</FSSJ>\n" +
                        "    </PACKAGEHEAD>\n" +
                        "    <DATA>\n" +
                        "        <CODE>-31</CODE>\n" +
                        "        <MSG>XML或JSON不符合规范</MSG>\n" +
                        "    </DATA>\n" +
                        "</PACKAGE>";
            }



        }catch (Exception e){
            //e.printStackTrace();
            logger.error("接口异常描述："+e.getMessage());
            return "<PACKAGE>\n" +
                    "    <PACKAGEHEAD>\n" +
                    "        <BBH>...</BBH>\n" +
                    "        <FSDW_SJGSDWDM>...</FSDW_SJGSDWDM>\n" +
                    "        <FSDW_SJGSDWMC>...</FSDW_SJGSDWMC>\n" +
                    "        <JSDW_SJGSDWDM>...</JSDW_SJGSDWDM>\n" +
                    "        <JSDW_SJGSDWMC>...</JSDW_SJGSDWMC>\n" +
                    "        <HZGLYWXTFLYDM>...</HZGLYWXTFLYDM>\n" +
                    "        <ywxtbh>...</ywxtbh> \n" +
                    "        <XTYWQYFW>...</XTYWQYFW>\n" +
                    "        <FSSJ>...</FSSJ>\n" +
                    "    </PACKAGEHEAD>\n" +
                    "    <DATA>\n" +
                    "        <CODE>-31</CODE>\n" +
                    "        <MSG>XML或JSON不符合规范</MSG>\n" +
                    "    </DATA>\n" +
                    "</PACKAGE>";
        }

        ArrayList<ErrData> list=new ArrayList<ErrData>();
        try{
            caoZuo.shenQing(hjzmPackeage,list);
            logger.info("申请开具户籍证明服务信息添加成功！");
        }catch (Exception e){
            logger.error("接口异常描述："+e.getMessage());
            int c=list.size();
            ErrData r=list.get(c-1);
            r.setCode("-41");
            r.setMsg("协同数据部分发生异常");
            e.printStackTrace();
            ErrPackage errPackage=new ErrPackage();
            errPackage.setHjzmPackageHead(hjzmPackeage.getHjzmPackageHead());
            errPackage.setNoData(list);
            return JAXBUtils.objectToXmlStr(errPackage,ErrPackage.class);
        }
        YesPackage yesPackage=new YesPackage();
        hjzmPackeage.getHjzmPackageHead().setCODE("10");
        hjzmPackeage.getHjzmPackageHead().setMSG("成功");
        yesPackage.setHjzmPackageHead(hjzmPackeage.getHjzmPackageHead());
        yesPackage.setHjzmRecord(hjzmPackeage.getData().get(0));

        return JAXBUtils.objectToXmlStr(yesPackage,YesPackage.class);
    }

    @Override
    public String ZAGL_KJHJZMFKJSFW(String sqzcm, String information) {
        logger.info("授权注册码："+sqzcm);
        logger.info("WebService请求参数："+information);
            if(sqzcm==null){
                PACKAGE returnXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
                Document document = loadXml("./kstb_configuration/ex_sqzcm.xml");
                Element rootElement = document.getRootElement();
                // 过滤出PACKAGEHEAD下的子节点
                for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext(); ) {
                    Element headElement = iterator.next();
                    Element bbh = headElement.element("BBH");
                    Element fsd = headElement.element("FSDW_SJGSDWDM");
                    Element fsm = headElement.element("FSDW_SJGSDWMC");
                    Element jsd = headElement.element("JSDW_SJGSDWDM");
                    Element jsm = headElement.element("JSDW_SJGSDWMC");
                    Element ywfl = headElement.element("ZAGLYWFLDM");
                    Element ywbh = headElement.element("YWXTBH");
                    Element qyfw = headElement.element("YWXTQYFW");
                    Element fssj = headElement.element("FSSJ");
                    bbh.setText(returnXml.getPacskageHead().getBbh());
                    fsd.setText(returnXml.getPacskageHead().getFsdwSjgsdwdm());
                    fsm.setText(returnXml.getPacskageHead().getFsdwSjgsdwmc());
                    jsd.setText(returnXml.getPacskageHead().getJsdwSjgsdwdm());
                    jsm.setText(returnXml.getPacskageHead().getJsdwSjgsdwmc());
                    ywfl.setText(returnXml.getPacskageHead().getZaglywfldm());
                    ywbh.setText(returnXml.getPacskageHead().getYwxtbh());
                    qyfw.setText(returnXml.getPacskageHead().getYwxtqyfw());
                    fssj.setText(returnXml.getPacskageHead().getFssj());
                }
                // 过滤出DATA节点下的子节点
                for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext(); ) {
                    Element dataElement = iterator.next();
                    Element code = dataElement.element("CODE");
                    Element msg = dataElement.element("MSG");
                    code.setText("-50");
                    msg.setText("许可证校验错误");
                }
                return document.asXML();
            }
        HjzmPackeage hjzmPackeage=null;
        try {
            hjzmPackeage= (HjzmPackeage) JAXBUtils.xmlStrToObject(HjzmPackeage.class,information);
        }catch (Exception e){
            e.printStackTrace();
            return "<PACKAGE>\n" +
                    "    <PACKAGEHEAD>\n" +
                    "        <BBH>...</BBH>\n" +
                    "        <FSDW_SJGSDWDM>...</FSDW_SJGSDWDM>\n" +
                    "        <FSDW_SJGSDWMC>...</FSDW_SJGSDWMC>\n" +
                    "        <JSDW_SJGSDWDM>...</JSDW_SJGSDWDM>\n" +
                    "        <JSDW_SJGSDWMC>...</JSDW_SJGSDWMC>\n" +
                    "        <HZGLYWXTFLYDM>...</HZGLYWXTFLYDM>\n" +
                    "        <ywxtbh>...</ywxtbh> \n" +
                    "        <XTYWQYFW>...</XTYWQYFW>\n" +
                    "        <FSSJ>...</FSSJ>\n" +
                    "    </PACKAGEHEAD>\n" +
                    "    <DATA>\n" +
                    "        <CODE>-31</CODE>\n" +
                    "        <MSG>XML或JSON不符合规范</MSG>\n" +
                    "    </DATA>\n" +
                    "</PACKAGE>";
        }
        ArrayList<ErrData> list=new ArrayList<ErrData>();
        try{
            caoZuo.fanKui(hjzmPackeage,list);
            logger.info("开具户籍证明反馈信息添加成功！");
        }catch (Exception e){
            logger.error("接口异常描述："+e.getMessage());
            int c=list.size();
            ErrData r=list.get(c-1);
            r.setCode("-41");
            r.setMsg("协同数据部分发生异常");
            e.printStackTrace();
            ErrPackage errPackage=new ErrPackage();
            errPackage.setHjzmPackageHead(hjzmPackeage.getHjzmPackageHead());
            errPackage.setNoData(list);
            return JAXBUtils.objectToXmlStr(errPackage,ErrPackage.class);
        }
        YesPackage yesPackage=new YesPackage();
        hjzmPackeage.getHjzmPackageHead().setCODE("10");
        hjzmPackeage.getHjzmPackageHead().setMSG("接口调用成功");
        yesPackage.setHjzmPackageHead(hjzmPackeage.getHjzmPackageHead());
        yesPackage.setHjzmRecord(hjzmPackeage.getData().get(0));

        return JAXBUtils.objectToXmlStr(yesPackage,YesPackage.class);
    }

    /**
     * 加载Xml并获取文档内容
     *
     * @author <致道科技>YunTong
     * @param fileName 文件名
     * @return document 对象
     * <p>Created on 2021-6-18<p>
     */
    private static Document loadXml(String fileName) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(fileName);
        } catch (DocumentException e) {
            logger.error(document.getName() + "Error！加载不到该文件...");
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 处理申请迁出信息响应结果的方法
     *
     * @author <致道科技>YunTong
     * @param information 请求参数
     * @return 响应申请迁出成功XML
     * <p>Created on 2021-6-24<p>
     * <p>Updated on 2021-8-2<p>
     */
    private static String response_sqqc_Handler(String information) {
        PACKAGE requestXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class, information);
        Document document = loadXml("./kstb_configuration/res_sqqc_info.xml");
        Element rootElement = document.getRootElement();
        // 遍历PACKAGEHEAD下的子节点
        for (Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD"); iterator.hasNext();) {
            Element headElement = iterator.next();
            Element bbh = headElement.element("BBH");
            Element fsd = headElement.element("FSDW_SJGSDWDM");
            Element fsm = headElement.element("FSDW_SJGSDWMC");
            Element jsd = headElement.element("JSDW_SJGSDWDM");
            Element jsm = headElement.element("JSDW_SJGSDWMC");
            Element ywfl = headElement.element("ZAGLYWFLDM");
            Element ywbh = headElement.element("YWXTBH");
            Element qyfw = headElement.element("YWXTQYFW");
            Element fssj = headElement.element("FSSJ");
            Element code = headElement.element("CODE");
            Element msg = headElement.element("MSG");
            bbh.setText(requestXml.getPacskageHead().getBbh());
            fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
            fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
            jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
            jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
            ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
            ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
            qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
            fssj.setText(requestXml.getPacskageHead().getFssj());
            code.setText("10");
            msg.setText("成功");
        }
        // 遍历DATA下的子节点
        for (Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext();) {
            Element dataElement = iterator.next();
            Element ywxtbh = dataElement.element("YWXTBH");
            Element ywlsh = dataElement.element("YWLSH");
            Element sqrGmsfhn = dataElement.element("SQR_GMSFHM");
            Element sqrXm = dataElement.element("SQR_XM");
            Element sqrLxdh = dataElement.element("SQR_LXDH");
			/*Element sqrZzSsxqdm = dataElement.element("SQR_ZZ_SSXQDM");
			Element sqrZzQhnxxdz = dataElement.element("SQR_ZZ_QHNXXDZ");*/
            Element qyrGmsfhm = dataElement.element("QYR_GMSFHM");
            Element qyrXm = dataElement.element("QYR_XM");
            Element qyrXbdm = dataElement.element("QYR_XBDM");
            Element qyrMzdm = dataElement.element("QYR_MZDM");
            Element qyrCsrq = dataElement.element("QYR_CSRQ");
            Element qyrYsqrgxJtgxdm = dataElement.element("QYR_YSQRGX_JTGXDM");
            Element qyldyydm = dataElement.element("QYLDYYDM");
            Element qcdSsxqdm = dataElement.element("QCD_SSXQDM");
            Element qcdQhnxxdz = dataElement.element("QCD_QHNXXDZ");
			/*Element qcdHkdjjgGajgjgdm = dataElement.element("QCD_HKDJJG_GAJGJGDM");
			Element qcdHkdjjgGajgmc = dataElement.element("QCD_HKDJJG_GAJGMC");*/
            Element qrdSsxqdm = dataElement.element("QRD_SSXQDM");
            Element qrdQhnxxdz = dataElement.element("QRD_QHNXXDZ");
            Element qrdHkdjjgGajgjgdm = dataElement.element("QRD_HKDJJG_GAJGJGDM");
            Element qrdHkdjjgGajgmc = dataElement.element("QRD_HKDJJG_GAJGMC");
            /*Element zqzbh = dataElement.element("ZQZBH");*/
            Element qfjgGajgjgdm = dataElement.element("QFJG_GAJGJGDM");
            Element qfjgGajgmc = dataElement.element("QFJG_GAJGMC");
			/*Element cbrXm = dataElement.element("CBR_XM");
			Element qfrq = dataElement.element("QFRQ");
			Element bz = dataElement.element("BZ");
			Element yxqjzrq = dataElement.element("YXQJZRQ");*/
            Element ywxtQyfwdm = dataElement.element("XTYW_QYFWDM");
            Element sldwGajgjgdm = dataElement.element("SLDW_GAJGJGDM");
            Element sldwGajgmc = dataElement.element("SLDW_GAJGMC");
            Element sldwLxdh = dataElement.element("SLDW_LXDH");
            Element slrGmsfhm = dataElement.element("SLR_GMSFHM");
            Element slrXm = dataElement.element("SLR_XM");
            /*Element slrLxdh = dataElement.element("SLR_LXDH");*/
            Element slsj = dataElement.element("SLSJ");
            Element sjgsdwdm = dataElement.element("SJGSDWDM");
            Element sjgsdwmc = dataElement.element("SJGSDWMC");
            Element fsdwSjgsdwdm = dataElement.element("FSDW_SJGSDWDM");
            Element fsdwSjgsdwmc = dataElement.element("FSDW_SJGSDWMC");
            Element jsdwSjgsdwdm = dataElement.element("JSDW_SJGSDWDM");
            Element jsdwSjgsdwmc = dataElement.element("JSDW_SJGSDWMC");
            List<RECORD> recordList = requestXml.getRecordList();
            for (RECORD record : recordList) {
                if (record.getSid().equals("011004")) {
                    ywxtbh.setText(record.getywxtbh());
                    ywlsh.setText(record.getYwlsh());
                    sqrGmsfhn.setText(record.getSqrGmsfhm());
                    sqrXm.setText(record.getSqrXm());
                    sqrLxdh.setText(record.getSqrLxdh());
					/*sqrZzSsxqdm.setText(record.getSqrZzSsxqdm());
					sqrZzQhnxxdz.setText(record.getSqrZzQhnxxdz());*/
                    qyrGmsfhm.setText(record.getQyrGmsfhm());
                    qyrXm.setText(record.getQyrXm());
                    qyrXbdm.setText(record.getQyrXbDm());
                    qyrMzdm.setText(record.getQyrMzDm());
                    qyrCsrq.setText(record.getQyrCsrq());
                    qyrYsqrgxJtgxdm.setText(record.getQyrYsqrgxJtgxdm());
                    qyldyydm.setText(record.getQyldyydm());
                    qcdSsxqdm.setText(record.getQcdSsxqdm());
                    qcdQhnxxdz.setText(record.getQcdQhnxxdz());
					/*qcdHkdjjgGajgjgdm.setText(record.getQcdHkdjjgGajgdm());
					qcdHkdjjgGajgmc.setText(record.getQcdHkdjjgGajgmc());*/
                    qrdSsxqdm.setText(record.getQrdSsxqdm());
                    qrdQhnxxdz.setText(record.getQrdQhnxxdz());
                    qrdHkdjjgGajgjgdm.setText(record.getQrdHkdjjgGajgdm());
                    qrdHkdjjgGajgmc.setText(record.getQrdHkdjjgGajgmc());
                    /*zqzbh.setText(record.getZqzbh());*/
                    qfjgGajgjgdm.setText(record.getQfjgGajgjgdm());
                    qfjgGajgmc.setText(record.getQfjgGajgmc());
					/*cbrXm.setText(record.getCbrXm());
					qfrq.setText(record.getQfRq());
					bz.setText(record.getBz());
					yxqjzrq.setText(record.getYxjzrq());*/
                    /*ywxtQyfwdm.setText(record.getXtywQyfwdm());*/
                    sldwGajgjgdm.setText(record.getSldwGajgjgdm());
                    sldwGajgmc.setText(record.getSldwGajgmc());
                    sldwLxdh.setText(record.getSldwLxdh());
                    slrGmsfhm.setText(record.getSlrGmsfhm());
                    slrXm.setText(record.getSlrXm());
                    /*slrLxdh.setText(record.getSlrLxdh());*/
                    slsj.setText(record.getSlsj());
                    sjgsdwdm.setText(record.getSjgsdwdm());
                    sjgsdwmc.setText(record.getSjgsdwmc());
                    fsdwSjgsdwdm.setText(record.getFsdwSjgsdwdm());
                    fsdwSjgsdwmc.setText(record.getFsdwSjgsdwmc());
                    jsdwSjgsdwdm.setText(record.getJsdwSjgsdwdm());
                    jsdwSjgsdwmc.setText(record.getJsdwSjgsdwmc());
                }
            }
        }
        return document.asXML();
    }

    /**
     * 处理响应不予迁出反馈信息的方法
     *
     * @author <致道科技>YunTong
     * @param information 业务请求参数
     * @return 响应不予迁出反馈XML
     * <p>Created on 2021-6-22<p>
     * <p>Updated on 2021-8-2<p>
     */
    private static String response_byqc_Handler(String information){
        PACKAGE requestXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class,information);
        //读取XML
        Document document = loadXml("./kstb_configuration/res_byqc_info.xml");
        //获取根节点
        Element rootElement = document.getRootElement();
        //遍历PACKAGEHEAD中的子节点
        for(Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD");iterator.hasNext();){
            Element headElement = iterator.next();
            Element bbh = headElement.element("BBH");
            Element fsd = headElement.element("FSDW_SJGSDWDM");
            Element fsm = headElement.element("FSDW_SJGSDWMC");
            Element jsd = headElement.element("JSDW_SJGSDWDM");
            Element jsm = headElement.element("JSDW_SJGSDWMC");
            Element ywfl = headElement.element("ZAGLYWFLDM");
            Element ywbh = headElement.element("YWXTBH");
            Element qyfw = headElement.element("YWXTQYFW");
            Element fssj = headElement.element("FSSJ");
            Element code = headElement.element("CODE");
            Element msg = headElement.element("MSG");
            bbh.setText(requestXml.getPacskageHead().getBbh());
            fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
            fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
            jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
            jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
            ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
            ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
            qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
            fssj.setText(requestXml.getPacskageHead().getFssj());
            code.setText("10");
            msg.setText("成功");
        }
        //遍历DATA中的子节点
        for(Iterator<Element> iterator = rootElement.elementIterator("DATA"); iterator.hasNext();){
            Element dataElement = iterator.next();
            Element ywxtbh = dataElement.element("YWXTBH");
            Element ywlsh = dataElement.element("YWLSH");
            Element qyrGmsfhm = dataElement.element("SQR_GMSFHM");
            Element qyrXm = dataElement.element("SQR_XM");
            Element jyqk = dataElement.element("BYQC_JYQK");
            /*Element qyfw = dataElement.element("XTYW_QYFWDM");*/
            Element bldwGajgjgdm = dataElement.element("BLDW_GAJGJGDM");
            Element bldwgajgmc = dataElement.element("BLDW_GAJGMC");
            Element bldwLxdh = dataElement.element("BLDW_LXDH");
            Element blrGmsfhm = dataElement.element("BLR_GMSFHM");
            Element blrXm = dataElement.element("BLR_XM");
            /*Element blrLxdh = dataElement.element("BLR_LXDH");*/
            Element blsj = dataElement.element("BLSJ");
            Element sjgsdwdm = dataElement.element("SJGSDWDM");
            Element sjgsdwmc = dataElement.element("SJGSDWMC");
            Element fsdwSjgsdwdm = dataElement.element("FSDW_SJGSDWDM");
            Element fsdwSjgsdwmc = dataElement.element("FSDW_SJGSDWMC");
            Element jsdwSjgsdwdm = dataElement.element("JSDW_SJGSDWDM");
            Element jsdwSjgsdwmc = dataElement.element("JSDW_SJGSDWMC");
            List<RECORD> recordList = requestXml.getRecordList();
            for(RECORD record : recordList){
                if(record.getSid().equals("011005")){
                    ywxtbh.setText(record.getywxtbh());
                    ywlsh.setText(record.getYwlsh());
                    qyrGmsfhm.setText(record.getSqrGmsfhm());
                    qyrXm.setText(record.getSqrXm());
                    jyqk.setText(record.getByqcJyqk());
                    /*qyfw.setText(record.getXtywQyfwdm());*/
                    bldwGajgjgdm.setText(record.getBldwGajgjgdm());
                    bldwgajgmc.setText(record.getBldwGajamc());
                    bldwLxdh.setText(record.getBldwLxdh());
                    blrGmsfhm.setText(record.getBlrGmsfhm());
                    blrXm.setText(record.getBlrXm());
                    /*blrLxdh.setText(record.getBlrLxdh());*/
                    blsj.setText(record.getBlsj());
                    sjgsdwdm.setText(record.getSjgsdwdm());
                    sjgsdwmc.setText(record.getSjgsdwmc());
                    fsdwSjgsdwdm.setText(record.getFsdwSjgsdwdm());
                    fsdwSjgsdwmc.setText(record.getFsdwSjgsdwmc());
                    jsdwSjgsdwdm.setText(record.getJsdwSjgsdwdm());
                    jsdwSjgsdwmc.setText(record.getJsdwSjgsdwmc());
                }
            }
        }
        return document.asXML();
    }

    /**
     * 处理响应迁出反馈信息的方法
     *
     * @author <致道科技>YunTong
     * @param information 业务请求参数
     * @return 响应申请迁出成功反馈XML
     * <p>Created on 2021-6-22<p>
     * <p>Updated on 2021-8-2<p>
     */
    private static String response_qcfk_Handler(String information){
        PACKAGE requestXml = (PACKAGE) JAXBUtils.xmlStrToObject(PACKAGE.class,information);
        Document document = loadXml("./kstb_configuration/res_qcfk_info.xml");
        Element rootElement = document.getRootElement();
        for(Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD");iterator.hasNext();){
            Element headElement = iterator.next();
            Element bbh = headElement.element("BBH");
            Element fsd = headElement.element("FSDW_SJGSDWDM");
            Element fsm = headElement.element("FSDW_SJGSDWMC");
            Element jsd = headElement.element("JSDW_SJGSDWDM");
            Element jsm = headElement.element("JSDW_SJGSDWMC");
            Element ywfl = headElement.element("ZAGLYWFLDM");
            Element ywbh = headElement.element("YWXTBH");
            Element qyfw = headElement.element("YWXTQYFW");
            Element fssj = headElement.element("FSSJ");
            Element code = headElement.element("CODE");
            Element msg = headElement.element("MSG");
            bbh.setText(requestXml.getPacskageHead().getBbh());
            fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
            fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
            jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
            jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
            ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
            ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
            qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
            fssj.setText(requestXml.getPacskageHead().getFssj());
            code.setText("10");
            msg.setText("成功");
        }
        for(Iterator<Element> iterator = rootElement.elementIterator("DATA");iterator.hasNext();){
            Element dataElement = iterator.next();
            //获取DATA节点中的所有标签对象
            Element ywxtbh = dataElement.element("YWXTBH");
            /*Element ywlsh = dataElement.element("YWLSH");*/
            Element qyrGmsfhm = dataElement.element("QYR_GMSFHM");
            Element qyrXm = dataElement.element("QYR_XM");
            Element qyrXbdm = dataElement.element("QYR_XBDM");
            Element qyrMzdm = dataElement.element("QYR_MZDM");
            Element qyrCsrq = dataElement.element("QYR_CSRQ");
            Element qyrCym = dataElement.element("QYR_CYM");
			/*Element qyrYczrgxJtgxdm = dataElement.element("QYR_YCZRGX_JTGXDM");
			Element qyrXldm = dataElement.element("QYR_XLDM");
			Element qyrHyzk = dataElement.element("QYR_HYZKDM");
			Element qyrZy = dataElement.element("QYR_ZY");
			Element qyrCsdGjhdqdm = dataElement.element("QYR_CSD_GJHDQDM");
			Element qyrCsdSsqxdm = dataElement.element("QYR_CSD_SSXQDM");
			Element qyrCsdQhnxxdz = dataElement.element("QYR_CSD_QHNXXDZ");
			Element qyrJgGjhdqdm = dataElement.element("QYR_JG_GJHDQDM");
			Element qyrJgSsqxdm = dataElement.element("QYR_JG_SSXQDM");
			Element qyrJgqhnxxdz = dataElement.element("QYR_JG_QHNXXDZ");*/
            Element qyldyydm = dataElement.element("QYLDYYDM");
            /*Element qyzbh = dataElement.element("QYZBH");*/
            Element czrGmsfhm = dataElement.element("CZR_GMSFHM");
            Element czrXm = dataElement.element("CZR_XM");
            Element yzzSsxqdm = dataElement.element("YZZ_SSXQDM");
            Element yzzQhnxxdz = dataElement.element("YZZ_QHNXXDZ");
            Element yzzCxfldm = dataElement.element("YZZ_CXFLDM");
            Element qwdSsxqdm = dataElement.element("QWD_SSXQDM");
            Element qwdQhnxxdz = dataElement.element("QWD_QHNXXDZ");
			/*Element qfjgGajgdm = dataElement.element("QFJG_GAJGJGDM");
			Element qfjgGajgmc = dataElement.element("QFJG_GAJGMC");
			Element qfrq = dataElement.element("QFRQ");
			Element yxjzrq = dataElement.element("YXQJZRQ");
			Element cbrXm = dataElement.element("CBR_XM");
			Element bz = dataElement.element("BZ");
			Element zqbh = dataElement.element("ZQBH");
			Element xtywQyfwdm = dataElement.element("XTYW_QYFWDM");*/
            Element sldwGajgjgdm = dataElement.element("SLDW_GAJGJGDM");
            Element sldwGajgmc = dataElement.element("SLDW_GAJGMC");
            Element sldwLxdh = dataElement.element("SLDW_LXDH");
            Element slrGmsfhm = dataElement.element("SLR_GMSFHM");
            Element slrXm = dataElement.element("SLR_XM");
            /*Element slrLxdh = dataElement.element("SLR_LXDH");*/
            Element slsj = dataElement.element("SLSJ");
            Element sjgsdwdm = dataElement.element("SJGSDWDM");
            Element sjgsdwmc = dataElement.element("SJGSDWMC");
            Element fsdwSjgsdwdm = dataElement.element("FSDW_SJGSDWDM");
            Element fsdwSjgsdwmc = dataElement.element("FSDW_SJGSDWMC");
            Element jsdwSjgsdwdm = dataElement.element("JSDW_SJGSDWDM");
            Element jsdwSjgsdwmc = dataElement.element("JSDW_SJGSDWMC");
            List<RECORD> recordList = requestXml.getRecordList();
            //System.err.println("recordList:"+recordList);
            for(RECORD record : recordList){
                if(record.getSid().equals("011006")){
                    ywxtbh.setText(record.getywxtbh());
                    /*ywlsh.setText(record.getYwlsh());*/
                    qyrGmsfhm.setText(record.getQyrGmsfhm());
                    qyrXm.setText(record.getQyrXm());
                    qyrXbdm.setText(record.getQyrXbDm());
                    qyrMzdm.setText(record.getQyrMzDm());
                    qyrCsrq.setText(record.getQyrCsrq());
                    qyrCym.setText(record.getQyrCym());
				/*	qyrYczrgxJtgxdm.setText(record.getQyrYczrgxJtgxdm());
					qyrXldm.setText(record.getQyrXldm());
					qyrHyzk.setText(record.getQyrHyzkdm());
					qyrZy.setText(record.getQyrZy());
					qyrCsdGjhdqdm.setText(record.getQyrCsdGjhdqdm());
					qyrCsdSsqxdm.setText(record.getQyrCsdSsxqdm());
					qyrCsdQhnxxdz.setText(record.getQyrCsdQhnxxdz());
					qyrJgGjhdqdm.setText(record.getQyrJgGjhdqdm());
					qyrJgSsqxdm.setText(record.getQyrJgSsxqdm());
					qyrJgqhnxxdz.setText(record.getQyrJgQhnxxdz());*/
                    qyldyydm.setText(record.getQyldyydm());
                    /*qyzbh.setText(record.getQyzBh());*/
                    czrGmsfhm.setText(record.getCzrGmsfhm());
                    czrXm.setText(record.getCzrXm());
                    yzzSsxqdm.setText(record.getYzzSsxqdm());
                    yzzQhnxxdz.setText(record.getYzzQhnxxdz());
                    yzzCxfldm.setText(record.getYzzCxfldm());
                    qwdSsxqdm.setText(record.getQwdSsxqdm());
                    qwdQhnxxdz.setText(record.getQwdQhnxxdz());
					/*qfjgGajgdm.setText(record.getQfjgGajgjgdm());
					qfjgGajgmc.setText(record.getQfjgGajgmc());
					qfrq.setText(record.getQfRq());
					yxjzrq.setText(record.getYxjzrq());
					cbrXm.setText(record.getCbrXm());
					bz.setText(record.getBz());
					zqbh.setText(record.getZqbh());
					xtywQyfwdm.setText(record.getXtywQyfwdm());*/
                    sldwGajgjgdm.setText(record.getSldwGajgjgdm());
                    sldwGajgmc.setText(record.getSldwGajgmc());
                    sldwLxdh.setText(record.getSldwLxdh());
                    slrGmsfhm.setText(record.getSlrGmsfhm());
                    slrXm.setText(record.getSlrXm());
                    /*slrLxdh.setText(record.getSlrLxdh());*/
                    slsj.setText(record.getSlsj());
                    sjgsdwdm.setText(record.getSjgsdwdm());
                    sjgsdwmc.setText(record.getSjgsdwmc());
                    fsdwSjgsdwdm.setText(record.getFsdwSjgsdwdm());
                    fsdwSjgsdwmc.setText(record.getFsdwSjgsdwmc());
                    jsdwSjgsdwdm.setText(record.getJsdwSjgsdwdm());
                    jsdwSjgsdwmc.setText(record.getJsdwSjgsdwmc());
                }
            }
        }
        return document.asXML();
    }

    /**
     * 接收申请出生入户数据成功返回
     *
     * @author <致道科技>Cui
     * @return 成功返回的xml
     * <p>Created on 2021-7-29<p>
     */
    private static String success(String information){
        PACKAGEC requestXml = (PACKAGEC) JAXBUtils.xmlStrToObject(PACKAGEC.class,information);
        //读取XML
        Document document = loadXml("./kstb_configuration/res_xsrh_success.xml");
        //获取根节点
        Element rootElement = document.getRootElement();
        //遍历PACKAGEHEAD中的子节点
        for(Iterator<Element> iterator = rootElement.elementIterator("PACKAGEHEAD");iterator.hasNext();){
            Element headElement = iterator.next();
            Element bbh = headElement.element("BBH");
            Element fsd = headElement.element("FSDW_SJGSDWDM");
            Element fsm = headElement.element("FSDW_SJGSDWMC");
            Element jsd = headElement.element("JSDW_SJGSDWDM");
            Element jsm = headElement.element("JSDW_SJGSDWMC");
            Element ywfl = headElement.element("HZGLYWXTFLYDM");
            Element ywbh = headElement.element("YWXTBH");
            Element qyfw = headElement.element("XTYWQYFW");
            Element fssj = headElement.element("FSSJ");
            Element code = headElement.element("CODE");
            Element msg = headElement.element("MSG");
            bbh.setText(requestXml.getPacskageHead().getBbh());
            fsd.setText(requestXml.getPacskageHead().getFsdwSjgsdwdm());
            fsm.setText(requestXml.getPacskageHead().getFsdwSjgsdwmc());
            jsd.setText(requestXml.getPacskageHead().getJsdwSjgsdwdm());
            jsm.setText(requestXml.getPacskageHead().getJsdwSjgsdwmc());
            ywfl.setText(requestXml.getPacskageHead().getZaglywfldm());
            ywbh.setText(requestXml.getPacskageHead().getYwxtbh());
            qyfw.setText(requestXml.getPacskageHead().getYwxtqyfw());
            fssj.setText(requestXml.getPacskageHead().getFssj());
            code.setText("10");
            msg.setText("成功");
        }


        return document.asXML();
    }

    //申请审核成功返回
    public String sqshSuc(SqshPackageHead pgh, SqshRecord r) {
        XMLUtil xmlUtil = new XMLUtil();
        SqshPackageSuc p = new SqshPackageSuc();
        r.setNo(null);
        r.setSid(null);
        p.setSqshPackageHead(pgh);
        p.setData(r);
        return xmlUtil.convertObjToXml(p);
    }

    //审核签发成功返回
    private String shqfSuc(ShqfPackageHead pkh, ShqfRecord dataSuc) {
        XMLUtil xmlUtil = new XMLUtil();
        ShqfPackageSuc sps = new ShqfPackageSuc();
        pkh.setCODE("10");
        pkh.setMSG("接口调用成功");
        dataSuc.setNo(null);
        dataSuc.setSid(null);
        sps.setShqfPackageHead(pkh);
        sps.setShqfRecord(dataSuc);
        return xmlUtil.convertObjToXml(sps);
    }

    // 证件制作反馈成功返回
    private String zjzzSuc(ZjzzPackageHead pkh, ZjzzRecord dataSuc) {
        XMLUtil xmlUtil = new XMLUtil();
        pkh.setCODE("10");
        pkh.setMSG("接口调用成功");
        dataSuc.setNo(null);
        dataSuc.setSid(null);
        dataSuc.setID(null);
        ZjzzPackageSuc zps = new ZjzzPackageSuc();
        zps.setZjzzPackageHead(pkh);
        zps.setData(dataSuc);
        return xmlUtil.convertObjToXml(zps);
    }

    //申请审核失败返回
    public String sqshErr(SqshPackageHead pgh, SqshRecord sqshRecord) {
        XMLUtil xmlUtil = new XMLUtil();
        SqshPackageErr pgr = new SqshPackageErr();
        pgr.setSqshPackageHead(pgh);
        List<SqshRecordErr> list = new ArrayList<>();
        SqshRecordErr sqshRecordErr = new SqshRecordErr();
        sqshRecordErr.setNo("1");
        sqshRecordErr.setSid(sqshRecord.getSid());
        sqshRecordErr.setCode("-72");
        sqshRecordErr.setMsg("数据项非法");
        list.add(sqshRecordErr);
        pgr.setSqshRecordErrs(list);
        return xmlUtil.convertObjToXml(pgr);
    }

    //申请审核失败返回
    public String sqshErr(SqshPackageHead pgh, List<SqshRecord> sqshRecords) {
        XMLUtil xmlUtil = new XMLUtil();
        SqshPackageErr pgr = new SqshPackageErr();
        pgr.setSqshPackageHead(pgh);
        List<SqshRecordErr> list = new ArrayList<>();
        SqshRecordErr sqshRecordErr = new SqshRecordErr();
        for (int i = 0; i < sqshRecords.size(); i++) {
            sqshRecordErr.setSid(sqshRecords.get(i).getSid());
            sqshRecordErr.setCode("-72");
            sqshRecordErr.setMsg("数据项非法");
            sqshRecordErr.setNo(String.valueOf(i));
            list.add(sqshRecordErr);
        }
        pgr.setSqshRecordErrs(list);
        return xmlUtil.convertObjToXml(pgr);
    }

    //申请审核签发日志
    public void sqshLogOut(SqshPackage pk, Log_jkfwWithBLOBs logIn, String info) {
        logIn.setYWLX(pk.getSqshPackageHead().getHZGLYWXTFLDM());
        logIn.setJKMC("ZAGL_JMSFZSQSHQFJSFW");
        try {
            String dd = logDao.selectDate();
            logIn.setFSSJ(dd);
        } catch (Exception ex) {
            logIn.setFSSJ("");
        }
        logIn.setJSNR(info);
        try {
            logIn.setFSIP(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException u) {
            logIn.setFSIP("");
        }
        logIn.setJKURL("http://" + logIn.getFSIP() + ":8088/ZAGL/KSTBService?wsdl");
        logDao.insertSelective(logIn);
    }

    //审核签发反馈日志
    private void shqfLogOut(ShqfPackage pk, Log_jkfwWithBLOBs logIn, String info) {
        logIn.setYWLX(pk.getShqfPackageHead().getHZGLYWFLDM());
        logIn.setJKMC("ZAGL_JMSFZSHQFFKJSFW");
        try {
            String dd = logDao.selectDate();
            logIn.setFSSJ(dd);
        } catch (Exception ex) {
            logIn.setFSSJ("");
        }
        logIn.setJSNR(info);
        try {
            logIn.setFSIP(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException u) {
            logIn.setFSIP("");
        }
        logIn.setJKURL("http://" + logIn.getFSIP() + ":8088/ZAGL/KSTBService?wsdl");
        logDao.insertSelective(logIn);
    }

    //证件制作反馈日志
    private void zjzzLogOut(ZjzzPackage pk, Log_jkfwWithBLOBs logIn, String info) {
        logIn.setYWLX(pk.getZjzzPackageHead().getHZGLYWXTFLDM());
        logIn.setJKMC("ZAGL_ZJZZFKJSFW");
        try {
            String dd = logDao.selectDate();
            logIn.setFSSJ(dd);
        } catch (Exception ex) {
            logIn.setFSSJ("");
        }
        logIn.setJSNR(info);
        try {
            logIn.setFSIP(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException u) {
            logIn.setFSIP("");
        }
        logIn.setJKURL("http://" + logIn.getFSIP() + ":8088/ZAGL/KSTBService?wsdl");
        logDao.insertSelective(logIn);
    }

    //xml错误日志
    public void LogOut(String info, Log_jkfwWithBLOBs logIn, String fName) {
        logIn.setJKMC(fName);
        try {
            String dd = logDao.selectDate();
            logIn.setFSSJ(dd);
        } catch (Exception ex) {
            logIn.setFSSJ("");
        }
        logIn.setJSNR(info);
        try {
            logIn.setFSIP(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException u) {
            logIn.setFSIP("");
        }
        logIn.setJKURL("http://" + logIn.getFSIP() + ":8088/ZAGL/KSTBService?wsdl");
        logDao.insertSelective(logIn);
    }
}
