package com.jingcheng.musicEducation.course.service;

import java.util.Map;

import com.jingcheng.musicEducation.course.entity.Course;
import com.jingcheng.musicEducation.course.entity.CourseSection;
import com.jingcheng.musicEducation.util.page.PagePojo;

public interface CourseBackgroundService {
   
	void  addCourse(Course course);
	void  addCourseSection(CourseSection section);
    void  modifyCourse(Course course);
    void  modifyCourseSection(CourseSection section);
    
    //修改下架状态
    void  deleteCourse(Integer id);
    
    //进行真删除
    void  deleteCourseSection(Integer[] ids,Integer courseId);
    
    Course getCourseById(Integer id);
    
    CourseSection getSectionById(Integer id);
    
    PagePojo getCourseList(Map<String,Object> params);
    
    
    
}
