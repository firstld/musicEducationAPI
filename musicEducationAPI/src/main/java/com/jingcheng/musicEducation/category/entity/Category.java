package com.jingcheng.musicEducation.category.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7391537756905211325L;
    
	   private Integer id;
	   private String  name;
	   private String  description;
	   private String  categorySn;
	   private String  parentSn;
	   private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategorySn() {
		return categorySn;
	}
	public void setCategorySn(String categorySn) {
		this.categorySn = categorySn;
	}
	public String getParentSn() {
		return parentSn;
	}
	public void setParentSn(String parentSn) {
		this.parentSn = parentSn;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", categorySn=" + categorySn
				+ ", parentSn=" + parentSn + ", status=" + status + "]";
	}
	   
	   
	
}
