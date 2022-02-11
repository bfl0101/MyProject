package com.zhidao.kstb.log.model;

public class Log_jkfwWithBLOBs extends Log_jkfw {
    private String FSNR;

    private String JSNR;

    public String getFSNR() {
        return FSNR;
    }

    public void setFSNR(String FSNR) {
        this.FSNR = FSNR == null ? null : FSNR.trim();
    }

    public String getJSNR() {
        return JSNR;
    }

    public void setJSNR(String JSNR) {
        this.JSNR = JSNR == null ? null : JSNR.trim();
    }
}