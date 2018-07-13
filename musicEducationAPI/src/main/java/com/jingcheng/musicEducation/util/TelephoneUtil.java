package com.jingcheng.musicEducation.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 判断手机号码是否符合规则
 * @author cx
 *
 */
public class TelephoneUtil {

	public static boolean isTelephone(String telephoneNum) {
		//判断手机号码规则
		Pattern p=Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		//匹配正则表达式
		Matcher m=p.matcher(telephoneNum);
		//返回boolean。matches()表示完全匹配
		return m.matches();
	}
}
