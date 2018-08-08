package com.jingcheng.musicEducation.order.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jingcheng.musicEducation.course.entity.Course;
/**
 * 课程订单课程列表
 * @author cx
 *
 */
@XmlRootElement(name="courseOrderForm")
public class CourseOrderForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7634964851159725100L;
      
	private Integer id;
	private String  courseOrderSn;
	private Integer courseId;
	private Double  payAmount;
	private String  status;
	private Course  course;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseOrderSn() {
		return courseOrderSn;
	}
	public void setCourseOrderSn(String courseOrderSn) {
		this.courseOrderSn = courseOrderSn;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "CourseOrderForm [id=" + id + ", courseOrderSn=" + courseOrderSn + ", courseId=" + courseId
				+ ", payAmount=" + payAmount + ", status=" + status + ", course=" + course + "]";
	}
	
	
}
