package com.soecode.lyf.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QueryBase {
	
	private static int		DEFAULT_PAGE_SIZE	= 20;

	/**
	 * pageSize
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 */

	protected int	pageSize	= DEFAULT_PAGE_SIZE;
	/**
	 * currentPage
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 */
	protected int	currentPage = 1;
	/**
	 * total
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 */
	protected long	total;

	/**
	 * start
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 */
	protected int	start = 0;

	/**
	 * end
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 */
	protected int	end = 20;

	/**
	 * getTotal
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */

	public long getTotal() {
		return total;
	}

	/**
	 * setTotal
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @param total
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * getPageSize
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * setPageSize
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize){
		if(pageSize != null && pageSize > 0){
			this.pageSize = pageSize;
		}
		this.setStartAndEnd();
	}

	/**
	 * getCurrentPage
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * setCurrentPage
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @param currentPage
	 */
	public void setCurrentPage(Integer currentPage) {
		if (currentPage != null && currentPage > 0) {
			this.currentPage = currentPage;
		}
		this.setStartAndEnd();
	}

	/**
	 * setStartAndEnd
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 */
	protected void setStartAndEnd(){
		this.start = (this.getCurrentPage() - 1) * this.getPageSize();
		if (this.start < 0){
			this.start = 0;
		}
		this.end = this.getStart() + this.getPageSize() - 1;
	}

	/**
	 * getStart
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */
	public int getStart() {
		return start;
	}

	/**
	 * setStart
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @param start
	 */
	public void setStart(Integer start) {
		if (start != null && start >= 0) {
			this.start = start;
		}
	}

	/**
	 * getEnd
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * setEnd
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @param end
	 */
	public void setEnd(Integer end) {
		if (end != null && end >= 0) {
			this.end = end;
		}
	}

	/**
	 * hasNextPage
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */
	public boolean hasNextPage() {
		return getCurrentPage() < getTotalPage() - 1;
	}

	/**
	 * hasPreviousPage
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */
	public boolean hasPreviousPage() {
		return getCurrentPage() > 1;
	}

	/**
	 * getTotalPage
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */
	public long getTotalPage() {
		if (total % (long) pageSize == 0)
			return total / (long) pageSize;
		else
			return total / (long) pageSize + 1;
	}

	/**
	 * toString
	 * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
	 * @return
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
