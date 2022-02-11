package com.zhidao.kstb.model.ele;


import javax.xml.bind.annotation.XmlElement;

/**
 * 包头部分实体类
 * 
 * @author <致道科技>YunTong
 * @version 0.1.1
 * <p>Created on 2021-6-9<p>
 * <p>Updated on 2021-6-11<p>
 * <p>Updated on 2021-6-24<p>
 */
public class PACKAGEHEAD {
	private String bbh;
	private String fsdwSjgsdwdm;
	private String fsdwSjgsdwmc;
	private String jsdwSjgsdwdm;
	private String jsdwSjgsdwmc;
	private String zaglywfldm;
	private String ywxtbh;
	private String ywxtqyfw;
	private String fssj;
	@XmlElement(name = "BBH")
	public String getBbh() {
		return bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	@XmlElement(name = "FSDW_SJGSDWDM")
	public String getFsdwSjgsdwdm() {
		return fsdwSjgsdwdm;
	}

	public void setFsdwSjgsdwdm(String fsdwSjgsdwdm) {
		this.fsdwSjgsdwdm = fsdwSjgsdwdm;
	}
	@XmlElement(name = "FSDW_SJGSDWMC")
	public String getFsdwSjgsdwmc() {
		return fsdwSjgsdwmc;
	}

	public void setFsdwSjgsdwmc(String fsdwSjgsdwmc) {
		this.fsdwSjgsdwmc = fsdwSjgsdwmc;
	}
	@XmlElement(name = "JSDW_SJGSDWDM")
	public String getJsdwSjgsdwdm() {
		return jsdwSjgsdwdm;
	}

	public void setJsdwSjgsdwdm(String jsdwSjgsdwdm) {
		this.jsdwSjgsdwdm = jsdwSjgsdwdm;
	}
	@XmlElement(name = "JSDW_SJGSDWMC")
	public String getJsdwSjgsdwmc() {
		return jsdwSjgsdwmc;
	}

	public void setJsdwSjgsdwmc(String jsdwSjgsdwmc) {
		this.jsdwSjgsdwmc = jsdwSjgsdwmc;
	}
	@XmlElement(name = "ZAGLYWFLDM")
	public String getZaglywfldm() {
		return zaglywfldm;
	}

	public void setZaglywfldm(String zaglywfldm) {
		this.zaglywfldm = zaglywfldm;
	}
	@XmlElement(name = "YWXTBH")
	public String getYwxtbh() {
		return ywxtbh;
	}

	public void setYwxtbh(String ywxtbh) {
		this.ywxtbh = ywxtbh;
	}
	@XmlElement(name = "YWXTQYFW")
	public String getYwxtqyfw() {
		return ywxtqyfw;
	}

	public void setYwxtqyfw(String ywxtqyfw) {
		this.ywxtqyfw = ywxtqyfw;
	}
	@XmlElement(name = "FSSJ")
	public String getFssj() {
		return fssj;
	}

	public void setFssj(String fssj) {
		this.fssj = fssj;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PACKAGEHEAD{");
		sb.append("bbh='").append(bbh).append('\'');
		sb.append(", fsdwSjgsdwdm='").append(fsdwSjgsdwdm).append('\'');
		sb.append(", fsdwSjgsdwmc='").append(fsdwSjgsdwmc).append('\'');
		sb.append(", jsdwSjgsdwdm='").append(jsdwSjgsdwdm).append('\'');
		sb.append(", jsdwSjgsdwmc='").append(jsdwSjgsdwmc).append('\'');
		sb.append(", zaglywfldm='").append(zaglywfldm).append('\'');
		sb.append(", ywxtbh='").append(ywxtbh).append('\'');
		sb.append(", ywxtqyfw='").append(ywxtqyfw).append('\'');
		sb.append(", fssj='").append(fssj).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
