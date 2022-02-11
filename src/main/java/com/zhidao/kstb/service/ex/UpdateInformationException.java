package com.zhidao.kstb.service.ex;

/**
 * 信息更新异常
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-9-18<p>
 */
public class UpdateInformationException extends ServiceException {
    public UpdateInformationException() {
    }

    public UpdateInformationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UpdateInformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateInformationException(String message) {
        super(message);
    }

    public UpdateInformationException(Throwable cause) {
        super(cause);
    }
}
