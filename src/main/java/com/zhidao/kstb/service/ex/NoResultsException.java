package com.zhidao.kstb.service.ex;
/**
 * 无满足条件的查询结果异常
 * 
 * @author <致道科技>YunTong
 * <p>Created on 2021-8-6<p>
 */
public class NoResultsException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7310932264660632039L;

	public NoResultsException() {
		super();
		
	}

	public NoResultsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public NoResultsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NoResultsException(String message) {
		super(message);
		
	}

	public NoResultsException(Throwable cause) {
		super(cause);
		
	}
	
}
