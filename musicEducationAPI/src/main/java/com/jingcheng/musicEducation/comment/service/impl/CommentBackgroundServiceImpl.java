package com.jingcheng.musicEducation.comment.service.impl;

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
import com.jingcheng.musicEducation.comment.dao.CourseCommentDao;
import com.jingcheng.musicEducation.comment.dao.CourseReplyCommentDao;
import com.jingcheng.musicEducation.comment.dao.ProductCommentDao;
import com.jingcheng.musicEducation.comment.dao.ProductCommentImageDao;
import com.jingcheng.musicEducation.comment.dao.ProductReplyCommentDao;
import com.jingcheng.musicEducation.comment.entity.CourseComment;
import com.jingcheng.musicEducation.comment.entity.ProductComment;
import com.jingcheng.musicEducation.comment.service.CommentBackgroundService;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;

@Service
@Transactional
@Scope("prototype")
public class CommentBackgroundServiceImpl implements CommentBackgroundService {
    
	@Autowired
     private CourseCommentDao  courseCommentDao;
    @Autowired
     private CourseReplyCommentDao  courseReplyCommentDao;
    @Autowired
     private ProductCommentDao  productCommentDao;
    @Autowired
     private ProductReplyCommentDao productReplyCommentDao;
    @Autowired
     private ProductCommentImageDao  productCommentImageDao;
    /**
     * 添加评论回复
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addReplyComment(Object entity, String type) {
		if(StringUtils.isEmpty(entity)||type==null){
			throw new ServiceException("参数不能为空");
		}
		if(type.equals("course")){
			int rows=courseReplyCommentDao.insertEntity(entity);
			if(rows!=1){
				throw new ServiceException("课程评论回复添加失败");
			}
		}
		if(type.equals("product")){
			int rows=productReplyCommentDao.insertEntity(entity);
			if(rows!=1){
				throw new ServiceException("商品评论回复添加失败");
			}
		}

	}
    //删除回复
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void removeReplyComment(Integer[] ids, String type) {
		if(ids.length==0||type==null){
			throw new ServiceException("参数不能为空");
		}
		if(type.equals("course")){
			int rows=courseReplyCommentDao.deleteEntities(ids);
			if(rows!=ids.length){
				throw new ServiceException("课程评论回复删除失败");
			}
		}
		if(type.equals("product")){
			int rows=productReplyCommentDao.deleteEntities(ids);
			if(rows!=ids.length){
				throw new ServiceException("商品评论回复删除失败");
			}
		}

	}
    
	@Override
	public PagePojo getCommentList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能为空");
		}
		Integer pageCurrent=Integer.valueOf((String)params.get("pageCurrent"));
		Integer pageSize=Integer.valueOf((String)params.get("pageSize"));
		String  orderBy=(String)params.get("orderBy");
		String  sort=(String)params.get("sort");
		String  order=orderBy+" "+sort;
		String  type=(String)params.get("type");
		PagePojo pojo=new PagePojo();
		if(pageCurrent==null||pageSize==null||orderBy==null||type==null){
			throw new ServiceException("参数不能为空");
		}else{
			if(type.equals("course")){
				PageHelper.startPage(pageCurrent, pageSize, order);
				List<Object> list=courseCommentDao.findObjList(params);
				pojo=PageUtil.setPage(list);
				return pojo;
				
			}
			if(type.equals("product")){
				PageHelper.startPage(pageCurrent, pageSize, order);
				List<Object> list=productCommentDao.findObjList(params);
			    pojo=PageUtil.setPage(list);
			    return pojo;
			}	
		}
		return null;
	}
    //获取评论的信息
	@Override
	public Object getCommentById(Integer id, String type) {
		if(id==null||type==null){
			throw new ServiceException("参数不能为空");
		}
		if(type.equals("course")){
			CourseComment cc=(CourseComment)courseCommentDao.findObjById(id);
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("courseCommentId", id);
			List<Object> replyList=courseReplyCommentDao.findObjList(params);
			cc.setReplyCommentList(replyList);
			return cc;
			
		}
		if(type.equals("product")){
			ProductComment pc=(ProductComment)productCommentDao.findObjById(id);
			Map<String,Object> params=new HashMap<>();
			params.put("productCommentId", id);
			List<Object> replyList=productReplyCommentDao.findObjList(params);
			List<Object> imageList=productCommentImageDao.findObjList(params);
			pc.setProductReplyComments(replyList);
			pc.setProductCommentImages(imageList);
			return pc;
		}
		return null;
	}
	/**
	 * 删除评论
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void removeComment(Integer[] ids, String type) {
		if(ids.length==0||type==null){
			throw new ServiceException("参数不能为空");
		}
		if(type.equals("course")){
			int rows=courseCommentDao.deleteEntities(ids);
			if(rows!=ids.length){
				throw new ServiceException("删除失败");
			}
		}
        if(type.equals("product")){
        	int rows=productCommentDao.deleteEntities(ids);
        	if(rows!=ids.length){
				throw new ServiceException("删除失败");
			}
        }
	}

}
