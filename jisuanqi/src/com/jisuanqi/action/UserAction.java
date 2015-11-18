package com.jisuanqi.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jisuanqi.bean.Result;
import com.jisuanqi.service.IUserService;

@Controller
public class UserAction {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/regist")
	@ResponseBody
	public Result regist(String username,String password){
		System.out.println("����regist");
		return userService.regist(username, password);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Result login(String username,String password){
		System.out.println("����login");
		System.out.println(password);
		return userService.login(username, password);
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Result logout(String username){
		System.out.println("����logout");
		return userService.logout(username);
	}
	
	@RequestMapping("/getNumber")
	@ResponseBody
	public Result getNumber(String username,HttpServletRequest request){
		System.out.println("����getNumber");
		return userService.getNumber(username, request);
	}
	@RequestMapping("/checkNumber")
	@ResponseBody
	public Result checkNumber(String username,String number,HttpServletRequest request){
		System.out.println("����checkNumber");
		return userService.checkNumber(username, number, request);
	}
	@RequestMapping("/revisePassword")
	@ResponseBody
	public Result revisePassword(String username,String newPassword){
		System.out.println("����revisePassword");
		return userService.revisePassword(username, newPassword);
	}
}
