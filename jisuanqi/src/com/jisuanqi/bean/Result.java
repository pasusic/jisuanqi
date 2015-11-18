package com.jisuanqi.bean;

import java.io.Serializable;

public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String OK = "1";	//����ɹ�
	public static final String FAIL = "0";	//�����Ƿ�����û������
	public static final String ERROR = "500";	//����������
	
	
	private Object data;	//���ص�����
	private String status;	//1.����    2.����    500.����������
	private String tips;	//��ʾ��Ϣ
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
