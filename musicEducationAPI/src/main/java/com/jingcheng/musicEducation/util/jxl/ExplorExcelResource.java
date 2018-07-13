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

import com.jingcheng.musicEducation.user.service.UserBackgroundService;
import com.jingcheng.musicEducation.util.MyUtil;

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
	
	@Context
	private HttpServletRequest req;
	
	@Context
	private HttpServletResponse res;
	/**
	 * @throws Exception 
	 * 
	 */
	//@JWTAuth
	@GET
	@Path("/explorUser")
	@Produces(MediaType.TEXT_PLAIN+ ";charset=" + "UTF-8")
	@Consumes(MediaType.APPLICATION_JSON+ ";charset=" + "UTF-8")
	public  Response explorExcel() throws Exception{
		Map<String,String[]> map=req.getParameterMap();
		Map<String,Object> params=MyUtil.stringArrayToObject(map);
		List<Object> objData=userService.getUsers(params);
		System.out.println(objData);
		String fileName="d://20180629.xls";
	    JxlUtil.explorExcel(objData, fileName);
	    File file=new File(fileName);
	    Response.ResponseBuilder res=Response.ok(file);
	    res.header("Content-Disposition", "attachment;filename='" + URLEncoder.encode(file.getName(), "utf-8") + "'");
		return res.build();
	}
}
