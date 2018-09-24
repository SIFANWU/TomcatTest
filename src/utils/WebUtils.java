package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils {

	public static <T> T requesttoBean(HttpServletRequest request, Class<T> beanClass) {
		// 不需要强制转换类型 都是<T>
		try {
			// 创建一个bean类
			T bean = beanClass.newInstance();

			// 把request 中数据传到bean中
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = request.getParameter(name);   
				BeanUtils.setProperty(bean, name, value);
				/*
				 * name = username value="aaa"
				 * name = password value="123"
				 * */

			}
			return bean;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static void  copyBean(Object src, Object dest) {
		//注册日期转换器
		ConvertUtils.register(new Converter( ) {
			@Override
			public Object convert(Class type, Object value) {
				
				if (value == null) {
					return null;
				}
				String str = (String) value;
				if (str.trim().equals("")) {
					return null;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				
				try {
					return df.parse(str);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	//UUID类产生唯一标识符ID
	public static String generateID() {
		return UUID.randomUUID().toString();
	}
	
	

}
