package com.jingcheng.musicEducation.teacher.resource;

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

import com.jingcheng.musicEducation.teacher.entity.Teacher;
import com.jingcheng.musicEducation.teacher.service.TeacherBackgroundService;
import com.jingcheng.musicEducation.util.JsonResult;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.auth.JWTAuth;
import com.jingcheng.musicEducation.util.page.PagePojo;

@Component
@Path("/teacher")
public class TeacherResource {

	@Autowired
	private TeacherBackgroundService teacherService;
	@Context
	private HttpServletRequest req;
	@Context
	private HttpServletResponse res;
	
	//@JWTAuth
	@POST
	@Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult addTeacher(@RequestBody Teacher teacher){
		teacherService.addTeacher(teacher);
		return new JsonResult("save ok");
	}
	
	/**
	 * 修改老师信息
	 * @param teacher
	 * @param id
	 * @return
	 */
	@JWTAuth
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult modifyTeacher(@RequestBody Teacher teacher,
			                        @PathParam("id") Integer id){
		teacherService.modifyTeacher(teacher);
		Teacher newTeacher=null;
		if(res.getStatus()==200){
			newTeacher=(Teacher)teacherService.getTeacherById(id);
		}
		
		return new JsonResult(newTeacher);
	}
	
	@JWTAuth
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getTeacherById(@PathParam("id") Integer id){
		Teacher teacher=(Teacher)teacherService.getTeacherById(id);
		return new JsonResult(teacher);
		
	}
	
	@JWTAuth
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getTeacherList(){
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		PagePojo pojo=teacherService.getTeacherList(params);
		return new JsonResult(pojo);
	}
	
	
	
	
}
