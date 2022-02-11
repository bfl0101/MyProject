package com.zhidao.kstb.model.tabc;

public class KSTB_CLXXC {
    private String id;

    private String ywxtbh;

    private Short sxh;

    private String cllbdm;

    private String clmc;

    private String clgs;

    private String clnr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getYwxtbh() {
        return ywxtbh;
    }

    public void setYwxtbh(String ywxtbh) {
        this.ywxtbh = ywxtbh == null ? null : ywxtbh.trim();
    }

    public Short getSxh() {
        return sxh;
    }

    public void setSxh(Short sxh) {
        this.sxh = sxh;
    }

    public String getCllbdm() {
        return cllbdm;
    }

    public void setCllbdm(String cllbdm) {
        this.cllbdm = cllbdm == null ? null : cllbdm.trim();
    }

    public String getClmc() {
        return clmc;
    }

    public void setClmc(String clmc) {
        this.clmc = clmc == null ? null : clmc.trim();
    }

    public String getClgs() {
        return clgs;
    }

    public void setClgs(String clgs) {
        this.clgs = clgs == null ? null : clgs.trim();
    }

    public String getClnr() {
        return clnr;
    }

    public void setClnr(String clnr) {
        this.clnr = clnr == null ? null : clnr.trim();
    }
}