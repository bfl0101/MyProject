package com.zhidao.kstb.shqffkjs.model.resp.err;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;

@Data
public class ShqfRecordErr {
    private String no;  //接收协作数据中的数据序号

    private String sid; //接收协作数据的信息分类代码

    private String code; //接收协作数据的信息分类代码

    private String msg; //接收协作数据的信息分类代码

    @XmlAttribute
    public String getNo() {
        return no;
    }

    @XmlAttribute
    public String getSid() {
        return sid;
    }

    @XmlAttribute
    public String getCode() {
        return code;
    }

    @XmlAttribute
    public String getMsg() {
        return msg;
    }
}
