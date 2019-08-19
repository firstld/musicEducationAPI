package com.jingcheng.musicEducation.util;

import java.security.MessageDigest;
//import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;
/**
 * MD5加密用于admin；
 * sha1加密用户user；
 * @author cx
 *
 */
public class PasswordUtil {
	
	private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',  
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  
	
	/**
	 * 生成盐
	 * 
	 */
	public static String createSalt(){
	/*	Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();*/
		String salt=UUID.randomUUID().toString().replace("-", "").substring(0, 16);
		return salt;
	}
	/**
	 * 生成含有随机盐的密码
	 */
	public static String generate(String password,String salt) {	
		String pwd= md5Hex(password + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = pwd.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = pwd.charAt(i / 3 * 2 + 1);
		}
		return new String(cs);
	}

	/**
	 * 校验密码是否正确
	 */
	public static boolean verify(String password, String md5) {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		return md5Hex(password + salt).equals(new String(cs1));
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要
	 */
	public static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}
    /**
     * 字节转换成字符串
     * @param bytes
     * @return
     */
	 private static String getFormattedText(byte[] bytes) {  
	        int len = bytes.length;  
	        StringBuilder buf = new StringBuilder(len * 2);  
	        // 把密文转换成十六进制的字符串形式  
	        for (int j = 0; j < len; j++) {  
	            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);  
	            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);  
	        }  
	        return buf.toString();  
	    }  
	  /**
	   * SHA1加密
	   * @param str
	   * @return
	   */
	    public static String encode(String str) {  
	        if (str == null) {  
	            return null;  
	        }  
	        try {  
	            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");  
	            messageDigest.update(str.getBytes());  
	            return getFormattedText(messageDigest.digest());  
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
	    }  
	
	
	
	public static void main(String[] args) {
		
		/*String salt=PasswordUtil.createSalt();
		System.out.println(salt);
		String password = generate("zhangsan",salt);
		
		System.out.println(password);
		System.out.println(verify("zhangsan", password));*/
		
		String password=encode("asdfghjkl");
		System.out.println(password);
		String p="asdfghjkl";
		boolean result=encode(p).equals(password);
		System.out.println(result);
		
		
	}
}
