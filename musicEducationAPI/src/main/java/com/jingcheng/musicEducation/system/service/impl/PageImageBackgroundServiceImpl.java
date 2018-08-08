package com.jingcheng.musicEducation.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.jingcheng.musicEducation.system.dao.PageImageDao;
import com.jingcheng.musicEducation.system.entity.PageImage;
import com.jingcheng.musicEducation.system.service.PageImageBackgroundService;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;

@Service
@Transactional
public class PageImageBackgroundServiceImpl implements PageImageBackgroundService {
    
	@Autowired
	private PageImageDao pageImageDao;
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addPageImage(PageImage pageImage) {
		if(StringUtils.isEmpty(pageImage)){
			throw new ServiceException("参数不能为空");
		}
		//查询是否已存在此数据
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("version", pageImage.getVersion());
		params.put("category", pageImage.getCategory());
		List<Object> list=pageImageDao.findObjList(params);
		if(list.size()!=0){
			throw new ServiceException("对象已存在，请重新输入");
		}else{
			//不存在则添加数据
			pageImage.setStatus(1);
			int row=pageImageDao.insertEntity(pageImage);
			if(row!=1){
				throw new ServiceException("添加失败");
			}else{
				//添加成功后，获取上一版本数据，然后修改状态
				params.put("status", 1);
				params.put("cateogry", pageImage.getCategory());
				List<Object> list2=pageImageDao.findObjList(params);
				for(Object entity:list2){
					PageImage image=(PageImage)entity;
					if(!image.getVersion().equalsIgnoreCase(pageImage.getVersion())){
						image.setStatus(0);
						image.setDeletedAt(pageImage.getCreatedAt());
					    pageImageDao.updateEntity(image);
					   
					}
				}
				
			}
		}

	}
    
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void updatePageImage(PageImage pageImage) {
		if(StringUtils.isEmpty(pageImage)){
			throw new ServiceException("参数不能为空");
		}
		int row=pageImageDao.updateEntity(pageImage);
		if(row!=1){
			throw new ServiceException("更新失败");
		}

	}

	@Override
	public PageImage getPageImageById(Integer id) {
		if(id==null){
			throw new ServiceException("参数不能为空");
		}
		PageImage pageImage=(PageImage)pageImageDao.findObjById(id);
		return pageImage;
	}

	@Override
	public PagePojo getPageImageList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能为空");
		}
		Integer pageCurrent=Integer.valueOf(params.get("pageCurrent").toString());
		Integer pageSize=Integer.valueOf(params.get("pageSize").toString());
		String orderBy=(String)params.get("orderBy");
		String sort=(String)params.get("sort");
		if(pageCurrent==null||pageSize==null||orderBy==null){
			throw new ServiceException("必要参数不能为空");
		}
	    String order=orderBy+" "+sort;
		PageHelper.startPage(pageCurrent, pageSize, order);
		List<Object> list=pageImageDao.findObjList(params);
		PagePojo pojo=PageUtil.setPage(list);
		return pojo;
	}

}
