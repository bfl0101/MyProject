package com.zhidao.kstb.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 封装Json响应结果的JavaBean
 * 
 * @author <致道科技>YunTong
 * <p>Created on 2021-8-6<p>
 * <p>Updated on 2021-8-10<p>
 * @param <E> Object
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JsonResult<E> {
	
	private String code;
	private String msg;
	private E data;
	
	public JsonResult() {
		
	}
	public JsonResult(String code, String msg, E data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(String code) {
        super();
        this.code = code;
    }

    public JsonResult(Throwable throwable) {
        super();
        this.msg = throwable.getMessage();
    }

    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(E data) {
        this.data = data;
    }

    public JsonResult(String code, E data) {
        this.code = code;
        this.data = data;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
