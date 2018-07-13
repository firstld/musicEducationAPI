package com.jingcheng.musicEducation.common;

import org.springframework.stereotype.Component;

import com.jingcheng.musicEducation.util.JsonResult;

@Component
public interface MyResourceType {
   
	public JsonResult addObj( Object entity);
	
	public JsonResult modifyObj(Object entity);
	
	public JsonResult getObjById(Integer id);
	
	public JsonResult getObjList();
}
