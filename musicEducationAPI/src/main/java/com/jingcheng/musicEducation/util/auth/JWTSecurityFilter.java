package com.jingcheng.musicEducation.util.auth;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Priority(Priorities.AUTHENTICATION)//优先级
@Provider
@JWTAuth//自定义的注解
public class JWTSecurityFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext req) throws IOException {
			String token = req.getHeaderString("x-musciEducationAPI-token");
			if(!token.isEmpty()){
				long now = System.currentTimeMillis();
				Date exp=null;
				try {
					exp = JWTUtil.parseJWT(token).getExpiration();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				long ttlMillis = 30L * 24L * 3600L * 1000L;
				//判断是否过期
				if(now-exp.getTime()>ttlMillis){
					req.abortWith(Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity("Authentication failed").build());
				}else{
					try {
						Integer id=Integer.valueOf(JWTUtil.parseJWT(token).getSubject());
						if(id==null){
							req.abortWith(Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity("Authentication failed").build());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}						
			}else{
				req.abortWith(Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity("Authentication failed").build());
			} 		
	}

}
