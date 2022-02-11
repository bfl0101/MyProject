package com.zhidao.kstb.shqffkjs.model.req;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "BBH",
        "FSDW_SJGSDWDM",
        "FSDW_SJGSDWMC",
        "JSDW_SJGSDWDM",
        "JSDW_SJGSDWMC",
        "HZGLYWFLDM",
        "YWXTBH",
        "XTYWQYFW",
        "FSSJ",
        "CODE",
        "MSG",
})
public class ShqfPackageHead {
    private String BBH; //  版本号

    private String FSDW_SJGSDWDM; //  发送单位代码

    private String FSDW_SJGSDWMC; //  发送单位名称

    private String JSDW_SJGSDWDM; //  接收单位代码

    private String JSDW_SJGSDWMC; //  接收单位名称

    private String HZGLYWFLDM; //  户政管理业务分类

    private String YWXTBH; //  业务协同编号

    private String XTYWQYFW; //  协同业务区域范围
    private String FSSJ; //  发送时间

    private String CODE;
    private String MSG;

}
