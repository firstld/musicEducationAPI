package com.jingcheng.musicEducation.comment.service;

import java.util.Map;

import com.jingcheng.musicEducation.util.page.PagePojo;

public interface CommentBackgroundService {
     //回复评论
	 void addReplyComment(Object entity,String type);
	 //删除回复
	 void removeReplyComment(Integer[] ids,String type);
	 //查询评论列表
	 PagePojo getCommentList(Map<String,Object> params);
	 //获取评论信息
	 Object getCommentById(Integer id,String type);
	 //删除评论
	 void removeComment(Integer[] ids,String type);
}
