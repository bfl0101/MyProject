package com.zhidao.kstb.shqffkjs.model;

import lombok.Data;

@Data
public class Zwqd_zwsjxxWithBLOBs extends Zwqd_zwsjxx {
    private byte[] ZWY_TXSJ;

    private byte[] ZWY_TZSJ;

    private byte[] ZWE_TXSJ;

    private byte[] ZWE_TZSJ;

    private byte[] CJZP;

    private byte[] ZWY_TXSJ_WS;

    private byte[] ZWE_TXSJ_WS;

    public byte[] getZWY_TXSJ() {
        return ZWY_TXSJ;
    }

    public void setZWY_TXSJ(byte[] ZWY_TXSJ) {
        this.ZWY_TXSJ = ZWY_TXSJ;
    }

    public byte[] getZWY_TZSJ() {
        return ZWY_TZSJ;
    }

    public void setZWY_TZSJ(byte[] ZWY_TZSJ) {
        this.ZWY_TZSJ = ZWY_TZSJ;
    }

    public byte[] getZWE_TXSJ() {
        return ZWE_TXSJ;
    }

    public void setZWE_TXSJ(byte[] ZWE_TXSJ) {
        this.ZWE_TXSJ = ZWE_TXSJ;
    }

    public byte[] getZWE_TZSJ() {
        return ZWE_TZSJ;
    }

    public void setZWE_TZSJ(byte[] ZWE_TZSJ) {
        this.ZWE_TZSJ = ZWE_TZSJ;
    }

    public byte[] getCJZP() {
        return CJZP;
    }

    public void setCJZP(byte[] CJZP) {
        this.CJZP = CJZP;
    }

    public byte[] getZWY_TXSJ_WS() {
        return ZWY_TXSJ_WS;
    }

    public void setZWY_TXSJ_WS(byte[] ZWY_TXSJ_WS) {
        this.ZWY_TXSJ_WS = ZWY_TXSJ_WS;
    }

    public byte[] getZWE_TXSJ_WS() {
        return ZWE_TXSJ_WS;
    }

    public void setZWE_TXSJ_WS(byte[] ZWE_TXSJ_WS) {
        this.ZWE_TXSJ_WS = ZWE_TXSJ_WS;
    }
}