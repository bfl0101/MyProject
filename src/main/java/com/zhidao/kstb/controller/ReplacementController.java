package com.zhidao.kstb.controller;

import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX_FS;
import com.zhidao.kstb.service.IReplaementServie;
import com.zhidao.kstb.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 补发信息控制器层
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-24<p>
 */
@RestController
@CrossOrigin
@RequestMapping(value = "KSTB")
public class ReplacementController extends BaseController{
    @Autowired
    IReplaementServie iReplaementServie;

    /**
     * 补发申请迁出信息
     *
     * @author <致道科技>YunTong
     * @param information 补发业务信息
     * @return 业务响应状态描述以及状态码
     * <p>Created on 2021-9-24<p>
     */
    @PostMapping(value="ZAGL_SQQCBFFW")
    private JsonResult<Void> ZAGL_SQQCBFFW(@RequestBody List<KSTB_SQQCXX_FS> information){
        iReplaementServie.replacement_sqqc_information(information);
        return new JsonResult<>(OK,msg);
    }

    /**
     * 补发迁出反馈信息
     *
     * @author <致道科技>YunTong
     * @param information 补发业务信息
     * @return 业务响应状态描述以及状态码
     * <p>Created on 2021-9-26<p>
     */
    @PostMapping(value = "ZAGL_QCFKBFFW")
    private JsonResult<Void> ZAGL_QCFKBFFW(@RequestBody List<KSTB_QCFKXX_FS> information){
        iReplaementServie.replacement_qcfk_information(information);
        return new JsonResult<>(OK,msg);
    }

    /**
     * 补发不予迁出反馈信息
     *
     * @author <致道科技>YunTong
     * @param information 补发业务信息
     * @return 业务响应状态描述以及状态码
     * <p>Created on 2021-9-26<p>
     */
    @PostMapping(value = "ZAGL_BYQCBFFW")
    private JsonResult<Void> ZAGL_BYQCBFFW(@RequestBody List<KSTB_BYQCFKXX_FS> information){
        iReplaementServie.replacement_byqc_information(information);
        return new JsonResult<>(OK,msg);
    }

}
