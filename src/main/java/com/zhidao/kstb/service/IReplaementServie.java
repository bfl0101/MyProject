package com.zhidao.kstb.service;

import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX_FS;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 补发信息业务层接口
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-24<p>
 */
@Service
public interface IReplaementServie {

    /**
     * 补发申请迁出信息
     * @author <致道科技>YunTong
     * @param information 业务信息
     * <p>Created on 2021-9-24<p>
     */
    void replacement_sqqc_information(List<KSTB_SQQCXX_FS> information);

    /**
     * 补发迁出反馈信息
     *
     * @author <致道科技>YunTong
     * @param information 补发业务信息
     * <p>Created on 2021-9-26<p>
     */
    void replacement_qcfk_information(List<KSTB_QCFKXX_FS> information);

    /**
     * 补发不予迁出反馈信息
     *
     * @author <致道科技>YunTong
     * @param information 补发业务信息
     * <p>Created on 2021-9-26<p>
     */
    void replacement_byqc_information(List<KSTB_BYQCFKXX_FS> information);
}
