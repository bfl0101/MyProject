package com.zhidao.kstb.controller;

import com.zhidao.kstb.model.tab.KSTB_BYQCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_QCFKXX_FS;
import com.zhidao.kstb.model.tab.KSTB_SQQCXX_FS;
import com.zhidao.kstb.service.IPushInfoService;
import com.zhidao.kstb.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推送信息控制器
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-16<p>
 */
@RestController
@CrossOrigin
@RequestMapping(value = "KSTB")
public class PushInfoController extends BaseController{
    @Autowired
    IPushInfoService iPushInfoService;

    /**
     * 推送申请迁出推送信息
     *
     * @author <致道科技>YunTong
     * @param information 跨省通办申请迁出推送信息
     * @return 响应状态码以及相关描述
     * <p>Created on 2021-9-16<p>
     */
    @PostMapping(value = "ZAGL_SQQCTSFW")
    private JsonResult<Void> ZAGL_SQQCTSFW(@RequestBody List<KSTB_SQQCXX_FS> information){
        System.err.println("information："+information);
        iPushInfoService.SaveMoveOutPushMessage(information);
        return new JsonResult<>(OK,msg);
    }

    /**
     * 推送迁出反馈信息
     *
     * @author <致道科技>YunTong
     * @param information 跨省通办迁出反馈推送信息
     * @return 响应状态码以及相关描述
     * <p>Created on 2021-9-26<p>
     */
    @PostMapping(value = "ZAGL_QCFKTSFW")
    private JsonResult<Void> ZAGL_QCFKTSFW(@RequestBody List<KSTB_QCFKXX_FS> information){
        iPushInfoService.SaveMoveOutFeedBack(information);
        return new JsonResult<>(OK,msg);
    }

    /**
     * 推送不予迁出反馈信息
     *
     * @author <致道科技>YunTong
     * @param information 跨省通办不予迁出反馈推送信息
     * @return 响应状态码以及相关描述
     * <p>Created on 2021-9-26<p>
     */
    @PostMapping(value = "ZAGL_BYQCTSFW")
    private JsonResult<Void> ZAGL_BYQCTSFW(@RequestBody List<KSTB_BYQCFKXX_FS> information){
        iPushInfoService.SaveUnMoveOutFeedBack(information);
        return new JsonResult<>(OK,msg);
    }
}
