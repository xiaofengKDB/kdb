package com.soecode.lyf.entity;

import java.util.Date;

public class OrderQuery {
	
	private int phone;
	
	private String wxopenId;
	
	private String orderId;
	
	private Date startTime;
	
	private Date endTime;

	/** 
	* 获取phone 
	* @return phone phone 
	*/
	public int getPhone() {
		return phone;
	}
	

	/** 
	* 设置phone 
	* @param phone phone 
	*/
	public void setPhone(int phone) {
		this.phone = phone;
	}
	

	/** 
	* 获取orderId 
	* @return orderId orderId 
	*/
	public String getOrderId() {
		return orderId;
	}
	

	/** 
	* 设置orderId 
	* @param orderId orderId 
	*/
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	

	/** 
	* 获取startTime 
	* @return startTime startTime 
	*/
	public Date getStartTime() {
		return startTime;
	}
	

	/** 
	* 设置startTime 
	* @param startTime startTime 
	*/
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	

	/** 
	* 获取endTime 
	* @return endTime endTime 
	*/
	public Date getEndTime() {
		return endTime;
	}
	

	/** 
	* 设置endTime 
	* @param endTime endTime 
	*/
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	/** 
	* 获取wxopenId 
	* @return wxopenId wxopenId 
	*/
	public String getWxopenId() {
		return wxopenId;
	}
	


	/** 
	* 设置wxopenId 
	* @param wxopenId wxopenId 
	*/
	public void setWxopenId(String wxopenId) {
		this.wxopenId = wxopenId;
	}
	
	

}
