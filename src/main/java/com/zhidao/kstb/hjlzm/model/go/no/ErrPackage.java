package com.zhidao.kstb.hjlzm.model.go.no;

import com.zhidao.kstb.hjlzm.model.come.HjzmPackageHead;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class ErrPackage {
    @XmlElement(name = "PACKAGEHEAD")
    private HjzmPackageHead hjzmPackageHead;
    @XmlElementWrapper(name = "DATA")
    @XmlElement(name = "RECORD")
    private List<ErrData> noData;

}
