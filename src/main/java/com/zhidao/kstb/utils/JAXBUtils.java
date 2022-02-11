package com.zhidao.kstb.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * JAXB(Java Architechure for XML Binding)工具类
 * 根据XML Schema 生产 Java类，提供将XML实例文档反向生
 * 成Java对象树的方法，并能将Java对象树的内容重新写到XML
 * 实例文档
 * 
 * @author <致道科技>YunTong
 * <p>Created on 2021-6-10<p>
 */
public class JAXBUtils {
	
	/**
	 * XML字符串转换为对象
	 * 
	 * @author <致道科技>YunTong
	 * @param clazz 目标对象
	 * @param xmlStr XML字符串
	 * @return 转换后的Xml对象
	 * <p>Created on 2021-6-10<p>
	 */
	public static Object xmlStrToObject(Class clazz , String xmlStr) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader stringReader = new StringReader(xmlStr);
			xmlObject = unmarshaller.unmarshal(stringReader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
		
	}

	/**
	 * 对象转换XML字符串
	 * 
	 * @author <致道科技>YunTong
	 * @param object 对象
	 * @param load 加载对象
	 * @return 转换成功的字符串
	 * <p>Created on 2021-6-10<p>
	 */
	public static String objectToXmlStr(Object object,Class<?>load) {
		String result = "";
		try {
			//根据javaBean类生成上下文对象
			JAXBContext context = JAXBContext.newInstance(load);
			//从上下文中获取Marshaller对象，用作将bean编组(转换)成XML
			Marshaller marshaller = context.createMarshaller();
			//格式化输出，按照标签自动换行，否则就是一行输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //设置编码，默认UTF-8
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //是否省略xml头信息，默认不省略(false)
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT,false);
            StringWriter stringWriter = new StringWriter();
            //编组
            marshaller.marshal(object,stringWriter);
            //将编组完成后的JavaBean转换成字符串
            result = stringWriter.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;
	}

}
