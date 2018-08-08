package com.jingcheng.musicEducation.order.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 课程订单
 * @author cx
 *
 */
@XmlRootElement(name="courseOrder")
public class CourseOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2429423696065225526L;
     private Integer id;
     private String  courseOrderSn;
     //订单状态：“100”成功，“101”失败
     private String  status;
     private Integer userId;
     private String  transcationId;
     private String  payCallBack;
     private Double  payAmount;
     private String  payTime;
     private String  createdAt;
     private List<?>    orderFormList;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(String transcationId) {
		this.transcationId = transcationId;
	}
	public String getPayCallBack() {
		return payCallBack;
	}
	public void setPayCallBack(String payCallBack) {
		this.payCallBack = payCallBack;
	}
	public Double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public List<?> getOrderFormList() {
		return orderFormList;
	}
	public void setOrderFormList(List<?> orderFormList) {
		this.orderFormList = orderFormList;
	}
	@Override
	public String toString() {
		return "CourseOrder [id=" + id + ", courseOrderSn=" + courseOrderSn + ", status=" + status + ", userId="
				+ userId + ", transcationId=" + transcationId + ", payCallBack=" + payCallBack + ", payAmount="
				+ payAmount + ", payTime=" + payTime + ", createdAt=" + createdAt + ", orderFormList=" + orderFormList
				+ "]";
	}
     
     
     
	
}
