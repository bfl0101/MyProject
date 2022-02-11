package com.zhidao.kstb.model.ele;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 封装XML数据包中的 PACKAGEC-PACKAGEHEADC-DATA
 * 
 * @author <致道科技>YunTong
 * @version 0.1.1
 * <p>Created on 2021-6-9<p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class PACKAGE {
	@XmlElement(name = "PACKAGEHEAD")
	private PACKAGEHEAD pacskageHead;
	@XmlElementWrapper(name = "DATA")
	@XmlElement(name = "RECORD")
	private List<RECORD> recordList;

	public PACKAGEHEAD getPacskageHead() {
		return pacskageHead;
	}

	public void setPacskageHead(PACKAGEHEAD pacskageHead) {
		this.pacskageHead = pacskageHead;
	}

	public List<RECORD> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<RECORD> recordList) {
		this.recordList = recordList;
	}

	@Override
	public String toString() {
		return "PACKAGEC [pacskageHead=" + pacskageHead + ", recordList=" + recordList + "]";
	}

}
