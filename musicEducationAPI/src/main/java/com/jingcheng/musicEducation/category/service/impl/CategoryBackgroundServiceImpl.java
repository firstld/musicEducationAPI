package com.jingcheng.musicEducation.category.service.impl;

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
import com.jingcheng.musicEducation.category.dao.CategoryDao;
import com.jingcheng.musicEducation.category.entity.Category;
import com.jingcheng.musicEducation.category.service.CategoryBackgroundService;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;

@Service
@Transactional
@Scope("prototype")
public class CategoryBackgroundServiceImpl implements CategoryBackgroundService {
    
	@Autowired
	private CategoryDao categoryDao;
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addCategory(Category category) {
		if(StringUtils.isEmpty(category)){
			throw new ServiceException("参数不能为空");
		}
		Map<String,Object> params=new HashMap<String, Object>();
		String name=category.getName();
		String categorySn=category.getCategorySn();
		params.put("name", name);
		params.put("categorySn", categorySn);
		List<Object> list=categoryDao.findObjList(params);
		if(list.size()!=0){
			throw new ServiceException("数据已存在，请重新输入");
		}
			int rows=categoryDao.insertEntity(category);
			if(rows!=1){
				throw new ServiceException("添加失败");
			}

	}
    
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void modifyCategory(Category category) {
		if(StringUtils.isEmpty(category)){
			throw new ServiceException("参数不能为空");
		}
		int rows=categoryDao.updateEntity(category);
		if(rows!=1){
			throw new ServiceException("更新失败");
		}
	}

	@Override
	public Category getCategoryById(Integer id) {
		if(id==null){
			throw new ServiceException("参数不能为空");
		}
		Category category=(Category)categoryDao.findObjById(id);
		return category;
	}

	@Override
	public PagePojo getCategoryList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能为空");
		}
		Integer pageCurrent=Integer.valueOf((String)params.get("pageCurrent"));
		Integer pageSize=Integer.valueOf((String)params.get("pageSize"));
		String orderBy=(String)params.get("orderBy");
		String sort=(String)params.get("sort");
		String order=orderBy+" "+sort;
		PageHelper.startPage(pageCurrent, pageSize, order);
		List<Object> list=categoryDao.findObjList(params);
		PagePojo pojo=PageUtil.setPage(list);
		return pojo;
	}

}
