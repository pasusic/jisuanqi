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
	 * ע��
	 */
	public Result regist(String username,String password){
		Result result = new Result();
		User user = new User();
		User u = userDao.getUser(username);
		if(u!=null){
			result.setStatus("0");
			result.setTips("�û����ѱ�ռ��");
			return result;
		}
		if(username==null || password==null){
			result.setStatus("0");
			result.setTips("�û��������벻��Ϊ��");
			return result;
		}
		user.setUsername(username);
		user.setPassword(password);
		user.setState(0);
		userDao.save(user);
		result.setStatus("1");
		result.setTips("ע��ɹ�");
		return result;
	}
	/**
	 * ��¼
	 */
	public Result login(String username, String password) {
		Result result = new Result();
		if(username==null || password==null){
			result.setStatus("0");
			result.setTips("�û��������벻��Ϊ��");
			return result;
		}
		User user = userDao.getUser(username);
		if(user==null){
			result.setStatus("0");
			result.setTips("�û�������ȷ");
			return result;
		}
		if(user.getState()==1){
			result.setStatus("0");
			result.setTips("�����ظ���¼��");
			return result;
		}
		//System.out.println(password);
		//System.out.println(user.getPassword());
		if(password.equals(user.getPassword())){
			user.setState(1);//����״̬��
			userDao.update(user);
			result.setStatus("1");
			result.setTips("��¼�ɹ�");			
		}else{
			result.setStatus("0");
			result.setTips("���벻��ȷ");
		}
		return result;
	}
	
	/**
	 * �ǳ�
	 */
	public Result logout(String username){
		Result result = new Result();
		User user = userDao.getUser(username);
		if(user==null){
			result.setStatus("0");
			result.setTips("���û�������");
			return result;
		}
		if(user.getState()==0){
			result.setStatus("0");
			result.setTips("�����ظ�ע����");
			return result;
		}
		user.setState(0);
		userDao.update(user);
		result.setStatus("1");
		result.setTips("ע���ɹ���");
		return result;
	}
	/**
	 * ��ȡ��֤��
	 */
	public Result getNumber(String username,HttpServletRequest request) {
		Result result = new Result();
		if(username=="" || username==null){
			result.setStatus("0");
			result.setTips("�û�������Ϊ��");
			return result;
		}
		HttpSession session = request.getSession();
		if(session.getAttribute(username)!=null){
			result.setStatus("0");
			result.setTips("30�����ڲ����ظ�������֤��");
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
		result.setTips("���ͳɹ�");
		return result;
	}
	/**
	 * ��֤��֤��
	 */
	public Result checkNumber(String username,String number,HttpServletRequest request){
		Result result = new Result();
		if(username=="" || username==null){
			result.setStatus("0");
			result.setTips("�û�������Ϊ��");
			return result;
		}
		if(number=="" || number==null){
			result.setStatus("0");
			result.setTips("��֤�벻��Ϊ��");
			return result;
		}
		String checkCode =(String)request.getSession().getAttribute(username);
		System.out.println(checkCode);
		if(number.equals(checkCode)){
			result.setStatus("1");
			result.setTips("��֤�ɹ�");
			return result;
		}else{
			result.setStatus("0");
			result.setTips("��֤�벻��ȷ");
			return result;
		}
	}
	public Result revisePassword(String username, String newPassword) {
		Result result = new Result();
		if(username=="" || username==null){
			result.setStatus("0");
			result.setTips("�û�������Ϊ��");
			return result;
		}
		User user = userDao.getUser(username);
		user.setPassword(newPassword);
		userDao.update(user);
		result.setStatus("1");
		result.setTips("�޸�����ɹ�");
		return result;
	}
}
