package com.jingcheng.musicEducation.system.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pageImage")
public class PageImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1885091229738478174L;

	private Integer id;
	private String  imageUrl;
	private String  version;
	private String  description;
	//分类 “关于我们”，“协议”
	private String  category;
	private Integer status;
	private String  createdAt;
	private String  deletedAt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}
	@Override
	public String toString() {
		return "PageImage [id=" + id + ", imageUrl=" + imageUrl + ", version=" + version + ", description="
				+ description + ", category=" + category + ", status=" + status + ", createdAt=" + createdAt
				+ ", deletedAt=" + deletedAt + "]";
	}
	
}
