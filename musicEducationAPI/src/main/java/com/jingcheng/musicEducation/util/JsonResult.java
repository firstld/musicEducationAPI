package com.jingcheng.musicEducation.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonResult {
	   public static final int SUCCESS=1;
	   public static final int ERROR=0;
	   /*状态:(SUCCESS,ERROR)*/
	   private int state;
	   /*状态信息*/
	   private String message;
	   /*统计数据
	   private int count;*/
	   /*具体数据*/
	   private Object data;
	   
	   
	   public JsonResult(){
		   state=SUCCESS;
	   }
	   
	   public JsonResult(String message){
		   this();
		   this.message=message;
		  
	   }
	  /* public JsonResult(int count){
		   this();
		   this.count=count;
	   }*/
	   public JsonResult(Object data){
		   this();
		   this.data=data;
	   }
	   public JsonResult(Throwable exp){
		   state=ERROR;
		   this.message=exp.getMessage();
	   }

		public int getState() {
			return state;
		}
		
		public String getMessage() {
			return message;
		}
		
		/*public int getCount() {
			return count;
		}
        
		public void setCount(int count) {
			this.count = count;
		}*/

		public Object getData() {
			return data;
		}
	   
	   
	}
