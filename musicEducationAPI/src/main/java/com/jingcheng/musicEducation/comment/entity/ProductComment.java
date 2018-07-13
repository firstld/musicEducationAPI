package com.jingcheng.musicEducation.comment.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="productComment")
public class ProductComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041454458072558912L;
     
	 private Integer id;
	 private Integer productId;
	 private Integer userId;
	 private String  comment;
	 //评级：1-5星
	 private Integer rate;
	 private String  createdAt;
	 private List<?> productReplyComments;
	 private List<?> productCommentImages;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	
	public List<?> getProductReplyComments() {
		return productReplyComments;
	}
	public void setProductReplyComments(List<?> productReplyComments) {
		this.productReplyComments = productReplyComments;
	}
	public List<?> getProductCommentImages() {
		return productCommentImages;
	}
	public void setProductCommentImages(List<?> productCommentImages) {
		this.productCommentImages = productCommentImages;
	}
	@Override
	public String toString() {
		return "ProductComment [id=" + id + ", productId=" + productId + ", userId=" + userId + ", comment=" + comment
				+ ", rate=" + rate + ", createdAt=" + createdAt + ", productReplyComments=" + productReplyComments
				+ ", productCommentImages=" + productCommentImages + "]";
	}
	 
}
