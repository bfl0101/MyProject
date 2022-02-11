package com.zhidao.kstb.controller;

import com.zhidao.kstb.service.ex.*;
import com.zhidao.kstb.utils.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制器基类，为其他控制器组件提供通用功能
 * 
 * @author <致道科技>YunTong
 * <p>Created on 2021-8-6<p>
 */
@ControllerAdvice
public class BaseController {
	public static final String OK = "10";
	public static final String msg = "操作成功";
	/**
	 * 捕获全局异常的方法
	 * 
	 * @author <致道科技>YunTong
	 * @param throwable 
	 * @return 状态描述以及对应的异常信息
	 * <p>Created on 2021-8-6<p>
	 */
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> HandleException(Throwable throwable){
		JsonResult jsonResult = new JsonResult();
		if(throwable instanceof NoResultsException){
			jsonResult.setCode("-40");
			jsonResult.setMsg("没有符合条件的查询结果！");
		}else if(throwable instanceof UnableToStatisticsException) {
			jsonResult.setCode("-51");
			jsonResult.setMsg("无法统计或条件表达式错误！");
		}else if(throwable instanceof InsertException){
			jsonResult.setCode("-50");
			jsonResult.setMsg("数据添加失败，请联系 系统系统管理员！");
		}else if(throwable instanceof ExistOfRecordException){
			jsonResult.setCode("-52");
			jsonResult.setMsg("ERROR：'该记录已经存在,禁止推送！'");
		}else if(throwable instanceof UpdateInformationException){
			jsonResult.setCode("-53");
			jsonResult.setMsg("ERROR：'数据更新失败，请联系 系统系统管理员！'");
		}else if(throwable instanceof RecordNotExistException){
			jsonResult.setCode("-54");
			jsonResult.setMsg("ERROR, 该记录不存在！");
		}else if(throwable instanceof YwxtbhNotFoundExceptipon){
			jsonResult.setCode("-55");
			jsonResult.setMsg("ERROR：协同协同编号不存在！");
		}
		return jsonResult;
	}
}
