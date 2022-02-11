package com.zhidao.kstb.shqffkjs.model.resp.err;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ShqfDataErr {

    @XmlElement(name = "RECORD")
    private ShqfRecordErr record;
}
