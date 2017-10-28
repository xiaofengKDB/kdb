package com.soecode.lyf.common;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class ErrorResult implements Serializable{
	
	private static final long serialVersionUID = 4521021961844996809L;
	public static final int GENERAL_CODE = 999;
	public static final String GENERAL_NAME = "SERVICE_ERROR";
	private int errorCode;
	private String errorName;
	private String errorMessage;

	public ErrorResult(){
		this(GENERAL_CODE,GENERAL_NAME,null);
	}
	
	public ErrorResult(int code,String name,String message){
		this.setError(code, name, message);
	}
	
	public void setError(int code,String name,String message){
		this.errorCode = code;
		this.errorName = name;
		this.errorMessage = message;
	}
	
	public void setError(String name,String message){
		this.setError(GENERAL_CODE,name, message);
	}
	
	public boolean hasError(){
		return StringUtils.isNotBlank(this.errorMessage)&&StringUtils.isNotBlank(this.errorName);
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
