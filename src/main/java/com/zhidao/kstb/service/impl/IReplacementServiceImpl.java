package com.zhidao.kstb.service.impl;

import com.zhidao.kstb.dao.ReplacementDao;
import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX_FS;
import com.zhidao.kstb.service.IReplaementServie;
import com.zhidao.kstb.service.ex.RecordNotExistException;
import com.zhidao.kstb.service.ex.UpdateInformationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 补发信息业务层接口实现类
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-26<p>
 */
@Service
public class IReplacementServiceImpl implements IReplaementServie {
    @Autowired
    private ReplacementDao replacementDao;
    private final static Logger logger = LoggerFactory.getLogger(IReplacementServiceImpl.class);

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void replacement_sqqc_information(List<KSTB_SQQCXX_FS> information) {
        logger.info("=====开始调用跨省通办_申请迁出补发服务=====");
        for(KSTB_SQQCXX_FS info : information){
            //获取需要校验的业务参数
            String ywxtbh = info.getYwxtbh();
            String qyrGmsfhm = info.getQyrGmsfhm();
            //判断目标记录是否存在
            Integer rows = replacementDao.existOfSuccess(ywxtbh,qyrGmsfhm);
            logger.info("该记录存在："+rows+"条");
            //不存在
            if(rows == 0){
                logger.error("该记录不存在!");
                throw new RecordNotExistException();
            }else{
                //存在，更新数据状态为'10'
                Integer row = replacementDao.changeSQQCInfoSuccess(ywxtbh,qyrGmsfhm);
                logger.info("申请迁出推送记录更新成功："+row+"条");
                if(row == 0){
                    logger.error("数据更新失败");
                    throw new UpdateInformationException();
                }

            }
        }
        logger.info("=====跨省通办_申请迁出补发服务调用结束=====");

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void replacement_qcfk_information(List<KSTB_QCFKXX_FS> information) {
        logger.info("=====开始调用跨省通办_迁出反馈补发服务=====");
        for(KSTB_QCFKXX_FS info : information){
            //判断标记不为'-10'的记录是否存在，如果存在表示确实有存在推送不成功的反馈信息
            Integer rows = replacementDao.existOfUnSuccessFromQCFKInfo(info.getYwxtbh());
            logger.info("该记录存在"+rows+"条");
            if(rows == 0){
                throw new RecordNotExistException();
            }else{
                //补发事件触发后，更新推送数据的状态
                Integer row = replacementDao.changeQCFKInfoSuccess(info.getYwxtbh());
                logger.info("迁出反馈推送记录更新成功"+row+"条");
                if(row == 0){
                    logger.error("数据更新失败");
                    throw new UpdateInformationException();
                }
            }
        }
        logger.info("=====跨省通办_迁出反馈补发服务调用完成=====");
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void replacement_byqc_information(List<KSTB_BYQCFKXX_FS> information) {
        logger.info("=====开始调用跨省通办_不予迁出补发服务=====");
        for(KSTB_BYQCFKXX_FS info : information){
            Integer rows = replacementDao.existOfUnSuccessFromBYQCFKInfo(info.getYwxtbh());
            logger.info("该记录存在"+rows+"条");
            if(rows == 0){
                throw new RecordNotExistException();
            }else{
                Integer row = replacementDao.changeBYQCFKInfoSuccess(info.getYwxtbh());
                logger.info("不予迁出反馈推送记录更新成功"+row+"条");
                if(row == 0){
                    logger.error("数据更新失败");
                    throw new UpdateInformationException();
                }
            }
        }
        logger.info("=====跨省通办_不予迁出补发服务调用结束=====");
    }
}
