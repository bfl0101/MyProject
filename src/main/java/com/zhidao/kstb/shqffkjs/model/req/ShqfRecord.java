package com.zhidao.kstb.shqffkjs.model.req;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.xml.bind.annotation.XmlAttribute;

@Data
@Validated
public class ShqfRecord {

    private String no;  //接收协作数据中的数据序号

    private String sid; //接收协作数据的信息分类代码

    @XmlAttribute
    public String getNo() {
        return no;
    }

    @XmlAttribute
    public String getSid() {
        return sid;
    }

    private String ID;

    private String YWXTBH;

    private String JMSFZSLH;

    private String GMSFHM;

    private String XM;

    private String SHDW_GAJGJGDM;

    private String SHDW_GAJGJGMC;

    private String SHDW_LXDH;

    private String SHR_GMSFHM;

    private String SHR_XM;

    private String SHR_LXDH;

    private String JMSFZSHJGDM;

    private String SHJG_JYQK;

    private String SHRQ;

    private String QFJG_GAJGDM;

    private String QFJG_GAJGMC;

    private String QFJG_LXDH;

    private String QFR_XM;

    private String QFRQ;

    private String XMMZWZ;

    private String XBMZWZ;

    private String MZMZWZ;

    private String ZZHYMZWZ;

    private String ZZHEMZWZ;

    private String ZZHSMZWZ;

    private String QFJGMZWZ;

    private String XTYW_QYFWDM;

    private String FSDW_SJGSDWDM;

    private String FSDW_SJGSDWMC;

    private String JSDW_SJGSDWDM;

    private String JSDW_SJGSDWMC;
    private String DZMC;


}
