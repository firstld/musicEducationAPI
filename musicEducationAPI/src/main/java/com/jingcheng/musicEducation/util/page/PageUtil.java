package com.jingcheng.musicEducation.util.page;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * 封装分页
 * @author cx
 *
 */
public class PageUtil {
  
	public static PagePojo setPage(List<Object> list){
		PageInfo<Object> pageInfo=new PageInfo<Object>(list);
		PagePojo pojo=new PagePojo();
		//页数总数
		pojo.setPageCount(pageInfo.getPages());
		//当前页
		pojo.setPageCurrent(pageInfo.getPageNum());
		//页面条数
		pojo.setPageSize(pageInfo.getPageSize());
		//总记录数
		pojo.setRowCount(pageInfo.getTotal());
		//起始页
		pojo.setStartIndex(pageInfo.getFirstPage());
		//结果集
		pojo.setList(list);
		return pojo;
		
	}
}
