package com.jingcheng.musicEducation.comment.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="courseReplyComment")
public class CourseReplyComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -551008550371667911L;
     private Integer id;
     private Integer courseCommentId;
     private Integer userId;
     private String  replyComment;
     private String  createdAt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseCommentId() {
		return courseCommentId;
	}
	public void setCourseCommentId(Integer courseCommentId) {
		this.courseCommentId = courseCommentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getReplyComment() {
		return replyComment;
	}
	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "CourseReplyComment [id=" + id + ", courseCommentId=" + courseCommentId + ", userId=" + userId
				+ ", replyComment=" + replyComment + ", createdAt=" + createdAt + "]";
	}
     
     
}
