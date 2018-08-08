package com.jingcheng.musicEducation.system.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jingcheng.musicEducation.user.entity.User;

@XmlRootElement(name="feedbackInfomation")
public class FeedbackInfomation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4574151801150951004L;

	private Integer id;
	private Integer userId;
	private String  content;
	private String  feedbackTime;
	private User    user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "FeedbackInfomation [id=" + id + ", userId=" + userId + ", content=" + content + ", feedbackTime="
				+ feedbackTime + ", user=" + user + "]";
	}
	
}
