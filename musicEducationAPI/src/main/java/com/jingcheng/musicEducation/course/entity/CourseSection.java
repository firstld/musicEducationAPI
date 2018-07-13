package com.jingcheng.musicEducation.course.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="courseSection")
public class CourseSection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -959489958259089208L;
	
	private Integer id;
	//课程编码
	private Integer  courseId;
	//标题
	private String  title;
	//视频url
	private String  videoUrl;
	//文件url
	private String  fileUrl;
	//是否免费
	private Integer isFree;
	private String  createdAt;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Integer getIsFree() {
		return isFree;
	}
	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "CourseSection [id=" + id + ", courseId=" + courseId + ", title=" + title + ", videoUrl=" + videoUrl
				+ ", fileUrl=" + fileUrl + ", isFree=" + isFree + ", createdAt=" + createdAt + "]";
	}
	

}
