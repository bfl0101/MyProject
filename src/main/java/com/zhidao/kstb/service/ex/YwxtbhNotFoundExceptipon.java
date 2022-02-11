package com.zhidao.kstb.service.ex;

/**
 * 业务协同编号不存在异常
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-24<p>
 */
public class YwxtbhNotFoundExceptipon extends ServiceException{
    public YwxtbhNotFoundExceptipon() {
    }

    public YwxtbhNotFoundExceptipon(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public YwxtbhNotFoundExceptipon(String message, Throwable cause) {
        super(message, cause);
    }

    public YwxtbhNotFoundExceptipon(String message) {
        super(message);
    }

    public YwxtbhNotFoundExceptipon(Throwable cause) {
        super(cause);
    }
}
