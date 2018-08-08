package com.jingcheng.musicEducation.order.service;

import java.util.List;
import java.util.Map;

import com.jingcheng.musicEducation.util.page.PagePojo;

public interface OrderBackgroundService {
    /**
     * 分页获取订单列表
     * @param params
     * @return
     */
	PagePojo getOrderList(Map<String,Object> params);
	/**
	 * 获取订单详情
	 * @param id
	 * @return
	 */
	Object getOrderById(Integer id,String type);
	
	/**
	 * 获取订单表单详情
	 * @param id
	 * @param type
	 * @return
	 */
	Object getOrderFormById(Integer id,String type);
	
	/**
	 * 用于根据条件输出列表到excel表
	 * @param params
	 * @return
	 */
	List<Object> getOrderExportList(Map<String,Object> params);
	
}
