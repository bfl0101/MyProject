package com.zhidao.kstb.model.tab;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 跨省通办申请迁出推送信息
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-16<p>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KSTB_SQQCXX_FS implements Serializable {
    @JsonProperty(value = "XLH")
    private String xlh;
    @JsonProperty(value = "YWXTBH")
    private String ywxtbh;
    @JsonProperty(value = "YWLSH")
    private String ywlsh;
    @JsonProperty(value = "SQR_GMSFHM")
    private String sqrGmsfhm;
    @JsonProperty(value = "SQR_XM")
    private String sqrXm;
    @JsonProperty(value = "SQR_LXDH")
    private String sqrLxdh;
    @JsonProperty(value = "SQR_ZZ_SSXQDM")
    private String sqrZzSsxqdm;
    @JsonProperty(value = "SQR_ZZ_QHNXXDZ")
    private String sqrZzQhnxxdz;
    @JsonProperty(value = "QYR_GMSFHM")
    private String qyrGmsfhm;
    @JsonProperty(value = "QYR_XM")
    private String qyrXm;
    @JsonProperty(value = "QYR_XBDM")
    private String qyrXbdm;
    @JsonProperty(value = "QYR_MZDM")
    private String qyrMzdm;
    @JsonProperty(value = "QYR_CSRQ")
    private String qyrCsrq;
    @JsonProperty(value = "QYR_YSQRGX_JTGXDM")
    private String qyrYsqrgxJtgxdm;
    @JsonProperty(value = "QYLDYYDM")
    private String qylbdm;
    @JsonProperty(value = "QCD_SSXQDM")
    private String qcdSsxqdm;
    @JsonProperty(value = "QCD_QHNXXDZ")
    private String qcdQhnxxdz;
    @JsonProperty(value = "QCD_HKDJJG_GAJGDM")
    private String qcdHkdjjgGajgjgdm;
    @JsonProperty(value = "QCD_HKDJJG_GAJGMC")
    private String qcdHkdjjggajgjgmc;
    @JsonProperty(value = "QRD_SSXQDM")
    private String qrdSsxqdm;
    @JsonProperty(value = "QRD_QHNXXDZ")
    private String qrdqhnxxdz;
    @JsonProperty(value = "QRD_HKDJJG_GAJGJGDM")
    private String qrdHkdjjgGajgjgdm;
    @JsonProperty(value = "QRD_HKDJJG_GAJGJGMC")
    private String qrdHkdjjgGajgjgmc;
    @JsonProperty(value = "ZQZBH")
    private String zqzbh;
    @JsonProperty(value = "QFJG_GAJGJGDM")
    private String qfjgGajgjgdm;
    @JsonProperty(value = "QFJG_GAJGMC")
    private String qfjgGajgmc;
    @JsonProperty(value = "CBR_XM")
    private String cbrXm;
    @JsonProperty(value = "QFRQ")
    private String qfrq;
    @JsonProperty(value = "BZ")
    private String bz;
    @JsonProperty(value = "YXJZRQ")
    private String yxjzrq;
    @JsonProperty(value = "XTYW_QYFWDM")
    private String xtywQyfwdm;
    @JsonProperty(value = "SLDW_GAJGJGDM")
    private String sldwGajgjgdm;
    @JsonProperty(value = "SLDW_GAJGMC")
    private String sldwGajgmc;
    @JsonProperty(value = "SLDW_LXDH")
    private String sldwLxdh;
    @JsonProperty(value = "SLR_GMSFHM")
    private String slrGmsfhm;
    @JsonProperty(value = "SLR_XM")
    private String slrXm;
    @JsonProperty(value = "SLR_LXDH")
    private String slrLxdh;
    @JsonProperty(value = "SLSJ")
    private String slsj;
    @JsonProperty(value = "SJGSDWDM")
    private String sjgsdwdm;
    @JsonProperty(value = "SJGSDWMC")
    private String sjgsdwmc;
    @JsonProperty(value = "FSDW_SJGSDWDM")
    private String fsdwSjgsdwdm;
    @JsonProperty(value = "FSDW_SJGSDWMC")
    private String fsdwSjgsdwmc;
    @JsonProperty(value = "JSDW_SJGSDWDM")
    private String jsdwSjgsdwdm;
    @JsonProperty(value = "JSDW_SJGSDWMC")
    private String jsdwSjgsdwmc;
    @JsonProperty(value = "CODE")
    private String code;
    @JsonProperty(value = "MSG")
    private String msg;
    @JsonProperty(value = "QCFKXLH")
    private String qcfkXlh;
    @JsonProperty(value = "QCFKSJ")
    private String qcfkSj;
    @JsonProperty(value = "BYQCBS")
    private String byqcBs;
    @JsonProperty(value = "QRSQSQH")
    private String qrsqSqh;
    @JsonProperty(value ="ZAGLYWFLDM")
    private String zaglywfldm;
    @JsonProperty(value = "BBH")
    private String bbh;
    @JsonProperty(value = "SID")
    private String sid;
    @JsonProperty(value = "NO")
    private String no;
    @JsonProperty(value = "URL")
    private String url;
    @JsonProperty(value = "KHDURL")
    private String khdurl;
    @JsonProperty(value = "FFNAME")
    private String ffname;
    @JsonProperty(value = "SQZCM")
    private String sqzcm;

}
