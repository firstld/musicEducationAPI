package com.jingcheng.musicEducation.order.resource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingcheng.musicEducation.order.service.OrderBackgroundService;
import com.jingcheng.musicEducation.util.JsonResult;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.auth.JWTAuth;
import com.jingcheng.musicEducation.util.page.PagePojo;

@Component
@Path("/order")
public class OrderResource {
   
	@Autowired
	private OrderBackgroundService orderService;
	@Context
	private HttpServletRequest req;
	@Context
	private HttpServletResponse res;
	
	/**
	 * 获取订单列表
	 * @param：分页参数与type参数为必要参数
	 * @return
	 */
	@JWTAuth
	@GET
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getOrderList(){
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		PagePojo pojo=orderService.getOrderList(params);
		return new JsonResult(pojo);
	}
	/**
	 * 获取订单详情
	 * @param id
	 * @param type
	 * @return
	 */
	@JWTAuth
	@GET
	@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getOrderById(@PathParam("id") Integer id,
			                       @QueryParam("type") String type){
		Object entity=orderService.getOrderById(id, type);
		
		return new JsonResult(entity);
	}
	/**
	 * 获取订单表单详情
	 * @param orderFormId
	 * @param type
	 * @return
	 */
	@JWTAuth
	@GET
	@Path("/{id}/orderForm/{orderFormId}")
	@Produces(MediaType.APPLICATION_JSON+"; charset="+"UTF-8")
	public JsonResult getOrderFormById(@PathParam("orderFormId") Integer orderFormId,
			                           @QueryParam("type")String type){
		Object entity=orderService.getOrderFormById(orderFormId, type);
		return new JsonResult(entity);
	}
	
	
}
