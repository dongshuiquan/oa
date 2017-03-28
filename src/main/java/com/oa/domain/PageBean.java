package com.oa.domain;

import java.util.List;

public class PageBean {
	//传递的参数或是配置的参数
	private int currentPage;	//当前页
	private int pageSize;		//每页显示多少条记录
	
	//查询数据库
	private List<?> recordList; //本页的数据列表
	private int recordCount;	//总记录数
	
	//计算
	private int pageCount;		//总页数
	private int beginPageIndex;	//页码列表的开始索引（包含）
	private int endPageIndex;	//页码列表的结束索引（包含）
	/**
	 * 接受4个必要的属性， 会自动计算出其它3个属性
	 * @param currentPage
	 * @param pageSize
	 * @param recordList
	 * @param recordCount
	 */
	public PageBean(int currentPage, int pageSize, List<?> recordList, int recordCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordList = recordList;
		this.recordCount = recordCount;
		// 计算pageCount
		pageCount = (recordCount + pageSize - 1) / pageSize;
		// 计算beginPageIndex 和 endPageIndex
		//总页码小于等于10， 全部显示
		if(pageCount <= 10){
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}else{	//总页码大于10， 显示当面附近的共 10 个
			//默认显示前4页 + 当前 + 后5页
			beginPageIndex = currentPage -4;
			endPageIndex = currentPage + 5;
			if(beginPageIndex < 1){
				beginPageIndex = 1;
				endPageIndex = 10;
			}else if(endPageIndex > pageCount){
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 9;
			}
			
		}
	}
	public List<?> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<?> recordList) {
		this.recordList = recordList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
}   
