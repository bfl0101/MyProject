package com.zhidao.kstb.zjzzfkjs.model.resp.err;

import com.zhidao.kstb.zjzzfkjs.model.req.ZjzzPackageHead;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PACKAGE")
public class ZjzzPackageErr {
    @XmlElement(name = "PACKAGEHEAD")
    private ZjzzPackageHead zjzzPackageHead;


    @XmlElementWrapper(name = "DATA")
    @XmlElement(name = "RECORD")
    private List<ZjzzRecordErr> data;
}
