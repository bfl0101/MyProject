package com.zhidao.kstb.hjlzm.model.come;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class HjzmPackeage {
    @XmlElement(name = "PACKAGEHEAD")
    private HjzmPackageHead hjzmPackageHead;

    @XmlElementWrapper(name = "DATA")
    @XmlElement(name = "RECORD")
    private List<HjzmRecord> data;
}
