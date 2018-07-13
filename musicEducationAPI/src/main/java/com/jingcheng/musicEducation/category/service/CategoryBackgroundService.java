package com.jingcheng.musicEducation.category.service;

import java.util.Map;

import com.jingcheng.musicEducation.category.entity.Category;
import com.jingcheng.musicEducation.util.page.PagePojo;

public interface CategoryBackgroundService {
  
	void addCategory(Category category);
	void modifyCategory(Category category);
	
	Category getCategoryById(Integer id);
	
	PagePojo getCategoryList(Map<String,Object> params);
	
}
