package com.zhidao.kstb.service.ex;
/**
 * 无法统计异常
 * 
 * @author <致道科技>YunTong
 * <p>Created on 2021-8-16<p>
 */
public class UnableToStatisticsException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7978265450792666835L;

	public UnableToStatisticsException() {
		super();
		
	}

	public UnableToStatisticsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UnableToStatisticsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UnableToStatisticsException(String message) {
		super(message);
		
	}

	public UnableToStatisticsException(Throwable cause) {
		super(cause);
		
	}
	
}
