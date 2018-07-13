package com.jingcheng.musicEducation.course.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 课程
 * @author cx
 *
 */
@XmlRootElement(name="course")
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 245409379376499522L;

	private Integer id;
	//分类编号
	private String  categorySn;
	//课程级别
	private Integer level;
	//老师id
	private Integer teacherId;
	//课程名称
	private String  title;
	//简介
	private String  description;
	//封面
	private String  coverImage;
	//详情
	private String  details;
	//适用对象1：没有经验；2:1-3年经验；3:3年以上
	private Integer fitUser;
	//原价
	private Double  originPrice;
	//售价
	private Double  salePrice;
	//是否分销
	private Integer isDistribution;
	//状态1：正在更新；2：更新完毕;3:下架；4：删除
	private Integer status;
	//学习人数
	private Integer learnedAmount;
	//小节总数
	private Integer sectionAmount;
	//小节更新时间
	private String  sectionUpdatedAt;
	//小节列表
	private List<?>    sectionList;
	private String  createdAt;
	private String  updatedAt;
	//下架时间
	private String  deletedAt;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategorySn() {
		return categorySn;
	}

	public void setCategorySn(String categorySn) {
		this.categorySn = categorySn;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getFitUser() {
		return fitUser;
	}

	public void setFitUser(Integer fitUser) {
		this.fitUser = fitUser;
	}

	public Double getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(Integer isDistribution) {
		this.isDistribution = isDistribution;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLearnedAmount() {
		return learnedAmount;
	}

	public void setLearnedAmount(Integer learnedAmount) {
		this.learnedAmount = learnedAmount;
	}

	public Integer getSectionAmount() {
		return sectionAmount;
	}

	public void setSectionAmount(Integer sectionAmount) {
		this.sectionAmount = sectionAmount;
	}

	public String getSectionUpdatedAt() {
		return sectionUpdatedAt;
	}

	public void setSectionUpdatedAt(String sectionUpdatedAt) {
		this.sectionUpdatedAt = sectionUpdatedAt;
	}

	public List<?> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<?> sectionList) {
		this.sectionList = sectionList;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", categorySn=" + categorySn + ", level=" + level + ", teacherId=" + teacherId
				+ ", title=" + title + ", description=" + description + ", coverImage=" + coverImage + ", details="
				+ details + ", fitUser=" + fitUser + ", originPrice=" + originPrice + ", salePrice=" + salePrice
				+ ", isDistribution=" + isDistribution + ", status=" + status + ", learnedAmount=" + learnedAmount
				+ ", sectionAmount=" + sectionAmount + ", sectionUpdatedAt=" + sectionUpdatedAt + ", sectionList="
				+ sectionList + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt
				+ "]";
	}
	
	
}
