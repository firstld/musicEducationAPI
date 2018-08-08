package com.jingcheng.musicEducation.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.jingcheng.musicEducation.system.dao.FeedbackInfomationDao;
import com.jingcheng.musicEducation.system.entity.FeedbackInfomation;
import com.jingcheng.musicEducation.system.service.FeedbackInfomationService;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;

@Service
@Transactional
public class FeedbackInfomationServiceImpl implements FeedbackInfomationService {
    @Autowired
    private FeedbackInfomationDao  feedbackDao;
	
	@Override
	public PagePojo getFeedbackInfomationList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能为空");
		}
		Integer pageCurrent=Integer.valueOf(params.get("pageCurrent").toString());
		Integer pageSize=Integer.valueOf(params.get("pageSize").toString());
		String orderBy=(String)params.get("orderBy");
		String sort=(String)params.get("sort");
		if(pageCurrent==null||pageSize==null||orderBy==null||sort==null){
			throw new ServiceException("必要参数不能为空");
		}
		String order=orderBy+" "+sort;
		PageHelper.startPage(pageCurrent, pageSize, order);
		List<Object> list=feedbackDao.findObjList(params);
		PagePojo pojo=PageUtil.setPage(list);
		return pojo;
	}

	@Override
	public FeedbackInfomation getFeedbackInfomationById(Integer id) {
		if(id==null){
			throw new ServiceException("id不能为空");
		}
		FeedbackInfomation feedbackInfomation=(FeedbackInfomation)feedbackDao.findObjById(id);
		if(StringUtils.isEmpty(feedbackInfomation)){
			throw new ServiceException("无此数据");
		}
		return feedbackInfomation;
	}

}
