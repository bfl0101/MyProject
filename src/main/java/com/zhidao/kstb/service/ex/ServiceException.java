package com.zhidao.kstb.service.ex;
/**
 * <p> 业务层异常的基类
 * 该异常下的子类有：
 *
 * <li> {@link ExistOfRecordException}
 * <li> {@link RecordNotExistException}
 * <li> {@link NoResultsException}
 * <li> {@link UnableToStatisticsException}
 * <li> {@link YwxtbhNotFoundExceptipon}
 * <li> {@link UpdateInformationException}
 * @see RuntimeException
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-8-6<p>
 */
public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6486500359622293126L;

	public ServiceException() {
		super();
		
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceException(String message) {
		super(message);
		
	}

	public ServiceException(Throwable cause) {
		super(cause);
		
	}
	
}
