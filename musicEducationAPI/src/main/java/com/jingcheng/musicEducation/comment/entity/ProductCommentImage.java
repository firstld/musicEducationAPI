package com.jingcheng.musicEducation.comment.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="productCommentImage")
public class ProductCommentImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7826410482704173518L;
    
	 private Integer id;
	 private Integer productCommentId;
	 private String  imageUrl;
	 private Integer sort;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "ProductCommentImage [id=" + id + ", productCommentId=" + productCommentId + ", imageUrl=" + imageUrl
				+ ", sort=" + sort + "]";
	}
	 
	 
}
