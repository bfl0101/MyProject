package com.zhidao.kstb.shqffkjs.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Data
public class Rk_zpxx {
    private String XLH;

    private Date SLSJ;

    private String DQM;

    private String GMSFHM;

    private String SJGSDWDM;

    private Date GXSJ;

    private Date ZXSJ;

    private String PCSDM;

    private String RKXLH;

    private Short SYS_WRITE;

    private String DSXLH;

    private Short ZT;

    private String XPLX;

    private String RYID;

    private String XM;

    private String CJSJ;

    private String SSSSX;

    private String TXH;

    private String XPCD;

    private String SLH;

    private String SERIAL_NO;

    private String RKLB;

    private String XPSLH;

    private byte[] XP;

    public String getXLH() {
        return XLH;
    }

    public void setXLH(String XLH) {
        this.XLH = XLH;
    }

    public Date getSLSJ() {
        return SLSJ;
    }

    public void setSLSJ(Date SLSJ) {
        this.SLSJ = SLSJ;
    }

    public String getDQM() {
        return DQM;
    }

    public void setDQM(String DQM) {
        this.DQM = DQM == null ? null : DQM.trim();
    }

    public String getGMSFHM() {
        return GMSFHM;
    }

    public void setGMSFHM(String GMSFHM) {
        this.GMSFHM = GMSFHM == null ? null : GMSFHM.trim();
    }

    public String getSJGSDWDM() {
        return SJGSDWDM;
    }

    public void setSJGSDWDM(String SJGSDWDM) {
        this.SJGSDWDM = SJGSDWDM == null ? null : SJGSDWDM.trim();
    }

    public Date getGXSJ() {
        return GXSJ;
    }

    public void setGXSJ(Date GXSJ) {
        this.GXSJ = GXSJ;
    }

    public Date getZXSJ() {
        return ZXSJ;
    }

    public void setZXSJ(Date ZXSJ) {
        this.ZXSJ = ZXSJ;
    }

    public String getPCSDM() {
        return PCSDM;
    }

    public void setPCSDM(String PCSDM) {
        this.PCSDM = PCSDM == null ? null : PCSDM.trim();
    }

    public String getRKXLH() {
        return RKXLH;
    }

    public void setRKXLH(String RKXLH) {
        this.RKXLH = RKXLH;
    }

    public Short getSYS_WRITE() {
        return SYS_WRITE;
    }

    public void setSYS_WRITE(Short SYS_WRITE) {
        this.SYS_WRITE = SYS_WRITE;
    }

    public String getDSXLH() {
        return DSXLH;
    }

    public void setDSXLH(String DSXLH) {
        this.DSXLH = DSXLH == null ? null : DSXLH.trim();
    }

    public Short getZT() {
        return ZT;
    }

    public void setZT(Short ZT) {
        this.ZT = ZT;
    }

    public String getXPLX() {
        return XPLX;
    }

    public void setXPLX(String XPLX) {
        this.XPLX = XPLX == null ? null : XPLX.trim();
    }

    public String getRYID() {
        return RYID;
    }

    public void setRYID(String RYID) {
        this.RYID = RYID == null ? null : RYID.trim();
    }

    public String getXM() {
        return XM;
    }

    public void setXM(String XM) {
        this.XM = XM == null ? null : XM.trim();
    }

    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ == null ? null : CJSJ.trim();
    }

    public String getSSSSX() {
        return SSSSX;
    }

    public void setSSSSX(String SSSSX) {
        this.SSSSX = SSSSX == null ? null : SSSSX.trim();
    }

    public String getTXH() {
        return TXH;
    }

    public void setTXH(String TXH) {
        this.TXH = TXH == null ? null : TXH.trim();
    }

    public String getXPCD() {
        return XPCD;
    }

    public void setXPCD(String XPCD) {
        this.XPCD = XPCD;
    }

    public String getSLH() {
        return SLH;
    }

    public void setSLH(String SLH) {
        this.SLH = SLH == null ? null : SLH.trim();
    }

    public String getSERIAL_NO() {
        return SERIAL_NO;
    }

    public void setSERIAL_NO(String SERIAL_NO) {
        this.SERIAL_NO = SERIAL_NO == null ? null : SERIAL_NO.trim();
    }

    public String getRKLB() {
        return RKLB;
    }

    public void setRKLB(String RKLB) {
        this.RKLB = RKLB == null ? null : RKLB.trim();
    }

    public String getXPSLH() {
        return XPSLH;
    }

    public void setXPSLH(String XPSLH) {
        this.XPSLH = XPSLH == null ? null : XPSLH.trim();
    }

    public byte[] getXP() {
        return XP;
    }

    public void setXP(byte[] XP) {
        this.XP = XP;
    }
}