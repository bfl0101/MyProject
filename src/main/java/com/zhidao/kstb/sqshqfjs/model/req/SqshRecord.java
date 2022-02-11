package com.zhidao.kstb.sqshqfjs.model.req;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;

@Data
public class SqshRecord {

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

    private String XBDM;

    private String MZDM;

    private String CSRQ;

    private String DZMC;

    private String HJDZ_SSXQDM;

    private String HJDZ_QHNXXDZ;

    private String ZJZDZ_SSXQDM;

    private String ZJZDZ_QHNXXDZ;

    private String JMSFZSLYYDM;

    private String JMSFZZZLXDM;

    private String JMSFZLZFSDM;

    private String LXDH;

    private String LSSFZSLBS;

    private String ZWCJJGDM;

    private String ZWY_ZWZCJGDM;

    private String ZWY_ZWDM;

    private String ZWY_ZWTXZLZ;

    private String ZWE_ZWZCJGDM;

    private String ZWE_ZWDM;

    private String ZWE_ZWTXZLZ;

    private String SZYCZKDM;

    private String ZWQDXTZCH;

    private String ZWCJQBSH;

    private String DSQR_XM;

    private String DSQR_GMSFHM;

    private String DSQR_YSQRGX_JTGXDM;

    private String XTYW_QYFWDM;

    private String SLDW_GAJGJGDM;

    private String SLDW_GAJGMC;

    private String SLDW_LXDH;

    private String SLR_GMSFHM;

    private String SLR_XM;

    private String SLR_LXDH;

    private String SLSJ;

    private String FSDW_SJGSDWDM;

    private String FSDW_SJGSDWMC;

    private String JSDW_SJGSDWDM;

    private String SDXP;

    private byte[] ZWY_ZWTXSJ;

    private byte[] ZWY_ZWTZSJ;

    private byte[] ZWE_ZWTXSJ;

    private byte[] ZWE_ZWTZSJ;

    private String JSDW_SJGSDWMC;

    private byte[] XP;                 // 相片

    private String RXXSD;              // 人像相似度

    private String SXH;//顺序号

    private String CLLBDM;//材料类别

    private String CLMC;//材料名称

    private String CLGS;//材料格式

    private String CLNR;//材料内容

    private String UUID;

    private byte[] CLNR_BLOB;//材料内容照片



}
