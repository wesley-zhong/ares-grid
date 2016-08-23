package com.wd.ares.db.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.wd.ares.bean.WdRequest;

public class WdBeanUtils {
	public static String concertBeanToInsertValue(Object msgBean) {
		Field[] fields = msgBean.getClass().getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		try{
			for(int i = 0 ; i < fields.length; ++i){
				Field field = fields[i];
				String fieldName = field.getName();
				System.out.println("filed Name = " + fieldName);
				String methodName = "get" + fieldName.replaceFirst(fieldName.substring(0, 1),fieldName.substring(0, 1).toUpperCase()) ;
				Method getMethod = msgBean.getClass().getMethod(methodName, null);
				String value = (String) getMethod.invoke(msgBean, null);
				sb.append("'");
				if(value != null)
				   sb.append(value);
				sb.append("'");
				sb.append(",");
				
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
			return sb.toString();		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String concertBeanListToInsertValue(List<?> msgBeans){
		StringBuilder sb = new StringBuilder();
		for(Object obj : msgBeans){
			sb.append(concertBeanToInsertValue(obj));
			sb.append(",");	
		}	
		return sb.substring(0,  sb.length() - 1);
	}
	
	public static void main(String[] args) {
		WdRequest  msgBean = new WdRequest();
		msgBean.setFauxPropId("aa");
		String values = concertBeanToInsertValue(msgBean);
		System.out.println("valued = " + values);
	}

}
