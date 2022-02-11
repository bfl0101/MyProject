package com.zhidao.kstb.model.tab;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 跨省通办不予迁出反馈推送信息
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-24<p>
 * <p>Updated on 2021-10-14</>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KSTB_BYQCFKXX_FS {
    @JsonProperty(value = "YWXTBH")
    private String ywxtbh;
    @JsonProperty(value = "YWLSH")
    private String ywlsh;
    @JsonProperty(value = "SQR_GMSFHM")
    private String sqrGmsfhm;
    @JsonProperty(value = "SQR_XM")
    private String sqrXm;
    @JsonProperty(value = "BYQC_JYQK")
    private String byqcJyqk;
    @JsonProperty(value = "BLDW_GAJGJGDM")
    private String bldwGajgjgdm;
    @JsonProperty(value = "BLDW_GAJGMC")
    private String bldwGajgmc;
    @JsonProperty(value = "BLDW_LXDH")
    private String bldwLxdh;
    @JsonProperty(value = "BLR_GMSFHM")
    private String blrGmsfhm;
    @JsonProperty(value = "BLR_XM")
    private String blrXm;
    @JsonProperty(value = "BLR_LXDH")
    private String blrLxdh;
    @JsonProperty(value = "BLSJ")
    private String blsj;
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
    @JsonProperty(value = "XTYW_QYFWDM")
    private String xtywQyfwdm;
    @JsonProperty(value = "RKXLH")
    private String rkxlh;
    @JsonProperty(value = "SQQCXLH")
    private String sqqcxlh;
    @JsonProperty(value = "SQH")
    private String sqh;
    @JsonProperty(value = "ZAGLYWFLDM")
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
    @JsonProperty(value = "XLH")
    private String xlh;
    @JsonProperty(value = "CODE")
    private String code;
    @JsonProperty(value = "MSG")
    private String msg;
}
