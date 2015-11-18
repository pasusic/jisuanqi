package com.jisuanqi.dao;

import com.jisuanqi.bean.User;

public interface IUserDao {
	public void save(User user);
	
	public User getUser(String username); 
	
	public void update(User user);
}
