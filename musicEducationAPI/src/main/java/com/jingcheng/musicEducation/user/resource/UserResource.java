package com.jingcheng.musicEducation.user.resource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingcheng.musicEducation.user.entity.User;
import com.jingcheng.musicEducation.user.service.UserBackgroundService;
import com.jingcheng.musicEducation.util.JsonResult;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.auth.JWTAuth;
import com.jingcheng.musicEducation.util.page.PagePojo;

@Component
@Path("/user")
public class UserResource {
   @Autowired
   private UserBackgroundService  userService;
   @Context
   private HttpServletRequest req;
   
   
   
   /**
    * 获取某位用户的信息
    * @param id
    * @return
    */
   @JWTAuth
   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
   public JsonResult getUserById(@PathParam("id")Integer id){
	   User user=userService.getUserById(id);
	   return new JsonResult(user);
   }
   /**
    * 获取用户列表，根据请求参数获取不同的列表
    * @return 分页对象
    */
   @JWTAuth
   @GET
   @Path("/search")
   @PathParam(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
   public JsonResult getUsers(){
	  Map<String,String[]> reqMap=req.getParameterMap();
	  Map<String,Object> params=MyUtil.stringArrayToObject(reqMap);
	  PagePojo pojo=userService.getUserList(params);
	   return new JsonResult(pojo);
	   
   }
   /**
    * 统计用户注册量
    * @param beginTime:查询开始时间
    * @param endTime:查询结束时间
    * @param type:"day"表示统计每天的注册量；"month"表示统计每月的注册量
    * @return:每天或每月一一对应的统计量
    */
   @JWTAuth
   @GET
   @Path("/groupCount")
   @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
   public JsonResult groupCountRegister(@QueryParam("beginTime") String beginTime,
		                                @QueryParam("endTime") String endTime,
		                                @QueryParam("type") String type){
       Map<String,Integer> result=userService.groupCountRegister(beginTime, endTime, type);
	   return new JsonResult(result);
	   
   }
}
