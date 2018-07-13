package com.jingcheng.musicEducation.comment.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="productReplyComment")
public class ProductReplyComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3131996799023178801L;
     private Integer id;
     private Integer productCommentId;
     private Integer userId;
     private String  replyComment;
     private String  createdAt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductCommentId() {
		return productCommentId;
	}
	public void setProductCommentId(Integer productCommentId) {
		this.productCommentId = productCommentId;
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
		return "ProductReplyComment [id=" + id + ", productCommentId=" + productCommentId + ", userId=" + userId
				+ ", replyComment=" + replyComment + ", createdAt=" + createdAt + "]";
	}
     
     
	
}
