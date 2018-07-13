package com.jingcheng.musicEducation.comment.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="courseComment")
public class CourseComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7993320074362276407L;
    
	
	private Integer id;
	private Integer courseId;
	private Integer userId;
	private String  comment;
	//评级：1-5星评级
	private Integer rate;
	private String  createdAt;
	private List<?> replyCommentList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public List<?> getReplyCommentList() {
		return replyCommentList;
	}
	public void setReplyCommentList(List<?> replyCommentList) {
		this.replyCommentList = replyCommentList;
	}
	@Override
	public String toString() {
		return "CoruseComment [id=" + id + ", courseId=" + courseId + ", userId=" + userId + ", comment=" + comment
				+ ", rate=" + rate + ", createdAt=" + createdAt + ", replyCommentList=" + replyCommentList + "]";
	}
	
	
	
	
}
