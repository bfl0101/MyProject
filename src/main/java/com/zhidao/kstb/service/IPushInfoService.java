package com.zhidao.kstb.service;

import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX_FS;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推送信息业务层接口
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-16<p>
 */
@Service
public interface IPushInfoService {

    /**
     * 保存申请迁出推送信息
     *
     * @author <致道科技>YunTong
     * @param information 跨省通办_申请迁出推送信息
     * @return 操作成功返回true, 否则返回false
     * <p>Created on 2021-9-16<p>
     */
    void SaveMoveOutPushMessage(List<KSTB_SQQCXX_FS> information);

    /**
     * 保存迁出反馈推送信息
     *
     * @author <致道科技>YunTong
     * @param information 跨省通办_迁出反馈推送信息
     * <p>Created on 2021-9-24<p>
     */
    void SaveMoveOutFeedBack(List<KSTB_QCFKXX_FS> information);

    /**
     * 保存不予迁出反馈推送信息
     *
     * @author <致道科技>YunTong
     * @param information 跨省通办_不予迁出反馈推送信息
     * <p>Created on 2021-9-24<p>
     */
    void SaveUnMoveOutFeedBack(List<KSTB_BYQCFKXX_FS> information);
}
