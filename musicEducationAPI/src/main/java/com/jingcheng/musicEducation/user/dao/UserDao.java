package com.jingcheng.musicEducation.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jingcheng.musicEducation.common.CurdType;

@Repository
public interface UserDao extends CurdType {
    
	//统计日注册量
	List<Map<String,Object>> countDayRegisterUser(@Param("beginTime")String beginTime,@Param("endTime") String endTime);
	
	//统计月注册量
	List<Map<String,Object>> countMonthRegisterUser(@Param("beginTime")String beginTime,@Param("endTime") String endTime);
}
