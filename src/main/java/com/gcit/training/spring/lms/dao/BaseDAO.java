package com.gcit.training.spring.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.entity.Publisher;


abstract public class BaseDAO<T>  {
	@Autowired
	protected 	JdbcTemplate template;
	

	private int pageNo = -1;

	private int pageSize = 10;

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		if(pageNo <1)
			pageNo = 1;
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * this function due the page number and the page size set the limits
	 */
	public int[] limits(){
		int max = pageNo* pageSize;
		int min = max-pageSize;
		
		return new int[]{min,max};
	}

}
