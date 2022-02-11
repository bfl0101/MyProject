package com.zhidao.kstb.model.elec;



import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 封装XML数据包中的RECORD
 *
 * @author <致道科技>Cui
 * @version 0.1.1
 * <p>Created on 2021-6-9<p>
 * <p>Updated on 2021-6-11<p>
 * <p>Updated on 2021-7-29<p>
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class RECORDC {
	@XmlAttribute(name = "no")
	private String no;
	@XmlAttribute(name = "sid")
	private String sid;
	@XmlAttribute(name = "code")
	private String code;
	@XmlAttribute(name = "msg")
	private String msg;


	//不予落户协同信息数据项
	@XmlElement(name = "YWXTBH")
	private String ywxtbh;

	@XmlElement(name = "XM")
	private String xm;

	@XmlElement(name = "SBR_GMSFHM")
	private String sbrGmsfhm;

	@XmlElement(name = "SBR_XM")
	private String sbrXm;

	@XmlElement(name = "KSTBSHJGDM")
	private String kstbshjgdm;

	@XmlElement(name = "BYLH_JYQK")
	private String bylhJyqk;

	@XmlElement(name = "BLDW_GAJGJGDM")
	private String bldwGajgjgdm;

	@XmlElement(name = "BLDW_GAJGMC")
	private String bldwGajgmc;

	@XmlElement(name = "BLDW_LXDH")
	private String bldwLxdh;

	@XmlElement(name = "BLR_XM")
	private String blrXm;

	@XmlElement(name = "BLSJ")
	private String blsj;

	@XmlElement(name = "XTYW_QYFWDM")
	private String xtywQyfwdm;

	@XmlElement(name = "FSDW_SJGSDWDM")
	private String fsdwSjgsdwdm;

	@XmlElement(name = "FSDW_SJGSDWMC")
	private String fsdwSjgsdwmc;

	@XmlElement(name = "JSDW_SJGSDWDM")
	private String jsdwSjgsdwdm;

	@XmlElement(name = "JSDW_SJGSDWMC")
	private String jsdwSjgsdwmc;

	@XmlElement(name = "BLR_GMSFHM")
	private String blrGmsfhm;

	@XmlElement(name = "BLR_LXDH")
	private String blrLxdh;



	//居民户口簿个人页信息数据项
	@XmlElement(name = "YHZGXDM")
	private String yhzgxdm;

	@XmlElement(name = "GMSFHM")
	private String gmsfhm;

	@XmlElement(name = "CYM")
	private String cym;

	@XmlElement(name = "XBDM")
	private String xbdm;

	@XmlElement(name = "MZ")
	private String mz;

	@XmlElement(name = "CSRQ")
	private String csrq;

	@XmlElement(name = "CSD_SSXQDM")
	private String csdSsxqdm;

	@XmlElement(name = "CSD_SSXQMC")
	private String csdSsxqmc;

	@XmlElement(name = "JG_SSXQDM")
	private String jgSsxqdm;

	@XmlElement(name = "JG_SSXQMC")
	private String jgSsxqmc;

	@XmlElement(name = "BSXQTZZ")
	private String bsxqtzz;

	@XmlElement(name = "XL")
	private String xl;

	@XmlElement(name = "HYZK")
	private String hyzk;

	@XmlElement(name = "ZY")
	private String zy;

	@XmlElement(name = "ZJXY")
	private String zjxy;

	@XmlElement(name = "BYZK")
	private String byzk;

	@XmlElement(name = "SG")
	private Short sg;

	@XmlElement(name = "XXDM")
	private String xxdm;

	@XmlElement(name = "FWCS")
	private String fwcs;

	@XmlElement(name = "HSYHDQLBSX")
	private String hsyhdqlbsx;

	@XmlElement(name = "HSYHDQLBZ")
	private String hsyhdqlbz;


	//申请出生入户协同信息
	@XmlElement(name = "X")
	private String x;

	@XmlElement(name = "M")
	private String m;

	@XmlElement(name = "MZDM")
	private String mzdm;

	@XmlElement(name = "CSSJ")
	private String cssj;

	@XmlElement(name = "CSD_GJHDQDM")
	private String csdGjhdqdm;

	@XmlElement(name = "CSD_QHNXXDZ")
	private String csdQhnxxdz;

	@XmlElement(name = "JG_GJHDQDM")
	private String jgGjhdqdm;

	@XmlElement(name = "JG_QHNXXDZ")
	private String jgQhnxxdz;

	@XmlElement(name = "HZ_XM")
	private String hzXm;

	@XmlElement(name = "HZ_GMSFHM")
	private String hzGmsfhm;

	@XmlElement(name = "JHRY_GMSFHM")
	private String jhryGmsfhm;

	@XmlElement(name = "JHRY_XM")
	private String jhryXm;

	@XmlElement(name = "JHRY_CYZJDM")
	private String jhryCyzjdm;

	@XmlElement(name = "JHRY_ZJHM")
	private String jhryZjhm;

	@XmlElement(name = "JHRY_WWX")
	private String jhryWwx;

	@XmlElement(name = "JHRY_WWM")
	private String jhryWwm;

	@XmlElement(name = "JHRY_JHGXDM")
	private String jhryJhgxdm;

	@XmlElement(name = "JHRY_LXDH")
	private String jhryLxdh;

	@XmlElement(name = "JHRE_GMSFHM")
	private String jhreGmsfhm;

	@XmlElement(name = "JHRE_XM")
	private String jhreXm;

	@XmlElement(name = "JHRE_CYZJDM")
	private String jhreCyzjdm;

	@XmlElement(name = "JHRE_ZJHM")
	private String jhreZjhm;

	@XmlElement(name = "JHRE_WWX")
	private String jhreWwx;

	@XmlElement(name = "JHRE_WWM")
	private String jhreWwm;

	@XmlElement(name = "JHRE_JHGXDM")
	private String jhreJhgxdm;

	@XmlElement(name = "JHRE_LXDH")
	private String jhreLxdh;

	@XmlElement(name = "FQ_GMSFHM")
	private String fqGmsfhm;

	@XmlElement(name = "FQ_XM")
	private String fqXm;

	@XmlElement(name = "FQ_CYZJDM")
	private String fqCyzjdm;

	@XmlElement(name = "FQ_ZJHM")
	private String fqZjhm;

	@XmlElement(name = "MQ_GMSFHM")
	private String mqGmsfhm;

	@XmlElement(name = "MQ_XM")
	private String mqXm;

	@XmlElement(name = "MQ_CYZJDM")
	private String mqCyzjdm;

	@XmlElement(name = "MQ_ZJHM")
	private String mqZjhm;

	@XmlElement(name = "CSDJLBDM")
	private String csdjlbdm;

	@XmlElement(name = "CSZMBH")
	private String cszmbh;

	@XmlElement(name = "SBR_LXDH")
	private String sbrLxdh;

	@XmlElement(name = "SBR_YCSRGX_JTGXDM")
	private String sbrYcsrgxJtgxdm;

	@XmlElement(name = "XZZ_SSXQDM")
	private String xzzSsxqdm;

	@XmlElement(name = "XZZ_QHNXXDZ")
	private String xzzQhnxxdz;

	@XmlElement(name = "SLDW_GAJGJGDM")
	private String sldwGajgjgdm;

	@XmlElement(name = "SLDW_GAJGMC")
	private String sldwGajgmc;

	@XmlElement(name = "SLDW_LXDH")
	private String sldwLxdh;

	@XmlElement(name = "SLR_XM")
	private String slrXm;

	@XmlElement(name = "SLSJ")
	private String slsj;

	@XmlElement(name = "SLR_GMSFHM")
	private String slrGmsfhm;

	@XmlElement(name = "SLR_LXDH")
	private String slrLxdh;



	//跨省通办_材料信息
	@XmlElement(name = "SXH")
	private Short sxh;

	@XmlElement(name = "CLLBDM")
	private String cllbdm;

	@XmlElement(name = "CLMC")
	private String clmc;

	@XmlElement(name = "CLGS")
	private String clgs;

	@XmlElement(name = "CLNR")
	private String clnr;





}
