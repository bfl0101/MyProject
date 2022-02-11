package com.zhidao.kstb.zjzzfkjs.model.resp.suc;

import com.zhidao.kstb.zjzzfkjs.model.req.ZjzzPackageHead;
import com.zhidao.kstb.zjzzfkjs.model.req.ZjzzRecord;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class ZjzzPackageSuc {
    @XmlElement(name = "PACKAGEHEAD")
    private ZjzzPackageHead zjzzPackageHead;

    @XmlElement(name = "DATA")
    private ZjzzRecord data;
}
