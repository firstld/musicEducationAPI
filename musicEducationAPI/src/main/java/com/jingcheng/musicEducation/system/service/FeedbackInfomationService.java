package com.jingcheng.musicEducation.system.service;

import java.util.Map;

import com.jingcheng.musicEducation.system.entity.FeedbackInfomation;
import com.jingcheng.musicEducation.util.page.PagePojo;

public interface FeedbackInfomationService {

	PagePojo getFeedbackInfomationList(Map<String,Object> params);
	
	FeedbackInfomation getFeedbackInfomationById(Integer id);
}
