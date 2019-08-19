package com.jingcheng.musicEducation.util.page;

import java.io.Serializable;
import java.util.List;

public class PagePojo implements Serializable {

	/**
	 * 分页实体类
	 */
	private static final long serialVersionUID = -8626236022969553426L;
	  /*当前页*/
		private Integer pageCurrent;
	  /*起始页*/
		private Integer startIndex;
	  /*总记录数*/
		private Long rowCount;
	  /*总页数*/
		private Integer pageCount;
	  /*页面大小（每页显示多少条数据）*/
		private Integer pageSize;
      /*结果集*/	
		private List<?> list;
		public List<?> getList() {
			return list;
		}
		public void setList(List<?> list) {
			this.list = list;
		}
		public Integer getPageCurrent() {
			return pageCurrent;
		}
		public void setPageCurrent(Integer pageCurrent) {
			this.pageCurrent = pageCurrent;
		}
		public Integer getStartIndex() {
			return startIndex;
		}
		public void setStartIndex(Integer startIndex) {
			this.startIndex = startIndex;
		}
		public Long getRowCount() {
			return rowCount;
		}
		public void setRowCount(Long rowCount) {
			this.rowCount = rowCount;
		}
		public Integer getPageCount() {
			return pageCount;
		}
		public void setPageCount(Integer pageCount) {
			this.pageCount = pageCount;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		@Override
		public String toString() {
			return "PagePojo [pageCurrent=" + pageCurrent + ", startIndex=" + startIndex + ", rowCount=" + rowCount
					+ ", pageCount=" + pageCount + ", pageSize=" + pageSize + ", list=" + list + "]";
		}
		
		
		

}
