package com.jingcheng.musicEducation.comment.resource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jingcheng.musicEducation.comment.service.CommentBackgroundService;
import com.jingcheng.musicEducation.util.JsonResult;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.auth.JWTAuth;
import com.jingcheng.musicEducation.util.page.PagePojo;

@Component
@Path("/comment")
public class CommentResource {
   
	@Autowired
	private CommentBackgroundService  commentService;
	
	@Context
	private HttpServletRequest req;
	
	@Context
	private HttpServletResponse res;
	
	/**
	 * 获取评论信息
	 * @param id
	 * @param type
	 * @return
	 */
	@JWTAuth
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON+";charset="+"UTF-8")
	public JsonResult getCommentById(@PathParam("id")Integer id,
			                         @QueryParam("type")String type){
		Object obj=commentService.getCommentById(id, type);
		return new JsonResult(obj);
	}
    /**
     * 获取评论列表
     * @return
     */
	@JWTAuth
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset="+"UTF-8")
	public JsonResult getCommentList(){
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		PagePojo pojo=commentService.getCommentList(params);
		return new JsonResult(pojo);
	}
	/**
	 * 添加评论回复
	 * @param entity
	 * @param type
	 * @param id
	 * @return
	 */
	@JWTAuth
	@POST
	@Path("/{id}/replyComment")
	@Consumes(MediaType.APPLICATION_JSON+";charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+";charset="+"UTF-8")
	public JsonResult addReplyComment(@RequestBody Object entity,
			                          @QueryParam("type") String type,
			                          @PathParam("id") Integer id){
		commentService.addReplyComment(entity, type);
		
		if(res.getStatus()==200){
			Object obj=commentService.getCommentById(id, type);
			return new JsonResult(obj);
		}
		return null;
	}
	/**
	 * 删除评论
	 * @param ids
	 * @param type
	 * @return
	 */
	@JWTAuth
	@POST
	@Path("/deleteComment")
	@Consumes(MediaType.APPLICATION_JSON+";charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+";charset="+"UTF-8")
	public JsonResult removeComment(@RequestBody String jsonData,
			                        @QueryParam("type")String type){
		JsonObject jsonObject=(JsonObject)new JsonParser().parse(jsonData);
	    JsonArray  ids=jsonObject.get("ids").getAsJsonArray();
	    Integer[] idArr=new Integer[ids.size()];  
	    for(int i=0;i<ids.size();i++){
	    	idArr[i]=ids.get(i).getAsInt();
	    }
		commentService.removeComment(idArr, type);
		return new JsonResult("delete ok");
	}
	/**
	 * 删除评论回复
	 * @param ids
	 * @param type
	 * @param id
	 * @return
	 */
	@JWTAuth
	@POST
	@Path("/{id}/ReplyComment/delete")
	@Consumes(MediaType.APPLICATION_JSON+";charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+";charset="+"UTF-8")
	public JsonResult removeReplyComment(@RequestBody String jsonData,
			                             @QueryParam("type") String type,
			                             @PathParam("id") Integer id){	
	    	
		JsonObject jsonObject=(JsonObject)new JsonParser().parse(jsonData);
	    JsonArray  ids=jsonObject.get("ids").getAsJsonArray();
	    Integer[] idArr=new Integer[ids.size()];  
	    for(int i=0;i<ids.size();i++){
	    	idArr[i]=ids.get(i).getAsInt();
	    }
		commentService.removeReplyComment(idArr, type);
		Object obj=null;
		if(res.getStatus()==200){
		  obj=commentService.getCommentById(id, type);
		  
		}
		return new JsonResult(obj);
	}
	
	
}
