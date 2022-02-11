package com.zhidao.kstb.zjzzfkjs.model.req;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class ZjzzPackage {
    @XmlElement(name = "PACKAGEHEAD")
    private ZjzzPackageHead zjzzPackageHead;


    @XmlElement(name = "RECORD")
    @XmlElementWrapper(name = "DATA")
    private List<ZjzzRecord> data;
}
