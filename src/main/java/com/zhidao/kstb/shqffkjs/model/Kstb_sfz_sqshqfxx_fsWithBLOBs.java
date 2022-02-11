package com.zhidao.kstb.shqffkjs.model;

import lombok.Data;

@Data
public class Kstb_sfz_sqshqfxx_fsWithBLOBs extends Kstb_sfz_sqshqfxx_fs {
    private String SDXP;

    private byte[] ZWY_ZWTXSJ;

    private byte[] ZWY_ZWTZSJ;

    private byte[] ZWE_ZWTXSJ;

    private byte[] ZWE_ZWTZSJ;

    public String getSDXP() {
        return SDXP;
    }

    public void setSDXP(String SDXP) {
        this.SDXP = SDXP == null ? null : SDXP.trim();
    }

    public byte[] getZWY_ZWTXSJ() {
        return ZWY_ZWTXSJ;
    }

    public void setZWY_ZWTXSJ(byte[] ZWY_ZWTXSJ) {
        this.ZWY_ZWTXSJ = ZWY_ZWTXSJ;
    }

    public byte[] getZWY_ZWTZSJ() {
        return ZWY_ZWTZSJ;
    }

    public void setZWY_ZWTZSJ(byte[] ZWY_ZWTZSJ) {
        this.ZWY_ZWTZSJ = ZWY_ZWTZSJ;
    }

    public byte[] getZWE_ZWTXSJ() {
        return ZWE_ZWTXSJ;
    }

    public void setZWE_ZWTXSJ(byte[] ZWE_ZWTXSJ) {
        this.ZWE_ZWTXSJ = ZWE_ZWTXSJ;
    }

    public byte[] getZWE_ZWTZSJ() {
        return ZWE_ZWTZSJ;
    }

    public void setZWE_ZWTZSJ(byte[] ZWE_ZWTZSJ) {
        this.ZWE_ZWTZSJ = ZWE_ZWTZSJ;
    }
}