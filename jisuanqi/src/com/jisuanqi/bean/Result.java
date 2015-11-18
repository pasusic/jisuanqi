package com.jisuanqi.bean;

import java.io.Serializable;

public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String OK = "1";	//请求成功
	public static final String FAIL = "0";	//参数非法或者没有数据
	public static final String ERROR = "500";	//服务器错误
	
	
	private Object data;	//返回的数据
	private String status;	//1.正常    2.错误    500.服务器错误
	private String tips;	//提示信息
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getOk() {
		return OK;
	}
	public static String getFail() {
		return FAIL;
	}
	public static String getError() {
		return ERROR;
	}
	@Override
	public String toString() {
		return "Result [data=" + data + ", status=" + status + ", tips=" + tips
				+ "]";
	}
	
}
