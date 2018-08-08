package com.jingcheng.musicEducation.util.jxl;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingcheng.musicEducation.order.service.OrderBackgroundService;
import com.jingcheng.musicEducation.user.service.UserBackgroundService;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.auth.JWTAuth;

/**
 * 
 * @author cx
 *
 */
@Component
@Path("/excel")
public class ExplorExcelResource {
     
	@Autowired
	private UserBackgroundService userService;
	
	@Autowired
	private OrderBackgroundService  orderService;
	
	@Context
	private HttpServletRequest req;
	
	@Context
	private HttpServletResponse res;
	/**
	 * 
	 * 用户表输出
	 * @throws Exception 
	 * 
	 */
	@JWTAuth
	@GET
	@Path("/explorUser")
	@Produces(MediaType.TEXT_PLAIN+ ";charset=" + "UTF-8")
	@Consumes(MediaType.APPLICATION_JSON+ ";charset=" + "UTF-8")
	public  Response explorExcel() throws Exception{
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		String fileName=params.get("fileName").toString();
		List<Object> objData=userService.getUsers(params);
	    JxlUtil.userExplorExcel(objData, fileName);
	    File file=new File(fileName);
	    Response.ResponseBuilder res=Response.ok(file);
	    res.header("Content-Disposition", "attachment;filename='" + URLEncoder.encode(file.getName(), "utf-8") + "'");
		return res.build();
	}
	/**
	 * 订单excel表
	 * @return
	 * @throws Exception
	 */
	@JWTAuth
	@GET
	@Path("/explorOrder")
	@Produces(MediaType.TEXT_PLAIN+ ";charset=" + "UTF-8")
	@Consumes(MediaType.APPLICATION_JSON+ ";charset=" + "UTF-8")
	public Response explorOrder() throws Exception{
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		String fileName=params.get("fileName").toString();
		List<Object> objData=orderService.getOrderExportList(params);
		String type=params.get("type").toString();
	    JxlUtil.orderExplorExcel(objData, fileName,type);
	    File file=new File(fileName);
	    Response.ResponseBuilder res=Response.ok(file);
	    res.header("Content-Disposition", "attachment;filename='" + URLEncoder.encode(file.getName(), "utf-8") + "'");
		return res.build();
	}
}
