package com.zhidao.kstb.model.ele;



import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 封装XML数据包中的RECORD
 * 
 * @author <致道科技>YunTong
 * @version 0.1.1
 * <p>Created on 2021-6-9<p>
 * <p>Updated on 2021-6-11<p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RECORD {
	@XmlAttribute(name = "no")
	private String no;
	@XmlAttribute(name = "sid")
	private String sid;
	@XmlAttribute(name = "code")
	private String code;
	@XmlAttribute(name = "msg")
	private String msg;
	@XmlElement(name = "YWXTBH")
	private String ywxtbh;
	@XmlElement(name = "YWLSH")
	private String ywlsh;
	@XmlElement(name = "SQR_GMSFHM")//SQR_GMSFHM
	private String sqrGmsfhm;
	@XmlElement(name = "SQR_XM")
	private String sqrXm;
	@XmlElement(name = "SQR_LXDH")
	private String sqrLxdh;
	@XmlElement(name = "SQR_ZZ_SSXQDM")//SQR_ZZ_SSXQDM
	private String sqrZzSsxqdm;
	@XmlElement(name = "SQR_ZZ_QHNXXDZ")//SQR_ZZ_QHNXXDZ
	private String sqrZzQhnxxdz;
	@XmlElement(name = "QYR_GMSFHM")//QYR_GMSFHM
	private String qyrGmsfhm;
	@XmlElement(name = "QYR_XM")//QYR_XM
	private String qyrXm;
	@XmlElement(name = "QYR_XBDM")//QYR_XBDM
	private String qyrXbDm;
	@XmlElement(name = "QYR_MZDM")//QYR_MZDM
	private String qyrMzDm;
	@XmlElement(name = "QYR_CSRQ")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private String qyrCsrq;
	@XmlElement(name = "QYR_YSQRGX_JTGXDM")
	private String qyrYsqrgxJtgxdm;
	@XmlElement(name = "QYLDYYDM")
	private String qyldyydm;
	@XmlElement(name = "QCD_SSXQDM")
	private String qcdSsxqdm;
	@XmlElement(name = "QCD_QHNXXDZ")
	private String qcdQhnxxdz;
	@XmlElement(name = "QCD_HKDJJG_GAJGJGDM")
	private String qcdHkdjjgGajgdm;
	@XmlElement(name = "QCD_HKDJJG_GAJGMC")
	private String qcdHkdjjgGajgmc;
	@XmlElement(name = "QRD_SSXQDM")
	private String qrdSsxqdm;
	@XmlElement(name = "QRD_QHNXXDZ")
	private String qrdQhnxxdz;
	@XmlElement(name = "QRD_HKDJJG_GAJGJGDM")
	private String qrdHkdjjgGajgdm;
	@XmlElement(name = "QRD_HKDJJG_GAJGMC")
	private String qrdHkdjjgGajgmc;
	@XmlElement(name = "ZQZBH")
	private String zqzbh;
	@XmlElement(name = "QFJG_GAJGJGDM")
	private String qfjgGajgjgdm;
	@XmlElement(name = "QFJG_GAJGMC")
	private String qfjgGajgmc;
	@XmlElement(name = "CBR_XM")
	private String cbrXm;
	@XmlElement(name = "QFRQ")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private String qfRq;
	@XmlElement(name = "BZ")
	private String Bz;
	@XmlElement(name = "YXQJZRQ")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private String yxjzrq;
	@XmlElement(name = "XTYW_QYFWDM")
	private String xtywQyfwdm;
	@XmlElement(name = "SLDW_GAJGJGDM")
	private String sldwGajgjgdm;
	@XmlElement(name = "SLDW_GAJGMC")
	private String sldwGajgmc;
	@XmlElement(name = "SLDW_LXDH")
	private String sldwLxdh;
	@XmlElement(name = "SLR_GMSFHM")
	private String slrGmsfhm;
	@XmlElement(name = "SLR_XM")
	private String slrXm;
	@XmlElement(name = "SLR_LXDH")
	private String slrLxdh;
	@XmlElement(name = "SLSJ")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private String slsj;
	@XmlElement(name = "SJGSDWDM")
	private String sjgsdwdm;
	@XmlElement(name = "SJGSDWMC")
	private String sjgsdwmc;
	@XmlElement(name = "FSDW_SJGSDWDM")
	private String fsdwSjgsdwdm;
	@XmlElement(name = "FSDW_SJGSDWMC")
	private String fsdwSjgsdwmc;
	@XmlElement(name = "JSDW_SJGSDWDM")
	private String jsdwSjgsdwdm;
	@XmlElement(name = "JSDW_SJGSDWMC")
	private String jsdwSjgsdwmc;
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
	@XmlElement(name = "QYR_CYM")
	private String qyrCym;
	@XmlElement(name = "QYR_YCZRGX_JTGXDM")
	private String qyrYczrgxJtgxdm;
	@XmlElement(name = "QYR_ZY")
	private String qyrZy;
	@XmlElement(name = "QYR_XLDM")
	private String qyrXldm;
	@XmlElement(name = "QYR_HYZKDM")
	private String qyrHyzkdm;
	@XmlElement(name = "QYR_CSD_GJHDQDM")
	private String qyrCsdGjhdqdm;
	@XmlElement(name = "QYR_CSD_SSXQDM")
	private String qyrCsdSsxqdm;
	@XmlElement(name = "QYR_CSD_QHNXXDZ")
	private String qyrCsdQhnxxdz;
	@XmlElement(name = "QYR_JG_GJHDQDM")
	private String qyrJgGjhdqdm;
	@XmlElement(name = "QYR_JG_SSXQDM")
	private String qyrJgSsxqdm;
	@XmlElement(name = "QYR_JG_QHNXXDZ")
	private String qyrJgQhnxxdz;
	@XmlElement(name = "QYZBH")
	private String qyzBh;
	@XmlElement(name = "CZR_GMSFHM")
	private String czrGmsfhm;
	@XmlElement(name = "CZR_XM")
	private String czrXm;
	@XmlElement(name = "YZZ_SSXQDM")
	private String yzzSsxqdm;
	@XmlElement(name = "YZZ_QHNXXDZ")
	private String yzzQhnxxdz;
	@XmlElement(name = "YZZ_CXFLDM")
	private String yzzCxfldm;
	@XmlElement(name = "QWD_SSXQDM")
	private String qwdSsxqdm;
	@XmlElement(name = "QWD_QHNXXDZ")
	private String qwdQhnxxdz;
	@XmlElement(name = "YWQJZRQ")
	private String yxqjzrq;
	@XmlElement(name = "ZQBH")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private String zqbh;
	@XmlElement(name = "BYQC_JYQK")
	private String byqcJyqk;
	@XmlElement(name = "BLDW_GAJGJGDM")
	private String bldwGajgjgdm;
	@XmlElement(name = "BLDW_GAJGMC")
	private String bldwGajamc;
	@XmlElement(name = "BLDW_LXDH")
	private String bldwLxdh;
	@XmlElement(name = "BLR_GMSFHM")
	private String blrGmsfhm;
	@XmlElement(name = "BLR_XM")
	private String blrXm;
	@XmlElement(name = "BLR_LXDH")
	private String blrLxdh;
	@XmlElement(name = "BLSJ")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private String blsj;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getywxtbh() {
		return ywxtbh;
	}
	public void setywxtbh(String ywxtbh) {
		this.ywxtbh = ywxtbh;
	}
	public String getYwlsh() {
		return ywlsh;
	}
	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}
	public String getSqrGmsfhm() {
		return sqrGmsfhm;
	}
	public void setSqrGmsfhm(String sqrGmsfhm) {
		this.sqrGmsfhm = sqrGmsfhm;
	}
	public String getSqrXm() {
		return sqrXm;
	}
	public void setSqrXm(String sqrXm) {
		this.sqrXm = sqrXm;
	}
	public String getSqrLxdh() {
		return sqrLxdh;
	}
	public void setSqrLxdh(String sqrLxdh) {
		this.sqrLxdh = sqrLxdh;
	}
	public String getSqrZzSsxqdm() {
		return sqrZzSsxqdm;
	}
	public void setSqrZzSsxqdm(String sqrZzSsxqdm) {
		this.sqrZzSsxqdm = sqrZzSsxqdm;
	}
	public String getSqrZzQhnxxdz() {
		return sqrZzQhnxxdz;
	}
	public void setSqrZzQhnxxdz(String sqrZzQhnxxdz) {
		this.sqrZzQhnxxdz = sqrZzQhnxxdz;
	}
	public String getQyrGmsfhm() {
		return qyrGmsfhm;
	}
	public void setQyrGmsfhm(String qyrGmsfhm) {
		this.qyrGmsfhm = qyrGmsfhm;
	}
	public String getQyrXm() {
		return qyrXm;
	}
	public void setQyrXm(String qyrXm) {
		this.qyrXm = qyrXm;
	}
	public String getQyrXbDm() {
		return qyrXbDm;
	}
	public void setQyrXbDm(String qyrXbDm) {
		this.qyrXbDm = qyrXbDm;
	}
	public String getQyrMzDm() {
		return qyrMzDm;
	}
	public void setQyrMzDm(String qyrMzDm) {
		this.qyrMzDm = qyrMzDm;
	}
	public String getQyrCsrq() {
		return qyrCsrq;
	}
	public void setQyrCsrq(String qyrCsrq) {
		this.qyrCsrq = qyrCsrq;
	}
	public String getQyrYsqrgxJtgxdm() {
		return qyrYsqrgxJtgxdm;
	}
	public void setQyrYsqrgxJtgxdm(String qyrYsqrgxJtgxdm) {
		this.qyrYsqrgxJtgxdm = qyrYsqrgxJtgxdm;
	}
	public String getQyldyydm() {
		return qyldyydm;
	}
	public void setQyldyydm(String qyldyydm) {
		this.qyldyydm = qyldyydm;
	}
	public String getQcdSsxqdm() {
		return qcdSsxqdm;
	}
	public void setQcdSsxqdm(String qcdSsxqdm) {
		this.qcdSsxqdm = qcdSsxqdm;
	}
	public String getQcdQhnxxdz() {
		return qcdQhnxxdz;
	}
	public void setQcdQhnxxdz(String qcdQhnxxdz) {
		this.qcdQhnxxdz = qcdQhnxxdz;
	}
	public String getQcdHkdjjgGajgdm() {
		return qcdHkdjjgGajgdm;
	}
	public void setQcdHkdjjgGajgdm(String qcdHkdjjgGajgdm) {
		this.qcdHkdjjgGajgdm = qcdHkdjjgGajgdm;
	}
	public String getQcdHkdjjgGajgmc() {
		return qcdHkdjjgGajgmc;
	}
	public void setQcdHkdjjgGajgmc(String qcdHkdjjgGajgmc) {
		this.qcdHkdjjgGajgmc = qcdHkdjjgGajgmc;
	}
	public String getQrdSsxqdm() {
		return qrdSsxqdm;
	}
	public void setQrdSsxqdm(String qrdSsxqdm) {
		this.qrdSsxqdm = qrdSsxqdm;
	}
	public String getQrdQhnxxdz() {
		return qrdQhnxxdz;
	}
	public void setQrdQhnxxdz(String qrdQhnxxdz) {
		this.qrdQhnxxdz = qrdQhnxxdz;
	}
	public String getQrdHkdjjgGajgdm() {
		return qrdHkdjjgGajgdm;
	}
	public void setQrdHkdjjgGajgdm(String qrdHkdjjgGajgdm) {
		this.qrdHkdjjgGajgdm = qrdHkdjjgGajgdm;
	}
	public String getQrdHkdjjgGajgmc() {
		return qrdHkdjjgGajgmc;
	}
	public void setQrdHkdjjgGajgmc(String qrdHkdjjgGajgmc) {
		this.qrdHkdjjgGajgmc = qrdHkdjjgGajgmc;
	}
	public String getZqzbh() {
		return zqzbh;
	}
	public void setZqzbh(String zqzbh) {
		this.zqzbh = zqzbh;
	}
	public String getQfjgGajgjgdm() {
		return qfjgGajgjgdm;
	}
	public void setQfjgGajgjgdm(String qfjgGajgjgdm) {
		this.qfjgGajgjgdm = qfjgGajgjgdm;
	}
	public String getQfjgGajgmc() {
		return qfjgGajgmc;
	}
	public void setQfjgGajgmc(String qfjgGajgmc) {
		this.qfjgGajgmc = qfjgGajgmc;
	}
	public String getCbrXm() {
		return cbrXm;
	}
	public void setCbrXm(String cbrXm) {
		this.cbrXm = cbrXm;
	}
	public String getQfRq() {
		return qfRq;
	}
	public void setQfRq(String qfRq) {
		this.qfRq = qfRq;
	}
	public String getBz() {
		return Bz;
	}
	public void setBz(String bz) {
		Bz = bz;
	}
	public String getYxjzrq() {
		return yxjzrq;
	}
	public void setYxjzrq(String yxjzrq) {
		this.yxjzrq = yxjzrq;
	}
	public String getXtywQyfwdm() {
		return xtywQyfwdm;
	}
	public void setXtywQyfwdm(String xtywQyfwdm) {
		this.xtywQyfwdm = xtywQyfwdm;
	}
	public String getSldwGajgjgdm() {
		return sldwGajgjgdm;
	}
	public void setSldwGajgjgdm(String sldwGajgjgdm) {
		this.sldwGajgjgdm = sldwGajgjgdm;
	}
	public String getSldwGajgmc() {
		return sldwGajgmc;
	}
	public void setSldwGajgmc(String sldwGajgmc) {
		this.sldwGajgmc = sldwGajgmc;
	}
	public String getSldwLxdh() {
		return sldwLxdh;
	}
	public void setSldwLxdh(String sldwLxdh) {
		this.sldwLxdh = sldwLxdh;
	}
	public String getSlrGmsfhm() {
		return slrGmsfhm;
	}
	public void setSlrGmsfhm(String slrGmsfhm) {
		this.slrGmsfhm = slrGmsfhm;
	}
	public String getSlrXm() {
		return slrXm;
	}
	public void setSlrXm(String slrXm) {
		this.slrXm = slrXm;
	}
	public String getSlrLxdh() {
		return slrLxdh;
	}
	public void setSlrLxdh(String slrLxdh) {
		this.slrLxdh = slrLxdh;
	}
	public String getSlsj() {
		return slsj;
	}
	public void setSlsj(String slsj) {
		this.slsj = slsj;
	}
	public String getSjgsdwdm() {
		return sjgsdwdm;
	}
	public void setSjgsdwdm(String sjgsdwdm) {
		this.sjgsdwdm = sjgsdwdm;
	}
	public String getSjgsdwmc() {
		return sjgsdwmc;
	}
	public void setSjgsdwmc(String sjgsdwmc) {
		this.sjgsdwmc = sjgsdwmc;
	}
	public String getFsdwSjgsdwdm() {
		return fsdwSjgsdwdm;
	}
	public void setFsdwSjgsdwdm(String fsdwSjgsdwdm) {
		this.fsdwSjgsdwdm = fsdwSjgsdwdm;
	}
	public String getFsdwSjgsdwmc() {
		return fsdwSjgsdwmc;
	}
	public void setFsdwSjgsdwmc(String fsdwSjgsdwmc) {
		this.fsdwSjgsdwmc = fsdwSjgsdwmc;
	}
	public String getJsdwSjgsdwdm() {
		return jsdwSjgsdwdm;
	}
	public void setJsdwSjgsdwdm(String jsdwSjgsdwdm) {
		this.jsdwSjgsdwdm = jsdwSjgsdwdm;
	}
	public String getJsdwSjgsdwmc() {
		return jsdwSjgsdwmc;
	}
	public void setJsdwSjgsdwmc(String jsdwSjgsdwmc) {
		this.jsdwSjgsdwmc = jsdwSjgsdwmc;
	}
	public String getSxh() {
		return sxh;
	}
	public void setSxh(String sxh) {
		this.sxh = sxh;
	}
	public String getCllbdm() {
		return cllbdm;
	}
	public void setCllbdm(String cllbdm) {
		this.cllbdm = cllbdm;
	}
	public String getClmc() {
		return clmc;
	}
	public void setClmc(String clmc) {
		this.clmc = clmc;
	}
	public String getClgs() {
		return clgs;
	}
	public void setClgs(String clgs) {
		this.clgs = clgs;
	}
	public String getClnr() {
		return clnr;
	}
	public void setClnr(String clnr) {
		this.clnr = clnr;
	}
	public String getQyrCym() {
		return qyrCym;
	}
	public void setQyrCym(String qyrCym) {
		this.qyrCym = qyrCym;
	}
	public String getQyrYczrgxJtgxdm() {
		return qyrYczrgxJtgxdm;
	}
	public void setQyrYczrgxJtgxdm(String qyrYczrgxJtgxdm) {
		this.qyrYczrgxJtgxdm = qyrYczrgxJtgxdm;
	}
	public String getQyrZy() {
		return qyrZy;
	}
	public void setQyrZy(String qyrZy) {
		this.qyrZy = qyrZy;
	}
	public String getQyrXldm() {
		return qyrXldm;
	}
	public void setQyrXldm(String qyrXldm) {
		this.qyrXldm = qyrXldm;
	}
	public String getQyrHyzkdm() {
		return qyrHyzkdm;
	}
	public void setQyrHyzkdm(String qyrHyzkdm) {
		this.qyrHyzkdm = qyrHyzkdm;
	}
	public String getQyrCsdGjhdqdm() {
		return qyrCsdGjhdqdm;
	}
	public void setQyrCsdGjhdqdm(String qyrCsdGjhdqdm) {
		this.qyrCsdGjhdqdm = qyrCsdGjhdqdm;
	}
	public String getQyrCsdSsxqdm() {
		return qyrCsdSsxqdm;
	}
	public void setQyrCsdSsxqdm(String qyrCsdSsxqdm) {
		this.qyrCsdSsxqdm = qyrCsdSsxqdm;
	}
	public String getQyrCsdQhnxxdz() {
		return qyrCsdQhnxxdz;
	}
	public void setQyrCsdQhnxxdz(String qyrCsdQhnxxdz) {
		this.qyrCsdQhnxxdz = qyrCsdQhnxxdz;
	}
	public String getQyrJgGjhdqdm() {
		return qyrJgGjhdqdm;
	}
	public void setQyrJgGjhdqdm(String qyrJgGjhdqdm) {
		this.qyrJgGjhdqdm = qyrJgGjhdqdm;
	}
	public String getQyrJgSsxqdm() {
		return qyrJgSsxqdm;
	}
	public void setQyrJgSsxqdm(String qyrJgSsxqdm) {
		this.qyrJgSsxqdm = qyrJgSsxqdm;
	}
	public String getQyrJgQhnxxdz() {
		return qyrJgQhnxxdz;
	}
	public void setQyrJgQhnxxdz(String qyrJgQhnxxdz) {
		this.qyrJgQhnxxdz = qyrJgQhnxxdz;
	}
	public String getQyzBh() {
		return qyzBh;
	}
	public void setQyzBh(String qyzBh) {
		this.qyzBh = qyzBh;
	}
	public String getCzrGmsfhm() {
		return czrGmsfhm;
	}
	public void setCzrGmsfhm(String czrGmsfhm) {
		this.czrGmsfhm = czrGmsfhm;
	}
	public String getCzrXm() {
		return czrXm;
	}
	public void setCzrXm(String czrXm) {
		this.czrXm = czrXm;
	}
	public String getYzzSsxqdm() {
		return yzzSsxqdm;
	}
	public void setYzzSsxqdm(String yzzSsxqdm) {
		this.yzzSsxqdm = yzzSsxqdm;
	}
	public String getYzzQhnxxdz() {
		return yzzQhnxxdz;
	}
	public void setYzzQhnxxdz(String yzzQhnxxdz) {
		this.yzzQhnxxdz = yzzQhnxxdz;
	}
	public String getYzzCxfldm() {
		return yzzCxfldm;
	}
	public void setYzzCxfldm(String yzzCxfldm) {
		this.yzzCxfldm = yzzCxfldm;
	}
	public String getQwdSsxqdm() {
		return qwdSsxqdm;
	}
	public void setQwdSsxqdm(String qwdSsxqdm) {
		this.qwdSsxqdm = qwdSsxqdm;
	}
	public String getQwdQhnxxdz() {
		return qwdQhnxxdz;
	}
	public void setQwdQhnxxdz(String qwdQhnxxdz) {
		this.qwdQhnxxdz = qwdQhnxxdz;
	}
	public String getYxqjzrq() {
		return yxqjzrq;
	}
	public void setYxqjzrq(String yxqjzrq) {
		this.yxqjzrq = yxqjzrq;
	}
	public String getZqbh() {
		return zqbh;
	}
	public void setZqbh(String zqbh) {
		this.zqbh = zqbh;
	}
	public String getByqcJyqk() {
		return byqcJyqk;
	}
	public void setByqcJyqk(String byqcJyqk) {
		this.byqcJyqk = byqcJyqk;
	}
	public String getBldwGajgjgdm() {
		return bldwGajgjgdm;
	}
	public void setBldwGajgjgdm(String bldwGajgjgdm) {
		this.bldwGajgjgdm = bldwGajgjgdm;
	}
	public String getBldwGajamc() {
		return bldwGajamc;
	}
	public void setBldwGajamc(String bldwGajamc) {
		this.bldwGajamc = bldwGajamc;
	}
	public String getBldwLxdh() {
		return bldwLxdh;
	}
	public void setBldwLxdh(String bldwLxdh) {
		this.bldwLxdh = bldwLxdh;
	}
	public String getBlrGmsfhm() {
		return blrGmsfhm;
	}
	public void setBlrGmsfhm(String blrGmsfhm) {
		this.blrGmsfhm = blrGmsfhm;
	}
	public String getBlrXm() {
		return blrXm;
	}
	public void setBlrXm(String blrXm) {
		this.blrXm = blrXm;
	}
	public String getBlrLxdh() {
		return blrLxdh;
	}
	public void setBlrLxdh(String blrLxdh) {
		this.blrLxdh = blrLxdh;
	}
	public String getBlsj() {
		return blsj;
	}
	public void setBlsj(String blsj) {
		this.blsj = blsj;
	}
	@Override
	public String toString() {
		return "RECORDC [no=" + no + ", sid=" + sid + ", code=" + code + ", msg=" + msg + ", ywxtbh=" + ywxtbh
				+ ", ywlsh=" + ywlsh + ", sqrGmsfhm=" + sqrGmsfhm + ", sqrXm=" + sqrXm + ", sqrLxdh=" + sqrLxdh
				+ ", sqrZzSsxqdm=" + sqrZzSsxqdm + ", sqrZzQhnxxdz=" + sqrZzQhnxxdz + ", qyrGmsfhm=" + qyrGmsfhm
				+ ", qyrXm=" + qyrXm + ", qyrXbDm=" + qyrXbDm + ", qyrMzDm=" + qyrMzDm + ", qyrCsrq=" + qyrCsrq
				+ ", qyrYsqrgxJtgxdm=" + qyrYsqrgxJtgxdm + ", qyldyydm=" + qyldyydm + ", qcdSsxqdm=" + qcdSsxqdm
				+ ", qcdQhnxxdz=" + qcdQhnxxdz + ", qcdHkdjjgGajgdm=" + qcdHkdjjgGajgdm + ", qcdHkdjjgGajgmc="
				+ qcdHkdjjgGajgmc + ", qrdSsxqdm=" + qrdSsxqdm + ", qrdQhnxxdz=" + qrdQhnxxdz + ", qrdHkdjjgGajgdm="
				+ qrdHkdjjgGajgdm + ", qrdHkdjjgGajgmc=" + qrdHkdjjgGajgmc + ", zqzbh=" + zqzbh + ", qfjgGajgjgdm="
				+ qfjgGajgjgdm + ", qfjgGajgmc=" + qfjgGajgmc + ", cbrXm=" + cbrXm + ", qfRq=" + qfRq + ", Bz=" + Bz
				+ ", yxjzrq=" + yxjzrq + ", xtywQyfwdm=" + xtywQyfwdm + ", sldwGajgjgdm=" + sldwGajgjgdm
				+ ", sldwGajgmc=" + sldwGajgmc + ", sldwLxdh=" + sldwLxdh + ", slrGmsfhm=" + slrGmsfhm + ", slrXm="
				+ slrXm + ", slrLxdh=" + slrLxdh + ", slsj=" + slsj + ", sjgsdwdm=" + sjgsdwdm + ", sjgsdwmc="
				+ sjgsdwmc + ", fsdwSjgsdwdm=" + fsdwSjgsdwdm + ", fsdwSjgsdwmc=" + fsdwSjgsdwmc + ", jsdwSjgsdwdm="
				+ jsdwSjgsdwdm + ", jsdwSjgsdwmc=" + jsdwSjgsdwmc + ", sxh=" + sxh + ", cllbdm=" + cllbdm + ", clmc="
				+ clmc + ", clgs=" + clgs + ", clnr=" + clnr + ", qyrCym=" + qyrCym + ", qyrYczrgxJtgxdm="
				+ qyrYczrgxJtgxdm + ", qyrZy=" + qyrZy + ", qyrXldm=" + qyrXldm + ", qyrHyzkdm=" + qyrHyzkdm
				+ ", qyrCsdGjhdqdm=" + qyrCsdGjhdqdm + ", qyrCsdSsxqdm=" + qyrCsdSsxqdm + ", qyrCsdQhnxxdz="
				+ qyrCsdQhnxxdz + ", qyrJgGjhdqdm=" + qyrJgGjhdqdm + ", qyrJgSsxqdm=" + qyrJgSsxqdm + ", qyrJgQhnxxdz="
				+ qyrJgQhnxxdz + ", qyzBh=" + qyzBh + ", czrGmsfhm=" + czrGmsfhm + ", czrXm=" + czrXm + ", yzzSsxqdm="
				+ yzzSsxqdm + ", yzzQhnxxdz=" + yzzQhnxxdz + ", yzzCxfldm=" + yzzCxfldm + ", qwdSsxqdm=" + qwdSsxqdm
				+ ", qwdQhnxxdz=" + qwdQhnxxdz + ", yxqjzrq=" + yxqjzrq + ", zqbh=" + zqbh + ", byqcJyqk=" + byqcJyqk
				+ ", bldwGajgjgdm=" + bldwGajgjgdm + ", bldwGajamc=" + bldwGajamc + ", bldwLxdh=" + bldwLxdh
				+ ", blrGmsfhm=" + blrGmsfhm + ", blrXm=" + blrXm + ", blrLxdh=" + blrLxdh + ", blsj=" + blsj + "]";
	}






}
