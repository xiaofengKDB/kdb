package com.soecode.lyf.common;

/**
 * 请求参数实体
 * @author 401681
 *
 */
public class RequestParameter {
	private int pageSize = 10;  //每页显示条数
	private int pageNum = 1;
	private int offset = 0;
	private String searchCondition = null;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	
	
}
