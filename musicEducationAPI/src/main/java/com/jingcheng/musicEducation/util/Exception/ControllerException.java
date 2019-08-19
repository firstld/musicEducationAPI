package com.jingcheng.musicEducation.util.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingcheng.musicEducation.util.JsonResult;
/**
 * 控制层全局异常封装
 * @author cx
 *
 */
@ControllerAdvice
public class ControllerException {
   
	 @ExceptionHandler(ServiceException.class)
     @ResponseBody
	 public JsonResult handlerServiceException(ServiceException e){
		 e.printStackTrace();
    	 return new JsonResult(e);
		 
	 }
}
