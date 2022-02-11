package com.zhidao.kstb.shqffkjs.model.resp.err;

import com.zhidao.kstb.shqffkjs.model.req.ShqfPackageHead;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class ShqfPackageErr {
    @XmlElement(name = "PACKAGEHEAD")
    private ShqfPackageHead shqfPackageHead;


    @XmlElement(name = "DATA")
    private ShqfDataErr shqfDataErr;
}
