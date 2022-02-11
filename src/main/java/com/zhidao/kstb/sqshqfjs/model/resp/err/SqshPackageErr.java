package com.zhidao.kstb.sqshqfjs.model.resp.err;

import com.zhidao.kstb.sqshqfjs.model.req.SqshPackageHead;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class SqshPackageErr {
    @XmlElement(name = "PACKAGEHEAD")
    private SqshPackageHead sqshPackageHead;

    @XmlElement(name = "RECORD")
    @XmlElementWrapper(name = "DATA")
    private List<SqshRecordErr> sqshRecordErrs;
}
