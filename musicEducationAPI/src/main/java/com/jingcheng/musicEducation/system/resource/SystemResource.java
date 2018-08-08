package com.jingcheng.musicEducation.system.resource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.jingcheng.musicEducation.system.entity.BroadcastImage;
import com.jingcheng.musicEducation.system.entity.PageImage;
import com.jingcheng.musicEducation.system.service.BroadcastImageBackgroundService;
import com.jingcheng.musicEducation.system.service.FeedbackInfomationService;
import com.jingcheng.musicEducation.system.service.PageImageBackgroundService;
import com.jingcheng.musicEducation.util.JsonResult;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.auth.JWTAuth;
import com.jingcheng.musicEducation.util.page.PagePojo;

@Component
@Path("/system")
public class SystemResource {

	@Context
	private HttpServletRequest req;
	@Context
	private HttpServletResponse res;
	
	
	@Autowired
	private BroadcastImageBackgroundService  broadcastImageService;
	
	@Autowired
	private PageImageBackgroundService  pageImageService;
	
	@Autowired
	private FeedbackInfomationService  feedbackService;
	
	
	/*
	 * 轮播图接口
	 */
	/**
	 * 添加轮播图
	 * @param broadcastImage
	 * @return
	 */
	@JWTAuth
	@POST
	@Path("/broadcastImage")
	@Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult addBroadcastImage(@RequestBody BroadcastImage broadcastImage){
		broadcastImageService.addBroadcastImage(broadcastImage);
		return new JsonResult("save ok");
	}
	/**
	 * 更新轮播图
	 * @param Image
	 * @param id
	 * @return
	 */
	@JWTAuth
	@Path("/broadcastImage/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult modifyBroadcastImage(@RequestBody BroadcastImage Image,
			                               @PathParam("id") Integer id){
		broadcastImageService.updateBroadcastImage(Image);
		BroadcastImage broadcastImage=null;
		if(res.getStatus()==200){
			broadcastImage=broadcastImageService.findImageById(id);
		}
		return new JsonResult(broadcastImage);
	}
	/**
	 * 获取轮播图图片详情
	 * @param id
	 * @return
	 */
	@JWTAuth
	@GET
	@Path("/broadcastImage/{id}")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getBroadcastImageById(@PathParam("id") Integer id){
		BroadcastImage broadcastImage=broadcastImageService.findImageById(id);
		return new JsonResult(broadcastImage);
	}
	/**
	 * 获取轮播图列表，分页获取
	 * @return
	 */
	@JWTAuth
	@GET
	@Path("/broadcastImage")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getBroadcastImageList(){
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		PagePojo pojo=broadcastImageService.findBroadcastImageList(params);
		return new JsonResult(pojo);
	}
	
	
	/*
	 * 页面管理接口
	 */
	/**
	 * 添加页面
	 * @param pageImage
	 * @return
	 */
	@JWTAuth
	@POST
	@Path("/pageImage")
	@Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult addPageImage(@RequestBody PageImage pageImage){
		pageImageService.addPageImage(pageImage);
		return new JsonResult("save ok");
	}	
	/**
	 * 更新页面
	 * @param pageImage
	 * @param id
	 * @return
	 */
	@JWTAuth
	@PUT
	@Path("/pageImage/{id}")
	@Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult modifyPageImage(@RequestBody PageImage pageImage,
			                          @PathParam("id")Integer id){
		pageImageService.updatePageImage(pageImage);
		if(res.getStatus()==200){
			PageImage image=pageImageService.getPageImageById(id);
			return new JsonResult(image);
		}
		
		return null;
	}
	/**
	 * 获取页面信息
	 * @param id
	 * @return
	 */
	@JWTAuth
	@GET
	@Path("/pageImage/{id}")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getPageImageById(@PathParam("id")Integer id){
		PageImage pageImage=pageImageService.getPageImageById(id);
		return new JsonResult(pageImage);
	}
	/**
	 *获取页面列表 
	 */
	@JWTAuth
	@GET
	@Path("/pageImage")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getPageImageList(){
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		PagePojo pojo=pageImageService.getPageImageList(params);
		return new JsonResult(pojo);
	}
	
	/*
	 * 反馈信息接口
	 */
	
	
	
	 
	
	
	
	
	
	
}
