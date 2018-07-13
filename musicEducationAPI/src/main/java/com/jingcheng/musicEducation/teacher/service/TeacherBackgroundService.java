package com.jingcheng.musicEducation.teacher.service;

import java.util.Map;

import com.jingcheng.musicEducation.teacher.entity.Teacher;
import com.jingcheng.musicEducation.util.page.PagePojo;

public interface TeacherBackgroundService {
   
	void addTeacher(Teacher teacher);
	void modifyTeacher(Teacher teacher);
	
	Teacher getTeacherById(Integer id);
	
	PagePojo getTeacherList(Map<String,Object> params);
}
