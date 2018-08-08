package com.jingcheng.musicEducation.system.service;

import java.util.Map;

import com.jingcheng.musicEducation.system.entity.PageImage;
import com.jingcheng.musicEducation.util.page.PagePojo;

public interface PageImageBackgroundService {
    
	void addPageImage(PageImage pageImage);
	
	void updatePageImage(PageImage pageImage);
	
	PageImage getPageImageById(Integer id);
	
	PagePojo getPageImageList(Map<String,Object> params);
}
