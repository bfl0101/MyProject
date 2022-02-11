package com.zhidao.kstb.shqffkjs.model.resp.suc;

import com.zhidao.kstb.shqffkjs.model.req.ShqfPackageHead;
import com.zhidao.kstb.shqffkjs.model.req.ShqfRecord;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class ShqfPackageSuc {
    @XmlElement(name = "PACKAGEHEAD")
    private ShqfPackageHead shqfPackageHead;


    @XmlElement(name = "DATA")
    private ShqfRecord shqfRecord;
}
