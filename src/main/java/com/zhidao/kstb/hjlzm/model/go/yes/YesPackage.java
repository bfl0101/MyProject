package com.zhidao.kstb.hjlzm.model.go.yes;

import com.zhidao.kstb.hjlzm.model.come.HjzmPackageHead;
import com.zhidao.kstb.hjlzm.model.come.HjzmRecord;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class YesPackage {
    @XmlElement(name = "PACKAGEHEAD")
    private HjzmPackageHead hjzmPackageHead;
    @XmlElement(name = "DATA")
    private HjzmRecord hjzmRecord;
}
