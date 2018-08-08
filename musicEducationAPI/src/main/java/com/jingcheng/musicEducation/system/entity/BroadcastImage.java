package com.jingcheng.musicEducation.system.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 轮播图
 * @author cx
 *
 */
@XmlRootElement(name="broadcastImage")
public class BroadcastImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8988110696318754931L;
     private Integer id;
     private String  imageUrl;
     private String  description;
     private Integer status;
     private Integer position;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
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
		return "BroadcastImage [id=" + id + ", imageUrl=" + imageUrl + ", description=" + description + ", status="
				+ status + ", position=" + position + ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + "]";
	}
     
     
}
