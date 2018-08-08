 
package com.jingcheng.musicEducation.course.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.jingcheng.musicEducation.course.dao.CourseDao;
import com.jingcheng.musicEducation.course.dao.CourseSectionDao;
import com.jingcheng.musicEducation.course.entity.Course;
import com.jingcheng.musicEducation.course.entity.CourseSection;
import com.jingcheng.musicEducation.course.service.CourseBackgroundService;
import com.jingcheng.musicEducation.util.DateUtil;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;


@Service
@Transactional
@Scope("prototype")
public class CourseBackgroundServiceImpl implements CourseBackgroundService {
    @Autowired
    private CourseDao courseDao;
    
    @Autowired
    private CourseSectionDao sectionDao;
	
    /**
     * 添加课程
     */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addCourse(Course course) {
		if(StringUtils.isEmpty(course)){
			throw new ServiceException("参数不能为空");
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("title", course.getTitle());
		map.put("teacherId", course.getTeacherId());
		List<Object> courses=courseDao.findObjList(map);
	    if(courses.size()!=0){
	    	throw new ServiceException("数据已存在，请重新输入");
	    }
	    int row=courseDao.insertEntity(course);
	    if(row!=1){
	    	throw new ServiceException("添加失败");
	    }
	}
	
	/**
	 * 添加课程小节
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addCourseSection(CourseSection section) {
		if(StringUtils.isEmpty(section)){
			throw new ServiceException("参数不能为空");
		}
		Map<String,Object> params=new HashMap<>();
		params.put("courseId", section.getCourseId());
		params.put("title", section.getTitle());
		List<Object> sectionList=sectionDao.findObjList(params);
		if(sectionList.size()!=0){
			throw new ServiceException("数据已添加，请重新输入");
		}
		int row=sectionDao.insertEntity(section);
		if(row!=1){
			throw new ServiceException("添加失败");
		}
		Course course=(Course)courseDao.findObjById(section.getCourseId());
		course.setSectionAmount(course.getSectionAmount()+1);
		Date date=new Date(System.currentTimeMillis());
		String sectionUpdateTime=DateUtil.dateToStrLong(date);
		course.setSectionUpdatedAt(sectionUpdateTime);
		courseDao.updateEntity(course);
	}
    /**
     * 课程更新
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void modifyCourse(Course course) {
		if(StringUtils.isEmpty(course)){
			throw new ServiceException("参数不能为空");
		}
		Course c=(Course)courseDao.findObjById(course.getId());
        if(c.equals(course)){
        	throw new ServiceException("与原有数据一致，修改参数");
        }
        int row=courseDao.updateEntity(course);
        if(row!=1){
        	throw new ServiceException("修新失败");
        }
        
	}
    /**
     *小节更新
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void modifyCourseSection(CourseSection section) {
		if(StringUtils.isEmpty(section)){
			throw new ServiceException("参数不能为空");
		}
		int row=sectionDao.updateEntity(section);
		if(row!=1){
			throw new ServiceException("更新失败");
		}

	}
    /**
     * 批量删除课程小节
     * 注：批量删除的课程小节必须是同一课程中
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void deleteCourseSection(Integer[] ids,Integer courseId) {
	    if(courseId==null){
	    	throw new ServiceException("选择课程");
	    }else{
	    	Course course=(Course)courseDao.findObjById(courseId);
	    	if(ids.length==0){
	 		   throw new ServiceException("请选择删除对象");
	 	   }
	 	  int rows=sectionDao.deleteEntities(ids);
	       if(rows!=ids.length){
	     	  throw new ServiceException("删除失败");
	       }else{
	     	  //删除小节后 更新课程的小节总数
	     	  course.setSectionAmount(course.getSectionAmount()-rows);
	     	  courseDao.updateEntity(course);
	       }
	    	
	    }
	}
    
    /**
     * 获取课程详情
     */
	@Override
	public Course getCourseById(Integer id) {
		if(id==null){
			throw new ServiceException("参数不能为空");
		}
		Course course=(Course)courseDao.findObjById(id);
		if(!StringUtils.isEmpty(course)){
			Map<String,Object> params=new HashMap<>();
			params.put("courseId", id);
			List<Object> sectionList=sectionDao.findObjList(params);
			course.setSectionList(sectionList);
		}
		return course;
	}
	
    /**
     * 获取课程小节详情
     */
	@Override
	public CourseSection getSectionById(Integer id) {
		if(id==null){
			throw new ServiceException("参数不能为空");
		}
		CourseSection section=(CourseSection)sectionDao.findObjById(id);
		return section;
	}
	
    /**
     * 获取课程列表，做分页处理
     */
	@Override
	public PagePojo getCourseList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能为空");
		}
		Integer pageCurrent=Integer.valueOf(params.get("pageCurrent").toString());
		Integer pageSize = Integer.valueOf(params.get("pageSize").toString());
		String  orderBy = params.get("orderBy").toString();
		//排序方式， 默认顺序 “asc”；  “desc”为倒序。
		//只有当orderBy存在的时候才存在sort
		String  sort = params.get("sort").toString();
		String order=orderBy+" "+sort;
		if(pageCurrent==null||pageSize==null||orderBy==null){
			throw new ServiceException("分页参数不能为空");
		}
		PageHelper.startPage(pageCurrent, pageSize, order);
		List<Object> courseList=courseDao.findObjList(params);
		PagePojo pojo=PageUtil.setPage(courseList);
		return pojo;
	}
    
	/**
	 * 修改课程状态为删除，做假删除
	 * Status=3
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void deleteCourse(Integer id) {
		if(id==null){
			throw new ServiceException("参数不能为空");
		}
		Course course=(Course)courseDao.findObjById(id);
		course.setStatus(3);
		Date date=new Date(System.currentTimeMillis());
		String deleteTime=DateUtil.dateToStrLong(date);
        course.setDeletedAt(deleteTime);
		courseDao.updateEntity(course);
		
	}

}
