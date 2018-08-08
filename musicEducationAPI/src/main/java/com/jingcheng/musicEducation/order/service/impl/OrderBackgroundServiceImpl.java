package com.jingcheng.musicEducation.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.jingcheng.musicEducation.course.dao.CourseDao;
import com.jingcheng.musicEducation.course.entity.Course;
import com.jingcheng.musicEducation.order.dao.CourseOrderDao;
import com.jingcheng.musicEducation.order.dao.CourseOrderFormDao;
import com.jingcheng.musicEducation.order.entity.CourseOrder;
import com.jingcheng.musicEducation.order.entity.CourseOrderForm;
import com.jingcheng.musicEducation.order.service.OrderBackgroundService;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;

@Service
@Transactional
public class OrderBackgroundServiceImpl implements OrderBackgroundService {
    
	@Autowired
	private CourseOrderDao courseOrderDao;
	
	@Autowired
	private CourseOrderFormDao courseOrderFormDao;
	
	@Autowired
	private CourseDao  courseDao;
	/**
	 * 获取订单列表
	 */
	@Override
	public PagePojo getOrderList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能为空");
		}
		Integer pageCurrent=Integer.valueOf(params.get("pageCurrent").toString());
		Integer pageSize=Integer.valueOf(params.get("pageSize").toString());
		String  orderBy=(String)params.get("orderBy");
		String  sort=(String)params.get("sort");
		String  type=(String)params.get("type");
		if(pageCurrent==null||pageSize==null||orderBy==null||type==null){
			throw new ServiceException("必要参数不能为空");
		}
		
		if(type=="course"){
		  String order=orderBy+" "+sort;
		  PageHelper.startPage(pageCurrent, pageSize, order);
		  List<Object> list=courseOrderDao.findObjList(params);
		  PagePojo pojo=PageUtil.setPage(list);
		  return pojo;
		}
		if(type=="product"){
			//此处填写商品订单列表分页
		}
		return null;
	}
    /**
     * 获取订单详情
     */
	@Override
	public Object getOrderById(Integer id,String type) {
		if(id==null||type==null){
			throw new ServiceException("参数不能为空");
		}
		if(type=="course"){
			CourseOrder  courseOrder=(CourseOrder) courseOrderDao.findObjById(id);
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("courseOrderId", id);
			List<Object> orderFormList=courseOrderFormDao.findObjList(params);
			courseOrder.setOrderFormList(orderFormList);
			return courseOrder;
		}
		if(type=="product"){
			//此处写商品订单详情
		}
		return null;
	}
    /**
     * 获取输出到excel中的列表
     */
	@Override
	public List<Object> getOrderExportList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能为空");
		}
		String type=params.get("type").toString();
		if(type==null){
			throw new ServiceException("必要参数不能为空");
		}
		if(type=="course"){
			List<Object> list=courseOrderDao.findObjList(params);
			return list;
		}
		if(type=="product"){
			//此处写商品订单的列表
		}
		
		return null;
	}
    /**
     * 获取订单表单详情
     */
	@Override
	public Object getOrderFormById(Integer id, String type) {
		if(id==null||type==null){
			throw new ServiceException("参数不能为空");
		}
		if(type=="course"){
			CourseOrderForm cof=(CourseOrderForm)courseOrderFormDao.findObjById(id);
			if(!StringUtils.isEmpty(cof)){
				Integer courseId=cof.getCourseId();
				Course course=(Course)courseDao.findObjById(courseId);
				cof.setCourse(course);
			}	
			return cof;
		}
		if(type=="product"){
			//此处写商品订单表单详情
		}
		return null;
	}

}
