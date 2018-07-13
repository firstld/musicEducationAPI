package com.jingcheng.musicEducation.category.resource;

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

import com.jingcheng.musicEducation.category.entity.Category;
import com.jingcheng.musicEducation.category.service.CategoryBackgroundService;
import com.jingcheng.musicEducation.util.JsonResult;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.auth.JWTAuth;
import com.jingcheng.musicEducation.util.page.PagePojo;

@Component
@Path("/category")
public class CategoryResource {
    
	@Autowired
	private CategoryBackgroundService   categoryService;
	@Context
	private HttpServletRequest req;
	@Context
	private HttpServletResponse res;
	
	/**
	 * 添加分类
	 * @param category
	 * @return
	 */
	@JWTAuth
	@POST
	@Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult addCategory(@RequestBody Category category){
		categoryService.addCategory(category);
		return new JsonResult("save ok");
	}
	/**
	 * 更新
	 * @param category
	 * @param id
	 * @return
	 */
	@JWTAuth
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult modifyCategory(@RequestBody Category category,
			                         @PathParam("id") Integer id){
		categoryService.modifyCategory(category);
		Category newCategory=null;
		if(res.getStatus()==200){
			newCategory=categoryService.getCategoryById(id);
		}
		return new JsonResult(newCategory);
	}
	/**
	 * 查询单个分类信息
	 * @param id
	 * @return
	 */
	@JWTAuth
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getCategoryById(@PathParam("id") Integer id){
		Category category=categoryService.getCategoryById(id);
		return new JsonResult(category);
	}
	/**
	 * 查询列表
	 * @return
	 */
	@JWTAuth
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getCategoryList(){
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		PagePojo pojo=categoryService.getCategoryList(params);
		return new JsonResult(pojo);
		
	}
	
	
}
