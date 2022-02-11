package com.zhidao.kstb.zjzzfkjs.model.req;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;

@Data
public class ZjzzRecord {
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

    private String JMSFZZZXXCWLBDM;

    private String CWMS_JYQK;

    private String JYDW_GAJGJGDM;

    private String JYDW_GAJGMC;

    private String JYR_XM;

    private String JYRQ;

    private String ZLHKCLQK_JYQK;

    private String CLDW_GAJGJGDM;

    private String CLDW_GAJGMC;

    private String CLR_XM;

    private String CLRQ;

    private String XTYW_QYFWDM;

    private String FSDW_SJGSDWDM;

    private String FSDW_SJGSDWMC;

    private String JSDW_SJGSDWDM;

    private String JSDW_SJGSDWMC;

    private String FFRQ01;

    private String JMSFZCSHGLH;

    private byte[] JDXP;

    public void setYWXTBH(String YWXTBH) {
        this.YWXTBH = YWXTBH == null ? "" : YWXTBH.trim();
    }

    public void setJMSFZSLH(String JMSFZSLH) {
        this.JMSFZSLH = JMSFZSLH == null ? "" : JMSFZSLH.trim();
    }

    public void setGMSFHM(String GMSFHM) {
        this.GMSFHM = GMSFHM == null ? "" : GMSFHM.trim();
    }

    public void setXM(String XM) {
        this.XM = XM == null ? "" : XM.trim();
    }

    public void setJMSFZZZXXCWLBDM(String JMSFZZZXXCWLBDM) {
        this.JMSFZZZXXCWLBDM = JMSFZZZXXCWLBDM == null ? "" : JMSFZZZXXCWLBDM.trim();
    }

    public void setCWMS_JYQK(String CWMS_JYQK) {
        this.CWMS_JYQK = CWMS_JYQK == null ? "" : CWMS_JYQK.trim();
    }

    public void setJYDW_GAJGJGDM(String JYDW_GAJGJGDM) {
        this.JYDW_GAJGJGDM = JYDW_GAJGJGDM == null ? "" : JYDW_GAJGJGDM.trim();
    }

    public void setJYDW_GAJGMC(String JYDW_GAJGMC) {
        this.JYDW_GAJGMC = JYDW_GAJGMC == null ? "" : JYDW_GAJGMC.trim();
    }

    public void setJYR_XM(String JYR_XM) {
        this.JYR_XM = JYR_XM == null ? "" : JYR_XM.trim();
    }

    public void setJYRQ(String JYRQ) {
        this.JYRQ = JYRQ == null ? "" : JYRQ.trim();
    }

    public void setZLHKCLQK_JYQK(String ZLHKCLQK_JYQK) {
        this.ZLHKCLQK_JYQK = ZLHKCLQK_JYQK == null ? "" : ZLHKCLQK_JYQK.trim();
    }

    public void setCLDW_GAJGJGDM(String CLDW_GAJGJGDM) {
        this.CLDW_GAJGJGDM = CLDW_GAJGJGDM == null ? "" : CLDW_GAJGJGDM.trim();
    }

    public void setCLDW_GAJGMC(String CLDW_GAJGMC) {
        this.CLDW_GAJGMC = CLDW_GAJGMC == null ? "" : CLDW_GAJGMC.trim();
    }

    public void setCLR_XM(String CLR_XM) {
        this.CLR_XM = CLR_XM == null ? "" : CLR_XM.trim();
    }

    public void setCLRQ(String CLRQ) {
        this.CLRQ = CLRQ == null ? "" : CLRQ.trim();
    }

    public void setXTYW_QYFWDM(String XTYW_QYFWDM) {
        this.XTYW_QYFWDM = XTYW_QYFWDM == null ? "" : XTYW_QYFWDM.trim();
    }

    public void setFSDW_SJGSDWDM(String FSDW_SJGSDWDM) {
        this.FSDW_SJGSDWDM = FSDW_SJGSDWDM == null ? "" : FSDW_SJGSDWDM.trim();
    }

    public void setFSDW_SJGSDWMC(String FSDW_SJGSDWMC) {
        this.FSDW_SJGSDWMC = FSDW_SJGSDWMC == null ? "" : FSDW_SJGSDWMC.trim();
    }

    public void setJSDW_SJGSDWDM(String JSDW_SJGSDWDM) {
        this.JSDW_SJGSDWDM = JSDW_SJGSDWDM == null ? "" : JSDW_SJGSDWDM.trim();
    }

    public void setJSDW_SJGSDWMC(String JSDW_SJGSDWMC) {
        this.JSDW_SJGSDWMC = JSDW_SJGSDWMC == null ? "" : JSDW_SJGSDWMC.trim();
    }

    public void setFFRQ01(String FFRQ01) {
        this.FFRQ01 = FFRQ01 == null ? "" : FFRQ01.trim();
    }

    public void setJMSFZCSHGLH(String JMSFZCSHGLH) {
        this.JMSFZCSHGLH = JMSFZCSHGLH == null ? "" : JMSFZCSHGLH.trim();
    }

    public void setJDXP(byte[] JDXP) {
        this.JDXP = JDXP;
    }
}
