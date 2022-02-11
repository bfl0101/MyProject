package com.zhidao.kstb.controller;


import com.zhidao.kstb.service.IKSTBService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 接收信息控制器
 * 
 * @author <致道科技>YunTong
 * @author <致道科技>Cui
 * <p>Created on 2021-6-11<p>
 * <p>Updated on 2021-9-2<p>
 */
@RestController
@CrossOrigin
@RequestMapping(value = "KSTB")
public class KSTBController {
	@Autowired
	private IKSTBService ikstbService;

	/**
	 * 接收申请迁出信息
	 * 
	 * @author <致道科技>YunTong
	 * @param sqzcm 授权注册码
	 * @param information 业务请求参数
	 * @return 接口调用结果
	 * <p>Created on 2021-6-21<p>
	 */
	@PostMapping(value = "ZAGL_SQQCJSFW", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	private String ZAGL_SQQCJSFW(String sqzcm, @RequestBody String information) {
		return ikstbService.ZAGL_SQQCJSFW(sqzcm, information);
	}

	/**
	 * 接收迁出反馈信息
	 *
	 * @author <致道科技>YunTong
	 * @param sqzcm 授权注册码
	 * @param information 业务请求参数
	 * @return 接口调用结果
	 * <p>Created on 2021-6-29<p>
	 */
	@PostMapping(value = "ZAGL_QCFKJSFW",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	private String ZAGL_QCFKJSFW(String sqzcm, @RequestBody String information) {
		return ikstbService.ZAGL_QCFKJSFW(sqzcm,information);
	}

	/**
	 * 申请出生入户接收服务
	 *
	 * @author <致道科技>Cui
	 * @param sqzcm 授权注册码
	 * @param information 业务请求参数
	 * @return 接口调用结果
	 * <p>Created on 2021-6-21<p>
	 */
	@PostMapping(value = "ZAGL_SQCSRHJSFW", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	private String ZAGL_SQCSRHJSFW(String sqzcm, @RequestBody String information)  {
		return ikstbService.ZAGL_SQCSRHJSFW(sqzcm, information);
	}

	/**
	 * 出生入户反馈接收服务和不予落户反馈接收服务
	 *
	 * @author <致道科技>Cui
	 * @param sqzcm 授权注册码
	 * @param information 业务请求参数
	 * @return 接口调用结果
	 * <p>Created on 2021-6-29<p>
	 */
	@PostMapping(value = "ZAGL_CSRHFKJSFW",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	private String ZAGL_CSRHFKJSFW(String sqzcm, @RequestBody String information) {
		return ikstbService.ZAGL_CSRHFKJSFW(sqzcm,information);
	}

	/**
	 * 常住人口登记表流转接收服务
	 *
	 * @author <致道科技>Cui
	 * @param sqzcm 授权注册码
	 * @param information 业务请求参数
	 * @return 接口调用结果
	 * <p>Created on 2021-7-30<p>
	 */
	@PostMapping(value = "ZAGL_CZRKDJBLZJSFW",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	private String ZAGL_CZRKDJBLZJSFW(String sqzcm, @RequestBody String information){
		return ikstbService.ZAGL_CZRKDJBLZJSFW(sqzcm,information);
	}

	/**
	 * 居民身份证申请审核签发服务
	 */
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	@RequestMapping("/ZAGL_JMSFZSQSHQFJSFW")
	public String aa(@RequestBody String a) throws Exception {
		Thread.currentThread().setContextClassLoader(classLoader);//在获取连接之0前 还原上下文
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client;
		client = dcf.createClient("http://172.30.5.106:8088/ZAGL/KSTBService?wsdl");
		Object[] o;
		o = client.invoke("ZAGL_JMSFZSQSHQFJSFW","123",a);

		return o[0].toString();
	}

	/**
	 * 申请开具户籍证明接收服务
	 *
	 * @author <致道科技>YunTong
	 * @param sqzcm 授权注册码
	 * @param information 业务请求参数
	 * @return 接口调用结果
	 * <p>Created on 2021-9-10<p>
	 */
	@RequestMapping(value = "ZAGL_SQKJHJZMJSFW",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	private String ZAGL_SQKJHJZMJSFW(String sqzcm,@RequestBody String information){
		return ikstbService.ZAGL_SQKJHJZMJSFW(sqzcm,information);
	}

	/**
	 * 开具户籍证明反馈接收服务
	 *
	 * @author <致道科技>YunTong
	 * @param sqzcm 授权注册码
	 * @param information 业务请求参数
	 * @return 接口调用结果
	 * <p>Created on 2021-9-10<p>
	 */
	@PostMapping(value = "ZAGL_KJHJZMFKJSFW",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	private String ZAGL_KJHJZMFKJSFW(String sqzcm ,@RequestBody String information){
		return ikstbService.ZAGL_KJHJZMFKJSFW(sqzcm,information);
	}
}
