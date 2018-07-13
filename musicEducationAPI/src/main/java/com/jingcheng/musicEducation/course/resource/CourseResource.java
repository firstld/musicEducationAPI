package com.jingcheng.musicEducation.course.resource;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jingcheng.musicEducation.course.entity.Course;
import com.jingcheng.musicEducation.course.entity.CourseSection;
import com.jingcheng.musicEducation.course.service.CourseBackgroundService;
import com.jingcheng.musicEducation.util.JsonResult;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.auth.JWTAuth;
import com.jingcheng.musicEducation.util.page.PagePojo;

@Component
@Path("/course")
public class CourseResource {
  @Autowired
  private CourseBackgroundService  courseService;
  @Context
  private HttpServletRequest req;
  @Context
  private HttpServletResponse res;
  
  
  
  
  /**
   * 添加课程
   * @param course
   * @return
   */
  @JWTAuth
  @POST
  @Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult addCourse(@RequestBody Course course){
	  System.out.println(course);
	  courseService.addCourse(course);  
     return  new JsonResult("save ok");
  }
  
  /**
   * 在课程下添加课程小节
   * @param id：此课程id
   * @param section：课程小节对象
   * @return
   */
  @JWTAuth
  @POST
  @Path("/{id}/courseSection")
  @Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult addCourseSection(@RequestBody CourseSection section){
	  courseService.addCourseSection(section);
	  
	  return new JsonResult("save ok");
  }
  /**
   * 更新课程并返回更新后的数据
   * @param id：课程id
   * @param course
   * @return
   */
  
  @JWTAuth
  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult modifyCourse(@PathParam("id") Integer id,
		                         @RequestBody Course course){
	  courseService.modifyCourse(course);
	  Course newCourse=null;
	  if(res.getStatus()==200){
		  newCourse=courseService.getCourseById(id);
	  }  
	  return new JsonResult(newCourse);
  }
  /**
   * 更新课程下的小节信息，并返回更新后的课程小节信息
   * @param sectionId：课程小节id
   * @param section：课程小节对象
   * @return
   */
  @JWTAuth
  @PUT
  @Path("/{id}/courseSection/{sectionId}")
  @Consumes(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult modifyCourseSection(@PathParam("sectionId") Integer sectionId,
		                                @RequestBody CourseSection section){
	  courseService.modifyCourseSection(section);
	  CourseSection courseSection=null;
	  if(res.getStatus()==200){
		  courseSection=courseService.getSectionById(sectionId);
	  }
	  
	  return new JsonResult(courseSection);
  }
  /**
   * 批量删除课程小节，并返回删除小节后的课程信息
   * @param ids：课程小节id的数组
   * @param id：课程id
   * @return
   */
  @JWTAuth
  @POST
  @Path("/{id}/courseSection/delete")
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult deleteCourseSection(@RequestBody String jsonData,
		                                @PathParam("id") Integer id){
	  JsonObject jsonObject=(JsonObject)new JsonParser().parse(jsonData);
	    JsonArray  ids=jsonObject.get("ids").getAsJsonArray();
	    Integer[] idArr=new Integer[ids.size()];  
	    for(int i=0;i<ids.size();i++){
	    	idArr[i]=ids.get(i).getAsInt();
	    }
	  courseService.deleteCourseSection(idArr, id);
	  Course course=null;
	  if(res.getStatus()==200){
		 course=courseService.getCourseById(id);
	  }	  
	  return new JsonResult(course);
  }
  /**
   * 删除课程
   * @param id：课程id
   * @return
   */
  @JWTAuth
  @PUT
  @Path("/{id}/removeCourse")
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult removeCourse(@PathParam("id") Integer id){
	  courseService.deleteCourse(id);
	  return new JsonResult("remove ok");
  }
  /**
   * 获取课程列表
   * @return
   */
  @JWTAuth
  @GET
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult getCourseList(){
	  Map<String,String[]> map=req.getParameterMap();
	  //System.out.println(map);
	  Map<String,Object> params=MyUtil.stringArrayToObject(map);
	  //System.out.println(params);
	  PagePojo pojo=courseService.getCourseList(params);
	  return new JsonResult(pojo);
  }
  /**
   * 获取课程信息
   * @param id
   * @return
   */
  @JWTAuth
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult getCourse(@PathParam("id") Integer id){
	  Course course=courseService.getCourseById(id);
	  return new JsonResult(course);
  }
  /**
   * 获取课程下某个小节的信息
   * @param sectionId
   * @return
   */
  @JWTAuth
  @GET
  @Path("/{id}/courseSection/{sectionId}")
  @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
  public JsonResult getCourseSection(@PathParam("sectionId") Integer sectionId){
	  CourseSection section=courseService.getSectionById(sectionId);
	  return new JsonResult(section);
  }
  
}
