package com.zhidao.kstb.zjzzfkjs.model.req;

import lombok.Data;

import javax.xml.bind.annotation.XmlType;

@Data
@XmlType(propOrder = {
        "BBH",
        "FSDW_SJGSDWDM",
        "FSDW_SJGSDWMC",
        "JSDW_SJGSDWDM",
        "JSDW_SJGSDWMC",
        "HZGLYWXTFLDM",
        "YWXTBH",
        "XTYWQYFW",
        "FSSJ",
        "CODE",
        "MSG",
})
public class ZjzzPackageHead {
    private String BBH; //  版本号

    private String FSDW_SJGSDWDM; //  发送单位代码

    private String FSDW_SJGSDWMC; //  发送单位名称

    private String JSDW_SJGSDWDM; //  接收单位代码

    private String JSDW_SJGSDWMC; //  接收单位名称

    private String HZGLYWXTFLDM; //  户政管理业务分类

    private String YWXTBH; //

    private String XTYWQYFW; //  协同业务区域范围
    private String FSSJ; //  发送时间
    private String CODE;
    private String MSG;
}
