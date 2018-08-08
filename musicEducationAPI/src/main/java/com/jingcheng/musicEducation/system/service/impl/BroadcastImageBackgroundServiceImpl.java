package com.jingcheng.musicEducation.system.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.jingcheng.musicEducation.system.dao.BroadcastImageDao;
import com.jingcheng.musicEducation.system.entity.BroadcastImage;
import com.jingcheng.musicEducation.system.service.BroadcastImageBackgroundService;
import com.jingcheng.musicEducation.util.DateUtil;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;

@Service
@Transactional
public class BroadcastImageBackgroundServiceImpl implements BroadcastImageBackgroundService {
    
	@Autowired
	private BroadcastImageDao  imageDao;
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addBroadcastImage(BroadcastImage broadcastImage) {
		if(StringUtils.isEmpty(broadcastImage)){
			throw new ServiceException("参数不能为空");
		}
		Date now=new Date(System.currentTimeMillis());
		String createdAt=DateUtil.dateToStrLong(now);
		broadcastImage.setStatus(1);
		broadcastImage.setCreatedAt(createdAt);
		int row=imageDao.insertEntity(broadcastImage);
		if(row!=1){
			throw new ServiceException("添加失败");
		}else{
			Map<String,Object> params=new HashMap<String, Object>();
			//status=1 表示上架到轮播图之中  为0则表示已从轮播图下架
			params.put("status", 1);
			params.put("position", broadcastImage.getPosition());
			//查询轮播图中sort位置上的图片，对原有的轮播图图片进行属性修改
			List<Object> list=imageDao.findObjList(params);		
			BroadcastImage image=null;
			for(int i=0;i<list.size();i++){
				image=(BroadcastImage)list.get(i);
				//如果image的createdAt与当前时间不同，则修改其属性
				if(!image.getCreatedAt().equals(createdAt)){
					image.setStatus(0);
					image.setDeletedAt(createdAt);
					int rows =imageDao.updateEntity(image);
					if(rows!=1){
						throw new ServiceException("更新失败");
					}
				}
			}		
		}	
	}
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void updateBroadcastImage(BroadcastImage broadcastImage) {
		if(StringUtils.isEmpty(broadcastImage)){
			throw new ServiceException("参数不能为空");
		}
		int row=imageDao.updateEntity(broadcastImage);
		if(row!=1){
			throw new ServiceException("更新失败");
		}

	}

	@Override
	public BroadcastImage findImageById(Integer id) {
		if(id==null){
			throw new ServiceException("参数不能为空");
		}
		BroadcastImage broadcastImage=(BroadcastImage)imageDao.findObjById(id);
		if(StringUtils.isEmpty(broadcastImage)){
			throw new ServiceException("数据不存在");
		}
		return broadcastImage;
	}

	@Override
	public PagePojo findBroadcastImageList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能我空");
		}
	    Integer pageCurrent=Integer.valueOf(params.get("pageCurrent").toString());
	    Integer pageSize=Integer.valueOf(params.get("pageSize").toString());
	    String  orderBy=(String)params.get("orderBy");
	    String  sort=(String)params.get("sort");
	    if(pageCurrent==null||pageSize==null||orderBy==null){
	    	throw new ServiceException("必要参数不能为空");
	    }
	    String order=orderBy+" "+sort;
	    PageHelper.startPage(pageCurrent, pageSize, order);
	    List<Object> list=imageDao.findObjList(params);
	    PagePojo pojo=PageUtil.setPage(list);
		return pojo;
	}

}
