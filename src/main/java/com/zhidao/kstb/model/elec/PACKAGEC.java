package com.zhidao.kstb.model.elec;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 封装XML数据包中的 PACKAGEC-PACKAGEHEADC-DATA
 * 
 * @author <致道科技>Cui
 * @version 0.1.1
 * <p>Created on 2021-6-9<p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class PACKAGEC {
	@XmlElement(name = "PACKAGEHEAD")
	private PACKAGEHEADC pacskageHead;
	@XmlElementWrapper(name = "DATA")
	@XmlElement(name = "RECORD")
	private List<RECORDC> RECORDCList;

	public PACKAGEHEADC getPacskageHead() {
		return pacskageHead;
	}

	public void setPacskageHead(PACKAGEHEADC pacskageHead) {
		this.pacskageHead = pacskageHead;
	}

	public List<RECORDC> getRecordList() {
		return RECORDCList;
	}

	public void setRecordList(List<RECORDC> RECORDCList) {
		this.RECORDCList = RECORDCList;
	}

	@Override
	public String toString() {
		return "PACKAGEC [pacskageHead=" + pacskageHead + ", RECORDCList=" + RECORDCList + "]";
	}

}
