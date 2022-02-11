package com.zhidao.kstb.service.impl;

import com.zhidao.kstb.dao.PushInfoDao;
import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX_FS;
import com.zhidao.kstb.service.IPushInfoService;
import com.zhidao.kstb.service.ex.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 推送信息业务层接口实现类
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-16<p>
 */
@Service
public class IPushInfoServiceImpl implements IPushInfoService {
    @Autowired
    PushInfoDao pushInfoDao;
    private final static Logger logger = LoggerFactory.getLogger(IPushInfoServiceImpl.class);

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void SaveMoveOutPushMessage(List<KSTB_SQQCXX_FS> information) {
        logger.info("=====开始调用跨省通办_申请迁出推送服务=====");
        for(KSTB_SQQCXX_FS info : information){
            //判断该记录是否存在
            Integer rows = pushInfoDao.existOfRecord(info.getYwxtbh(),info.getQyrGmsfhm());
            logger.info("该记录存在："+rows+"条");
            if(rows > 0){
                throw new ExistOfRecordException();
            }
            try {
                //添加申请迁出信息
                info.setXlh(UUID.randomUUID().toString().toUpperCase());
                Integer row = pushInfoDao.addMoveOutInformation(info);
                logger.info("迁出申请推送信息添加成功："+row+"条");
                /*更新常住人口迁入审批信息状态*/
                Integer r = pushInfoDao.changeApprovalInformation(info.getYwxtbh(),info.getQrsqSqh());
                if(r == 0){
                    logger.error("数据更新异常，迁移人姓名：'"+info.getQyrXm()+"' 公民身份号码：'"+info.getQyrGmsfhm()+"' 找不到该业务信息");
                    throw new UpdateInformationException();
                }
                logger.info("迁移人: '"+info.getQyrXm()+"' 迁入申请审批信息更新成功！");
            }catch (Exception e){
                logger.error("接口异常描述："+e.getMessage());
                throw new InsertException();
            }
        }
        logger.info("=====跨省通办_申请迁出推送服务调用完毕=====");
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void SaveMoveOutFeedBack(List<KSTB_QCFKXX_FS> information) {
        logger.info("=====开始调用跨省通办_迁出反馈推送服务=====");
        for(KSTB_QCFKXX_FS info : information){
            //判断该记录是否存在
            Integer rows = pushInfoDao.existOfFeedBack(info.getYwxtbh(),info.getQyrGmsfhm());
            logger.info("该记录存在："+rows+"条");
            if(rows > 0){
                throw new ExistOfRecordException();
            }
            info.setXlh(UUID.randomUUID().toString().toUpperCase());
            String sqqcXlh = pushInfoDao.getYwxtbhFromSqqcInfo(info.getYwxtbh());
            if(sqqcXlh == null){
                logger.error("该协同业务不存在！");
                throw new YwxtbhNotFoundExceptipon();
            }
            Integer qczxrkxlh = pushInfoDao.getPopulationInfo(info.getQyrGmsfhm());
            if(qczxrkxlh == null){
                logger.error("没有找到符合添加的记录");
                throw new NoResultsException();
            }
            try{
                info.setQczxrkxlh(qczxrkxlh.toString());
                info.setSqqcxlh(sqqcXlh);
                info.setXlh(UUID.randomUUID().toString().toUpperCase());
                pushInfoDao.addFeedBackInfo(info);
                logger.info("迁出反馈推送信息添加成功！");
//                Integer row = pushInfoDao.changeStatusIntoDestroy(info.getQyrGmsfhm());
//                if(row == 0){
//                    throw new UpdateInformationException();
//                }
//                logger.info("迁移人：'"+info.getQyrXm()+"' 公民身份号码：'"+info.getQyrGmsfhm()+"' 状态更新成功");
//                logger.info("迁移人：'"+info.getQyrXm()+"'状态更新成功："+row+"条记录");
            }catch (Exception e){
                logger.info("接口异常描述："+e.getMessage());
                throw new InsertException();
            }
        }
        logger.info("=====跨省通办_迁出反馈推送服务调用完毕=====");

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void SaveUnMoveOutFeedBack(List<KSTB_BYQCFKXX_FS> information) {
        logger.info("=====开始调用跨省通办_不予迁出反馈推送服务=====");
        for(KSTB_BYQCFKXX_FS info : information){
            Integer rows = pushInfoDao.existOfUnFeedBack(info.getYwxtbh(),info.getSqrGmsfhm());
            if(rows > 0){
                throw new ExistOfRecordException();
            }
            try{
                logger.info("该记录存在："+rows+"条");
                String sqqcXlh = pushInfoDao.getYwxtbhFromSqqcInfo(info.getYwxtbh());
                if(sqqcXlh == null){
                    logger.error("该协同业务不存在");
                    throw new YwxtbhNotFoundExceptipon();
                }
                info.setSqqcxlh(sqqcXlh);
                info.setXlh(UUID.randomUUID().toString().toUpperCase());
                pushInfoDao.addUnFeedBackInfo(info);
                logger.info(""+info.getSqrGmsfhm());
                logger.info("不予迁出推送信息添加成功！");
            }catch (Exception e){
                logger.error("接口异常描述："+e.getMessage());
                throw new InsertException();
            }
        }
        logger.info("=====跨省通办_不予迁出反馈推送服务调用完毕=====");
    }
}
