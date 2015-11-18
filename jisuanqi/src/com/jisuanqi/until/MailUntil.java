package com.jisuanqi.until;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//发送一封只带文本的邮件
public class MailUntil {
	public static void sendMail(String content,String username) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.host","smtp.sina.com");
		props.setProperty("mail.transport.protocol","smtp");
		props.setProperty("mail.smtp.auth","true");
		//1.创建session
		Session session = Session.getInstance(props);
		//2.开启debug，可以看到发送状态
		session.setDebug(true);
		//3.得到transport对象
		Transport ts = session.getTransport();
		//4.连接邮件服务器，发送用户名密码给smtp服务器
		ts.connect("smtp.sina.com","xun_mato","1234567");
		//5.创建邮件
		Message message = createMail(session,content,username);
		//6.发送邮件
		ts.sendMessage(message,message.getAllRecipients());
		ts.close();
	}
	
	public static MimeMessage createMail(Session session,String content,String username) throws Exception{
		//创建邮件
		MimeMessage message = new MimeMessage(session);
		//指明邮件的发件人
		message.setFrom(new InternetAddress("xun_mato@sina.com"));
		//指明邮件的收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(username));
		//邮件的标题
		message.setSubject("验证码");
		//邮件的正文
		message.setContent(content,"text/html;charset=UTF-8");
		return message;	
	}
}
