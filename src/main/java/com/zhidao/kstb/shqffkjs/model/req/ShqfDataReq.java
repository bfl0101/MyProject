package com.zhidao.kstb.shqffkjs.model.req;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ShqfDataReq {
    @XmlElement(name = "RECORD")
    private ShqfRecord shqfRecord;
}
