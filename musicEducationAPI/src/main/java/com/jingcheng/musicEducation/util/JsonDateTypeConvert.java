package com.jingcheng.musicEducation.util;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
/**
 * 以注解的形式将数据库的时间转换成指定的显示格式
 * @author cx
 *
 */
public class JsonDateTypeConvert extends JsonSerializer<String> {

	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider Serializer)
			throws IOException, JsonProcessingException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Long s=new Long(value);//转换long类型时间戳
		Date date=new Date(s);//转换成date类型
		String dateStr=sdf.format(date);//将date转换成指定格式
		gen.writeString(dateStr);//以字符串的格式写入json格式中
		

	}

}
