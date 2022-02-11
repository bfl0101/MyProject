package com.zhidao.kstb.service.ex;

/**
 * 该记录已存在异常
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-17<p>
 */
public class ExistOfRecordException extends ServiceException{
    public ExistOfRecordException() {
    }

    public ExistOfRecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ExistOfRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistOfRecordException(String message) {
        super(message);
    }

    public ExistOfRecordException(Throwable cause) {
        super(cause);
    }
}
