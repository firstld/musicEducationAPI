package com.jingcheng.musicEducation.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jingcheng.musicEducation.common.CurdType;

@Repository
public interface CourseOrderDao extends CurdType {
    List<Map<String,Object>> countDayOrder(@Param("beginTime") String beginTime,@Param("endTime")String endTime);
    
    List<Map<String,Object>> countMonthOrder(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
}
