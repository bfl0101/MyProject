package com.zhidao.kstb.service.ex;

/**
 * 添加数据异常
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-16<p>
 */
public class InsertException extends ServiceException{

    public InsertException() {
    }

    public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }
}
