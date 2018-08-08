package com.jingcheng.musicEducation.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1891864664213323791L;
	/**
	 * 此方法为将map中，array类型的value转成Object类型
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String,Object> stringArrayToObject(Map<String,String[]> map){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> iter = map.entrySet().iterator();
		String name = "";
		String value = "";
		while (iter.hasNext()) {
			Entry<String, String[]> entry = iter.next();
			name = entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) { // 用于请求参数中有多个相同名称
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();// 用于请求参数中请求参数名唯一
			}
			try {
				returnMap.put(name, StringUtils.isEmpty(value)?"":new String(value.getBytes("iso-8859-1"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}	
		return returnMap;
		
	}
    /**
     * 字符串数组转整型数组
     * 注：字符串为整型数字字符串
     * @param str
     * @return
     */
	public static Integer[] stringArrayToIntegerArray(String[]str) {
		int length=str.length;
		Integer[] iArr=new Integer[length];
		for(int i=0;i<length;i++){
			iArr[i]=Integer.parseInt(str[i]);
		}
		return iArr;
	}
	/**
	 * json数据转字符串数组
	 * 注：数据在json中以“,”隔开的
	 * @param jsonData
	 * @param name
	 * @return
	 */
	public static String[] jsonToStringArray(String jsonData,String name){
	    JsonObject jo=(JsonObject) new JsonParser().parse(jsonData);
		String str=jo.get(name).toString();
		String[] strArr=str.split(",");
		return strArr;
	}
	/**
	 * 实体类转Map
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map beanToMap(Object entity) throws Exception {
		 Class type=entity.getClass();
		 Map<String,Object> returnMap=new HashMap<>();
		 BeanInfo beanInfo=Introspector.getBeanInfo(type);
		 PropertyDescriptor[]  pd=beanInfo.getPropertyDescriptors();
		 for(PropertyDescriptor descriptor:pd){
			 String propertyName=descriptor.getName();
			 //System.out.println("******"+propertyName+"*****");
			 if(!propertyName.equals("class")){
				 Method readMethod=descriptor.getReadMethod();
				 Object result=readMethod.invoke(entity);
				 if(result!=null){
					 returnMap.put(propertyName, result);
				 }else{
					 returnMap.put(propertyName, "");
				 }
			 }
		 }
		return returnMap;
	}
	
	
	public static Map<String,Object> entityToMap(Object entity) throws Exception{
		Map<String,Object> map=new HashMap<>();
		if(entity!=null){
			Field[] fields=entity.getClass().getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				map.put(field.getName(), field.get(entity));
			}
		}
		return map;
	}
	
	
	
	
	
}
