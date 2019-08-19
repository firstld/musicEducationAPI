package com.jingcheng.musicEducation.util;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.jingcheng.musicEducation.category.resource.CategoryResource;
import com.jingcheng.musicEducation.comment.resource.CommentResource;
import com.jingcheng.musicEducation.course.resource.CourseResource;
import com.jingcheng.musicEducation.order.resource.OrderResource;
import com.jingcheng.musicEducation.teacher.resource.TeacherResource;
import com.jingcheng.musicEducation.user.resource.UserResource;
import com.jingcheng.musicEducation.util.auth.JWTSecurityFilter;
import com.jingcheng.musicEducation.util.azure.UploadResource;
import com.jingcheng.musicEducation.util.jxl.ExplorExcelResource;

public class ApplicationConfig extends ResourceConfig {

	@SuppressWarnings("deprecation")
	public ApplicationConfig() {
		/**
		 *  加载resources
		 */
        //加载user模块
		register(UserResource.class);
		//加载course模块
		register(CourseResource.class);
        //加载teacher模块
	    register(TeacherResource.class);
	    //加载category模块
		register(CategoryResource.class);
		//加载comment模块
		register(CommentResource.class);
		//加载order模块
		register(OrderResource.class);
		
		
		
		//加载上传模块
		register(UploadResource.class);
		//加载Excel导出模块
		register(ExplorExcelResource.class);
		
		
		//加载model
		register(JsonResult.class);
		//注册数据转换器
		register(JacksonJsonProvider.class);
		//注册文件格式
		register(MultiPartFeature.class);
		//加载日志
		register(LoggingFilter.class);
		//加载过滤器
		register(JWTSecurityFilter.class);
		
	}
}
