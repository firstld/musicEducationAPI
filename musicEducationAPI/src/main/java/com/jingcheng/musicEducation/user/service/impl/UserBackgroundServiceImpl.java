package com.jingcheng.musicEducation.user.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.jingcheng.musicEducation.user.dao.UserDao;
import com.jingcheng.musicEducation.user.entity.User;
import com.jingcheng.musicEducation.user.service.UserBackgroundService;
import com.jingcheng.musicEducation.util.DateUtil;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;


@Service
@Transactional
public class UserBackgroundServiceImpl implements UserBackgroundService {
    @Autowired
    private UserDao userDao;
	
    
    /**
     * 获取用户个人信息
     */
	@Override
	public User getUserById(Integer id) {
		if(id==null){
			throw new ServiceException("id不能为空");
		}
		User user=(User)userDao.findObjById(id);
		return user;
	}
    
	/**
	 * 获取用户列表
	 */
	@Override
	public PagePojo getUserList(Map<String, Object> map) {
	    if(StringUtils.isEmpty(map)){
			throw new ServiceException("参数不能为空");
		}
		Integer pageCurrent=Integer.valueOf(map.get("pageCurrent").toString());
		Integer pageSize   =Integer.valueOf(map.get("pageSize").toString());
		String  orderBy    =(String)map.get("orderBy");
		String  sort       =(String)map.get("sort");
		String  order      = orderBy+" "+sort;
		if(pageCurrent==null||pageSize==null||orderBy==null){
			throw new ServiceException("参数不能为null");
		}
		PageHelper.startPage(pageCurrent,pageSize,order);
		List<Object> list=userDao.findObjList(map);
		PagePojo pagePojo=PageUtil.setPage(list);
		return pagePojo;
		
	}
    
	/**
	 * 分组统计用户的注册量
	 */
	@Override
	public Map<String, Long>groupCountRegister(String beginTime, String endTime, String type) {
		List<Map<String,Object>> results=new ArrayList<>();
		//申明一个返回的map结果集
		Map<String,Long> map=new TreeMap<>(new Comparator<String>() {
            //根据map的key值重写排序
			@Override
			public int compare(String key1, String key2) {		
				return key1.compareTo(key2);
			}
		});
		if(beginTime==null||type==null||endTime==null){
			throw new ServiceException("参数不能为空");
		}
			//type为“day”表示查询统计每天的注册量；为“month”表示查询统计每月的注册量
			if(type.equalsIgnoreCase("day")){
				results=userDao.countDayRegisterUser(beginTime,endTime);
				//获得两个时间内的所有日期
				List<String> everDaysList=DateUtil.getEverDays(beginTime, endTime);
				if(!StringUtils.isEmpty(results)){
					//遍历结果集，获得每一个map结果，并将map中的key对应的value重写写入一个map中
					for(Map<String,Object> resultMap:results){
						String key=(String)resultMap.get("createdAt");
						Long value=(Long)resultMap.get("count");
						map.put(key, value);
					}
					//遍历日期列表，如果不存在map中就写入map，值为0
					//如果需要设置列表的长度，只需添加一个判断即可，统计月份的也是同理
					/*if(everDaysList.size()>31){
						throw new ServiceException("超过查询范围");
					}*/
					for(String str:everDaysList){
						if(map.get(str)==null){
							String  key=str;
							Long value=0l;
							map.put(key, value);
						}	
					}		
				}
				return map;
			}
			if(type.equalsIgnoreCase("month")){
				results=userDao.countMonthRegisterUser(beginTime, endTime);
				//获取两个时间内所有的月份
				List<String> everMonthList=DateUtil.getEverMonth(beginTime, endTime);
				if(!StringUtils.isEmpty(results)){
					for(Map<String,Object> resultMap:results){
						String key=(String)resultMap.get("createdAt");
						Long value=(Long)resultMap.get("count");
						map.put(key, value);
					}
					for(String str:everMonthList){
						if(map.get(str)==null){
							String key=str;
							Long value=0l;
							map.put(key, value);
						}
					}
				}
				return map;
			}
		return null;
	}
    /**
     * 获取用户列表  用户输出到excel中
     */
	@Override
	public List<Object> getUsers(Map<String, Object> map) {
		List<Object> list=userDao.findObjList(map);
		return list;
	}

}
