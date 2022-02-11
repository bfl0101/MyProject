package com.zhidao.kstb.config;

import com.zhidao.kstb.service.IKSTBService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * 发布WebService配置工具类
 * 
 * @author <致道科技>YunTong
 * <p>Created on 2021-6-10<p>
 */
@Configuration
public class WebServiceConfig {
	@Autowired
	IKSTBService ikstbService;
	// Simple Logging Facade for Java 
	private static final Logger logger = LoggerFactory.getLogger(WebServiceConfig.class);
	/**
	 * 改变项目中服务名的前缀名
	 * 
	 * @author <致道科技>YunTong
	 * @return 创建一个新的ServletRegistrationBean实例，并指定Servlet和URL映射。
	 * <p>Created on 2021-6-10<p>
	 */
	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		return new ServletRegistrationBean<CXFServlet>(new CXFServlet(),"/ZAGL/*");
	}
	/**
	 * 配置&初始化消息总站
	 * 
	 * @author <致道科技>YunTong
	 * @return SpringBus实例
	 * <p>Created on 2021-6-10<p>
	 */
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	/**
	 * JAX-WS 站点服务: 
	 * 
	 * @author <致道科技>YunTong
	 * @return Endpoint address
	 * <p>Created on 2021-6-10<p>
	 */
	@Bean
	public Endpoint endPoint() {
		EndpointImpl endPoint = new EndpointImpl(springBus(),ikstbService);//需要绑定发布的服务
		logger.info("正在发布服务...");
		endPoint.publish("/KSTBService");//发布的服务名称
		logger.info("发布完毕!");
		return endPoint;
	}
	
	
}
