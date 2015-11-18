package com.jisuanqi.service;


import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;

import com.jisuanqi.bean.Result;

public interface IUserService {
	public Result regist(String username,String password);
	public Result login(String username,String password);
	public Result logout(String username);
	public Result getNumber(String username,HttpServletRequest request);
	public Result checkNumber(String username,String number,HttpServletRequest request);
	public Result revisePassword(String username,String newPassword);
}
