package com.zhidao.kstb.service.ex;

/**
 * 该记录不存在异常
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-18<p>
 */
public class RecordNotExistException extends ServiceException{
    public RecordNotExistException() {
    }

    public RecordNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RecordNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordNotExistException(String message) {
        super(message);
    }

    public RecordNotExistException(Throwable cause) {
        super(cause);
    }
}
