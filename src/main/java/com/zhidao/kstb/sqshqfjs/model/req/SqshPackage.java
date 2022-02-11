package com.zhidao.kstb.sqshqfjs.model.req;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class SqshPackage {
    @XmlElement(name = "PACKAGEHEAD")
    private SqshPackageHead sqshPackageHead;


    @XmlElement(name = "RECORD")
    @XmlElementWrapper(name = "DATA")
    private List<SqshRecord> data;
}
