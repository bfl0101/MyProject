package com.zhidao.kstb.sqshqfjs.model.resp.suc;

import com.zhidao.kstb.sqshqfjs.model.req.SqshPackageHead;
import com.zhidao.kstb.sqshqfjs.model.req.SqshRecord;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class SqshPackageSuc {
    @XmlElement(name = "PACKAGEHEAD")
    private SqshPackageHead sqshPackageHead;


    @XmlElement(name = "Data")
    private SqshRecord data;
}
