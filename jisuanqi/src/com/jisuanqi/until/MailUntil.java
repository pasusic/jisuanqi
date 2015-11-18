package com.jisuanqi.until;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//����һ��ֻ���ı����ʼ�
public class MailUntil {
	public static void sendMail(String content,String username) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.host","smtp.sina.com");
		props.setProperty("mail.transport.protocol","smtp");
		props.setProperty("mail.smtp.auth","true");
		//1.����session
		Session session = Session.getInstance(props);
		//2.����debug�����Կ�������״̬
		session.setDebug(true);
		//3.�õ�transport����
		Transport ts = session.getTransport();
		//4.�����ʼ��������������û��������smtp������
		ts.connect("smtp.sina.com","xun_mato","1234567");
		//5.�����ʼ�
		Message message = createMail(session,content,username);
		//6.�����ʼ�
		ts.sendMessage(message,message.getAllRecipients());
		ts.close();
	}
	
	public static MimeMessage createMail(Session session,String content,String username) throws Exception{
		//�����ʼ�
		MimeMessage message = new MimeMessage(session);
		//ָ���ʼ��ķ�����
		message.setFrom(new InternetAddress("xun_mato@sina.com"));
		//ָ���ʼ����ռ���
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(username));
		//�ʼ��ı���
		message.setSubject("��֤��");
		//�ʼ�������
		message.setContent(content,"text/html;charset=UTF-8");
		return message;	
	}
}
