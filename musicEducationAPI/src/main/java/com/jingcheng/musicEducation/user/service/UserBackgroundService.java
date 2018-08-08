package com.jingcheng.musicEducation.user.service;

import java.util.List;
import java.util.Map;

import com.jingcheng.musicEducation.user.entity.User;
import com.jingcheng.musicEducation.util.page.PagePojo;

public interface UserBackgroundService {
    
	User getUserById(Integer id);
	
	PagePojo getUserList(Map<String,Object> map);
	
	//统计注册量
	//type:为统计注册量的类型   1:每天的统计（day） 2：每月的统计（month）
	Map<String,Long>groupCountRegister(String beginTime,String endTime,String type);

	List<Object> getUsers(Map<String,Object> map);
}
