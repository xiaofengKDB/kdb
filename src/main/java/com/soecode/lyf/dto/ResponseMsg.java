package com.soecode.lyf.dto;

public class ResponseMsg {

	//error code : 0 :success ; non 0， error
	public int errorCode = 0;
	
	//returning message
	public String msg;

	public Object data;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
