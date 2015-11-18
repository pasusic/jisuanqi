package com.jisuanqi.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.jisuanqi.bean.User;
import com.jisuanqi.dao.IUserDao;
@Repository("iUserDao")
public class UserDaoImpl implements IUserDao{

	@Resource
	private SessionFactory factory;
	
	public void save(User user) {
		Session session = factory.getCurrentSession();
		session.save(user);
		System.out.println("½øÈësave");
		
	}

	public User getUser(String username) {
		Session session = factory.getCurrentSession();
		String sql = "select * from User where username= '"+username+"'";
		Query query = session.createSQLQuery(sql).addEntity(User.class);
		List<User> list = query.list();
		if(list.size()!=0){
			User user = list.get(0);
			return user;
		}
		return null;
	}

	public void update(User user) {
		Session session = factory.getCurrentSession();
		session.update(user);
	}

}
