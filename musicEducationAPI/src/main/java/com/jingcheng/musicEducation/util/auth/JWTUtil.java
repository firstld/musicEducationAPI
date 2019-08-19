package com.jingcheng.musicEducation.util.auth;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	 private static final  String key="SLDLKK$DS743ss*dd@#@@gf";
	
    
  
    /** 
     * 创建jwt 
     * 
     * @param subject 
     * @param ttlMillis 
     * @return 
     * @throws Exception 
     */  
    public static String createJWT( String subject, long ttlMillis) throws Exception {  
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;  
        long nowMillis = System.currentTimeMillis();  
        Date now = new Date(nowMillis);  
        JwtBuilder builder = Jwts.builder()    
        	.setHeaderParam("typ", "x-musciEducationAPI-token")
        	.setHeaderParam("alg", "HS256")
        	.setIssuedAt(now)  
            .setSubject(subject)  
            .signWith(signatureAlgorithm, key);  
        if (ttlMillis >= 0) {  
            long expMillis = nowMillis + ttlMillis;  
            Date exp = new Date(expMillis);  
            builder.setExpiration(exp);  
        }  
        return builder.compact();  
    }  
    
    /** 
     * 解析jwt 
     * @param jwt 
     * @return 
     * @throws Exception 
     */  
    public static Claims parseJWT(String jwt) throws Exception{  
        Claims claims = Jwts.parser()           
           .setSigningKey(key)  
           .parseClaimsJws(jwt).getBody();  
  
        return claims;  
    }  
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public static String createToken(Integer id) throws Exception{
    	long ttlMillis = 30L * 24L * 3600L * 1000L;
		String subject = id.toString();
		// 生成token
		String jwtStr = JWTUtil.createJWT(subject, ttlMillis);
		/*Token token = new Token();
		token.setAccess_token(jwtStr);
		token.setExpires(ttlMillis);*/
		//JsonObject json=new JsonObject();
    	return jwtStr;
    }
    
    
    
    
    public static void main(String[] args) throws NumberFormatException, Exception {
    	//tring key="SLDLKK$DS743ss*dd@#@@gf";
    	/*String jwt="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXNzaW9uX2tleSI6ImRWLzUzdGF2blljejJKc3VveFc5NGc9PSIsImV4cGlyZXNfaW4iOjcyMDAsIm9wZW5pZCI6Im9WMEhzME91d1Fod2RPM05sbzhQX0d0ZHJRbnciLCJ1c2VyX2lkIjo5LCJpYXQiOjE1MTI2MTA2MDcsImV4cCI6MjExNzQxMDYwN30.OVg5HmMhg3Kx6qArDF-qanpS4JYK9HsPlf4ZF60Egp0";
	    Integer id=Integer.valueOf(parseJWT(jwt).getSubject());
	    System.out.println(id);*/
	   /* Long t=30L*24L*3600L*1000L;
	    Integer id=3;
	    String sub=id.toString();
	    String token=createJWT(sub, t);
	    System.out.println(token);
	    String sub1=parseJWT(token).getSubject();
	    System.out.println(sub1);*/
    	Integer id=6;
    	String token=createToken(id);
    	System.out.println(token);
    	Integer n=Integer.valueOf(parseJWT(token).getSubject());
    	System.out.println(n);
	    
    }
}
