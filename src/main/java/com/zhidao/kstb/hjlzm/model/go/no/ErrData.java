package com.zhidao.kstb.hjlzm.model.go.no;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ErrData {
    @XmlAttribute(name = "no")
    private String no;
    @XmlAttribute(name= "sid")
    private String sid;
    @XmlAttribute(name = "code")
    private String code;
    @XmlAttribute(name = "msg")
    private String msg;


}
