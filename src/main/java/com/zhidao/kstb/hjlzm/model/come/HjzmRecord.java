package com.zhidao.kstb.hjlzm.model.come;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class HjzmRecord {
    @XmlAttribute
    private String no;
    @XmlAttribute
    private String sid;
    @XmlElement(name = "YWXTBH")
    private String ywxtbh;

    //申请开具
    @XmlElement(name = "YWLSH")
    private String ywlsh;
    @XmlElement(name = "XM")
    private String xm;
    @XmlElement(name = "GMSFHM")
    private String gmsfhm;
    @XmlElement(name = "HJDZ_SSXQDM")
    private String hjdz_ssxqdm;
    @XmlElement(name = "HJDZ_QHNXXDZ")
    private String hjdz_qhnxxdz;
    @XmlElement(name = "LXDH")
    private String lxdh;
    @XmlElement(name = "JMSFZLZFSDM")
    private String jmsfzlzfsdm;
    @XmlElement(name = "SJR_XM")
    private String sjr_xm;
    @XmlElement(name = "SJR_LXDH")
    private String sjr_lxdh;
    @XmlElement(name = "SJR_YZBM")
    private String sjr_yzbm;
    @XmlElement(name = "SJR_TXDZ")
    private String sjr_txdz;
    @XmlElement(name = "HJZMLXDM")
    private String hjzmlxdm;
    @XmlElement(name = "ZMSXNR_JYQK")
    private String zmsxnr_jyqk;
    @XmlElement(name = "BZ")
    private String bz;
    @XmlElement(name = "SLDW_GAJGJGDM")
    private String sldw_gajgjgdm;
    @XmlElement(name = "SLDW_GAJGMC")
    private String sldw_gajgmc;
    @XmlElement(name = "SLDW_LXDH")
    private String sldw_lxdh;
    @XmlElement(name = "SLR_GMSFHM")
    private String slr_gmsfhm;
    @XmlElement(name = "SLR_XM")
    private String slr_xm;
    @XmlElement(name = "SLR_LXDH")
    private String slr_lxdh;
    @XmlElement(name = "SLSJ")
    private String slsj;
    @XmlElement(name = "XTYW_QYFWDM")
    private String xtyw_qyfwdm;
    @XmlElement(name = "FSDW_SJGSDWDM")
    private String fsdw_sjgsdwdm;
    @XmlElement(name = "FSDW_SJGSDWMC")
    private String fsdw_sjgsdwmc;
    @XmlElement(name = "JSDW_SJGSDWDM")
    private String jsdw_sjgsdwdm;
    @XmlElement(name = "JSDW_SJGSDWMC")
    private String jsdw_sjgsdwmc;
    //材料信息
    @XmlElement(name = "SXH")
    private String sxh;
    @XmlElement(name = "CLLBDM")
    private String cllbdm;
    @XmlElement(name = "CLMC")
    private String clmc;
    @XmlElement(name = "CLGS")
    private String clgs;
    @XmlElement(name = "CLNR")
    private String clnr;
    //不予出具
    @XmlElement(name = "BYCJHJZMYYDM")
    private String bycjhjzmyydm;
    @XmlElement(name = "BYCJHJZMYY_JYQK")
    private String bycjhjzmyy_jyqk;
    @XmlElement(name = "BLDW_GAJGJGDM")
    private String bldw_gajgjgdm;
    @XmlElement(name = "BLDW_GAJGMC")
    private String bldw_gajgmc;
    @XmlElement(name = "BLDW_LXDH")
    private String bldw_lxdh;
    @XmlElement(name = "BLR_GMSFHM")
    private String blr_gmsfhm;
    @XmlElement(name = "BLR_XM")
    private String blr_xm;
    @XmlElement(name = "BLR_LSDH")
    private String blr_lsdh;
    @XmlElement(name ="BLSJ")
    private String blsj;
    //出具


}
