package com.jingcheng.musicEducation.teacher.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.jingcheng.musicEducation.teacher.dao.TeacherDao;
import com.jingcheng.musicEducation.teacher.entity.Teacher;
import com.jingcheng.musicEducation.teacher.service.TeacherBackgroundService;
import com.jingcheng.musicEducation.util.Exception.ServiceException;
import com.jingcheng.musicEducation.util.page.PagePojo;
import com.jingcheng.musicEducation.util.page.PageUtil;

@Service
@Transactional
@Scope("prototype")
public class TeacherBackgroundServiceImpl implements TeacherBackgroundService {
    
	@Autowired
	private TeacherDao teacherDao;
	/**
	 * 添加
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addTeacher(Teacher teacher) {
		if(StringUtils.isEmpty(teacher)){
			throw new ServiceException("参数不能为空");
		}
		String teacherSn=teacher.getTeacherSn();
		String name=teacher.getName();
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("teacherSn", teacherSn);
		params.put("name", name);
		List<Object> list=teacherDao.findObjList(params);
		if(list.size()!=0){
			throw new ServiceException("数据已存在，请重新输入");
		}
        int rows=teacherDao.insertEntity(teacher);
        if(rows!=1){
        	throw new ServiceException("添加失败");
        }
	}
    /**
     * 修改
     */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void modifyTeacher(Teacher teacher) {
		if(StringUtils.isEmpty(teacher)){
			throw new ServiceException("参数 不能为空");
		}
		/*System.out.println("传过来的信息："+teacher);
		Teacher t=(Teacher)teacherDao.findObjById(teacher.getId());
		System.out.println("数据库中查到的信息："+t);
		if(t==teacher){
			System.out.println("*****************");
			throw new ServiceException("参数为改变，无法更新");
		}*/
		int rows=teacherDao.updateEntity(teacher);
		if(rows!=1){
			throw new ServiceException("更新失败");
		}

	}

	@Override
	public Teacher getTeacherById(Integer id) {
		if(id==null){
			throw new ServiceException("参数不能为空");
		}
		Teacher teacher=(Teacher)teacherDao.findObjById(id);
		return teacher;
	}

	@Override
	public PagePojo getTeacherList(Map<String, Object> params) {
		if(StringUtils.isEmpty(params)){
			throw new ServiceException("参数不能为空");
		}
		PagePojo pojo=null;
		Integer pageCurrent=Integer.valueOf((String)params.get("pageCurrent"));
		Integer pageSize=Integer.valueOf((String)params.get("pageSize"));
		String  orderBy=(String)params.get("orderBy");
		String  sort=(String)params.get("sort");
		if(pageCurrent!=null&&pageSize!=null&&orderBy!=null&&sort!=null){
			String order=orderBy+' '+sort;
			PageHelper.startPage(pageCurrent, pageSize, order);
			List<Object> teacherList=teacherDao.findObjList(params);
			pojo=PageUtil.setPage(teacherList);
		}else{
			throw new ServiceException("分页参数不足");
		}
		
		return pojo;
	}

}
