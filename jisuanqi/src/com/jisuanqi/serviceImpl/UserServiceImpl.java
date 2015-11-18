package com.jisuanqi.serviceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jisuanqi.bean.Result;
import com.jisuanqi.bean.User;
import com.jisuanqi.dao.IUserDao;
import com.jisuanqi.service.IUserService;
import com.jisuanqi.until.CodeUntil;
import com.jisuanqi.until.MailUntil;

@Service("iUserService")
@Transactional
public class UserServiceImpl implements IUserService{
	
	@Resource
	private IUserDao userDao;
	/**
	 * 注册
	 */
	public Result regist(String username,String password){
		Result result = new Result();
		User user = new User();
		User u = userDao.getUser(username);
		if(u!=null){
			result.setStatus("0");
			result.setTips("用户名已被占用");
			return result;
		}
		if(username==null || password==null){
			result.setStatus("0");
			result.setTips("用户名或密码不能为空");
			return result;
		}
		user.setUsername(username);
		user.setPassword(password);
		user.setState(0);
		userDao.save(user);
		result.setStatus("1");
		result.setTips("注册成功");
		return result;
	}
	/**
	 * 登录
	 */
	public Result login(String username, String password) {
		Result result = new Result();
		if(username==null || password==null){
			result.setStatus("0");
			result.setTips("用户名或密码不能为空");
			return result;
		}
		User user = userDao.getUser(username);
		if(user==null){
			result.setStatus("0");
			result.setTips("用户名不正确");
			return result;
		}
		if(user.getState()==1){
			result.setStatus("0");
			result.setTips("不能重复登录！");
			return result;
		}
		//System.out.println(password);
		//System.out.println(user.getPassword());
		if(password.equals(user.getPassword())){
			user.setState(1);//设置状态码
			userDao.update(user);
			result.setStatus("1");
			result.setTips("登录成功");			
		}else{
			result.setStatus("0");
			result.setTips("密码不正确");
		}
		return result;
	}
	
	/**
	 * 登出
	 */
	public Result logout(String username){
		Result result = new Result();
		User user = userDao.getUser(username);
		if(user==null){
			result.setStatus("0");
			result.setTips("该用户不存在");
			return result;
		}
		if(user.getState()==0){
			result.setStatus("0");
			result.setTips("不能重复注销！");
			return result;
		}
		user.setState(0);
		userDao.update(user);
		result.setStatus("1");
		result.setTips("注销成功！");
		return result;
	}
	/**
	 * 获取验证码
	 */
	public Result getNumber(String username,HttpServletRequest request) {
		Result result = new Result();
		if(username=="" || username==null){
			result.setStatus("0");
			result.setTips("用户名不能为空");
			return result;
		}
		HttpSession session = request.getSession();
		if(session.getAttribute(username)!=null){
			result.setStatus("0");
			result.setTips("30分钟内不能重复申请验证码");
			return result;
		}
		String str = CodeUntil.createCheckCode();		
		session.setAttribute(username, str);
		System.out.println(session.getAttribute(username));
		try {
			MailUntil.sendMail(str, username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setStatus("1");
		result.setTips("发送成功");
		return result;
	}
	/**
	 * 验证验证码
	 */
	public Result checkNumber(String username,String number,HttpServletRequest request){
		Result result = new Result();
		if(username=="" || username==null){
			result.setStatus("0");
			result.setTips("用户名不能为空");
			return result;
		}
		if(number=="" || number==null){
			result.setStatus("0");
			result.setTips("验证码不能为空");
			return result;
		}
		String checkCode =(String)request.getSession().getAttribute(username);
		System.out.println(checkCode);
		if(number.equals(checkCode)){
			result.setStatus("1");
			result.setTips("验证成功");
			return result;
		}else{
			result.setStatus("0");
			result.setTips("验证码不正确");
			return result;
		}
	}
	public Result revisePassword(String username, String newPassword) {
		Result result = new Result();
		if(username=="" || username==null){
			result.setStatus("0");
			result.setTips("用户名不能为空");
			return result;
		}
		User user = userDao.getUser(username);
		user.setPassword(newPassword);
		userDao.update(user);
		result.setStatus("1");
		result.setTips("修改密码成功");
		return result;
	}
}
